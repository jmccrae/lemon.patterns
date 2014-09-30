package net.lemonmodel.patterns.parser.Absyn; // Java Package generated by the BNF Converter.

public abstract class AdjectivePattern implements java.io.Serializable {
  public abstract <R,A> R accept(AdjectivePattern.Visitor<R,A> v, A arg);
  public interface Visitor <R,A> {
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIntersectiveAdjective p, A arg);
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIntersectiveObjectPropertyAdjective p, A arg);
    public R visit(net.lemonmodel.patterns.parser.Absyn.EIntersectiveDataPropertyAdjective p, A arg);
    public R visit(net.lemonmodel.patterns.parser.Absyn.ERelationalAdjective p, A arg);
    public R visit(net.lemonmodel.patterns.parser.Absyn.EScalarAdjective p, A arg);

  }

}
