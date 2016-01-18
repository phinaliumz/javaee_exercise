package com.samikallio.exercise.user;

import com.samikallio.exercise.job.ApplyingReasonEntity;
import com.sun.istack.logging.Logger;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 * This is bean, which manages user information in the JSF-pages
 * 
 * @author Sami Kallio <sami.m.j.kallio at student.jyu.fi>
 */
@Named("userBean")
@RequestScoped
public class UserManagedBean {
    
    private final static Logger LOGGER = Logger.getLogger(UserManagedBean.class);
    
    /**
     * Enterprise bean for database transactions
     */
    @EJB
    private UserEnterpriseBeanLocal userEJB;
  
    /**
     * Entity, which is the data that will be persisted to database
     */
    private UserEntity userEntity;
    
    /**
     * Entity that represents the data of reason for applying the job 
     * in the database
     */
    private ApplyingReasonEntity reasonEntity;
    
    /*
     * Variables for webpage information 
     */
    private String firstName;
    private String lastName;
    private String gender="Male";
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReasonForApplying() {
        return reasonForApplying;
    }

    public void setReasonForApplying(String reasonForApplying) {
        this.reasonForApplying = reasonForApplying;
    }
    
    /*
    * This method is called when user clicks "Submit"-button in the form. The
    * method is responsible for checking the submitted information, and should
    * everything be ok, then save the information to database
    */
    public String submit() {
		
        userEntity = new UserEntity();
        userEntity.setFirstName(this.firstName);
        userEntity.setLastName(this.lastName);
        
        if(this.gender.equals("Male")) {
                userEntity.setIsFemale(false);
        } else {
                userEntity.setIsFemale(true);
        }

        reasonEntity = new ApplyingReasonEntity();
        reasonEntity.setReason(this.reasonForApplying);

        userEntity.setReason(reasonEntity);
        reasonEntity.setUser(userEntity);
        userEntity = userEJB.persistUser(userEntity);
		
        return "success";
    }
}
