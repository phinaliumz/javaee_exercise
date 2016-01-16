/*
 * This bean validates the reason that user enters for why applying the job
 */
package com.samikallio.exercise.validators;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import com.sun.istack.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

/**
 *
 * @author Sami Kallio <sami.m.j.kallio at student.jyu.fi>
 */
@Named(value = "reasonValidatorBean")
@RequestScoped
public class ReasonValidatorBean {
	
    private final static Logger LOGGER = Logger.getLogger(ReasonValidatorBean.class);

    /*
     * These are the maximum and minimun limits for the reason. 
     * There are not based on any research, I just got them out of my hat 
     */
    private final static int MIN_LENGTH = 1;
    private final static int MAX_LENGTH = 1000;
	
    private Pattern reasonPattern;
        
    private Matcher matcher;
    
    private FacesMessage message;

    public ReasonValidatorBean() {
        
    }
    
    public void validateReason(FacesContext c, UIComponent toValidate, Object value) {
        
    }
    
}
