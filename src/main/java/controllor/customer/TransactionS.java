package controllor.customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.customer.CustomerServices;
import services.login.customer.CustomerLogin;

import java.io.IOException;

/**
 * Servlet implementation class TransactinoS
 */
public class TransactionS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionS() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long traAccNo = Long.parseLong(request.getParameter("accNo"));
		int amt = Integer.parseInt(request.getParameter("amount"));
		boolean didTransaction = false;
		
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
		       System.out.println("1");
		       CustomerServices sc = new CustomerServices();
			   String msg = sc.transaction(userName,traAccNo, amt);
			   System.out.println("2");
			   request.setAttribute("msg", msg);
			   request.getRequestDispatcher("sendMoney.jsp").forward(request, response);
			   System.out.println(msg);
		    }else {
		    	response.sendRedirect("login.html");
		    }
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
