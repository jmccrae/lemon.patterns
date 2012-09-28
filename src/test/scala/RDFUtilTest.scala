package net.lemonmodel.rdfutil

import java.net.URI
import org.scalatest._
import org.scalatest.matchers._
import scala.xml._
import scala.xml.Utility._

class RDFUtilTest extends FlatSpec with ShouldMatchers {
  import RDFUtil._
  
  "prefixURI" should "handle fragments" in {
    val uri = URI.create("http://www.example.com/file#frag")
    prefixURI(uri) should equal ("http://www.example.com/file#","file","frag")
  }
  
  "prefixURI" should "handle slashes" in {
    val uri = URI.create("http://www.example.com/file/frag")
    prefixURI(uri) should equal ("http://www.example.com/file/","file","frag")
  }
  
  "prefixURI" should "fallback gracefully" in {
    val uri = URI.create("file:example")
    prefixURI(uri) should equal ("file:exampl","exampl","e")
  }
}
