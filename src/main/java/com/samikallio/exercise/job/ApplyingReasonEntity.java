/*
 * This is the entity, that saves the reason user provided to the database
 */
package com.samikallio.exercise.job;

import com.samikallio.exercise.user.UserEntity;
import com.samikallio.exercise.validators.Constraints;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Here is not a lot of validation, since validation is done in the view-level
 * 
 * @author Sami Kallio <sami.m.j.kallio at student.jyu.fi>
 */
@Entity
public class ApplyingReasonEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @NotNull
    @Lob
    @Column(name="REASON", length=Constraints.REASON_MAX_LENGTH)
    @Size(max = Constraints.REASON_MAX_LENGTH)
    private String reason;
    
    @OneToOne
    private UserEntity user;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ApplyingReasonEntity)) {
            return false;
        }
        ApplyingReasonEntity other = (ApplyingReasonEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.samikallio.exercise.job.JobDescription[ id=" + id + " ]";
    }
    
}
