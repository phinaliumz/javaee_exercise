package com.samikallio.exercise.user;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is the class that implements the EnterpriseBean local interface, 
 * and takes care of database operations for UserEntity
 * 
 * @author Sami Kallio <sami.m.j.kallio at student.jyu.fi>
 */
@Stateless
public class UserEnterpriseBean implements UserEnterpriseBeanLocal {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public UserEntity persistUser(UserEntity user) {
        em.persist(user);
        return user;
    }
}
