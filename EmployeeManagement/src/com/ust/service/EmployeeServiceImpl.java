package com.ust.service;

import java.util.List;

import com.ust.dao.EmployeeDao;
import com.ust.dao.EmployeeDaoImpl;
import com.ust.entity.Employee;
import com.ust.exception.EmployeeException;

public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDao eDao;
	
	public EmployeeServiceImpl() {
		eDao = new EmployeeDaoImpl();
	}

	@Override
	public Employee createEmp(Employee emp) throws EmployeeException {
		return eDao.createEmp(emp);
	}

	@Override
	public Employee getEmployee(int id) throws EmployeeException {
		return eDao.getEmployee(id);
	}

	@Override
	public Employee updateEmp(Employee updatedEmp) throws EmployeeException {
		return eDao.updateEmp(updatedEmp);
	}

	@Override
	public List<Employee> getAllEmployees() throws EmployeeException {
		return eDao.getAllEmployees();
	}

	@Override
	public void close() {
		eDao.close();
	}

	@Override
	public Employee deleteEmployee(int id) throws EmployeeException {
		return eDao.deleteEmployee(id);
	}

}
