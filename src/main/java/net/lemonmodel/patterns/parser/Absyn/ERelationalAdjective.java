package net.lemonmodel.patterns.parser.Absyn; // Java Package generated by the BNF Converter.

public class ERelationalAdjective extends AdjectivePattern {
  public final AP ap_;
  public final URI uri_;
  public final Arg arg_;

  public ERelationalAdjective(AP p1, URI p2, Arg p3) { ap_ = p1; uri_ = p2; arg_ = p3; }

  public <R,A> R accept(net.lemonmodel.patterns.parser.Absyn.AdjectivePattern.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof net.lemonmodel.patterns.parser.Absyn.ERelationalAdjective) {
      net.lemonmodel.patterns.parser.Absyn.ERelationalAdjective x = (net.lemonmodel.patterns.parser.Absyn.ERelationalAdjective)o;
      return this.ap_.equals(x.ap_) && this.uri_.equals(x.uri_) && this.arg_.equals(x.arg_);
    }
    return false;
  }

  public int hashCode() {
    return 37*(37*(this.ap_.hashCode())+this.uri_.hashCode())+this.arg_.hashCode();
  }


}
