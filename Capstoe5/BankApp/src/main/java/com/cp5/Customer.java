package com.cp5;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CUSTID" )
	private long custId;
	
	@Column(name = "CUSTNAME")  
	@Size (min = 8, max = 30)
	@NotNull
	private String custName;
	
	@Column(name = "EMAIL")
	@Size (min = 8, max = 30)
	@NotNull
	@Email(message = " Should be a valid Email Address")
	private String email;
	
	@NotNull
	@Column(name = "DOB")  
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	
	@Column(name = "ISACTIVE")
	private boolean isActive;
	
	@NotNull
	@Column(name = "PHONENO")
	@Size (min = 8, max = 13)
	private String phoneNo;
	
	@NotNull
	@Size (min = 6, max = 6)
	@Column(name = "PASSCODE")
	private String passCode;
	
	
	@Column(name = "POSITIVITY")	
	private int positivity;

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public String getPassCode() {
		return passCode;
	}

	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}

	public int getPositivity() {
		return positivity;
	}

	public void setPositivity(int positivity) {
		this.positivity = positivity;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", email=" + email + ", dob=" + dob
				+ ", isActive=" + isActive + ", phoneNo=" + phoneNo + ", passCode=" + passCode + ", positivity="
				+ positivity + "]";
	}

}
