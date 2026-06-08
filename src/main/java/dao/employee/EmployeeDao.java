package dao.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.db.Database;
import dao.entities.Employee;
import services.login.employee.EmployeeLogin;

public class EmployeeDao {
       public static boolean contain(EmployeeLogin employeeLogin) {
    	    String userName = employeeLogin.getUserName();
    	    String password = employeeLogin.getPassword();
    	    Connection con = Database.getConnection();
    	    boolean isValid = false;
    	    try {
    	    	PreparedStatement psmt =
    	    	con.prepareStatement("select * from employees where username=? and password=?");
    	    	psmt.setString(1, userName);
    	    	psmt.setString(2, password);
    	    	ResultSet rs = psmt.executeQuery();
    	    	if(rs.next()) 
    	    		isValid = true;   	
    	    }catch(Exception e ) {
    	    	e.printStackTrace();;
    	    }finally {
    	    	Database.close(con);
    	    }
    	    return isValid;
       }
       
       public static int getBalance(String userName) {
       	    Connection con = Database.getConnection();
       	    int bal = -1;
       	    try {
       	    	PreparedStatement psmt =
       	    	con.prepareStatement("select balance from employees where username=?");
       	    	psmt.setString(1, userName);
       	    	ResultSet rs = psmt.executeQuery();
       	    	if(rs.next()) 
       	    		bal = rs.getInt("balance");   	
       	    }catch(Exception e ) {
       	    	e.printStackTrace();;
       	    }finally {
       	    	Database.close(con);
       	    }
       	    return bal;
        } 
        
       public static int getBalance(int id) {
      	    Connection con = Database.getConnection();
      	    int bal = -1;
      	    try {
      	    	PreparedStatement psmt =
      	    	con.prepareStatement("select balance from employees where id=?");
      	    	psmt.setInt(1, id);
      	    	ResultSet rs = psmt.executeQuery();
      	    	if(rs.next()) 
      	    		bal = rs.getInt("balance");   	
      	    }catch(Exception e ) {
      	    	e.printStackTrace();;
      	    }finally {
      	    	Database.close(con);
      	    }
      	    return bal;
       } 
       
       public static int getCash(String userName) {
      	    Connection con = Database.getConnection();
      	    int cash = -1;
      	    try {
      	    	PreparedStatement psmt =
      	    	con.prepareStatement("select cash from employees where username=?");
      	    	psmt.setString(1, userName);
      	    	ResultSet rs = psmt.executeQuery();
      	    	if(rs.next()) 
      	    		cash = rs.getInt("cash");   	
      	    }catch(Exception e ) {
      	    	e.printStackTrace();;
      	    }finally {
      	    	Database.close(con);
      	    }
      	    return cash;
       }
       public static int getCash(int id) {
     	    Connection con = Database.getConnection();
     	    int cash = -1;
     	    try {
     	    	PreparedStatement psmt =
     	    	con.prepareStatement("select cash from employees where id=?");
     	    	psmt.setInt(1, id);
     	    	ResultSet rs = psmt.executeQuery();
     	    	if(rs.next()) 
     	    		cash = rs.getInt("cash");   	
     	    }catch(Exception e ) {
     	    	e.printStackTrace();;
     	    }finally {
     	    	Database.close(con);
     	    }
     	    return cash;
      } 
       public static Employee getEmployee(String userName) {
    	   Connection con = Database.getConnection();
      	    Employee employee = new Employee();
      	    try {
      	    	PreparedStatement psmt =
      	    	con.prepareStatement("select * from employees where username=?");
      	    	psmt.setString(1, userName);
      	    	ResultSet rs = psmt.executeQuery();
      	    	if(rs.next()) {
      	    		employee.setName(rs.getString("name"));
      	    	    employee.setUsername(rs.getString("username"));
      	    	    employee.setPassword(rs.getString("password"));
    	    	    employee.setRole(rs.getString("role"));
    	    	    employee.setId(rs.getInt("id"));
      	    	    employee.setBranch(rs.getString("branch"));
      	    	    employee.setBalance(rs.getInt("balance"));
      	    	    System.out.println(employee);
      	    	    return employee;
      	    	}
      	    }catch(Exception e ) {
      	    	e.printStackTrace();;
      	    }finally {
      	    	Database.close(con);
      	    }
      	    return employee;
       }
       
       public static boolean updateBal(int id, int bal) {
    	   Connection conn = Database.getConnection();
    	   int countOperation=0;
    	   try {
    		   PreparedStatement pstmt = conn.prepareStatement("update employees set balance=? where id=?");
    		   pstmt.setInt(1, bal);
    		   pstmt.setInt(2, id);
    		   countOperation = pstmt.executeUpdate();
    		   System.out.println(countOperation);
    		   return (countOperation > 0) ? true : false ;
    		   
    	   }catch(Exception e) {
    		   e.printStackTrace();
    	   }finally {
    		   Database.close(conn);
    	   }
    	   return false;
       }
       public static boolean updateCash(int id, int cash) {
    	   Connection conn = Database.getConnection();
    	   int countOperation=0;
    	   try {
    		   PreparedStatement pstmt = conn.prepareStatement("update employees set cash=? where id=?");
    		   pstmt.setInt(1, cash);
    		   pstmt.setInt(2, id);
    		   countOperation = pstmt.executeUpdate();
    		   System.out.println(countOperation);
    		   return (countOperation > 0) ? true : false ;
    		   
    	   }catch(Exception e) {
    		   e.printStackTrace();
    	   }finally {
    		   Database.close(conn);
    	   }
    	   return false;
       }
       public static boolean updateCash(String userName , int cash) {
    	   Connection conn = Database.getConnection();
    	   int countOperation=0;
    	   try {
    		   PreparedStatement pstmt = conn.prepareStatement("update employees set cash=? where username=?");
    		   pstmt.setInt(1, cash);
    		   pstmt.setString(2, userName);
    		   countOperation = pstmt.executeUpdate();
    		   System.out.println(countOperation);
    		   return (countOperation > 0) ? true : false ;
    		   
    	   }catch(Exception e) {
    		   e.printStackTrace();
    	   }finally {
    		   Database.close(conn);
    	   }
    	   return false;
       }
}
