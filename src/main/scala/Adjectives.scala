package net.lemonmodel.patterns

import java.net.URI
import scala.xml._
import net.lemonmodel.rdfutil.RDFUtil._


/**
 * An adjective
 */
trait Adjective extends Pattern {
  def makeWithForm(form : Form) : Adjective
  protected def makeWithForms(forms : Seq[Form]) : Adjective
  protected def senseXML(namer : URINamer) : NodeSeq
  def extractForms(namespace : Namespace,  table : Map[(String,String),Any], props : List[(URI,URI)]) : Seq[Form] = {
    (for(((prop,propVal),subtable) <- table) yield {
      val propURI = namespace(prop)
      val propValURI = namespace(propVal)
      subtable match {
        case form : String => Seq(Form(form,(props :+ (propURI,propValURI)).toMap))
        case st : Map[_,_] => extractForms(namespace,st.asInstanceOf[Map[(String,String),Any]],props :+ (propURI,propValURI))
        case st : (_,_) => extractForms(namespace,Map(st).asInstanceOf[Map[(String,String),Any]],props :+ (propURI,propValURI))
        case fail => throw new IllegalArgumentException("Invalid value in a table " + fail.toString())
      }
    }).flatten.toSeq
  }
  def withTable(namespace : Namespace, table : Map[(String,String),Any]) : Adjective = {
    val forms = extractForms(namespace,table,Nil)
    makeWithForms(forms)
  }
  def withComparative(comparativeForm : String) = makeWithForm(Form(comparativeForm,Map(lexinfo("degree")->lexinfo("comparative"))))
  def withSuperlative(superlativeForm : String) = makeWithForm(Form(superlativeForm,Map(lexinfo("degree")->lexinfo("superlative"))))
  def lemma : AP
  def forms : Seq[Form]
  def toXML(namer : URINamer, lang : String) = <lemon:LexicalEntry rdf:about={namer("adjective",lemma.toString())}>
      <lemon:canonicalForm>
        <lemon:Form rdf:about={namer("adjective",lemma.toString(),Some("canonicalForm"))}>
          <lemon:writtenRep xml:lang={lang}>{lemma.toString()}</lemon:writtenRep>
        </lemon:Form>
      </lemon:canonicalForm> 
      { lemma.toXML(namer,lang) }
      {
        for(form <- forms) yield {
          <lemon:otherForm>
            <lemon:Form rdf:about={namer("adjective",lemma.toString(),Some("form"))}>
              <lemon:writtenRep xml:lang={lang}>{form.writtenRep}</lemon:writtenRep>
              {
                for((prop,propVal) <- form.props) yield {
                  val (prefixUri,prefix,suffix) = prefixURI(prop)
                  <lingonto:prop rdf:resource={propVal}/>.copy(prefix=prefix, label=suffix) %
                    Attribute("","xmlns:"+prefix,prefixUri,Null)
                }
              }
            </lemon:Form>
          </lemon:otherForm>
        }   
      }
      {senseXML(namer)}
    </lemon:LexicalEntry>
}

case class IntersectiveAdjective(val lemma : AP,
                                      val sense : URI = null,
                                      val forms : Seq[Form] = Nil,
                                      val register : Option[Register] = None) extends Adjective {
  def makeWithForm(form : Form) = IntersectiveAdjective(lemma,sense,forms :+ form,register)
  protected def makeWithForms(otherForms : Seq[Form]) = IntersectiveAdjective(lemma,sense, forms ++ otherForms,register)
  protected def senseXML(namer : URINamer) = {
  val subjURI = namer("adjective",lemma.toString(),Some("subject"))
    <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("adjective",lemma.toString(),Some("sense"))}>
         <lemon:reference>
           <owl:Class rdf:about={sense}/>
         </lemon:reference>
         {registerXML(register)}
         <lemon:isA>
            <lemon:Argument rdf:about={subjURI}/>
         </lemon:isA>
       </lemon:LexicalSense>
    </lemon:sense> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("adjective",lemma.toString(),Some("predFrame"))}>
        <rdf:type rdf:resource={lexinfo("AdjectivePredicativeFrame")}/>
        <lexinfo:copulativeSubject rdf:resource={subjURI}/>
      </lemon:Frame>
    </lemon:synBehavior> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("adjective",lemma.toString(),Some("attrFrame"))}>
        <rdf:type rdf:resource={lexinfo("AdjectiveAttributiveFrame")}/>
        <lexinfo:attributiveArg rdf:resource={subjURI}/>
      </lemon:Frame>
    </lemon:synBehavior>
  }
}
                                      
case class IntersectiveObjectPropertyAdjective(val lemma : AP,
                                               val property : URI,
                                               val value : URI,
                                               val forms : Seq[Form] = Nil,
                                               val register : Option[Register] = None) extends Adjective {
  def makeWithForm(form : Form) = IntersectiveObjectPropertyAdjective(lemma,property,value,forms :+ form,register)
  protected def makeWithForms(otherForms : Seq[Form]) = IntersectiveObjectPropertyAdjective(lemma,property,value, forms ++ otherForms,register)
  protected def senseXML(namer : URINamer) = {
  val subjURI = namer("adjective",lemma.toString(),Some("subject"))
    <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("adjective",lemma.toString(),Some("sense"))}>
         <lemon:reference>
           <owl:Restriction>
             <owl:onProperty rdf:resource={property}/>
             <owl:hasValue rdf:resource={value}/>
           </owl:Restriction>
         </lemon:reference>
         {registerXML(register)}
         <lemon:isA>
            <lemon:Argument rdf:about={subjURI}/>
         </lemon:isA>
       </lemon:LexicalSense>
    </lemon:sense> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("adjective",lemma.toString(),Some("predFrame"))}>
        <rdf:type rdf:resource={lexinfo("AdjectivePredicativeFrame")}/>
        <lexinfo:copulativeSubject rdf:resource={subjURI}/>
      </lemon:Frame>
    </lemon:synBehavior> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("adjective",lemma.toString(),Some("attrFrame"))}>
        <rdf:type rdf:resource={lexinfo("AdjectiveAttributiveFrame")}/>
        <lexinfo:attributiveArg rdf:resource={subjURI}/>
      </lemon:Frame>
    </lemon:synBehavior>
  }
}
                                               
case class IntersectiveDataPropertyAdjective(val lemma : AP,
                                             val property : URI,
                                             val value : String,
                                             val forms : Seq[Form] = Nil,
                                             val register : Option[Register] = None) extends Adjective {
  def makeWithForm(form : Form) = IntersectiveDataPropertyAdjective(lemma,property,value,forms :+ form,register)
  protected def makeWithForms(otherForms : Seq[Form]) = IntersectiveDataPropertyAdjective(lemma,property,value, forms ++ otherForms,register)
  protected def senseXML(namer : URINamer) = {
  val subjURI = namer("adjective",lemma.toString(),Some("subject"))
    <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("adjective",lemma.toString(),Some("sense"))}>
         <lemon:reference>
           <owl:Restriction>
             <owl:onProperty rdf:resource={property}/>
             <owl:hasValue>{value}</owl:hasValue>
           </owl:Restriction>
         </lemon:reference>
         {registerXML(register)}
         <lemon:isA>
            <lemon:Argument rdf:about={subjURI}/>
         </lemon:isA>
       </lemon:LexicalSense>
    </lemon:sense> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("adjective",lemma.toString(),Some("predFrame"))}>
        <rdf:type rdf:resource={lexinfo("AdjectivePredicativeFrame")}/>
        <lexinfo:copulativeSubject rdf:resource={subjURI}/>
      </lemon:Frame>
    </lemon:synBehavior> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("adjective",lemma.toString(),Some("attrFrame"))}>
        <rdf:type rdf:resource={lexinfo("AdjectiveAttributiveFrame")}/>
        <lexinfo:attributiveArg rdf:resource={subjURI}/>
      </lemon:Frame>
    </lemon:synBehavior>
  }
}
                                             
case class PropertyModifyingAdjective(val lemma : AP,
                                      val property : URI,
                                      val forms : Seq[Form] = Nil,
                                      val register : Option[Register] = None) extends Adjective {
  def makeWithForm(form : Form) = PropertyModifyingAdjective(lemma,property,forms :+ form, register)
  protected def makeWithForms(otherForms : Seq[Form]) = PropertyModifyingAdjective(lemma,property,forms ++ otherForms,register)
  protected def senseXML(namer : URINamer) = {
    val subjURI = namer("adjective",lemma.toString(),Some("subject"))
    val objURI = namer("adjective",lemma.toString(),Some("attributive"))
    <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("adjective",lemma.toString(),Some("sense"))}>
         <lemon:reference>
           <rdf:Property rdf:about={property}/>
         </lemon:reference>
         {registerXML(register)}
         <lemon:subjOfProp>
            <lemon:Argument rdf:about={subjURI}/>
         </lemon:subjOfProp>
         <lemon:objOfProp>
            <lemon:Argument rdf:about={objURI}/>
         </lemon:objOfProp>
       </lemon:LexicalSense>
    </lemon:sense> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("adjective",lemma.toString(),Some("frame"))}>
        <rdf:type rdf:resource={lexinfo("AdjectivePropertyModifyingFrame")}/>
        <lexinfo:copulativeSubject rdf:resource={subjURI}/>
        <lexinfo:attributeArg rdf:resource={objURI}/>
      </lemon:Frame>
    </lemon:synBehavior>
  }
}
                                      
case class RelationalAdjective(val lemma : AP,
                               val property : URI = null,
                               val relationalArg : Arg,
                               val forms : Seq[Form] = Nil,
                               val register : Option[Register] = None) extends Adjective {
  def makeWithForm(form : Form) = RelationalAdjective(lemma,property,relationalArg,forms :+ form,register)
  protected def makeWithForms(otherForms : Seq[Form]) = RelationalAdjective(lemma,property,relationalArg,forms ++ otherForms,register)
  protected def senseXML(namer : URINamer) = {
    val subjURI = namer("adjective",lemma.toString(),Some("subject"))
    val objURI = namer("adjective",lemma.toString(),Some("attributive"))
    <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("adjective",lemma.toString(),Some("sense"))}>
         <lemon:reference>
           <rdf:Property rdf:about={property}/>
         </lemon:reference>
         {registerXML(register)}
         <lemon:subjOfProp>
            <lemon:Argument rdf:about={subjURI}/>
         </lemon:subjOfProp>
         <lemon:objOfProp>
            <lemon:Argument rdf:about={objURI}/>
         </lemon:objOfProp>
        { 
          if(relationalArg.restriction != None) {
            <lemon:propertyDomain rdf:resource={relationalArg.restriction.get}/>
          }
        }
       </lemon:LexicalSense>
    </lemon:sense> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("adjective",lemma.toString(),Some("frame"))}>
        <rdf:type rdf:resource={lexinfo("AdjectivePPFrame")}/>
        <lexinfo:copulativeSubject rdf:resource={subjURI}/>
        { relationalArg.toXML(objURI,namer) }
      </lemon:Frame>
    </lemon:synBehavior>
  }
}

                               
case class ScalarAdjective(val lemma : AP,
                           val scalarMemberships : Seq[ScalarMembership] = Nil,
                           val forms : Seq[Form] = Nil,
                           val register : Option[Register] = None) extends Adjective {   
  def makeWithForm(form : Form) = ScalarAdjective(lemma,scalarMemberships,forms :+ form,register)
  protected def makeWithForms(otherForms : Seq[Form]) = ScalarAdjective(lemma,scalarMemberships,forms ++ otherForms,register)
  protected def senseXML(namer : URINamer) = {
    val subjURI = namer("adjective",lemma.toString(),Some("subject"))
    val scaleSubjURI = namer("adjective",lemma.toString(),Some("scaleSubj"))
    val scaleObjURI = namer("adjective",lemma.toString(),Some("scaleObj"))
    (for(ScalarMembership(property,forClass,boundary,direction,boundary2) <- scalarMemberships) yield {
    <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("adjective",lemma.toString(),Some("sense"))}>
         <lemon:reference>
            <owl:Class>
              { 
                if(forClass != null) {
                 <rdfs:subClassOf rdf:resource={forClass}/>
                }
              }
              {
                if(!boundary.isNaN) {
              <owl:equivalentClass>
                <owl:Restriction>
                  <owl:onProperty rdf:resource={property}/>
                  <owl:someValuesFrom>
                    <rdfs:Datatype>
                      <owl:withRestrictions rdf:parseType="Collection">
                        <rdf:Description>{            if(direction == positive) {
                            <xsd:minExclusive>{boundary}</xsd:minExclusive>
                          } else if(direction == central) {
                            <xsd:minExclusive>{boundary}</xsd:minExclusive>
                            <xsd:maxExclusive>{boundary2}</xsd:maxExclusive>
                          } else {
                            <xsd:maxExclusive>{boundary}</xsd:maxExclusive> 
                          }
                        }</rdf:Description>
                      </owl:withRestrictions>
                    </rdfs:Datatype>
                  </owl:someValuesFrom>
                </owl:Restriction>
              </owl:equivalentClass>
                }
            }
            <oils:boundTo rdf:resource={property}/>
            {
              if(direction == positive) {
                <rdfs:subClassOf rdf:resource="http://lemon-model.net/oils#CovariantScalar"/>
              } else {
                <rdfs:subClassOf rdf:resource="http://lemon-model.net/oils#ContravariantScalar"/>
              }
            }
            </owl:Class>
         </lemon:reference>
         {registerXML(register)}
         <lemon:isA>
            <lemon:Argument rdf:about={subjURI}/>
         </lemon:isA>
       </lemon:LexicalSense>
    </lemon:sense> :+
    <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("adjective",lemma.toString(),Some("sense"))}>
          <lemon:reference rdf:resource={property}/>
          <lemon:subjOfProp rdf:resource={scaleSubjURI}/>
          <lemon:objOfProp rdf:resource={scaleObjURI}/>
      </lemon:LexicalSense>
    </lemon:sense>
    }).flatten :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("adjective",lemma.toString(),Some("predFrame"))}>
        <rdf:type rdf:resource={lexinfo("AdjectivePredicativeFrame")}/>
        <lexinfo:copulativeSubject rdf:resource={subjURI}/>
      </lemon:Frame>
    </lemon:synBehavior> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("adjective",lemma.toString(),Some("attrFrame"))}>
        <rdf:type rdf:resource={lexinfo("AdjectiveAttributiveFrame")}/>
        <lexinfo:attributiveArg rdf:resource={subjURI}/>
      </lemon:Frame>
    </lemon:synBehavior> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("adjective",lemma.toString(),Some("attrFrame"))}>
        <rdf:type rdf:resource={lexinfo("AdjectiveScaleFrame")}/>
        <lexinfo:copulativeSubject rdf:resource={scaleSubjURI}/>
        <lexinfo:adverbialComplement rdf:resource={scaleObjURI}/>
      </lemon:Frame>
    </lemon:synBehavior>
  }
}

/*case class ScalarQuantifyingAdjective(val lemma : AP,
                                      val scalarMembership : ScalarMembership,
                                      val forms : Seq[Form] = Nil) extends Adjective {
  def makeWithForm(form : Form) = ScalarQuantifyingAdjective(lemma,scalarMembership,forms :+ form)
  protected def makeWithForms(otherForms : Seq[Form]) = ScalarQuantifyingAdjective(lemma,scalarMembership,forms ++ otherForms)
  protected def senseXML(namer : URINamer) = {
    val subjURI = namer("adjective",lemma.toString(),Some("subject"))
    val objURI = namer("adjective",lemma.toString(),Some("object"))
    <lemon:sense>
      <lemon:LexicalSense rdf:about={namer("adjective",lemma.toString(),Some("sense"))}>
         <lemon:reference>
            <owl:DatatypeProperty rdf:resource={scalarMembership.property}>
              {
                if(!scalarMembership.boundary.isNaN) {
              <rdfs:range>
                <owl:Restriction>
                  <owl:onProperty rdf:resource={scalarMembership.property}/>
                  <owl:someValuesForm>
                    <rdfs:Datatype>
                      <owl:withRestrictions rdf:parseType="Collection">
                        <rdf:Description>{
                          if(scalarMembership.direction == positive) {
                            <xsd:minExclusive>{scalarMembership.boundary}</xsd:minExclusive>
                          } else {
                            <xsd:maxExclusive>{scalarMembership.boundary}</xsd:maxExclusive> 
                          }
                        }</rdf:Description>
                      </owl:withRestrictions>
                    </rdfs:Datatype>
                  </owl:someValuesForm>
                </owl:Restriction>
              </rdfs:range>
                }
            }
            </owl:DatatypeProperty>
         </lemon:reference>
         <lemon:subjOfProp>
            <lemon:Argument rdf:about={subjURI}/>
         </lemon:subjOfProp>
         <lemon:objOfProp>
            <lemon:Argument rdf:about={subjURI}/>
         </lemon:objOfProp>
       </lemon:LexicalSense>
    </lemon:sense> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("adjective",lemma.toString(),Some("frame"))}>
        <rdf:type rdf:resource={lexinfo("AdjectiveScaleFrame")}/>
        <lexinfo:copulativeSubject rdf:resource={subjURI}/>
        <lexinfo:adverbialComplement rdf:resource={objURI}/>
      </lemon:Frame>
    </lemon:synBehavior>
  }
}*/
/*
case class ScalarParticleAdjective(val lemma : AP,
                           val scalarMemberships : Seq[ScalarMembership] = Nil,
                           val forms : Seq[Form] = Nil) extends Adjective[ScalarParticleAdjective] {
                             
  def makeWithForm(form : Form) = ScalarAdjective(lemma,scalarMemberships,forms :+ form)
  protected def makeWithForms(otherForms : Seq[Form]) = ScalarAdjective(lemma,scalarMemberships,forms ++ otherForms)
  protected def senseXML(namer : URINamer) = {
    val subjURI = namer("adjective",lemma.toString(),Some("subject"))
    val subjURI = namer("adjective",lemma.toString(),Some("object"))
    <lemon:sense>
    {
      for(ScalarMembership(property,boundary,direction) <- scalarMemberships) yield {
      <lemon:LexicalSense rdf:about={namer("adjective",lemma.toString(),Some("sense"))}>
         <lemon:reference>
            <rdfs:Datatype>
                <owl:withRestrictions rdf:parseType="Collection">
                <rdf:Description>{
                  if(direction == positive) {
                    <xsd:minExclusive>{boundary}</xsd:minExclusive>
                  } else {
                    <xsd:maxExclusive>{boundary}</xsd:maxExclusive>
                  }
                }
                    </rdf:Description>
                </owl:withRestrictions>
            </rdfs:Datatype>
         </lemon:reference>
         <lemon:isA>
            <lemon:Argument rdf:about={subjURI}/>
         </lemon:isA>
       </lemon:LexicalSense>
      }
    }
    </lemon:sense> :+
    <lemon:synBehavior>
      <lemon:Frame rdf:about={namer("adjective",lemma.toString(),Some("frame"))}>
        <rdf:type rdf:resource={lexinfo("AdjectivePredicativeFrame")}/>
        <lexinfo:subject rdf:resource={subjURI}/>
      </lemon:Frame>
      <lemon:Frame rdf:about={namer("adjective",lemma.toString(),Some("frame"))}>
        <rdf:type rdf:resource={lexinfo("AdjectiveAttributiveFrame")}/>
        <lexinfo:attributiveArg rdf:resource={subjURI}/>
      </lemon:Frame>
      <lemon:Frame rdf:about={namer("adjective",lemma.toString(),Some("frame"))}>
        <rdf:type rdf:resource={lexinfo("AdjectiveComparativeFrame")}/>
        <lexinfo:subject rdf:resource={subjURI}/>
        <lexinfo:comparativeAdjunct rdf:resource={objURI}/>
      </lemon:Frame>
      <lemon:Frame rdf:about={namer("adjective",lemma.toString(),Some("frame"))}>
        <rdf:type rdf:resource={lexinfo("AdjectiveSuperlativeFrame")}/>
        <lexinfo:superlativeAdjunct rdf:resource={objURI}/>
      </lemon:Frame>
    </lemon:synBehavior>
  }
}*/
