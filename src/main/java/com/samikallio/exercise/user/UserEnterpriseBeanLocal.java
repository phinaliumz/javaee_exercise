package com.samikallio.exercise.user;

import javax.ejb.Local;

/**
 * Interface for EnterpriseBean, which will take care of database operations
 * for UserEntity
 * @author Sami Kallio <sami.m.j.kallio at student.jyu.fi>
 */
@Local
public interface UserEnterpriseBeanLocal {
    
    public UserEntity persistUser(UserEntity user);
    
}
