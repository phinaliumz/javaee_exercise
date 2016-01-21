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

    //The reason can only contain letters and numbers in this short exercise. 
    private static final String VALID_REASON_PATTERN = "^[a-öA-Ö0-9]{" + Constraints.REASON_MIN_LENGTH + ","
            + Constraints.REASON_MAX_LENGTH + "}$";
    private final Pattern reasonPattern;
    private String validationError;
    
    //Validation error message 
    private final static String REASON_VALIDATION_ERROR = "reasonValidationError";
    private final static String REASON_TOO_LONG = "reasonTooLong";
    private final static String REASON_TOO_SHORT = "reasonTooShort";
        
    private Matcher matcher;
    
    private FacesMessage message;
    private final ResourceBundle resources;

    public ReasonValidatorBean() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Locale locale = ctx.getViewRoot().getLocale();
        resources = ResourceBundle.getBundle(
                "com.samikallio.exercise.messages.ValidationMessages", locale);
        reasonPattern = Pattern.compile(VALID_REASON_PATTERN);
        
    }
    
    public void validateReason(FacesContext c, UIComponent toValidate, Object value) {
        String reasonCandidate = (String)value;
        boolean isReasonValid = false;
        
        matcher = reasonPattern.matcher(reasonCandidate);
        
        if(!matcher.matches()) {
            if(reasonCandidate.length() > Constraints.REASON_MAX_LENGTH) {
                validationError = resources.getString(REASON_TOO_LONG);
            } else if(reasonCandidate.length() < Constraints.REASON_MIN_LENGTH) {
                validationError = resources.getString(REASON_TOO_SHORT);
            } else {
                validationError = resources.getString(REASON_VALIDATION_ERROR);
            }
            
            ((UIInput)toValidate).setValid(isReasonValid);
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, validationError, null);
            c.addMessage(toValidate.getClientId(c), message);
        }
    }
}
