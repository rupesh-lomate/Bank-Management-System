package dao.entities;

import java.util.Date;

public class Transactions {
   private long accNo;
   private String operation;
   private int amt;
   private String info;
   private int currentBal;
   private int oldBal;
   private Date date;
   
   
   public Date getDate() {
	   return date;
   }
    public void setDate(Date date) {
	   this.date = date;
     }
   public long getAccNo() {
	   return accNo;
   }
    public void setAccNo(long accNo) {
	   this.accNo = accNo;
     }
    public String getOperation() {
	   return operation;
    } 
    public void setOperation(String operation) {
	   this.operation = operation;
    }
    public int getAmt() {
  	   return amt;
    }
    public void setAmt(int amt) {
	   this.amt = amt;
    }
    public String getInfo() {
	   return info;
    }
    public void setInfo(String info) {
	   this.info = info;
    }
    public int getCurrentBal() {
	   return currentBal;
    }
    public void setCurrentBal(int currentBal) {
	   this.currentBal = currentBal;
    }
    public int getOldBal() {
       return oldBal;
    }
    public void setOldBal(int oldBal) {
	   this.oldBal = oldBal;
    }
	@Override
	public String toString() {
		return "Transactions [accNo=" + accNo + ", operation=" + operation + ", amt=" + amt + ", info=" + info
				+ ", currentBal=" + currentBal + ", oldBal=" + oldBal + " , date=" + date +"]";
	}

   
   
}
