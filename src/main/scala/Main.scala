package net.lemonmodel.patterns

import java.io._
import net.lemonmodel.patterns.parser._

object ConvertPatterns {
  def main(args : Array[String]) {
    val ontolex = !args.exists(_ == "--lemon")
    val _args = args.filter(_ != "--lemon")
    if(_args.length == 0) {
       System.err.println("Please specify an input file")
       return
    }
    val inputFile = _args(0)
    val outputFile = _args.tail.headOption
    val l = try {
/*      Seems broken obviously due to Yylex bug
        val in = new BufferedReader(new InputStreamReader(System.in))
        val buf = new StringBuffer()
        var s = ""
        while({s = in.readLine();s} != null) {
          buf.append(s + System.getProperty("line.separator"))
        }
        System.err.println(buf.toString())
        new Yylex(new StringReader(buf.toString()))*/
        new Yylex(new FileReader(inputFile))
    } catch {
      case x : FileNotFoundException => {
        System.err.println("Error: File not found: " + args(0));
        System.exit(1)
        return
      }
    }
    val p = new parser(l);
    try
    {
      val parse_tree = p.pStatements();
      val visitor = new PatternVisitor();
      val lexicons = parse_tree.accept(visitor,collection.mutable.Map[String,String]())
      outputFile match {
        case None =>
          println(WriteAsRDF.apply(for(lexicon <- lexicons) yield { if(ontolex) { lexicon.toOntoLexXML() } else { lexicon.toXML() } },ontolex))
        case Some(f) =>
          val out = new PrintWriter(f)
          out.println(WriteAsRDF.apply(for(lexicon <- lexicons) yield { if(ontolex) { lexicon.toOntoLexXML() } else { lexicon.toXML() } },ontolex))
        out.flush
        out.close
      }
    }
    catch
    {
      case (e : Throwable) => {
        e.printStackTrace()
        System.err.println("At line " + String.valueOf(l.line_num()) + ", near \"" + l.buff() + "\" :");
        System.err.println("     " + e.getMessage());
        System.exit(1);
      }
    }
  }
}
