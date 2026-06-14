package controllor.employee;

import java.io.IOException;
import dao.entities.Employee;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import dao.entities.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.mail.MailUtil;
import services.employee.EmployeeServices;

// Assuming Customer and CustomerDAO are implemented
public class CreateAccountS extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public CreateAccountS() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("first-name");
        String middleName = request.getParameter("middle-name");
        String lastName = request.getParameter("last-name");
        long aadhaarNumber = Long.parseLong(request.getParameter("aadhaar-number"));
        long phoneNumber = Long.parseLong(request.getParameter("phone-number"));
        String ifscCode = request.getParameter("ifsc-code"); // This is readonly so it will be submitted with the default value
        String branch = request.getParameter("branch"); // This will come from the session attribute
        String address = request.getParameter("address");
        String mail = request.getParameter("mail");
        
        int initialBalance = Integer.parseInt(request.getParameter("initial-balance"));
        HttpSession session = request.getSession(false);
        int empId =  ((Employee) session.getAttribute("employee")).getId();
        
        String dobString = request.getParameter("dob");
        LocalDate dob = null;
        if (dobString != null && !dobString.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                 dob = LocalDate.parse(dobString);
                // Now you can use the 'dob' Date object
            } catch (Exception e) {
                // Handle the exception, e.g., if the date format is invalid
                e.printStackTrace();
            }
        }
  
        Customer customer = Customer.of()
                 .name((firstName+" "+middleName+" "+lastName).toUpperCase())
                 .adharNo(aadhaarNumber)
                 .phoneNo(phoneNumber)
                 .ifsc(ifscCode)
                 .branch(branch)
                 .bal(initialBalance)
                 .dob(dob)
                 .address(address)
                .mail(mail);

        long accNo = EmployeeServices.createAccount(customer,empId);
        String msg = null;
        
        
        if (accNo != -1 && accNo != -127) {
        	request.setAttribute("sucess","Account crated successfully accNo : "+customer.getAccNo());
            String subject =
                    "Welcome to Swarajya Bank";

            String message =
                    "Dear Customer,\n\n" +
                            "We are pleased to inform you that your account has been successfully created with Swarajya Bank.\n\n" +
                            "Account Details:\n" +
                            "----------------------------------\n" +
                            "Account Number : " + customer.getAccNo() + "\n" +
                            "Account Holder : " + customer.getName() + "\n" +
                            "Initial Balance: ₹" + initialBalance + "\n" +
                            "----------------------------------\n\n" +
                            "You can now access banking services through our system.\n\n" +
                            "For security reasons, please keep your account information confidential and do not share your credentials with anyone.\n\n" +
                            "Thank you for choosing Swarajya Bank. We look forward to serving your financial needs.\n\n" +
                            "Warm Regards,\n" +
                            "Swarajya Bank\n" +
                            "Customer Service Department\n\n" +
                            "This is an automated message. Please do not reply to this email.";
            try {
                MailUtil.sendMail(customer.getMail(), subject, message);
                response.sendRedirect("accountcreated.jsp?acc_no=" + accNo);
            }catch (Exception e) {
                System.out.println("Mail Info : To sent mail "+customer.getMail()+"we failed due to some issues");
                System.out.println("       Issue may - "+e.getMessage());
            }
        }else {
            request.setAttribute("first-name", firstName);
            request.setAttribute("middle-name", middleName);
            request.setAttribute("last-name", lastName);
            request.setAttribute("aadhaar-number", aadhaarNumber);
            request.setAttribute("phone-number", phoneNumber);
            request.setAttribute("ifsc-code", ifscCode);
            request.setAttribute("branch", branch);
            request.setAttribute("dob", dobString);
            request.setAttribute("address", address);
            request.setAttribute("initial-balance", initialBalance);
            request.setAttribute("mail", mail);

            if(accNo == -127)
            	request.setAttribute("errorMessage", "You have unsufficient balance...");
            else
        	    request.setAttribute("errorMessage", "Account creation failed. Please try again...");
        	request.getRequestDispatcher("createaccount.jsp").forward(request, response);
        }
    }

}