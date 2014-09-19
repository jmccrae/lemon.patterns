package net.lemonmodel.patterns.parser;

import net.lemonmodel.patterns.parser.Absyn.*;

/** BNFC-Generated All Visitor */
public interface AllVisitor<R,A> extends
  net.lemonmodel.patterns.parser.Absyn.Statements.Visitor<R,A>,
  net.lemonmodel.patterns.parser.Absyn.Statement.Visitor<R,A>,
  net.lemonmodel.patterns.parser.Absyn.PatternType.Visitor<R,A>,
  net.lemonmodel.patterns.parser.Absyn.Pattern.Visitor<R,A>,
  net.lemonmodel.patterns.parser.Absyn.NounPattern.Visitor<R,A>,
  net.lemonmodel.patterns.parser.Absyn.VerbPattern.Visitor<R,A>,
  net.lemonmodel.patterns.parser.Absyn.AdjectivePattern.Visitor<R,A>,
  net.lemonmodel.patterns.parser.Absyn.Arg.Visitor<R,A>,
  net.lemonmodel.patterns.parser.Absyn.OntologyFrameElement.Visitor<R,A>,
  net.lemonmodel.patterns.parser.Absyn.PNP.Visitor<R,A>,
  net.lemonmodel.patterns.parser.Absyn.NP.Visitor<R,A>,
  net.lemonmodel.patterns.parser.Absyn.VP.Visitor<R,A>,
  net.lemonmodel.patterns.parser.Absyn.AP.Visitor<R,A>,
  net.lemonmodel.patterns.parser.Absyn.POSTaggedWord.Visitor<R,A>,
  net.lemonmodel.patterns.parser.Absyn.ScalarMembership.Visitor<R,A>,
  net.lemonmodel.patterns.parser.Absyn.Category.Visitor<R,A>,
  net.lemonmodel.patterns.parser.Absyn.POSTag.Visitor<R,A>,
  net.lemonmodel.patterns.parser.Absyn.Gender.Visitor<R,A>,
  net.lemonmodel.patterns.parser.Absyn.Register.Visitor<R,A>,
  net.lemonmodel.patterns.parser.Absyn.URI.Visitor<R,A>
{}
