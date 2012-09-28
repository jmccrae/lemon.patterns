package net.lemonmodel.patterns

import java.net.URI
import scala.xml._
import net.lemonmodel.rdfutil.RDFUtil._
                            
case class IntersectiveClassAdjective(val form : String,
                                      val sense : URI = null,
                                      val forms : Seq[Form] = Nil)
                                      
case class IntersectiveObjectPropertyAdjective(val form : String,
                                               val property : URI,
                                               val value : URI,
                                               val forms : Seq[Form] = Nil)
                                               
case class IntersectiveDataPropertyAdjective(val form : String,
                                             val property : URI,
                                             val value : String,
                                             val forms : Seq[Form] = Nil)
                                             
case class PropertyModifyingAdjective(val form : String,
                                      val property : URI,
                                      val relationalArg : Arg,
                                      val forms : Seq[Form] = Nil)
                                      
case class RelationalAdjective(val form : String,
                               val property : URI = null,
                               val relationalArg : Arg,
                               val forms : Seq[Form] = Nil)

                               
case class ScalarAdjective(val form : String,
                           val scalarMemberships : Seq[ScalarMembership] = Nil,
                           val forms : Seq[Form] = Nil) {
   def withComparative(comparativeForm : String) = ScalarAdjective(form,scalarMemberships,forms:+Form(comparativeForm,Map(lexinfo("degree")->lexinfo("comparative"))))
   def withSuperlative(comparativeForm : String) = ScalarAdjective(form,scalarMemberships,forms:+Form(comparativeForm,Map(lexinfo("degree")->lexinfo("comparative"))))                             
}

case class ScalarParticleAdjective(val form : String,
                           val scalarMemberships : Seq[ScalarMembership] = Nil,
                           val forms : Seq[Form] = Nil) {
   def withComparative(comparativeForm : String) = ScalarAdjective(form,scalarMemberships,forms:+Form(comparativeForm,Map(lexinfo("degree")->lexinfo("comparative"))))
   def withSuperlative(comparativeForm : String) = ScalarAdjective(form,scalarMemberships,forms:+Form(comparativeForm,Map(lexinfo("degree")->lexinfo("comparative"))))                             
}
