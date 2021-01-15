package oneBank;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

import org.springframework.format.annotation.DateTimeFormat;

@Entity // meaning this is linked to a table in the database
@Table(name = "Customer")  // and this is the name of the linked table in the database
public class Customer {
	
	@Id // this is the primary key for this record
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // defines how this id is to be generated....??
	@Column(name="CUSTID")  // The following attribute is linked to this column in the db table
	private long custId;

	@Column(name = "CUSTNAME")  
	@Size (min = 8, max = 45)
	@NotNull
	private String custName;

	@Column()
	@Size (min = 9, max = 9)
	@NotNull
	private String nric;

	@Column(name = "EMAIL")
	@Size (min = 8, max = 30)
	@NotNull
	private String email;

	@Column(name = "PHONENO")
	@Size (min = 8, max = 15)
	@NotNull
	private String phoneNo;

	@Column(name = "ADDR1")  
	@Size (min = 2, max = 30)
	@NotNull
	private String addr1;

	@Column(name = "ADDR2")  
	@Size (min = 2, max = 30)
	@NotNull
	private String addr2;

	@NotNull
	@Column() // when name is not specified, it means db table column name same as attribute variable name
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;

	@Column(name = "ISACTIVE")
	private boolean isActive;
	
	@NotNull
	@Size (min = 6, max = 6)
	@Column(name = "PASSCODE")
	private String passCode;

	@NotNull
	@Column(name="DATEUPD")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateUpd;

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

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
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

	public String getPassCode() {
		return passCode;
	}

	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}

	public LocalDate getDateUpd() {
		return dateUpd;
	}

	public void setDateUpd(LocalDate dateUpd) {
		this.dateUpd = dateUpd;
	}

	
}
