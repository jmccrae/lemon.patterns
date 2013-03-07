package net.lemonmodel.patterns

import java.net.URI
import java.net.URLEncoder

case class Lexicon(uri : URI, lang : String, patterns : Pattern*) {
  private var elems = collection.mutable.Set[String]()
  private def from(n : Int) : Stream[Int] = Stream.cons(n,from(n+1))
  
  private val uriNamer = new URINamer {
    def apply(pos : String, form : String, element : Option[String] = None) = element match {
      case Some(e) => {
        val frag = URLEncoder.encode(form,"UTF-8") + "__" + pos + "/" + e
        if((elems contains frag) && !(e equals "canonicalForm")) {
          val freeIdx = from(1) find { x => !elems.contains(frag+x) }
          elems += (frag+freeIdx.get)
          URI.create(uri + "#" + frag + freeIdx.get)
        } else {
          elems += frag 
          URI.create(uri + "#" + frag)
        }
      }
      case None => URI.create(uri + "#" + URLEncoder.encode(form,"UTF-8") + "__" + pos)
    }
    def auxiliaryEntry(form : String) = URI.create(uri + "#" + form)
  }
  
  def toXML() = {
    <lemon:Lexicon rdf:about={uri.toString} lemon:language={lang}>
      {for(pattern <- patterns) yield {
        <lemon:entry>
        {pattern.toXML(uriNamer,lang)}
        </lemon:entry>
     }}
   </lemon:Lexicon>
  }
}
      
