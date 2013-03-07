package net.lemonmodel {

  import java.net.URI
  import scala.xml._
  import net.lemonmodel.rdfutil.RDFUtil._
 
  package object patterns {
    implicit def pimpStr(uri : String) = new {
      def as(arg : Arg) : OntologyFrameElement = OntologyFrameElement(URI.create(uri), arg)
      def greaterThan(boundary : Double) : ScalarMembershipSlashClass = new ScalarMembershipSlashClass(URI.create(uri),boundary,positive)
      def lessThan(boundary : Double) : ScalarMembershipSlashClass = new ScalarMembershipSlashClass(URI.create(uri),boundary,negative)
    }
    implicit def pimpStr(uri : URI) = new {
      def as(arg : Arg) : OntologyFrameElement = OntologyFrameElement(uri, arg)
      def greaterThan(boundary : Double) : ScalarMembershipSlashClass = new ScalarMembershipSlashClass(uri,boundary,positive)
      def lessThan(boundary : Double) : ScalarMembershipSlashClass = new ScalarMembershipSlashClass(uri,boundary,negative)
    }
    
    implicit def str2URI(uri : String) : URI = URI.create(uri)
    implicit def uriToStr(uri : URI) : String = uri.toString()
    
    implicit def str2NP(lemma : String) : NP = NP(Word(lemma,pos.commonNoun))
    implicit def str2PNP(lemma : String) : PNP = PNP(Word(lemma,pos.properNoun))
    implicit def str2VP(lemma : String) : VP = VP(Word(lemma,pos.verb))
    implicit def str2AP(lemma : String) : VP = VP(Word(lemma,pos.adjective))
    
    implicit def str2Word(lemma : String) = new {
      def /(pos : POS) = Word(lemma,pos)
    }
    
    val lexinfo = Namespace("http://lexinfo.net/ontology/2.0/lexinfo#")

        
  }
  
  package patterns {
    case class Namespace(prefix : String) {
      def apply(frag : String) : URI = URI.create(prefix + frag)
    }
    
    object WriteAsRDF {
      def apply(ns : Seq[Node]) = new PrettyPrinter(80,2).format(<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:lemon="http://www.monnet-project.eu/lemon#" xmlns:lexinfo="http://lexinfo.net/ontology/2.0/lexinfo#" xmlns:owl="http://www.w3.org/2002/07/owl#">{for(n <- ns) yield n}</rdf:RDF>)
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
      def optional : Arg
    }
    trait Copulative extends Arg

    /**
    * The subject of a verb
    */
    trait SubjectArg extends Arg
    object Subject extends SubjectArg {
      def toXML(uri : URI, namer : URINamer) = <lexinfo:subject rdf:resource={uri}/>
      def optional = OptionalSubject
    }
    object OptionalSubject extends SubjectArg {
      def toXML(uri : URI, namer : URINamer) = <lexinfo:subject><lemon:Argument rdf:about={uri} lemon:optional="true"/></lexinfo:subject>
      def optional = this
    }
    
    /**
     * The copulative argument of a noun or adjective
     */
    object CopulativeArg extends Copulative {
      def toXML(uri : URI, namer : URINamer) = <lexinfo:copulativeArg rdf:resource={uri}/>
      def optional = OptionalCopulativeArg
    }
    object OptionalCopulativeArg extends Copulative {
      def toXML(uri : URI, namer : URINamer) = <lexinfo:copulativeArg><lemon:Argument rdf:about={uri} lemon:optional="true"/></lexinfo:copulativeArg>
      def optional = this
    }
    
    /**
     * The copulative subject of a noun or adjective
     */
    object CopulativeSubject extends Copulative {
      def toXML(uri : URI, namer : URINamer) = <lexinfo:copulativeSubject rdf:resource={uri}/>
      def optional = OptionalCopulativeSubject
    }
    object OptionalCopulativeSubject extends Copulative {
      def toXML(uri : URI, namer : URINamer) = <lexinfo:copulativeSubject><lemon:Argument rdf:about={uri} lemon:optional="true"/></lexinfo:copulativeSubject>
      def optional = this
    }
    
    /**
    * The direct object of a verb
    */
    trait DirectObjectArg extends Arg
    object DirectObject extends DirectObjectArg {
      def toXML(uri : URI, namer : URINamer) = <lexinfo:directObject rdf:resource={uri}/>
      def optional = OptionalDirectObject
    }
    object OptionalDirectObject extends DirectObjectArg {
      def toXML(uri : URI, namer : URINamer) = <lexinfo:directObject><lemon:Argument rdf:about={uri} lemon:optional="true"/></lexinfo:directObject>
      def optional = this
    }
    
    /**
    * The indirect object of a verb
    */
    trait IndirectObjectArg extends Arg
    object IndirectObject extends IndirectObjectArg {
      def toXML(uri : URI, namer : URINamer) = <lexinfo:indirectObject rdf:resource={uri}/>
      def optional = OptionalIndirectObject
    }
    object OptionalIndirectObject extends IndirectObjectArg {
      def toXML(uri : URI, namer : URINamer) = <lexinfo:indirectObject><lemon:Argument rdf:about={uri} lemon:optional="true"/></lexinfo:indirectObject>
      def optional = this
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
      def optional = OptionalPrepositionalObject(preposition) 
    }
    case class OptionalPrepositionalObject(preposition : String) extends AdpositionalObject {
      def toXML(uri : URI, namer : URINamer) = <lexinfo:prepositionalObject>
                  <lemon:Argument rdf:about={uri} lemon:optional="true">
                    <lemon:marker rdf:resource={namer.auxiliaryEntry(preposition)}/>
                  </lemon:Argument>
                </lexinfo:prepositionalObject>
      def optional = OptionalPrepositionalObject(preposition) 
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
      def optional = OptionalPostpositionalObject(postposition)
    }
    case class OptionalPostpositionalObject(postposition : String) extends AdpositionalObject  {
      def toXML(uri : URI, namer : URINamer) = <lexinfo:postpositionalObject>
                  <lemon:Argument rdf:about={uri} lemon:optional="true">
                    <lemon:marker rdf:resource={namer.auxiliaryEntry(postposition)}/>
                  </lemon:Argument>
                </lexinfo:postpositionalObject>
      def optional = this
    }
    
    trait PossessiveAdjunctArg extends Arg
    object PossessiveAdjunct extends PossessiveAdjunctArg {
      def toXML(uri : URI, namer : URINamer) = <lexinfo:possessiveAdjunct rdf:resource={uri}/>
      def optional = OptionalPossessiveAdjunct
    } 
    object OptionalPossessiveAdjunct extends PossessiveAdjunctArg {
      def toXML(uri : URI, namer : URINamer) = <lexinfo:possessiveAdjunct><lemon:Argument rdf:about={uri} lemon:optional="true"/></lexinfo:possessiveAdjunct>
      def optional = this
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
 
    
    
    class ScalarMembershipSlashClass(val property : URI,
                                     val boundary : Double,
                                     val direction : Direction) {
      def forClass(classURI : URI) = ScalarMembership(property,classURI,boundary,direction)
    }
      
    case class ScalarMembership(val property : URI,
                                val forClass : URI,
                                val boundary : Double,
                                val direction : Direction)
  }
}
