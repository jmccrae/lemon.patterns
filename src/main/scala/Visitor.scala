package net.lemonmodel.patterns

import java.net.URI
import net.lemonmodel.patterns.parser._
import scala.collection.JavaConversions._

class PatternVisitor extends Absyn.Statements.Visitor[Seq[Lexicon],collection.mutable.Map[String,String]] with
                             Absyn.Statement.Visitor[Option[Lexicon],collection.mutable.Map[String,String]] with
                             Absyn.Pattern.Visitor[Pattern,collection.mutable.Map[String,String]] with
                             Absyn.NounPattern.Visitor[Pattern,collection.mutable.Map[String,String]] with
                             Absyn.VerbPattern.Visitor[Pattern,collection.mutable.Map[String,String]] with
                             Absyn.AdjectivePattern.Visitor[Pattern,collection.mutable.Map[String,String]] with
                             Absyn.Arg.Visitor[Arg,collection.mutable.Map[String,String]] with
                             Absyn.OntologyFrameElement.Visitor[OntologyFrameElement,collection.mutable.Map[String,String]] with
                             Absyn.PNP.Visitor[PNP,collection.mutable.Map[String,String]] with
                             Absyn.NP.Visitor[NP,collection.mutable.Map[String,String]] with
                             Absyn.VP.Visitor[VP,collection.mutable.Map[String,String]] with
                             Absyn.AP.Visitor[AP,collection.mutable.Map[String,String]] with
                             Absyn.POSTaggedWord.Visitor[Word,collection.mutable.Map[String,String]] with
                             Absyn.ScalarMembership.Visitor[ScalarMembership,collection.mutable.Map[String,String]] with
                             Absyn.Category.Visitor[(URI,URI),collection.mutable.Map[String,String]] with
                             Absyn.POSTag.Visitor[POS,collection.mutable.Map[String,String]] with
                             Absyn.URI.Visitor[URI,collection.mutable.Map[String,String]] {
/* Statements */
    def visit(p : Absyn.EStatments, arg : collection.mutable.Map[String,String]) = { 
      p.liststatement_.flatMap { _.accept(this,arg) }
    }
/* Statement */
    def visit(p : Absyn.EPrefix, arg : collection.mutable.Map[String,String]) = { 
      arg.put(p.ident_,p.fulluri_)
      None
    }
    def visit(p : Absyn.ELexicon, arg : collection.mutable.Map[String,String]) = {
      val patterns : Seq[Pattern] = p.listpattern_.map { _.accept(this,arg) }
      Some(Lexicon(p.uri_.accept(this,arg), p.string_, patterns:_*))
    }
/* Pattern */
    def visit(p : Absyn.EPatternWithForm, arg : collection.mutable.Map[String,String]) = { 
      val basePattern : Pattern = p.pattern_.accept(this,arg)
      val form : Form = Form(p.string_,(p.listcategory_.map { _.accept(this,arg) }).toMap)
      basePattern makeWithForm form
    }
    def visit(p : Absyn.ENoun, arg : collection.mutable.Map[String,String]) = { 
      p.nounpattern_.accept(this,arg)
    }
    def visit(p : Absyn.EVerb, arg : collection.mutable.Map[String,String]) = {
      p.verbpattern_.accept(this,arg)
    }
    def visit(p : Absyn.EAdjective, arg : collection.mutable.Map[String,String]) = {
      p.adjectivepattern_.accept(this,arg)
    }
/* NounPattern */
    def visit(p : Absyn.EName, arg : collection.mutable.Map[String,String]) = Name(
      p.pnp_.accept(this,arg),
      p.uri_.accept(this,arg)
    )
    def visit(p : Absyn.EClassNoun, arg : collection.mutable.Map[String,String]) = ClassNoun(
      p.np_.accept(this,arg),
      p.uri_.accept(this,arg)
    )
    def visit(p : Absyn.ERelationalNoun1, arg : collection.mutable.Map[String,String]) = RelationalNoun(
      p.np_.accept(this,arg),
      p.uri_.accept(this,arg),
      propSubj=p.arg_1.accept(this,arg),
      propObj=p.arg_2.accept(this,arg)
    )
    def visit(p : Absyn.ERelationalNoun2, arg : collection.mutable.Map[String,String]) = RelationalNoun(
      p.np_.accept(this,arg),
      p.uri_.accept(this,arg),
      propObj=p.arg_.accept(this,arg)
    )
    def visit(p : Absyn.ERelationalMultivalentNoun, arg : collection.mutable.Map[String,String]) = { 
      val ofes : Seq[OntologyFrameElement] = p.listontologyframeelement_.map { _.accept(this,arg) }
      RelationalMultivalentNoun(
        p.np_.accept(this,arg),
        p.uri_.accept(this,arg),
        ofes
      )
    }
    def visit(p : Absyn.EClassRelationalNoun1, arg : collection.mutable.Map[String,String]) = ClassRelationalNoun(
      p.np_.accept(this,arg),
      p.uri_1.accept(this,arg),
      p.uri_2.accept(this,arg),
      propSubj=p.arg_1.accept(this,arg),
      propObj=p.arg_2.accept(this,arg)
    )
    def visit(p : Absyn.EClassRelationalNoun2, arg : collection.mutable.Map[String,String]) = ClassRelationalNoun(
      p.np_.accept(this,arg),
      p.uri_1.accept(this,arg),
      p.uri_2.accept(this,arg),
      propObj=p.arg_.accept(this,arg)
    )
/* VerbPattern */
    def visit(p : Absyn.EStateVerb1, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EStateVerb2, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EStateVerb3, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.ETelicEventVerb, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.ENontelicEventVerb, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EDunnotelicEventVerb, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EDurativeConsequenceVerb, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EInstantConsequenceVerb, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EConsequenceVerb, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EDurativeEventVerb, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EInstantEventVerb, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EEventVerb, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EConsequenceVerb1, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EConsequenceVerb2, arg : collection.mutable.Map[String,String]) = { null }
/* AdjectivePattern */
    def visit(p : Absyn.EIntersectiveAdjective, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EIntersectiveObjectPropertyAdjective, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EIntersectiveDataPropertyAdjective, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EPropertyModifyingAdjective, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.ERelationalAdjective, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EScalarAdjective, arg : collection.mutable.Map[String,String]) = { null }
/* Arg */
    def visit(p : Absyn.ESubject, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EDirectObject, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EIndirectObject, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.ECopulativeArg, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.ECopulativeSubject, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EPrepositionalObject, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EPostpositionalObject, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EPossessiveAdjunct, arg : collection.mutable.Map[String,String]) = { null }
/* OntologyFrameElement */
    def visit(p : Absyn.EURIAsSynArg, arg : collection.mutable.Map[String,String]) = { null }
/* PNP */
    def visit(p : Absyn.EPNPSimple, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EPNPComplex, arg : collection.mutable.Map[String,String]) = { null }
/* NP */
    def visit(p : Absyn.ENPSimple, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.ENPComplex, arg : collection.mutable.Map[String,String]) = { null }
/* VP */
    def visit(p : Absyn.EVPSimple, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EVPComplex, arg : collection.mutable.Map[String,String]) = { null }
/* AP */
    def visit(p : Absyn.EAPSimple, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EAPComplex, arg : collection.mutable.Map[String,String]) = { null }
/* POSTaggedWord */
    def visit(p : Absyn.EPOSTaggedWord, arg : collection.mutable.Map[String,String]) = { null }
/* ScalarMembership */
    def visit(p : Absyn.CovariantScalarMembership, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.ContravariantScalarMembership, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.GreaterThanScalarMembership, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.LessThanScalarMembership, arg : collection.mutable.Map[String,String]) = { null }
/* Category */
    def visit(p : Absyn.ESingular, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EDual, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EPlural, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.ENominative, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EAccusative, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EGenitive, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EDative, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EComparative, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.ESuperlative, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EPresent, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EPast, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EFuture, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EFirstPerson, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.ESecondPerson, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EThirdPerson, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EImperfect, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EImperative, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EIndicative, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.ESubjunctive, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EConditional, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EGerundive, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EInfinitive, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EParticiple, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EAnyCat, arg : collection.mutable.Map[String,String]) = { null }
/* POSTag */
    def visit(p : Absyn.EAdjectivePOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EAdpositionPOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EAdverbPOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EArticlePOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EBulletPOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.ECircumpositionPOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EColonPOSPOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.ECommaPOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EConjunctionPOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.ECopulaPOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EDeterminerPOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EInterjectionPOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.ENounPOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.ENumeralPOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EParticlePOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EPointPOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EPostpositionPOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EPrepositionPOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EPronounPOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EPunctuationPOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.ESemiColonPOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.ESlashPOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EVerbPOS, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EAnyPOS, arg : collection.mutable.Map[String,String]) = { null }
/* URI */
    def visit(p : Absyn.EQName, arg : collection.mutable.Map[String,String]) = { null }
    def visit(p : Absyn.EURI, arg : collection.mutable.Map[String,String]) = { null }

}
