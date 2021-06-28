package com.ust.service;

import java.util.List;

import com.ust.entity.Employee;
import com.ust.exception.EmployeeException;

public interface EmployeeService {

	Employee createEmp(Employee emp) throws EmployeeException;

	Employee getEmployee(int id) throws EmployeeException;

	Employee updateEmp(Employee updatedEmp) throws EmployeeException;

	List<Employee> getAllEmployees() throws EmployeeException;

	void close();

	Employee deleteEmployee(int id) throws EmployeeException;

}
