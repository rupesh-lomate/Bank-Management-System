package test;

import dao.bank.BankDao;

public class Test {

	public static void main(String[] args) {
//	  Customer customer = new Customer();
//	  customer.setAccNo(1234567890);
//	  customer.setName("rupesh lomate");
//	  customer.setDob(LocalDate.now());
//	  customer.setAddress("nehru nagar");
//	  System.out.println(CustomerDao.updateCustomer(customer));
	   
		
		//System.out.println(EmployeeDao.updateBal(12345, 10000));
		
		BankDao.updateBankBal(BankDao.getBankBal()+1000);
	    
	  
	}

}
