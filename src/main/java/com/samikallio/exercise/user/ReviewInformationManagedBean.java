/*
 * This bean reads the userEntity id from Session-map and then retrieves
 * the data from the database
 */
package com.samikallio.exercise.user;

import com.samikallio.exercise.exception.UserIdNotFound;
import com.sun.istack.logging.Logger;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Sami Kallio <sami.m.j.kallio at student.jyu.fi>
 */
@Named(value = "reviewBean")
@RequestScoped
public class ReviewInformationManagedBean {

    private final static Logger LOGGER = Logger.getLogger(ReviewInformationManagedBean.class);
    
    /**
     * Enterprise bean for database transactions
     */
    @EJB
    private UserEnterpriseBeanLocal userEJB;
  
    /**
     * Entity, which is the data that will be persisted to database
     */
    private UserEntity userEntity;
    
    private Integer userId;
    
    private final ResourceBundle resources;
    
    public ReviewInformationManagedBean() {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        resources = ResourceBundle.getBundle(
                "com.samikallio.exercise.messages.Messages", locale);
    }
    
    @PostConstruct 
    public void init() {
        if(userEntity == null) {
            userId = (Integer)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId");
            if(userId == null) {
                throw new UserIdNotFound("Userid not found!");
            } else {
                userEntity = userEJB.findUserById(userId);
            }
                
        }
    }
    
    public String getFirstName() { return userEntity.getFirstName(); }
    public String getLastName() { return userEntity.getLastName(); }
    public String getGender() {
        if(userEntity.getIsFemale()) {
            return resources.getString("female");
        } else {
            return resources.getString("male");
        }
    }
    
    public String getReason() 
    { 
        if(userId == null)
            return resources.getString("pleaseFillMe");
        else
            return userEntity.getReason().getReason(); 
    }
    
}
