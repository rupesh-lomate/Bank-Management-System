package dao.bank;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.db.Database;

public class BankDao {
       
	public static String getBankName() {
		Connection con = Database.getConnection();
		String bankName = null;
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select bank_name from bank");
			if(rs.next())
		 	    bankName = rs.getString("bank_name");
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			Database.close(con);
		}
		return bankName;
	}
	
	public static long getBankBal() {
		Connection con = Database.getConnection();
		long bankBal = 0;
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select bank_bal from bank");
			if(rs.next())
			   bankBal = rs.getLong("bank_bal");
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			Database.close(con);
		}
		return bankBal;
	}
	
	public static boolean updateBankBal(long bankBal) {
		Connection con = Database.getConnection();
		try {
			PreparedStatement pstmt = 
					con.prepareStatement("update bank set bank_bal=?");
		    pstmt.setLong(1,bankBal);
		    pstmt.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			Database.close(con);
		}
		return false;
	}
	
	public static long getLastAccNo(){
		Connection con = Database.getConnection();
		long lastAccNo = 0;
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select last_acc_no from bank");
			if(rs.next())
			   lastAccNo = rs.getLong("last_acc_no");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			Database.close(con);
		}
		return lastAccNo;
	}
	public static boolean updateLastAccNo(Long accNo) {
		Connection con = Database.getConnection();
		try {
			PreparedStatement pstmt = 
					con.prepareStatement("update bank set last_acc_no=?");
		    pstmt.setLong(1,accNo);
		    pstmt.executeUpdate();
		    
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			Database.close(con);
		}
		return false;
	}
}
