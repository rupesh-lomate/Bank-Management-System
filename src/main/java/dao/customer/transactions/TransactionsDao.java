package dao.customer.transactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;

import dao.db.Database;
import dao.entities.Transactions;

public class TransactionsDao {
     
	public static boolean save(Transactions tran) {
		Connection con = Database.getConnection();
		boolean flag = false;
		try {
			PreparedStatement psmt = 
					con.prepareStatement("insert into transactions(acc_no, operation, amount, info, current_bal, old_bal, dates)"
							+ "values(?,?,?,?,?,?,?)");
			psmt.setLong(1, tran.getAccNo());
			psmt.setString(2, tran.getOperation());
			psmt.setInt(3, tran.getAmt());
			psmt.setString(4, tran.getInfo());
			psmt.setInt(5, tran.getCurrentBal());
			psmt.setInt(6,  tran.getOldBal());
			psmt.setDate(7, new java.sql.Date((tran.getDate().getTime())));
			
		    return ( psmt.executeUpdate() != 0 ) ? true : false;
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Database.close(con);
		}
		return flag;
	} 
	
	public static List<Transactions> getTrasactions(long accNo){
		Connection con = Database.getConnection();
		List<Transactions> allTrans = new ArrayList<>();
		boolean flag = false;
		try {
			PreparedStatement psmt = 
					con.prepareStatement("select * from transactions where acc_no = ? order by dates desc");
			psmt.setLong(1, accNo);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Transactions tran = new Transactions();
				tran.setAccNo(rs.getLong("acc_no"));
				tran.setOperation(rs.getString("operation"));
				tran.setAmt(rs.getInt("amount"));
				tran.setInfo(rs.getString("info"));
				tran.setCurrentBal(rs.getInt("current_bal"));
				tran.setOldBal(rs.getInt("old_bal"));
				tran.setDate(rs.getDate("dates"));
				
				allTrans.add(tran);
			}
		    return allTrans;
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Database.close(con);
		}
		return allTrans;		
	}
}
