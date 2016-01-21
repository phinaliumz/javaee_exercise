package com.samikallio.exercise.user;

import com.samikallio.exercise.job.ApplyingReasonEntity;
import com.sun.istack.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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
    * validation of the data is done in the view level, so the validation is 
    * not repeated here. 
    */
    public String submit() {
		
        this.userEntity = new UserEntity();
        this.userEntity.setFirstName(this.firstName);
        this.userEntity.setLastName(this.lastName);
        
        if(this.gender.equals("Male")) {
                this.userEntity.setIsFemale(false);
        } else {
                this.userEntity.setIsFemale(true);
        }

        this.reasonEntity = new ApplyingReasonEntity();
        this.reasonEntity.setReason(this.reasonForApplying);

        this.userEntity.setReason(this.reasonEntity);
        this.reasonEntity.setUser(this.userEntity);
        this.userEntity = userEJB.persistUser(this.userEntity);
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userId", this.userEntity.getId());
		
        return "success";
    }
}
