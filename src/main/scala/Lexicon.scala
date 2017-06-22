package net.lemonmodel.patterns

import java.net.URI
import java.net.URLEncoder

case class Lexicon(uri : URI, lang : String, val patterns : Pattern*) {
  private var elems = collection.mutable.Set[String]()
  private def from(n : Int) : Stream[Int] = Stream.cons(n,from(n+1))
  
  private def joinChar(s : String) = {
    if((s endsWith "#") || (s endsWith "/")) {
      ""
    } else if(s contains "#") {
      "/"
    } else {
      "#"
    }
  }
  
  private val uriNamer = new URINamer {
    var auxNodes : List[xml.Node] = Nil
    private var auxSet = collection.mutable.Set[String]()
    
    def apply(pos : String, form : String, element : Option[String] = None) = element match {
      case Some(e) => {
        val frag = URLEncoder.encode(form,"UTF-8") + "__" + pos + "/" + e
        if((elems contains frag) && !(e equals "canonicalForm")) {
          val freeIdx = from(1) find { x => !elems.contains(frag+x) }
          elems += (frag+freeIdx.get)
          URI.create(uri + joinChar(uri) + frag + freeIdx.get)
        } else {
          elems += frag 
          URI.create(uri + joinChar(uri) + frag)
        }
      }
      case None => URI.create(uri + joinChar(uri) + URLEncoder.encode(form,"UTF-8") + "__" + pos)
    }
    def auxiliaryEntry(form : String, pos : String) = {
      val auxURI = URI.create(uri + joinChar(uri) + form + "__" + pos)
      if(!(auxSet contains form)) {
        auxSet += form
        auxNodes ::= <lemon:entry><lemon:LexicalEntry rdf:about={auxURI}>
        <lexinfo:partOfSpeech rdf:resource={lexinfo(pos)}/>
        <lemon:canonicalForm>
          <lemon:Form rdf:about={auxURI + joinChar(auxURI.toString()) + "canonicalForm"}>
            <lemon:writtenRep xml:lang={lang}>{form}</lemon:writtenRep>
          </lemon:Form>
        </lemon:canonicalForm>
      </lemon:LexicalEntry></lemon:entry>
      }
      auxURI
    }
    def auxXML = auxNodes
    private val anonURIs = collection.mutable.Map[Any,URI]()
    private def randomID = (math.random * 10000000000l).toLong.toString
    def anonURI(ref : Any) = anonURIs.get(ref) match {
      case Some(x) => x
      case None => {
        val anon = URI.create(uri + joinChar(uri) + randomID)
        anonURIs.put(ref,anon)
        anon
      }
    }
  }
  def toOntoLexXML() = {
    <lime:Lexicon rdf:about={uri.toString} ontolex:language={lang}>
      {(for(pattern <- patterns) yield {
        <lime:entry>
        {pattern.toOntoLexXML(uriNamer,lang)}
        </lime:entry>
     } ++ uriNamer.auxXML)}
   </lime:Lexicon>
  }
  
  def toXML() = {
    <lemon:Lexicon rdf:about={uri.toString} lemon:language={lang}>
      {(for(pattern <- patterns) yield {
        <lemon:entry>
        {pattern.toXML(uriNamer,lang)}
        </lemon:entry>
     } ++ uriNamer.auxXML)}
   </lemon:Lexicon>
  }
}
      
