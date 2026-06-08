package controllor.employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import services.employee.EmployeeServices;

import java.io.IOException;

import dao.entities.Employee;

/**
 * Servlet implementation class DepositeCashS
 */
public class DepositeCashS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepositeCashS() {
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
		HttpSession session = request.getSession(false);
		if(session == null)
			response.sendRedirect("login.jsp");
		String accNoString = request.getParameter("accNo");
		String amtString = request.getParameter("amount");
		System.out.println(accNoString+" "+amtString);
		int empId =  ((Employee)session.getAttribute("employee")).getId();
		System.out.println(empId);
		long accNo = Long.parseLong(accNoString);
		int amt = Integer.parseInt(amtString);
		
		EmployeeServices employeeService = new EmployeeServices();
		boolean isSuccess = employeeService.deposite(empId, accNo, amt);
		System.out.println(isSuccess);
		String msgSuccess = employeeService.getMsgSuccess();
		String msgError = employeeService.getMsgError();
		System.out.println(msgSuccess+ " "+ msgError);
		request.setAttribute("depositSuccess", msgSuccess);
		request.setAttribute("depositError", msgError);
        
		request.getRequestDispatcher("deposit.jsp").forward(request, response);
	}

}
