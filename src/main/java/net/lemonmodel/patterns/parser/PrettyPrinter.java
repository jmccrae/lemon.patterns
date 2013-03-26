package net.lemonmodel.patterns.parser;
import net.lemonmodel.patterns.parser.Absyn.*;

public class PrettyPrinter
{
  //For certain applications increasing the initial size of the buffer may improve performance.
  private static final int INITIAL_BUFFER_SIZE = 128;
  //You may wish to change the parentheses used in precedence.
  private static final String _L_PAREN = new String("(");
  private static final String _R_PAREN = new String(")");
  //You may wish to change render
  private static void render(String s)
  {
    if (s.equals("{"))
    {
       buf_.append("\n");
       indent();
       buf_.append(s);
       _n_ = _n_ + 2;
       buf_.append("\n");
       indent();
    }
    else if (s.equals("(") || s.equals("["))
       buf_.append(s);
    else if (s.equals(")") || s.equals("]"))
    {
       backup();
       buf_.append(s);
       buf_.append(" ");
    }
    else if (s.equals("}"))
    {
       _n_ = _n_ - 2;
       backup();
       backup();
       buf_.append(s);
       buf_.append("\n");
       indent();
    }
    else if (s.equals(","))
    {
       backup();
       buf_.append(s);
       buf_.append(" ");
    }
    else if (s.equals(";"))
    {
       backup();
       buf_.append(s);
       buf_.append("\n");
       indent();
    }
    else if (s.equals("")) return;
    else
    {
       buf_.append(s);
       buf_.append(" ");
    }
  }


  //  print and show methods are defined for each category.
  public static String print(net.lemonmodel.patterns.parser.Absyn.Statements foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.Statements foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.Statement foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.Statement foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.ListStatement foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.ListStatement foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.Pattern foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.Pattern foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.NounPattern foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.NounPattern foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.VerbPattern foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.VerbPattern foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.AdjectivePattern foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.AdjectivePattern foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.ListPattern foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.ListPattern foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.Arg foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.Arg foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.OntologyFrameElement foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.OntologyFrameElement foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.ListOntologyFrameElement foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.ListOntologyFrameElement foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.PNP foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.PNP foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.NP foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.NP foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.VP foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.VP foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.AP foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.AP foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.POSTaggedWord foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.POSTaggedWord foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.ListPOSTaggedWord foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.ListPOSTaggedWord foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.ScalarMembership foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.ScalarMembership foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.ListScalarMembership foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.ListScalarMembership foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.Category foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.Category foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.ListCategory foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.ListCategory foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.POSTag foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.POSTag foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(net.lemonmodel.patterns.parser.Absyn.URI foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(net.lemonmodel.patterns.parser.Absyn.URI foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  /***   You shouldn't need to change anything beyond this point.   ***/

  private static void pp(net.lemonmodel.patterns.parser.Absyn.Statements foo, int _i_)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EStatments)
    {
       net.lemonmodel.patterns.parser.Absyn.EStatments _estatments = (net.lemonmodel.patterns.parser.Absyn.EStatments) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_estatments.liststatement_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.Statement foo, int _i_)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPrefix)
    {
       net.lemonmodel.patterns.parser.Absyn.EPrefix _eprefix = (net.lemonmodel.patterns.parser.Absyn.EPrefix) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("@prefix");
       pp(_eprefix.ident_, 0);
       render(":");
       pp(_eprefix.fulluri_, 0);
       render(".");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ELexicon)
    {
       net.lemonmodel.patterns.parser.Absyn.ELexicon _elexicon = (net.lemonmodel.patterns.parser.Absyn.ELexicon) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("Lexicon");
       render("(");
       pp(_elexicon.uri_, 0);
       render(",");
       printQuoted(_elexicon.string_);
       render(",");
       pp(_elexicon.listpattern_, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.ListStatement foo, int _i_)
  {
     for (java.util.Iterator<Statement> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), 0);
       if (it.hasNext()) {
         render("");
       } else {
         render("");
       }
     }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.Pattern foo, int _i_)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPatternWithForm)
    {
       net.lemonmodel.patterns.parser.Absyn.EPatternWithForm _epatternwithform = (net.lemonmodel.patterns.parser.Absyn.EPatternWithForm) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_epatternwithform.pattern_, 0);
       render("with");
       pp(_epatternwithform.listcategory_, 0);
       printQuoted(_epatternwithform.string_);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ENoun)
    {
       net.lemonmodel.patterns.parser.Absyn.ENoun _enoun = (net.lemonmodel.patterns.parser.Absyn.ENoun) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_enoun.nounpattern_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EVerb)
    {
       net.lemonmodel.patterns.parser.Absyn.EVerb _everb = (net.lemonmodel.patterns.parser.Absyn.EVerb) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_everb.verbpattern_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EAdjective)
    {
       net.lemonmodel.patterns.parser.Absyn.EAdjective _eadjective = (net.lemonmodel.patterns.parser.Absyn.EAdjective) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_eadjective.adjectivepattern_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.NounPattern foo, int _i_)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EName)
    {
       net.lemonmodel.patterns.parser.Absyn.EName _ename = (net.lemonmodel.patterns.parser.Absyn.EName) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("Name");
       render("(");
       pp(_ename.pnp_, 0);
       render(",");
       pp(_ename.uri_, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EClassNoun)
    {
       net.lemonmodel.patterns.parser.Absyn.EClassNoun _eclassnoun = (net.lemonmodel.patterns.parser.Absyn.EClassNoun) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("ClassNoun");
       render("(");
       pp(_eclassnoun.np_, 0);
       render(",");
       pp(_eclassnoun.uri_, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ERelationalNoun1)
    {
       net.lemonmodel.patterns.parser.Absyn.ERelationalNoun1 _erelationalnoun1 = (net.lemonmodel.patterns.parser.Absyn.ERelationalNoun1) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("RelationalNoun");
       render("(");
       pp(_erelationalnoun1.np_, 0);
       render(",");
       pp(_erelationalnoun1.uri_, 0);
       render(",");
       render("propSubj");
       render("=");
       pp(_erelationalnoun1.arg_1, 0);
       render(",");
       render("propObj");
       render("=");
       pp(_erelationalnoun1.arg_2, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ERelationalNoun2)
    {
       net.lemonmodel.patterns.parser.Absyn.ERelationalNoun2 _erelationalnoun2 = (net.lemonmodel.patterns.parser.Absyn.ERelationalNoun2) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("RelationalNoun");
       render("(");
       pp(_erelationalnoun2.np_, 0);
       render(",");
       pp(_erelationalnoun2.uri_, 0);
       render(",");
       render("propObj");
       render("=");
       pp(_erelationalnoun2.arg_, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ERelationalMultivalentNoun)
    {
       net.lemonmodel.patterns.parser.Absyn.ERelationalMultivalentNoun _erelationalmultivalentnoun = (net.lemonmodel.patterns.parser.Absyn.ERelationalMultivalentNoun) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("RelationalMultivalentNoun");
       render("(");
       pp(_erelationalmultivalentnoun.np_, 0);
       render(",");
       pp(_erelationalmultivalentnoun.uri_, 0);
       render(",");
       render("[");
       pp(_erelationalmultivalentnoun.listontologyframeelement_, 0);
       render("]");
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun1)
    {
       net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun1 _eclassrelationalnoun1 = (net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun1) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("ClassRelationalNoun");
       render("(");
       pp(_eclassrelationalnoun1.np_, 0);
       render(",");
       render("property");
       render("=");
       pp(_eclassrelationalnoun1.uri_1, 0);
       render(",");
       render("class");
       render("=");
       pp(_eclassrelationalnoun1.uri_2, 0);
       render(",");
       render("propSubj");
       render("=");
       pp(_eclassrelationalnoun1.arg_1, 0);
       render(",");
       render("propObj");
       render("=");
       pp(_eclassrelationalnoun1.arg_2, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun2)
    {
       net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun2 _eclassrelationalnoun2 = (net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun2) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("ClassRelationalNoun");
       render("(");
       pp(_eclassrelationalnoun2.np_, 0);
       render(",");
       render("property");
       render("=");
       pp(_eclassrelationalnoun2.uri_1, 0);
       render(",");
       render("class");
       render("=");
       pp(_eclassrelationalnoun2.uri_2, 0);
       render(",");
       render("propObj");
       render("=");
       pp(_eclassrelationalnoun2.arg_, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.VerbPattern foo, int _i_)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EStateVerb1)
    {
       net.lemonmodel.patterns.parser.Absyn.EStateVerb1 _estateverb1 = (net.lemonmodel.patterns.parser.Absyn.EStateVerb1) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("StateVerb");
       render("(");
       pp(_estateverb1.vp_, 0);
       render(",");
       pp(_estateverb1.uri_, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EStateVerb2)
    {
       net.lemonmodel.patterns.parser.Absyn.EStateVerb2 _estateverb2 = (net.lemonmodel.patterns.parser.Absyn.EStateVerb2) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("StateVerb");
       render("(");
       pp(_estateverb2.vp_, 0);
       render(",");
       pp(_estateverb2.uri_, 0);
       render(",");
       render("propObj");
       render("=");
       pp(_estateverb2.arg_, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EStateVerb3)
    {
       net.lemonmodel.patterns.parser.Absyn.EStateVerb3 _estateverb3 = (net.lemonmodel.patterns.parser.Absyn.EStateVerb3) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("StateVerb");
       render("(");
       pp(_estateverb3.vp_, 0);
       render(",");
       pp(_estateverb3.uri_, 0);
       render(",");
       render("propSubj");
       render("=");
       pp(_estateverb3.arg_1, 0);
       render(",");
       render("probObj");
       render("=");
       pp(_estateverb3.arg_2, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ETelicEventVerb)
    {
       net.lemonmodel.patterns.parser.Absyn.ETelicEventVerb _eteliceventverb = (net.lemonmodel.patterns.parser.Absyn.ETelicEventVerb) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("telic");
       pp(_eteliceventverb.verbpattern_, 2);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ENontelicEventVerb)
    {
       net.lemonmodel.patterns.parser.Absyn.ENontelicEventVerb _enonteliceventverb = (net.lemonmodel.patterns.parser.Absyn.ENontelicEventVerb) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("nontelic");
       pp(_enonteliceventverb.verbpattern_, 2);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EDunnotelicEventVerb)
    {
       net.lemonmodel.patterns.parser.Absyn.EDunnotelicEventVerb _edunnoteliceventverb = (net.lemonmodel.patterns.parser.Absyn.EDunnotelicEventVerb) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_edunnoteliceventverb.verbpattern_, 3);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb1)
    {
       net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb1 _econsequenceverb1 = (net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb1) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("ConsequenceVerb");
       render("(");
       pp(_econsequenceverb1.vp_, 0);
       render(",");
       pp(_econsequenceverb1.uri_1, 0);
       render(",");
       render("propSubj");
       render("=");
       pp(_econsequenceverb1.ontologyframeelement_1, 0);
       render(",");
       render("propObj");
       render("=");
       pp(_econsequenceverb1.ontologyframeelement_2, 0);
       render(",");
       pp(_econsequenceverb1.uri_2, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb2)
    {
       net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb2 _econsequenceverb2 = (net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb2) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("ConsequenceVerb");
       render("(");
       pp(_econsequenceverb2.vp_, 0);
       render(",");
       pp(_econsequenceverb2.uri_, 0);
       render(",");
       render("propSubj");
       render("=");
       pp(_econsequenceverb2.ontologyframeelement_1, 0);
       render(",");
       render("propObj");
       render("=");
       pp(_econsequenceverb2.ontologyframeelement_2, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EDurativeEventVerb)
    {
       net.lemonmodel.patterns.parser.Absyn.EDurativeEventVerb _edurativeeventverb = (net.lemonmodel.patterns.parser.Absyn.EDurativeEventVerb) foo;
       if (_i_ > 2) render(_L_PAREN);
       render("durative");
       pp(_edurativeeventverb.verbpattern_, 3);
       if (_i_ > 2) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EInstantEventVerb)
    {
       net.lemonmodel.patterns.parser.Absyn.EInstantEventVerb _einstanteventverb = (net.lemonmodel.patterns.parser.Absyn.EInstantEventVerb) foo;
       if (_i_ > 2) render(_L_PAREN);
       render("instant");
       pp(_einstanteventverb.verbpattern_, 3);
       if (_i_ > 2) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EEventVerb)
    {
       net.lemonmodel.patterns.parser.Absyn.EEventVerb _eeventverb = (net.lemonmodel.patterns.parser.Absyn.EEventVerb) foo;
       if (_i_ > 3) render(_L_PAREN);
       render("EventVerb");
       render("(");
       pp(_eeventverb.vp_, 0);
       render(",");
       pp(_eeventverb.uri_, 0);
       render(",");
       render("[");
       pp(_eeventverb.listontologyframeelement_, 0);
       render("]");
       render(")");
       if (_i_ > 3) render(_R_PAREN);
    }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.AdjectivePattern foo, int _i_)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EIntersectiveAdjective)
    {
       net.lemonmodel.patterns.parser.Absyn.EIntersectiveAdjective _eintersectiveadjective = (net.lemonmodel.patterns.parser.Absyn.EIntersectiveAdjective) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("IntersectiveAdjective");
       render("(");
       pp(_eintersectiveadjective.ap_, 0);
       render(",");
       pp(_eintersectiveadjective.uri_, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EIntersectiveObjectPropertyAdjective)
    {
       net.lemonmodel.patterns.parser.Absyn.EIntersectiveObjectPropertyAdjective _eintersectiveobjectpropertyadjective = (net.lemonmodel.patterns.parser.Absyn.EIntersectiveObjectPropertyAdjective) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("IntersectiveObjectPropertyAdjective");
       render("(");
       pp(_eintersectiveobjectpropertyadjective.ap_, 0);
       render(",");
       pp(_eintersectiveobjectpropertyadjective.uri_1, 0);
       render(",");
       pp(_eintersectiveobjectpropertyadjective.uri_2, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EIntersectiveDataPropertyAdjective)
    {
       net.lemonmodel.patterns.parser.Absyn.EIntersectiveDataPropertyAdjective _eintersectivedatapropertyadjective = (net.lemonmodel.patterns.parser.Absyn.EIntersectiveDataPropertyAdjective) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("IntersectiveDataPropertyAdjective");
       render("(");
       pp(_eintersectivedatapropertyadjective.ap_, 0);
       render(",");
       pp(_eintersectivedatapropertyadjective.uri_, 0);
       render(",");
       printQuoted(_eintersectivedatapropertyadjective.string_);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPropertyModifyingAdjective)
    {
       net.lemonmodel.patterns.parser.Absyn.EPropertyModifyingAdjective _epropertymodifyingadjective = (net.lemonmodel.patterns.parser.Absyn.EPropertyModifyingAdjective) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("PropertyModifyingAdjective");
       render("(");
       pp(_epropertymodifyingadjective.ap_, 0);
       render(",");
       pp(_epropertymodifyingadjective.uri_, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ERelationalAdjective)
    {
       net.lemonmodel.patterns.parser.Absyn.ERelationalAdjective _erelationaladjective = (net.lemonmodel.patterns.parser.Absyn.ERelationalAdjective) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("RelationalAdjective");
       render("(");
       pp(_erelationaladjective.ap_, 0);
       render(",");
       pp(_erelationaladjective.uri_, 0);
       render(",");
       render("relationalArg");
       render("=");
       pp(_erelationaladjective.arg_, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EScalarAdjective)
    {
       net.lemonmodel.patterns.parser.Absyn.EScalarAdjective _escalaradjective = (net.lemonmodel.patterns.parser.Absyn.EScalarAdjective) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("ScalarAdjective");
       render("(");
       pp(_escalaradjective.ap_, 0);
       render(",");
       render("[");
       pp(_escalaradjective.listscalarmembership_, 0);
       render("]");
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.ListPattern foo, int _i_)
  {
     for (java.util.Iterator<Pattern> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), 0);
       if (it.hasNext()) {
         render(",");
       } else {
         render("");
       }
     }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.Arg foo, int _i_)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EOptionalArg)
    {
       net.lemonmodel.patterns.parser.Absyn.EOptionalArg _eoptionalarg = (net.lemonmodel.patterns.parser.Absyn.EOptionalArg) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_eoptionalarg.arg_, 0);
       render("optional");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ERestrictedArg)
    {
       net.lemonmodel.patterns.parser.Absyn.ERestrictedArg _erestrictedarg = (net.lemonmodel.patterns.parser.Absyn.ERestrictedArg) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_erestrictedarg.arg_, 0);
       render("restrictedTo");
       pp(_erestrictedarg.uri_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ESubject)
    {
       net.lemonmodel.patterns.parser.Absyn.ESubject _esubject = (net.lemonmodel.patterns.parser.Absyn.ESubject) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("Subject");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EDirectObject)
    {
       net.lemonmodel.patterns.parser.Absyn.EDirectObject _edirectobject = (net.lemonmodel.patterns.parser.Absyn.EDirectObject) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("DirectObject");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EIndirectObject)
    {
       net.lemonmodel.patterns.parser.Absyn.EIndirectObject _eindirectobject = (net.lemonmodel.patterns.parser.Absyn.EIndirectObject) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("IndirectObject");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ECopulativeArg)
    {
       net.lemonmodel.patterns.parser.Absyn.ECopulativeArg _ecopulativearg = (net.lemonmodel.patterns.parser.Absyn.ECopulativeArg) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("CopulativeArg");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ECopulativeSubject)
    {
       net.lemonmodel.patterns.parser.Absyn.ECopulativeSubject _ecopulativesubject = (net.lemonmodel.patterns.parser.Absyn.ECopulativeSubject) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("CopulativeSubject");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPrepositionalObject)
    {
       net.lemonmodel.patterns.parser.Absyn.EPrepositionalObject _eprepositionalobject = (net.lemonmodel.patterns.parser.Absyn.EPrepositionalObject) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("PrepositionalObject");
       render("(");
       printQuoted(_eprepositionalobject.string_);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPostpositionalObject)
    {
       net.lemonmodel.patterns.parser.Absyn.EPostpositionalObject _epostpositionalobject = (net.lemonmodel.patterns.parser.Absyn.EPostpositionalObject) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("PostpositionalObject");
       render("(");
       printQuoted(_epostpositionalobject.string_);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPossessiveAdjunct)
    {
       net.lemonmodel.patterns.parser.Absyn.EPossessiveAdjunct _epossessiveadjunct = (net.lemonmodel.patterns.parser.Absyn.EPossessiveAdjunct) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("PossessiveAdjunct");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.OntologyFrameElement foo, int _i_)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EURIAsSynArg)
    {
       net.lemonmodel.patterns.parser.Absyn.EURIAsSynArg _euriassynarg = (net.lemonmodel.patterns.parser.Absyn.EURIAsSynArg) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_euriassynarg.uri_, 0);
       render("as");
       pp(_euriassynarg.arg_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.ListOntologyFrameElement foo, int _i_)
  {
     for (java.util.Iterator<OntologyFrameElement> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), 0);
       if (it.hasNext()) {
         render(",");
       } else {
         render("");
       }
     }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.PNP foo, int _i_)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPNPSimple)
    {
       net.lemonmodel.patterns.parser.Absyn.EPNPSimple _epnpsimple = (net.lemonmodel.patterns.parser.Absyn.EPNPSimple) foo;
       if (_i_ > 0) render(_L_PAREN);
       printQuoted(_epnpsimple.string_);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPNPComplex)
    {
       net.lemonmodel.patterns.parser.Absyn.EPNPComplex _epnpcomplex = (net.lemonmodel.patterns.parser.Absyn.EPNPComplex) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("PNP");
       render("(");
       pp(_epnpcomplex.listpostaggedword_, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.NP foo, int _i_)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ENPSimple)
    {
       net.lemonmodel.patterns.parser.Absyn.ENPSimple _enpsimple = (net.lemonmodel.patterns.parser.Absyn.ENPSimple) foo;
       if (_i_ > 0) render(_L_PAREN);
       printQuoted(_enpsimple.string_);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ENPComplex)
    {
       net.lemonmodel.patterns.parser.Absyn.ENPComplex _enpcomplex = (net.lemonmodel.patterns.parser.Absyn.ENPComplex) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("NP");
       render("(");
       pp(_enpcomplex.listpostaggedword_, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.VP foo, int _i_)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EVPSimple)
    {
       net.lemonmodel.patterns.parser.Absyn.EVPSimple _evpsimple = (net.lemonmodel.patterns.parser.Absyn.EVPSimple) foo;
       if (_i_ > 0) render(_L_PAREN);
       printQuoted(_evpsimple.string_);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EVPComplex)
    {
       net.lemonmodel.patterns.parser.Absyn.EVPComplex _evpcomplex = (net.lemonmodel.patterns.parser.Absyn.EVPComplex) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("VP");
       render("(");
       pp(_evpcomplex.listpostaggedword_, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.AP foo, int _i_)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EAPSimple)
    {
       net.lemonmodel.patterns.parser.Absyn.EAPSimple _eapsimple = (net.lemonmodel.patterns.parser.Absyn.EAPSimple) foo;
       if (_i_ > 0) render(_L_PAREN);
       printQuoted(_eapsimple.string_);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EAPComplex)
    {
       net.lemonmodel.patterns.parser.Absyn.EAPComplex _eapcomplex = (net.lemonmodel.patterns.parser.Absyn.EAPComplex) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("AP");
       render("(");
       pp(_eapcomplex.listpostaggedword_, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.POSTaggedWord foo, int _i_)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPOSTaggedWord)
    {
       net.lemonmodel.patterns.parser.Absyn.EPOSTaggedWord _epostaggedword = (net.lemonmodel.patterns.parser.Absyn.EPOSTaggedWord) foo;
       if (_i_ > 0) render(_L_PAREN);
       printQuoted(_epostaggedword.string_);
       render("/");
       pp(_epostaggedword.postag_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.ListPOSTaggedWord foo, int _i_)
  {
     for (java.util.Iterator<POSTaggedWord> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), 0);
       if (it.hasNext()) {
         render(",");
       } else {
         render("");
       }
     }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.ScalarMembership foo, int _i_)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.CovariantScalarMembership)
    {
       net.lemonmodel.patterns.parser.Absyn.CovariantScalarMembership _covariantscalarmembership = (net.lemonmodel.patterns.parser.Absyn.CovariantScalarMembership) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_covariantscalarmembership.uri_, 0);
       render("covariant");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ContravariantScalarMembership)
    {
       net.lemonmodel.patterns.parser.Absyn.ContravariantScalarMembership _contravariantscalarmembership = (net.lemonmodel.patterns.parser.Absyn.ContravariantScalarMembership) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_contravariantscalarmembership.uri_, 0);
       render("contravariant");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.CentralScalarMembership)
    {
       net.lemonmodel.patterns.parser.Absyn.CentralScalarMembership _centralscalarmembership = (net.lemonmodel.patterns.parser.Absyn.CentralScalarMembership) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_centralscalarmembership.uri_, 0);
       render("central");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.GreaterThanScalarMembership)
    {
       net.lemonmodel.patterns.parser.Absyn.GreaterThanScalarMembership _greaterthanscalarmembership = (net.lemonmodel.patterns.parser.Absyn.GreaterThanScalarMembership) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_greaterthanscalarmembership.uri_1, 0);
       render(">");
       pp(_greaterthanscalarmembership.double_, 0);
       render("for");
       pp(_greaterthanscalarmembership.uri_2, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.LessThanScalarMembership)
    {
       net.lemonmodel.patterns.parser.Absyn.LessThanScalarMembership _lessthanscalarmembership = (net.lemonmodel.patterns.parser.Absyn.LessThanScalarMembership) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_lessthanscalarmembership.uri_1, 0);
       render("<");
       pp(_lessthanscalarmembership.double_, 0);
       render("for");
       pp(_lessthanscalarmembership.uri_2, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.BoundedScalarMembership)
    {
       net.lemonmodel.patterns.parser.Absyn.BoundedScalarMembership _boundedscalarmembership = (net.lemonmodel.patterns.parser.Absyn.BoundedScalarMembership) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_boundedscalarmembership.double_1, 0);
       render("<");
       pp(_boundedscalarmembership.uri_1, 0);
       render("<");
       pp(_boundedscalarmembership.double_2, 0);
       render("for");
       pp(_boundedscalarmembership.uri_2, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.ListScalarMembership foo, int _i_)
  {
     for (java.util.Iterator<ScalarMembership> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), 0);
       if (it.hasNext()) {
         render(",");
       } else {
         render("");
       }
     }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.Category foo, int _i_)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ESingular)
    {
       net.lemonmodel.patterns.parser.Absyn.ESingular _esingular = (net.lemonmodel.patterns.parser.Absyn.ESingular) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("singular");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EDual)
    {
       net.lemonmodel.patterns.parser.Absyn.EDual _edual = (net.lemonmodel.patterns.parser.Absyn.EDual) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("dual");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPlural)
    {
       net.lemonmodel.patterns.parser.Absyn.EPlural _eplural = (net.lemonmodel.patterns.parser.Absyn.EPlural) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("plural");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ENominative)
    {
       net.lemonmodel.patterns.parser.Absyn.ENominative _enominative = (net.lemonmodel.patterns.parser.Absyn.ENominative) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("nominative");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EAccusative)
    {
       net.lemonmodel.patterns.parser.Absyn.EAccusative _eaccusative = (net.lemonmodel.patterns.parser.Absyn.EAccusative) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("accusative");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EGenitive)
    {
       net.lemonmodel.patterns.parser.Absyn.EGenitive _egenitive = (net.lemonmodel.patterns.parser.Absyn.EGenitive) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("genitive");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EDative)
    {
       net.lemonmodel.patterns.parser.Absyn.EDative _edative = (net.lemonmodel.patterns.parser.Absyn.EDative) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("dative");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EComparative)
    {
       net.lemonmodel.patterns.parser.Absyn.EComparative _ecomparative = (net.lemonmodel.patterns.parser.Absyn.EComparative) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("comparative");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ESuperlative)
    {
       net.lemonmodel.patterns.parser.Absyn.ESuperlative _esuperlative = (net.lemonmodel.patterns.parser.Absyn.ESuperlative) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("superlative");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPresent)
    {
       net.lemonmodel.patterns.parser.Absyn.EPresent _epresent = (net.lemonmodel.patterns.parser.Absyn.EPresent) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("present");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPast)
    {
       net.lemonmodel.patterns.parser.Absyn.EPast _epast = (net.lemonmodel.patterns.parser.Absyn.EPast) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("past");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EFuture)
    {
       net.lemonmodel.patterns.parser.Absyn.EFuture _efuture = (net.lemonmodel.patterns.parser.Absyn.EFuture) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("future");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EFirstPerson)
    {
       net.lemonmodel.patterns.parser.Absyn.EFirstPerson _efirstperson = (net.lemonmodel.patterns.parser.Absyn.EFirstPerson) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("firstPerson");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ESecondPerson)
    {
       net.lemonmodel.patterns.parser.Absyn.ESecondPerson _esecondperson = (net.lemonmodel.patterns.parser.Absyn.ESecondPerson) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("secondPerson");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EThirdPerson)
    {
       net.lemonmodel.patterns.parser.Absyn.EThirdPerson _ethirdperson = (net.lemonmodel.patterns.parser.Absyn.EThirdPerson) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("thirdPerson");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EImperfect)
    {
       net.lemonmodel.patterns.parser.Absyn.EImperfect _eimperfect = (net.lemonmodel.patterns.parser.Absyn.EImperfect) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("imperfect");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EImperative)
    {
       net.lemonmodel.patterns.parser.Absyn.EImperative _eimperative = (net.lemonmodel.patterns.parser.Absyn.EImperative) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("imperative");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EIndicative)
    {
       net.lemonmodel.patterns.parser.Absyn.EIndicative _eindicative = (net.lemonmodel.patterns.parser.Absyn.EIndicative) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("indicative");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ESubjunctive)
    {
       net.lemonmodel.patterns.parser.Absyn.ESubjunctive _esubjunctive = (net.lemonmodel.patterns.parser.Absyn.ESubjunctive) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("subjunctive");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EConditional)
    {
       net.lemonmodel.patterns.parser.Absyn.EConditional _econditional = (net.lemonmodel.patterns.parser.Absyn.EConditional) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("conditional");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EGerundive)
    {
       net.lemonmodel.patterns.parser.Absyn.EGerundive _egerundive = (net.lemonmodel.patterns.parser.Absyn.EGerundive) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("gerundive");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EInfinitive)
    {
       net.lemonmodel.patterns.parser.Absyn.EInfinitive _einfinitive = (net.lemonmodel.patterns.parser.Absyn.EInfinitive) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("infinitive");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EParticiple)
    {
       net.lemonmodel.patterns.parser.Absyn.EParticiple _eparticiple = (net.lemonmodel.patterns.parser.Absyn.EParticiple) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("participle");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EAnyCat)
    {
       net.lemonmodel.patterns.parser.Absyn.EAnyCat _eanycat = (net.lemonmodel.patterns.parser.Absyn.EAnyCat) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_eanycat.uri_1, 0);
       render("=>");
       pp(_eanycat.uri_2, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.ListCategory foo, int _i_)
  {
     for (java.util.Iterator<Category> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), 0);
       if (it.hasNext()) {
         render("");
       } else {
         render("");
       }
     }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.POSTag foo, int _i_)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EAdjectivePOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EAdjectivePOS _eadjectivepos = (net.lemonmodel.patterns.parser.Absyn.EAdjectivePOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("adjective");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EAdpositionPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EAdpositionPOS _eadpositionpos = (net.lemonmodel.patterns.parser.Absyn.EAdpositionPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("adposition");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EAdverbPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EAdverbPOS _eadverbpos = (net.lemonmodel.patterns.parser.Absyn.EAdverbPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("adverb");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EArticlePOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EArticlePOS _earticlepos = (net.lemonmodel.patterns.parser.Absyn.EArticlePOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("article");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EBulletPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EBulletPOS _ebulletpos = (net.lemonmodel.patterns.parser.Absyn.EBulletPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("bullet");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ECircumpositionPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.ECircumpositionPOS _ecircumpositionpos = (net.lemonmodel.patterns.parser.Absyn.ECircumpositionPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("circumposition");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EColonPOSPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EColonPOSPOS _ecolonpospos = (net.lemonmodel.patterns.parser.Absyn.EColonPOSPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("colon");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ECommaPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.ECommaPOS _ecommapos = (net.lemonmodel.patterns.parser.Absyn.ECommaPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("comma");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EConjunctionPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EConjunctionPOS _econjunctionpos = (net.lemonmodel.patterns.parser.Absyn.EConjunctionPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("conjunction");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ECopulaPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.ECopulaPOS _ecopulapos = (net.lemonmodel.patterns.parser.Absyn.ECopulaPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("copula");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EDeterminerPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EDeterminerPOS _edeterminerpos = (net.lemonmodel.patterns.parser.Absyn.EDeterminerPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("determiner");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EInterjectionPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EInterjectionPOS _einterjectionpos = (net.lemonmodel.patterns.parser.Absyn.EInterjectionPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("interjection");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ENounPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.ENounPOS _enounpos = (net.lemonmodel.patterns.parser.Absyn.ENounPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("noun");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ENumeralPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.ENumeralPOS _enumeralpos = (net.lemonmodel.patterns.parser.Absyn.ENumeralPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("numeral");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EParticlePOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EParticlePOS _eparticlepos = (net.lemonmodel.patterns.parser.Absyn.EParticlePOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("particle");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPointPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EPointPOS _epointpos = (net.lemonmodel.patterns.parser.Absyn.EPointPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("point");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPostpositionPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EPostpositionPOS _epostpositionpos = (net.lemonmodel.patterns.parser.Absyn.EPostpositionPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("postposition");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPrepositionPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EPrepositionPOS _eprepositionpos = (net.lemonmodel.patterns.parser.Absyn.EPrepositionPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("preposition");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPronounPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EPronounPOS _epronounpos = (net.lemonmodel.patterns.parser.Absyn.EPronounPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("pronoun");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPunctuationPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EPunctuationPOS _epunctuationpos = (net.lemonmodel.patterns.parser.Absyn.EPunctuationPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("punctuation");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ESemiColonPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.ESemiColonPOS _esemicolonpos = (net.lemonmodel.patterns.parser.Absyn.ESemiColonPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("semiColon");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ESlashPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.ESlashPOS _eslashpos = (net.lemonmodel.patterns.parser.Absyn.ESlashPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("slash");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EVerbPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EVerbPOS _everbpos = (net.lemonmodel.patterns.parser.Absyn.EVerbPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("verb");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EAnyPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EAnyPOS _eanypos = (net.lemonmodel.patterns.parser.Absyn.EAnyPOS) foo;
       if (_i_ > 0) render(_L_PAREN);
       printQuoted(_eanypos.string_);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(net.lemonmodel.patterns.parser.Absyn.URI foo, int _i_)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EQName)
    {
       net.lemonmodel.patterns.parser.Absyn.EQName _eqname = (net.lemonmodel.patterns.parser.Absyn.EQName) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_eqname.ident_1, 0);
       render(":");
       pp(_eqname.ident_2, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EQName2)
    {
       net.lemonmodel.patterns.parser.Absyn.EQName2 _eqname2 = (net.lemonmodel.patterns.parser.Absyn.EQName2) foo;
       if (_i_ > 0) render(_L_PAREN);
       render(":");
       pp(_eqname2.ident_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EURI)
    {
       net.lemonmodel.patterns.parser.Absyn.EURI _euri = (net.lemonmodel.patterns.parser.Absyn.EURI) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_euri.fulluri_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }


  private static void sh(net.lemonmodel.patterns.parser.Absyn.Statements foo)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EStatments)
    {
       net.lemonmodel.patterns.parser.Absyn.EStatments _estatments = (net.lemonmodel.patterns.parser.Absyn.EStatments) foo;
       render("(");
       render("EStatments");
       render("[");
       sh(_estatments.liststatement_);
       render("]");
       render(")");
    }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.Statement foo)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPrefix)
    {
       net.lemonmodel.patterns.parser.Absyn.EPrefix _eprefix = (net.lemonmodel.patterns.parser.Absyn.EPrefix) foo;
       render("(");
       render("EPrefix");
       sh(_eprefix.ident_);
       sh(_eprefix.fulluri_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ELexicon)
    {
       net.lemonmodel.patterns.parser.Absyn.ELexicon _elexicon = (net.lemonmodel.patterns.parser.Absyn.ELexicon) foo;
       render("(");
       render("ELexicon");
       sh(_elexicon.uri_);
       sh(_elexicon.string_);
       render("[");
       sh(_elexicon.listpattern_);
       render("]");
       render(")");
    }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.ListStatement foo)
  {
     for (java.util.Iterator<Statement> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.Pattern foo)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPatternWithForm)
    {
       net.lemonmodel.patterns.parser.Absyn.EPatternWithForm _epatternwithform = (net.lemonmodel.patterns.parser.Absyn.EPatternWithForm) foo;
       render("(");
       render("EPatternWithForm");
       sh(_epatternwithform.pattern_);
       render("[");
       sh(_epatternwithform.listcategory_);
       render("]");
       sh(_epatternwithform.string_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ENoun)
    {
       net.lemonmodel.patterns.parser.Absyn.ENoun _enoun = (net.lemonmodel.patterns.parser.Absyn.ENoun) foo;
       render("(");
       render("ENoun");
       sh(_enoun.nounpattern_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EVerb)
    {
       net.lemonmodel.patterns.parser.Absyn.EVerb _everb = (net.lemonmodel.patterns.parser.Absyn.EVerb) foo;
       render("(");
       render("EVerb");
       sh(_everb.verbpattern_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EAdjective)
    {
       net.lemonmodel.patterns.parser.Absyn.EAdjective _eadjective = (net.lemonmodel.patterns.parser.Absyn.EAdjective) foo;
       render("(");
       render("EAdjective");
       sh(_eadjective.adjectivepattern_);
       render(")");
    }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.NounPattern foo)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EName)
    {
       net.lemonmodel.patterns.parser.Absyn.EName _ename = (net.lemonmodel.patterns.parser.Absyn.EName) foo;
       render("(");
       render("EName");
       sh(_ename.pnp_);
       sh(_ename.uri_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EClassNoun)
    {
       net.lemonmodel.patterns.parser.Absyn.EClassNoun _eclassnoun = (net.lemonmodel.patterns.parser.Absyn.EClassNoun) foo;
       render("(");
       render("EClassNoun");
       sh(_eclassnoun.np_);
       sh(_eclassnoun.uri_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ERelationalNoun1)
    {
       net.lemonmodel.patterns.parser.Absyn.ERelationalNoun1 _erelationalnoun1 = (net.lemonmodel.patterns.parser.Absyn.ERelationalNoun1) foo;
       render("(");
       render("ERelationalNoun1");
       sh(_erelationalnoun1.np_);
       sh(_erelationalnoun1.uri_);
       sh(_erelationalnoun1.arg_1);
       sh(_erelationalnoun1.arg_2);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ERelationalNoun2)
    {
       net.lemonmodel.patterns.parser.Absyn.ERelationalNoun2 _erelationalnoun2 = (net.lemonmodel.patterns.parser.Absyn.ERelationalNoun2) foo;
       render("(");
       render("ERelationalNoun2");
       sh(_erelationalnoun2.np_);
       sh(_erelationalnoun2.uri_);
       sh(_erelationalnoun2.arg_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ERelationalMultivalentNoun)
    {
       net.lemonmodel.patterns.parser.Absyn.ERelationalMultivalentNoun _erelationalmultivalentnoun = (net.lemonmodel.patterns.parser.Absyn.ERelationalMultivalentNoun) foo;
       render("(");
       render("ERelationalMultivalentNoun");
       sh(_erelationalmultivalentnoun.np_);
       sh(_erelationalmultivalentnoun.uri_);
       render("[");
       sh(_erelationalmultivalentnoun.listontologyframeelement_);
       render("]");
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun1)
    {
       net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun1 _eclassrelationalnoun1 = (net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun1) foo;
       render("(");
       render("EClassRelationalNoun1");
       sh(_eclassrelationalnoun1.np_);
       sh(_eclassrelationalnoun1.uri_1);
       sh(_eclassrelationalnoun1.uri_2);
       sh(_eclassrelationalnoun1.arg_1);
       sh(_eclassrelationalnoun1.arg_2);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun2)
    {
       net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun2 _eclassrelationalnoun2 = (net.lemonmodel.patterns.parser.Absyn.EClassRelationalNoun2) foo;
       render("(");
       render("EClassRelationalNoun2");
       sh(_eclassrelationalnoun2.np_);
       sh(_eclassrelationalnoun2.uri_1);
       sh(_eclassrelationalnoun2.uri_2);
       sh(_eclassrelationalnoun2.arg_);
       render(")");
    }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.VerbPattern foo)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EStateVerb1)
    {
       net.lemonmodel.patterns.parser.Absyn.EStateVerb1 _estateverb1 = (net.lemonmodel.patterns.parser.Absyn.EStateVerb1) foo;
       render("(");
       render("EStateVerb1");
       sh(_estateverb1.vp_);
       sh(_estateverb1.uri_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EStateVerb2)
    {
       net.lemonmodel.patterns.parser.Absyn.EStateVerb2 _estateverb2 = (net.lemonmodel.patterns.parser.Absyn.EStateVerb2) foo;
       render("(");
       render("EStateVerb2");
       sh(_estateverb2.vp_);
       sh(_estateverb2.uri_);
       sh(_estateverb2.arg_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EStateVerb3)
    {
       net.lemonmodel.patterns.parser.Absyn.EStateVerb3 _estateverb3 = (net.lemonmodel.patterns.parser.Absyn.EStateVerb3) foo;
       render("(");
       render("EStateVerb3");
       sh(_estateverb3.vp_);
       sh(_estateverb3.uri_);
       sh(_estateverb3.arg_1);
       sh(_estateverb3.arg_2);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ETelicEventVerb)
    {
       net.lemonmodel.patterns.parser.Absyn.ETelicEventVerb _eteliceventverb = (net.lemonmodel.patterns.parser.Absyn.ETelicEventVerb) foo;
       render("(");
       render("ETelicEventVerb");
       sh(_eteliceventverb.verbpattern_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ENontelicEventVerb)
    {
       net.lemonmodel.patterns.parser.Absyn.ENontelicEventVerb _enonteliceventverb = (net.lemonmodel.patterns.parser.Absyn.ENontelicEventVerb) foo;
       render("(");
       render("ENontelicEventVerb");
       sh(_enonteliceventverb.verbpattern_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EDunnotelicEventVerb)
    {
       net.lemonmodel.patterns.parser.Absyn.EDunnotelicEventVerb _edunnoteliceventverb = (net.lemonmodel.patterns.parser.Absyn.EDunnotelicEventVerb) foo;
       render("(");
       render("EDunnotelicEventVerb");
       sh(_edunnoteliceventverb.verbpattern_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb1)
    {
       net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb1 _econsequenceverb1 = (net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb1) foo;
       render("(");
       render("EConsequenceVerb1");
       sh(_econsequenceverb1.vp_);
       sh(_econsequenceverb1.uri_1);
       sh(_econsequenceverb1.ontologyframeelement_1);
       sh(_econsequenceverb1.ontologyframeelement_2);
       sh(_econsequenceverb1.uri_2);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb2)
    {
       net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb2 _econsequenceverb2 = (net.lemonmodel.patterns.parser.Absyn.EConsequenceVerb2) foo;
       render("(");
       render("EConsequenceVerb2");
       sh(_econsequenceverb2.vp_);
       sh(_econsequenceverb2.uri_);
       sh(_econsequenceverb2.ontologyframeelement_1);
       sh(_econsequenceverb2.ontologyframeelement_2);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EDurativeEventVerb)
    {
       net.lemonmodel.patterns.parser.Absyn.EDurativeEventVerb _edurativeeventverb = (net.lemonmodel.patterns.parser.Absyn.EDurativeEventVerb) foo;
       render("(");
       render("EDurativeEventVerb");
       sh(_edurativeeventverb.verbpattern_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EInstantEventVerb)
    {
       net.lemonmodel.patterns.parser.Absyn.EInstantEventVerb _einstanteventverb = (net.lemonmodel.patterns.parser.Absyn.EInstantEventVerb) foo;
       render("(");
       render("EInstantEventVerb");
       sh(_einstanteventverb.verbpattern_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EEventVerb)
    {
       net.lemonmodel.patterns.parser.Absyn.EEventVerb _eeventverb = (net.lemonmodel.patterns.parser.Absyn.EEventVerb) foo;
       render("(");
       render("EEventVerb");
       sh(_eeventverb.vp_);
       sh(_eeventverb.uri_);
       render("[");
       sh(_eeventverb.listontologyframeelement_);
       render("]");
       render(")");
    }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.AdjectivePattern foo)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EIntersectiveAdjective)
    {
       net.lemonmodel.patterns.parser.Absyn.EIntersectiveAdjective _eintersectiveadjective = (net.lemonmodel.patterns.parser.Absyn.EIntersectiveAdjective) foo;
       render("(");
       render("EIntersectiveAdjective");
       sh(_eintersectiveadjective.ap_);
       sh(_eintersectiveadjective.uri_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EIntersectiveObjectPropertyAdjective)
    {
       net.lemonmodel.patterns.parser.Absyn.EIntersectiveObjectPropertyAdjective _eintersectiveobjectpropertyadjective = (net.lemonmodel.patterns.parser.Absyn.EIntersectiveObjectPropertyAdjective) foo;
       render("(");
       render("EIntersectiveObjectPropertyAdjective");
       sh(_eintersectiveobjectpropertyadjective.ap_);
       sh(_eintersectiveobjectpropertyadjective.uri_1);
       sh(_eintersectiveobjectpropertyadjective.uri_2);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EIntersectiveDataPropertyAdjective)
    {
       net.lemonmodel.patterns.parser.Absyn.EIntersectiveDataPropertyAdjective _eintersectivedatapropertyadjective = (net.lemonmodel.patterns.parser.Absyn.EIntersectiveDataPropertyAdjective) foo;
       render("(");
       render("EIntersectiveDataPropertyAdjective");
       sh(_eintersectivedatapropertyadjective.ap_);
       sh(_eintersectivedatapropertyadjective.uri_);
       sh(_eintersectivedatapropertyadjective.string_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPropertyModifyingAdjective)
    {
       net.lemonmodel.patterns.parser.Absyn.EPropertyModifyingAdjective _epropertymodifyingadjective = (net.lemonmodel.patterns.parser.Absyn.EPropertyModifyingAdjective) foo;
       render("(");
       render("EPropertyModifyingAdjective");
       sh(_epropertymodifyingadjective.ap_);
       sh(_epropertymodifyingadjective.uri_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ERelationalAdjective)
    {
       net.lemonmodel.patterns.parser.Absyn.ERelationalAdjective _erelationaladjective = (net.lemonmodel.patterns.parser.Absyn.ERelationalAdjective) foo;
       render("(");
       render("ERelationalAdjective");
       sh(_erelationaladjective.ap_);
       sh(_erelationaladjective.uri_);
       sh(_erelationaladjective.arg_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EScalarAdjective)
    {
       net.lemonmodel.patterns.parser.Absyn.EScalarAdjective _escalaradjective = (net.lemonmodel.patterns.parser.Absyn.EScalarAdjective) foo;
       render("(");
       render("EScalarAdjective");
       sh(_escalaradjective.ap_);
       render("[");
       sh(_escalaradjective.listscalarmembership_);
       render("]");
       render(")");
    }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.ListPattern foo)
  {
     for (java.util.Iterator<Pattern> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.Arg foo)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EOptionalArg)
    {
       net.lemonmodel.patterns.parser.Absyn.EOptionalArg _eoptionalarg = (net.lemonmodel.patterns.parser.Absyn.EOptionalArg) foo;
       render("(");
       render("EOptionalArg");
       sh(_eoptionalarg.arg_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ERestrictedArg)
    {
       net.lemonmodel.patterns.parser.Absyn.ERestrictedArg _erestrictedarg = (net.lemonmodel.patterns.parser.Absyn.ERestrictedArg) foo;
       render("(");
       render("ERestrictedArg");
       sh(_erestrictedarg.arg_);
       sh(_erestrictedarg.uri_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ESubject)
    {
       net.lemonmodel.patterns.parser.Absyn.ESubject _esubject = (net.lemonmodel.patterns.parser.Absyn.ESubject) foo;
       render("ESubject");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EDirectObject)
    {
       net.lemonmodel.patterns.parser.Absyn.EDirectObject _edirectobject = (net.lemonmodel.patterns.parser.Absyn.EDirectObject) foo;
       render("EDirectObject");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EIndirectObject)
    {
       net.lemonmodel.patterns.parser.Absyn.EIndirectObject _eindirectobject = (net.lemonmodel.patterns.parser.Absyn.EIndirectObject) foo;
       render("EIndirectObject");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ECopulativeArg)
    {
       net.lemonmodel.patterns.parser.Absyn.ECopulativeArg _ecopulativearg = (net.lemonmodel.patterns.parser.Absyn.ECopulativeArg) foo;
       render("ECopulativeArg");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ECopulativeSubject)
    {
       net.lemonmodel.patterns.parser.Absyn.ECopulativeSubject _ecopulativesubject = (net.lemonmodel.patterns.parser.Absyn.ECopulativeSubject) foo;
       render("ECopulativeSubject");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPrepositionalObject)
    {
       net.lemonmodel.patterns.parser.Absyn.EPrepositionalObject _eprepositionalobject = (net.lemonmodel.patterns.parser.Absyn.EPrepositionalObject) foo;
       render("(");
       render("EPrepositionalObject");
       sh(_eprepositionalobject.string_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPostpositionalObject)
    {
       net.lemonmodel.patterns.parser.Absyn.EPostpositionalObject _epostpositionalobject = (net.lemonmodel.patterns.parser.Absyn.EPostpositionalObject) foo;
       render("(");
       render("EPostpositionalObject");
       sh(_epostpositionalobject.string_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPossessiveAdjunct)
    {
       net.lemonmodel.patterns.parser.Absyn.EPossessiveAdjunct _epossessiveadjunct = (net.lemonmodel.patterns.parser.Absyn.EPossessiveAdjunct) foo;
       render("EPossessiveAdjunct");
    }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.OntologyFrameElement foo)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EURIAsSynArg)
    {
       net.lemonmodel.patterns.parser.Absyn.EURIAsSynArg _euriassynarg = (net.lemonmodel.patterns.parser.Absyn.EURIAsSynArg) foo;
       render("(");
       render("EURIAsSynArg");
       sh(_euriassynarg.uri_);
       sh(_euriassynarg.arg_);
       render(")");
    }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.ListOntologyFrameElement foo)
  {
     for (java.util.Iterator<OntologyFrameElement> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.PNP foo)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPNPSimple)
    {
       net.lemonmodel.patterns.parser.Absyn.EPNPSimple _epnpsimple = (net.lemonmodel.patterns.parser.Absyn.EPNPSimple) foo;
       render("(");
       render("EPNPSimple");
       sh(_epnpsimple.string_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPNPComplex)
    {
       net.lemonmodel.patterns.parser.Absyn.EPNPComplex _epnpcomplex = (net.lemonmodel.patterns.parser.Absyn.EPNPComplex) foo;
       render("(");
       render("EPNPComplex");
       render("[");
       sh(_epnpcomplex.listpostaggedword_);
       render("]");
       render(")");
    }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.NP foo)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ENPSimple)
    {
       net.lemonmodel.patterns.parser.Absyn.ENPSimple _enpsimple = (net.lemonmodel.patterns.parser.Absyn.ENPSimple) foo;
       render("(");
       render("ENPSimple");
       sh(_enpsimple.string_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ENPComplex)
    {
       net.lemonmodel.patterns.parser.Absyn.ENPComplex _enpcomplex = (net.lemonmodel.patterns.parser.Absyn.ENPComplex) foo;
       render("(");
       render("ENPComplex");
       render("[");
       sh(_enpcomplex.listpostaggedword_);
       render("]");
       render(")");
    }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.VP foo)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EVPSimple)
    {
       net.lemonmodel.patterns.parser.Absyn.EVPSimple _evpsimple = (net.lemonmodel.patterns.parser.Absyn.EVPSimple) foo;
       render("(");
       render("EVPSimple");
       sh(_evpsimple.string_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EVPComplex)
    {
       net.lemonmodel.patterns.parser.Absyn.EVPComplex _evpcomplex = (net.lemonmodel.patterns.parser.Absyn.EVPComplex) foo;
       render("(");
       render("EVPComplex");
       render("[");
       sh(_evpcomplex.listpostaggedword_);
       render("]");
       render(")");
    }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.AP foo)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EAPSimple)
    {
       net.lemonmodel.patterns.parser.Absyn.EAPSimple _eapsimple = (net.lemonmodel.patterns.parser.Absyn.EAPSimple) foo;
       render("(");
       render("EAPSimple");
       sh(_eapsimple.string_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EAPComplex)
    {
       net.lemonmodel.patterns.parser.Absyn.EAPComplex _eapcomplex = (net.lemonmodel.patterns.parser.Absyn.EAPComplex) foo;
       render("(");
       render("EAPComplex");
       render("[");
       sh(_eapcomplex.listpostaggedword_);
       render("]");
       render(")");
    }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.POSTaggedWord foo)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPOSTaggedWord)
    {
       net.lemonmodel.patterns.parser.Absyn.EPOSTaggedWord _epostaggedword = (net.lemonmodel.patterns.parser.Absyn.EPOSTaggedWord) foo;
       render("(");
       render("EPOSTaggedWord");
       sh(_epostaggedword.string_);
       sh(_epostaggedword.postag_);
       render(")");
    }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.ListPOSTaggedWord foo)
  {
     for (java.util.Iterator<POSTaggedWord> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.ScalarMembership foo)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.CovariantScalarMembership)
    {
       net.lemonmodel.patterns.parser.Absyn.CovariantScalarMembership _covariantscalarmembership = (net.lemonmodel.patterns.parser.Absyn.CovariantScalarMembership) foo;
       render("(");
       render("CovariantScalarMembership");
       sh(_covariantscalarmembership.uri_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ContravariantScalarMembership)
    {
       net.lemonmodel.patterns.parser.Absyn.ContravariantScalarMembership _contravariantscalarmembership = (net.lemonmodel.patterns.parser.Absyn.ContravariantScalarMembership) foo;
       render("(");
       render("ContravariantScalarMembership");
       sh(_contravariantscalarmembership.uri_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.CentralScalarMembership)
    {
       net.lemonmodel.patterns.parser.Absyn.CentralScalarMembership _centralscalarmembership = (net.lemonmodel.patterns.parser.Absyn.CentralScalarMembership) foo;
       render("(");
       render("CentralScalarMembership");
       sh(_centralscalarmembership.uri_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.GreaterThanScalarMembership)
    {
       net.lemonmodel.patterns.parser.Absyn.GreaterThanScalarMembership _greaterthanscalarmembership = (net.lemonmodel.patterns.parser.Absyn.GreaterThanScalarMembership) foo;
       render("(");
       render("GreaterThanScalarMembership");
       sh(_greaterthanscalarmembership.uri_1);
       sh(_greaterthanscalarmembership.double_);
       sh(_greaterthanscalarmembership.uri_2);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.LessThanScalarMembership)
    {
       net.lemonmodel.patterns.parser.Absyn.LessThanScalarMembership _lessthanscalarmembership = (net.lemonmodel.patterns.parser.Absyn.LessThanScalarMembership) foo;
       render("(");
       render("LessThanScalarMembership");
       sh(_lessthanscalarmembership.uri_1);
       sh(_lessthanscalarmembership.double_);
       sh(_lessthanscalarmembership.uri_2);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.BoundedScalarMembership)
    {
       net.lemonmodel.patterns.parser.Absyn.BoundedScalarMembership _boundedscalarmembership = (net.lemonmodel.patterns.parser.Absyn.BoundedScalarMembership) foo;
       render("(");
       render("BoundedScalarMembership");
       sh(_boundedscalarmembership.double_1);
       sh(_boundedscalarmembership.uri_1);
       sh(_boundedscalarmembership.double_2);
       sh(_boundedscalarmembership.uri_2);
       render(")");
    }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.ListScalarMembership foo)
  {
     for (java.util.Iterator<ScalarMembership> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.Category foo)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ESingular)
    {
       net.lemonmodel.patterns.parser.Absyn.ESingular _esingular = (net.lemonmodel.patterns.parser.Absyn.ESingular) foo;
       render("ESingular");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EDual)
    {
       net.lemonmodel.patterns.parser.Absyn.EDual _edual = (net.lemonmodel.patterns.parser.Absyn.EDual) foo;
       render("EDual");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPlural)
    {
       net.lemonmodel.patterns.parser.Absyn.EPlural _eplural = (net.lemonmodel.patterns.parser.Absyn.EPlural) foo;
       render("EPlural");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ENominative)
    {
       net.lemonmodel.patterns.parser.Absyn.ENominative _enominative = (net.lemonmodel.patterns.parser.Absyn.ENominative) foo;
       render("ENominative");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EAccusative)
    {
       net.lemonmodel.patterns.parser.Absyn.EAccusative _eaccusative = (net.lemonmodel.patterns.parser.Absyn.EAccusative) foo;
       render("EAccusative");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EGenitive)
    {
       net.lemonmodel.patterns.parser.Absyn.EGenitive _egenitive = (net.lemonmodel.patterns.parser.Absyn.EGenitive) foo;
       render("EGenitive");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EDative)
    {
       net.lemonmodel.patterns.parser.Absyn.EDative _edative = (net.lemonmodel.patterns.parser.Absyn.EDative) foo;
       render("EDative");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EComparative)
    {
       net.lemonmodel.patterns.parser.Absyn.EComparative _ecomparative = (net.lemonmodel.patterns.parser.Absyn.EComparative) foo;
       render("EComparative");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ESuperlative)
    {
       net.lemonmodel.patterns.parser.Absyn.ESuperlative _esuperlative = (net.lemonmodel.patterns.parser.Absyn.ESuperlative) foo;
       render("ESuperlative");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPresent)
    {
       net.lemonmodel.patterns.parser.Absyn.EPresent _epresent = (net.lemonmodel.patterns.parser.Absyn.EPresent) foo;
       render("EPresent");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPast)
    {
       net.lemonmodel.patterns.parser.Absyn.EPast _epast = (net.lemonmodel.patterns.parser.Absyn.EPast) foo;
       render("EPast");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EFuture)
    {
       net.lemonmodel.patterns.parser.Absyn.EFuture _efuture = (net.lemonmodel.patterns.parser.Absyn.EFuture) foo;
       render("EFuture");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EFirstPerson)
    {
       net.lemonmodel.patterns.parser.Absyn.EFirstPerson _efirstperson = (net.lemonmodel.patterns.parser.Absyn.EFirstPerson) foo;
       render("EFirstPerson");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ESecondPerson)
    {
       net.lemonmodel.patterns.parser.Absyn.ESecondPerson _esecondperson = (net.lemonmodel.patterns.parser.Absyn.ESecondPerson) foo;
       render("ESecondPerson");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EThirdPerson)
    {
       net.lemonmodel.patterns.parser.Absyn.EThirdPerson _ethirdperson = (net.lemonmodel.patterns.parser.Absyn.EThirdPerson) foo;
       render("EThirdPerson");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EImperfect)
    {
       net.lemonmodel.patterns.parser.Absyn.EImperfect _eimperfect = (net.lemonmodel.patterns.parser.Absyn.EImperfect) foo;
       render("EImperfect");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EImperative)
    {
       net.lemonmodel.patterns.parser.Absyn.EImperative _eimperative = (net.lemonmodel.patterns.parser.Absyn.EImperative) foo;
       render("EImperative");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EIndicative)
    {
       net.lemonmodel.patterns.parser.Absyn.EIndicative _eindicative = (net.lemonmodel.patterns.parser.Absyn.EIndicative) foo;
       render("EIndicative");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ESubjunctive)
    {
       net.lemonmodel.patterns.parser.Absyn.ESubjunctive _esubjunctive = (net.lemonmodel.patterns.parser.Absyn.ESubjunctive) foo;
       render("ESubjunctive");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EConditional)
    {
       net.lemonmodel.patterns.parser.Absyn.EConditional _econditional = (net.lemonmodel.patterns.parser.Absyn.EConditional) foo;
       render("EConditional");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EGerundive)
    {
       net.lemonmodel.patterns.parser.Absyn.EGerundive _egerundive = (net.lemonmodel.patterns.parser.Absyn.EGerundive) foo;
       render("EGerundive");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EInfinitive)
    {
       net.lemonmodel.patterns.parser.Absyn.EInfinitive _einfinitive = (net.lemonmodel.patterns.parser.Absyn.EInfinitive) foo;
       render("EInfinitive");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EParticiple)
    {
       net.lemonmodel.patterns.parser.Absyn.EParticiple _eparticiple = (net.lemonmodel.patterns.parser.Absyn.EParticiple) foo;
       render("EParticiple");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EAnyCat)
    {
       net.lemonmodel.patterns.parser.Absyn.EAnyCat _eanycat = (net.lemonmodel.patterns.parser.Absyn.EAnyCat) foo;
       render("(");
       render("EAnyCat");
       sh(_eanycat.uri_1);
       sh(_eanycat.uri_2);
       render(")");
    }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.ListCategory foo)
  {
     for (java.util.Iterator<Category> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.POSTag foo)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EAdjectivePOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EAdjectivePOS _eadjectivepos = (net.lemonmodel.patterns.parser.Absyn.EAdjectivePOS) foo;
       render("EAdjectivePOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EAdpositionPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EAdpositionPOS _eadpositionpos = (net.lemonmodel.patterns.parser.Absyn.EAdpositionPOS) foo;
       render("EAdpositionPOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EAdverbPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EAdverbPOS _eadverbpos = (net.lemonmodel.patterns.parser.Absyn.EAdverbPOS) foo;
       render("EAdverbPOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EArticlePOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EArticlePOS _earticlepos = (net.lemonmodel.patterns.parser.Absyn.EArticlePOS) foo;
       render("EArticlePOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EBulletPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EBulletPOS _ebulletpos = (net.lemonmodel.patterns.parser.Absyn.EBulletPOS) foo;
       render("EBulletPOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ECircumpositionPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.ECircumpositionPOS _ecircumpositionpos = (net.lemonmodel.patterns.parser.Absyn.ECircumpositionPOS) foo;
       render("ECircumpositionPOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EColonPOSPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EColonPOSPOS _ecolonpospos = (net.lemonmodel.patterns.parser.Absyn.EColonPOSPOS) foo;
       render("EColonPOSPOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ECommaPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.ECommaPOS _ecommapos = (net.lemonmodel.patterns.parser.Absyn.ECommaPOS) foo;
       render("ECommaPOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EConjunctionPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EConjunctionPOS _econjunctionpos = (net.lemonmodel.patterns.parser.Absyn.EConjunctionPOS) foo;
       render("EConjunctionPOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ECopulaPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.ECopulaPOS _ecopulapos = (net.lemonmodel.patterns.parser.Absyn.ECopulaPOS) foo;
       render("ECopulaPOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EDeterminerPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EDeterminerPOS _edeterminerpos = (net.lemonmodel.patterns.parser.Absyn.EDeterminerPOS) foo;
       render("EDeterminerPOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EInterjectionPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EInterjectionPOS _einterjectionpos = (net.lemonmodel.patterns.parser.Absyn.EInterjectionPOS) foo;
       render("EInterjectionPOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ENounPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.ENounPOS _enounpos = (net.lemonmodel.patterns.parser.Absyn.ENounPOS) foo;
       render("ENounPOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ENumeralPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.ENumeralPOS _enumeralpos = (net.lemonmodel.patterns.parser.Absyn.ENumeralPOS) foo;
       render("ENumeralPOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EParticlePOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EParticlePOS _eparticlepos = (net.lemonmodel.patterns.parser.Absyn.EParticlePOS) foo;
       render("EParticlePOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPointPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EPointPOS _epointpos = (net.lemonmodel.patterns.parser.Absyn.EPointPOS) foo;
       render("EPointPOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPostpositionPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EPostpositionPOS _epostpositionpos = (net.lemonmodel.patterns.parser.Absyn.EPostpositionPOS) foo;
       render("EPostpositionPOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPrepositionPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EPrepositionPOS _eprepositionpos = (net.lemonmodel.patterns.parser.Absyn.EPrepositionPOS) foo;
       render("EPrepositionPOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPronounPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EPronounPOS _epronounpos = (net.lemonmodel.patterns.parser.Absyn.EPronounPOS) foo;
       render("EPronounPOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EPunctuationPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EPunctuationPOS _epunctuationpos = (net.lemonmodel.patterns.parser.Absyn.EPunctuationPOS) foo;
       render("EPunctuationPOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ESemiColonPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.ESemiColonPOS _esemicolonpos = (net.lemonmodel.patterns.parser.Absyn.ESemiColonPOS) foo;
       render("ESemiColonPOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.ESlashPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.ESlashPOS _eslashpos = (net.lemonmodel.patterns.parser.Absyn.ESlashPOS) foo;
       render("ESlashPOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EVerbPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EVerbPOS _everbpos = (net.lemonmodel.patterns.parser.Absyn.EVerbPOS) foo;
       render("EVerbPOS");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EAnyPOS)
    {
       net.lemonmodel.patterns.parser.Absyn.EAnyPOS _eanypos = (net.lemonmodel.patterns.parser.Absyn.EAnyPOS) foo;
       render("(");
       render("EAnyPOS");
       sh(_eanypos.string_);
       render(")");
    }
  }

  private static void sh(net.lemonmodel.patterns.parser.Absyn.URI foo)
  {
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EQName)
    {
       net.lemonmodel.patterns.parser.Absyn.EQName _eqname = (net.lemonmodel.patterns.parser.Absyn.EQName) foo;
       render("(");
       render("EQName");
       sh(_eqname.ident_1);
       sh(_eqname.ident_2);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EQName2)
    {
       net.lemonmodel.patterns.parser.Absyn.EQName2 _eqname2 = (net.lemonmodel.patterns.parser.Absyn.EQName2) foo;
       render("(");
       render("EQName2");
       sh(_eqname2.ident_);
       render(")");
    }
    if (foo instanceof net.lemonmodel.patterns.parser.Absyn.EURI)
    {
       net.lemonmodel.patterns.parser.Absyn.EURI _euri = (net.lemonmodel.patterns.parser.Absyn.EURI) foo;
       render("(");
       render("EURI");
       sh(_euri.fulluri_);
       render(")");
    }
  }


  private static void pp(Integer n, int _i_) { buf_.append(n); buf_.append(" "); }
  private static void pp(Double d, int _i_) { buf_.append(d); buf_.append(" "); }
  private static void pp(String s, int _i_) { buf_.append(s); buf_.append(" "); }
  private static void pp(Character c, int _i_) { buf_.append("'" + c.toString() + "'"); buf_.append(" "); }
  private static void sh(Integer n) { render(n.toString()); }
  private static void sh(Double d) { render(d.toString()); }
  private static void sh(Character c) { render(c.toString()); }
  private static void sh(String s) { printQuoted(s); }
  private static void printQuoted(String s) { render("\"" + s + "\""); }
  private static void indent()
  {
    int n = _n_;
    while (n > 0)
    {
      buf_.append(" ");
      n--;
    }
  }
  private static void backup()
  {
     if (buf_.charAt(buf_.length() - 1) == ' ') {
      buf_.setLength(buf_.length() - 1);
    }
  }
  private static void trim()
  {
     while (buf_.length() > 0 && buf_.charAt(0) == ' ')
        buf_.deleteCharAt(0); 
    while (buf_.length() > 0 && buf_.charAt(buf_.length()-1) == ' ')
        buf_.deleteCharAt(buf_.length()-1);
  }
  private static int _n_ = 0;
  private static StringBuilder buf_ = new StringBuilder(INITIAL_BUFFER_SIZE);
}

