package de.xunify.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MySQL {

	private String host, port, database, username, password;
	private Connection connection;
	
	public MySQL(String host, String port, String database, String username, String password) {
		this.host = host;
		this.port = port;
		this.database = database;
		this.username = username;
		this.password = password;
		
		try {
			connect();
		} catch (SQLException e) {
			System.err.println("Connecting to Database failed! Check access data!");
			e.printStackTrace();
		}
		try {
      /*createTable("tablename", Arrays.asList("column1", "column2"));
      * Use the line above to create new tables.*/
		} catch (SQLException e) {
			System.err.println("Problem creating tables in the database! Check access data!");
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection() {
		return connection;
	}
  
  /*To execute a MySQL command simple type:
  * getConnection().prepareStatement("command");
  * If you ned information for MySQL commands try this site: https://goo.gl/N3CaKj
  * Thanks to @hofmannsvenn*/
	
  /*To login into the mysql database.*/
	private void connect() throws SQLException {
		System.out.println("Connecting to Database..");
		this.connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
	}
	
  /*This Table will create a complete String value table if you want to change the datatype, for example instead of VARCHAR(length) try INT(length)*/
	private void createTable(String tablename, List<String> arguments) throws SQLException {
		String data = "";
		for(String st : arguments) {
			data = data +" "+st+"VARCHAR(100),";
		}
		data = data.substring(0, data.length()-1);
		PreparedStatement ps = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS ? ("+data+")");
		try {
			ps.setString(1, tablename);
			ps.executeUpdate();
		} finally {
			ps.close();
		}
	}
	
  /*Use this method in the Main class of your plugin liek this:
  * MySQL mysqlinstance = new MySQL("host", "port", "database", "username", "password");
  * mysqlinstance.close();*/
	public void close() throws SQLException {
		if(!connection.isClosed()) connection.close();
	}
	
}
