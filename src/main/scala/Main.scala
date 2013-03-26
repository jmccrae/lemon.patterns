package net.lemonmodel.patterns

import java.io._
import net.lemonmodel.patterns.parser._

object ConvertPatterns {
  def main(args : Array[String]) {
    val l = try {
      if(args.length == 0) {
        new Yylex(System.in)
      } else {
        new Yylex(new FileReader(args(0)))
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
      val parse_tree = p.pStatements();
      val visitor = new PatternVisitor();
      val lexicons = parse_tree.accept(visitor,collection.mutable.Map[String,String]())
      if(args.length == 1) {
        println(WriteAsRDF.apply(for(lexicon <- lexicons) yield { lexicon.toXML() }))
      } else {
        val out = new PrintWriter(args(1))
        out.println(WriteAsRDF.apply(for(lexicon <- lexicons) yield { lexicon.toXML() }))
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
