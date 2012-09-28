package net.lemonmodel.patterns

import java.net.URI
import scala.xml._
import net.lemonmodel.rdfutil.RDFUtil._

/**
 * A verb
 */
trait Verb[V <: Verb[_]] extends Pattern {
  protected def makeWithForm(form : Form) : V
  protected def makeWithForms(forms : Seq[Form]) : V
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
  def withTable(namespace : Namespace, table : Map[(String,String),Any]) : V = {
    val forms = extractForms(namespace,table,Nil)
    makeWithForms(forms)
  }
  def lemma : String
  def forms : Seq[Form]
  def toXML(namer : URINamer, lang : String) = <lemon:LexicalEntry rdf:about={namer("verb",lemma)}>
      <lemon:canonicalForm>
        <lemon:LexicalForm rdf:about={namer("verb",lemma,Some("canonicalForm"))}>
          <lemon:writtenRep xml:lang={lang}>{lemma}</lemon:writtenRep>
        </lemon:LexicalForm>
      </lemon:canonicalForm> 
      <lexinfo:partOfSpeech rdf:resource={lexinfo("verb")}/>
      {
        for(form <- forms) yield {
          <lemon:otherForm>
            <lemon:LexicalForm rdf:about={namer("verb",lemma,Some("form"))}>
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
case class StateVerb(val lemma : String,
                     val sense : URI = null,
                     val propSubj : Arg = Subject, 
                     val propObj : Arg = DirectObject, 
                     val forms : Seq[Form] = Nil) extends Verb[StateVerb] {
  protected def makeWithForm(form : Form) = StateVerb(lemma,sense,propSubj,propObj,forms :+ form)
  protected def makeWithForms(extraForms : Seq[Form]) = StateVerb(lemma,sense,propSubj,propObj,forms ++ extraForms)
  protected def senseXML(namer : URINamer) = {
     val subjURI = namer("verb",lemma,Some("subject"))
     val objURI = namer("verb",lemma,Some("object"))
     <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("verb",lemma,Some("sense"))}>
         <lemon:reference>
           <rdf:Property rdf:about={sense}/>
         </lemon:reference>
         <lemon:semArg>
            <lemon:Argument rdf:about={subjURI}/>
            <lemon:Argument rdf:about={objURI}/>
         </lemon:semArg>
       </lemon:LexicalSense>
    </lemon:sense> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("verb",lemma,Some("frame"))}>
        { (propSubj,propObj) match {
            case (Subject,DirectObject) => <rdf:type rdf:resource={lexinfo("TransitiveFrame")}/>
            case (DirectObject, Subject) => <rdf:type rdf:resource={lexinfo("TransitiveFrame")}/>
            case (Subject,o : AdpositionalObject) => <rdf:type rdf:resource={lexinfo("IntransitivePPFrame")}/>
            case (o : AdpositionalObject, Subject) => <rdf:type rdf:resource={lexinfo("IntransitivePPFrame")}/>
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
trait EventVerb[V <: EventVerb[_]] extends Verb[V] {
  def eventClass : URI
  def args : Seq[OntologyFrameElement]
  def forms : Seq[Form]
  protected def oilsURI : URI
  protected def senseXML(namer : URINamer) = {
     val argURI = (for((arg,i) <- args.zipWithIndex) yield {
       arg -> namer("verb",lemma,Some("arg"+(i+1)))
     }).toMap
     <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("verb",lemma,Some("sense"))}>
         <lemon:reference>
           <rdfs:Class rdf:about={eventClass}>
           <rdfs:subClassOf rdf:resource={oilsURI}/>
           </rdfs:Class>
         </lemon:reference>
         {
           for((arg,i) <- args.zipWithIndex) yield {
            <lemon:subsense>
              <lemon:LexicalSense rdf:about={namer("verb",lemma,Some("subsense"+(i+1)))}>
                <lemon:semArg>
                  <lemon:Argument rdf:about={argURI(arg)}/>
                  {
                    if(arg.isOptional) {
                      <lemon:optional rdf:datatype="http://www.w3.org/2001/XMLSchema#boolean">true</lemon:optional>
                    } else {
                      <!-- Mandatory argument -->
                    }
                  }
                </lemon:semArg>
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
      <lemon:Frame rdf:about={namer("verb",lemma,Some("frame"))}>
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
  def apply(_lemma : String,
            _eventClass : URI = null,
            _args : Seq[OntologyFrameElement],
            _forms : Seq[Form] = Nil) : EventVerb[EventVerb[_]] = new EventVerb[EventVerb[_]] {
    protected def makeWithForm(form : Form) = EventVerb(lemma,eventClass,args,forms :+ form)
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
case class AchievementVerb(val lemma : String,
                           val eventClass : URI = null,
                           val args : Seq[OntologyFrameElement],
                           val forms : Seq[Form] = Nil) extends EventVerb[AchievementVerb] {
  protected def makeWithForm(form : Form) = AchievementVerb(lemma,eventClass,args,forms :+ form)
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
case class AccomplishmentVerb(val lemma : String,
                              val eventClass : URI = null,
                              val args : Seq[OntologyFrameElement],
                              val forms : Seq[Form] = Nil) extends EventVerb[AccomplishmentVerb] {
  protected def makeWithForm(form : Form) = AccomplishmentVerb(lemma,eventClass,args,forms :+ form)
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
case class SemelfactiveVerb(val lemma : String,
                            val eventClass : URI = null,
                            val args : Seq[OntologyFrameElement],
                            val forms : Seq[Form] = Nil) extends EventVerb[SemelfactiveVerb] {
  protected def makeWithForm(form : Form) = SemelfactiveVerb(lemma,eventClass,args,forms :+ form)
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
case class ActivityVerb(val lemma : String,
                        val eventClass : URI = null,
                        val args : Seq[OntologyFrameElement],
                        val forms : Seq[Form] = Nil) extends EventVerb[ActivityVerb] {
  protected def makeWithForm(form : Form) = ActivityVerb(lemma,eventClass,args,forms :+ form)
  protected def makeWithForms(extraForms : Seq[Form]) = ActivityVerb(lemma,eventClass,args,forms ++ extraForms)
  protected def oilsURI = URI.create("http://www.lemon-model.net/oils#ActivityVerb")
}

