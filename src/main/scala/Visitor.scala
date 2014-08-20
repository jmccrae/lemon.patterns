package net.lemonmodel.patterns

import java.net.URI
import net.lemonmodel.patterns.parser._
import scala.collection.JavaConversions._

class PatternVisitor extends Absyn.Statements.Visitor[Seq[Lexicon],collection.mutable.Map[String,String]] with
                             Absyn.Statement.Visitor[Option[Lexicon],collection.mutable.Map[String,String]] with
                             Absyn.PatternType.Visitor[Pattern,collection.mutable.Map[String,String]] with
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
                             Absyn.POSTaggedWord.Visitor[(Word,Boolean),collection.mutable.Map[String,String]] with
                             Absyn.ScalarMembership.Visitor[ScalarMembership,collection.mutable.Map[String,String]] with
                             Absyn.Category.Visitor[(URI,URI),collection.mutable.Map[String,String]] with
                             Absyn.POSTag.Visitor[POS,collection.mutable.Map[String,String]] with
                             Absyn.Gender.Visitor[Gender,collection.mutable.Map[String,String]] with
                             Absyn.URI.Visitor[URI,collection.mutable.Map[String,String]] {
/* Statements */
    def visit(p : Absyn.EStatments, arg : collection.mutable.Map[String,String]) = { 
      p.liststatement_.flatMap { _.accept(this,arg) }
    }
/* Statement */
    def visit(p : Absyn.EPrefix, arg : collection.mutable.Map[String,String]) = { 
      arg.put(p.ident_,p.fulluri_.substring(1,p.fulluri_.length-1))
      None
    }
    def visit(p : Absyn.ELexicon, arg : collection.mutable.Map[String,String]) = {
      val patterns : Seq[Pattern] = p.listpatterntype_.map { _.accept(this,arg) }
      Some(Lexicon(p.uri_.accept(this,arg), p.string_, patterns:_*))
    }
/* PatternType */
    def visit(p : Absyn.EPatternWithRegister, arg : collection.mutable.Map[String,String]) = {
      p.pattern_.accept(this,arg + ("register" -> p.register_.accept(this,arg).toString))
    }
    def visit(p : Absyn.ECorePattern, arg : collection.mutable.Map[String,String]) = {
      p.pattern_.accept(this,arg)
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
    def visit(p : Absyn.ENounWithGender, arg : collection.mutable.Map[String,String]) = {
      p.nounpattern_.accept(this,arg) match {
        case np : Noun => np withGender p.gender_.accept(this,arg)
        case _ => throw new IllegalArgumentException("Gender on non-noun pattern")
      }
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
    def visit(p : Absyn.EStateVerb1, arg : collection.mutable.Map[String,String]) = StateVerb( 
      p.vp_.accept(this,arg),
      p.uri_.accept(this,arg)
    )
    def visit(p : Absyn.EStateVerb2, arg : collection.mutable.Map[String,String]) = StateVerb( 
      p.vp_.accept(this,arg),
      p.uri_.accept(this,arg),
      Subject,
      p.arg_.accept(this,arg)
    )
    def visit(p : Absyn.EStateVerb3, arg : collection.mutable.Map[String,String]) = StateVerb( 
      p.vp_.accept(this,arg),
      p.uri_.accept(this,arg),
      p.arg_1.accept(this,arg),
      p.arg_2.accept(this,arg)
    )
    def visit(p : Absyn.ETelicEventVerb, arg : collection.mutable.Map[String,String]) = { 
      p.verbpattern_.accept(this,arg) match {
        case EventVerb(lemma,eventClass,args,_,durative,forms) => EventVerb(lemma,eventClass,args,Some(true),durative,forms)
        case _ => throw new IllegalArgumentException("telic annotation on non-event verb")
      }
    }
    def visit(p : Absyn.ENontelicEventVerb, arg : collection.mutable.Map[String,String]) = { 
      p.verbpattern_.accept(this,arg) match {
        case EventVerb(lemma,eventClass,args,_,durative,forms) => EventVerb(lemma,eventClass,args,Some(false),durative,forms)
        case _ => throw new IllegalArgumentException("telic annotation on non-event verb")
      }
    } 
    def visit(p : Absyn.EDunnotelicEventVerb, arg : collection.mutable.Map[String,String]) = { 
      p.verbpattern_.accept(this,arg)
    }
    def visit(p : Absyn.EDurativeEventVerb, arg : collection.mutable.Map[String,String]) = { 
      p.verbpattern_.accept(this,arg) match {
        case EventVerb(lemma,eventClass,args,telic,_,forms) => EventVerb(lemma,eventClass,args,telic,Some(true),forms)
        case _ => throw new IllegalArgumentException("telic annotation on non-event verb")
      }
    }
    def visit(p : Absyn.EInstantEventVerb, arg : collection.mutable.Map[String,String]) = { 
      p.verbpattern_.accept(this,arg) match {
        case EventVerb(lemma,eventClass,args,telic,_,forms) => EventVerb(lemma,eventClass,args,telic,Some(false),forms)
        case _ => throw new IllegalArgumentException("telic annotation on non-event verb")
      }
    }
    def visit(p : Absyn.EEventVerb, arg : collection.mutable.Map[String,String]) = EventVerb(
      p.vp_.accept(this,arg),
      p.uri_.accept(this,arg),
      p.listontologyframeelement_.map{_.accept(this,arg)}
    )
    def visit(p : Absyn.EConsequenceVerb1, arg : collection.mutable.Map[String,String]) = ConsequenceVerb(
      p.vp_.accept(this,arg),
      p.uri_1.accept(this,arg),
      p.ontologyframeelement_1.accept(this,arg),
      p.ontologyframeelement_2.accept(this,arg),
      p.uri_2.accept(this,arg)
    )
    def visit(p : Absyn.EConsequenceVerb2, arg : collection.mutable.Map[String,String]) =  ConsequenceVerb(
      p.vp_.accept(this,arg),
      p.uri_.accept(this,arg),
      p.ontologyframeelement_1.accept(this,arg),
      p.ontologyframeelement_2.accept(this,arg)
    )
    def visit(p : Absyn.EConsequenceVerb3, arg : collection.mutable.Map[String,String]) =  ConsequenceVerb(
      p.vp_.accept(this,arg),
      p.uri_1.accept(this,arg),
      p.ontologyframeelement_.accept(this,arg),
      eventClass = p.uri_2.accept(this,arg)
    )
    def visit(p : Absyn.EConsequenceVerb4, arg : collection.mutable.Map[String,String]) =  ConsequenceVerb(
      p.vp_.accept(this,arg),
      p.uri_.accept(this,arg),
      p.ontologyframeelement_.accept(this,arg)
    )
    def visit(p : Absyn.EConsequenceVerb5, arg : collection.mutable.Map[String,String]) =  ConsequenceVerb(
      p.vp_.accept(this,arg),
      p.uri_1.accept(this,arg),
      propObj = p.ontologyframeelement_.accept(this,arg),
      eventClass = p.uri_2.accept(this,arg)
    )
    def visit(p : Absyn.EConsequenceVerb6, arg : collection.mutable.Map[String,String]) =  ConsequenceVerb(
      p.vp_.accept(this,arg),
      p.uri_.accept(this,arg),
      propObj = p.ontologyframeelement_.accept(this,arg)
    )
    def visit(p : Absyn.EConsequenceVerb7, arg : collection.mutable.Map[String,String]) =  ConsequenceVerb(
      p.vp_.accept(this,arg),
      p.uri_1.accept(this,arg),
      eventClass = p.uri_2.accept(this,arg)
    )
    def visit(p : Absyn.EConsequenceVerb8, arg : collection.mutable.Map[String,String]) =  ConsequenceVerb(
      p.vp_.accept(this,arg),
      p.uri_.accept(this,arg)
    )
/* AdjectivePattern */
    def visit(p : Absyn.EIntersectiveAdjective, arg : collection.mutable.Map[String,String]) = IntersectiveAdjective(
      p.ap_.accept(this,arg),
      p.uri_.accept(this,arg)
    )
    def visit(p : Absyn.EIntersectiveObjectPropertyAdjective, arg : collection.mutable.Map[String,String]) = IntersectiveObjectPropertyAdjective(
      p.ap_.accept(this,arg),
      p.uri_1.accept(this,arg),
      p.uri_2.accept(this,arg)
    )
    def visit(p : Absyn.EIntersectiveDataPropertyAdjective, arg : collection.mutable.Map[String,String]) =  IntersectiveDataPropertyAdjective(
      p.ap_.accept(this,arg),
      p.uri_.accept(this,arg),
      p.string_
    )
    def visit(p : Absyn.EPropertyModifyingAdjective, arg : collection.mutable.Map[String,String]) = PropertyModifyingAdjective(
      p.ap_.accept(this,arg),
      p.uri_.accept(this,arg)
    )
    def visit(p : Absyn.ERelationalAdjective, arg : collection.mutable.Map[String,String]) = RelationalAdjective(
      p.ap_.accept(this,arg),
      p.uri_.accept(this,arg),
      p.arg_.accept(this,arg)
    )
    def visit(p : Absyn.EScalarAdjective, arg : collection.mutable.Map[String,String]) = ScalarAdjective(
      p.ap_.accept(this,arg),
      p.listscalarmembership_.map{_.accept(this,arg)}
    )
/* Arg */ 
    def visit(p : Absyn.EOptionalArg, arg : collection.mutable.Map[String,String]) = p.arg_.accept(this,arg).optional
    def visit(p : Absyn.ERestrictedArg, arg : collection.mutable.Map[String,String]) = p.arg_.accept(this,arg).restrictedTo(p.uri_.accept(this,arg))
    def visit(p : Absyn.ESubject, arg : collection.mutable.Map[String,String]) = Subject
    def visit(p : Absyn.EDirectObject, arg : collection.mutable.Map[String,String]) = DirectObject
    def visit(p : Absyn.EIndirectObject, arg : collection.mutable.Map[String,String]) = IndirectObject
    def visit(p : Absyn.ECopulativeArg, arg : collection.mutable.Map[String,String]) = CopulativeArg
    def visit(p : Absyn.ECopulativeSubject, arg : collection.mutable.Map[String,String]) = CopulativeSubject
    def visit(p : Absyn.EPrepositionalObject, arg : collection.mutable.Map[String,String]) = PrepositionalObject(p.string_)
    def visit(p : Absyn.EPostpositionalObject, arg : collection.mutable.Map[String,String]) = PostpositionalObject(p.string_)
    def visit(p : Absyn.EPossessiveAdjunct, arg : collection.mutable.Map[String,String]) = PossessiveAdjunct
/* OntologyFrameElement */
    def visit(p : Absyn.EURIAsSynArg, arg : collection.mutable.Map[String,String]) = OntologyFrameElement(
      Some(p.uri_.accept(this,arg)),
      p.arg_.accept(this,arg)
    )
    def visit(p : Absyn.EArgAsOFE, arg : collection.mutable.Map[String,String]) = OntologyFrameElement(
      None,
      p.arg_.accept(this,arg)
    )
/* PNP */
    def visit(p : Absyn.EPNPSimple, arg : collection.mutable.Map[String,String]) = PNP(p.string_ / pos.properNoun)
    def visit(p : Absyn.EPNPComplex, arg : collection.mutable.Map[String,String]) = {
      val words = p.listpostaggedword_.map{_.accept(this,arg)}
      words.find(_._2) match {
        case Some(w) => new PNP(Some(w._1),words.map(_._1):_*)
        case None => new PNP(None,words.map(_._1):_*)
      }
    }
/* NP */
    def visit(p : Absyn.ENPSimple, arg : collection.mutable.Map[String,String]) = NP(p.string_ / pos.commonNoun)
    def visit(p : Absyn.ENPComplex, arg : collection.mutable.Map[String,String]) = {
      val words = p.listpostaggedword_.map{_.accept(this,arg)}
      words.find(_._2) match {
        case Some(w) => new NP(Some(w._1),words.map(_._1):_*)
        case None => new NP(None,words.map(_._1):_*)
      }
    }
/* VP */
    def visit(p : Absyn.EVPSimple, arg : collection.mutable.Map[String,String]) = VP(p.string_ / pos.verb)
    def visit(p : Absyn.EVPComplex, arg : collection.mutable.Map[String,String]) = {
      val words = p.listpostaggedword_.map{_.accept(this,arg)}
      words.find(_._2) match {
        case Some(w) => new VP(Some(w._1),words.map(_._1):_*)
        case None => new VP(None,words.map(_._1):_*)
      }
    }
/* AP */
    def visit(p : Absyn.EAPSimple, arg : collection.mutable.Map[String,String]) = AP(p.string_ / pos.adjective)
    def visit(p : Absyn.EAPComplex, arg : collection.mutable.Map[String,String]) = {
      val words = p.listpostaggedword_.map{_.accept(this,arg)}
      words.find(_._2) match {
        case Some(w) => new AP(Some(w._1),words.map(_._1):_*)
        case None => new AP(None,words.map(_._1):_*)
      }
    }
/* POSTaggedWord */
    def visit(p : Absyn.EPOSTaggedWord, arg : collection.mutable.Map[String,String]) = (Word(
      p.string_,
      p.postag_.accept(this,arg)
    ),false)
    def visit(p : Absyn.EPOSTaggedHeadWord, arg : collection.mutable.Map[String,String]) = (Word(
      p.string_,
      p.postag_.accept(this,arg)
    ),true)
     def visit(p : Absyn.ELemmaPOSTaggedWord, arg : collection.mutable.Map[String,String]) = (Word(
      p.string_2,
      p.postag_.accept(this,arg),
      Some(p.string_1)
    ),false)
    def visit(p : Absyn.ELemmaPOSTaggedHeadWord, arg : collection.mutable.Map[String,String]) = (Word(
      p.string_2,
      p.postag_.accept(this,arg),
      Some(p.string_1)
    ),true)
    

/* ScalarMembership */
    def visit(p : Absyn.CovariantScalarMembership, arg : collection.mutable.Map[String,String]) = ScalarMembership(
      p.uri_.accept(this,arg),
      null,
      Double.NaN,
      positive
    )
    def visit(p : Absyn.ContravariantScalarMembership, arg : collection.mutable.Map[String,String]) = ScalarMembership(
      p.uri_.accept(this,arg),
      null,
      Double.NaN,
      negative
    )
    def visit(p : Absyn.CentralScalarMembership, arg : collection.mutable.Map[String,String]) = ScalarMembership(
      p.uri_.accept(this,arg),
      null,
      Double.NaN,
      central
    )
    def visit(p : Absyn.GreaterThanScalarMembership, arg : collection.mutable.Map[String,String]) = ScalarMembership(
      p.uri_1.accept(this,arg),
      p.uri_2.accept(this,arg),
      p.double_,
      positive
    ) 
    def visit(p : Absyn.LessThanScalarMembership, arg : collection.mutable.Map[String,String]) = ScalarMembership(
      p.uri_1.accept(this,arg),
      p.uri_2.accept(this,arg),
      p.double_,
      negative
    ) 
    def visit(p : Absyn.BoundedScalarMembership, arg : collection.mutable.Map[String,String]) = ScalarMembership(
      p.uri_1.accept(this,arg),
      p.uri_2.accept(this,arg),
      p.double_1,
      central,
      p.double_2
    ) 
/* Category */
    def visit(p : Absyn.ESingular, arg : collection.mutable.Map[String,String]) = (lexinfo("number"),lexinfo("singular"))
    def visit(p : Absyn.EDual, arg : collection.mutable.Map[String,String]) = (lexinfo("number"),lexinfo("dual"))
    def visit(p : Absyn.EPlural, arg : collection.mutable.Map[String,String]) = (lexinfo("number"),lexinfo("plural"))
    def visit(p : Absyn.ENominative, arg : collection.mutable.Map[String,String]) = (lexinfo("case"),lexinfo("nominative"))
    def visit(p : Absyn.EAccusative, arg : collection.mutable.Map[String,String]) = (lexinfo("case"),lexinfo("accusative"))
    def visit(p : Absyn.EGenitive, arg : collection.mutable.Map[String,String]) = (lexinfo("case"),lexinfo("genitive"))
    def visit(p : Absyn.EDative, arg : collection.mutable.Map[String,String]) = (lexinfo("case"),lexinfo("dative"))
    def visit(p : Absyn.EComparative, arg : collection.mutable.Map[String,String]) = (lexinfo("degree"),lexinfo("comparative"))
    def visit(p : Absyn.ESuperlative, arg : collection.mutable.Map[String,String]) = (lexinfo("degree"),lexinfo("superlative"))
    def visit(p : Absyn.EPresent, arg : collection.mutable.Map[String,String]) = (lexinfo("tense"),lexinfo("present"))
    def visit(p : Absyn.EPast, arg : collection.mutable.Map[String,String]) = (lexinfo("tense"),lexinfo("past"))
    def visit(p : Absyn.EFuture, arg : collection.mutable.Map[String,String]) = (lexinfo("tense"),lexinfo("future"))
    def visit(p : Absyn.EFirstPerson, arg : collection.mutable.Map[String,String]) = (lexinfo("person"),lexinfo("firstPerson"))
    def visit(p : Absyn.ESecondPerson, arg : collection.mutable.Map[String,String]) = (lexinfo("person"),lexinfo("secondPerson"))
    def visit(p : Absyn.EThirdPerson, arg : collection.mutable.Map[String,String]) = (lexinfo("person"),lexinfo("thirdPerson"))
    def visit(p : Absyn.EImperfect, arg : collection.mutable.Map[String,String]) = (lexinfo("aspect"),lexinfo("imperfective"))
    def visit(p : Absyn.EImperative, arg : collection.mutable.Map[String,String]) = (lexinfo("aspect"),lexinfo("imperative"))
    def visit(p : Absyn.EIndicative, arg : collection.mutable.Map[String,String]) = (lexinfo("aspect"),lexinfo("indicative"))
    def visit(p : Absyn.ESubjunctive, arg : collection.mutable.Map[String,String]) = (lexinfo("verbFormMood"),lexinfo("subjunctive"))
    def visit(p : Absyn.EConditional, arg : collection.mutable.Map[String,String]) = (lexinfo("verbFormMood"),lexinfo("conditional"))
    def visit(p : Absyn.EGerundive, arg : collection.mutable.Map[String,String]) = (lexinfo("verbFormMood"),lexinfo("gerundive"))
    def visit(p : Absyn.EInfinitive, arg : collection.mutable.Map[String,String]) = (lexinfo("verbFormMood"),lexinfo("infinitive"))
    def visit(p : Absyn.EParticiple, arg : collection.mutable.Map[String,String]) = (lexinfo("verbFormMood"),lexinfo("participle"))
    def visit(p : Absyn.EAnyCat, arg : collection.mutable.Map[String,String]) = (p.uri_1.accept(this,arg),p.uri_2.accept(this,arg))
/* POSTag */
    def visit(p : Absyn.EAdjectivePOS, arg : collection.mutable.Map[String,String]) = pos.adjective
    def visit(p : Absyn.EAdpositionPOS, arg : collection.mutable.Map[String,String]) = pos.adposition
    def visit(p : Absyn.EAdverbPOS, arg : collection.mutable.Map[String,String]) = pos.adverb
    def visit(p : Absyn.EArticlePOS, arg : collection.mutable.Map[String,String]) = pos.article
    def visit(p : Absyn.EBulletPOS, arg : collection.mutable.Map[String,String]) = pos.bullet
    def visit(p : Absyn.ECircumpositionPOS, arg : collection.mutable.Map[String,String]) = pos.circumposition
    def visit(p : Absyn.EColonPOSPOS, arg : collection.mutable.Map[String,String]) = pos.colon
    def visit(p : Absyn.ECommaPOS, arg : collection.mutable.Map[String,String]) = pos.comma
    def visit(p : Absyn.EConjunctionPOS, arg : collection.mutable.Map[String,String]) = pos.conjunction
    def visit(p : Absyn.ECopulaPOS, arg : collection.mutable.Map[String,String]) = pos.copula
    def visit(p : Absyn.EDeterminerPOS, arg : collection.mutable.Map[String,String]) = pos.determiner
    def visit(p : Absyn.EInterjectionPOS, arg : collection.mutable.Map[String,String]) = pos.interjection
    def visit(p : Absyn.ENounPOS, arg : collection.mutable.Map[String,String]) = pos.noun
    def visit(p : Absyn.ENumeralPOS, arg : collection.mutable.Map[String,String]) = pos.numeral
    def visit(p : Absyn.EParticlePOS, arg : collection.mutable.Map[String,String]) = pos.particle
    def visit(p : Absyn.EPointPOS, arg : collection.mutable.Map[String,String]) = pos.point
    def visit(p : Absyn.EPostpositionPOS, arg : collection.mutable.Map[String,String]) = pos.postposition
    def visit(p : Absyn.EPrepositionPOS, arg : collection.mutable.Map[String,String]) = pos.preposition
    def visit(p : Absyn.EPronounPOS, arg : collection.mutable.Map[String,String]) = pos.pronoun
    def visit(p : Absyn.EPunctuationPOS, arg : collection.mutable.Map[String,String]) = pos.punctuation
    def visit(p : Absyn.ESemiColonPOS, arg : collection.mutable.Map[String,String]) = pos.semiColon
    def visit(p : Absyn.ESlashPOS, arg : collection.mutable.Map[String,String]) = pos.slash
    def visit(p : Absyn.EVerbPOS, arg : collection.mutable.Map[String,String]) = pos.verb
    def visit(p : Absyn.EAnyPOS, arg : collection.mutable.Map[String,String]) = p.string_ match {
      case "adverbial pronoun" => pos.adverbialPronoun
      case "affirmative particule" => pos.affirmativeParticule 
      case "affixed personal pronoun" => pos. affixedPersonalPronoun 
      case "allusive pronoun" => pos. allusivePronoun 
      case "cardinal numeral" => pos. cardinalNumeral  
      case "close parenthesis" => pos. closeParenthesis  
      case "collective pronoun" => pos. collectivePronoun 
      case "comparative particle" => pos. comparativeParticle 
      case "compound preposition" => pos. compoundPreposition 
      case "conditional particule" => pos. conditionalParticule 
      case "conditional pronoun" => pos. conditionalPronoun 
      case "coordinating conjunction" => pos. coordinatingConjunction 
      case "coordination particle" => pos. coordinationParticle 
      case "deficient verb" => pos.deficientVerb 
      case "definite article" => pos. definiteArticle 
      case "demonstrative determiner" => pos. demonstrativeDeterminer 
      case "demonstrative pronoun" => pos. demonstrativePronoun 
      case "diminutive noun" => pos. diminutiveNoun 
      case "distinctive particle" => pos. distinctiveParticle 
      case "emphatic pronoun" => pos. emphaticPronoun 
      case "exclamative determiner" => pos. exclamativeDeterminer 
      case "exclamative point" => pos. exclamativePoint 
      case "exclamative pronoun" => pos. exclamativePronoun 
      case "existential pronoun" => pos. existentialPronoun 
      case "fused preposition determiner" => pos. fusedPrepositionDeterminer 
      case "fused preposition pronoun" => pos. fusedPrepositionPronoun 
      case "fused preposition" => pos. fusedPreposition 
      case "fused pronounAuxiliary" => pos. fusedPronounAuxiliary 
      case "future particle" => pos. futureParticle 
      case "general adverb" => pos. generalAdverb 
      case "generalization word" => pos. generalizationWord 
      case "generic numeral" => pos. genericNumeral 
      case "impersonal pronoun" => pos. impersonalPronoun 
      case "indefinite article" => pos. indefiniteArticle 
      case "indefinite cardinal numeral" => pos. indefiniteCardinalNumeral 
      case "indefinite determiner" => pos. indefiniteDeterminer 
      case "indefinite multiplicative numeral" => pos. indefiniteMultiplicativeNumeral 
      case "indefinite ordinal numeral" => pos. indefiniteOrdinalNumeral 
      case "indefinite pronoun" => pos. indefinitePronoun 
      case "infinitive particle" => pos. infinitiveParticle 
      case "interrogative cardinal numeral" => pos. interrogativeCardinalNumeral 
      case "interrogative determiner" => pos. interrogativeDeterminer 
      case "interrogative multiplicative numeral" => pos. interrogativeMultiplicativeNumeral 
      case "interrogative ordinal numeral" => pos. interrogativeOrdinalNumeral 
      case "interrogative particle" => pos. interrogativeParticle 
      case "interrogative pronoun" => pos. interrogativePronoun 
      case "interrogative relative pronoun" => pos. interrogativeRelativePronoun 
      case "inverted comma" => pos. invertedComma 
      case "irreflexive personal pronoun" => pos. irreflexivePersonalPronoun 
      case "light verb" => pos. lightVerb 
      case "main verb" => pos. mainVerb 
      case "multiplicative numeral" => pos. multiplicativeNumeral  
      case "negative particle" => pos. negativeParticle 
      case "negative pronoun" => pos. negativePronoun 
      case "numeral fraction" => pos. numeralFraction 
      case "open parenthesis" => pos.openParenthesis 
      case "ordinal adjective" => pos. ordinalAdjective 
      case "participle adjective" => pos. participleAdjective 
      case "partitive article" => pos. partitiveArticle 
      case "past participle adjective" => pos. pastParticipleAdjective 
      case "personal pronoun" => pos. personalPronoun 
      case "plain verb" => pos. plainVerb 
      case "possessive adjective" => pos. possessiveAdjective 
      case "possessive determiner" => pos. possessiveDeterminer 
      case "possessive particle" => pos. possessiveParticle 
      case "possessive pronoun" => pos. possessivePronoun 
      case "possessive relative pronoun" => pos. possessiveRelativePronoun 
      case "prepositional adverb" => pos. prepositionalAdverb 
      case "present participle adjective" => pos. presentParticipleAdjective 
      case "presentative pronoun" => pos. presentativePronoun 
      case "pronominal adverb" => pos. pronominalAdverb 
      case "qualifier adjective" => pos. qualifierAdjective 
      case "question mark" => pos. questionMark 
      case "reciprocal pronoun" => pos. reciprocalPronoun 
      case "reflexive adjective" => pos. reflexiveAdjective 
      case "reflexive personal pronoun" => pos. reflexivePersonalPronoun 
      case "reflexive possessive pronoun" => pos. reflexivePossessivePronoun 
      case "relation noun" => pos. relationNoun 
      case "relative determiner" => pos. relativeDeterminer 
      case "relative particle" => pos. relativeParticle 
      case "relative pronoun" => pos. relativePronoun 
      case "strong personal pronoun" => pos. strongPersonalPronoun 
      case "subordinating conjunction" => pos. subordinatingConjunction 
      case "superlative particle" => pos. superlativeParticle 
      case "suspension points" => pos. suspensionPoints 
      case "unclassified particle" => pos. unclassifiedParticle 
      case "weak personal pronoun" => pos. weakPersonalPronoun 
      case "adjective" => pos. adjective 
      case "adposition" => pos. adposition 
      case "adverb" => pos. adverb 
      case "article" => pos. article 
      case "bullet" => pos. bullet 
      case "circumposition" => pos. circumposition  
      case "colon" => pos. colon 
      case "comma" => pos. comma 
      case "common noun" => pos. commonNoun 
      case "conjunction" => pos. conjunction 
      case "copula" => pos. copula 
      case "determiner" => pos. determiner 
      case "interjection" => pos. interjection 
      case "modal" => pos. modal 
      case "noun" => pos. noun 
      case "numeral" => pos. numeral 
      case "particle" => pos. particle 
      case "point" => pos. point 
      case "postposition" => pos. postposition  
      case "preposition" => pos. preposition 
      case "pronoun" => pos. pronoun 
      case "proper noun" => pos. properNoun 
      case "punctuation" => pos. punctuation 
      case "semiColon" => pos. semiColon 
      case "slash" => pos. slash 
      case "verb" => pos. verb
      case _ => throw new RuntimeException(p.string_ + " is not in LexInfo")
    }
/* Gender */
    def visit(p : Absyn.EMascGender, arg : collection.mutable.Map[String,String]) = Masculine
    def visit(p : Absyn.EFemGender, arg : collection.mutable.Map[String,String]) = Feminine
    def visit(p : Absyn.ENeutGender, arg : collection.mutable.Map[String,String]) = Neuter
    def visit(p : Absyn.ECommonGender, arg : collection.mutable.Map[String,String]) = CommonGender
    def visit(p : Absyn.EOtherGender, arg : collection.mutable.Map[String,String]) = OtherGender
    
/* URI */
    def visit(p : Absyn.EQName, arg : collection.mutable.Map[String,String]) = URI.create(
      arg.getOrElse(p.ident_1, { throw new IllegalArgumentException("Undeclared prefix \""+p.ident_1+"\"") }) + p.ident_2)
    def visit(p : Absyn.EQName2, arg : collection.mutable.Map[String,String]) = URI.create(
      arg.getOrElse("base", "#") + p.ident_)
    def visit(p : Absyn.EURI, arg : collection.mutable.Map[String,String]) = URI.create(p.fulluri_.substring(1,p.fulluri_.length-1))
}
