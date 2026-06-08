package controllor.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.customer.CustomerServices;

import java.io.IOException;
import java.util.Random;


public class ForgotPasswordS extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String otp = request.getParameter("otp");
        System.out.println("otp "+otp);
        if(otp == null) {
            if(CustomerServices.containUsername(username)) {
        	    request.setAttribute("username", username);
        	    request.setAttribute("generatedOTP", generateOTP());
            }else {
        	    request.setAttribute("errorMessage", "Invalid username");
            }
            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
        }else {
        	  if(CustomerServices.containUsername(username)) {
        		  request.setAttribute("username", username);
           	      int generatedOTP = Integer.parseInt((String) request.getParameter("generatedOTP"));
           	      int enterOTP = Integer.parseInt(otp);
           	      System.out.println(generatedOTP+" "+enterOTP);
           	      if(generatedOTP == enterOTP) {
           	    	    System.out.println(username);
           	    	    request.getRequestDispatcher("setnewpassword.jsp").forward(request, response);
           	      }else {
           	    	  request.setAttribute("errorMessageOTP", "wrong otp enter");
           	    	  request.setAttribute("generatedOTP", generatedOTP);
           	    	  request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
           	      }
           	      
              }else {
           	      request.setAttribute("errorMessage", "Invalid username");
           	      request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
              }
        }
     }


    // Method to generate OTP
    private String generateOTP() {
        Random rand = new Random();
        return String.format("%06d", rand.nextInt(1000000)); // Generate 6-digit OTP
    }
}