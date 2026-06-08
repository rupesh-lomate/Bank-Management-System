package services.employee;

import java.util.Date;

import dao.bank.BankDao;
import dao.customer.CustomerDao;
import dao.customer.transactions.TransactionsDao;
import dao.employee.EmployeeDao;
import dao.entities.Customer;
import dao.entities.Employee;
import dao.entities.Transactions;
import services.customer.CustomerServices;
import services.login.employee.EmployeeLogin;

public class EmployeeServices {
	
    private String msgSuccess = null;
    private String msgError = null;
    public void setMsgSuccess(String msgSuccess) {
    	this.msgSuccess = msgSuccess;
    }
    public void setMsgError(String msgError) {
    	this.msgError = msgError;
    }
    public String getMsgSuccess() {
    	return this.msgSuccess;
    }
    public String getMsgError() {
    	return this.msgError;
    }
    
    public static boolean verifyLogin(EmployeeLogin employeeLogin) {
    	return EmployeeDao.contain(employeeLogin);
    }
    public static Employee getEmployee(String userName) {
    	return EmployeeDao.getEmployee(userName);
    }
    public static int getBalance(String userName) {
    	return EmployeeDao.getBalance(userName);
    }
    public static int getBalance(int id) {
    	return EmployeeDao.getBalance(id);
    }
    public static int getCash(String userName) {
    	return EmployeeDao.getCash(userName);
    }
    public static int getCash(int id) {
    	return EmployeeDao.getCash(id);
    }
    public static long createAccount(Customer customer, int empId) {
    	if(customer.getBal() > getBalance(empId)) {
    		return -127;
    	}
    	long accNo = CustomerDao.save(customer);
    	
    	if(accNo != -1) {
    		BankDao.updateBankBal(BankDao.getBankBal() + customer.getBal());
    		Transactions tran = new Transactions();
   	        tran.setAccNo(accNo);
   	        tran.setOperation("credit");
   	        tran.setAmt(customer.getBal());
   	        tran.setInfo("initial balance");
   	        tran.setCurrentBal(customer.getBal());
   	        tran.setOldBal(0);
   	        tran.setDate(new Date());
   	     TransactionsDao.save(tran);
    	}
    	EmployeeDao.updateBal(empId, EmployeeDao.getBalance(empId) - customer.getBal());
    	EmployeeDao.updateCash(empId, EmployeeDao.getCash(empId) + customer.getBal());
    	return accNo;
    }
    
    public static boolean containCustomerAcc(long AccNO) {
    	  return CustomerServices.containAccNo(AccNO);
    }
    
    public static Customer getCustomerDetails(long accNo) {
    	return CustomerServices.getCustomer(accNo);
    }
    
    public static boolean modifyCustomer(Customer customer) {
    	return CustomerServices.modify(customer);
    }
    
    public boolean deposite(int empId, long accNo, int amt) {
    	if(!CustomerDao.contain(accNo)) {
    		msgError = "accno is not existed";
    	    return false;
    	}
    	if(isSufficientBal(empId, amt)) {
            boolean flag1 = debitBal(empId, amt);
            boolean flag2 = creditCash(empId, amt);
            if(flag1 && flag2) {
    	       CustomerServices customerService = new CustomerServices();
    	       if(customerService.creditBalByEmp(accNo, amt)) {
    	          msgSuccess =  "&#8377;"+amt +" deposited to "+CustomerServices.getName(accNo)+" account";
    	          return true;
    	       }else {
    	    	   return false;
    	       }
            }
    	}
    	return false;
    }
    
    public boolean withdraw(int empId,long accNo,  int amt) {
       	if(!CustomerDao.contain(accNo)) {
    		msgError = "accno is not existed";
    	    return false;
    	}
       	if(amt <= 0) {
       		msgError = "amount not valid";
       	    return false;
       	}
    	if(isSufficientCash(empId, amt)) {
            boolean flag1 = debitCash(empId, amt);
            boolean flag2 = creditBal(empId, amt);
            if(flag1 && flag2) {
    	       CustomerServices customerService = new CustomerServices();
    	       if(customerService.debitBalByEmp(accNo, amt)) {
    	          msgSuccess =  "&#8377;"+amt +" withdrawn of "+CustomerServices.getName(accNo)+" account";
    	          return true;
    	       }else {
    	    	   creditCash(empId, amt);
    	    	   debitBal(empId, amt);
    	    	   msgError = customerService.getMsg();
    	    	   return false;
    	       }
            }
    	}
    	return false;
    }

    
    public boolean creditCash(int empId, int amt) {
    	int currentBal = EmployeeServices.getCash(empId);
    	return EmployeeDao.updateCash(empId, amt + currentBal);
    }
    public boolean creditBal(int empId, int amt) {
    	int currentBal = EmployeeServices.getBalance(empId);
    	return EmployeeDao.updateBal(empId, amt + currentBal);
    }
    public boolean debitCash(int empId, int amt) {
    	if(isSufficientCash(empId, amt)) {
    	    int currentCash = EmployeeServices.getCash(empId);
    	    return EmployeeDao.updateCash(empId, currentCash-amt);
    	}
    	msgError = "debitbal balace operation fail";
    	return false;
    }
    public boolean debitBal(int empId, int amt) {
    	if(isSufficientBal(empId, amt)) {
    	    int currentBal = EmployeeServices.getBalance(empId);
    	    return EmployeeDao.updateBal(empId, currentBal-amt);
    	}
    	msgError = "debitbal balace operation fail";
    	return false;
    }
    public boolean isSufficientBal(int empId, int amt) {
    	boolean flag =  getBalance(empId) >= amt;
    	if(!flag)
           msgError = "You have unsufficient balance";
    	return flag;
    }
    public boolean isSufficientCash(int empId, int amt) {
    	boolean flag =  getCash(empId) >= amt;
    	if(!flag)
           msgError = "You have unsufficient cash";
    	return flag;
    }
    
    
}
