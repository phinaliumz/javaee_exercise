package com.samikallio.exercise.user;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 * This is bean, which manages user information in the JSF-pages
 * 
 * @author Sami Kallio <sami.m.j.kallio at student.jyu.fi>
 */
@Named(value = "userBean")
@RequestScoped
public class UserManagedBean {
    
    /**
     * Enterprise bean for database transactions
     */
    @EJB
    private UserEnterpriseBeanLocal userEJB;
    
    /**
     * Entity, which is the data that will be persisted to database
     */
    private UserEntity userEntity;
    
    /*
     * Variables for webpage information 
     */
    private String firstName;
    private String lastName;
    private Boolean isFemale;
    private String reasonForApplying;

    public UserManagedBean() {
        
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getIsFemale() {
        return isFemale;
    }

    public void setIsFemale(Boolean isFemale) {
        this.isFemale = isFemale;
    }

    public String getReasonForApplying() {
        return reasonForApplying;
    }

    public void setReasonForApplying(String reasonForApplying) {
        this.reasonForApplying = reasonForApplying;
    }
}
