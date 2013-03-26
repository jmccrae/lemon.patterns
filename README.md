lemon Design Pattern Library
============================

This library consists of a Scala script implementing design patterns using the 
[lemon](http://lemon-model.net) model and the [OWL](http://www.w3.org/TR/owl2-overview/) language
to define ontology-lexica. 

_lemon_ Design Patterns provide an interface for using the most commonly used _lemon_ 
patterns for the ontology-lexicon interface. This project represents the converter
from the pattern language to RDF/XML as well as a Scala API for working with these
patterns.

Usage and Requirements
----------------------

The project may be compiled using [sbt](http://www.scala-sbt.org/) or [Maven](http://maven.apache.org).

The converter may be executed as follows:

    ./lemonpatterns lexicon.ldp lexicon.xml
    
You may omit the second argument to print to standard out. Note that the first
execution may require downloading some libraries and may take some time.

If you execute `lemonpatterns` from another directory you must set the 
`LEMONPATTERNS_HOME` variable, e.g., 

     LEMONPATTERNS_HOME=/home/me/lemon.patterns /home/me/lemonpatterns lexicon.ldp

Writing ontology-lexica with patterns
-------------------------------------

Writing an ontology-lexicon using these patterns involves starting the file with an `OntologyLexicon`
declaration that defines the path of the ontology-lexicon and its language, finally the serialization

    Lexicon(<http://www.example.com/published/path>, // Here is where the ontology-lexicon will be published
            "eng", // The language (as an ISO-639 code)
            ... // The entries go here
    )

In addition namespaces may be defined before the `Lexicon` as follows

    @prefix dbpedia: <http://dbpedia.org/resource/> .

And used anywhere a full URI would be acceptable as follows
  
    dbpedia:Cat // Generates the URI http://dbpedia.org/resource/Cat
    
The full syntax for the pattern language is [here](docs/parser.pdf?raw=true)

Catalogue
---------

# Nouns

## Names

![The name pattern](https://raw.github.com/jmccrae/lemon.patterns/master/images/Name.png)

Names are used for named individuals in the domain, these should be proper nouns

    Name("Microsoft",<http://microsoft.com>)

## Class Nouns

![The class noun pattern](https://raw.github.com/jmccrae/lemon.patterns/master/images/ClassNoun.png)

Class nouns represent genuses of objects from the domain, this includes most common nouns

    ClassNoun("cat",dbpedia:Cat) with plural "cats"
    
Multiple word expressions can be introduced using a simple phrase pattern

    ClassNoun("body"/"common noun" "of"/preposition "water"/"common noun",dbpedia:BodyOfWater)
  
_Note: On the part of speech quotes are optional on single word values only_

## Relational Nouns

![The relational noun pattern](https://raw.github.com/jmccrae/lemon.patterns/master/images/RelationalNoun.png)

These represent relations between two entities that are expressed in sentences as nouns

    RelationalNoun("agreement",ontology:agreeAbout,
                   propSubj=Subject,
                   propObj=PrepositionalObject("about"))

## Multivalent Relational Nouns

![The multivalent relational noun pattern](https://raw.github.com/jmccrae/lemon.patterns/master/images/MultivalentRelationalNoun.png)

As with relational nouns but supports more than two arguments

    RelationalMultivalentNoun("position",ontology:Employement,
                [ontology:employee as PossessiveAdjunct,
                 ontology:role as PrepositionalObject("as") optional,
                 ontology:startOfEmployment as PrepositionalObject("since") optional])

## Class Relational Nouns

This is a shortcut pattern for nouns that have both a class and property ontological 
description. An example of this "father", as a father is both a father to someone
and a father.

    ClassRelationalNoun("father",
                      class = ontology:Father,
                      property = ontology:fatherOf,
                      propObj = PossessiveAdjunct)
                        
Note: `PossessiveAdjunct` is used to indicate arguments made with "of" or a genitive
form, this allows constructions like "John is Betty's father" as well as 
"John is the father of Betty".
                         
# Verbs

## State Verb

![The state verb pattern](https://raw.github.com/jmccrae/lemon.patterns/master/images/StateVerb.png)

Represents a verb describing a (atemporal) state of an individual. The following
example shows how to create an inflectional table for a verb, which can be used 
with any verb pattern

    StateVerb("amare",ontology:loves) 
      with present singular firstPerson  "amo"
      with present singular secondPerson "amas"
      with present singular thirdPerson  "amat"
      with present plural   firstPerson  "amamus"
      with present plural   secondPerson "amatis"
      with present plural   thirdPerson  "amant"
    
## Event Verbs

![The accomplishment verb pattern](https://raw.github.com/jmccrae/lemon.patterns/master/images/AccomplishmentVerb.png) 

Event verbs describe an event involving multiple arguments.

    EventVerb("give",ontology:GivingEvent,
         [ontology:giver as Subject,
          ontology:recipient as DirectObject,
          ontology:givenObject as IndirectObject]),  
                  
Event verbs may also be modified as follows:

    telic durative EventVerb(...)

These classes are as follows
    
* `EventVerb` (superclass of all below)
  * (Telic,Durative) `AccomplishmentVerb`: Verb has an end (i.e., accomplishes a result) and has a duration
  * (Telic,Instant) `AchievementVerb`: Verb has an end (i.e., achieves a result) but does not have a duration
  * (Nontelic,Durative) `ActivityVerb`: Verb has no result and occurs for some duration.
  * (Nontelic,Instant) `SemelfactiveVerb`: Verb has no result and has no duration (occurs instantaneously)
           
# Adjectives

## Intersective Adjectives

Intersective adjectives meaning are composed by intersection. As such these adjectives form
a natural class of objects in the world. 

    IntersectiveAdjective("Belgian",ontology:Belgian)
    
In addition there are two patterns for constructing adjectives from values of properties. The two 
patterns are for object and datatype properties

    IntersectiveDataPropertyAdjective("green",ontology:color,"green")
    
## Property-modifying Adjectives

Property-modifying adjectives alter the meaning of the noun they are attributed to. They can normally
only be used in an attributive manner. In the ontology they are generally mappable to object properties

    PropertyModifyingAdjective("former",ontology:heldRole)
    
This works as _"X is a former Y"_ means `X heldRole Y`

## Relational Adjectives

Relational adjectives constitute a relationship between two individuals and as such
are properties in the ontology

    RelationalAdjective("related",ontology:isRelatedTo,
       relationalArg = PrepositionalObject("to"))
    
## Scalar Adjectives

Scalar adjectives consitute a subspace of a conceptual space and are modelled in the 
ontology as datatype ranges

    ScalarAdjective("big",
      [ontology:size > 5.0 for ontology:Building])
