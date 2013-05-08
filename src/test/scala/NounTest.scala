package net.lemonmodel.patterns

import java.net.URI
import org.scalatest._
import org.scalatest.matchers._
import scala.xml._
import scala.xml.Utility._

class NounTest extends FlatSpec with ShouldMatchers {
  
  val defaultNamer = new URINamer {
    def apply(pos : String, form : String, element : Option[String] = None) = {
      URI.create("file:example/"+form+"-"+pos+(element match {
        case Some(elem) => "#" + elem
        case None => ""
      }))
    }
    def auxiliaryEntry(form : String, pos : String) = URI.create("file:example/"+form)
    def auxXML = Nil
  }
  
  def xmlCheck(pattern : Pattern, xml : Elem) {
    trim(pattern.toXML(defaultNamer,"en")).toString() should equal (trim(xml).toString())
  }
  
  "The name pattern" should "produce valid lemon" in {
    xmlCheck(Name("Microsoft","http://microsoft.com"),
       <lemon:LexicalEntry rdf:about="file:example/Microsoft-noun">
         <lemon:canonicalForm>
           <lemon:Form rdf:about="file:example/Microsoft-noun#canonicalForm">
             <lemon:writtenRep xml:lang="en">Microsoft</lemon:writtenRep>
           </lemon:Form>
         </lemon:canonicalForm>
         <lexinfo:partOfSpeech rdf:resource="http://lexinfo.net/ontology/2.0/lexinfo#properNoun"></lexinfo:partOfSpeech>
         <lemon:sense>
           <lemon:LexicalSense rdf:about="file:example/Microsoft-noun#sense">
             <lemon:reference>
               <owl:NamedIndividual rdf:about="http://microsoft.com"/>
             </lemon:reference>
           </lemon:LexicalSense>
         </lemon:sense>
       </lemon:LexicalEntry>)
  }
  
  val dbpedia = Namespace("http://dbpedia.org/resource/")
  
  "The class noun pattern" should "produce valid lemon" in {
    xmlCheck(ClassNoun("cat",dbpedia("Cat")) withPlural "cats",
       <lemon:LexicalEntry rdf:about="file:example/cat-noun">
         <lemon:canonicalForm>
           <lemon:Form rdf:about="file:example/cat-noun#canonicalForm">
             <lemon:writtenRep xml:lang="en">cat</lemon:writtenRep>
           </lemon:Form>
         </lemon:canonicalForm>
         <lexinfo:partOfSpeech rdf:resource="http://lexinfo.net/ontology/2.0/lexinfo#commonNoun"></lexinfo:partOfSpeech>
         <lemon:otherForm>
           <lemon:Form rdf:about="file:example/cat-noun#form">
             <lemon:writtenRep xml:lang="en">cats</lemon:writtenRep>
             <lexinfo:number rdf:resource="http://lexinfo.net/ontology/2.0/lexinfo#plural" xmlns:lexinfo="http://lexinfo.net/ontology/2.0/lexinfo#"></lexinfo:number>
           </lemon:Form>
         </lemon:otherForm>
         <lemon:sense>
           <lemon:LexicalSense rdf:about="file:example/cat-noun#sense">
             <lemon:reference>
               <owl:Class rdf:about="http://dbpedia.org/resource/Cat"/>
             </lemon:reference>
             <lemon:isA>
               <lemon:Argument rdf:about="file:example/cat-noun#subject"/>
             </lemon:isA>
           </lemon:LexicalSense>
         </lemon:sense>
         <lemon:synBehavior>
           <lemon:Frame rdf:about="file:example/cat-noun#frame">
             <rdf:type rdf:resource="http://lexinfo.net/ontology/2.0/lexinfo#NounPredicativeFrame"/>
             <lexinfo:subject rdf:resource="file:example/cat-noun#subject"/>
           </lemon:Frame>
         </lemon:synBehavior>
       </lemon:LexicalEntry>)
  }
  
  val ontology = Namespace("http://www.example.com/ontology#")
  
  "The relation noun pattern" should "produce valid lemon" in {
    xmlCheck(RelationalNoun("agreement",ontology("agreeAbout"),
                 propSubj=CopulativeArg,
                 propObj=PrepositionalObject("about")),
       <lemon:LexicalEntry rdf:about="file:example/agreement-noun">
         <lemon:canonicalForm>
           <lemon:Form rdf:about="file:example/agreement-noun#canonicalForm">
             <lemon:writtenRep xml:lang="en">agreement</lemon:writtenRep>
           </lemon:Form>
         </lemon:canonicalForm>
         <lexinfo:partOfSpeech rdf:resource="http://lexinfo.net/ontology/2.0/lexinfo#commonNoun"></lexinfo:partOfSpeech>
         <lemon:sense>
           <lemon:LexicalSense rdf:about="file:example/agreement-noun#sense">
             <lemon:reference>
               <rdf:Property rdf:about="http://www.example.com/ontology#agreeAbout"/>
             </lemon:reference>
             <lemon:subjOfProp>
               <lemon:Argument rdf:about="file:example/agreement-noun#subject"/>
             </lemon:subjOfProp>
             <lemon:objOfProp>
               <lemon:Argument rdf:about="file:example/agreement-noun#adpositionalObject"/>
             </lemon:objOfProp>
           </lemon:LexicalSense>
         </lemon:sense>
         <lemon:synBehavior>
           <lemon:Frame rdf:about="file:example/agreement-noun#frame">
             <rdf:type rdf:resource="http://lexinfo.net/ontology/2.0/lexinfo#NounPPFrame"/>
             <lexinfo:copulativeArg rdf:resource="file:example/agreement-noun#subject"/>
             <lexinfo:prepositionalObject>
               <lemon:Argument rdf:about="file:example/agreement-noun#adpositionalObject">
                 <lemon:marker rdf:resource="file:example/about"/>
               </lemon:Argument>
             </lexinfo:prepositionalObject>
           </lemon:Frame>
         </lemon:synBehavior>
       </lemon:LexicalEntry>)
  }
  
  "The multivalent relational noun pattern" should "produce valid lemon" in {
    xmlCheck(RelationalMultivalentNoun("position",ontology("Employment"),
              args=Seq(ontology("employee") as PossessiveAdjunct,
                       ontology("role") as PrepositionalObject("as") optional,
                       ontology("startOfEmployment") as PrepositionalObject("since") optional)),
       <lemon:LexicalEntry rdf:about="file:example/position-noun">
         <lemon:canonicalForm>
           <lemon:Form rdf:about="file:example/position-noun#canonicalForm">
             <lemon:writtenRep xml:lang="en">position</lemon:writtenRep>
           </lemon:Form>
         </lemon:canonicalForm>
         <lexinfo:partOfSpeech rdf:resource="http://lexinfo.net/ontology/2.0/lexinfo#commonNoun"></lexinfo:partOfSpeech>
         <lemon:sense>
           <lemon:LexicalSense rdf:about="file:example/position-noun#sense">
             <lemon:reference>
               <rdfs:Class rdf:about="http://www.example.com/ontology#Employment">
                 <rdfs:subClassOf rdf:resource="http://www.lemon-model.net/oils#Relationship"/>
               </rdfs:Class>
             </lemon:reference>
             <lemon:subsense>
               <lemon:LexicalSense rdf:about="file:example/position-noun#subsense1">
                 <lemon:objOfProp>
                   <lemon:Argument rdf:about="file:example/position-noun#arg1">
                   <!-- Mandatory argument -->
                   </lemon:Argument>
                 </lemon:objOfProp>
                 <lemon:reference>
                   <rdf:Property rdf:about="http://www.example.com/ontology#employee"/>
                 </lemon:reference>
               </lemon:LexicalSense>
             </lemon:subsense>
             <lemon:subsense>
               <lemon:LexicalSense rdf:about="file:example/position-noun#subsense2">
                 <lemon:objOfProp>
                   <lemon:Argument rdf:about="file:example/position-noun#arg2">
                    <lemon:optional rdf:datatype="http://www.w3.org/2001/XMLSchema#boolean">true</lemon:optional>
                   </lemon:Argument>
                 </lemon:objOfProp>
                 <lemon:reference>
                   <rdf:Property rdf:about="http://www.example.com/ontology#role"/>
                 </lemon:reference>
               </lemon:LexicalSense>
             </lemon:subsense>
             <lemon:subsense>
               <lemon:LexicalSense rdf:about="file:example/position-noun#subsense3">
                 <lemon:objOfProp>
                   <lemon:Argument rdf:about="file:example/position-noun#arg3">
                    <lemon:optional rdf:datatype="http://www.w3.org/2001/XMLSchema#boolean">true</lemon:optional>
                   </lemon:Argument>
                 </lemon:objOfProp>
                 <lemon:reference>
                   <rdf:Property rdf:about="http://www.example.com/ontology#startOfEmployment"/>
                 </lemon:reference>
               </lemon:LexicalSense>
             </lemon:subsense>
           </lemon:LexicalSense>
         </lemon:sense>
         <lemon:synBehavior>
           <lemon:Frame rdf:about="file:example/position-noun#frame">
             <lexinfo:possessiveAdjunct rdf:resource="file:example/position-noun#arg1"/>
             <lexinfo:prepositionalObject>
               <lemon:Argument rdf:about="file:example/position-noun#arg2">
                 <lemon:marker rdf:resource="file:example/as"/>
                 <lemon:optional rdf:datatype="http://www.w3.org/2001/XMLSchema#boolean">true</lemon:optional>
               </lemon:Argument>
             </lexinfo:prepositionalObject>
             <lexinfo:prepositionalObject>
               <lemon:Argument rdf:about="file:example/position-noun#arg3">
                 <lemon:marker rdf:resource="file:example/since"/>
                 <lemon:optional rdf:datatype="http://www.w3.org/2001/XMLSchema#boolean">true</lemon:optional>
               </lemon:Argument>
             </lexinfo:prepositionalObject>
           </lemon:Frame>
         </lemon:synBehavior>
       </lemon:LexicalEntry>)
  }
}
