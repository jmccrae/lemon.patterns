package net.lemonmodel.rdfutil

import java.net.URI

object RDFUtil {
  private val xmlNameStartChar = """A-Z_a-z\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u02FF\u0370-\u037D\u037F-\u1FFF\u200C-\u200D\u2070-\u218F\u2C00-\u2FEF\u3001-\uD7FF\uF900-\uFDCF\uFDF0-\uFFFD"""
  private val xmlNameChar = xmlNameStartChar + """-\\.0-9\u00B7\u0300-\u036F\u203F-\u2040"""
  private val xmlName = "["+xmlNameStartChar+"]["+xmlNameChar+"]*"
  private val hashFragURI = ("""(.*/(""" + xmlName + """)#)(.*)""").r
  private val lastSlashURI = ("""(.*/(""" + xmlName + """)/)(""" + xmlName + """)""").r
  private val fallbackURI = ("""(.*?("""+xmlName+""")?)(""" + xmlName + """)""").r
  
  def prefixURI(uri : URI) : (String, String, String) = uri.toString() match {
    case hashFragURI(prefixURI,prefixName,suffix) => (prefixURI,prefixName,suffix)
    case lastSlashURI(prefixURI,prefixName,suffix) => (prefixURI,prefixName,suffix)
    case fallbackURI(prefixURI,prefixName,suffix) => (prefixURI,prefixName,suffix)
    case _ => throw new RuntimeException("Could not rended " + uri + " to XML form")
  }
}
