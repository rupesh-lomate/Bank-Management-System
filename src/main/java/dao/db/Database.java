package dao.db;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;


public class Database {
   static String url;
   static String user;
   static String pwd;
   
   static {
	   Properties properties = new Properties();
	   try {
		properties.load(Database.class.getClassLoader().getResourceAsStream("db.properties"));
		url = properties.getProperty("db.url");
		user = properties.getProperty("db.user");
		pwd = properties.getProperty("db.pwd");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   
   public static Connection getConnection(){
	   Connection con = null;
	   try {
	   Class.forName("oracle.jdbc.OracleDriver");
	   con = DriverManager.getConnection(url, user, pwd);
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   System.out.println("Database class Conn : "+ con);
	   System.out.println(url +" "+" "+ user +" "+pwd);
	   return con;
   }
   
   public static void close(Connection con) {
	   try {
		  con.close();
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   
	}
}








