package controllor.employee;

import java.io.IOException;
import java.time.LocalDate;

import dao.entities.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.employee.EmployeeServices;

// Assuming Customer and CustomerDAO are implemented
public class UpdateAccountProfileS extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAccountProfileS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String accNoParam = request.getParameter("accNo");
            String name = request.getParameter("name");
            String dob = request.getParameter("dob");
            String phoneNo = request.getParameter("phoneNo");
            String address = request.getParameter("address");

            if (accNoParam != null) {
                long accNo = Long.parseLong(accNoParam);

                // Step 1: Check if account number exists in the database
                if (!EmployeeServices.containCustomerAcc(accNo)) {
                    request.setAttribute("errorMessage", "Account number not found.");
                    request.getRequestDispatcher("updateAccountProfile.jsp").forward(request, response);
                    return;
                }

                // Step 2: If updating customer details
                if (name != null && dob != null && phoneNo != null && address != null) {
                    Customer customer = new Customer();
                    customer.setAccNo(accNo);
                    customer.setName(name);
                    customer.setDob(LocalDate.parse(dob));
                    customer.setPhoneNo(Long.parseLong(phoneNo));
                    customer.setAddress(address);

                    boolean isUpdated = EmployeeServices.modifyCustomer(customer);

                    if (isUpdated) {
                        request.setAttribute("successMessage", "Customer details updated successfully.");
                    } else {
                        request.setAttribute("errorMessage", "Failed to update customer details.");
                    }
                    request.getRequestDispatcher("updateAccountProfile.jsp").forward(request, response);
                    return;
                }

                // Step 3: If getting customer details for modification
                Customer customer = EmployeeServices.getCustomerDetails(accNo);
                if (customer != null) {
                    request.setAttribute("name", customer.getName());
                    request.setAttribute("dob", customer.getDob());
                    request.setAttribute("phoneNo", customer.getPhoneNo());
                    request.setAttribute("address", customer.getAddress());
                    request.setAttribute("accNo", accNo);
                }
            }

            request.getRequestDispatcher("updateAccountProfile.jsp").forward(request, response);
        }
    
}
