/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    @EJB
    private UserEnterpriseBeanLocal userEJB;
    
    private UserEntity userEntity;

    public UserManagedBean() {
        
    }
    
}
