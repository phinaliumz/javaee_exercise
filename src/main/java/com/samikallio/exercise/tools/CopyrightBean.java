/*
 * This class is responsible about copyright-notice on the bottom of the 
 * webpages in the application
 */
package com.samikallio.exercise.tools;

import java.util.Calendar;
import javax.inject.Named;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Sami Kallio <sami.m.j.kallio at student.jyu.fi>
 */
@Named(value = "copyrightBean")
@RequestScoped
public class CopyrightBean {
    
    private static final String COPYRIGHT_SIGN = "\u00a9";
    private final Calendar calendar;
    
    private String copyrightClause;
    
    public CopyrightBean() {
        calendar = Calendar.getInstance();
    }

    public String getCopyrightClause() {
        return COPYRIGHT_SIGN + " " + calendar.get(Calendar.YEAR);
    }
    
    
}
