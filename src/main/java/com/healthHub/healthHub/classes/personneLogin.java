package com.healthHub.healthHub.classes;

import java.util.Date;

public class personneLogin {
	private String lastName;
	private String firstName;
	private Date birthDate;
	private String telephone;
	private String email;
	private String role;
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	private long idHub;

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getIdHub() {
		return idHub;
	}

	public void setIdHub(long idHub) {
		this.idHub = idHub;
	}

	public personneLogin() {
		super();
	}

	public personneLogin(String lastName, String firstName, Date birthDate, String telephone, String email,
			long idHub,String role) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthDate = birthDate;
		this.telephone = telephone;
		this.email = email;
		this.idHub = idHub;
		this.role=role;
	}

}
