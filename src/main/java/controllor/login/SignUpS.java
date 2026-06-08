package controllor.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.customer.CustomerServices;

import java.io.IOException;

/**
 * Servlet implementation class SignUpS
 */
public class SignUpS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpS() {
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
		 String password = request.getParameter("password");
		 long accNo = Long.parseUnsignedLong(request.getParameter("accNo"));
		 request.setAttribute("accNo", accNo);
		 System.out.print(username+" "+password);
		 if(!CustomerServices.containUsername(username)) {
			 if(CustomerServices.register(accNo, username, password)) {
			      request.setAttribute("successMessage", "Your account has been successfully created");
			 }else {
				 request.setAttribute("errorMessage", "some thing wrong");
			 }
			 request.getRequestDispatcher("signup.jsp").forward(request, response);
		 }else if(CustomerServices.containUsername(username)){
		     request.setAttribute("errorMessage", "username not available");
		     request.getRequestDispatcher("signup.jsp").forward(request, response);
		 }else {
			 request.getRequestDispatcher("signup.jsp").forward(request, response);
		 }
	}

}
