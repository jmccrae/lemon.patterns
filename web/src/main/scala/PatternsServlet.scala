package net.lemonmodel.patterns.web

import java.io._
import javax.servlet._
import javax.servlet.http._
import net.lemonmodel.patterns._
import net.lemonmodel.patterns.parser._

class PatternsServlet extends HttpServlet {
  override def service(req : HttpServletRequest, resp : HttpServletResponse) {
    req.getPathInfo() match {
      case "/" => 
        val pattern = req.getParameter("pattern")
        if(pattern == null) {
          resp.setStatus(200)
          resp.setContentType("text/html")
          val out = resp.getWriter()
          out.println(page)
          out.flush
          out.close
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
          resp.setStatus(200)
          resp.setContentType("application/rdf+xml")
          val out = resp.getWriter()
          out.println(WriteAsRDF.apply(for(lexicon <- lexicons) yield { lexicon.toXML() }))
          out.flush
          out.close
        } catch {
          case (e : Throwable) => {
            resp.setStatus(400)
            resp.setContentType("text/html")
            val out = resp.getWriter()
            out.write(error(e,l,pattern).toString)
            out.flush
            out.close
            return
          }
        }
      case "/lemonpatterns/bootstrap.min.css" =>
        sendFile("/static/bootstrap.min.css", resp)
      case "/bootstrap.min.css" =>
        sendFile("/static/bootstrap.min.css", resp)
      case otherPath =>
        println("404: " + otherPath)
        resp.sendError(404)
    }
  }

  def sendFile(name : String, response : HttpServletResponse) {
    val path = getServletContext().getRealPath(name)
    
    val file = if(path != null) {
      new File(path)
    } else {
      throw new Exception("Could not translate " + name)
    }
    
    val input = getServletContext().getResourceAsStream(name)
    if(input == null) {
      response.sendError(404, "Could not open resource")
      return
    }
    val DEFAULT_BUFFER_SIZE = 10240
    val contentType = Option(getServletContext().getMimeType(name)).getOrElse("application/octet-stream")
    response.reset();
    response.setBufferSize(DEFAULT_BUFFER_SIZE);
    response.setContentType(contentType);
    if(file != null) {
      response.setHeader("Content-Length", String.valueOf(file.length()));
      response.setDateHeader("Last-Modified", file.lastModified())
    }

    // Prepare streams.
    var output : BufferedOutputStream = null;

    try {
      // Open streams.
      output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

      // Write file contents to response.
      val buffer = new Array[Byte](DEFAULT_BUFFER_SIZE)
      var length = 0
      while ({length = input.read(buffer);length} > 0) {
        output.write(buffer, 0, length);
      }
    } finally {
      // Gently close streams.
      input.close()
      if(output != null) {
        output.close()
      }
    }
  }



  def page = {
    <html>
      <head>
        <title>Lemon Design Patterns Compiler</title>
        <link href="http://fonts.googleapis.com/css?family=Source+Code+Pro" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="/lemonpatterns/bootstrap.min.css"/>
      </head>
      <body>
        <div class="container">
          <div class="row">
            <h1>Lemon Design Patterns</h1>
            <div class="well">
              <form method="post" class="form-horizontal">
                <fieldset>
                  <legend>Pattern</legend>
                  <div class="form-group">
                    <div class="col-lg-11">
                      <textarea class="form-control" name="pattern" rows="40" cols="80" style="font-family: Source Code Pro;">
Lexicon(&lt;http://www.example.org/lexicon&gt;, "en",
  ClassNoun("test",&lt;http://www.example.org/ontology#Test&gt;)
)</textarea>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-lg-offset-11">
                      <input class="btn btn-primary" type="submit" value="Submit"/>
                    </div>
                  </div>
                </fieldset>
              </form>
            </div>
          </div>
          <div class="row">
            <p>For documentation please see <a href="https://github.com/jmccrae/lemon.patterns">project at GitHub</a></p>
            <p>&copy; 2014. This software was developed by John P. M<sup>c</sup>Crae for the 
            <a href="http://www.lider-project.eu/">LIDER</a> Project and is available under the Apache License</p>
          </div>
        </div>
      </body>
    </html>
  }

  def error(e : Throwable, l : Yylex, document : String) = {
    <html>
      <head>
        <title>Lemon Design Patterns Compiler</title>
        <link href="http://fonts.googleapis.com/css?family=Source+Code+Pro" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="/lemonpatterns/bootstrap.min.css"/>
      </head>
      <body>
        <div class="container">
          <div class="row">
            <div class="page-header">
              <h1>An error occurred</h1>
            </div>
            <h3>{"At line " + String.valueOf(l.line_num()) + ", near \"" + l.buff() + "\" :"}</h3>
            <div class="alert alert-danger">{
              e.getMessage().split("\n").map(text => <p>{text}</p>)
            }</div>
            <h3>Original document</h3>
            <table style="font-family: Source Code Pro;">{
              document.replaceAll(" ","\u00a0").split("\n").zipWithIndex.map{
                case (text,id) => <tr><td style="width:30px;text-align:right;color:#ccc;padding-right:3px;">{id+1}</td><td>{text}</td></tr>
              }
            }</table>
          </div>
        </div>
      </body>
    </html>
  }
}

