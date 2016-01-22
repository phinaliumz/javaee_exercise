package com.samikallio.exercise.user;

import com.samikallio.exercise.job.ApplyingReasonEntity;
import com.samikallio.exercise.validators.Constraints;
import java.io.Serializable;
import java.util.Date;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The user entity. 
 * 
 * There is not a lot of validation here, since most of the validation is done
 * in the view-level
 * 
 * @author Sami Kallio <sami.m.j.kallio at student.jyu.fi>
 */
@Entity
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @NotNull
    @Size(min = Constraints.NAME_MIN_LENGTH, max = Constraints.NAME_MAX_LENGTH)
    private String firstName;
    @NotNull
    @Size(min = Constraints.NAME_MIN_LENGTH, max = Constraints.NAME_MAX_LENGTH)
    private String lastName;
    @NotNull
    private Boolean isFemale;
    
    /*
    * Since user can enter the reason multiple times, this field is saved
    * to database to see which of the reasons is the most current one.
    */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date registrationDate;
    
    @OneToOne(cascade=ALL, mappedBy="user")
    private ApplyingReasonEntity reason;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getIsFemale() {
        return isFemale;
    }

    public void setIsFemale(Boolean isFemale) {
        this.isFemale = isFemale;
    }

    public ApplyingReasonEntity getReason() {
        return reason;
    }

    public void setReason(ApplyingReasonEntity reason) {
        this.reason = reason;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.samikallio.exercise.user.UserEntity[ id=" + id + " ]";
    }
    
}
