package com.ust.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ust.entity.Employee;
import com.ust.exception.EmployeeException;
import com.ust.util.DBUtil;
import com.ust.util.Messages;

public class EmployeeDaoImpl implements EmployeeDao {
	private Logger logger = Logger.getLogger(EmployeeDaoImpl.class);
	@Override
	public Employee createEmp(Employee emp) throws EmployeeException {
		logger.info(emp);
		Connection conn = DBUtil.getConnection();
		String query = "insert into  Emp_record(name,mobile,designation) values(?,?,?)";
		String autoIncrQuery = "select auto_increment from information_schema.tables where table_name='emp_record'";
		try {
			PreparedStatement stmt = conn.prepareStatement(autoIncrQuery);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				int id = rs.getInt(1);
				emp.setId(id);
				logger.info("id: " + id);
				stmt = conn.prepareStatement(query);
				stmt.setString(1, emp.getName());
				stmt.setLong(2, emp.getMobile());
				stmt.setString(3, emp.getDesignation());
				int rows = stmt.executeUpdate();
				if(rows<=0) {
					return null;
				}
			}
			else {
				// do something
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.error(e);
			throw new EmployeeException(Messages.DATABASE_ERROR);
		}
		return emp;
	}

	@Override
	public Employee getEmployee(int id) throws EmployeeException {
		logger.info("id: " + id);
		Employee emp = null;
		String selectQuery = "select name,mobile,designation from emp_record where id=?";
		Connection conn = DBUtil.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(selectQuery);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				emp = new Employee(id, rs.getString(1), rs.getLong(2), rs.getString(3));
			}
			else {
				// do something
			}
		} catch (SQLException e) {
			throw new EmployeeException(Messages.DATABASE_ERROR);
		}
		return emp;
	}

	@Override
	public Employee updateEmp(Employee updatedEmp) throws EmployeeException {
		Employee emp = null;
		logger.info(updatedEmp);
		Connection conn = DBUtil.getConnection();
		String query="update emp_record set mobile=?, designation=? where id=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, updatedEmp.getMobile());
			stmt.setString(2, updatedEmp.getDesignation());
			stmt.setInt(3, updatedEmp.getId());
			int rows = stmt.executeUpdate();
			if(rows>0) {
				emp = updatedEmp.getCopy();// gives a safe object as copy
			}
			else {
				logger.error("Error updating : " + updatedEmp);
				throw new EmployeeException(Messages.UPDATE_FAILED);
				// send email sms to dev
			}
			
			
		} catch (SQLException e) {
			logger.error(e);
			throw new EmployeeException(Messages.DATABASE_ERROR);
		}
		return emp;
	}

	@Override
	public List<Employee> getAllEmployees() throws EmployeeException {
		List<Employee> empList = new ArrayList<>();
		logger.info("All employees");
		String selectQuery = "select id, name,mobile,designation from emp_record";
		Connection conn = DBUtil.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(selectQuery);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee(rs.getInt(1), rs.getString(2), 
						rs.getLong(3), rs.getString(4));
				empList.add(emp);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new EmployeeException(Messages.DATABASE_ERROR);
		}
		
		return empList;
	}

	@Override
	public void close() {
		DBUtil.close();
	}

	@Override
	public Employee deleteEmployee(int id) throws EmployeeException {
		logger.info("id: " + id);
		Employee emp = getEmployee(id);
		String query = "delete from emp_record where id=?";
		Connection conn = DBUtil.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			int rows = stmt.executeUpdate();
			if(rows<=0) {
				logger.error("Error deleting record");
				throw new EmployeeException(Messages.DELETE_FAILED);
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.error(e);
			throw new EmployeeException(Messages.DELETE_FAILED);
		}
		return emp;
	}

}
