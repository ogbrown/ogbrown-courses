/*
 * Copyright (c) 2017 - 2019 Oswald G. Brown, III
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ogbrown.devcourses.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Instructor that = (Instructor) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(firstName, that.firstName) &&
				Objects.equals(middleInitial, that.middleInitial) &&
				Objects.equals(lastName, that.lastName) &&
				Objects.equals(suffix, that.suffix) &&
				Objects.equals(nickname, that.nickname) &&
				Objects.equals(email, that.email) &&
				Objects.equals(emailInstructions, that.emailInstructions) &&
				Objects.equals(phone, that.phone) &&
				Objects.equals(phoneInstructions, that.phoneInstructions) &&
				Objects.equals(website, that.website);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, middleInitial, lastName, suffix, nickname, email, emailInstructions, phone, phoneInstructions, website);
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", middleInitial=" + middleInitial + ", lastName="
				+ lastName + ", suffix=" + suffix + ", nickname=" + nickname + ", email=" + email
				+ ", emailInstructions=" + emailInstructions + ", phone=" + phone + ", phoneInstructions="
				+ phoneInstructions + ", website=" + website + "]";
	}



	
}
