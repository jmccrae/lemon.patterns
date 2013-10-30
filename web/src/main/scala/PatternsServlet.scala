package net.lemonmodel.patterns.web

import java.io._
import javax.servlet._
import javax.servlet.http._
import net.lemonmodel.patterns._
import net.lemonmodel.patterns.parser._

class PatternsServlet extends HttpServlet {
  override def doGet(req : HttpServletRequest, resp : HttpServletResponse) {
    val pattern = req.getParameter("pattern")
    if(pattern == null) {
      resp.sendError(400,"No input")
      return
    }
    val l = try {
      new Yylex(new StringReader(pattern))
    } catch {
      case x : FileNotFoundException => {
        resp.sendError(400,"Lex Error")
        return
      }
    }
    val p = new parser(l);
    try {
      val parse_tree = p.pStatements();
      val visitor = new PatternVisitor();
      val lexicons = parse_tree.accept(visitor,collection.mutable.Map[String,String]())
      val out = resp.getWriter()
      out.println(WriteAsRDF.apply(for(lexicon <- lexicons) yield { lexicon.toXML() }))
      out.flush
      out.close
    } catch {
      case (e : Throwable) => {
        System.err.println("At line " + String.valueOf(l.line_num()) + ", near \"" + l.buff() + "\" :");
        System.err.println("     " + e.getMessage());
        resp.sendError(400,"Parse Error" + pattern)
      }
    }
  }

  override def doPost(req : HttpServletRequest, resp : HttpServletResponse) { doGet(req,resp) }
}

