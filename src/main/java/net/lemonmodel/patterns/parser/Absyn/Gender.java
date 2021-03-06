package net.lemonmodel.patterns.parser.Absyn; // Java Package generated by the BNF Converter.

public abstract class Gender implements java.io.Serializable {
  public abstract <R,A> R accept(Gender.Visitor<R,A> v, A arg);
  public interface Visitor <R,A> {
    public R visit(net.lemonmodel.patterns.parser.Absyn.EMascGender p, A arg);
    public R visit(net.lemonmodel.patterns.parser.Absyn.EFemGender p, A arg);
    public R visit(net.lemonmodel.patterns.parser.Absyn.ENeutGender p, A arg);
    public R visit(net.lemonmodel.patterns.parser.Absyn.ECommonGender p, A arg);
    public R visit(net.lemonmodel.patterns.parser.Absyn.EOtherGender p, A arg);

  }

}
