package net.lemonmodel {

  import java.net.URI
  import scala.xml._
  import net.lemonmodel.rdfutil.RDFUtil._
 
  package object patterns {
    implicit def pimpStr(uri : String) = new {
      def as(arg : Arg) : OntologyFrameElement = OntologyFrameElement(Some(URI.create(uri)), arg)
      def greaterThan(boundary : Double) : ScalarMembershipSlashClass = new ScalarMembershipSlashClass(URI.create(uri),boundary,positive)
      def lessThan(boundary : Double) : ScalarMembershipSlashClass = new ScalarMembershipSlashClass(URI.create(uri),boundary,negative)
    }
    implicit def pimpStr(uri : URI) = new {
      def as(arg : Arg) : OntologyFrameElement = OntologyFrameElement(Some(uri), arg)
      def greaterThan(boundary : Double) : ScalarMembershipSlashClass = new ScalarMembershipSlashClass(uri,boundary,positive)
      def lessThan(boundary : Double) : ScalarMembershipSlashClass = new ScalarMembershipSlashClass(uri,boundary,negative)
      def covariant : ScalarMembershipSimpleClass = new ScalarMembershipSimpleClass(uri,positive)
      def contravariant : ScalarMembershipSimpleClass = new ScalarMembershipSimpleClass(uri,negative)
    }
    
    implicit def smsc2Seq(smsc : ScalarMembershipSimpleClass) : Seq[ScalarMembership] = 
      Seq(ScalarMembership(smsc.property,null,Double.NaN,smsc.direction))
    
    implicit def smsc2sms(smsc : ScalarMembershipSimpleClass) : ScalarMembership =
      ScalarMembership(smsc.property,null,Double.NaN,smsc.direction)
    
    implicit def str2URI(uri : String) : URI = URI.create(uri)
    implicit def uriToStr(uri : URI) : String = uri.toString()
    
    implicit def str2NP(lemma : String) : NP = NP(Word(lemma,pos.commonNoun))
    implicit def str2PNP(lemma : String) : PNP = PNP(Word(lemma,pos.properNoun))
    implicit def str2VP(lemma : String) : VP = VP(Word(lemma,pos.verb))
    implicit def str2AP(lemma : String) : AP = AP(Word(lemma,pos.adjective))
        
    implicit def str2Word(form : String) = new {
      def /(pos : POS) = Word(form,pos)
      def /(lemma : String) = new {
        def /(pos :POS) = Word(lemma,pos,Some(form))
      }
    }
    
    val lexinfo = Namespace("http://www.lexinfo.net/ontology/2.0/lexinfo#")
    val base = Namespace("#")
            
  }
  
  package patterns {
    case class Namespace(prefix : String) {
      def apply(frag : String) : URI = URI.create(prefix + frag)
    }
    
    object WriteAsRDF {
      def apply(ns : Seq[Node]) = (<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
        xmlns:lemon="http://lemon-model.net/lemon#" xmlns:lexinfo="http://www.lexinfo.net/ontology/2.0/lexinfo#"
        xmlns:owl="http://www.w3.org/2002/07/owl#" xmlns:oils="http://lemon-model.net/oils#"
        xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#" xmlns:xsd="http://www.w3.org/2001/XMLSchema#">{for(n <- ns) yield
        n}
</rdf:RDF>).toString()
    }
    
    trait URINamer {
      def apply(pos : String, form : String, element : Option[String] = None) : URI
      def auxiliaryEntry(form : String, pos : String) : URI
      def auxXML : Seq[Node]
      def anonURI(ref : Any) : URI
    }
    
    trait Pattern {
      def makeWithForm(form : Form) : Pattern
      def withRegister(register : Register) : Pattern
      def toXML(namer : URINamer, lang : String)  : Node
      def toOntoLexXML(namer : URINamer, lang : String) : Node
      def registerXML(register : Option[Register]) = {
	{register match {
	    case Some(r) => <lexinfo:register rdf:resource={lexinfo(r.toString()).toString()}/>
	    case None =>
	  }
         }
      } 
    }
    
   sealed trait Register
    object BenchLevelRegister extends Register { override def toString = "benchLevelRegister" }
    object DialectRegister extends Register { override def toString = "dialectRegister" }
    object FacetiousRegister extends Register { override def toString = "facetiousRegister" }
    object FormalRegister extends Register { override def toString = "formalRegister" }
    object InHouseRegister extends Register { override def toString = "inHouseRegister" }
    object IronicRegister extends Register { override def toString = "ironicRegister" }
    object NeutralRegister extends Register { override def toString = "neutralRegister" }
    object SlangRegister extends Register { override def toString = "slangRegister" }
    object TabooRegister extends Register { override def toString = "tabooRegister" }
    object TechnicalRegister extends Register { override def toString = "technicalRegister" }
    object VulgarRegister extends Register { override def toString = "vulgarRegister" }
    
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
      def isOptional : Boolean
      def restriction : Option[URI]
      def toXML(uri : URI, namer : URINamer) : Node
      def optional : Arg
      def restrictedTo(uri : URI) : Arg
    }
    
    case class ArgImpl(val isOptional : Boolean, val restriction : Option[URI], val name : String) extends Arg{
      def toXML(uri : URI, namer : URINamer) = if(!isOptional) {
        xml.Elem("lexinfo",name,Null,TopScope) % new xml.PrefixedAttribute("rdf","resource",uri.toString,Null)
      } else {
        val a = <lemon:Argument rdf:about={uri}><lemon:optional rdf:datatype="http://www.w3.org/2001/XMLSchema#boolean">true</lemon:optional></lemon:Argument>
        xml.Elem("lexinfo",name,Null,TopScope,a)
      }
      def optional = ArgImpl(true,restriction,name)
      def restrictedTo(uri : URI) = ArgImpl(isOptional,Some(uri),name)
    }
    
    /**
    * The subject of a verb
    */
    object Subject extends ArgImpl(false,None,"subject")
    
    /**
     * The copulative argument of a noun or adjective
     */
    object CopulativeArg extends ArgImpl(false,None,"copulativeArg")
    
    /**
     * The copulative subject of a noun or adjective
     */
    object CopulativeSubject extends ArgImpl(false,None,"copulativeSubject")
    
    /**
    * The direct object of a verb
    */
    object DirectObject extends ArgImpl(false,None,"directObject")
    
    /**
    * The indirect object of a verb
    */
    object IndirectObject extends ArgImpl(false,None,"indirectObject")

    /**
    * A prepositional object in a frame
    */
    case class PrepositionalObject(val isOptional : Boolean, val restriction : Option[URI], val preposition : String) extends Arg {
      def toXML(uri : URI, namer : URINamer) = if(isOptional) {
        <lexinfo:prepositionalObject>
          <lemon:Argument rdf:about={uri}>
            <lemon:marker rdf:resource={namer.auxiliaryEntry(preposition,"preposition")}/>
            <lemon:optional rdf:datatype="http://www.w3.org/2001/XMLSchema#boolean">true</lemon:optional>
           </lemon:Argument>
        </lexinfo:prepositionalObject>
      } else {
        <lexinfo:prepositionalObject>
          <lemon:Argument rdf:about={uri}>
            <lemon:marker rdf:resource={namer.auxiliaryEntry(preposition,"preposition")}/>
           </lemon:Argument>
        </lexinfo:prepositionalObject>
      }
      def optional = PrepositionalObject(true,restriction, preposition)
      def restrictedTo(uri : URI) = PrepositionalObject(isOptional,Some(uri),preposition)
      def aux(uri : URI, namer : URINamer, lang : String) = Some(<lemon:LexicalEntry rdf:about={uri}>
        <lemon:canonicalForm>
          <lemon:Form>
            <lemon:writtenRep xml:lang={lang}>{preposition}</lemon:writtenRep>
          </lemon:Form>
        </lemon:canonicalForm>
      </lemon:LexicalEntry>)
    }
    
    object PrepositionalObject {
      def apply(preposition : String) = new PrepositionalObject(false,None,preposition)
    }
    
    /**
     * A postposition object in a frame
     */
    case class PostpositionalObject(val isOptional : Boolean, val restriction : Option[URI], val postposition : String) extends Arg {
      def toXML(uri : URI, namer : URINamer) = if(isOptional) {
        <lexinfo:postpositionalObject>
          <lemon:Argument rdf:about={uri}>
            <lemon:marker rdf:resource={namer.auxiliaryEntry(postposition,"postposition")}/>
            <lemon:optional rdf:datatype="http://www.w3.org/2001/XMLSchema#boolean">true</lemon:optional>
           </lemon:Argument>
        </lexinfo:postpositionalObject>
      } else {
        <lexinfo:postpositionalObject>
          <lemon:Argument rdf:about={uri}>
            <lemon:marker rdf:resource={namer.auxiliaryEntry(postposition,"postposition")}/>
           </lemon:Argument>
        </lexinfo:postpositionalObject>
      }
      def optional = PostpositionalObject(true,restriction, postposition)
      def restrictedTo(uri : URI) = PostpositionalObject(isOptional,Some(uri),postposition)
      def aux(uri : URI, namer : URINamer, lang : String) = Some(<lemon:LexicalEntry rdf:about={uri}>
        <lemon:canonicalForm>
          <lemon:Form>
            <lemon:writtenRep xml:lang={lang}>{postposition}</lemon:writtenRep>
          </lemon:Form>
        </lemon:canonicalForm>
      </lemon:LexicalEntry>)
    }
    
    object PostpositionalObject {
      def apply(postposition : String) = new PostpositionalObject(false,None,postposition)
    }
    
    object PossessiveAdjunct extends ArgImpl(false,None,"possessiveAdjunct")
        
    /**
     * An element of a multivalent frame in the ontology. Normally constructed
     * using the {@code as} implicit, e.g., {@code "http://www.example.com/ontology#property" as Subject}
     */
    case class OntologyFrameElement(val property : Option[URI], val arg : Arg) {
      def optional = OntologyFrameElement(property,arg optional)
    }
      
     
    sealed trait Direction
    object positive extends Direction   
    object negative extends Direction
    object central extends Direction
 
    
    
    class ScalarMembershipSlashClass(val property : URI,
                                     val boundary : Double,
                                     val direction : Direction) {
      def forClass(classURI : URI) = ScalarMembership(property,classURI,boundary,direction)
    }
    
    class ScalarMembershipSimpleClass(val property : URI,
                                      val direction : Direction) {
      def greaterThan(boundary : Double) = if(direction == positive) {
        new ScalarMembershipSlashClass(property,boundary,direction)
      } else {
        throw new IllegalArgumentException("Scalar membership indicated as both positive and negative!");
      }
      
      def lessThan(boundary : Double) = if(direction == negative) {
        new ScalarMembershipSlashClass(property,boundary,direction)
      } else {
        throw new IllegalArgumentException("Scalar membership indicated as both positive and negative!");
      }
    }
      
    case class ScalarMembership(val property : URI,
                                val forClass : URI,
                                val boundary : Double,
                                val direction : Direction,
                                val boundary2 : Double = Double.NaN)
  }
}
