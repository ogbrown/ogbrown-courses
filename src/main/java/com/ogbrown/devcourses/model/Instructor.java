package com.ogbrown.devcourses.model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;
@Cacheable(value="deviceCache")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
@Entity
public class Instructor implements Serializable {
    public final static Long DEFAULT_INSTRUCTOR_ID = 9999L;
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String middleInitial;
	private String lastName;
	private String suffix;
	private String nickname;
	private String email;
	@Column(name="emailInst")
	private String emailInstructions;
	private String phone;
	@Column(name="phoneInst")
	private String phoneInstructions;
	private String website;
	
	public Instructor() {
		// TODO Auto-generated constructor stub
	}

	public Instructor(String firstName, String middleInitial, String lastName, String suffix, String nickname,
			String email, String phone) {
		super();
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
		this.suffix = suffix;
		this.nickname = nickname;
		this.email = email;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailInstructions() {
		return emailInstructions;
	}

	public void setEmailInstructions(String emailInstructions) {
		this.emailInstructions = emailInstructions;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneInstructions() {
		return phoneInstructions;
	}

	public void setPhoneInstructions(String phoneInstructions) {
		this.phoneInstructions = phoneInstructions;
	}
	
	

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}


	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((emailInstructions == null) ? 0 : emailInstructions.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((middleInitial == null) ? 0 : middleInitial.hashCode());
        result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((phoneInstructions == null) ? 0 : phoneInstructions.hashCode());
        result = prime * result + ((suffix == null) ? 0 : suffix.hashCode());
        result = prime * result + ((website == null) ? 0 : website.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Instructor other = (Instructor) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (emailInstructions == null) {
            if (other.emailInstructions != null)
                return false;
        } else if (!emailInstructions.equals(other.emailInstructions))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (middleInitial == null) {
            if (other.middleInitial != null)
                return false;
        } else if (!middleInitial.equals(other.middleInitial))
            return false;
        if (nickname == null) {
            if (other.nickname != null)
                return false;
        } else if (!nickname.equals(other.nickname))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (phoneInstructions == null) {
            if (other.phoneInstructions != null)
                return false;
        } else if (!phoneInstructions.equals(other.phoneInstructions))
            return false;
        if (suffix == null) {
            if (other.suffix != null)
                return false;
        } else if (!suffix.equals(other.suffix))
            return false;
        if (website == null) {
            if (other.website != null)
                return false;
        } else if (!website.equals(other.website))
            return false;
        return true;
    }

    @Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", middleInitial=" + middleInitial + ", lastName="
				+ lastName + ", suffix=" + suffix + ", nickname=" + nickname + ", email=" + email
				+ ", emailInstructions=" + emailInstructions + ", phone=" + phone + ", phoneInstructions="
				+ phoneInstructions + ", website=" + website + "]";
	}



	
}
