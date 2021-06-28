package com.ust.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ust.entity.Employee;
import com.ust.exception.EmployeeException;

public class EmployeeDaoImplTest {
	EmployeeDaoImpl eDao = new EmployeeDaoImpl();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test//(expected = EmployeeException.class)
	public void testCreateEmp() {
		Employee emp = new Employee(0, "Ajay", 9019283744L, "Manager");
		try {
			Employee saved = eDao.createEmp(emp);
			//assertNotEquals(0, saved.getId());
			assertEquals(emp.getName(), saved.getName());
		} catch (EmployeeException e) {
			e.printStackTrace();
		}
		
	}

	@Test//(expected = EmployeeException.class)
	public void testGetEmployee() {
		Employee emp;
		try {
			emp = eDao.getEmployee(10001);
			assertNotNull(emp);
		} catch (EmployeeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateEmp() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllEmployees() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteEmployee() {
		fail("Not yet implemented");
	}

}
