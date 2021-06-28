package com.ust.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.ust.dao.EmployeeDaoImpl;
import com.ust.exception.EmployeeException;

public class DBUtil {
	private static Connection conn;
	private static Logger logger = Logger.getLogger(DBUtil.class);
	public static Connection getConnection() throws EmployeeException {
		if(conn==null) {
			try {
				String configFile="db.config";
				ReadDBDetails rdb = new ReadDBDetails(configFile);
				Properties props = rdb.getData();
				String driver =(String) props.get("DRIVER");
				String url = (String) props.get("URL");
				String username = (String) props.get("USERNAME");
				String pswd = (String) props.get("PASSWORD");
				Class.forName(driver);
				conn = DriverManager.getConnection(url, username, PasswordHandler.decrypt(pswd));
			} catch (ClassNotFoundException e) {
				//e.printStackTrace();
				logger.error(e);
				throw new EmployeeException("Internal Error. Please check with Admin");
			} catch (SQLException e) {
				//e.printStackTrace();
				logger.error(e);
				throw new EmployeeException("Error in Database. Please report to DBA");
			}
		}
		return conn;		
	}
	public static void close() {
		if(conn!=null) {
			try {conn.close();} catch (SQLException e) {}
		}
	}

}
