package net.lemonmodel.patterns

trait Phrase {
  def toXML(namer : URINamer, lang : String) : scala.xml.NodeSeq
  def toOntoLexXML(namer : URINamer, lang : String) : scala.xml.NodeSeq
}

trait POS {
  override def toString() = this.getClass().getSimpleName().dropRight(1)
}

case class Word(val lemma : String, val pos : POS, _form : Option[String] = None) {
  def toOntoLexXML(namer : URINamer, lang : String) = <ontolex:LexicalEntry rdf:about={namer(pos.toString,lemma)}>
     <ontolex:canonicalForm>
       <ontolex:Form rdf:about={namer(pos.toString,lemma.toString(),Some("canonicalForm"))}>
         <ontolex:writtenRep xml:lang={lang}>{lemma}</ontolex:writtenRep>
       </ontolex:Form>
     </ontolex:canonicalForm>
     {if(_form != None && _form.get != lemma) {
       <ontolex:otherForm>
         <ontolex:Form rdf:about={namer(pos.toString,lemma.toString(),Some("otherForm"))}>
           <ontolex:writtenRep xml:lang={lang}>{_form.get}</ontolex:writtenRep>
         </ontolex:Form>
       </ontolex:otherForm>
     } }
     <lexinfo:partOfSpeech rdf:resource={lexinfo(pos.toString)}/>
   </ontolex:LexicalEntry>
   def toXML(namer : URINamer, lang : String) = <lemon:LexicalEntry rdf:about={namer(pos.toString,lemma)}>
     <lemon:canonicalForm>
       <lemon:Form rdf:about={namer(pos.toString,lemma.toString(),Some("canonicalForm"))}>
         <lemon:writtenRep xml:lang={lang}>{lemma}</lemon:writtenRep>
       </lemon:Form>
     </lemon:canonicalForm>
     {if(_form != None && _form.get != lemma) {
       <lemon:otherForm>
         <lemon:Form rdf:about={namer(pos.toString,lemma.toString(),Some("otherForm"))}>
           <lemon:writtenRep xml:lang={lang}>{_form.get}</lemon:writtenRep>
         </lemon:Form>
       </lemon:otherForm>
     } }
     <lexinfo:partOfSpeech rdf:resource={lexinfo(pos.toString)}/>
   </lemon:LexicalEntry>
  def name(namer : URINamer) = namer(pos.toString,lemma) 
  def form = _form match {
    case Some(f) => f
    case None => lemma
  }
}

class AbstractPhrase(words : Seq[Word], lexinfoType : String, pos : String, val head : Option[Word]) {
  def toOntoLexXML(namer : URINamer, lang : String) : xml.NodeSeq = if(words.length == 1) {
    <lexinfo:partOfSpeech rdf:resource={lexinfo(words(0).pos.toString)}/>
  } else {
    <rdf:type rdf:resource={lexinfo(lexinfoType)}/> +:
    <decomp:constituent>{
      for((word,idx) <- words.zipWithIndex) yield {
        <decomp:Component rdf:about={namer(pos,toString(),Some("element_"+idx))}>
          <decomp:correspondsTo>{word.toOntoLexXML(namer,lang)}</decomp:correspondsTo>
        </decomp:Component>
      }
    }</decomp:constituent> ++:
    (words.zipWithIndex map { case (word,idx) => xml.Elem("rdf", "_" + (idx + 1), xml.Null, xml.TopScope, true) % new xml.PrefixedAttribute("rdf", "resource", namer(pos, toString(), Some("element_"+idx)).toString,xml.Null) }) ++: 
    (head match {
      case Some(w) => List(<lexinfo:head rdf:resource={w.name(namer)}/>)
      case None => Nil
    })
  }
   def toXML(namer : URINamer, lang : String) : xml.NodeSeq = if(words.length == 1) {
    <lexinfo:partOfSpeech rdf:resource={lexinfo(words(0).pos.toString)}/>
  } else {
    <rdf:type rdf:resource={lexinfo(lexinfoType)}/> +:
    <lemon:decomposition rdf:parseType="Collection">{
      for((word,idx) <- words.zipWithIndex) yield {
        <lemon:Component rdf:about={namer(pos,toString(),Some("element_"+idx))}>
          <lemon:element>{word.toXML(namer,lang)}</lemon:element>
        </lemon:Component>
      }
    }</lemon:decomposition> ++:
    (head match {
      case Some(w) => List(<lexinfo:head rdf:resource={w.name(namer)}/>)
      case None => Nil
    })
  }
  override def toString() = words.map(_.form).mkString(" ")
}

trait NounPhrase extends Phrase

class NP(head : Option[Word], words : Word*) extends AbstractPhrase(words.toSeq,"NounPhrase","noun",head) with NounPhrase

class PNP(head : Option[Word], words : Word*) extends AbstractPhrase(words.toSeq,"NounPhrase","noun",head) with NounPhrase

class VP(head : Option[Word], words : Word*) extends AbstractPhrase(words.toSeq,"VerbPhrase","verb",head)

class AP(head : Option[Word], words : Word*) extends AbstractPhrase(words.toSeq,"AdjectivePhrase","adjective",head)

object NP { def apply(words : Word*) = new NP(None,words:_*) }

object PNP { def apply(words : Word*) = new PNP(None,words:_*) }

object VP { def apply(words : Word*) = new VP(None,words:_*) }

object AP { def apply(words : Word*) = new AP(None,words:_*) }


package pos {
 object adverbialPronoun extends POS
 object affirmativeParticule extends POS
 object affixedPersonalPronoun extends POS
 object allusivePronoun extends POS
 object cardinalNumeral extends POS
 object closeParenthesis extends POS
 object collectivePronoun extends POS
 object comparativeParticle extends POS
 object compoundPreposition extends POS
 object conditionalParticule extends POS
 object conditionalPronoun extends POS
 object coordinatingConjunction extends POS
 object coordinationParticle extends POS
 object deficientVerb extends POS
 object definiteArticle extends POS
 object demonstrativeDeterminer extends POS
 object demonstrativePronoun extends POS
 object diminutiveNoun extends POS
 object distinctiveParticle extends POS
 object emphaticPronoun extends POS
 object exclamativeDeterminer extends POS
 object exclamativePoint extends POS
 object exclamativePronoun extends POS
 object existentialPronoun extends POS
 object fusedPrepositionDeterminer extends POS
 object fusedPrepositionPronoun extends POS
 object fusedPreposition extends POS
 object fusedPronounAuxiliary extends POS
 object futureParticle extends POS
 object generalAdverb extends POS
 object generalizationWord extends POS
 object genericNumeral extends POS
 object impersonalPronoun extends POS
 object indefiniteArticle extends POS
 object indefiniteCardinalNumeral extends POS
 object indefiniteDeterminer extends POS
 object indefiniteMultiplicativeNumeral extends POS
 object indefiniteOrdinalNumeral extends POS
 object indefinitePronoun extends POS
 object infinitiveParticle extends POS
 object interrogativeCardinalNumeral extends POS
 object interrogativeDeterminer extends POS
 object interrogativeMultiplicativeNumeral extends POS
 object interrogativeOrdinalNumeral extends POS
 object interrogativeParticle extends POS
 object interrogativePronoun extends POS
 object interrogativeRelativePronoun extends POS
 object invertedComma extends POS
 object irreflexivePersonalPronoun extends POS
 object lightVerb extends POS
 object mainVerb extends POS
 object multiplicativeNumeral extends POS
 object negativeParticle extends POS
 object negativePronoun extends POS
 object numeralFraction extends POS
 object openParenthesis extends POS
 object ordinalAdjective extends POS
 object participleAdjective extends POS
 object partitiveArticle extends POS
 object pastParticipleAdjective extends POS
 object personalPronoun extends POS
 object plainVerb extends POS
 object possessiveAdjective extends POS
 object possessiveDeterminer extends POS
 object possessiveParticle extends POS
 object possessivePronoun extends POS
 object possessiveRelativePronoun extends POS
 object prepositionalAdverb extends POS
 object presentParticipleAdjective extends POS
 object presentativePronoun extends POS
 object pronominalAdverb extends POS
 object qualifierAdjective extends POS
 object questionMark extends POS
 object reciprocalPronoun extends POS
 object reflexiveAdjective extends POS
 object reflexivePersonalPronoun extends POS
 object reflexivePossessivePronoun extends POS
 object relationNoun extends POS
 object relativeDeterminer extends POS
 object relativeParticle extends POS
 object relativePronoun extends POS
 object strongPersonalPronoun extends POS
 object subordinatingConjunction extends POS
 object superlativeParticle extends POS
 object suspensionPoints extends POS
 object unclassifiedParticle extends POS
 object weakPersonalPronoun extends POS
 object adjective extends POS
 object adposition extends POS
 object adverb extends POS
 object article extends POS
 object bullet extends POS
 object circumposition extends POS
 object colon extends POS
 object comma extends POS
 object commonNoun extends POS
 object conjunction extends POS
 object copula extends POS
 object determiner extends POS
 object interjection extends POS
 object modal extends POS
 object noun extends POS
 object numeral extends POS
 object particle extends POS
 object point extends POS
 object postposition extends POS
 object preposition extends POS
 object pronoun extends POS
 object properNoun extends POS
 object punctuation extends POS
 object semiColon extends POS
 object slash extends POS
 object verb extends POS
}
