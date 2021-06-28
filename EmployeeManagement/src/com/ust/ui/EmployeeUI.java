package com.ust.ui;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ust.entity.Employee;
import com.ust.exception.EmployeeException;
import com.ust.service.EmployeeService;
import com.ust.service.EmployeeServiceImpl;

public class EmployeeUI {

	private EmployeeService eService;

	public void start() {
		eService = new EmployeeServiceImpl();
		Scanner scan = new Scanner(System.in);
		boolean canrun = true;
		int choice = 0;
		while (canrun) {
			choice = getChoice(scan);
			switch (choice) {
			case 1:
				System.out.println("Create an Employee Record");
				Employee emp = null;
				Employee savedEmp;
				try {
					System.out.println("Enter <Name> <Mobile> <Designation>");
					emp = new Employee(0, scan.next(), scan.nextLong(), scan.next());
					savedEmp = eService.createEmp(emp);
					if (savedEmp != null) {
						System.out.println("Employee record created");
						System.out.println(savedEmp);
					} else {
						System.out.println("Failed to create record");
					}
				} catch (EmployeeException e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("Please enter correct values");
				}
				break;
			case 2:
				System.out.println("Update an Employee");
				try {
					System.out.println("Enter ID of employee to update");
					int id = scan.nextInt();
					emp = eService.getEmployee(id);
					if (emp != null) {
						System.out.println(emp);
						System.out.println("Enter <mobile> <designation> to update");
						Employee updatedEmp = 
								new Employee(id, emp.getName(), scan.nextLong(), scan.next());
						emp = eService.updateEmp(updatedEmp);
						if(emp!=null) {
							System.out.println("Updated: "+emp);
						}
						else {
							System.out.println("Failed to updated Record");
						}
					}
				} catch (EmployeeException e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("Please enter correct values");
				}

				break;
			case 3:
				System.out.println("Delete an Employee");
				try {
					System.out.println("Enter ID of employee to delete");
					int id = scan.nextInt();
					emp = eService.deleteEmployee(id);
					if(emp!=null) {
						System.out.println("Deleted: " + emp);
					}
					else {
						System.out.println("Failed deletion");
					}
				} catch (EmployeeException e1) {
					e1.printStackTrace();
				} catch (InputMismatchException e) {
					System.out.println("Please enter correct values");
				}
				break;
			case 4:
				System.out.println("Display all Employees");
				List<Employee> empList;
				try {
					empList = eService.getAllEmployees();
					if(empList.size()>0) {
						print(empList);
					}
					else {
						System.out.println("No data found");
					}
				} catch (EmployeeException e) {
					e.printStackTrace();
				}
				break;
			case 5:
				eService.close();
				System.out.println("Thank you. Exiting system.");
				canrun = false;
				break;

			default:
				System.err.println("Please enter 1 to 5 only");
				break;
			}
		}
	}

	private void print(List<Employee> empList) {
		for (Employee employee : empList) {
			System.out.println(employee);
		}
	}

	private int getChoice(Scanner scan) {
		int choice = 0;
		System.out.println("Welcome to Employee Management System");
		System.out.println("1. Create an Employee Record");
		System.out.println("2. Update an Employee");
		System.out.println("3. Delete an Employee");
		System.out.println("4. Display all Employees");
		System.out.println("5. Exit System");
		System.out.println("Please enter your choice 1 to 5");
		try {
			choice = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Please enter numbers only");
			scan.nextLine();
		}
		return choice;
	}

	public static void main(String[] args) {
		new EmployeeUI().start();
	}

}
