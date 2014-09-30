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
  def withAbessive(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("abessiveCase"))))
  def withAblative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("ablativeCase"))))
  def withAbsolutive(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("absolutiveCase"))))
  def withAccusative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("accusativeCase"))))
  def withAdessive(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("adessiveCase"))))
  def withAditive(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("aditiveCase"))))
  def withAllative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("allativeCase"))))
  def withBenefactive(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("benefactiveCase"))))
  def withCausative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("causativeCase"))))
  def withComitative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("comitativeCase"))))
  def withDative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("dativeCase"))))
  def withDelative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("delativeCase"))))
  def withElative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("elativeCase"))))
  def withEquative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("equativeCase"))))
  def withErgative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("ergativeCase"))))
  def withEssive(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("essiveCase"))))
  def withGenitive(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("genitiveCase"))))
  def withIllative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("illativeCase"))))
  def withInessive(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("inessiveCase"))))
  def withInstrumental(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("instrumentalCase"))))
  def withLative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("lativeCase"))))
  def withLocative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("locativeCase"))))
  def withOblique(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("obliqueCase"))))
  def withPartitive(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("partitiveCase"))))
  def withProlative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("prolativeCase"))))
  def withSociative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("sociativeCase"))))
  def withSublative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("sublativeCase"))))
  def withSuperessive(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("superessiveCase"))))
  def withTerminative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("terminativeCase"))))
  def withTranslative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("translativeCase"))))
  def withVocative(form : String) = makeWithForm(Form(form,Map(lexinfo("case")->lexinfo("vocativeCase"))))
  def withAbessiveSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("abessiveCase"))))
  def withAblativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("ablativeCase"))))
  def withAbsolutiveSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("absolutiveCase"))))
  def withAccusativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("accusativeCase"))))
  def withAdessiveSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("adessiveCase"))))
  def withAditiveSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("aditiveCase"))))
  def withAllativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("allativeCase"))))
  def withBenefactiveSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("benefactiveCase"))))
  def withCausativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("causativeCase"))))
  def withComitativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("comitativeCase"))))
  def withDativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("dativeCase"))))
  def withDelativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("delativeCase"))))
  def withElativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("elativeCase"))))
  def withEquativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("equativeCase"))))
  def withErgativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("ergativeCase"))))
  def withEssiveSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("essiveCase"))))
  def withGenitiveSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("genitiveCase"))))
  def withIllativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("illativeCase"))))
  def withInessiveSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("inessiveCase"))))
  def withInstrumentalSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("instrumentalCase"))))
  def withLativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("lativeCase"))))
  def withLocativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("locativeCase"))))
  def withNominativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("nominativeCase"))))
  def withObliqueSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("obliqueCase"))))
  def withPartitiveSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("partitiveCase"))))
  def withProlativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("prolativeCase"))))
  def withSociativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("sociativeCase"))))
  def withSublativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("sublativeCase"))))
  def withSuperessiveSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("superessiveCase"))))
  def withTerminativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("terminativeCase"))))
  def withTranslativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("translativeCase"))))
  def withVocativeSingular(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("singular"),lexinfo("case")->lexinfo("vocativeCase"))))
  def withAbessiveDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("abessiveCase"))))
  def withAblativeDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("ablativeCase"))))
  def withAbsolutiveDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("absolutiveCase"))))
  def withAccusativeDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("accusativeCase"))))
  def withAdessiveDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("adessiveCase"))))
  def withAditiveDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("aditiveCase"))))
  def withAllativeDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("allativeCase"))))
  def withBenefactiveDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("benefactiveCase"))))
  def withCausativeDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("causativeCase"))))
  def withComitativeDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("comitativeCase"))))
  def withDativeDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("dativeCase"))))
  def withDelativeDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("delativeCase"))))
  def withElativeDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("elativeCase"))))
  def withEquativeDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("equativeCase"))))
  def withErgativeDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("ergativeCase"))))
  def withEssiveDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("essiveCase"))))
  def withGenitiveDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("genitiveCase"))))
  def withIllativeDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("illativeCase"))))
  def withInessiveDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("inessiveCase"))))
  def withInstrumentalDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("instrumentalCase"))))
  def withLativeDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("lativeCase"))))
  def withLocativeDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("locativeCase"))))
  def withNominativeDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("nominativeCase"))))
  def withObliqueDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("obliqueCase"))))
  def withPartitiveDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("partitiveCase"))))
  def withProlativeDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("prolativeCase"))))
  def withSociativeDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("sociativeCase"))))
  def withSublativeDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("sublativeCase"))))
  def withSuperessiveDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("superessiveCase"))))
  def withTerminativeDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("terminativeCase"))))
  def withTranslativeDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("translativeCase"))))
  def withVocativeDual(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("dual"),lexinfo("case")->lexinfo("vocativeCase"))))
  def withAbessivePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("abessiveCase"))))
  def withAblativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("ablativeCase"))))
  def withAbsolutivePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("absolutiveCase"))))
  def withAccusativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("accusativeCase"))))
  def withAdessivePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("adessiveCase"))))
  def withAditivePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("aditiveCase"))))
  def withAllativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("allativeCase"))))
  def withBenefactivePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("benefactiveCase"))))
  def withCausativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("causativeCase"))))
  def withComitativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("comitativeCase"))))
  def withDativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("dativeCase"))))
  def withDelativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("delativeCase"))))
  def withElativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("elativeCase"))))
  def withEquativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("equativeCase"))))
  def withErgativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("ergativeCase"))))
  def withEssivePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("essiveCase"))))
  def withGenitivePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("genitiveCase"))))
  def withIllativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("illativeCase"))))
  def withInessivePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("inessiveCase"))))
  def withInstrumentalPlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("instrumentalCase"))))
  def withLativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("lativeCase"))))
  def withLocativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("locativeCase"))))
  def withNominativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("nominativeCase"))))
  def withObliquePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("obliqueCase"))))
  def withPartitivePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("partitiveCase"))))
  def withProlativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("prolativeCase"))))
  def withSociativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("sociativeCase"))))
  def withSublativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("sublativeCase"))))
  def withSuperessivePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("superessiveCase"))))
  def withTerminativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("terminativeCase"))))
  def withTranslativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("translativeCase"))))
  def withVocativePlural(form : String) = makeWithForm(Form(form,Map(lexinfo("number")->lexinfo("plural"),lexinfo("case")->lexinfo("vocativeCase"))))
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
                val gender : Option[Gender] = None,
                val register : Option[Register] = None) extends Noun {
  def makeWithForm(form : Form) = Name(lemma,sense, forms :+ form,gender,register)
  def withGender(gender : Gender) = Name(lemma,sense,forms,Some(gender),register)
  def withRegister(register : Register) = Name(lemma,sense,forms,gender,Some(register))
  protected def senseXML(namer : URINamer) = <lemon:sense>
       <lemon:LexicalSense rdf:about={namer("noun",lemma.toString(),Some("sense"))}>
         <lemon:reference>
           <owl:NamedIndividual rdf:about={sense}/>
         </lemon:reference>
         {registerXML(register)}
       </lemon:LexicalSense>
    </lemon:sense>
   protected override def isProper = true
}

/**
 * Abstract class noun
 */
trait AbsClassNoun extends Noun {
  def register : Option[Register]
  def referenceXML(namer : URINamer) : Elem
  def senseXML(namer : URINamer) = {
  val subjURI = namer("noun",lemma.toString(),Some("subject"))
    <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("noun",lemma.toString(),Some("sense"))}>
         <lemon:reference>
         {referenceXML(namer)}
         </lemon:reference>
         {registerXML(register)}
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
* A noun representing a genus of object associated with an ontology class
* @param lemma The canonical form of the noun (required)
* @param sense The URI to be associated with
* @param forms The set of other forms
*/
case class ClassNoun(val lemma : NP, 
                     val sense : URI = null, 
                     val forms : Seq[Form] = Nil,
                     val gender : Option[Gender] = None,
                     val register : Option[Register] = None) extends AbsClassNoun {
  def makeWithForm(form : Form) = ClassNoun(lemma,sense,forms :+ form,gender,register)
  def withGender(gender : Gender) = ClassNoun(lemma,sense,forms,Some(gender),register)
  def withRegister(register : Register) = ClassNoun(lemma,sense,forms,gender,Some(register))
  def referenceXML(name : URINamer) = <owl:Class rdf:about={sense}/>
}

/**
 * A noun representing a genus of object associated with an anonymous ontology class defined by a property and a value
 * @param lemma The canonical form of the noun (required)
 * @param sense The URI to be associated with
 * @param forms The set of other forms
 */
case class ObjectPropertyNoun(val lemma : NP, 
                     val prop : URI, val obj : URI,
                     val forms : Seq[Form] = Nil,
                     val gender : Option[Gender] = None,
                     val register : Option[Register] = None) extends AbsClassNoun {
  def makeWithForm(form : Form) = ObjectPropertyNoun(lemma,prop,obj,forms :+ form,gender,register)
  def withGender(gender : Gender) = ObjectPropertyNoun(lemma,prop,obj,forms,Some(gender),register)
  def withRegister(register : Register) = ObjectPropertyNoun(lemma,prop,obj,forms,gender,Some(register))
  def referenceXML(name : URINamer) = <owl:Restriction rdf:about={name("noun",lemma.toString(), Some("sense"))}>
    <owl:onProperty rdf:resource={prop}/>
    <owl:hasValue rdf:resource={obj}/>
  </owl:Restriction>
}

/**
 * A noun representing a genus of object associated with an anonymous ontology class defined by a property and a value
 * @param lemma The canonical form of the noun (required)
 * @param sense The URI to be associated with
 * @param forms The set of other forms
 */
case class DataPropertyNoun(val lemma : NP, 
                     val prop : URI, val value : String,
                     val forms : Seq[Form] = Nil,
                     val gender : Option[Gender] = None,
                     val register : Option[Register] = None) extends AbsClassNoun {
  def makeWithForm(form : Form) = DataPropertyNoun(lemma,prop,value,forms :+ form,gender,register)
  def withGender(gender : Gender) = DataPropertyNoun(lemma,prop,value,forms,Some(gender),register)
  def withRegister(register : Register) = DataPropertyNoun(lemma,prop,value,forms,gender,Some(register))
  def referenceXML(name : URINamer) = <owl:Restriction rdf:about={name("noun",lemma.toString(), Some("sense"))}>
    <owl:onProperty rdf:resource={prop}/>
    <owl:hasValue>{value}</owl:hasValue>
  </owl:Restriction>
}


/**
 * A noun representing a bivalent relationship associated with an object property
 * in the ontology
 * @param lemma The canonical form of the noun (required)
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
                          val gender : Option[Gender] = None,
                          val register : Option[Register] = None) extends Noun {
   def makeWithForm(inflForm : Form) = RelationalNoun(lemma,sense,propSubj,propObj,forms :+ inflForm,gender,register)
   def withGender(gender : Gender) = RelationalNoun(lemma,sense,propSubj,propObj,forms,Some(gender),register)
   def withRegister(register : Register) = RelationalNoun(lemma,sense,propSubj,propObj,forms,gender,Some(register))
   protected def senseXML(namer : URINamer) = {
     val subjURI = namer("noun",lemma.toString(),Some("subject"))
     val objURI = namer("noun",lemma.toString(),Some("adpositionalObject"))
     <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("noun",lemma.toString(),Some("sense"))}>
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
                                     val gender : Option[Gender] = None,
                                     val register : Option[Register] = None) extends Noun {
   def makeWithForm(form : Form) = RelationalMultivalentNoun(lemma,relationClass,args,forms :+ form,gender,register)
   def withGender(gender : Gender) = RelationalMultivalentNoun(lemma,relationClass,args,forms,Some(gender),register)
   def withRegister(register : Register) = RelationalMultivalentNoun(lemma,relationClass,args,forms,gender,Some(register))
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
         {registerXML(register)}
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
                               val gender : Option[Gender] = None,
                               val register : Option[Register] = None) extends Noun {
   def makeWithForm(form : Form) = ClassRelationalNoun(lemma,relationClass,relation,propSubj,propObj,forms :+ form,gender,register)
   def withGender(gender : Gender) = ClassRelationalNoun(lemma,relationClass,relation,propSubj,propObj,forms,Some(gender),register)
   def withRegister(register : Register) = ClassRelationalNoun(lemma,relationClass,relation,propSubj,propObj,forms,gender,Some(register))
   protected def senseXML(namer : URINamer) = {
     val subjURI = namer("noun",lemma.toString(),Some("subject"))
     val objURI = namer("noun",lemma.toString(),Some("adpositionalObject"))
     <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("noun",lemma.toString(),Some("senseRel"))}>
         <lemon:reference>
           <rdf:Property rdf:about={relation}/>
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
