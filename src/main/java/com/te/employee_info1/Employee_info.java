package com.te.employee_info1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="E_info")
public class Employee_info {
	@Column(name="E_id")
    @Id
 int Employee_ID;
	@Column(name="E_name")
 String Employee_Name;
	@Column(name="E_type")
 String  Employee_Type;
	@Column(name="E_email")
 String Email;
	@Column(name="E_password")
 String Password;
	
	@Override
	public String toString() {
		return "Employee_info [Employee_ID=" + Employee_ID + ", Employee_Name=" + Employee_Name + ", Employee_Type="
				+ Employee_Type + ", Email=" + Email + ", Password=" + Password + "]";
	}
	
	
}
