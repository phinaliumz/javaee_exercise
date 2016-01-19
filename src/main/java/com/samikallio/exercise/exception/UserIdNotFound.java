/*
 * This exception is throwed in ReviewInformationManagedBean, if user
 * navigates to success.xthml before information has been entered.
 */
package com.samikallio.exercise.exception;

/**
 *
 * @author Sami Kallio <sami.m.j.kallio at student.jyu.fi>
 */
public class UserIdNotFound extends RuntimeException {


    public UserIdNotFound() {
        
    }


    public UserIdNotFound(String msg) {
        super(msg);
    }
    
}
