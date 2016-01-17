package com.samikallio.exercise.job;

import javax.ejb.Local;

/**
 * Interface for EnterpriseBean, which will take care of database operations
 * for ApplyingReasonEntity
 * @author Sami Kallio <sami.m.j.kallio at student.jyu.fi>
 */
@Local
public interface ApplyingReasonEnterpriseBeanLocal {
    
    public ApplyingReasonEntity persistReason(ApplyingReasonEntity reason);
    
}

