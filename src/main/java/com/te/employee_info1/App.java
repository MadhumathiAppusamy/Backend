package com.te.employee_info1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.List;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU01");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		int i = 0;
		while (i != 1) {
			System.out.println("select option ");
			System.out.println("press 1 to register employee");
			System.out.println("press 2 to login");
			System.out.println("press 3 to exit");
			Scanner sc = new Scanner(System.in);
			int option = sc.nextInt();

			switch (option) {
			case 1: {
				System.out.println("Enter Employee Id:");
				int id = sc.nextInt();
				System.out.println("Enter Employee Name: ");
				String name = sc.next();
				System.out.println("Enter Employee Type");
				String type = sc.next();
				System.out.println("Enter Employee Email");
				String email = sc.next();
				System.out.println("Enter Employee Password");
				String password = sc.next();
				Employee_info employee = new Employee_info(id, name, type, email, password);
				entityTransaction.begin();
				entityManager.persist(employee);
				entityTransaction.commit();
			}
				break;
			case 2: {
				System.out.println("Enter the Employee id:");
				int id = sc.nextInt();
				System.out.println("Enter the password:");
				String typecheck = "select Employee_Type from Employee_info where Employee_ID=:id";
				Query query = entityManager.createQuery(typecheck);
				query.setParameter("id", id);
				String type = (String) query.getSingleResult();
				String typelow = type.toLowerCase();
				int j = 0;
				while (j != 1) {
					switch (typelow) {
					case "manager": {
						System.out.println("1.Press 1 to show all leave requests");
						System.out.println("2.press 2 to Approve/Reject the leave request");
						System.out.println("3.Press 3 to exit to main menu");
						int option2 = sc.nextInt();
						switch (option2) {
						case 1: {
							String show = "from Employee_leaveinfo";
							Query listQuery = entityManager.createQuery(show);
							List list = listQuery.getResultList();
							for (Object object : list) {
								Employee_leaveinfo employeeleave = (Employee_leaveinfo) object;
								System.out.println("=> " + employeeleave);
							}
						}

							break;
						case 2: {
							System.out.println("Enter the leave id");
							int leaveId = sc.nextInt();
							String leave = "select Leave_Status from Employee_leaveinfo where Leave_ID=:id";
							Query leaveQuery = entityManager.createQuery(leave);
							leaveQuery.setParameter("id", id);
							System.out.println("to Approve Press 1");
							System.out.println("to Reject Press 2");
							int leaveStatus = sc.nextInt();
							String status = "";
							String update = "";
							if (leaveStatus == 1) {
								update = "Approved";
								status = "update Employee_leaveinfo set Leave_Status=:n where Leave_Id=:id";
							} else if (leaveStatus == 2) {
								update = "Rejected";
								status = "update Employee_leaveinfo set Leave_Status=:n where Leave_Id=:id";
							} else
								update = "Pending";
							Query permitQuery = entityManager.createQuery(status);
							permitQuery.setParameter("id", leaveId);
							permitQuery.setParameter("n", update);
							entityTransaction.begin();
							permitQuery.executeUpdate();
							entityTransaction.commit();
						}

							break;
						case 3:
							j = 1;
							break;
						default:
							System.out.println("Invalid Input! Enter Again");
							break;
						}
					}
						break;
					case "employee": {
						System.out.println("1.Press 1 to show status of applied leave requests");
						System.out.println("2.Press 2 to apply new leave request");
						System.out.println("3.Press 3 to exit to main menu");
						int leaveReq = sc.nextInt();
						switch (leaveReq) {
						case 1: {
							String leaveList = "from Employee_leaveinfo where Employee_Id =:id";

							Query leaveQuery = entityManager.createQuery(leaveList);
							leaveQuery.setParameter("id", id);
							List listLeave = leaveQuery.getResultList();
							for (Object object : listLeave) {
								Employee_leaveinfo leave = (Employee_leaveinfo) object;
								System.out.println("=> " + leave);
							}
						}
							break;
						case 2: {
							System.out.println("Enter the date of leave");
							String date = sc.next();
							Employee_leaveinfo leave = new Employee_leaveinfo(id, date);
							entityTransaction.begin();
							entityManager.persist(leave);
							entityTransaction.commit();
						}
							break;
						case 3:
							j = 1;
							break;
						default:
							System.out.println("Invalid Input!");
							break;
						}
					}
						break;
					default:
						break;
					}
				}
			}
				break;
			default:
				i = 1;
				break;

			}
		}
	}
}