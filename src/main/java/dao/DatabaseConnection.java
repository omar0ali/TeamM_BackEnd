package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	/*
	 * This class will manually connect to the database and it will be static so it
	 * can be used on all classes
	 * 
	 * 
	 */
	private static DatabaseConnection instance;
	private Connection con = null;

	public static DatabaseConnection getInstance() {
		if (instance == null || instance.con == null) {
			instance = new DatabaseConnection();
		}
		
		return instance;
	}
	private DatabaseConnection() {
		System.out.println("Connecting to database");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://id-db-teamm.ciity4oybruj.us-east-1.rds.amazonaws.com:3306/db_teamm?autoReconnect=true","admin","adminOfTeamM-");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() throws Exception {
		if(instance == null) {
			throw new Exception("Database Connection instance is null. Must be created first.");
		}
		return con;
	}
	public void Destroy() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
