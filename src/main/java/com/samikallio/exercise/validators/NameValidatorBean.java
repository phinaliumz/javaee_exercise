/*
 * This bean validates the first and last name the user entered to the form
 */
package com.samikallio.exercise.validators;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

/**
 *
 * @author sami
 */
@Named(value = "NameValidatorBean")
@RequestScoped
public class NameValidatorBean {

    private final static int FIRSTNAME_MAX_LENGTH = 30;
    private final static int FIRSTNAME_MIN_LENGTH = 2;
    
    private final static int LASTNAME_MAX_LENGTH = 30;
    private final static int LASTNAME_MIN_LENGTH = 2;
    /*
     * Validates firstname against small letters a-z and big letters A-Z
     */
    private final static String FIRSTNAME_PATTERN = "^[a-zA-Z]{" + FIRSTNAME_MIN_LENGTH + "," + FIRSTNAME_MAX_LENGTH + "}$";
    
    /*
    * Validates lastname against small letters a-z and big letters A-Z
    */
    private final static String LASTNAME_PATTERN = "^[a-zA-Z]{" + FIRSTNAME_MIN_LENGTH + "," + FIRSTNAME_MAX_LENGTH + "}$";
    
    private Pattern firstNamePattern;
    private Pattern lastNamePattern;
    private Matcher matcher;
    
    private FacesMessage message;
    
    private final ResourceBundle resources;
    
    public NameValidatorBean() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Locale locale = ctx.getViewRoot().getLocale();
        resources = ResourceBundle.getBundle(
                "com.samikallio.exercise.messages.ErrorMessages", locale);
    }
    
    public void validateFirstname(FacesContext c, UIComponent toValidate, Object value) {
        firstNamePattern = Pattern.compile(FIRSTNAME_PATTERN);
        
        String input = (String)value;
        if(input.length() > FIRSTNAME_MAX_LENGTH) {
            ((UIInput)toValidate).setValid(false);
            message = new FacesMessage(resources.getString("firstNameTooLong"));
        } else if(input.length() < FIRSTNAME_MIN_LENGTH) {
            ((UIInput)toValidate).setValid(false);
            message = new FacesMessage(resources.getString("firstNameTooShort"));
        } else {
            matcher = firstNamePattern.matcher(input);
            if(!matcher.matches()) {
                message = new FacesMessage(resources.getString("firstNameValidationError"));
            }
            ((UIInput)toValidate).setValid(matcher.matches());
        }
        
        if(!((UIInput)toValidate).isValid()) {
            c.addMessage(toValidate.getClientId(c), message);
        }
    }
    
    /*
    * NOT DONE YET!!
    */
    public void validateLastname(FacesContext c, UIComponent toValidate, Object value) {
        firstNamePattern = Pattern.compile(FIRSTNAME_PATTERN);
        
        String input = (String)value;
        if(input.length() > FIRSTNAME_MAX_LENGTH) {
            ((UIInput)toValidate).setValid(false);
            message = new FacesMessage(resources.getString("firstNameTooLong"));
        } else if(input.length() < FIRSTNAME_MIN_LENGTH) {
            ((UIInput)toValidate).setValid(false);
            message = new FacesMessage(resources.getString("firstNameTooShort"));
        } else {
            matcher = firstNamePattern.matcher(input);
            if(!matcher.matches()) {
                message = new FacesMessage(resources.getString("firstNameValidationError"));
            }
            ((UIInput)toValidate).setValid(matcher.matches());
        }
        
        if(!((UIInput)toValidate).isValid()) {
            c.addMessage(toValidate.getClientId(c), message);
        }
    }
    
}
