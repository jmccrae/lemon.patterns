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

  "consequence verb" should "parse" in {
    val l = new Yylex(new StringReader("""@prefix dbpedia: <http://dbpedia.org/resource/> .
      @prefix lex: <http://www.example.com/lex#> .

      Lexicon(<test>,"eng", ConsequenceVerb("write",dbpedia:programmingLanguage,
       propSubj = lex:software as Subject,
       propObj  = lex:language as PrepositionalObject("in")))"""))
    val p = new parser(l)
    val parse_tree = p.pStatements();
    val visitor = new PatternVisitor();
    val lexicons = parse_tree.accept(visitor,collection.mutable.Map[String,String]())
    val x = WriteAsRDF.apply(for(lexicon <- lexicons) yield { lexicon.toXML() })
    x should equal("""<rdf:RDF 
xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:owl="http://www.w3.org/2002/07/owl#" xmlns:xsd="http://www.w3.org/2001/XMLSchema#" xmlns:lemon="http://lemon-model.net/lemon#" xmlns:oils="http://lemon-model.net/oils#" xmlns:lexinfo="http://www.lexinfo.net/ontology/2.0/lexinfo#" xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#">
  <lemon:Lexicon lemon:language="eng" rdf:about="test">
    <lemon:entry>
      <lemon:LexicalEntry rdf:about="test#write__verb">
        <lemon:canonicalForm>
          <lemon:Form rdf:about="test#write__verb/canonicalForm">
            <lemon:writtenRep xml:lang="eng">write</lemon:writtenRep>
          </lemon:Form>
        </lemon:canonicalForm>
        <lexinfo:partOfSpeech 
        rdf:resource="http://www.lexinfo.net/ontology/2.0/lexinfo#verb">
</lexinfo:partOfSpeech>
        <lemon:sense>
          <lemon:LexicalSense rdf:about="test#write__verb/sense">
            <lemon:subsense>
              <lemon:LexicalSense rdf:about="test#write__verb/sense1">
                <lemon:reference>
                  <rdf:Property rdf:about="http://www.example.com/lex#software"></rdf:Property>
                </lemon:reference>
                <lemon:objOfProp>
                  <lemon:Argument rdf:about="test#write__verb/subject"></lemon:Argument>
                </lemon:objOfProp>
              </lemon:LexicalSense>
            </lemon:subsense>
            <lemon:subsense>
              <lemon:LexicalSense rdf:about="test#write__verb/sense2">
                <lemon:reference>
                  <rdf:Property rdf:about="http://www.example.com/lex#language"></rdf:Property>
                </lemon:reference>
                <lemon:objOfProp>
                  <lemon:Argument rdf:about="test#write__verb/object"></lemon:Argument>
                </lemon:objOfProp>
              </lemon:LexicalSense>
            </lemon:subsense>
          </lemon:LexicalSense>
        </lemon:sense>
        <lemon:sense>
          <lemon:LexicalSense rdf:about="test#write__verb/sense3">
            <lemon:reference>
              <rdf:Property rdf:about="http://dbpedia.org/resource/programmingLanguage">
                <owl:propertyChainAxiom rdf:parseType="Collection">
                  <rdf:Description>
                    <owl:inverseOf rdf:resource="http://www.example.com/lex#software">
                    </owl:inverseOf>
                  </rdf:Description>
                  <rdf:Description rdf:about="http://www.example.com/lex#language">
                  </rdf:Description>
                </owl:propertyChainAxiom>
              </rdf:Property>
            </lemon:reference>
            <lemon:subjOfProp rdf:resource="test#write__verb/subject"></lemon:subjOfProp>
            <lemon:objOfProp rdf:resource="test#write__verb/object"></lemon:objOfProp>
          </lemon:LexicalSense>
        </lemon:sense>
        <lemon:synBehavior>
          <lemon:Frame rdf:about="test#write__verb/frame">
            <rdf:type 
            rdf:resource="http://www.lexinfo.net/ontology/2.0/lexinfo#IntransitivePPFrame">
</rdf:type>
            <lexinfo:subject rdf:resource="test#write__verb/subject"></lexinfo:subject>
            <lexinfo:prepositionalObject>
              <lemon:Argument rdf:about="test#write__verb/object">
                <lemon:marker rdf:resource="test#in__preposition"></lemon:marker>
              </lemon:Argument>
            </lexinfo:prepositionalObject>
          </lemon:Frame>
        </lemon:synBehavior>
      </lemon:LexicalEntry>
    </lemon:entry>
    <lemon:entry>
      <lemon:LexicalEntry rdf:about="test#in__preposition">
        <lexinfo:partOfSpeech 
        rdf:resource="http://www.lexinfo.net/ontology/2.0/lexinfo#preposition">
</lexinfo:partOfSpeech>
        <lemon:canonicalForm>
          <lemon:Form rdf:about="test#in__preposition/canonicalForm">
            <lemon:writtenRep xml:lang="eng">in</lemon:writtenRep>
          </lemon:Form>
        </lemon:canonicalForm>
      </lemon:LexicalEntry>
    </lemon:entry>
  </lemon:Lexicon>
</rdf:RDF>
""")
  }

  "consequence verb" should "work in short form" in {
   val l = new Yylex(new StringReader("""@prefix dbpedia: <http://dbpedia.org/resource/> .
      @prefix lex: <http://www.example.com/lex#> .

      Lexicon(<test>,"eng", 
        ConsequenceVerb("bear",dbpedia:birthYear,
         propObj = PrepositionalObject("in")))"""))
    val p = new parser(l)
    val parse_tree = p.pStatements();
    val visitor = new PatternVisitor();
    val lexicons = parse_tree.accept(visitor,collection.mutable.Map[String,String]())
    val x = WriteAsRDF.apply(for(lexicon <- lexicons) yield { lexicon.toXML() })
  }

  "head pattern" should "Generates a head" in {
    val l = new Yylex(new StringReader("Lexicon(<test>,\"deu\",ClassNoun([\"siamesisch\"/adjective \"Zwilling\"/noun=head],<test>))"))
    val p = new parser(l);
    try {
      val parse_tree = p.pStatements();
      val visitor = new PatternVisitor()
      val x = parse_tree.accept(visitor, collection.mutable.Map[String,String]())
      x(0).patterns(0).asInstanceOf[ClassNoun].lemma.head should not be (None)
    } catch {
      case (e : Throwable) => {
        System.err.println("At line " + String.valueOf(l.line_num()) + ", near \"" + l.buff() + "\" :");
        throw e
      }
    }
  }


}

