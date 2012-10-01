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

# Nouns

## Names

Names are used for named individuals in the domain, these should be proper nouns

    Name("Microsoft","http://microsoft.com")

## Class Nouns

Class nouns represent genuses of objects from the domain, this includes most common nouns

    ClassNoun("cat",dbpedia("cat")) withPlural "cats"

## Relational Nouns

![The relational noun pattern](raw/master/images/RelationalNoun.png)

These represent relations between two entities that are expressed in sentences as nouns

    RelationalNoun("agreement",ontology("agreeAbout"),
                   propSubj=Subject,
                   propObj=PrepositionalObject("about"))

## Multivalent Relational Nouns

As with relational nouns but supports more than two arguments

    RelationalMultivalentNoun("position",ontology("Employement"),
                args=Seq(ontology("employee") as PossessiveAdjunct,
                         ontology("role") as PrepositionalObject("as") optional,
                         ontology("startOfEmployment") as PrepositionalObject("since") optional))
                         
# Verbs

## State Verb

Represents a verb describing a (atemporal) state of an individual. The following
example shows how to create an inflectional table for a verb, which can be used 
with any verb pattern

    StateVerb("amare",ontology("loves")) withTable (lexinfo,Map(
      ("tense","present") -> Map(
        ("number","singular") -> Map(
          ("person","firstPerson") -> "amo",
          ("person","secondPerson") -> "amas",
          ("person","thirdPerson") -> "amat"
        ),
        ("number","plural") -> Map(
          ("person","firstPerson") -> "amamus",
          ("person","secondPerson") -> "amatis",
          ("person","thirdPerson") -> "amant"
        )
      )
    ))
    
## Event Verbs

Event verbs describe an event involving multiple arguments.

    AchievementVerb("give",ontology("GivingEvent"),
         args=Seq(ontology("giver") as Subject,
                  ontology("recipient") as DirectObject,
                  ontology("givenObject") as IndirectObject))
                  
Event verbs may be one of the following:

* `EventVerb` (superclass of all below)
  * `AchievementVerb`: Verb has an end (i.e., achieves a result) but does not have a duration
  * `AccomplishmentVerb`: Verb has an end (i.e., accomplishes a result) and has a duration
  * `SemelfactiveVerb`: Verb has no result and has no duration (occurs instantaneously)
  * `ActivityVerb`: Verb has no result and occurs for some duration.
           
# Adjectives

## Intersective Adjectives

Intersective adjectives meaning are composed by intersection. As such these adjectives form
a natural class of objects in the world. 

    IntersectiveAdjective("Belgian",ontology("Belgian"))
    
In addition there are two patterns for constructing adjectives from values of properties. The two 
patterns are for object and datatype properties

    IntersectiveDataPropertyAdjective("green",ontology("color"),"green")
    
## Property-modifying Adjectives

Property-modifying adjectives alter the meaning of the noun they are attributed to. They can normally
only be used in an attributive manner. In the ontology they are generally mappable to object properties

    PropertyModifyingAdjective("former",ontology("heldRole"))
    
This works as _"X is a former Y"_ means `X heldRole Y`

## Relational Adjectives

Relational adjectives constitute a relationship between two indidivuals and as such
are properties in the ontology

    RelationalAdjective("related",ontology("isRelatedTo"))
    
## Scalar Adjectives

Scalar adjectives consitute a subspace of a conceptual space and are modelled in the 
ontology as datatype ranges

    ScalarAdjective("big",
      scalarMemberships=Seq(
        ontology("size") greaterThan "5" forClass ontology("Building"))) withComparative "bigger" withSuperlative "biggest"
