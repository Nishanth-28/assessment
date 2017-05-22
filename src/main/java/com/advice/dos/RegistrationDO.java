/**
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.dos;

import java.util.Date;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author cjp
 */
@Document
public class RegistrationDO {

    @Id
    private String id;

    private String firstName;
    private String middleName;
    private String lastName;
    private String emailId;
    private String organazationName;
    private Map<String,String> employee;
     private String employees;
    private Map<String,String> designation;
     private String designations;
    private Map<String,String> country;
     private String countries;
    private  Map<String,String> state;
     private String states;
    private String phone;
    private Map<String,String> publicity;
     private String publicities;
    private String other;
    private  Map<String,String> cloudAdaption;
     private String cloudAdaptions;
    private String userId;
    private String pass1;
    private String pass2;
    private String role;
    private String active;
    private String organizationFirst;
    private String organizationEmployees;
     private  Map<String,String> annualIncome;
    private String revenue;
    private String organizationEmailId;
    private String  location;
    private String adminId;

   
    private Date creationDate;
    private Date modificationDate;
    
    public Date getCreationDate() {
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }


    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     *
     * @param middleName
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     *
     * @param emailId
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     *
     * @return
     */
    public String getOrganazationName() {
        return organazationName;
    }

    /**
     *
     * @param organazationName
     */
    public void setOrganazationName(String organazationName) {
        this.organazationName = organazationName;
    }

    /**
     *
     * @return
     */
    public Map<String,String> getEmployee() {
        return employee;
    }

    /**
     *
     * @param employee
     */
    public void setEmployee(Map<String,String> employee) {
        this.employee = employee;
    }

    /**
     *
     * @return
     */
    public String getEmployees() {
        return employees;
    }

    /**
     *
     * @param employees
     */
    public void setEmployees(String employees) {
        this.employees = employees;
    }

    /**
     *
     * @return
     */
    public Map<String,String> getDesignation() {
        return designation;
    }

    /**
     *
     * @param designation
     */
    public void setDesignation(Map<String,String> designation) {
        this.designation = designation;
    }

    /**
     *
     * @return
     */
    public String getDesignations() {
        return designations;
    }

    /**
     *
     * @param designations
     */
    public void setDesignations(String designations) {
        this.designations = designations;
    }

    /**
     *
     * @return
     */
    public Map<String,String> getCountry() {
        return country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(Map<String,String> country) {
        this.country = country;
    }

    /**
     *
     * @return
     */
    public String getCountries() {
        return countries;
    }

    /**
     *
     * @param countries
     */
    public void setCountries(String countries) {
        this.countries = countries;
    }

    /**
     *
     * @return
     */
    public Map<String,String> getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(Map<String,String> state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
    public String getStates() {
        return states;
    }

    /**
     *
     * @param states
     */
    public void setStates(String states) {
        this.states = states;
    }

    /**
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     */
    public Map<String,String> getPublicity() {
        return publicity;
    }

    /**
     *
     * @param publicity
     */
    public void setPublicity(Map<String,String> publicity) {
        this.publicity = publicity;
    }

    /**
     *
     * @return
     */
    public String getPublicities() {
        return publicities;
    }

    /**
     *
     * @param publicities
     */
    public void setPublicities(String publicities) {
        this.publicities = publicities;
    }

    /**
     *
     * @return
     */
    public String getOther() {
        return other;
    }

    /**
     *
     * @param other
     */
    public void setOther(String other) {
        this.other = other;
    }

    /**
     *
     * @return
     */
    public Map<String,String> getCloudAdaption() {
        return cloudAdaption;
    }

    /**
     *
     * @param cloudAdaption
     */
    public void setCloudAdaption(Map<String,String> cloudAdaption) {
        this.cloudAdaption = cloudAdaption;
    }

    /**
     *
     * @return
     */
    public String getCloudAdaptions() {
        return cloudAdaptions;
    }

    /**
     *
     * @param cloudAdaptions
     */
    public void setCloudAdaptions(String cloudAdaptions) {
        this.cloudAdaptions = cloudAdaptions;
    }

    /**
     *
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     */
    public String getPass1() {
        return pass1;
    }

    /**
     *
     * @param pass1
     */
    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    /**
     *
     * @return
     */
    public String getPass2() {
        return pass2;
    }

    /**
     *
     * @param pass2
     */
    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    /**
     *
     * @return
     */
    public String getRole() {
        return role;
    }

    /**
     *
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     *
     * @return
     */
    public String getActive() {
        return active;
    }

    /**
     *
     * @param active
     */
    public void setActive(String active) {
        this.active = active;
    }

    /**
     *
     * @return
     */
    public String getOrganizationFirst() {
        return organizationFirst;
    }

    /**
     *
     * @param organizationFirst
     */
    public void setOrganizationFirst(String organizationFirst) {
        this.organizationFirst = organizationFirst;
    }

    /**
     *
     * @return
     */
    public String getOrganizationEmployees() {
        return organizationEmployees;
    }

    /**
     *
     * @param organizationEmployees
     */
    public void setOrganizationEmployees(String organizationEmployees) {
        this.organizationEmployees = organizationEmployees;
    }

    /**
     *
     * @return
     */
    public String getRevenue() {
        return revenue;
    }

    /**
     *
     * @param revenue
     */
    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    /**
     *
     * @return
     */
    public String getOrganizationEmailId() {
        return organizationEmailId;
    }

    /**
     *
     * @param organizationEmailId
     */
    public void setOrganizationEmailId(String organizationEmailId) {
        this.organizationEmailId = organizationEmailId;
    }

    /**
     *
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String,String> getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(Map<String,String> annualIncome) {
        this.annualIncome = annualIncome;
    }

     public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
   

}
