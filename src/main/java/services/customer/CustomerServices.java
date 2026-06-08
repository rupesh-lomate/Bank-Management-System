package services.customer;

import java.util.Date;
import java.util.List;

import dao.bank.BankDao;
import dao.customer.CustomerDao;
import dao.customer.transactions.TransactionsDao;
import dao.entities.Customer;
import dao.entities.Transactions;
import services.login.customer.CustomerLogin;

public class CustomerServices {
	 
	private String msg = "";
	private long senderAccNo;
	private long traAccNo;
	 
	public String getMsg() {
		return this.msg;
	}
	public static boolean verfiyLogin(CustomerLogin customerLogin) {
		 return CustomerDao.contain(customerLogin);

	} 
	public static String getName(String userName) {
		return CustomerDao.getName(userName);
	}
	public static String getName(long accNo) {
		return CustomerDao.getName(accNo);
	}
	public static int getBal(String userName) {
		return CustomerDao.getBal(userName);
	}
	public static int getBal(Long accNo) {
		return CustomerDao.getBal(accNo);
	}
	public static long getAccNo(String userName) {
		return CustomerDao.getAcc(userName);
	}
	
	public  String transaction(String senderUserName,long traAccNo, int amt) {
		if(amt == 0){
			msg = "Can't send &#8377;"+amt;
			return msg;
		}
		if(amt < 0) {
			msg = "amount not valid";
			return msg;
		}
		if(getAccNo(senderUserName) == traAccNo) {
			msg = "Can't send money to your own ac";
			return msg;
		}
		boolean isCustomerAvailable1 = CustomerDao.contain(senderUserName);
		System.out.println("t1");
		boolean isCustomerAvailable2 = CustomerDao.contain(traAccNo);
		System.out.println("t2");
		if(isCustomerAvailable1 && isCustomerAvailable2) {
			senderAccNo = CustomerDao.getAcc(senderUserName);
			if(!checkSufficientBal(senderUserName, amt)) {
				return msg;
			}
			this.traAccNo = traAccNo;
			boolean tr1 = debit(senderUserName, amt);
			boolean tr2 = credit(traAccNo, amt);
			if(tr1 && tr2) {
				   return  msg = msg+"&#8377;"+amt+" sent  to "+getName(traAccNo).toUpperCase();
			}if(!tr1 && tr2) {
				debit(traAccNo, amt);
				   return msg = msg+"\n Transaction cansal error in debit";
			}if(!tr2 && tr1) {
				credit(senderUserName, amt);
				   return  msg = msg+"Transaction cansal error in credit";
			}if(!(tr1 || tr2)) {
				   return  msg = msg+"Transaction casal both debit and credit";
			}
		}else if(!(isCustomerAvailable1 && isCustomerAvailable2)) {
			if(!isCustomerAvailable1)
				return "You Are Account Is Not Available";
			if(!isCustomerAvailable2)
				return "Entered Account Number Is Not Exist";
		}
		if(msg.isEmpty())
	        msg="Transaction failed";
		
		return msg;
	}
	
	public boolean checkSufficientBal(String userName, int amt) {
		 int customerBal = CustomerServices.getBal(userName);
		 if(customerBal >= amt) {
			 return true;
		 }else if(customerBal < amt) {
			 msg = "Insufficent balance";
			 return false;
		 }else {
			  throw new IllegalArgumentException("Such user not aviable");
		 }
	}
	public boolean checkSufficientBal(long accNo, int amt) {
		 int customerBal = CustomerServices.getBal(accNo);
		 if(customerBal >= amt) {
			 return true;
		 }else if(customerBal < amt) {
			 msg = "Insufficent balance"+"\n";
			 return false;
		 }else {
			  throw new IllegalArgumentException("Such user not aviable");
		 }
	}
	
	public boolean debit(String userName, int amt) {
		boolean flag = false;
		 if(checkSufficientBal(userName, amt)) {
			 int customerBal = CustomerServices.getBal(userName);
		     flag = CustomerDao.updateBal(userName, customerBal - amt );
		     Transactions tran = new Transactions();
		     tran.setAccNo(senderAccNo);
		     tran.setOperation("debit");
		     tran.setAmt(amt);
		     tran.setInfo("transfer to "+getName(traAccNo).toUpperCase());
		     tran.setCurrentBal(customerBal-amt);
		     tran.setOldBal(customerBal);
		     tran.setDate(new Date());
		     TransactionsDao.save(tran);
		     return flag;
		 }
		 	return flag; 
	}
		
	public boolean debit(long accNo, int amt) {
		 boolean flag = false;
		 if(checkSufficientBal(accNo, amt)) {
			 int customerBal = CustomerServices.getBal(accNo);
		     flag = CustomerDao.updateBal(accNo, customerBal - amt );
		     Transactions tran = new Transactions();
		     tran.setAccNo(accNo);
		     tran.setOperation("debit");
		     tran.setAmt(amt);
		     tran.setInfo("transfer to "+getName(traAccNo).toUpperCase());
		     tran.setCurrentBal(customerBal-amt);
		     tran.setOldBal(customerBal);
		     tran.setDate(new Date());
		     TransactionsDao.save(tran);
		     return flag;
		 }    
		 	return false; 
	}
	
	public boolean credit(String userName, int amt) {
		boolean flag = false;
		int customerBal = CustomerServices.getBal(userName);
		flag = CustomerDao.updateBal(userName,customerBal + amt);
		Transactions tran = new Transactions();
	     tran.setAccNo(traAccNo);
	     tran.setOperation("credit");
	     tran.setAmt(amt);
	     tran.setInfo("send by "+getName(senderAccNo).toUpperCase());
	     tran.setCurrentBal(customerBal+amt);
	     tran.setOldBal(customerBal);
	     tran.setDate(new Date());
	     TransactionsDao.save(tran);
	     return flag;
	}
		
	public  boolean credit(long accNo, int amt) {
		boolean flag = false;
		int customerBal = CustomerServices.getBal(accNo);
		flag = CustomerDao.updateBal(accNo,customerBal + amt);
		Transactions tran = new Transactions();
	     tran.setAccNo(traAccNo);
	     tran.setOperation("credit");
	     tran.setAmt(amt);
	     tran.setInfo("send by "+getName(senderAccNo).toUpperCase());
	     tran.setCurrentBal(customerBal+amt);
	     tran.setOldBal(customerBal);
	     tran.setDate(new Date());
	     TransactionsDao.save(tran);
	     return flag;
	}

	
	public static Customer getCustomer(Long accNo) {
		return CustomerDao.get(accNo);
	} 
	
	
	public static List<Transactions> getAllStatements(long accNo){
		return TransactionsDao.getTrasactions(accNo);
	}
	
	public static boolean containAccNo(long accNo) {
		return CustomerDao.contain(accNo);		
	}
	public static boolean containUsername(String username) {
		return CustomerDao.contain(username);		
	}
	
	public static boolean modify(Customer customer) {
		return CustomerDao.updateCustomer(customer);
	}
	
	public boolean creditBalByEmp(long accNo, int amt) {
        int currentBal = getBal(accNo);
        boolean isSuccess = CustomerDao.updateBal(accNo, currentBal + amt);
        if(isSuccess)
        	BankDao.updateBankBal(BankDao.getBankBal()+amt);
        Transactions tran = new Transactions();
	     tran.setAccNo(accNo);
	     tran.setOperation("credit");
	     tran.setAmt(amt);
	     tran.setInfo("deposited by "+ BankDao.getBankName().toLowerCase() +" "+CustomerDao.getBranch(accNo).toLowerCase());
	     tran.setCurrentBal(currentBal+amt);
	     tran.setOldBal(currentBal);
	     tran.setDate(new Date());
	     TransactionsDao.save(tran);
	     return isSuccess;
        
	}
	
	public boolean debitBalByEmp(long accNo, int amt) {
        int currentBal = getBal(accNo);
        if(checkSufficientBal(accNo, amt)) {
        	boolean isSuccess = CustomerDao.updateBal(accNo, currentBal - amt);
        	if(isSuccess)
            	BankDao.updateBankBal(BankDao.getBankBal()-amt);
        	Transactions tran = new Transactions();
   	        tran.setAccNo(accNo);
   	        tran.setOperation("debit");
   	        tran.setAmt(amt);
   	        tran.setInfo("withdrew by "+ BankDao.getBankName().toLowerCase() +" "+CustomerDao.getBranch(accNo).toLowerCase());
   	        tran.setCurrentBal(currentBal-amt);
   	        tran.setOldBal(currentBal);
   	        tran.setDate(new Date());
   	        TransactionsDao.save(tran);
   	       return isSuccess;
        }
        msg = "unsufficient balance in "+accNo;	
   	    return false;
        	
	}
	
	public static boolean updatePassword(String username, String password) {
		return CustomerDao.updatePassword(username, password);
	}
	public static boolean verifyRegister(long accNo) {
		return CustomerDao.verifyRegister(accNo);
	}
	public static boolean verifyAccNoAndPhoneNo(long accNo, long phoneNo) {
		return CustomerDao.verifyAccNoAndPhoneNo(accNo, phoneNo);
	}
	public static boolean register(long accNo, String usename, String password) {
		return CustomerDao.updateUserNameAndPassword(accNo, usename, password);
	}
}
