package net.lemonmodel.patterns.parser.Absyn; // Java Package generated by the BNF Converter.

public abstract class PatternType implements java.io.Serializable {
  public abstract <R,A> R accept(PatternType.Visitor<R,A> v, A arg);
  public interface Visitor <R,A> {
    public R visit(net.lemonmodel.patterns.parser.Absyn.EPatternWithRegister p, A arg);
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECorePattern p, A arg);

  }

}