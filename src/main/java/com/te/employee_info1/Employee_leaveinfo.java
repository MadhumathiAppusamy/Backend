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
@Table(name = "E_leaveinfo")
public class Employee_leaveinfo {
public Employee_leaveinfo(int employee_Id, String leave_Date) {
		super();
		Employee_Id = employee_Id;
		Leave_Date = leave_Date;
	}
	@Id
	private int Leave_ID ;
	@Column(name = "E_id")
	private int Employee_Id;
	@Column(name = "E_date")
	private String Leave_Date;
	@Column(name = "E_status")
	private String Leave_Status="pending";

	

}
