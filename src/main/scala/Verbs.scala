package net.lemonmodel.patterns

import java.net.URI
import scala.xml._
import net.lemonmodel.rdfutil.RDFUtil._
                                  
/**
 * A state verb indicating a property
 * @param form The canonical form of the verb (required)
 * @param sense The sense of the verb
 * @param forms The set of other forms
 **/
case class StateVerb(val form : String,
                     val sense : URI = null,
                     val forms : Seq[Form] = Nil)
    
/**
 * Event verbs
 * @param form The canonical form of the verb (required)
 * @param eventClass The class of events for this verb
 * @param args The argument structure of the verb (required)
 * @param forms The set of other forms
 */
trait EventVerb {
  def form : String
  def eventClass : URI
  def args : Seq[OntologyFrameElement]
  def forms : Seq[Form]
}

object EventVerb {
  def apply(_form : String,
            _eventClass : URI = null,
            _args : Seq[OntologyFrameElement],
            _forms : Seq[Form] = Nil) = new EventVerb {
    def form = _form
    def eventClass = _eventClass
    def args = _args
    def forms = _forms
  }
}
             
                
/**
 * Indicates an event verb that has a clear end but not beginning
 * @param form The canonical form of the verb (required)
 * @param eventClass The class of events for this verb
 * @param args The argument structure of the verb (required)
 * @param forms The set of other forms
 */
case class AchievementVerb(val form : String,
                           val eventClass : URI = null,
                           val args : Seq[OntologyFrameElement],
                           val forms : Seq[Form] = Nil) extends EventVerb
                           
 /**
 * Indicates an event verb that has a clear goal and started at some point
 * @param form The canonical form of the verb (required)
 * @param eventClass The class of events for this verb
 * @param args The argument structure of the verb (required)
 * @param forms The set of other forms
 */
case class AccomplishmentVerb(val form : String,
                              val eventClass : URI = null,
                              val args : Seq[OntologyFrameElement],
                              val forms : Seq[Form] = Nil) extends EventVerb
                           
 /**
 * Indicates an event verb that occurs at some point in time (without a duration)
 * @param form The canonical form of the verb (required)
 * @param eventClass The class of events for this verb
 * @param args The argument structure of the verb (required)
 * @param forms The set of other forms
 */
case class SemelfactiveVerb(val form : String,
                            val eventClass : URI = null,
                            val args : Seq[OntologyFrameElement],
                            val forms : Seq[Form] = Nil) extends EventVerb
                           
/**
 * Indicates an event verb that occurs at some point of time
 * @param form The canonical form of the verb (required)
 * @param eventClass The class of events for this verb
 * @param args The argument structure of the verb (required)
 * @param forms The set of other forms
 */
case class ActivityVerb(val form : String,
                        val eventClass : URI = null,
                        val args : Seq[OntologyFrameElement],
                        val forms : Seq[Form] = Nil) extends EventVerb

