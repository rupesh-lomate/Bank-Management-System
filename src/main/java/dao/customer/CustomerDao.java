package dao.customer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dao.bank.BankDao;
import dao.db.Database;
import dao.entities.Customer;
import services.login.customer.CustomerLogin;

public class CustomerDao {


    public static boolean updateCustomer(Customer customer) {
        Connection con = Database.getConnection();
        int countChanges = 0;
        try {
            if(con != null) {
                PreparedStatement psmt =
                        con.prepareStatement("update customers set name=?, dob=?, phone_no=?, address=?, mail=?  where acc_no= ?");
                psmt.setString(1, customer.getName());
                psmt.setDate(2, Date.valueOf(customer.getDob()));
                psmt.setLong(3, customer.getPhoneNo());
                psmt.setString(4, customer.getAddress());
                psmt.setString(5, customer.getMail());      // mail added
                psmt.setLong(6, customer.getAccNo());       // shifted from 5 → 6
                countChanges = psmt.executeUpdate();
                return  countChanges > 0 ? true : false ;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            Database.close(con);
        }
        return false;
    }

    public static boolean contain(String username) {
        Connection con = Database.getConnection();
        try {
            if(con != null) {
                PreparedStatement psmt = con.prepareStatement("select * from customers where username = ?");
                psmt.setString(1, username);
                ResultSet rs = psmt.executeQuery();
                if(rs.next())
                    return true;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            Database.close(con);
        }

        return false;
    }
    public static boolean contain(Long  accNo) {
        Connection con = Database.getConnection();
        try {
            if(con != null) {
                PreparedStatement psmt = con.prepareStatement("select * from customers where acc_no = ?");
                psmt.setLong(1, accNo);
                ResultSet rs = psmt.executeQuery();
                if(rs.next())
                    return true;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            Database.close(con);
        }

        return false;
    }


    public static boolean contain(CustomerLogin customerLogin) {
        System.out.println("verifying customer "+customerLogin.getUserName());
        Connection con = Database.getConnection();
        try {
            if(contain(customerLogin.getUserName())){
                PreparedStatement psmt =
                        con.prepareStatement("SELECT ACC_NO FROM customers WHERE USERNAME = ? AND  PASSWORD = ?");
                psmt.setString(1,customerLogin.getUserName());
                psmt.setString(2, customerLogin.getPassword());
                ResultSet rs =  psmt.executeQuery();

                if(rs.next()) {
                    System.out.println("Customer "+customerLogin.getUserName() +", "+customerLogin.getPassword()+" } "+" : Valid");
                    return true;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            Database.close(con);
        }
        System.out.println("Customer {"+customerLogin.getUserName() +", "+customerLogin.getPassword()+" } "+" : Not valid");
        return  false;
    }

    public static long save(Customer customer) {
        Connection con =  Database.getConnection();
        long lastAccNo = BankDao.getLastAccNo();
        long newAccNo = lastAccNo + 1;
        try {
            PreparedStatement psmt =
                    con.prepareStatement("insert into customers values(?,?,?,?,?,?,?,?,?,?,?,?)"); // 12 columns now
            psmt.setString(1, customer.getName());
            psmt.setLong(2, newAccNo);
            psmt.setString(3, customer.getUserName());
            psmt.setString(4, customer.getPassword());
            psmt.setInt(5, customer.getBal());
            psmt.setDate(6, Date.valueOf(customer.getDob()));
            psmt.setLong(7, customer.getAdharNo());
            psmt.setString(8, customer.getIfsc());
            psmt.setLong(9, customer.getPhoneNo());
            psmt.setString(10, customer.getAddress());
            psmt.setString(11, customer.getBranch());
            psmt.setString(12, customer.getMail());     // mail added
            BankDao.updateLastAccNo(newAccNo);
            customer.setAccNo(newAccNo);
            psmt.executeUpdate();
            return newAccNo;
        }catch(Exception e ) {
            e.printStackTrace();
        }finally {
            Database.close(con);
        }
        System.out.println("11");
        return -1;

    }


    public static String getName(String userName) {
        Connection con = Database.getConnection();
        String name = null;
        try {
            PreparedStatement psmt = con.prepareStatement("select name from customers where username = ?");
            psmt.setString(1, userName);
            ResultSet rs = psmt.executeQuery();
            if(rs.next()) {
                name = rs.getString(1);
                return name;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            Database.close(con);
        }
        return name;
    }
    public static String getName(long accNo) {
        Connection con = Database.getConnection();
        String name = null;
        try {
            PreparedStatement psmt = con.prepareStatement("select name from customers where acc_no = ?");
            psmt.setLong(1, accNo);
            ResultSet rs = psmt.executeQuery();
            if(rs.next()) {
                name = rs.getString(1);
                return name;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            Database.close(con);
        }
        return name;
    }
    public static String getBranch(long accNo) {
        Connection con = Database.getConnection();
        String branch = null;
        try {
            PreparedStatement psmt = con.prepareStatement("select branch from customers where acc_no = ?");
            psmt.setLong(1, accNo);
            ResultSet rs = psmt.executeQuery();
            if(rs.next()) {
                branch = rs.getString(1);
                return branch;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            Database.close(con);
        }
        return branch;
    }

    public static int getBal(String userName) {
        Connection con = Database.getConnection();
        int bal = 0;
        try {
            PreparedStatement psmt = con.prepareStatement("select bal from customers where username = ?");
            psmt.setString(1, userName);
            ResultSet rs = psmt.executeQuery();
            if(rs.next()) {
                bal = rs.getInt(1);
                return bal;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            Database.close(con);
        }
        return bal;
    }

    public static int getBal(long accNo) {
        Connection con = Database.getConnection();
        int bal = 0;
        try {
            PreparedStatement psmt = con.prepareStatement("select bal from customers where acc_no = ?");
            psmt.setLong(1, accNo);
            ResultSet rs = psmt.executeQuery();
            if(rs.next()) {
                bal = rs.getInt(1);
                return bal;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            Database.close(con);
        }
        return bal;
    }
    public static long getAcc(String userName) {
        Connection con = Database.getConnection();
        try {
            PreparedStatement psmt = con.prepareStatement("select acc_no from customers where userName = ?");
            psmt.setString(1, userName);
            ResultSet rs = psmt.executeQuery();
            if(rs.next()) {
                return rs.getLong("acc_no");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            Database.close(con);
        }

        return 0;
    }

    public static boolean updateBal(String userName, int bal) {
        Connection con = Database.getConnection();
        int countChanges = 0;
        boolean isSuccess = false;
        try {
            if(con != null) {
                PreparedStatement psmt =
                        con.prepareStatement("update customers set bal=? where username=?");

                psmt.setInt(1, bal);
                psmt.setString(2, userName);

                countChanges = psmt.executeUpdate();
                System.out.println(countChanges);
                isSuccess = (countChanges == 0) ? false : true;
                return isSuccess;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            Database.close(con);
        }
        return isSuccess;
    }

    public static boolean updateBal(long accNo, int bal) {
        Connection con = Database.getConnection();
        int countChanges = 0;
        boolean isSuccess = false;
        try {
            if(con != null) {
                PreparedStatement psmt =
                        con.prepareStatement("update customers set bal=? where acc_no=?");

                psmt.setInt(1, bal);
                psmt.setLong(2,accNo);

                countChanges = psmt.executeUpdate();
                isSuccess = (countChanges == 0) ? false : true;
                return isSuccess;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            Database.close(con);
        }
        return isSuccess;
    }

    public static Customer get(long accNo) {
        Connection con = Database.getConnection();
        Customer customer = new Customer();
        try {
            PreparedStatement psmt = con.prepareStatement("select * from customers where acc_no=?");
            psmt.setLong(1, accNo);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()) {
                customer.setAccNo(accNo);
                customer.setName(rs.getString("name"));
                customer.setBal(rs.getInt("bal"));
                customer.setDob(rs.getDate("dob").toLocalDate());
                customer.setAdharNo(rs.getLong("adhar_no"));
                customer.setIfsc(rs.getString("ifsc_code"));
                customer.setPhoneNo(rs.getLong("phone_no"));
                customer.setAddress(rs.getString("address"));
                customer.setBranch(rs.getString("branch"));
                customer.setMail(rs.getString("mail"));    // mail added
                System.out.println(rs.getString("mail"));;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            Database.close(con);
        }
        return customer;
    }

    public static boolean updatePassword(String username, String password) {
        Connection con = Database.getConnection();
        int countChanges = 0;
        boolean isSuccess = false;
        try {
            if(con != null) {
                PreparedStatement psmt =
                        con.prepareStatement("update customers set password=? where username=?");

                psmt.setString(1, password);
                psmt.setString(2, username);
                countChanges = psmt.executeUpdate();
                isSuccess = (countChanges == 0) ? false : true;
                return isSuccess;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            Database.close(con);
        }
        return isSuccess;
    }

    public static boolean verifyAccNoAndPhoneNo(long accNo, long phoneNo) {
        Connection con = Database.getConnection();
        try {
            if(con != null) {
                PreparedStatement psmt = con.prepareStatement("select * from customers where acc_No = ? and phone_no = ?");
                psmt.setLong(1, accNo);
                psmt.setLong(2, phoneNo);
                ResultSet rs = psmt.executeQuery();
                if(rs.next())
                    return true;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            Database.close(con);
        }

        return false;
    }

    public static boolean verifyRegister(long accNo) {
        Connection con = Database.getConnection();
        try {
            if(con != null) {
                PreparedStatement psmt = con.prepareStatement("select username from customers where acc_no = ?");
                psmt.setLong(1, accNo);
                ResultSet rs = psmt.executeQuery();
                if(rs.next()) {
                    String username = rs.getString("username");
                    return ((username != null) && (username != ""))? true : false;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            Database.close(con);
        }

        return false;
    }

    public static boolean updateUserNameAndPassword(long accNo, String username, String password) {
        Connection con = Database.getConnection();
        int countChanges = 0;
        boolean isSuccess = false;
        try {
            if(con != null) {
                PreparedStatement psmt =
                        con.prepareStatement("update customers set username=? , password=? where acc_no=?");

                psmt.setString(1, username);
                psmt.setString(2,password);
                psmt.setLong(3, accNo);

                countChanges = psmt.executeUpdate();
                isSuccess = (countChanges == 0) ? false : true;
                return isSuccess;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            Database.close(con);
        }
        return false;
    }

    public static String getMailByUsername(String username) {
        Connection con = Database.getConnection();
        String mail = null;
        try {
            PreparedStatement psmt = con.prepareStatement("select mail from customers where username = ?");
            psmt.setString(1, username);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                mail = rs.getString("mail");
                return mail;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
        return mail;
    }

    public static String getMailByAccNo(long accNo) {
        Connection con = Database.getConnection();
        String mail = null;
        try {
            PreparedStatement psmt = con.prepareStatement("select mail from customers where acc_no = ?");
            psmt.setLong(1, accNo);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                mail = rs.getString("mail");
                return mail;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
        return mail;
    }
}