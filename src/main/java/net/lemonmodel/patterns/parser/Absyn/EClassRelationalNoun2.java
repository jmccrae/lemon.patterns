package net.lemonmodel.patterns.parser.Absyn; // Java Package generated by the BNF Converter.

public class EClassRelationalNoun2 extends NounPattern {
  public final NP np_;
  public final URI uri_1, uri_2;
  public final Arg arg_;

  public EClassRelationalNoun2(NP p1, URI p2, URI p3, Arg p4) { np_ = p1; uri_1 = p2; uri_2 = p3; arg_ = p4; }

  public <R,A> R accept(net.lemonmodel.patterns.parser.Absyn.NounPattern.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun2) {
      net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun2 x = (net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun2)o;
      return this.np_.equals(x.np_) && this.uri_1.equals(x.uri_1) && this.uri_2.equals(x.uri_2) && this.arg_.equals(x.arg_);
    }
    return false;
  }

  public int hashCode() {
    return 37*(37*(37*(this.np_.hashCode())+this.uri_1.hashCode())+this.uri_2.hashCode())+this.arg_.hashCode();
  }


}
