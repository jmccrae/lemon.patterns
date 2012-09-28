package net.lemonmodel {

  import java.net.URI
  import scala.xml._
  import net.lemonmodel.rdfutil.RDFUtil._
 
  package object patterns {
    implicit def pimpStr(uri : String) = new {
      def as(arg : Arg) : OntologyFrameElement = OntologyFrameElement(URI.create(uri), arg)
      def greaterThan(boundary : Double) : ScalarMembership = ScalarMembership(URI.create(uri),boundary,positive)
      def lessThan(boundary : Double) : ScalarMembership = ScalarMembership(URI.create(uri),boundary,negative)
    }
    implicit def pimpStr(uri : URI) = new {
      def as(arg : Arg) : OntologyFrameElement = OntologyFrameElement(uri, arg)
      def greaterThan(boundary : Double) : ScalarMembership = ScalarMembership(uri,boundary,positive)
      def lessThan(boundary : Double) : ScalarMembership = ScalarMembership(uri,boundary,negative)
    }
    
    implicit def str2URI(uri : String) : URI = URI.create(uri)
    implicit def uriToStr(uri : URI) : String = uri.toString()
        
    val lexinfo = Namespace("http://lexinfo.net/ontology/2.0/lexinfo#")
  }
  
  package patterns {
    case class Namespace(prefix : String) {
      def apply(frag : String) : URI = URI.create(prefix + frag)
    }
    
    trait URINamer {
      def apply(pos : String, form : String, element : Option[String] = None) : URI
      def auxiliaryEntry(form : String) : URI
    }
    
    trait Pattern {
      def toXML(namer : URINamer, lang : String)  : Node
    }

    /**
    * An other (non-canonical) form of a word
    * @param writtenRep The string form of the word
    * @param props The properties of the form, as defined by a linguistic ontology
    *   such as LexInfo
    */
    case class Form(val writtenRep : String, val props : Map[URI,URI])

    /**
    * An argument in a frame
    */
    trait Arg {
      def toXML(uri : URI, namer : URINamer) : NodeSeq
    }

    /**
    * The subject of a verb or the copulative in noun/adjective predicative frame
    */
    object Subject extends Arg {
      def toXML(uri : URI, namer : URINamer) = <lexinfo:subject rdf:resource={uri}/>
    }
    
    /**
    * The direct object of a verb
    */
    object DirectObject extends Arg {
      def toXML(uri : URI, namer : URINamer) = <lexinfo:directObject rdf:resource={uri}/>
    }
    
    /**
    * The indirect object of a verb
    */
    object IndirectObject extends Arg {
      def toXML(uri : URI, namer : URINamer) = <lexinfo:indirectObject rdf:resource={uri}/>
    }

    trait AdpositionalObject extends Arg 
    
    /**
    * A prepositional object in a frame
    */
    case class PrepositionalObject(preposition : String) extends AdpositionalObject {
      def toXML(uri : URI, namer : URINamer) = <lexinfo:prepositionalObject>
                  <lemon:Argument rdf:about={uri}>
                    <lemon:marker rdf:resource={namer.auxiliaryEntry(preposition)}/>
                  </lemon:Argument>
                </lexinfo:prepositionalObject>
    }
    
    /**
     * A postposition object in a frame
     */
    case class PostpositionalObject(postposition : String) extends AdpositionalObject  {
      def toXML(uri : URI, namer : URINamer) = <lexinfo:postpositionalObject>
                  <lemon:Argument rdf:about={uri}>
                    <lemon:marker rdf:resource={namer.auxiliaryEntry(postposition)}/>
                  </lemon:Argument>
                </lexinfo:postpositionalObject>
    }
    
    object PossessiveAdjunct extends Arg {
      def toXML(uri : URI, namer : URINamer) = <lexinfo:possessiveAdjunct rdf:resource={uri}/>
    }
    
    /**
     * An element of a multivalent frame in the ontology. Normally constructed
     * using the {@code as} implicit, e.g., {@code "http://www.example.com/ontology#property" as Subject}
     */
     case class OntologyFrameElement(val property : URI, val arg : Arg, val isOptional : Boolean = false) {
       def optional = OntologyFrameElement(property,arg,true)
     }
      
     
    sealed trait Direction
    object positive extends Direction
    object negative extends Direction
                                   
    case class ScalarMembership(val property : URI,
                                val boundary : Double,
                                val direction : Direction)
  }
}
