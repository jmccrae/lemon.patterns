package net.lemonmodel.patterns.parser.Absyn; // Java Package generated by the BNF Converter.

public class EVPComplex extends VP {
  public final ListPOSTaggedWord listpostaggedword_;

  public EVPComplex(ListPOSTaggedWord p1) { listpostaggedword_ = p1; }

  public <R,A> R accept(net.lemonmodel.patterns.parser.Absyn.VP.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof net.lemonmodel.patterns.parser.Absyn.EVPComplex) {
      net.lemonmodel.patterns.parser.Absyn.EVPComplex x = (net.lemonmodel.patterns.parser.Absyn.EVPComplex)o;
      return this.listpostaggedword_.equals(x.listpostaggedword_);
    }
    return false;
  }

  public int hashCode() {
    return this.listpostaggedword_.hashCode();
  }


}
