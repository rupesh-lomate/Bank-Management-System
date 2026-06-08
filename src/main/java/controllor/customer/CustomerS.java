package controllor.customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.login.customer.CustomerLogin;
import services.customer.CustomerServices;

import java.io.IOException;
import java.util.List;

import dao.entities.Transactions;


/**
 * Servlet implementation class CustomerS
 */
public class CustomerS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		boolean isUserValid = false;
		HttpSession session = request.getSession(false);
		if(session != null) {
		    String userName = (String)session.getAttribute("userName");
		    String password = (String)session.getAttribute("password");
		    CustomerLogin customerLogin = new CustomerLogin();
		    customerLogin.setUserName(userName);
		    customerLogin.setPassword(password);
		    isUserValid = CustomerServices.verfiyLogin(customerLogin);
		    if(isUserValid) {
		    	if(operation.equals("checkBalance")) {
		    		response.sendRedirect("checkBalance.jsp");
		    	}else if(operation.equals("sendMoney")) {
		    		response.sendRedirect("sendMoney.jsp");
		    	}else if(operation.equals("viewStatements")) {
		    		List<Transactions> transactionList = CustomerServices.getAllStatements(CustomerServices.getAccNo(userName));
		    		System.out.println(transactionList.size());
		    		request.setAttribute("transactions", transactionList);
		    		request.getRequestDispatcher("viewStatements.jsp").forward(request, response);
		    	}else{
		    		response.getWriter().println("<h1>Some thing wrong<h1>");
		    	}
		    }else {
		    	response.sendRedirect("login.html");
		    }
		}else {
			response.sendRedirect("customerhome.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
