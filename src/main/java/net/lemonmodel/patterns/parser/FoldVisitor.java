package net.lemonmodel.patterns.parser;

import net.lemonmodel.patterns.parser.Absyn.*;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/** BNFC-Generated Fold Visitor */
public abstract class FoldVisitor<R,A> implements AllVisitor<R,A> {
    public abstract R leaf(A arg);
    public abstract R combine(R x, R y, A arg);

/* Statements */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EStatments p, A arg) {
      R r = leaf(arg);
      for (Statement x : p.liststatement_) {
        r = combine(x.accept(this,arg), r, arg);
      }
      return r;
    }

/* Statement */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPrefix p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ELexicon p, A arg) {
      R r = leaf(arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      for (PatternType x : p.listpatterntype_) {
        r = combine(x.accept(this,arg), r, arg);
      }
      return r;
    }

/* PatternType */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPatternWithRegister p, A arg) {
      R r = leaf(arg);
      r = combine(p.pattern_.accept(this, arg), r, arg);
      r = combine(p.register_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECorePattern p, A arg) {
      R r = leaf(arg);
      r = combine(p.pattern_.accept(this, arg), r, arg);
      return r;
    }

/* Pattern */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPatternWithForm p, A arg) {
      R r = leaf(arg);
      r = combine(p.pattern_.accept(this, arg), r, arg);
      for (Category x : p.listcategory_) {
        r = combine(x.accept(this,arg), r, arg);
      }
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENoun p, A arg) {
      R r = leaf(arg);
      r = combine(p.nounpattern_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENounWithGender p, A arg) {
      R r = leaf(arg);
      r = combine(p.nounpattern_.accept(this, arg), r, arg);
      r = combine(p.gender_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EVerb p, A arg) {
      R r = leaf(arg);
      r = combine(p.verbpattern_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAdjective p, A arg) {
      R r = leaf(arg);
      r = combine(p.adjectivepattern_.accept(this, arg), r, arg);
      return r;
    }

/* NounPattern */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EName p, A arg) {
      R r = leaf(arg);
      r = combine(p.pnp_.accept(this, arg), r, arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EClassNoun p, A arg) {
      R r = leaf(arg);
      r = combine(p.np_.accept(this, arg), r, arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EObjectPropertyNoun p, A arg) {
      R r = leaf(arg);
      r = combine(p.np_.accept(this, arg), r, arg);
      r = combine(p.uri_1.accept(this, arg), r, arg);
      r = combine(p.uri_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDataPropertyNoun p, A arg) {
      R r = leaf(arg);
      r = combine(p.np_.accept(this, arg), r, arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ERelationalNoun1 p, A arg) {
      R r = leaf(arg);
      r = combine(p.np_.accept(this, arg), r, arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      r = combine(p.arg_1.accept(this, arg), r, arg);
      r = combine(p.arg_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ERelationalNoun2 p, A arg) {
      R r = leaf(arg);
      r = combine(p.np_.accept(this, arg), r, arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      r = combine(p.arg_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ERelationalMultivalentNoun p, A arg) {
      R r = leaf(arg);
      r = combine(p.np_.accept(this, arg), r, arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      for (OntologyFrameElement x : p.listontologyframeelement_) {
        r = combine(x.accept(this,arg), r, arg);
      }
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun1 p, A arg) {
      R r = leaf(arg);
      r = combine(p.np_.accept(this, arg), r, arg);
      r = combine(p.uri_1.accept(this, arg), r, arg);
      r = combine(p.uri_2.accept(this, arg), r, arg);
      r = combine(p.arg_1.accept(this, arg), r, arg);
      r = combine(p.arg_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun2 p, A arg) {
      R r = leaf(arg);
      r = combine(p.np_.accept(this, arg), r, arg);
      r = combine(p.uri_1.accept(this, arg), r, arg);
      r = combine(p.uri_2.accept(this, arg), r, arg);
      r = combine(p.arg_.accept(this, arg), r, arg);
      return r;
    }

/* VerbPattern */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EStateVerb1 p, A arg) {
      R r = leaf(arg);
      r = combine(p.vp_.accept(this, arg), r, arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EStateVerb2 p, A arg) {
      R r = leaf(arg);
      r = combine(p.vp_.accept(this, arg), r, arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      r = combine(p.arg_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EStateVerb3 p, A arg) {
      R r = leaf(arg);
      r = combine(p.vp_.accept(this, arg), r, arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      r = combine(p.arg_1.accept(this, arg), r, arg);
      r = combine(p.arg_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ETelicEventVerb p, A arg) {
      R r = leaf(arg);
      r = combine(p.verbpattern_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENontelicEventVerb p, A arg) {
      R r = leaf(arg);
      r = combine(p.verbpattern_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDunnotelicEventVerb p, A arg) {
      R r = leaf(arg);
      r = combine(p.verbpattern_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb1 p, A arg) {
      R r = leaf(arg);
      r = combine(p.vp_.accept(this, arg), r, arg);
      r = combine(p.uri_1.accept(this, arg), r, arg);
      r = combine(p.ontologyframeelement_1.accept(this, arg), r, arg);
      r = combine(p.ontologyframeelement_2.accept(this, arg), r, arg);
      r = combine(p.uri_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb2 p, A arg) {
      R r = leaf(arg);
      r = combine(p.vp_.accept(this, arg), r, arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      r = combine(p.ontologyframeelement_1.accept(this, arg), r, arg);
      r = combine(p.ontologyframeelement_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb3 p, A arg) {
      R r = leaf(arg);
      r = combine(p.vp_.accept(this, arg), r, arg);
      r = combine(p.uri_1.accept(this, arg), r, arg);
      r = combine(p.ontologyframeelement_.accept(this, arg), r, arg);
      r = combine(p.uri_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb4 p, A arg) {
      R r = leaf(arg);
      r = combine(p.vp_.accept(this, arg), r, arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      r = combine(p.ontologyframeelement_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb5 p, A arg) {
      R r = leaf(arg);
      r = combine(p.vp_.accept(this, arg), r, arg);
      r = combine(p.uri_1.accept(this, arg), r, arg);
      r = combine(p.ontologyframeelement_.accept(this, arg), r, arg);
      r = combine(p.uri_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb6 p, A arg) {
      R r = leaf(arg);
      r = combine(p.vp_.accept(this, arg), r, arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      r = combine(p.ontologyframeelement_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb7 p, A arg) {
      R r = leaf(arg);
      r = combine(p.vp_.accept(this, arg), r, arg);
      r = combine(p.uri_1.accept(this, arg), r, arg);
      r = combine(p.uri_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb8 p, A arg) {
      R r = leaf(arg);
      r = combine(p.vp_.accept(this, arg), r, arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDurativeEventVerb p, A arg) {
      R r = leaf(arg);
      r = combine(p.verbpattern_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EInstantEventVerb p, A arg) {
      R r = leaf(arg);
      r = combine(p.verbpattern_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EEventVerb p, A arg) {
      R r = leaf(arg);
      r = combine(p.vp_.accept(this, arg), r, arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      for (OntologyFrameElement x : p.listontologyframeelement_) {
        r = combine(x.accept(this,arg), r, arg);
      }
      return r;
    }

/* AdjectivePattern */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIntersectiveAdjective p, A arg) {
      R r = leaf(arg);
      r = combine(p.ap_.accept(this, arg), r, arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIntersectiveObjectPropertyAdjective p, A arg) {
      R r = leaf(arg);
      r = combine(p.ap_.accept(this, arg), r, arg);
      r = combine(p.uri_1.accept(this, arg), r, arg);
      r = combine(p.uri_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIntersectiveDataPropertyAdjective p, A arg) {
      R r = leaf(arg);
      r = combine(p.ap_.accept(this, arg), r, arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ERelationalAdjective p, A arg) {
      R r = leaf(arg);
      r = combine(p.ap_.accept(this, arg), r, arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      r = combine(p.arg_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EScalarAdjective p, A arg) {
      R r = leaf(arg);
      r = combine(p.ap_.accept(this, arg), r, arg);
      for (ScalarMembership x : p.listscalarmembership_) {
        r = combine(x.accept(this,arg), r, arg);
      }
      return r;
    }

/* Arg */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EOptionalArg p, A arg) {
      R r = leaf(arg);
      r = combine(p.arg_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ERestrictedArg p, A arg) {
      R r = leaf(arg);
      r = combine(p.arg_.accept(this, arg), r, arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESubject p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDirectObject p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIndirectObject p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECopulativeArg p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECopulativeSubject p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPrepositionalObject p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPostpositionalObject p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPossessiveAdjunct p, A arg) {
      R r = leaf(arg);
      return r;
    }

/* OntologyFrameElement */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EURIAsSynArg p, A arg) {
      R r = leaf(arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      r = combine(p.arg_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EArgAsOFE p, A arg) {
      R r = leaf(arg);
      r = combine(p.arg_.accept(this, arg), r, arg);
      return r;
    }

/* PNP */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPNPSimple p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPNPComplex p, A arg) {
      R r = leaf(arg);
      for (POSTaggedWord x : p.listpostaggedword_) {
        r = combine(x.accept(this,arg), r, arg);
      }
      return r;
    }

/* NP */
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENPSimple p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENPComplex p, A arg) {
      R r = leaf(arg);
      for (POSTaggedWord x : p.listpostaggedword_) {
        r = combine(x.accept(this,arg), r, arg);
      }
      return r;
    }

/* VP */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EVPSimple p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EVPComplex p, A arg) {
      R r = leaf(arg);
      for (POSTaggedWord x : p.listpostaggedword_) {
        r = combine(x.accept(this,arg), r, arg);
      }
      return r;
    }

/* AP */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAPSimple p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAPComplex p, A arg) {
      R r = leaf(arg);
      for (POSTaggedWord x : p.listpostaggedword_) {
        r = combine(x.accept(this,arg), r, arg);
      }
      return r;
    }

/* POSTaggedWord */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPOSTaggedHeadWord p, A arg) {
      R r = leaf(arg);
      r = combine(p.postag_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPOSTaggedWord p, A arg) {
      R r = leaf(arg);
      r = combine(p.postag_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ELemmaPOSTaggedHeadWord p, A arg) {
      R r = leaf(arg);
      r = combine(p.postag_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ELemmaPOSTaggedWord p, A arg) {
      R r = leaf(arg);
      r = combine(p.postag_.accept(this, arg), r, arg);
      return r;
    }

/* ScalarMembership */
    public R visit(net.lemonmodel.patterns.parser.Absyn.CovariantScalarMembership p, A arg) {
      R r = leaf(arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ContravariantScalarMembership p, A arg) {
      R r = leaf(arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.CentralScalarMembership p, A arg) {
      R r = leaf(arg);
      r = combine(p.uri_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.GreaterThanScalarMembership p, A arg) {
      R r = leaf(arg);
      r = combine(p.uri_1.accept(this, arg), r, arg);
      r = combine(p.uri_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.LessThanScalarMembership p, A arg) {
      R r = leaf(arg);
      r = combine(p.uri_1.accept(this, arg), r, arg);
      r = combine(p.uri_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.BoundedScalarMembership p, A arg) {
      R r = leaf(arg);
      r = combine(p.uri_1.accept(this, arg), r, arg);
      r = combine(p.uri_2.accept(this, arg), r, arg);
      return r;
    }

/* Category */
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESingular p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDual p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPlural p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAbessive p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAblative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAbsolutive p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAccusative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAdessive p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAditive p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAllative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EBenefactive p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECausative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EComitative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDelative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EElative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EEquative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EErgative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EEssive p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EGenitive p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIllative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EInessive p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EInstrumental p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ELative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ELocative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENominative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EOblique p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPartitive p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EProlative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESociative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESublative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESuperessive p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ETerminative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ETranslative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EVocative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EComparative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESuperlative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPresent p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPast p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EFuture p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EFirstPerson p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESecondPerson p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EThirdPerson p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EImperfect p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EImperative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIndicative p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESubjunctive p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConditional p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EGerundive p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EInfinitive p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EParticiple p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAnyCat p, A arg) {
      R r = leaf(arg);
      r = combine(p.uri_1.accept(this, arg), r, arg);
      r = combine(p.uri_2.accept(this, arg), r, arg);
      return r;
    }

/* POSTag */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAdjectivePOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAdpositionPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAdverbPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EArticlePOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EBulletPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECircumpositionPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EColonPOSPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECommaPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EConjunctionPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECopulaPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDeterminerPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EInterjectionPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENounPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENumeralPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EParticlePOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPointPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPostpositionPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPrepositionPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPronounPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPunctuationPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESemiColonPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESlashPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EVerbPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EAnyPOS p, A arg) {
      R r = leaf(arg);
      return r;
    }

/* Gender */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EMascGender p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EFemGender p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENeutGender p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECommonGender p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EOtherGender p, A arg) {
      R r = leaf(arg);
      return r;
    }

/* Register */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EBenchLevelRegister p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EDialectRegister p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EFacetiousRegister p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EFormalRegister p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EInHouseRegister p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIronicRegister p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENeutralRegister p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ESlangRegister p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ETabooRegister p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.ETechnicalRegister p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EVulgarRegister p, A arg) {
      R r = leaf(arg);
      return r;
    }

/* URI */
    public R visit(net.lemonmodel.patterns.parser.Absyn.EQName p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EQName2 p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(net.lemonmodel.patterns.parser.Absyn.EURI p, A arg) {
      R r = leaf(arg);
      return r;
    }


}
