package net.lemonmodel.patterns

import net.lemonmodel.patterns.parser._
import java.io.FileReader
import java.io.StringReader
import java.net.URI
import org.scalatest._
import org.scalatest.matchers._
import scala.xml._
import scala.xml.Utility._

class BNFCTest extends FlatSpec with ShouldMatchers {
  "the test file" should "parse" in {
    val l = new Yylex(new FileReader("src/test/resources/dbpedia-places.ldp"))
    val p = new parser(l);
    try {
      val parse_tree = p.pStatements();
    parse_tree should not be (null)
    } catch {
      case (e : Throwable) => {
        System.err.println("At line " + String.valueOf(l.line_num()) + ", near \"" + l.buff() + "\" :");
        throw e
      }
    }
  }
  
  "the test file" should "validate" in {
    val l = new Yylex(new FileReader("src/test/resources/dbpedia-places.ldp"))
    val p = new parser(l);
    try {
      val parse_tree = p.pStatements();
      val visitor = new PatternVisitor()
      val x = parse_tree.accept(visitor, collection.mutable.Map[String,String]())
      x should not be (null)
      x.size should be (1)
    } catch {
      case (e : Throwable) => {
        System.err.println("At line " + String.valueOf(l.line_num()) + ", near \"" + l.buff() + "\" :");
        throw e
      }
    }
  }
  
  "the readme examples" should "validate" in {
    val l = new Yylex(new FileReader("src/test/resources/readme-examples.ldp"))
    val p = new parser(l);
    try {
      val parse_tree = p.pStatements();
      val visitor = new PatternVisitor()
      val x = parse_tree.accept(visitor, collection.mutable.Map[String,String]())
      x should not be (null)
      x.size should be (2)
    } catch {
      case (e : Throwable) => {
        System.err.println("At line " + String.valueOf(l.line_num()) + ", near \"" + l.buff() + "\" :");
        throw e
      }
    }
  }
  
  "another example" should "produce ipp frame" in {
    val l = new Yylex(new StringReader("Lexicon(<test>,\"nl\",StateVerb([\"vereist\"/verb \"inplanning\"/adverb], <http://www.beinformed.nl/owl/ontology#requiresScheduled>, propObj=PrepositionalObject(\"van\")))"))
    val p = new parser(l);
    try {
      val parse_tree = p.pStatements();
      val visitor = new PatternVisitor()
      val x = parse_tree.accept(visitor, collection.mutable.Map[String,String]())
      x should not be (null)
      x.size should be (1)
      x(0).patterns(0).asInstanceOf[StateVerb].propSubj should be (Subject)
      x(0).patterns(0).asInstanceOf[StateVerb].propObj should be (PrepositionalObject("van"))
    } catch {
      case (e : Throwable) => {
        System.err.println("At line " + String.valueOf(l.line_num()) + ", near \"" + l.buff() + "\" :");
        throw e
      }
    }
  }
  
  "utf8" should "work" in {
    val l = new Yylex(new StringReader("Lexicon(<test>,\"deu\",ClassNoun(\"\u00dcbung\",<uebung>))"))
    val p = new parser(l);
    try {
      val parse_tree = p.pStatements();
      val visitor = new PatternVisitor()
      val x = parse_tree.accept(visitor, collection.mutable.Map[String,String]())
      x(0).patterns(0).asInstanceOf[ClassNoun].lemma.toString should be ("\u00dcbung")
    } catch {
      case (e : Throwable) => {
        System.err.println("At line " + String.valueOf(l.line_num()) + ", near \"" + l.buff() + "\" :");
        throw e
      }
    }
    
  }
}

