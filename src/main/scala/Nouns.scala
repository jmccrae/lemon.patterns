package net.lemonmodel.patterns

import java.net.URI
import scala.xml._
import net.lemonmodel.rdfutil.RDFUtil._
        
/**
 * A noun
 */
trait Noun extends Pattern {
  def makeWithForm(form : Form) : Noun
  protected def senseXML(namer : URINamer) : NodeSeq
  protected def isProper = false
  def withPlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"))))
  def withAccusative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("accusativeCase"))))
  def withDative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("dativeCase"))))
  def withGenitive(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("genitiveCase"))))
  def withNominativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("nominativeCase"))))
  def withAccusativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("accusativeCase"))))
  def withGenitiveSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("genitiveCase"))))
  def withDativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("dativeCase"))))
  def withNominativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("nominativeCase"))))
  def withAccusativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("accusativeCase"))))
  def withGenitivePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("genitiveCase"))))
  def withDativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("dativeCase"))))
  def withGender(gender : Gender) : Noun
  def masculine = withGender(Masculine)
  def feminine = withGender(Feminine)
  def neuter = withGender(Neuter)
  def commonGender = withGender(CommonGender)
  def otherGender = withGender(OtherGender)
  def gender : Option[Gender]
  def lemma : NounPhrase
  def forms : Seq[Form]
  def toXML(namer : URINamer, lang : String) = <lemon:LexicalEntry rdf:about={namer("noun",lemma.toString())}>
      <lemon:canonicalForm>
        <lemon:Form rdf:about={namer("noun",lemma.toString(),Some("canonicalForm"))}>
          <lemon:writtenRep xml:lang={lang}>{lemma.toString()}</lemon:writtenRep>
        </lemon:Form>
      </lemon:canonicalForm>
      {gender match {
        case Some(g) => <lexinfo:gender rdf:resource={lexinfo(g.toString()).toString()}/>
        case None => 
        }
      }
      { lemma.toXML(namer,lang) }
      {
        for(form <- forms) yield {
          <lemon:otherForm>
            <lemon:Form rdf:about={namer("noun",lemma.toString(),Some("form"))}>
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

sealed trait Gender

object Masculine extends Gender { override def toString = "masculine" }
object Feminine extends Gender { override def toString = "feminine" }
object Neuter extends Gender { override def toString = "neuter" }
object CommonGender extends Gender { override def toString = "commonGender" }
object OtherGender extends Gender { override def toString = "otherGender" }

/**
* A name (proper noun) associated to a named individual in the ontology
* @param lemma The canonical form of the name (required)
* @param sense The URI of the associated named individual
* @param forms The set of other forms
*/
case class Name(val lemma : PNP, 
                val sense : URI = null, 
                val forms : Seq[Form] = Nil,
                val gender : Option[Gender] = None) extends Noun {
  def makeWithForm(form : Form) = Name(lemma,sense, forms :+ form,gender)
  def withGender(gender : Gender) = Name(lemma,sense,forms,Some(gender))
  protected def senseXML(namer : URINamer) = <lemon:sense>
       <lemon:LexicalSense rdf:about={namer("noun",lemma.toString(),Some("sense"))}>
         <lemon:reference>
           <owl:NamedIndividual rdf:about={sense}/>
         </lemon:reference>
       </lemon:LexicalSense>
    </lemon:sense>
   protected override def isProper = true
}

/**
* A noun representing a genus of object associated with an ontology class
* @param form The canonical form of the noun (required)
* @param sense The URI to be associated with
* @param forms The set of other forms
*/
case class ClassNoun(val lemma : NP, 
                     val sense : URI = null, 
                     val forms : Seq[Form] = Nil,
                     val gender : Option[Gender] = None) extends Noun {
  def makeWithForm(form : Form) = ClassNoun(lemma,sense,forms :+ form,gender)
  def withGender(gender : Gender) = ClassNoun(lemma,sense,forms,Some(gender))
  def senseXML(namer : URINamer) = {
  val subjURI = namer("noun",lemma.toString(),Some("subject"))
    <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("noun",lemma.toString(),Some("sense"))}>
         <lemon:reference>
           <owl:Class rdf:about={sense}/>
         </lemon:reference>
         <lemon:isA>
            <lemon:Argument rdf:about={subjURI}/>
         </lemon:isA>
       </lemon:LexicalSense>
    </lemon:sense> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("noun",lemma.toString(),Some("frame"))}>
        <rdf:type rdf:resource={lexinfo("NounPredicateFrame")}/>
        <lexinfo:subject rdf:resource={subjURI}/>
      </lemon:Frame>
    </lemon:synBehavior>
  }
}

/**
 * A noun representing a bivalent relationship associated with an object property
 * in the ontology
 * @param form The canonical form of the noun (required)
 * @param sense The URI to be associated with
 * @param propSubj Indicates the argument that fills the subject (domain) of the object property
 * @param propObj Indicates the argument that fills the object (range) of the object property (required)
 * @param forms The set of other forms
 */
case class RelationalNoun(val lemma : NP, 
                          val sense : URI = null, 
                          val propSubj : Arg = CopulativeArg, 
                          val propObj : Arg, 
                          val forms : Seq[Form] = Nil,
                          val gender : Option[Gender] = None) extends Noun {
   def makeWithForm(inflForm : Form) = RelationalNoun(lemma,sense,propSubj,propObj,forms :+ inflForm,gender)
   def withGender(gender : Gender) = RelationalNoun(lemma,sense,propSubj,propObj,forms,Some(gender))
   protected def senseXML(namer : URINamer) = {
     val subjURI = namer("noun",lemma.toString(),Some("subject"))
     val objURI = namer("noun",lemma.toString(),Some("adpositionalObject"))
     <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("noun",lemma.toString(),Some("sense"))}>
         <lemon:reference>
           <rdf:Property rdf:about={sense}/>
         </lemon:reference>
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
      <lemon:Frame rdf:about={namer("noun",lemma.toString(),Some("frame"))}>
        { (propSubj,propObj) match {
          case (ArgImpl(_,_,"copulativeArg"),PrepositionalObject(_,_,_)) => <rdf:type rdf:resource={lexinfo("NounPPFrame")}/>
            case (PrepositionalObject(_,_,_),ArgImpl(_,_,"copulativeArg")) => <rdf:type rdf:resource={lexinfo("NounPPFrame")}/>
            case (ArgImpl(_,_,"copulativeArg"),ArgImpl(_,_,"possessiveAdjunct")) => <rdf:type rdf:resource={lexinfo("NounPossessiveFrame")}/>
            case (ArgImpl(_,_,"possessiveAdjunct"),ArgImpl(_,_,"copulativeArg")) => <rdf:type rdf:resource={lexinfo("NounPossessiveFrame")}/>
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
 * A noun representing a relationship with multiple arguments
 * @param form The canonical form of the noun (required)
 * @param relationClass The class of relations
 * @param args The argument structure (required)
 * @param forms The set of other forms
 */
case class RelationalMultivalentNoun(val lemma : NP,
                                     val relationClass : URI = null,
                                     val args : Seq[OntologyFrameElement],
                                     val forms : Seq[Form] = Nil,
                                     val gender : Option[Gender] = None) extends Noun {
   def makeWithForm(form : Form) = RelationalMultivalentNoun(lemma,relationClass,args,forms :+ form,gender)
   def withGender(gender : Gender) = RelationalMultivalentNoun(lemma,relationClass,args,forms,Some(gender))
   protected def senseXML(namer : URINamer) = {
     val argURI = (for((arg,i) <- args.zipWithIndex) yield {
       arg -> namer("noun",lemma.toString(),Some("arg"+(i+1)))
     }).toMap
     <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("noun",lemma.toString(),Some("sense"))}>
         <lemon:reference>
           <rdfs:Class rdf:about={relationClass}>
             <rdfs:subClassOf rdf:resource="http://www.lemon-model.net/oils#Relationship"/>
           </rdfs:Class>
         </lemon:reference>
         {
           for((arg,i) <- args.zipWithIndex) yield {
            <lemon:subsense>
              <lemon:LexicalSense rdf:about={namer("noun",lemma.toString(),Some("subsense"+(i+1)))}>
                <lemon:objOfProp>
                  <lemon:Argument rdf:about={argURI(arg)}>
                  {
                    if(arg.arg.isOptional) {
                      <lemon:optional rdf:datatype="http://www.w3.org/2001/XMLSchema#boolean">true</lemon:optional>
                    } else {
                      <!-- Mandatory argument -->
                    }
                  }
                 </lemon:Argument>
                </lemon:objOfProp>
                <lemon:reference>
                  <rdf:Property rdf:about={arg.property}/>
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
      <lemon:Frame rdf:about={namer("noun",lemma.toString(),Some("frame"))}>
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
 * A noun representing a relation that also defines a class
 * @param form The canonical form of the noun (required)
 * @param relationClass The class created by this relation
 * @param relation The property described by this relation
 * @param propSubj The subject of this property
 * @param propObj The object of the proprety (required)
 * @param forms The set of other forms
 **/
case class ClassRelationalNoun(val lemma : NP,
                               val relationClass : URI = null,
                               val relation : URI = null,
                               val propSubj : Arg = CopulativeArg,
                               val propObj : Arg,
                               val forms : Seq[Form] = Nil,
                               val gender : Option[Gender] = None) extends Noun {
   def makeWithForm(form : Form) = ClassRelationalNoun(lemma,relationClass,relation,propSubj,propObj,forms :+ form,gender)
   def withGender(gender : Gender) = ClassRelationalNoun(lemma,relationClass,relation,propSubj,propObj,forms,Some(gender))
   protected def senseXML(namer : URINamer) = {
     val subjURI = namer("noun",lemma.toString(),Some("subject"))
     val objURI = namer("noun",lemma.toString(),Some("adpositionalObject"))
     <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("noun",lemma.toString(),Some("senseRel"))}>
         <lemon:reference>
           <rdf:Property rdf:about={relation}/>
         </lemon:reference>
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
      <lemon:LexicalSense rdf:about={namer("noun",lemma.toString(),Some("senseClass"))}>
         <lemon:reference>
           <rdf:Property rdf:about={relationClass}/>
         </lemon:reference>
         <lemon:isA>
            <lemon:Argument rdf:about={subjURI}/>
         </lemon:isA>
       </lemon:LexicalSense>
    </lemon:sense> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("noun",lemma.toString(),Some("frame"))}>
        { (propSubj,propObj) match {
          case (ArgImpl(_,_,"copulativeArg"),PrepositionalObject(_,_,_)) => <rdf:type rdf:resource={lexinfo("NounPPFrame")}/>
            case (PrepositionalObject(_,_,_), ArgImpl(_,_,"copulativeArg")) => <rdf:type rdf:resource={lexinfo("NounPPFrame")}/>
            case (ArgImpl(_,_,"copulativeArg"),ArgImpl(_,_,"possessiveAdjunct")) => <rdf:type rdf:resource={lexinfo("NounPossessiveFrame")}/>
            case (ArgImpl(_,_,"possessiveAdjunct"),ArgImpl(_,_,"copulativeArg")) => <rdf:type rdf:resource={lexinfo("NounPossessiveFrame")}/>
            case _ => <!--Unrecognised frame-->
           }
        }
        { propSubj.toXML(subjURI,namer) }
        { propObj.toXML(objURI,namer) }
      </lemon:Frame>
    </lemon:synBehavior> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("noun",lemma.toString(),Some("frame"))}>
        <rdf:type rdf:resource={lexinfo("NounPredicateFrame")}/>
        { propSubj.toXML(subjURI,namer) }
      </lemon:Frame>
    </lemon:synBehavior>
   }
} 
