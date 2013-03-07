package net.lemonmodel.patterns

trait Phrase {
  def toXML(namer : URINamer, lang : String) : scala.xml.NodeSeq
}

trait POS {
  override def toString() = this.getClass().getSimpleName().dropRight(1)
}

case class Word(val lemma : String, val pos : POS) {
  def toXML(namer : URINamer, lang : String) = <lemon:LexicalEntry rdf:about={namer(pos.toString,lemma)}>
     <lemon:canonicalForm>
       <lemon:Form>
         <lemon:writtenRep xml:lang={lang}>{lemma}</lemon:writtenRep>
       </lemon:Form>
     </lemon:canonicalForm>
     <lexinfo:partOfSpeech rdf:resource={lexinfo(pos.toString)}/>
   </lemon:LexicalEntry>
}

class AbstractPhrase(words : Seq[Word], lexinfoType : String) {
  def toXML(namer : URINamer, lang : String) = if(words.length == 1) {
    if(words(0).pos == pos.properNoun) {
      <lexinfo:partOfSpeech rdf:resource={lexinfo("properNoun")}/>
    } else {
      <lexinfo:partOfSpeech rdf:resource={lexinfo("commonNoun")}/>
    }
  } else {
    <rdf:type rdf:resource={lexinfo(lexinfoType)}/> +:
    <lemon:decomposition rdf:parseType="Collection">{
      for((word,idx) <- words.zipWithIndex) yield {
        <lemon:Component rdf:about={namer(word.pos.toString,word.lemma,Some("element_"+idx))}>
          <lemon:element>{word.toXML(namer,lang)}</lemon:element>
        </lemon:Component>
      }
    }</lemon:decomposition>
  }
  override def toString() = words.map(_.lemma).mkString(" ")
}

trait NounPhrase extends Phrase

case class NP(words : Word*) extends AbstractPhrase(words.toSeq,"NounPhrase") with NounPhrase

case class PNP(words : Word*) extends AbstractPhrase(words.toSeq,"NounPhrase") with NounPhrase

case class VP(words : Word*) extends AbstractPhrase(words.toSeq,"VerbPhrase")

case class AP(words : Word*) extends AbstractPhrase(words.toSeq,"AdjectivePhrase")

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
