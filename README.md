lemon Design Pattern Library
============================

This library consists of a Scala script implementing design patterns using the 
[lemon](http://lemon-model.net) model and the [OWL](http://www.w3.org/???) language
to define ontology-lexica. 

Usage and Requirements
----------------------

You will need [Scala](http://www.scala-lang.org) to compile scripts using this language

Source files are normal Scala files and as such they can be compile using the following 
command
 
  scala -cp lemon-patterns.jar lexicon.scala

The project may be compiled using [sbt](http://www.scala-sbt.org/) or [Maven](http://maven.apache.org).

Writing ontology-lexica with patterns
-------------------------------------

Writing an ontology-lexicon using these patterns involves starting the file with a `OntologyLexicon`
declaration that defines the path of the ontology-lexicon and its language, finally the serialization

  OntologyLexicon("http://www.example.com/published/path", // Here is where the ontology-lexicon will be published
                  "eng", // The language (as an ISO-639 code)
                  ... // The entries go here
                  ).toXML // serialize as RDF/XML

In addition namespaces may be defined before the `OntologyLexicon` as follows

  val dbpedia = Namespace("http://dbpedia.org/resource/")

And used anywhere a full URI would be acceptable as follows
  
  dbpedia("Cat") // Generates the URI http://dbpedia.org/resource/Cat

Catalogue
---------

**Names**

Names are used for named individuals in the domain, these should be proper nouns

  Name("Microsoft","http://microsoft.com")

**Class Nouns**

Class nouns represent genuses of objects from the domain, this includes most common nouns

  ClassNoun("cat",dbpedia("cat")) withPlural "cats"

**Relational Nouns**

These represent relations between two entities that are expressed in sentences as nouns

  RelationalNoun("agreement",ontology("agreeAbout"),
                 propSubj=Subject,
                 propObj=PrepositionalObject("about"))

** Multivalent Relational Nouns **

As with relational nouns but supports more than two arguments

  RelationalMultivalentNoun("position",ontology("Employement"),
              args=Seq(ontology("employee") as PossessiveAdjunct,
                       ontology("role") as PrepositionalObject("as") optional,
                       ontology("startOfEmployment") as PrepositionalObject("since") optional))
                 
