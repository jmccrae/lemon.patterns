package net.lemonmodel.patterns

import java.net.URI
import scala.xml._
import net.lemonmodel.rdfutil.RDFUtil._

/**
 * A verb
 */
trait Verb extends Pattern {
  def makeWithForm(form : Form) : Verb
  protected def makeWithForms(forms : Seq[Form]) : Verb
  protected def senseXML(namer : URINamer) : NodeSeq
  def extractForms(namespace : Namespace,  table : Map[(String,String),Any], props : List[(URI,URI)]) : Seq[Form] = {
    (for(((prop,propVal),subtable) <- table) yield {
      val propURI = namespace(prop)
      val propValURI = namespace(propVal)
      subtable match {
        case form : String => Seq(Form(form,(props :+ (propURI,propValURI)).toMap))
        case st : Map[_,_] => extractForms(namespace,st.asInstanceOf[Map[(String,String),Any]],props :+ (propURI,propValURI))
        case st : (_,_) => extractForms(namespace,Map(st).asInstanceOf[Map[(String,String),Any]],props :+ (propURI,propValURI))
        case fail => throw new IllegalArgumentException("Invalid value in a table " + fail.toString())
      }
    }).flatten.toSeq
  }
  def withTable(namespace : Namespace, table : Map[(String,String),Any]) : Verb = {
    val forms = extractForms(namespace,table,Nil)
    makeWithForms(forms)
  }
  def lemma : VP
  def forms : Seq[Form]
  def toXML(namer : URINamer, lang : String) = <lemon:LexicalEntry rdf:about={namer("verb",lemma.toString())}>
      <lemon:canonicalForm>
        <lemon:LexicalForm rdf:about={namer("verb",lemma.toString(),Some("canonicalForm"))}>
          <lemon:writtenRep xml:lang={lang}>{lemma.toString()}</lemon:writtenRep>
        </lemon:LexicalForm>
      </lemon:canonicalForm> 
      <lexinfo:partOfSpeech rdf:resource={lexinfo("verb")}/>
      {
        for(form <- forms) yield {
          <lemon:otherForm>
            <lemon:LexicalForm rdf:about={namer("verb",lemma.toString(),Some("form"))}>
              <lemon:writtenRep xml:lang={lang}>{form.writtenRep}</lemon:writtenRep>
              {
                for((prop,propVal) <- form.props) yield {
                  val (prefixUri,prefix,suffix) = prefixURI(prop)
                  <lingonto:prop rdf:resource={propVal}/>.copy(prefix=prefix, label=suffix) %
                    Attribute("","xmlns:"+prefix,prefixUri,Null)
                }
              }
            </lemon:LexicalForm>
          </lemon:otherForm>
        }   
      }
      {senseXML(namer)}
    </lemon:LexicalEntry>
}

/**
 * A state verb indicating a property
 * @param form The canonical form of the verb (required)
 * @param sense The sense of the verb
 * @param forms The set of other forms
 **/
case class StateVerb(val lemma : VP,
                     val sense : URI = null,
                     val propSubj : Arg = Subject, 
                     val propObj : Arg = DirectObject, 
                     val forms : Seq[Form] = Nil) extends Verb {
  def makeWithForm(form : Form) = StateVerb(lemma,sense,propSubj,propObj,forms :+ form)
  protected def makeWithForms(extraForms : Seq[Form]) = StateVerb(lemma,sense,propSubj,propObj,forms ++ extraForms)
  protected def senseXML(namer : URINamer) = {
     val subjURI = namer("verb",lemma.toString(),Some("subject"))
     val objURI = namer("verb",lemma.toString(),Some("object"))
     <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("verb",lemma.toString(),Some("sense"))}>
         <lemon:reference>
           <rdf:Property rdf:about={sense}/>
         </lemon:reference>
         <lemon:subjOfProp>
            <lemon:Argument rdf:about={subjURI}/>
         </lemon:subjOfProp>
         <lemon:objOfProp>
            <lemon:Argument rdf:about={objURI}/>
         </lemon:objOfProp>
       </lemon:LexicalSense>
    </lemon:sense> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("verb",lemma.toString(),Some("frame"))}>
        { (propSubj,propObj) match {
            case (s : SubjectArg,o : DirectObjectArg) => <rdf:type rdf:resource={lexinfo("TransitiveFrame")}/>
            case (o : DirectObjectArg,s : SubjectArg) => <rdf:type rdf:resource={lexinfo("TransitiveFrame")}/>
            case (s : SubjectArg,o : AdpositionalObject) => <rdf:type rdf:resource={lexinfo("IntransitivePPFrame")}/>
            case (o : AdpositionalObject, s : SubjectArg) => <rdf:type rdf:resource={lexinfo("IntransitivePPFrame")}/>
            case _ => <!--Unrecognised frame-->
           }
        }
        { propSubj.toXML(subjURI,namer) }
        { propObj.toXML(objURI,namer) }
      </lemon:Frame>
    </lemon:synBehavior>
   }
}
    
/**
 * Event verbs
 * @param form The canonical form of the verb (required)
 * @param eventClass The class of events for this verb
 * @param args The argument structure of the verb (required)
 * @param forms The set of other forms
 */
trait EventVerb extends Verb {
  def eventClass : URI
  def args : Seq[OntologyFrameElement]
  def forms : Seq[Form]
  protected def oilsURI : URI
  protected def senseXML(namer : URINamer) = {
     val argURI = (for((arg,i) <- args.zipWithIndex) yield {
       arg -> namer("verb",lemma.toString(),Some("arg"+(i+1)))
     }).toMap
     <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("verb",lemma.toString(),Some("sense"))}>
         <lemon:reference>
           <rdfs:Class rdf:about={eventClass}>
           <rdfs:subClassOf rdf:resource={oilsURI}/>
           </rdfs:Class>
         </lemon:reference>                        
         {
           for((arg,i) <- args.zipWithIndex) yield {
            <lemon:subsense>
              <lemon:LexicalSense rdf:about={namer("verb",lemma.toString(),Some("subsense"+(i+1)))}>
                <lemon:objOfProp>
                  <lemon:Argument rdf:about={argURI(arg)}/>
                  {
                    if(arg.isOptional) {
                      <lemon:optional rdf:datatype="http://www.w3.org/2001/XMLSchema#boolean">true</lemon:optional>
                    } else {
                      <!-- Mandatory argument -->
                    }
                  }
                </lemon:objOfProp>
                <lemon:reference>
                  <rdf:Property rdf:about={arg.property}/>
                </lemon:reference>
              </lemon:LexicalSense>
            </lemon:subsense>
           }
         }
       </lemon:LexicalSense>
    </lemon:sense> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("verb",lemma.toString(),Some("frame"))}>
        {
          for(arg <- args) yield {
            arg.arg.toXML(argURI(arg),namer)
          }
        }
      </lemon:Frame>
    </lemon:synBehavior>
   }
}

object EventVerb {
  def apply(_lemma : VP,
            _eventClass : URI = null,
            _args : Seq[OntologyFrameElement],
            _forms : Seq[Form] = Nil) : EventVerb = new EventVerb {
    def makeWithForm(form : Form) = EventVerb(lemma,eventClass,args,forms :+ form)
    protected def makeWithForms(extraForms : Seq[Form]) = EventVerb(lemma,eventClass,args,forms ++ extraForms)
    protected def oilsURI = URI.create("http://www.lemon-model.net/oils#Event")
    def lemma = _lemma
    def eventClass = _eventClass
    def args = _args
    def forms = _forms
  }
}
             
                
/**
 * Indicates an event verb that has a clear end but not beginning
 * @param form The canonical form of the verb (required)
 * @param eventClass The class of events for this verb
 * @param args The argument structure of the verb (required)
 * @param forms The set of other forms
 */
case class AchievementVerb(val lemma : VP,
                           val eventClass : URI = null,
                           val args : Seq[OntologyFrameElement],
                           val forms : Seq[Form] = Nil) extends EventVerb {
  def makeWithForm(form : Form) = AchievementVerb(lemma,eventClass,args,forms :+ form)
  protected def makeWithForms(extraForms : Seq[Form]) = AchievementVerb(lemma,eventClass,args,forms ++ extraForms)
  protected def oilsURI = URI.create("http://www.lemon-model.net/oils#Achievement")
}
                           
 /**
 * Indicates an event verb that has a clear goal and started at some point
 * @param form The canonical form of the verb (required)
 * @param eventClass The class of events for this verb
 * @param args The argument structure of the verb (required)
 * @param forms The set of other forms
 */
case class AccomplishmentVerb(val lemma : VP,
                              val eventClass : URI = null,
                              val args : Seq[OntologyFrameElement],
                              val forms : Seq[Form] = Nil) extends EventVerb {
  def makeWithForm(form : Form) = AccomplishmentVerb(lemma,eventClass,args,forms :+ form)
  protected def makeWithForms(extraForms : Seq[Form]) = AccomplishmentVerb(lemma,eventClass,args,forms ++ extraForms)
  protected def oilsURI = URI.create("http://www.lemon-model.net/oils#Accomplishment")
}
                           
 /**
 * Indicates an event verb that occurs at some point in time (without a duration)
 * @param form The canonical form of the verb (required)
 * @param eventClass The class of events for this verb
 * @param args The argument structure of the verb (required)
 * @param forms The set of other forms
 */
case class SemelfactiveVerb(val lemma : VP,
                            val eventClass : URI = null,
                            val args : Seq[OntologyFrameElement],
                            val forms : Seq[Form] = Nil) extends EventVerb {
  def makeWithForm(form : Form) = SemelfactiveVerb(lemma,eventClass,args,forms :+ form)
  protected def makeWithForms(extraForms : Seq[Form]) = SemelfactiveVerb(lemma,eventClass,args,forms ++ extraForms)
  protected def oilsURI = URI.create("http://www.lemon-model.net/oils#SemelfactiveVerb")
}
                           
/**
 * Indicates an event verb that occurs at some point of time
 * @param form The canonical form of the verb (required)
 * @param eventClass The class of events for this verb
 * @param args The argument structure of the verb (required)
 * @param forms The set of other forms
 */
case class ActivityVerb(val lemma : VP,
                        val eventClass : URI = null,
                        val args : Seq[OntologyFrameElement],
                        val forms : Seq[Form] = Nil) extends EventVerb {
  def makeWithForm(form : Form) = ActivityVerb(lemma,eventClass,args,forms :+ form)
  protected def makeWithForms(extraForms : Seq[Form]) = ActivityVerb(lemma,eventClass,args,forms ++ extraForms)
  protected def oilsURI = URI.create("http://www.lemon-model.net/oils#ActivityVerb")
}

case class ConsequenceVerb(val lemma : VP,
                           val conseqProp : URI,
                           val propSubj : OntologyFrameElement, 
                           val propObj : OntologyFrameElement, 
                           val eventClass : URI = null,
                           val forms : Seq[Form] = Nil) extends Verb {
  def makeWithForm(form : Form) = ConsequenceVerb(lemma,conseqProp,propSubj,propObj,eventClass,forms :+ form)
  protected def makeWithForms(extraForms : Seq[Form]) = ConsequenceVerb(lemma,conseqProp,propSubj,propObj,eventClass,forms ++ extraForms)
  protected def senseXML(namer : URINamer) = {
     val subjURI = namer("verb",lemma.toString(),Some("subject"))
     val objURI = namer("verb",lemma.toString(),Some("object"))
     <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("verb",lemma.toString(),Some("sense"))}>
         { if(eventClass != null)  {
             <lemon:reference>
               <rdf:Property rdf:about={eventClass}/>
             </lemon:reference>
           }
         }
         <lemon:subsense>
           <lemon:LexicalSense rdf:about={namer("verb",lemma.toString(),Some("sense"))}>
             <lemon:reference>
               <rdf:Property rdf:about={propSubj.property}/>
             </lemon:reference>
             <lemon:objOfProp>
               <lemon:Argument rdf:about={subjURI}/>
             </lemon:objOfProp>
           </lemon:LexicalSense>
         </lemon:subsense>
         <lemon:subsense>
           <lemon:LexicalSense rdf:about={namer("verb",lemma.toString(),Some("sense"))}>
             <lemon:reference>
               <rdf:Property rdf:about={propObj.property}/>
             </lemon:reference>
             <lemon:objOfProp>
               <lemon:Argument rdf:about={objURI}/>
             </lemon:objOfProp>
           </lemon:LexicalSense>
         </lemon:subsense>
       </lemon:LexicalSense>
    </lemon:sense> :+
    <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("verb",lemma.toString(),Some("sense"))}>
        <lemon:reference>
          <rdf:Property rdf:about={conseqProp}>
             <owl:propertyChainAxiom rdf:parseType="Collection">
               <rdf:Description>
                 <owl:inverseOf rdf:resource={propSubj.property}/>
               </rdf:Description>
               <rdf:Description rdf:resource={propObj.property}/>
             </owl:propertyChainAxiom>
          </rdf:Property>
        </lemon:reference>
      </lemon:LexicalSense>
    </lemon:sense> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("verb",lemma.toString(),Some("frame"))}>
        { (propSubj.arg,propObj.arg) match {
            case (s : SubjectArg,o : DirectObjectArg) => <rdf:type rdf:resource={lexinfo("TransitiveFrame")}/>
            case (o : DirectObjectArg,s : SubjectArg) => <rdf:type rdf:resource={lexinfo("TransitiveFrame")}/>
            case (s : SubjectArg,o : AdpositionalObject) => <rdf:type rdf:resource={lexinfo("IntransitivePPFrame")}/>
            case (o : AdpositionalObject, s : SubjectArg) => <rdf:type rdf:resource={lexinfo("IntransitivePPFrame")}/>
            case _ => <!--Unrecognised frame-->
           }
        }
        { propSubj.arg.toXML(subjURI,namer) }
        { propObj.arg.toXML(objURI,namer) }
      </lemon:Frame>
    </lemon:synBehavior>
   }
}                           

