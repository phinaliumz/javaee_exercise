/*
 * This bean reads the userEntity id from Session-map and then retrieves
 * the data from the database
 */
package com.samikallio.exercise.user;

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
    
    public ReviewInformationManagedBean() {
        
    }
    
    @PostConstruct 
    public void init() {
        if(userEntity == null) {
            userId = (Integer)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId");
            if(userId == null) {
                
            } else {
                userEntity = userEJB.findUserById(userId);
            }
                
        }
    }
    
    public String getFirstName() { return userEntity.getFirstName(); }
    public String getLastName() { return userEntity.getLastName(); }
    public String getGender() {
        if(userEntity.getIsFemale()) {
            return "Nainen";
        } else {
            return "Mies";
        }
    }
    
    public String getReason() { return userEntity.getReason().getReason(); }
    
}
