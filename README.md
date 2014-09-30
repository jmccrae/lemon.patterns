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

    ClassNoun(["body"/"common noun" "of"/preposition "water"/"common noun"],dbpedia:BodyOfWater)
  
_Note: On the part of speech quotes are optional on single word values only_

If one of more of the words in the expression are inflected the lemmatized form should be given, for example:

    ClassNoun(["variedad"/"common noun" "cultivada"/"cultivado"/adjective],dbpedia:CultivatedVariety)

In addition two patterns are provided that classes that are not named but refer to having a particular value

    ObjectPropertyNoun("historian",dbpedia:profession,dbpedia:Historian)
    DataPropertyNoun("historian",ontology:profession,"Historian")

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

## Specifying Gender

Gender may be specified by following the pattern with an appropriate label, e.g.,

    ClassNoun("Katze",dbpedia:Cat) feminine with plural "Katzen"
    
The values for gender are `masculine`,`feminine`,`neuter`,`commonGender` and `otherGender`.
Gender must be specified before any inflectional variants
                         
In a similar manner register may be specified 

    ClassNoun("emesis",dbpedia:Vomiting) technicalRegister

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

**In general, state verbs are expressed in the present tense**

## Consequence Verb

![The consequence verb pattern](https//raw.github.com/jmccrae/lemon.patterns/master/image/ConsequenceVerb.png)

Represents a verb which after it is completed results in a fact being true about an
entity. These may typically be invoked with an event, e.g.,

    ConsequenceVerb("die",ontology:deathDate,
      propSubj = ontology:deadPerson as Subject,
      propObj  = ontology:time as PrepositionalObject("on"),
      ontology:DeathEvent)

If there is no event it is possible to use the frame without giving an event using the
same syntax as a state verb

    ConsequenceVerb("die",dbpedia:deathDate,
      propObj = PrepositionalObject("on"))

**In general, consequence verbs are expressed in the past tense**
    
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

# Annotations

## Part of speeches

Multi-word names must be quoted with double quotes.

* adverbial pronoun
* affirmative particule
* affixed personal pronoun
* allusive pronoun
* cardinal numeral
* close parenthesis
* collective pronoun
* comparative particle
* compound preposition
* conditional particule
* conditional pronoun
* coordinating conjunction
* coordination particle
* deficient verb
* definite article
* demonstrative determiner
* demonstrative pronoun
* diminutive noun
* distinctive particle
* emphatic pronoun
* exclamative determiner
* exclamative point
* exclamative pronoun
* existential pronoun
* fused preposition determiner
* fused preposition pronoun
* fused preposition
* fused pronounAuxiliary
* future particle
* general adverb
* generalization word
* generic numeral
* impersonal pronoun
* indefinite article
* indefinite cardinal numeral
* indefinite determiner
* indefinite multiplicative numeral
* indefinite ordinal numeral
* indefinite pronoun
* infinitive particle
* interrogative cardinal numeral
* interrogative determiner
* interrogative multiplicative numeral
* interrogative ordinal numeral
* interrogative particle
* interrogative pronoun
* interrogative relative pronoun
* inverted comma
* irreflexive personal pronoun
* light verb
* main verb
* multiplicative numeral
* negative particle
* negative pronoun
* numeral fraction
* open parenthesis
* ordinal adjective
* participle adjective
* partitive article
* past participle adjective
* personal pronoun
* plain verb
* possessive adjective
* possessive determiner
* possessive particle
* possessive pronoun
* possessive relative pronoun
* prepositional adverb
* present participle adjective
* presentative pronoun
* pronominal adverb
* qualifier adjective
* question mark
* reciprocal pronoun
* reflexive adjective
* reflexive personal pronoun
* reflexive possessive pronoun
* relation noun
* relative determiner
* relative particle
* relative pronoun
* strong personal pronoun
* subordinating conjunction
* superlative particle
* suspension points
* unclassified particle
* weak personal pronoun
* adjective
* adposition
* adverb
* article
* bullet
* circumposition
* colon
* comma
* common noun
* conjunction
* copula
* determiner
* interjection
* modal
* noun
* numeral
* particle
* point
* postposition
* preposition
* pronoun
* proper noun
* punctuation
* semiColon
* slash
* verb

## Grammatical Categories

* singular
* dual
* plural
* nominative
* accusative
* genitive
* dative
* comparative
* superlative
* present
* past
* future
* firstPerson
* secondPerson
* thirdPerson
* imperfect
* imperative
* indicative
* subjunctive
* conditional
* gerundive
* infinitive
* participle
* Alternatively any URI may be given as follows:
    * _URI_ `=>` _URI_
    * e.g., `lexinfo:gender => lexinfo:masculine`

## Registers

* benchLevelRegister
* dialectRegister
* facetiousRegister
* formalRegister
* inHouseRegister
* ironicRegister
* neutralRegister
* slangRegister
* tabooRegister
* technicalRegister
* vulgarRegister
