package bg.infosys.daeu.ws.wsbg.regix.responses.bulstat;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.Event;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.Subject;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.SubjectPropAccountingRecordForm;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.SubjectPropActivityDate;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.SubjectPropActivityKID2003;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.SubjectPropActivityKID2008;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.SubjectPropCollectiveBody;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.SubjectPropFundingSource;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.SubjectPropInstallments;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.SubjectPropLifeTime;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.SubjectPropOwnershipForm;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.SubjectPropProfession;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.SubjectPropRepresentationType;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.SubjectPropScopeOfActivity;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.SubjectPropState;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.SubjectRelAssignee;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.SubjectRelBelonging;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.SubjectRelManager;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.SubjectRelPartner;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StateOfPlay", namespace = "http://www.bulstat.bg/StateOfPlay", propOrder = {
 "subject",
 "event",
 "representationType",
 "scopeOfActivity",
 "mainActivity2008",
 "mainActivity2003",
 "installments",
 "lifeTime",
 "accountingRecordForm",
 "ownershipForms",
 "fundingSources",
 "state",
 "managers",
 "partners",
 "assignee",
 "belonging",
 "collectiveBodies",
 "activityDate",
 "additionalActivities2008",
 "professions"
})
@XmlRootElement(name = "StateOfPlay")
public class StateOfPlay {

 @XmlElement(name = "Subject", required = true)
 protected Subject subject;
 @XmlElement(name = "Event")
 protected Event event;
 @XmlElement(name = "RepresentationType")
 protected SubjectPropRepresentationType representationType;
 @XmlElement(name = "ScopeOfActivity")
 protected SubjectPropScopeOfActivity scopeOfActivity;
 @XmlElement(name = "MainActivity2008")
 protected SubjectPropActivityKID2008 mainActivity2008;
 @XmlElement(name = "MainActivity2003")
 protected SubjectPropActivityKID2003 mainActivity2003;
 @XmlElement(name = "Installments")
 protected List<SubjectPropInstallments> installments;
 @XmlElement(name = "LifeTime")
 protected SubjectPropLifeTime lifeTime;
 @XmlElement(name = "AccountingRecordForm")
 protected SubjectPropAccountingRecordForm accountingRecordForm;
 @XmlElement(name = "OwnershipForms")
 protected List<SubjectPropOwnershipForm> ownershipForms;
 @XmlElement(name = "FundingSources")
 protected List<SubjectPropFundingSource> fundingSources;
 @XmlElement(name = "State")
 protected SubjectPropState state;
 @XmlElement(name = "Managers")
 protected List<SubjectRelManager> managers;
 @XmlElement(name = "Partners")
 protected List<SubjectRelPartner> partners;
 @XmlElement(name = "Assignee")
 protected SubjectRelAssignee assignee;
 @XmlElement(name = "Belonging")
 protected SubjectRelBelonging belonging;
 @XmlElement(name = "CollectiveBodies")
 protected List<SubjectPropCollectiveBody> collectiveBodies;
 @XmlElement(name = "ActivityDate")
 protected SubjectPropActivityDate activityDate;
 @XmlElement(name = "AdditionalActivities2008")
 protected List<SubjectPropActivityKID2008> additionalActivities2008;
 @XmlElement(name = "Professions")
 protected List<SubjectPropProfession> professions;

 /**
  * Gets the value of the subject property.
  * 
  * @return
  *     possible object is
  *     {@link Subject }
  *     
  */
 public Subject getSubject() {
     return subject;
 }

 /**
  * Sets the value of the subject property.
  * 
  * @param value
  *     allowed object is
  *     {@link Subject }
  *     
  */
 public void setSubject(Subject value) {
     this.subject = value;
 }

 /**
  * Gets the value of the event property.
  * 
  * @return
  *     possible object is
  *     {@link Event }
  *     
  */
 public Event getEvent() {
     return event;
 }

 /**
  * Sets the value of the event property.
  * 
  * @param value
  *     allowed object is
  *     {@link Event }
  *     
  */
 public void setEvent(Event value) {
     this.event = value;
 }

 /**
  * Gets the value of the representationType property.
  * 
  * @return
  *     possible object is
  *     {@link SubjectPropRepresentationType }
  *     
  */
 public SubjectPropRepresentationType getRepresentationType() {
     return representationType;
 }

 /**
  * Sets the value of the representationType property.
  * 
  * @param value
  *     allowed object is
  *     {@link SubjectPropRepresentationType }
  *     
  */
 public void setRepresentationType(SubjectPropRepresentationType value) {
     this.representationType = value;
 }

 /**
  * Gets the value of the scopeOfActivity property.
  * 
  * @return
  *     possible object is
  *     {@link SubjectPropScopeOfActivity }
  *     
  */
 public SubjectPropScopeOfActivity getScopeOfActivity() {
     return scopeOfActivity;
 }

 /**
  * Sets the value of the scopeOfActivity property.
  * 
  * @param value
  *     allowed object is
  *     {@link SubjectPropScopeOfActivity }
  *     
  */
 public void setScopeOfActivity(SubjectPropScopeOfActivity value) {
     this.scopeOfActivity = value;
 }

 /**
  * Gets the value of the mainActivity2008 property.
  * 
  * @return
  *     possible object is
  *     {@link SubjectPropActivityKID2008 }
  *     
  */
 public SubjectPropActivityKID2008 getMainActivity2008() {
     return mainActivity2008;
 }

 /**
  * Sets the value of the mainActivity2008 property.
  * 
  * @param value
  *     allowed object is
  *     {@link SubjectPropActivityKID2008 }
  *     
  */
 public void setMainActivity2008(SubjectPropActivityKID2008 value) {
     this.mainActivity2008 = value;
 }

 /**
  * Gets the value of the mainActivity2003 property.
  * 
  * @return
  *     possible object is
  *     {@link SubjectPropActivityKID2003 }
  *     
  */
 public SubjectPropActivityKID2003 getMainActivity2003() {
     return mainActivity2003;
 }

 /**
  * Sets the value of the mainActivity2003 property.
  * 
  * @param value
  *     allowed object is
  *     {@link SubjectPropActivityKID2003 }
  *     
  */
 public void setMainActivity2003(SubjectPropActivityKID2003 value) {
     this.mainActivity2003 = value;
 }

 /**
  * Gets the value of the installments property.
  * 
  * <p>
  * This accessor method returns a reference to the live list,
  * not a snapshot. Therefore any modification you make to the
  * returned list will be present inside the JAXB object.
  * This is why there is not a <CODE>set</CODE> method for the installments property.
  * 
  * <p>
  * For example, to add a new item, do as follows:
  * <pre>
  *    getInstallments().add(newItem);
  * </pre>
  * 
  * 
  * <p>
  * Objects of the following type(s) are allowed in the list
  * {@link SubjectPropInstallments }
  * 
  * 
  */
 public List<SubjectPropInstallments> getInstallments() {
     if (installments == null) {
         installments = new ArrayList<SubjectPropInstallments>();
     }
     return this.installments;
 }

 /**
  * Gets the value of the lifeTime property.
  * 
  * @return
  *     possible object is
  *     {@link SubjectPropLifeTime }
  *     
  */
 public SubjectPropLifeTime getLifeTime() {
     return lifeTime;
 }

 /**
  * Sets the value of the lifeTime property.
  * 
  * @param value
  *     allowed object is
  *     {@link SubjectPropLifeTime }
  *     
  */
 public void setLifeTime(SubjectPropLifeTime value) {
     this.lifeTime = value;
 }

 /**
  * Gets the value of the accountingRecordForm property.
  * 
  * @return
  *     possible object is
  *     {@link SubjectPropAccountingRecordForm }
  *     
  */
 public SubjectPropAccountingRecordForm getAccountingRecordForm() {
     return accountingRecordForm;
 }

 /**
  * Sets the value of the accountingRecordForm property.
  * 
  * @param value
  *     allowed object is
  *     {@link SubjectPropAccountingRecordForm }
  *     
  */
 public void setAccountingRecordForm(SubjectPropAccountingRecordForm value) {
     this.accountingRecordForm = value;
 }

 /**
  * Gets the value of the ownershipForms property.
  * 
  * <p>
  * This accessor method returns a reference to the live list,
  * not a snapshot. Therefore any modification you make to the
  * returned list will be present inside the JAXB object.
  * This is why there is not a <CODE>set</CODE> method for the ownershipForms property.
  * 
  * <p>
  * For example, to add a new item, do as follows:
  * <pre>
  *    getOwnershipForms().add(newItem);
  * </pre>
  * 
  * 
  * <p>
  * Objects of the following type(s) are allowed in the list
  * {@link SubjectPropOwnershipForm }
  * 
  * 
  */
 public List<SubjectPropOwnershipForm> getOwnershipForms() {
     if (ownershipForms == null) {
         ownershipForms = new ArrayList<SubjectPropOwnershipForm>();
     }
     return this.ownershipForms;
 }

 /**
  * Gets the value of the fundingSources property.
  * 
  * <p>
  * This accessor method returns a reference to the live list,
  * not a snapshot. Therefore any modification you make to the
  * returned list will be present inside the JAXB object.
  * This is why there is not a <CODE>set</CODE> method for the fundingSources property.
  * 
  * <p>
  * For example, to add a new item, do as follows:
  * <pre>
  *    getFundingSources().add(newItem);
  * </pre>
  * 
  * 
  * <p>
  * Objects of the following type(s) are allowed in the list
  * {@link SubjectPropFundingSource }
  * 
  * 
  */
 public List<SubjectPropFundingSource> getFundingSources() {
     if (fundingSources == null) {
         fundingSources = new ArrayList<SubjectPropFundingSource>();
     }
     return this.fundingSources;
 }

 /**
  * Gets the value of the state property.
  * 
  * @return
  *     possible object is
  *     {@link SubjectPropState }
  *     
  */
 public SubjectPropState getState() {
     return state;
 }

 /**
  * Sets the value of the state property.
  * 
  * @param value
  *     allowed object is
  *     {@link SubjectPropState }
  *     
  */
 public void setState(SubjectPropState value) {
     this.state = value;
 }

 /**
  * Gets the value of the managers property.
  * 
  * <p>
  * This accessor method returns a reference to the live list,
  * not a snapshot. Therefore any modification you make to the
  * returned list will be present inside the JAXB object.
  * This is why there is not a <CODE>set</CODE> method for the managers property.
  * 
  * <p>
  * For example, to add a new item, do as follows:
  * <pre>
  *    getManagers().add(newItem);
  * </pre>
  * 
  * 
  * <p>
  * Objects of the following type(s) are allowed in the list
  * {@link SubjectRelManager }
  * 
  * 
  */
 public List<SubjectRelManager> getManagers() {
     if (managers == null) {
         managers = new ArrayList<SubjectRelManager>();
     }
     return this.managers;
 }

 /**
  * Gets the value of the partners property.
  * 
  * <p>
  * This accessor method returns a reference to the live list,
  * not a snapshot. Therefore any modification you make to the
  * returned list will be present inside the JAXB object.
  * This is why there is not a <CODE>set</CODE> method for the partners property.
  * 
  * <p>
  * For example, to add a new item, do as follows:
  * <pre>
  *    getPartners().add(newItem);
  * </pre>
  * 
  * 
  * <p>
  * Objects of the following type(s) are allowed in the list
  * {@link SubjectRelPartner }
  * 
  * 
  */
 public List<SubjectRelPartner> getPartners() {
     if (partners == null) {
         partners = new ArrayList<SubjectRelPartner>();
     }
     return this.partners;
 }

 /**
  * Gets the value of the assignee property.
  * 
  * @return
  *     possible object is
  *     {@link SubjectRelAssignee }
  *     
  */
 public SubjectRelAssignee getAssignee() {
     return assignee;
 }

 /**
  * Sets the value of the assignee property.
  * 
  * @param value
  *     allowed object is
  *     {@link SubjectRelAssignee }
  *     
  */
 public void setAssignee(SubjectRelAssignee value) {
     this.assignee = value;
 }

 /**
  * Gets the value of the belonging property.
  * 
  * @return
  *     possible object is
  *     {@link SubjectRelBelonging }
  *     
  */
 public SubjectRelBelonging getBelonging() {
     return belonging;
 }

 /**
  * Sets the value of the belonging property.
  * 
  * @param value
  *     allowed object is
  *     {@link SubjectRelBelonging }
  *     
  */
 public void setBelonging(SubjectRelBelonging value) {
     this.belonging = value;
 }

 /**
  * Gets the value of the collectiveBodies property.
  * 
  * <p>
  * This accessor method returns a reference to the live list,
  * not a snapshot. Therefore any modification you make to the
  * returned list will be present inside the JAXB object.
  * This is why there is not a <CODE>set</CODE> method for the collectiveBodies property.
  * 
  * <p>
  * For example, to add a new item, do as follows:
  * <pre>
  *    getCollectiveBodies().add(newItem);
  * </pre>
  * 
  * 
  * <p>
  * Objects of the following type(s) are allowed in the list
  * {@link SubjectPropCollectiveBody }
  * 
  * 
  */
 public List<SubjectPropCollectiveBody> getCollectiveBodies() {
     if (collectiveBodies == null) {
         collectiveBodies = new ArrayList<SubjectPropCollectiveBody>();
     }
     return this.collectiveBodies;
 }

 /**
  * Gets the value of the activityDate property.
  * 
  * @return
  *     possible object is
  *     {@link SubjectPropActivityDate }
  *     
  */
 public SubjectPropActivityDate getActivityDate() {
     return activityDate;
 }

 /**
  * Sets the value of the activityDate property.
  * 
  * @param value
  *     allowed object is
  *     {@link SubjectPropActivityDate }
  *     
  */
 public void setActivityDate(SubjectPropActivityDate value) {
     this.activityDate = value;
 }

 /**
  * Gets the value of the additionalActivities2008 property.
  * 
  * <p>
  * This accessor method returns a reference to the live list,
  * not a snapshot. Therefore any modification you make to the
  * returned list will be present inside the JAXB object.
  * This is why there is not a <CODE>set</CODE> method for the additionalActivities2008 property.
  * 
  * <p>
  * For example, to add a new item, do as follows:
  * <pre>
  *    getAdditionalActivities2008().add(newItem);
  * </pre>
  * 
  * 
  * <p>
  * Objects of the following type(s) are allowed in the list
  * {@link SubjectPropActivityKID2008 }
  * 
  * 
  */
 public List<SubjectPropActivityKID2008> getAdditionalActivities2008() {
     if (additionalActivities2008 == null) {
         additionalActivities2008 = new ArrayList<SubjectPropActivityKID2008>();
     }
     return this.additionalActivities2008;
 }

 /**
  * Gets the value of the professions property.
  * 
  * <p>
  * This accessor method returns a reference to the live list,
  * not a snapshot. Therefore any modification you make to the
  * returned list will be present inside the JAXB object.
  * This is why there is not a <CODE>set</CODE> method for the professions property.
  * 
  * <p>
  * For example, to add a new item, do as follows:
  * <pre>
  *    getProfessions().add(newItem);
  * </pre>
  * 
  * 
  * <p>
  * Objects of the following type(s) are allowed in the list
  * {@link SubjectPropProfession }
  * 
  * 
  */
 public List<SubjectPropProfession> getProfessions() {
     if (professions == null) {
         professions = new ArrayList<SubjectPropProfession>();
     }
     return this.professions;
 }

}

