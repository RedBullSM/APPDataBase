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
	/*
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
      
      
     try {
    	properties pro = new properties();
		pro.connessione();
	//	pro.Stringa(con);
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		List<persone> trovati = new ArrayList<persone> (pro.aggiungi());
		//System.out.println(trovati.isEmpty());
	    //pro.aggiungi();
		for (persone persone : trovati) {
			System.out.println("FORZA NINO ce la puoi fare : " + " " + persone.getId() + " " + persone.getNome());
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	*/
   public static Connection connessione () throws IOException, SQLException, ClassNotFoundException {
	   final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	  
	   Properties pro = new Properties();
	 
	     File f = new File ("C:\\Users\\Sebastiano\\Desktop\\dbconfig.properties");
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
//  /**
//   public   ArrayList<persone> aggiungi (/*ArrayList<persone> listone */){
//	   properties pro = new properties();
//	  ArrayList<persone> array;
//	try {
//		array = new ArrayList<persone>(pro.Stringa(con));
//		System.out.println("CIAOAOOOAOOAOA");
//	//	System.out.println(array.isEmpty());
//		for (persone persone : array) {
//			System.out.println(persone.getId() + persone.getNome());
//		}
//		return array;
//	} catch (ClassNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	return null;
//	   
//   }
//    public List<persone> Stringa (Connection con) throws ClassNotFoundException, IOException {
//    	 String sql = "SELECT id , nome  FROM clienti";
//    	 List<persone> listone = new ArrayList<persone>();
//    	 List<String> listone1 = new ArrayList<String>();
//		 try {
//			PreparedStatement st = (PreparedStatement) ((Connection) connessione()).prepareStatement(sql);
//			 ResultSet rs = 	st.executeQuery();
//			while (rs.next()){
//		    //	System.out.println((" id : "+ rs.getInt(1)));
//		    //	System.out.println(("nome : "+ rs.getString(2)));
//		        listone.add(new persone(rs.getInt("id"),rs.getString("nome")));
//		        listone1.add(rs.getString(2));
//		        System.out.println(listone.isEmpty());
//		        }
//			System.out.println("---------------------------");
//			for (persone persone : listone) {
//				      System.out.println(persone.getNome()+ " " + persone.getId());
//			}
//			System.out.println("---------------------------");
//			System.out.println("---------------------------");
//			System.out.println("---------------------------");
//			 for (String string : listone1) {
//				  System.out.println(string);
//			}
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		 
//	return listone;	
//    }
//    */
}
