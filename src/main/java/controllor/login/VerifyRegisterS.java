package controllor.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.customer.CustomerServices;

import java.io.IOException;

/**
 * Servlet implementation class VerifyRegisterS
 */
public class VerifyRegisterS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyRegisterS() {
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
		long accNo = Long.parseLong(request.getParameter("accNo"));
		long phoneNo = Long.parseLong(request.getParameter("phoneNo"));
		if(CustomerServices.verifyAccNoAndPhoneNo(accNo, phoneNo)) {
			if(!CustomerServices.verifyRegister(accNo)) {
				request.setAttribute("accNo", accNo);
				request.getRequestDispatcher("signup.jsp").forward(request,response);
			}else {
				request.setAttribute("errorMessage", "account alredy have");
				request.getRequestDispatcher("verifyregister.jsp").forward(request, response);
			}
		
		}else {
			request.setAttribute("errorMessage", "data not valid");
			request.getRequestDispatcher("verifyregister.jsp").forward(request, response);
		}
		
	}

}
