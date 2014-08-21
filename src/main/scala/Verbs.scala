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
        <lemon:Form rdf:about={namer("verb",lemma.toString(),Some("canonicalForm"))}>
          <lemon:writtenRep xml:lang={lang}>{lemma.toString()}</lemon:writtenRep>
        </lemon:Form>
      </lemon:canonicalForm> 
      { lemma.toXML(namer,lang) }
      {
        for(form <- forms) yield {
          <lemon:otherForm>
            <lemon:Form rdf:about={namer("verb",lemma.toString(),Some("form"))}>
              <lemon:writtenRep xml:lang={lang}>{form.writtenRep}</lemon:writtenRep>
              {
                for((prop,propVal) <- form.props) yield {
                  val (prefixUri,prefix,suffix) = prefixURI(prop)
                  <lingonto:prop rdf:resource={propVal}/>.copy(prefix=prefix, label=suffix) %
                    Attribute("","xmlns:"+prefix,prefixUri,Null)
                }
              }
            </lemon:Form>
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
                     val forms : Seq[Form] = Nil,
                     val register : Option[Register] = None) extends Verb {
  def makeWithForm(form : Form) = StateVerb(lemma,sense,propSubj,propObj,forms :+ form,register)
  protected def makeWithForms(extraForms : Seq[Form]) = StateVerb(lemma,sense,propSubj,propObj,forms ++ extraForms,register)
  protected def senseXML(namer : URINamer) = {
     val subjURI = namer("verb",lemma.toString(),Some("subject"))
     val objURI = namer("verb",lemma.toString(),Some("object"))
     <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("verb",lemma.toString(),Some("sense"))}>
         <lemon:reference>
           <rdf:Property rdf:about={sense}/>
         </lemon:reference>
         {registerXML(register)}
         <lemon:subjOfProp>
            <lemon:Argument rdf:about={subjURI}/>
         </lemon:subjOfProp>
         <lemon:objOfProp>
            <lemon:Argument rdf:about={objURI}/>
         </lemon:objOfProp>
        { 
          if(propSubj.restriction != None) {
            <lemon:propertyDomain rdf:resource={propSubj.restriction.get}/>
          }
        }
        { 
          if(propObj.restriction != None) {
            <lemon:propertyRange rdf:resource={propObj.restriction.get}/>
          }
        }
       </lemon:LexicalSense>
    </lemon:sense> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("verb",lemma.toString(),Some("frame"))}>
        { (propSubj,propObj) match {
            case (ArgImpl(_,_,"subject"),ArgImpl(_,_,"directObject")) => <rdf:type rdf:resource={lexinfo("TransitiveFrame")}/>
            case (ArgImpl(_,_,"directObject"),ArgImpl(_,_,"subject")) => <rdf:type rdf:resource={lexinfo("TransitiveFrame")}/>
            case (ArgImpl(_,_,"subject"),PrepositionalObject(_,_,_)) => <rdf:type rdf:resource={lexinfo("IntransitivePPFrame")}/>
            case (PrepositionalObject(_,_,_),ArgImpl(_,_,"subject")) => <rdf:type rdf:resource={lexinfo("IntransitivePPFrame")}/>
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
case class EventVerb(val lemma : VP,
                     val eventClass : URI = null,
                     val args : Seq[OntologyFrameElement],
                     val telic : Option[Boolean] = None,
                     val durative : Option[Boolean] = None,
                     val forms : Seq[Form] = Nil,
                     val register : Option[Register] = None) extends Verb {
                       
  def makeWithForm(form : Form) = EventVerb(lemma,eventClass,args,telic,durative,forms :+ form,register)
  protected def makeWithForms(extraForms : Seq[Form]) = EventVerb(lemma,eventClass,args,telic,durative,forms ++ extraForms,register)
  protected def oilsURI = telic match {
    case Some(true) => durative match {
      case Some(true) => URI.create("http://www.lemon-model.net/oils#Accomplishment")
      case Some(false) => URI.create("http://www.lemon-model.net/oils#Achievement")
      case None => URI.create("http://www.lemon-model.net/oils#Event")
    }
    case Some(false) => durative match {
      case Some(true) => URI.create("http://www.lemon-model.net/oils#ActivityVerb")
      case Some(false) => URI.create("http://www.lemon-model.net/oils#SemelfactiveVerb")
      case None => URI.create("http://www.lemon-model.net/oils#Event")
    }
    case None => URI.create("http://www.lemon-model.net/oils#Event")
  }
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
         {registerXML(register)}
         {
           for((arg,i) <- args.zipWithIndex) yield {
            <lemon:subsense>
              <lemon:LexicalSense rdf:about={namer("verb",lemma.toString(),Some("subsense"+(i+1)))}>
                <lemon:objOfProp>
                  <lemon:Argument rdf:about={argURI(arg)}/>
                  {
                    if(arg.arg.isOptional) {
                      <lemon:optional rdf:datatype="http://www.w3.org/2001/XMLSchema#boolean">true</lemon:optional>
                    } else {
                      <!-- Mandatory argument -->
                    }
                  }
                </lemon:objOfProp>
                <lemon:reference>
                  <rdf:Property rdf:about={arg.property.getOrElse(namer.anonURI(arg))}/>
                </lemon:reference>
        { 
          if(arg.arg.restriction != None) {
            <lemon:propertyRange rdf:resource={arg.arg.restriction.get}/>
          }
        }
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
                
/**
 * Indicates an event verb that has a clear end but not beginning
 * @param form The canonical form of the verb (required)
 * @param eventClass The class of events for this verb
 * @param args The argument structure of the verb (required)
 * @param forms The set of other forms
 */
object AchievementVerb {
    def apply(_lemma : VP,
            _eventClass : URI = null,
            _args : Seq[OntologyFrameElement],
            _forms : Seq[Form] = Nil,
            _register : Option[Register] = None) : EventVerb = EventVerb(_lemma, _eventClass,
              _args,Some(true),Some(false),_forms,_register)
}
                           
 /**
 * Indicates an event verb that has a clear goal and started at some point
 * @param form The canonical form of the verb (required)
 * @param eventClass The class of events for this verb
 * @param args The argument structure of the verb (required)
 * @param forms The set of other forms
 */
object AccomplishmentVerb {
  def apply(_lemma : VP,
            _eventClass : URI = null,
            _args : Seq[OntologyFrameElement],
            _forms : Seq[Form] = Nil,
            _register : Option[Register] = None) : EventVerb = EventVerb(_lemma, _eventClass,
              _args,Some(true),Some(true),_forms,_register)
}
                           
 /**
 * Indicates an event verb that occurs at some point in time (without a duration)
 * @param form The canonical form of the verb (required)
 * @param eventClass The class of events for this verb
 * @param args The argument structure of the verb (required)
 * @param forms The set of other forms
 */
object SemelfactiveVerb {
                              def apply(_lemma : VP,
            _eventClass : URI = null,
            _args : Seq[OntologyFrameElement],
            _forms : Seq[Form] = Nil,
            _register : Option[Register]) : EventVerb = EventVerb(_lemma, _eventClass,
              _args,Some(false),Some(false),_forms,_register)
}
                           
/**
 * Indicates an event verb that occurs at some point of time
 * @param form The canonical form of the verb (required)
 * @param eventClass The class of events for this verb
 * @param args The argument structure of the verb (required)
 * @param forms The set of other forms
 */
object ActivityVerb {
  def apply(_lemma : VP,
            _eventClass : URI = null,
            _args : Seq[OntologyFrameElement],
            _forms : Seq[Form] = Nil,
            _register : Option[Register]) : EventVerb = EventVerb(_lemma, _eventClass,
              _args,Some(false),Some(true),_forms,_register)
}

case class ConsequenceVerb(val lemma : VP,
                           val conseqProp : URI,
                           val propSubj : OntologyFrameElement = OntologyFrameElement(None,Subject), 
                           val propObj : OntologyFrameElement = OntologyFrameElement(None,DirectObject), 
                           val eventClass : URI = null,
                           val forms : Seq[Form] = Nil,
                           val register : Option[Register] = None) extends Verb {
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
         {registerXML(register)}
         <lemon:subsense>
           <lemon:LexicalSense rdf:about={namer("verb",lemma.toString(),Some("sense"))}>
             <lemon:reference>
               <rdf:Property rdf:about={propSubj.property.getOrElse(namer.anonURI(propSubj))}/>
             </lemon:reference>
             <lemon:objOfProp>
               <lemon:Argument rdf:about={subjURI}/>
             </lemon:objOfProp>
        { 
          if(propSubj.arg.restriction != None) {
            <lemon:propertyDomain rdf:resource={propSubj.arg.restriction.get}/>
          }
        }
           </lemon:LexicalSense>
         </lemon:subsense>
         <lemon:subsense>
           <lemon:LexicalSense rdf:about={namer("verb",lemma.toString(),Some("sense"))}>
             <lemon:reference>
               <rdf:Property rdf:about={propObj.property.getOrElse(namer.anonURI(propObj))}/>
             </lemon:reference>
             <lemon:objOfProp>
               <lemon:Argument rdf:about={objURI}/>
             </lemon:objOfProp>
        { 
          if(propObj.arg.restriction != None) {
            <lemon:propertyRange rdf:resource={propObj.arg.restriction.get}/>
          }
        }
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
                 <owl:inverseOf rdf:resource={propSubj.property.getOrElse(namer.anonURI(propSubj))}/>
               </rdf:Description>
               <rdf:Description rdf:about={propObj.property.getOrElse(namer.anonURI(propObj))}/>
             </owl:propertyChainAxiom>
          </rdf:Property>
        </lemon:reference>
        { 
          if(propSubj.arg.restriction != None) {
            <lemon:propertyDomain rdf:resource={propSubj.arg.restriction.get}/>
          }
        }
        { 
          if(propObj.arg.restriction != None) {
            <lemon:propertyRange rdf:resource={propObj.arg.restriction.get}/>
          }
        }
        <lemon:subjOfProp rdf:resource={subjURI}/>
        <lemon:objOfProp rdf:resource={objURI}/>
      </lemon:LexicalSense>
    </lemon:sense> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("verb",lemma.toString(),Some("frame"))}>
        { (propSubj.arg,propObj.arg) match {
            case (ArgImpl(_,_,"subject"),ArgImpl(_,_,"directObject")) => <rdf:type rdf:resource={lexinfo("TransitiveFrame")}/>
            case (ArgImpl(_,_,"directObject"),ArgImpl(_,_,"subject")) => <rdf:type rdf:resource={lexinfo("TransitiveFrame")}/>
            case (ArgImpl(_,_,"subject"),PrepositionalObject(_,_,_)) => <rdf:type rdf:resource={lexinfo("IntransitivePPFrame")}/>
            case (PrepositionalObject(_,_,_), ArgImpl(_,_,"subject")) => <rdf:type rdf:resource={lexinfo("IntransitivePPFrame")}/>
            case _ => <!--Unrecognised frame-->
           }
        }
        { propSubj.arg.toXML(subjURI,namer) }
        { propObj.arg.toXML(objURI,namer) }
      </lemon:Frame>
    </lemon:synBehavior>
   }
}                           

