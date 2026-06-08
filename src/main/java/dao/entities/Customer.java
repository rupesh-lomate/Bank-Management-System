package dao.entities;

import java.time.LocalDate;

public class Customer {
   private String name;
   private long accNo;
   private String userName;
   private String password;
   private int bal;
   private LocalDate dob;
   private String ifsc;  
   private long adharNo;
   private long phoneNo;
   private String address;
   private String branch;
  
   public void setBranch(String branch) {
	   this.branch = branch;
   }  
   
   public String getBranch() {
	   return this.branch;
   }
   public static Customer of(){
	   return new Customer();
   }
   
   public long getPhoneNo() {
	   return this.phoneNo;
   }
   
   public void setPhoneNo(long phoneNo) {
	   this.phoneNo = phoneNo;
   }
   public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public long getAccNo() {
	return accNo;
}

public void setAccNo(long accNo) {
	this.accNo = accNo;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public int getBal() {
	return bal;
}

public void setBal(int bal) {
	this.bal = bal;
}

public LocalDate getDob() {
	return dob;
}

public void setDob(LocalDate dob) {
	this.dob = dob;
}

public String getIfsc() {
	return ifsc;
}

public void setIfsc(String ifsc) {
	this.ifsc = ifsc;
}

public long getAdharNo() {
	return adharNo;
}

public void setAdharNo(long adharNo) {
	this.adharNo = adharNo;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public Customer name(String name) { this.name = name; return this;}
   public Customer accNo(long accNo) { this.accNo = accNo; return this;}
   
   
   public Customer userName(String userName) { this.userName = userName; return this;}
   
   public Customer password(String password) { this.password = password; return this;}
   
   public Customer bal(int bal) { this.bal = bal; return this;}
   
   public Customer dob(LocalDate dob) { this.dob = dob; return this;}
   
   public Customer ifsc( String ifsc) { this.ifsc = ifsc; return this;}
   
   public Customer adharNo(long adharNo) { this.adharNo = adharNo; return this;}
   
   public Customer phoneNo(long phoneNo) { this.phoneNo = phoneNo; return this;}
   
   public Customer address(String address) { this.address = address; return this;}
   
   public Customer branch(String branch) {this.branch = branch; return this;}
   
   public String toString() {
       return "Customer{" +
              "name='" + name + '\'' +
              ", accNo=" + accNo +
              ", userName='" + userName + '\'' +
              ", password='" + password + '\'' +
              ", bal=" + bal +
              ", dob=" + dob +
              ", ifsc='" + ifsc + '\'' +
              ", adharNo=" + adharNo +
              ", phoneNo=" + phoneNo +
              ", address='" + address + '\'' +
              ", branch='" +branch +'\'' +
              '}';

   }
   }

