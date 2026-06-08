package services.login.employee;

public class EmployeeLogin {
    private String userName;
    private String password;
    
    public EmployeeLogin(String userName, String password) {
    	this.userName = userName;
    	this.password = password;
    }

    public EmployeeLogin() {
    }
    
    public void setUserName(String userName) {
 	   this.userName = userName;
    } 
    
    public String getUserName() {
 	   return userName;
    }
    
    public void setPassword(String password) {
 	   this.password = password;
    }
    
    public String getPassword() {
 	   return password;
    } 
    
    public String toString() {
    	return "EmployeeLogin{username:"+getUserName()+", password:"+getPassword()+"}";
    }
}
