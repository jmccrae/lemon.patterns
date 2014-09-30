package net.lemonmodel.patterns.parser;
import net.lemonmodel.patterns.parser.Absyn.*;
/*** BNFC-Generated Visitor Design Pattern Skeleton. ***/
/* This implements the common visitor design pattern.
   Tests show it to be slightly less efficient than the
   instanceof method, but easier to use. 
   Replace the R and A parameters with the desired return
   and context types.*/

public class VisitSkel
{
  public class StatementsVisitor<R,A> implements Statements.Visitor<R,A>
  {
    public R visit(net.lemonmodel.patterns.parser.Absyn.EStatments p, A arg)
    {
      /* Code For EStatments Goes Here */

      for (Statement x : p.liststatement_) {
      }

      return null;
    }

  }
  public class StatementVisitor<R,A> implements Statement.Visitor<R,A>
  {
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPrefix p, A arg)
    {
      /* Code For EPrefix Goes Here */

      //p.ident_;
      //p.fulluri_;

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ELexicon p, A arg)
    {
      /* Code For ELexicon Goes Here */

      p.uri_.accept(new URIVisitor<R,A>(), arg);
      //p.string_;
      for (PatternType x : p.listpatterntype_) {
      }

      return null;
    }

  }
  public class PatternTypeVisitor<R,A> implements PatternType.Visitor<R,A>
  {
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPatternWithRegister p, A arg)
    {
      /* Code For EPatternWithRegister Goes Here */

      p.pattern_.accept(new PatternVisitor<R,A>(), arg);
      p.register_.accept(new RegisterVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECorePattern p, A arg)
    {
      /* Code For ECorePattern Goes Here */

      p.pattern_.accept(new PatternVisitor<R,A>(), arg);

      return null;
    }

  }
  public class PatternVisitor<R,A> implements Pattern.Visitor<R,A>
  {
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPatternWithForm p, A arg)
    {
      /* Code For EPatternWithForm Goes Here */

      p.pattern_.accept(new PatternVisitor<R,A>(), arg);
      for (Category x : p.listcategory_) {
      }
      //p.string_;

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENoun p, A arg)
    {
      /* Code For ENoun Goes Here */

      p.nounpattern_.accept(new NounPatternVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENounWithGender p, A arg)
    {
      /* Code For ENounWithGender Goes Here */

      p.nounpattern_.accept(new NounPatternVisitor<R,A>(), arg);
      p.gender_.accept(new GenderVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EVerb p, A arg)
    {
      /* Code For EVerb Goes Here */

      p.verbpattern_.accept(new VerbPatternVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAdjective p, A arg)
    {
      /* Code For EAdjective Goes Here */

      p.adjectivepattern_.accept(new AdjectivePatternVisitor<R,A>(), arg);

      return null;
    }

  }
  public class NounPatternVisitor<R,A> implements NounPattern.Visitor<R,A>
  {
    public R visit(net.lemonmodel.patterns.parser.Absyn.EName p, A arg)
    {
      /* Code For EName Goes Here */

      p.pnp_.accept(new PNPVisitor<R,A>(), arg);
      p.uri_.accept(new URIVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EClassNoun p, A arg)
    {
      /* Code For EClassNoun Goes Here */

      p.np_.accept(new NPVisitor<R,A>(), arg);
      p.uri_.accept(new URIVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EObjectPropertyNoun p, A arg)
    {
      /* Code For EObjectPropertyNoun Goes Here */

      p.np_.accept(new NPVisitor<R,A>(), arg);
      p.uri_1.accept(new URIVisitor<R,A>(), arg);
      p.uri_2.accept(new URIVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDataPropertyNoun p, A arg)
    {
      /* Code For EDataPropertyNoun Goes Here */

      p.np_.accept(new NPVisitor<R,A>(), arg);
      p.uri_.accept(new URIVisitor<R,A>(), arg);
      //p.string_;

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ERelationalNoun1 p, A arg)
    {
      /* Code For ERelationalNoun1 Goes Here */

      p.np_.accept(new NPVisitor<R,A>(), arg);
      p.uri_.accept(new URIVisitor<R,A>(), arg);
      p.arg_1.accept(new ArgVisitor<R,A>(), arg);
      p.arg_2.accept(new ArgVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ERelationalNoun2 p, A arg)
    {
      /* Code For ERelationalNoun2 Goes Here */

      p.np_.accept(new NPVisitor<R,A>(), arg);
      p.uri_.accept(new URIVisitor<R,A>(), arg);
      p.arg_.accept(new ArgVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ERelationalMultivalentNoun p, A arg)
    {
      /* Code For ERelationalMultivalentNoun Goes Here */

      p.np_.accept(new NPVisitor<R,A>(), arg);
      p.uri_.accept(new URIVisitor<R,A>(), arg);
      for (OntologyFrameElement x : p.listontologyframeelement_) {
      }

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun1 p, A arg)
    {
      /* Code For EClassRelationalNoun1 Goes Here */

      p.np_.accept(new NPVisitor<R,A>(), arg);
      p.uri_1.accept(new URIVisitor<R,A>(), arg);
      p.uri_2.accept(new URIVisitor<R,A>(), arg);
      p.arg_1.accept(new ArgVisitor<R,A>(), arg);
      p.arg_2.accept(new ArgVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun2 p, A arg)
    {
      /* Code For EClassRelationalNoun2 Goes Here */

      p.np_.accept(new NPVisitor<R,A>(), arg);
      p.uri_1.accept(new URIVisitor<R,A>(), arg);
      p.uri_2.accept(new URIVisitor<R,A>(), arg);
      p.arg_.accept(new ArgVisitor<R,A>(), arg);

      return null;
    }

  }
  public class VerbPatternVisitor<R,A> implements VerbPattern.Visitor<R,A>
  {
    public R visit(net.lemonmodel.patterns.parser.Absyn.EStateVerb1 p, A arg)
    {
      /* Code For EStateVerb1 Goes Here */

      p.vp_.accept(new VPVisitor<R,A>(), arg);
      p.uri_.accept(new URIVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EStateVerb2 p, A arg)
    {
      /* Code For EStateVerb2 Goes Here */

      p.vp_.accept(new VPVisitor<R,A>(), arg);
      p.uri_.accept(new URIVisitor<R,A>(), arg);
      p.arg_.accept(new ArgVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EStateVerb3 p, A arg)
    {
      /* Code For EStateVerb3 Goes Here */

      p.vp_.accept(new VPVisitor<R,A>(), arg);
      p.uri_.accept(new URIVisitor<R,A>(), arg);
      p.arg_1.accept(new ArgVisitor<R,A>(), arg);
      p.arg_2.accept(new ArgVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ETelicEventVerb p, A arg)
    {
      /* Code For ETelicEventVerb Goes Here */

      p.verbpattern_.accept(new VerbPatternVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENontelicEventVerb p, A arg)
    {
      /* Code For ENontelicEventVerb Goes Here */

      p.verbpattern_.accept(new VerbPatternVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDunnotelicEventVerb p, A arg)
    {
      /* Code For EDunnotelicEventVerb Goes Here */

      p.verbpattern_.accept(new VerbPatternVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb1 p, A arg)
    {
      /* Code For EConsequenceVerb1 Goes Here */

      p.vp_.accept(new VPVisitor<R,A>(), arg);
      p.uri_1.accept(new URIVisitor<R,A>(), arg);
      p.ontologyframeelement_1.accept(new OntologyFrameElementVisitor<R,A>(), arg);
      p.ontologyframeelement_2.accept(new OntologyFrameElementVisitor<R,A>(), arg);
      p.uri_2.accept(new URIVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb2 p, A arg)
    {
      /* Code For EConsequenceVerb2 Goes Here */

      p.vp_.accept(new VPVisitor<R,A>(), arg);
      p.uri_.accept(new URIVisitor<R,A>(), arg);
      p.ontologyframeelement_1.accept(new OntologyFrameElementVisitor<R,A>(), arg);
      p.ontologyframeelement_2.accept(new OntologyFrameElementVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb3 p, A arg)
    {
      /* Code For EConsequenceVerb3 Goes Here */

      p.vp_.accept(new VPVisitor<R,A>(), arg);
      p.uri_1.accept(new URIVisitor<R,A>(), arg);
      p.ontologyframeelement_.accept(new OntologyFrameElementVisitor<R,A>(), arg);
      p.uri_2.accept(new URIVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb4 p, A arg)
    {
      /* Code For EConsequenceVerb4 Goes Here */

      p.vp_.accept(new VPVisitor<R,A>(), arg);
      p.uri_.accept(new URIVisitor<R,A>(), arg);
      p.ontologyframeelement_.accept(new OntologyFrameElementVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb5 p, A arg)
    {
      /* Code For EConsequenceVerb5 Goes Here */

      p.vp_.accept(new VPVisitor<R,A>(), arg);
      p.uri_1.accept(new URIVisitor<R,A>(), arg);
      p.ontologyframeelement_.accept(new OntologyFrameElementVisitor<R,A>(), arg);
      p.uri_2.accept(new URIVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb6 p, A arg)
    {
      /* Code For EConsequenceVerb6 Goes Here */

      p.vp_.accept(new VPVisitor<R,A>(), arg);
      p.uri_.accept(new URIVisitor<R,A>(), arg);
      p.ontologyframeelement_.accept(new OntologyFrameElementVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb7 p, A arg)
    {
      /* Code For EConsequenceVerb7 Goes Here */

      p.vp_.accept(new VPVisitor<R,A>(), arg);
      p.uri_1.accept(new URIVisitor<R,A>(), arg);
      p.uri_2.accept(new URIVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb8 p, A arg)
    {
      /* Code For EConsequenceVerb8 Goes Here */

      p.vp_.accept(new VPVisitor<R,A>(), arg);
      p.uri_.accept(new URIVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDurativeEventVerb p, A arg)
    {
      /* Code For EDurativeEventVerb Goes Here */

      p.verbpattern_.accept(new VerbPatternVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EInstantEventVerb p, A arg)
    {
      /* Code For EInstantEventVerb Goes Here */

      p.verbpattern_.accept(new VerbPatternVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EEventVerb p, A arg)
    {
      /* Code For EEventVerb Goes Here */

      p.vp_.accept(new VPVisitor<R,A>(), arg);
      p.uri_.accept(new URIVisitor<R,A>(), arg);
      for (OntologyFrameElement x : p.listontologyframeelement_) {
      }

      return null;
    }

  }
  public class AdjectivePatternVisitor<R,A> implements AdjectivePattern.Visitor<R,A>
  {
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIntersectiveAdjective p, A arg)
    {
      /* Code For EIntersectiveAdjective Goes Here */

      p.ap_.accept(new APVisitor<R,A>(), arg);
      p.uri_.accept(new URIVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIntersectiveObjectPropertyAdjective p, A arg)
    {
      /* Code For EIntersectiveObjectPropertyAdjective Goes Here */

      p.ap_.accept(new APVisitor<R,A>(), arg);
      p.uri_1.accept(new URIVisitor<R,A>(), arg);
      p.uri_2.accept(new URIVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIntersectiveDataPropertyAdjective p, A arg)
    {
      /* Code For EIntersectiveDataPropertyAdjective Goes Here */

      p.ap_.accept(new APVisitor<R,A>(), arg);
      p.uri_.accept(new URIVisitor<R,A>(), arg);
      //p.string_;

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ERelationalAdjective p, A arg)
    {
      /* Code For ERelationalAdjective Goes Here */

      p.ap_.accept(new APVisitor<R,A>(), arg);
      p.uri_.accept(new URIVisitor<R,A>(), arg);
      p.arg_.accept(new ArgVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EScalarAdjective p, A arg)
    {
      /* Code For EScalarAdjective Goes Here */

      p.ap_.accept(new APVisitor<R,A>(), arg);
      for (ScalarMembership x : p.listscalarmembership_) {
      }

      return null;
    }

  }
  public class ArgVisitor<R,A> implements Arg.Visitor<R,A>
  {
    public R visit(net.lemonmodel.patterns.parser.Absyn.EOptionalArg p, A arg)
    {
      /* Code For EOptionalArg Goes Here */

      p.arg_.accept(new ArgVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ERestrictedArg p, A arg)
    {
      /* Code For ERestrictedArg Goes Here */

      p.arg_.accept(new ArgVisitor<R,A>(), arg);
      p.uri_.accept(new URIVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESubject p, A arg)
    {
      /* Code For ESubject Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDirectObject p, A arg)
    {
      /* Code For EDirectObject Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIndirectObject p, A arg)
    {
      /* Code For EIndirectObject Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECopulativeArg p, A arg)
    {
      /* Code For ECopulativeArg Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECopulativeSubject p, A arg)
    {
      /* Code For ECopulativeSubject Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPrepositionalObject p, A arg)
    {
      /* Code For EPrepositionalObject Goes Here */

      //p.string_;

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPostpositionalObject p, A arg)
    {
      /* Code For EPostpositionalObject Goes Here */

      //p.string_;

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPossessiveAdjunct p, A arg)
    {
      /* Code For EPossessiveAdjunct Goes Here */


      return null;
    }

  }
  public class OntologyFrameElementVisitor<R,A> implements OntologyFrameElement.Visitor<R,A>
  {
    public R visit(net.lemonmodel.patterns.parser.Absyn.EURIAsSynArg p, A arg)
    {
      /* Code For EURIAsSynArg Goes Here */

      p.uri_.accept(new URIVisitor<R,A>(), arg);
      p.arg_.accept(new ArgVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EArgAsOFE p, A arg)
    {
      /* Code For EArgAsOFE Goes Here */

      p.arg_.accept(new ArgVisitor<R,A>(), arg);

      return null;
    }

  }
  public class PNPVisitor<R,A> implements PNP.Visitor<R,A>
  {
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPNPSimple p, A arg)
    {
      /* Code For EPNPSimple Goes Here */

      //p.string_;

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPNPComplex p, A arg)
    {
      /* Code For EPNPComplex Goes Here */

      for (POSTaggedWord x : p.listpostaggedword_) {
      }

      return null;
    }

  }
  public class NPVisitor<R,A> implements NP.Visitor<R,A>
  {
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENPSimple p, A arg)
    {
      /* Code For ENPSimple Goes Here */

      //p.string_;

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENPComplex p, A arg)
    {
      /* Code For ENPComplex Goes Here */

      for (POSTaggedWord x : p.listpostaggedword_) {
      }

      return null;
    }

  }
  public class VPVisitor<R,A> implements VP.Visitor<R,A>
  {
    public R visit(net.lemonmodel.patterns.parser.Absyn.EVPSimple p, A arg)
    {
      /* Code For EVPSimple Goes Here */

      //p.string_;

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EVPComplex p, A arg)
    {
      /* Code For EVPComplex Goes Here */

      for (POSTaggedWord x : p.listpostaggedword_) {
      }

      return null;
    }

  }
  public class APVisitor<R,A> implements AP.Visitor<R,A>
  {
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAPSimple p, A arg)
    {
      /* Code For EAPSimple Goes Here */

      //p.string_;

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAPComplex p, A arg)
    {
      /* Code For EAPComplex Goes Here */

      for (POSTaggedWord x : p.listpostaggedword_) {
      }

      return null;
    }

  }
  public class POSTaggedWordVisitor<R,A> implements POSTaggedWord.Visitor<R,A>
  {
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPOSTaggedHeadWord p, A arg)
    {
      /* Code For EPOSTaggedHeadWord Goes Here */

      //p.string_;
      p.postag_.accept(new POSTagVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPOSTaggedWord p, A arg)
    {
      /* Code For EPOSTaggedWord Goes Here */

      //p.string_;
      p.postag_.accept(new POSTagVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ELemmaPOSTaggedHeadWord p, A arg)
    {
      /* Code For ELemmaPOSTaggedHeadWord Goes Here */

      //p.string_1;
      //p.string_2;
      p.postag_.accept(new POSTagVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ELemmaPOSTaggedWord p, A arg)
    {
      /* Code For ELemmaPOSTaggedWord Goes Here */

      //p.string_1;
      //p.string_2;
      p.postag_.accept(new POSTagVisitor<R,A>(), arg);

      return null;
    }

  }
  public class ScalarMembershipVisitor<R,A> implements ScalarMembership.Visitor<R,A>
  {
    public R visit(net.lemonmodel.patterns.parser.Absyn.CovariantScalarMembership p, A arg)
    {
      /* Code For CovariantScalarMembership Goes Here */

      p.uri_.accept(new URIVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ContravariantScalarMembership p, A arg)
    {
      /* Code For ContravariantScalarMembership Goes Here */

      p.uri_.accept(new URIVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.CentralScalarMembership p, A arg)
    {
      /* Code For CentralScalarMembership Goes Here */

      p.uri_.accept(new URIVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.GreaterThanScalarMembership p, A arg)
    {
      /* Code For GreaterThanScalarMembership Goes Here */

      p.uri_1.accept(new URIVisitor<R,A>(), arg);
      //p.double_;
      p.uri_2.accept(new URIVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.LessThanScalarMembership p, A arg)
    {
      /* Code For LessThanScalarMembership Goes Here */

      p.uri_1.accept(new URIVisitor<R,A>(), arg);
      //p.double_;
      p.uri_2.accept(new URIVisitor<R,A>(), arg);

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.BoundedScalarMembership p, A arg)
    {
      /* Code For BoundedScalarMembership Goes Here */

      //p.double_1;
      p.uri_1.accept(new URIVisitor<R,A>(), arg);
      //p.double_2;
      p.uri_2.accept(new URIVisitor<R,A>(), arg);

      return null;
    }

  }
  public class CategoryVisitor<R,A> implements Category.Visitor<R,A>
  {
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESingular p, A arg)
    {
      /* Code For ESingular Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDual p, A arg)
    {
      /* Code For EDual Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPlural p, A arg)
    {
      /* Code For EPlural Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAbessive p, A arg)
    {
      /* Code For EAbessive Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAblative p, A arg)
    {
      /* Code For EAblative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAbsolutive p, A arg)
    {
      /* Code For EAbsolutive Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAccusative p, A arg)
    {
      /* Code For EAccusative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAdessive p, A arg)
    {
      /* Code For EAdessive Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAditive p, A arg)
    {
      /* Code For EAditive Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAllative p, A arg)
    {
      /* Code For EAllative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EBenefactive p, A arg)
    {
      /* Code For EBenefactive Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECausative p, A arg)
    {
      /* Code For ECausative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EComitative p, A arg)
    {
      /* Code For EComitative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDative p, A arg)
    {
      /* Code For EDative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDelative p, A arg)
    {
      /* Code For EDelative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EElative p, A arg)
    {
      /* Code For EElative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EEquative p, A arg)
    {
      /* Code For EEquative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EErgative p, A arg)
    {
      /* Code For EErgative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EEssive p, A arg)
    {
      /* Code For EEssive Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EGenitive p, A arg)
    {
      /* Code For EGenitive Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIllative p, A arg)
    {
      /* Code For EIllative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EInessive p, A arg)
    {
      /* Code For EInessive Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EInstrumental p, A arg)
    {
      /* Code For EInstrumental Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ELative p, A arg)
    {
      /* Code For ELative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ELocative p, A arg)
    {
      /* Code For ELocative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENominative p, A arg)
    {
      /* Code For ENominative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EOblique p, A arg)
    {
      /* Code For EOblique Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPartitive p, A arg)
    {
      /* Code For EPartitive Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EProlative p, A arg)
    {
      /* Code For EProlative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESociative p, A arg)
    {
      /* Code For ESociative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESublative p, A arg)
    {
      /* Code For ESublative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESuperessive p, A arg)
    {
      /* Code For ESuperessive Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ETerminative p, A arg)
    {
      /* Code For ETerminative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ETranslative p, A arg)
    {
      /* Code For ETranslative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EVocative p, A arg)
    {
      /* Code For EVocative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EComparative p, A arg)
    {
      /* Code For EComparative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESuperlative p, A arg)
    {
      /* Code For ESuperlative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPresent p, A arg)
    {
      /* Code For EPresent Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPast p, A arg)
    {
      /* Code For EPast Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EFuture p, A arg)
    {
      /* Code For EFuture Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EFirstPerson p, A arg)
    {
      /* Code For EFirstPerson Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESecondPerson p, A arg)
    {
      /* Code For ESecondPerson Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EThirdPerson p, A arg)
    {
      /* Code For EThirdPerson Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EImperfect p, A arg)
    {
      /* Code For EImperfect Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EImperative p, A arg)
    {
      /* Code For EImperative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIndicative p, A arg)
    {
      /* Code For EIndicative Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESubjunctive p, A arg)
    {
      /* Code For ESubjunctive Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConditional p, A arg)
    {
      /* Code For EConditional Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EGerundive p, A arg)
    {
      /* Code For EGerundive Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EInfinitive p, A arg)
    {
      /* Code For EInfinitive Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EParticiple p, A arg)
    {
      /* Code For EParticiple Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAnyCat p, A arg)
    {
      /* Code For EAnyCat Goes Here */

      p.uri_1.accept(new URIVisitor<R,A>(), arg);
      p.uri_2.accept(new URIVisitor<R,A>(), arg);

      return null;
    }

  }
  public class POSTagVisitor<R,A> implements POSTag.Visitor<R,A>
  {
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAdjectivePOS p, A arg)
    {
      /* Code For EAdjectivePOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAdpositionPOS p, A arg)
    {
      /* Code For EAdpositionPOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAdverbPOS p, A arg)
    {
      /* Code For EAdverbPOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EArticlePOS p, A arg)
    {
      /* Code For EArticlePOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EBulletPOS p, A arg)
    {
      /* Code For EBulletPOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECircumpositionPOS p, A arg)
    {
      /* Code For ECircumpositionPOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EColonPOSPOS p, A arg)
    {
      /* Code For EColonPOSPOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECommaPOS p, A arg)
    {
      /* Code For ECommaPOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConjunctionPOS p, A arg)
    {
      /* Code For EConjunctionPOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECopulaPOS p, A arg)
    {
      /* Code For ECopulaPOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDeterminerPOS p, A arg)
    {
      /* Code For EDeterminerPOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EInterjectionPOS p, A arg)
    {
      /* Code For EInterjectionPOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENounPOS p, A arg)
    {
      /* Code For ENounPOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENumeralPOS p, A arg)
    {
      /* Code For ENumeralPOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EParticlePOS p, A arg)
    {
      /* Code For EParticlePOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPointPOS p, A arg)
    {
      /* Code For EPointPOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPostpositionPOS p, A arg)
    {
      /* Code For EPostpositionPOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPrepositionPOS p, A arg)
    {
      /* Code For EPrepositionPOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPronounPOS p, A arg)
    {
      /* Code For EPronounPOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPunctuationPOS p, A arg)
    {
      /* Code For EPunctuationPOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESemiColonPOS p, A arg)
    {
      /* Code For ESemiColonPOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESlashPOS p, A arg)
    {
      /* Code For ESlashPOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EVerbPOS p, A arg)
    {
      /* Code For EVerbPOS Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAnyPOS p, A arg)
    {
      /* Code For EAnyPOS Goes Here */

      //p.string_;

      return null;
    }

  }
  public class GenderVisitor<R,A> implements Gender.Visitor<R,A>
  {
    public R visit(net.lemonmodel.patterns.parser.Absyn.EMascGender p, A arg)
    {
      /* Code For EMascGender Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EFemGender p, A arg)
    {
      /* Code For EFemGender Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENeutGender p, A arg)
    {
      /* Code For ENeutGender Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECommonGender p, A arg)
    {
      /* Code For ECommonGender Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EOtherGender p, A arg)
    {
      /* Code For EOtherGender Goes Here */


      return null;
    }

  }
  public class RegisterVisitor<R,A> implements Register.Visitor<R,A>
  {
    public R visit(net.lemonmodel.patterns.parser.Absyn.EBenchLevelRegister p, A arg)
    {
      /* Code For EBenchLevelRegister Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDialectRegister p, A arg)
    {
      /* Code For EDialectRegister Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EFacetiousRegister p, A arg)
    {
      /* Code For EFacetiousRegister Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EFormalRegister p, A arg)
    {
      /* Code For EFormalRegister Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EInHouseRegister p, A arg)
    {
      /* Code For EInHouseRegister Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIronicRegister p, A arg)
    {
      /* Code For EIronicRegister Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENeutralRegister p, A arg)
    {
      /* Code For ENeutralRegister Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESlangRegister p, A arg)
    {
      /* Code For ESlangRegister Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ETabooRegister p, A arg)
    {
      /* Code For ETabooRegister Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ETechnicalRegister p, A arg)
    {
      /* Code For ETechnicalRegister Goes Here */


      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EVulgarRegister p, A arg)
    {
      /* Code For EVulgarRegister Goes Here */


      return null;
    }

  }
  public class URIVisitor<R,A> implements URI.Visitor<R,A>
  {
    public R visit(net.lemonmodel.patterns.parser.Absyn.EQName p, A arg)
    {
      /* Code For EQName Goes Here */

      //p.ident_1;
      //p.ident_2;

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EQName2 p, A arg)
    {
      /* Code For EQName2 Goes Here */

      //p.ident_;

      return null;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EURI p, A arg)
    {
      /* Code For EURI Goes Here */

      //p.fulluri_;

      return null;
    }

  }
}