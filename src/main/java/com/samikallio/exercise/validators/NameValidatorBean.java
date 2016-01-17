/*
 * This bean validates the first and last name the user entered to the form
 */
package com.samikallio.exercise.validators;

import com.sun.istack.logging.Logger;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

/**
 *
 * @author Sami Kallio <sami.m.j.kallio at student.jyu.fi>
 */
@Named(value = "NameValidatorBean")
@RequestScoped
public class NameValidatorBean {
    
    private final static Logger LOGGER = Logger.getLogger(NameValidatorBean.class);
    
    /*
    * There can be (to my knowledge) up to four names for a person in Finland.
    * For example, first name Veli-Matti (two first names) Ylä-Oja-Ala-Viita (four last names)
    */
    private final static int MAX_SPLITTED_NAMES = 4;
    
    private final static String requiredField = "*";
    
    //Validation error codes
    private final static int OK = 0;
    private final static int TOO_SHORT = -1;
    private final static int TOO_LONG = -2;
    private final static int TOO_MANY_SPLITS = -3;
    private final static int VALIDATION_ERROR = -4;
    private final static int VALIDATION_ERROR_IN_SPLIT_NAMES = -5;
    
    //Validation error message 
    private final static String NAME_VALIDATION_ERROR = "nameValidationError";
    private final static String NAME_TOO_LONG = "nameTooLong";
    private final static String NAME_TOO_SHORT = "nameTooShort";
    private final static String NAME_OTHER_PARTS_VALIDATION_ERROR = "nameOtherPartsValidationError";
    
    //Validation ok message
    private final static String VALIDATED_OK = "validatedOk";

    //These limits are not based on any actual research, I just picked them
    //from the hat
    private final static int NAME_MAX_LENGTH = 30;
    private final static int NAME_MIN_LENGTH = 2;
    /*
     * Validates names against small letters a-ö and big letters A-Ö.
     * Also, there might be a dash in a name, eg. "Veli-Matti", so it also
     * must be validated. This won't be built into pattern-matching, String.split
     * will be used and validate the two names separately
     */
    private final static String NAME_PATTERN = "^[a-öA-Ö]{" + NAME_MIN_LENGTH + "," + NAME_MAX_LENGTH + "}$";
    private final Pattern namePattern;
    private Matcher matcher;
    private boolean isValidMessage;
    
    private FacesMessage message;
    private final ResourceBundle resources;
    
    
    public NameValidatorBean() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Locale locale = ctx.getViewRoot().getLocale();
        resources = ResourceBundle.getBundle(
                "com.samikallio.exercise.messages.ValidationMessages", locale);
        namePattern = Pattern.compile(NAME_PATTERN);
    }
    
    private boolean nameMatchesValidationRules(String name) {
        matcher = namePattern.matcher(name);
        return matcher.matches();
    }
    
    private int validateName(String name) {
        
        if(name.length() > NAME_MAX_LENGTH) {
            return TOO_LONG;
        } else if(name.length() < NAME_MIN_LENGTH) {
            return TOO_SHORT;
        } else {
            String[] splittedName = name.split("-");
            
            if(splittedName.length > MAX_SPLITTED_NAMES) {
                return TOO_MANY_SPLITS;
            }
            
            for (int i = 0; i < splittedName.length; i++) {
                if (!nameMatchesValidationRules(splittedName[i])) {
                    if(i > 0) {
                        return VALIDATION_ERROR_IN_SPLIT_NAMES;
                    } else 
                        return VALIDATION_ERROR;
                } 
            }
           
            /*
            * If we got so far, the names were ok. Now we just have to check
            * that the name does not end to a "-"
            */
            
            if(name.endsWith("-"))
                return VALIDATION_ERROR;
        }
        
        return OK;
    }
    
    private void validateName(FacesContext c, UIComponent toValidate, Object value) {
        
        String input = (String)value;
        /*
        * default value is that message is valid. The matcher patterns
        * will set it to false, should the input break any validation rules
        */
        isValidMessage = true;
        
        switch(validateName(input)) {
            case OK:
                //nothing needs to be done, name was ok
            break;
            
            case TOO_LONG:
                message = nameValidationError(NAME_TOO_LONG);
                isValidMessage = false;
            break;
            
            case TOO_SHORT:
                message = nameValidationError(NAME_TOO_SHORT);
                isValidMessage = false;
            break;
            
            case VALIDATION_ERROR:
                message = nameValidationError(NAME_VALIDATION_ERROR);
                isValidMessage = false;
            break;
            
            case VALIDATION_ERROR_IN_SPLIT_NAMES:
                message = nameValidationError(NAME_OTHER_PARTS_VALIDATION_ERROR);
                isValidMessage = false;
            break;
                   
        }
        
        ((UIInput)toValidate).setValid(isValidMessage);
        
        if(!isValidMessage) {
            c.addMessage(toValidate.getClientId(c), message);
        } else {
            c.addMessage(toValidate.getClientId(c), nameValidatedOk(VALIDATED_OK));
        }
    }
    
    public void validateFirstName(FacesContext c, UIComponent toValidate, Object value) {
        validateName(c, toValidate, value);
    }
    
    public void validateLastName(FacesContext c, UIComponent toValidate, Object value) {
        validateName(c, toValidate, value);
    }
    
    private FacesMessage nameValidationError(String msg) {
        return new FacesMessage(FacesMessage.SEVERITY_ERROR, resources.getString(msg), null);
    }
    
    private FacesMessage nameValidatedOk(String msg) {
        return new FacesMessage(FacesMessage.SEVERITY_INFO, resources.getString(msg), null);
    }
}
