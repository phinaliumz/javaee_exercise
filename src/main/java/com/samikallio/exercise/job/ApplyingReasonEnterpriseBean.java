package com.samikallio.exercise.job;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is the class that implements the EnterpriseBean local interface, 
 * and takes care of database operations for applyingReasonEntity
 * 
 * @author Sami Kallio <sami.m.j.kallio at student.jyu.fi>
 */
@Stateless
public class ApplyingReasonEnterpriseBean implements ApplyingReasonEnterpriseBeanLocal {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public ApplyingReasonEntity persistReason(ApplyingReasonEntity reason) {
        em.persist(reason);
        return reason;
    }
}
