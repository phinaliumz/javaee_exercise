/*
 * This class has the common limits for validation.
 */
package com.samikallio.exercise.validators;

/**
 *
 * @author Sami Kallio <sami..m.j.kallio at student.jyu.fi>
 */
public class Constraints {
    
    //These limits are not based on any actual research, I just picked them
    //from the hat
    public final static int NAME_MAX_LENGTH = 30; //first and last name max length
    public final static int NAME_MIN_LENGTH = 2;  // ------""---------- min length
    public final static int REASON_MIN_LENGTH = 1; //reason min length
    public final static int REASON_MAX_LENGTH = 500; //reason max length
    
    public Constraints() {
        
    }
    
}
