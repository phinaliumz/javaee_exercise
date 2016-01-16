/*
 * This bean is responsible of calculating how many characters user
 * has entered to the form's textarea
 */
package com.samikallio.exercise.tools;

import com.sun.istack.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
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
    private static final int MAX_CHARS = 500;
    
    private HtmlInputTextarea reasonArea;

    private int typedChars = 0;
    
    private FacesMessage message;
    private FacesContext context;
    
    public CharacterCalculatorBean() {
        
    }
    
    public void charTyped(AjaxBehaviorEvent event) {
        String content = (String)reasonArea.getValue();
        typedChars = content.length();
        LOGGER.log(Level.INFO, "content -> " + content + ", content.length -> " + typedChars);
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, Integer.toString(MAX_CHARS - typedChars), null);
        context = FacesContext.getCurrentInstance();
        context.addMessage(reasonArea.getClientId(context), message);
    }

    public HtmlInputTextarea getReasonArea() {
        return reasonArea;
    }

    public void setReasonArea(HtmlInputTextarea reasonArea) {
        this.reasonArea = reasonArea;
    }
}
