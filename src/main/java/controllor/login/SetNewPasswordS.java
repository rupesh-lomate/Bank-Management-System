package controllor.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.customer.CustomerServices;

import java.io.IOException;

/**
 * Servlet implementation class SetNewPasswordS
 */
public class SetNewPasswordS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetNewPasswordS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String username = request.getParameter("username");
		 String password = request.getParameter("newPassword");
		 boolean isAlreadyChanged = request.getParameter("isAlreadyChanged").equals("true") ? true : false;
		 boolean canShowLoginButton = request.getParameter("canShowLoginButton").equals("true") ? true : false;
		 System.out.println("current username "+username);
		 
		 System.out.println("isAlreadyChanged"+isAlreadyChanged);
		 if(password != null) {
			 if(password.trim() == "") {
				 if(isAlreadyChanged)
					 request.setAttribute("errorMessage", "password you alredy changed");
				 else
				     request.setAttribute("errorMessage", "username or password not valid");
				 request.setAttribute("username",username);
				 request.setAttribute("isAlreadyChanged", isAlreadyChanged);
				 request.setAttribute("canShowLoginButton", canShowLoginButton );
				 request.getRequestDispatcher("setnewpassword.jsp?username="+username).forward(request, response);
			 }
		 }
		 if(!isAlreadyChanged) {
		       boolean isSucess = CustomerServices.updatePassword(username, password);
		       if(isSucess) {
		    	   request.setAttribute("successMessage", "Password change successfully");
		    	   isAlreadyChanged =true;
		    	   canShowLoginButton = true;
		       }else {
				   request.setAttribute("errorMessage", "Some thing wrong");
				   request.setAttribute("username",username);
		       }
		 }else {
			 request.setAttribute("errorMessage", "password you alredy changed");
		 }
		 
		 
		 request.setAttribute("canShowLoginButton", canShowLoginButton );
	     request.setAttribute("isAlreadyChanged", isAlreadyChanged);
		 request.getRequestDispatcher("setnewpassword.jsp").forward(request, response);
		   
        }
		 
		 
}
