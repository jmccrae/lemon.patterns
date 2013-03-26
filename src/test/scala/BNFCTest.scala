package net.lemonmodel.patterns

import net.lemonmodel.patterns.parser._
import java.io.FileReader
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
      x.size should be (1)
    } catch {
      case (e : Throwable) => {
        System.err.println("At line " + String.valueOf(l.line_num()) + ", near \"" + l.buff() + "\" :");
        throw e
      }
    }
  }
}

