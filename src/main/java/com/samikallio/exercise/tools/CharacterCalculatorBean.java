/*
 * This bean is responsible of calculating how many characters user
 * has entered to the form's textarea
 */
package com.samikallio.exercise.tools;

import com.samikallio.exercise.validators.Constraints;
import com.sun.istack.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Sami Kallio <sami.m.j.kallio at student.jyu.fi>
 */
@Named(value = "characterCalculatorBean")
@SessionScoped
public class CharacterCalculatorBean implements Serializable {
    
    private final static Logger LOGGER = Logger.getLogger(CharacterCalculatorBean.class);
    private final static String CHARACTERS_LEFT_MESSAGE = "charactersLeft";
    
    private static final int PLENTY_CHARACTERS_LEFT = 0;
    private static final int NOT_MUCH_CHARACTERS_LEFT = -1;
    private static final int NO_CHARACTERS_LEFT = -2;
    
    private HtmlInputTextarea reasonArea;

    private int typedChars = 0;
    private int messageType = 0;
    
    private FacesMessage message;
    
    private final ResourceBundle resources;
    
    public CharacterCalculatorBean() {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        resources = ResourceBundle.getBundle(
                "com.samikallio.exercise.messages.Messages", locale);
    }
    
    public void charTyped(AjaxBehaviorEvent event) {
        String content = (String)reasonArea.getValue();
        typedChars = content.length();
        String charactersLeftMessage = "(" + 
			Integer.toString(Constraints.REASON_MAX_CHARS - typedChars) + " " + resources.getString(CHARACTERS_LEFT_MESSAGE)
			+ ")";
        
        //50 percent chars left = PLENTY_CHARACTERS_LEFT
        if(Constraints.REASON_MAX_CHARS - typedChars > Constraints.REASON_MAX_CHARS * 0.5) {
            messageType = PLENTY_CHARACTERS_LEFT;
        } else if(Constraints.REASON_MAX_CHARS - typedChars > Constraints.REASON_MAX_CHARS * 0.25) { //25 percent chars left = NOT_MUCH_CHARACTERS_LEFT
            messageType = NOT_MUCH_CHARACTERS_LEFT;
        } else if((Constraints.REASON_MAX_CHARS - typedChars) < 0) { //zero chars left = NO_CHARACTERS_LEFT
            messageType = NO_CHARACTERS_LEFT;
        }
        
        switch(messageType) {
            case PLENTY_CHARACTERS_LEFT: 
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, charactersLeftMessage, null);
            break;
            case NOT_MUCH_CHARACTERS_LEFT:
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, charactersLeftMessage, null);
            break;
            case NO_CHARACTERS_LEFT:
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, charactersLeftMessage, null);
            break;
        }
        
        
        FacesContext.getCurrentInstance().addMessage(reasonArea.getClientId(FacesContext.getCurrentInstance()), message);
    }

    public HtmlInputTextarea getReasonArea() {
        return reasonArea;
    }

    public void setReasonArea(HtmlInputTextarea reasonArea) {
        this.reasonArea = reasonArea;
    }
}
