package net.lemonmodel.patterns.parser.Absyn; // Java Package generated by the BNF Converter.

public class EBulletPOS extends POSTag {

  public EBulletPOS() { }

  public <R,A> R accept(net.lemonmodel.patterns.parser.Absyn.POSTag.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof net.lemonmodel.patterns.parser.Absyn.EBulletPOS) {
      return true;
    }
    return false;
  }

  public int hashCode() {
    return 37;
  }


}
