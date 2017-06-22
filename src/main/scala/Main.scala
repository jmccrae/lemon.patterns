package net.lemonmodel.patterns

import java.io._
import net.lemonmodel.patterns.parser._

object ConvertPatterns {
  def main(args : Array[String]) {
    val l = try {
      if(args.length == 0) {
/*      Seems broken obviously due to Yylex bug
        val in = new BufferedReader(new InputStreamReader(System.in))
        val buf = new StringBuffer()
        var s = ""
        while({s = in.readLine();s} != null) {
          buf.append(s + System.getProperty("line.separator"))
        }
        System.err.println(buf.toString())
        new Yylex(new StringReader(buf.toString()))*/
       System.err.println("Please specify an input file")
       return
      } else if(args.length == 1) {
        new Yylex(new FileReader(args(0)))
      } else if(args.length == 2 && args(0) == "--lemon") {
        new Yylex(new FileReader(args(1)))
      } else {
       System.err.println("Please specify an input file")
       return
      }
    } catch {
      case x : FileNotFoundException => {
        System.err.println("Error: File not found: " + args(0));
        System.exit(1)
        null
      }
    }
    val p = new parser(l);
    try
    {
      val ontolex = (args.length == 0)
      val parse_tree = p.pStatements();
      val visitor = new PatternVisitor();
      val lexicons = parse_tree.accept(visitor,collection.mutable.Map[String,String]())
      if(args.length == 1) {
        println(WriteAsRDF.apply(for(lexicon <- lexicons) yield { if(ontolex) { lexicon.toOntoLexXML() } else { lexicon.toXML() } }))
      } else {
        val out = new PrintWriter(args(1))
        out.println(WriteAsRDF.apply(for(lexicon <- lexicons) yield { if(ontolex) { lexicon.toOntoLexXML() } else { lexicon.toXML() } }))
        out.flush
        out.close
      }
    }
    catch
    {
      case (e : Throwable) => {
        System.err.println("At line " + String.valueOf(l.line_num()) + ", near \"" + l.buff() + "\" :");
        System.err.println("     " + e.getMessage());
        System.exit(1);
      }
    }
  }
}
