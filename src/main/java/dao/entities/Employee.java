package dao.entities;

public class Employee {
    // Attributes
    private String username;
    private String password;
    private String name;
    private String role;
    private int id;
    private String branch;
    private int balance;
    private int cash;
    
    // Constructor
    public Employee(String username, String password, String name, String role, int id, String branch, int balance, int cash) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
        this.id = id;
        this.branch = branch;
        this.balance = balance;
        this.cash = cash;
    }

    // Default Constructor
    public Employee() {
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    // toString Method
    @Override
    public String toString() {
        return "Employee{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", id=" + id +
                ", branch='" + branch + '\'' +
                ", balance=" + balance + '\''+
                ", cash=" + cash + 
                '}';
    }
}
