package net.lemonmodel.patterns.parser.Absyn; // Java Package generated by the BNF Converter.

public class EEventVerb extends VerbPattern {
  public final VP vp_;
  public final URI uri_;
  public final ListOntologyFrameElement listontologyframeelement_;

  public EEventVerb(VP p1, URI p2, ListOntologyFrameElement p3) { vp_ = p1; uri_ = p2; listontologyframeelement_ = p3; }

  public <R,A> R accept(net.lemonmodel.patterns.parser.Absyn.VerbPattern.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof net.lemonmodel.patterns.parser.Absyn.EEventVerb) {
      net.lemonmodel.patterns.parser.Absyn.EEventVerb x = (net.lemonmodel.patterns.parser.Absyn.EEventVerb)o;
      return this.vp_.equals(x.vp_) && this.uri_.equals(x.uri_) && this.listontologyframeelement_.equals(x.listontologyframeelement_);
    }
    return false;
  }

  public int hashCode() {
    return 37*(37*(this.vp_.hashCode())+this.uri_.hashCode())+this.listontologyframeelement_.hashCode();
  }


}
