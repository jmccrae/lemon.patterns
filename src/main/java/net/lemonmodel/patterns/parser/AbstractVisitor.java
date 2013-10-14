package net.lemonmodel.patterns.parser;
import net.lemonmodel.patterns.parser.Absyn.*;
/** BNFC-Generated Abstract Visitor */
public class AbstractVisitor<R,A> implements AllVisitor<R,A> {
/* Statements */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EStatments p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(net.lemonmodel.patterns.parser.Absyn.Statements p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* Statement */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPrefix p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ELexicon p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(net.lemonmodel.patterns.parser.Absyn.Statement p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* Pattern */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPatternWithForm p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENoun p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENounWithGender p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EVerb p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAdjective p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(net.lemonmodel.patterns.parser.Absyn.Pattern p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* NounPattern */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EName p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EClassNoun p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ERelationalNoun1 p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ERelationalNoun2 p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ERelationalMultivalentNoun p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun1 p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun2 p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(net.lemonmodel.patterns.parser.Absyn.NounPattern p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* VerbPattern */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EStateVerb1 p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EStateVerb2 p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EStateVerb3 p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ETelicEventVerb p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENontelicEventVerb p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDunnotelicEventVerb p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb1 p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb2 p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb3 p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb4 p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb5 p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb6 p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb7 p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb8 p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDurativeEventVerb p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EInstantEventVerb p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EEventVerb p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(net.lemonmodel.patterns.parser.Absyn.VerbPattern p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* AdjectivePattern */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIntersectiveAdjective p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIntersectiveObjectPropertyAdjective p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIntersectiveDataPropertyAdjective p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPropertyModifyingAdjective p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ERelationalAdjective p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EScalarAdjective p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(net.lemonmodel.patterns.parser.Absyn.AdjectivePattern p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* Arg */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EOptionalArg p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ERestrictedArg p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESubject p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDirectObject p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIndirectObject p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECopulativeArg p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECopulativeSubject p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPrepositionalObject p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPostpositionalObject p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPossessiveAdjunct p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(net.lemonmodel.patterns.parser.Absyn.Arg p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* OntologyFrameElement */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EURIAsSynArg p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EArgAsOFE p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(net.lemonmodel.patterns.parser.Absyn.OntologyFrameElement p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* PNP */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPNPSimple p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPNPComplex p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(net.lemonmodel.patterns.parser.Absyn.PNP p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* NP */
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENPSimple p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENPComplex p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(net.lemonmodel.patterns.parser.Absyn.NP p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* VP */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EVPSimple p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EVPComplex p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(net.lemonmodel.patterns.parser.Absyn.VP p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* AP */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAPSimple p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAPComplex p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(net.lemonmodel.patterns.parser.Absyn.AP p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* POSTaggedWord */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPOSTaggedHeadWord p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPOSTaggedWord p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(net.lemonmodel.patterns.parser.Absyn.POSTaggedWord p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* ScalarMembership */
    public R visit(net.lemonmodel.patterns.parser.Absyn.CovariantScalarMembership p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ContravariantScalarMembership p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.CentralScalarMembership p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.GreaterThanScalarMembership p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.LessThanScalarMembership p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.BoundedScalarMembership p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(net.lemonmodel.patterns.parser.Absyn.ScalarMembership p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* Category */
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESingular p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDual p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPlural p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENominative p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAccusative p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EGenitive p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDative p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EComparative p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESuperlative p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPresent p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPast p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EFuture p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EFirstPerson p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESecondPerson p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EThirdPerson p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EImperfect p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EImperative p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIndicative p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESubjunctive p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConditional p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EGerundive p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EInfinitive p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EParticiple p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAnyCat p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(net.lemonmodel.patterns.parser.Absyn.Category p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* POSTag */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAdjectivePOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAdpositionPOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAdverbPOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EArticlePOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EBulletPOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECircumpositionPOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EColonPOSPOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECommaPOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConjunctionPOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECopulaPOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDeterminerPOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EInterjectionPOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENounPOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENumeralPOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EParticlePOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPointPOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPostpositionPOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPrepositionPOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPronounPOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPunctuationPOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESemiColonPOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESlashPOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EVerbPOS p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAnyPOS p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(net.lemonmodel.patterns.parser.Absyn.POSTag p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* Gender */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EMascGender p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EFemGender p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENeutGender p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECommonGender p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EOtherGender p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(net.lemonmodel.patterns.parser.Absyn.Gender p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* URI */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EQName p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EQName2 p, A arg) { return visitDefault(p, arg); }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EURI p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(net.lemonmodel.patterns.parser.Absyn.URI p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }

}
