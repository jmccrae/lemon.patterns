package net.lemonmodel.patterns

import java.net.URI
import org.scalatest._
import org.scalatest.matchers._
import scala.xml._
import scala.xml.Utility._

class AdjectiveTest extends FlatSpec with ShouldMatchers {
  
  val defaultNamer = new URINamer {
    def apply(pos : String, form : String, element : Option[String] = None) = {
      URI.create("file:example/"+form+"-"+pos+(element match {
        case Some(elem) => "#" + elem
        case None => ""
      }))
    }
    def auxiliaryEntry(form : String, pos : String) = URI.create("file:example/"+form)
    def auxXML = Nil
    private val anonURIs = collection.mutable.Map[Any,URI]()
    private def randomID = (math.random * 10000000000l).toLong.toString
    def anonURI(ref : Any) = anonURIs.get(ref) match {
      case Some(x) => x
      case None => {
        val anon = URI.create("file:example/" + randomID)
        anonURIs.put(ref,anon)
        anon
      }
    }
  }
  
  def xmlCheck(pattern : Pattern, xml : Elem) {
    trim(pattern.toXML(defaultNamer,"en")).toString() should equal (trim(xml).toString())
  }
  val ontology = Namespace("http://www.example.com/ontology#")
  
  "Intersective class adjective pattern" should "produce valid lemon" in {
    
    xmlCheck(IntersectiveAdjective("Belgian",ontology("Belgian")),
       <lemon:LexicalEntry rdf:about="file:example/Belgian-adjective">
         <lemon:canonicalForm>
           <lemon:Form rdf:about="file:example/Belgian-adjective#canonicalForm">
             <lemon:writtenRep xml:lang="en">Belgian</lemon:writtenRep>
           </lemon:Form>
         </lemon:canonicalForm>
         <lexinfo:partOfSpeech rdf:resource="http://www.lexinfo.net/ontology/2.0/lexinfo#adjective"></lexinfo:partOfSpeech>
         <lemon:sense>
           <lemon:LexicalSense rdf:about="file:example/Belgian-adjective#sense">
             <lemon:reference>
               <owl:Class rdf:about="http://www.example.com/ontology#Belgian"/>
             </lemon:reference>
             <lemon:isA>
               <lemon:Argument rdf:about="file:example/Belgian-adjective#subject"/>
             </lemon:isA>
           </lemon:LexicalSense>
         </lemon:sense>
         <lemon:synBehavior>
           <lemon:Frame rdf:about="file:example/Belgian-adjective#predFrame">
             <rdf:type rdf:resource="http://www.lexinfo.net/ontology/2.0/lexinfo#AdjectivePredicativeFrame"/>
             <lexinfo:copulativeSubject rdf:resource="file:example/Belgian-adjective#subject"/>
           </lemon:Frame>
         </lemon:synBehavior>
         <lemon:synBehavior>
           <lemon:Frame rdf:about="file:example/Belgian-adjective#attrFrame">
             <rdf:type rdf:resource="http://www.lexinfo.net/ontology/2.0/lexinfo#AdjectiveAttributiveFrame"/>
             <lexinfo:attributiveArg rdf:resource="file:example/Belgian-adjective#subject"/>
           </lemon:Frame>
         </lemon:synBehavior>
       </lemon:LexicalEntry>)
  }
}
