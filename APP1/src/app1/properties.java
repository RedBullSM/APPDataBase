package app1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;



public class properties {

	public static  Connection con = null;
	
   public static Connection connessione () throws IOException, SQLException, ClassNotFoundException {
	   final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	  
	   Properties pro = new Properties();
	 
	     File f = new File ("C:\\Users\\Sebastiano\\Desktop\\dbconfig\\dbconfig.properties");
	     InputStream input = new FileInputStream(f);
		pro.load(input);
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setDatabaseName(pro.getProperty("db-name"));
		dataSource.setPortNumber(3306);
	    dataSource.setServerName(pro.getProperty("db-address"));
	    dataSource.setUser(pro.getProperty("db-user"));
	    dataSource.setPassword(pro.getProperty("db-password"));
	      input.close();
	    con = (Connection) dataSource.getConnection();
   
	   return con;
	   }

}
