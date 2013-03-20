package net.lemonmodel.patterns.parser;
import net.lemonmodel.patterns.parser.Absyn.*;
/** BNFC-Generated Composition Visitor
*/

public class ComposVisitor<A> implements
  net.lemonmodel.patterns.parser.Absyn.Statements.Visitor<net.lemonmodel.patterns.parser.Absyn.Statements,A>,
  net.lemonmodel.patterns.parser.Absyn.Statement.Visitor<net.lemonmodel.patterns.parser.Absyn.Statement,A>,
  net.lemonmodel.patterns.parser.Absyn.Pattern.Visitor<net.lemonmodel.patterns.parser.Absyn.Pattern,A>,
  net.lemonmodel.patterns.parser.Absyn.NounPattern.Visitor<net.lemonmodel.patterns.parser.Absyn.NounPattern,A>,
  net.lemonmodel.patterns.parser.Absyn.VerbPattern.Visitor<net.lemonmodel.patterns.parser.Absyn.VerbPattern,A>,
  net.lemonmodel.patterns.parser.Absyn.AdjectivePattern.Visitor<net.lemonmodel.patterns.parser.Absyn.AdjectivePattern,A>,
  net.lemonmodel.patterns.parser.Absyn.Arg.Visitor<net.lemonmodel.patterns.parser.Absyn.Arg,A>,
  net.lemonmodel.patterns.parser.Absyn.OntologyFrameElement.Visitor<net.lemonmodel.patterns.parser.Absyn.OntologyFrameElement,A>,
  net.lemonmodel.patterns.parser.Absyn.PNP.Visitor<net.lemonmodel.patterns.parser.Absyn.PNP,A>,
  net.lemonmodel.patterns.parser.Absyn.NP.Visitor<net.lemonmodel.patterns.parser.Absyn.NP,A>,
  net.lemonmodel.patterns.parser.Absyn.VP.Visitor<net.lemonmodel.patterns.parser.Absyn.VP,A>,
  net.lemonmodel.patterns.parser.Absyn.AP.Visitor<net.lemonmodel.patterns.parser.Absyn.AP,A>,
  net.lemonmodel.patterns.parser.Absyn.POSTaggedWord.Visitor<net.lemonmodel.patterns.parser.Absyn.POSTaggedWord,A>,
  net.lemonmodel.patterns.parser.Absyn.ScalarMembership.Visitor<net.lemonmodel.patterns.parser.Absyn.ScalarMembership,A>,
  net.lemonmodel.patterns.parser.Absyn.Category.Visitor<net.lemonmodel.patterns.parser.Absyn.Category,A>,
  net.lemonmodel.patterns.parser.Absyn.POSTag.Visitor<net.lemonmodel.patterns.parser.Absyn.POSTag,A>,
  net.lemonmodel.patterns.parser.Absyn.URI.Visitor<net.lemonmodel.patterns.parser.Absyn.URI,A>
{
/* Statements */
    public Statements visit(net.lemonmodel.patterns.parser.Absyn.EStatments p, A arg)
    {
      ListStatement liststatement_ = new ListStatement();
      for (Statement x : p.liststatement_) {
        liststatement_.add(x.accept(this,arg));
      }

      return new net.lemonmodel.patterns.parser.Absyn.EStatments(liststatement_);
    }

/* Statement */
    public Statement visit(net.lemonmodel.patterns.parser.Absyn.EPrefix p, A arg)
    {
      String ident_ = p.ident_;
      String fulluri_ = p.fulluri_;

      return new net.lemonmodel.patterns.parser.Absyn.EPrefix(ident_, fulluri_);
    }
    public Statement visit(net.lemonmodel.patterns.parser.Absyn.ELexicon p, A arg)
    {
      URI uri_ = p.uri_.accept(this, arg);
      String string_ = p.string_;
      ListPattern listpattern_ = new ListPattern();
      for (Pattern x : p.listpattern_) {
        listpattern_.add(x.accept(this,arg));
      }

      return new net.lemonmodel.patterns.parser.Absyn.ELexicon(uri_, string_, listpattern_);
    }

/* Pattern */
    public Pattern visit(net.lemonmodel.patterns.parser.Absyn.EPatternWithForm p, A arg)
    {
      Pattern pattern_ = p.pattern_.accept(this, arg);
      ListCategory listcategory_ = new ListCategory();
      for (Category x : p.listcategory_) {
        listcategory_.add(x.accept(this,arg));
      }
      String string_ = p.string_;

      return new net.lemonmodel.patterns.parser.Absyn.EPatternWithForm(pattern_, listcategory_, string_);
    }
    public Pattern visit(net.lemonmodel.patterns.parser.Absyn.ENoun p, A arg)
    {
      NounPattern nounpattern_ = p.nounpattern_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.ENoun(nounpattern_);
    }
    public Pattern visit(net.lemonmodel.patterns.parser.Absyn.EVerb p, A arg)
    {
      VerbPattern verbpattern_ = p.verbpattern_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EVerb(verbpattern_);
    }
    public Pattern visit(net.lemonmodel.patterns.parser.Absyn.EAdjective p, A arg)
    {
      AdjectivePattern adjectivepattern_ = p.adjectivepattern_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EAdjective(adjectivepattern_);
    }

/* NounPattern */
    public NounPattern visit(net.lemonmodel.patterns.parser.Absyn.EName p, A arg)
    {
      PNP pnp_ = p.pnp_.accept(this, arg);
      URI uri_ = p.uri_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EName(pnp_, uri_);
    }
    public NounPattern visit(net.lemonmodel.patterns.parser.Absyn.EClassNoun p, A arg)
    {
      NP np_ = p.np_.accept(this, arg);
      URI uri_ = p.uri_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EClassNoun(np_, uri_);
    }
    public NounPattern visit(net.lemonmodel.patterns.parser.Absyn.ERelationalNoun1 p, A arg)
    {
      NP np_ = p.np_.accept(this, arg);
      URI uri_ = p.uri_.accept(this, arg);
      Arg arg_1 = p.arg_1.accept(this, arg);
      Arg arg_2 = p.arg_2.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.ERelationalNoun1(np_, uri_, arg_1, arg_2);
    }
    public NounPattern visit(net.lemonmodel.patterns.parser.Absyn.ERelationalNoun2 p, A arg)
    {
      NP np_ = p.np_.accept(this, arg);
      URI uri_ = p.uri_.accept(this, arg);
      Arg arg_ = p.arg_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.ERelationalNoun2(np_, uri_, arg_);
    }
    public NounPattern visit(net.lemonmodel.patterns.parser.Absyn.ERelationalMultivalentNoun p, A arg)
    {
      NP np_ = p.np_.accept(this, arg);
      URI uri_ = p.uri_.accept(this, arg);
      ListOntologyFrameElement listontologyframeelement_ = new ListOntologyFrameElement();
      for (OntologyFrameElement x : p.listontologyframeelement_) {
        listontologyframeelement_.add(x.accept(this,arg));
      }

      return new net.lemonmodel.patterns.parser.Absyn.ERelationalMultivalentNoun(np_, uri_, listontologyframeelement_);
    }
    public NounPattern visit(net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun1 p, A arg)
    {
      NP np_ = p.np_.accept(this, arg);
      URI uri_1 = p.uri_1.accept(this, arg);
      URI uri_2 = p.uri_2.accept(this, arg);
      Arg arg_1 = p.arg_1.accept(this, arg);
      Arg arg_2 = p.arg_2.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun1(np_, uri_1, uri_2, arg_1, arg_2);
    }
    public NounPattern visit(net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun2 p, A arg)
    {
      NP np_ = p.np_.accept(this, arg);
      URI uri_1 = p.uri_1.accept(this, arg);
      URI uri_2 = p.uri_2.accept(this, arg);
      Arg arg_ = p.arg_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun2(np_, uri_1, uri_2, arg_);
    }

/* VerbPattern */
    public VerbPattern visit(net.lemonmodel.patterns.parser.Absyn.EStateVerb1 p, A arg)
    {
      VP vp_ = p.vp_.accept(this, arg);
      URI uri_ = p.uri_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EStateVerb1(vp_, uri_);
    }
    public VerbPattern visit(net.lemonmodel.patterns.parser.Absyn.EStateVerb2 p, A arg)
    {
      VP vp_ = p.vp_.accept(this, arg);
      URI uri_ = p.uri_.accept(this, arg);
      Arg arg_ = p.arg_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EStateVerb2(vp_, uri_, arg_);
    }
    public VerbPattern visit(net.lemonmodel.patterns.parser.Absyn.EStateVerb3 p, A arg)
    {
      VP vp_ = p.vp_.accept(this, arg);
      URI uri_ = p.uri_.accept(this, arg);
      Arg arg_1 = p.arg_1.accept(this, arg);
      Arg arg_2 = p.arg_2.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EStateVerb3(vp_, uri_, arg_1, arg_2);
    }
    public VerbPattern visit(net.lemonmodel.patterns.parser.Absyn.ETelicEventVerb p, A arg)
    {
      VerbPattern verbpattern_ = p.verbpattern_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.ETelicEventVerb(verbpattern_);
    }
    public VerbPattern visit(net.lemonmodel.patterns.parser.Absyn.ENontelicEventVerb p, A arg)
    {
      VerbPattern verbpattern_ = p.verbpattern_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.ENontelicEventVerb(verbpattern_);
    }
    public VerbPattern visit(net.lemonmodel.patterns.parser.Absyn.EDunnotelicEventVerb p, A arg)
    {
      VerbPattern verbpattern_ = p.verbpattern_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EDunnotelicEventVerb(verbpattern_);
    }
    public VerbPattern visit(net.lemonmodel.patterns.parser.Absyn.EDurativeConsequenceVerb p, A arg)
    {
      VerbPattern verbpattern_ = p.verbpattern_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EDurativeConsequenceVerb(verbpattern_);
    }
    public VerbPattern visit(net.lemonmodel.patterns.parser.Absyn.EInstantConsequenceVerb p, A arg)
    {
      VerbPattern verbpattern_ = p.verbpattern_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EInstantConsequenceVerb(verbpattern_);
    }
    public VerbPattern visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb p, A arg)
    {
      VerbPattern verbpattern_ = p.verbpattern_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb(verbpattern_);
    }
    public VerbPattern visit(net.lemonmodel.patterns.parser.Absyn.EDurativeEventVerb p, A arg)
    {
      VerbPattern verbpattern_ = p.verbpattern_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EDurativeEventVerb(verbpattern_);
    }
    public VerbPattern visit(net.lemonmodel.patterns.parser.Absyn.EInstantEventVerb p, A arg)
    {
      VerbPattern verbpattern_ = p.verbpattern_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EInstantEventVerb(verbpattern_);
    }
    public VerbPattern visit(net.lemonmodel.patterns.parser.Absyn.EEventVerb p, A arg)
    {
      VP vp_ = p.vp_.accept(this, arg);
      URI uri_ = p.uri_.accept(this, arg);
      ListOntologyFrameElement listontologyframeelement_ = new ListOntologyFrameElement();
      for (OntologyFrameElement x : p.listontologyframeelement_) {
        listontologyframeelement_.add(x.accept(this,arg));
      }

      return new net.lemonmodel.patterns.parser.Absyn.EEventVerb(vp_, uri_, listontologyframeelement_);
    }
    public VerbPattern visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb1 p, A arg)
    {
      VP vp_ = p.vp_.accept(this, arg);
      URI uri_1 = p.uri_1.accept(this, arg);
      Arg arg_1 = p.arg_1.accept(this, arg);
      Arg arg_2 = p.arg_2.accept(this, arg);
      URI uri_2 = p.uri_2.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb1(vp_, uri_1, arg_1, arg_2, uri_2);
    }
    public VerbPattern visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb2 p, A arg)
    {
      VP vp_ = p.vp_.accept(this, arg);
      URI uri_ = p.uri_.accept(this, arg);
      Arg arg_1 = p.arg_1.accept(this, arg);
      Arg arg_2 = p.arg_2.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb2(vp_, uri_, arg_1, arg_2);
    }

/* AdjectivePattern */
    public AdjectivePattern visit(net.lemonmodel.patterns.parser.Absyn.EIntersectiveAdjective p, A arg)
    {
      AP ap_ = p.ap_.accept(this, arg);
      URI uri_ = p.uri_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EIntersectiveAdjective(ap_, uri_);
    }
    public AdjectivePattern visit(net.lemonmodel.patterns.parser.Absyn.EIntersectiveObjectPropertyAdjective p, A arg)
    {
      AP ap_ = p.ap_.accept(this, arg);
      URI uri_1 = p.uri_1.accept(this, arg);
      URI uri_2 = p.uri_2.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EIntersectiveObjectPropertyAdjective(ap_, uri_1, uri_2);
    }
    public AdjectivePattern visit(net.lemonmodel.patterns.parser.Absyn.EIntersectiveDataPropertyAdjective p, A arg)
    {
      AP ap_ = p.ap_.accept(this, arg);
      URI uri_1 = p.uri_1.accept(this, arg);
      URI uri_2 = p.uri_2.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EIntersectiveDataPropertyAdjective(ap_, uri_1, uri_2);
    }
    public AdjectivePattern visit(net.lemonmodel.patterns.parser.Absyn.EPropertyModifyingAdjective p, A arg)
    {
      AP ap_ = p.ap_.accept(this, arg);
      URI uri_ = p.uri_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EPropertyModifyingAdjective(ap_, uri_);
    }
    public AdjectivePattern visit(net.lemonmodel.patterns.parser.Absyn.ERelationalAdjective p, A arg)
    {
      AP ap_ = p.ap_.accept(this, arg);
      URI uri_ = p.uri_.accept(this, arg);
      Arg arg_ = p.arg_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.ERelationalAdjective(ap_, uri_, arg_);
    }
    public AdjectivePattern visit(net.lemonmodel.patterns.parser.Absyn.EScalarAdjective p, A arg)
    {
      AP ap_ = p.ap_.accept(this, arg);
      ListScalarMembership listscalarmembership_ = new ListScalarMembership();
      for (ScalarMembership x : p.listscalarmembership_) {
        listscalarmembership_.add(x.accept(this,arg));
      }

      return new net.lemonmodel.patterns.parser.Absyn.EScalarAdjective(ap_, listscalarmembership_);
    }

/* Arg */
    public Arg visit(net.lemonmodel.patterns.parser.Absyn.ESubject p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.ESubject();
    }
    public Arg visit(net.lemonmodel.patterns.parser.Absyn.EDirectObject p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EDirectObject();
    }
    public Arg visit(net.lemonmodel.patterns.parser.Absyn.EIndirectObject p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EIndirectObject();
    }
    public Arg visit(net.lemonmodel.patterns.parser.Absyn.ECopulativeArg p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.ECopulativeArg();
    }
    public Arg visit(net.lemonmodel.patterns.parser.Absyn.ECopulativeSubject p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.ECopulativeSubject();
    }
    public Arg visit(net.lemonmodel.patterns.parser.Absyn.EPrepositionalObject p, A arg)
    {
      String string_ = p.string_;

      return new net.lemonmodel.patterns.parser.Absyn.EPrepositionalObject(string_);
    }
    public Arg visit(net.lemonmodel.patterns.parser.Absyn.EPostpositionalObject p, A arg)
    {
      String string_ = p.string_;

      return new net.lemonmodel.patterns.parser.Absyn.EPostpositionalObject(string_);
    }
    public Arg visit(net.lemonmodel.patterns.parser.Absyn.EPossessiveAdjunct p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EPossessiveAdjunct();
    }

/* OntologyFrameElement */
    public OntologyFrameElement visit(net.lemonmodel.patterns.parser.Absyn.EURIAsSynArg p, A arg)
    {
      URI uri_ = p.uri_.accept(this, arg);
      Arg arg_ = p.arg_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EURIAsSynArg(uri_, arg_);
    }

/* PNP */
    public PNP visit(net.lemonmodel.patterns.parser.Absyn.EPNPSimple p, A arg)
    {
      String string_ = p.string_;

      return new net.lemonmodel.patterns.parser.Absyn.EPNPSimple(string_);
    }
    public PNP visit(net.lemonmodel.patterns.parser.Absyn.EPNPComplex p, A arg)
    {
      ListPOSTaggedWord listpostaggedword_ = new ListPOSTaggedWord();
      for (POSTaggedWord x : p.listpostaggedword_) {
        listpostaggedword_.add(x.accept(this,arg));
      }

      return new net.lemonmodel.patterns.parser.Absyn.EPNPComplex(listpostaggedword_);
    }

/* NP */
    public NP visit(net.lemonmodel.patterns.parser.Absyn.ENPSimple p, A arg)
    {
      String string_ = p.string_;

      return new net.lemonmodel.patterns.parser.Absyn.ENPSimple(string_);
    }
    public NP visit(net.lemonmodel.patterns.parser.Absyn.ENPComplex p, A arg)
    {
      ListPOSTaggedWord listpostaggedword_ = new ListPOSTaggedWord();
      for (POSTaggedWord x : p.listpostaggedword_) {
        listpostaggedword_.add(x.accept(this,arg));
      }

      return new net.lemonmodel.patterns.parser.Absyn.ENPComplex(listpostaggedword_);
    }

/* VP */
    public VP visit(net.lemonmodel.patterns.parser.Absyn.EVPSimple p, A arg)
    {
      String string_ = p.string_;

      return new net.lemonmodel.patterns.parser.Absyn.EVPSimple(string_);
    }
    public VP visit(net.lemonmodel.patterns.parser.Absyn.EVPComplex p, A arg)
    {
      ListPOSTaggedWord listpostaggedword_ = new ListPOSTaggedWord();
      for (POSTaggedWord x : p.listpostaggedword_) {
        listpostaggedword_.add(x.accept(this,arg));
      }

      return new net.lemonmodel.patterns.parser.Absyn.EVPComplex(listpostaggedword_);
    }

/* AP */
    public AP visit(net.lemonmodel.patterns.parser.Absyn.EAPSimple p, A arg)
    {
      String string_ = p.string_;

      return new net.lemonmodel.patterns.parser.Absyn.EAPSimple(string_);
    }
    public AP visit(net.lemonmodel.patterns.parser.Absyn.EAPComplex p, A arg)
    {
      ListPOSTaggedWord listpostaggedword_ = new ListPOSTaggedWord();
      for (POSTaggedWord x : p.listpostaggedword_) {
        listpostaggedword_.add(x.accept(this,arg));
      }

      return new net.lemonmodel.patterns.parser.Absyn.EAPComplex(listpostaggedword_);
    }

/* POSTaggedWord */
    public POSTaggedWord visit(net.lemonmodel.patterns.parser.Absyn.EPOSTaggedWord p, A arg)
    {
      String string_ = p.string_;
      POSTag postag_ = p.postag_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EPOSTaggedWord(string_, postag_);
    }

/* ScalarMembership */
    public ScalarMembership visit(net.lemonmodel.patterns.parser.Absyn.CovariantScalarMembership p, A arg)
    {
      URI uri_ = p.uri_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.CovariantScalarMembership(uri_);
    }
    public ScalarMembership visit(net.lemonmodel.patterns.parser.Absyn.ContravariantScalarMembership p, A arg)
    {
      URI uri_ = p.uri_.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.ContravariantScalarMembership(uri_);
    }
    public ScalarMembership visit(net.lemonmodel.patterns.parser.Absyn.GreaterThanScalarMembership p, A arg)
    {
      URI uri_1 = p.uri_1.accept(this, arg);
      Double double_ = p.double_;
      URI uri_2 = p.uri_2.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.GreaterThanScalarMembership(uri_1, double_, uri_2);
    }
    public ScalarMembership visit(net.lemonmodel.patterns.parser.Absyn.LessThanScalarMembership p, A arg)
    {
      URI uri_1 = p.uri_1.accept(this, arg);
      Double double_ = p.double_;
      URI uri_2 = p.uri_2.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.LessThanScalarMembership(uri_1, double_, uri_2);
    }

/* Category */
    public Category visit(net.lemonmodel.patterns.parser.Absyn.ESingular p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.ESingular();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.EDual p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EDual();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.EPlural p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EPlural();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.ENominative p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.ENominative();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.EAccusative p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EAccusative();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.EGenitive p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EGenitive();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.EDative p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EDative();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.EComparative p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EComparative();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.ESuperlative p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.ESuperlative();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.EPresent p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EPresent();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.EPast p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EPast();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.EFuture p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EFuture();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.EFirstPerson p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EFirstPerson();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.ESecondPerson p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.ESecondPerson();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.EThirdPerson p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EThirdPerson();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.EImperfect p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EImperfect();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.EImperative p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EImperative();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.EIndicative p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EIndicative();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.ESubjunctive p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.ESubjunctive();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.EConditional p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EConditional();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.EGerundive p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EGerundive();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.EInfinitive p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EInfinitive();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.EParticiple p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EParticiple();
    }
    public Category visit(net.lemonmodel.patterns.parser.Absyn.EAnyCat p, A arg)
    {
      URI uri_1 = p.uri_1.accept(this, arg);
      URI uri_2 = p.uri_2.accept(this, arg);

      return new net.lemonmodel.patterns.parser.Absyn.EAnyCat(uri_1, uri_2);
    }

/* POSTag */
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.EAdjectivePOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EAdjectivePOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.EAdpositionPOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EAdpositionPOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.EAdverbPOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EAdverbPOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.EArticlePOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EArticlePOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.EBulletPOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EBulletPOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.ECircumpositionPOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.ECircumpositionPOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.EColonPOSPOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EColonPOSPOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.ECommaPOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.ECommaPOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.EConjunctionPOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EConjunctionPOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.ECopulaPOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.ECopulaPOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.EDeterminerPOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EDeterminerPOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.EInterjectionPOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EInterjectionPOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.ENounPOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.ENounPOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.ENumeralPOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.ENumeralPOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.EParticlePOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EParticlePOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.EPointPOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EPointPOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.EPostpositionPOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EPostpositionPOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.EPrepositionPOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EPrepositionPOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.EPronounPOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EPronounPOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.EPunctuationPOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EPunctuationPOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.ESemiColonPOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.ESemiColonPOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.ESlashPOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.ESlashPOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.EVerbPOS p, A arg)
    {

      return new net.lemonmodel.patterns.parser.Absyn.EVerbPOS();
    }
    public POSTag visit(net.lemonmodel.patterns.parser.Absyn.EAnyPOS p, A arg)
    {
      String string_ = p.string_;

      return new net.lemonmodel.patterns.parser.Absyn.EAnyPOS(string_);
    }

/* URI */
    public URI visit(net.lemonmodel.patterns.parser.Absyn.EQName p, A arg)
    {
      String ident_1 = p.ident_1;
      String ident_2 = p.ident_2;

      return new net.lemonmodel.patterns.parser.Absyn.EQName(ident_1, ident_2);
    }
    public URI visit(net.lemonmodel.patterns.parser.Absyn.EURI p, A arg)
    {
      String fulluri_ = p.fulluri_;

      return new net.lemonmodel.patterns.parser.Absyn.EURI(fulluri_);
    }

}