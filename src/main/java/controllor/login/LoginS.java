package controllor.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.login.customer.CustomerLogin;
import services.login.employee.EmployeeLogin;
import services.customer.CustomerServices;
import services.employee.EmployeeServices;

import java.io.IOException;

/**
 * Servlet implementation class LoginS
 */
public class LoginS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginS() {
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
		String user = request.getParameter("user");
		String userName = request.getParameter("username");
		String password = request.getParameter("password"); 
		System.out.println("testing -"+user +" "+ userName +" "+ password);
		System.out.println("testing -"+user.length() +" "+ userName.length() +" "+ password.length());
		String name = "";
		boolean isUser = false;
		
		System.out.print(user);
		if(user.equals("customer")) {
		      CustomerLogin customerLogin = new CustomerLogin();
		      customerLogin.setUserName(userName);
			  customerLogin.setPassword(password);
			  isUser = CustomerServices.verfiyLogin(customerLogin);
			 
			  if(isUser) {
				  name = CustomerServices.getName(userName);
				  HttpSession session = request.getSession(false);
				  if(session == null) {
					  session = request.getSession();
				  }
				  session.setAttribute("userName", userName);
				  session.setAttribute("password", password);
				  session.setAttribute("name", name);
				  response.sendRedirect("customerhome.jsp");			  
			  }else {
				  request.setAttribute("errorMessage", "Invalid username or password.");
				  request.setAttribute("username", userName);
		          request.setAttribute("user",user); 
		          request.setAttribute("password", password);
				  request.getRequestDispatcher("login.jsp").forward(request,response);                                              
			  }
			  
		} else if(user.equals("employee")) {
			EmployeeLogin employeeLogin = new EmployeeLogin();
			employeeLogin.setUserName(userName);
			employeeLogin.setPassword(password);
			boolean isValid = EmployeeServices.verifyLogin(employeeLogin); 
			if(isValid) {
				 HttpSession session = request.getSession(false);
				 if(session == null) {
					 session = request.getSession();
				 }
				 session.setAttribute("employee", EmployeeServices.getEmployee(userName));
				 session.setAttribute("userName", userName);
				 session.setAttribute("password", password);
				 session.setAttribute("name", name);
				 
		         response.sendRedirect("employeehome.jsp");
			}
			else {
				request.setAttribute("user",user); 
				request.setAttribute("userName", userName);
				request.setAttribute("password", password);
				request.setAttribute("errorMessage", "Invalid username or password.");
				request.getRequestDispatcher("login.jsp").forward(request,response);
			}
		} 
			
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		 
	}

}
