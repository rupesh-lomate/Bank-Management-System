<%@ page import="dao.entities.Customer" %>
<%@ page import="services.customer.CustomerServices" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="services.employee.EmployeeServices" %>
<%@ page import="services.login.employee.EmployeeLogin" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Created</title>
    <style>
        body {
            font-family: 'Poppins', Arial, sans-serif;
            background: linear-gradient(to bottom right, #1e3c72, #2a5298);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            color: #333;
        }

        .passbook {
            width: 550px;
            background: linear-gradient(to bottom, #ffffff, #f8f9fc);
            border-radius: 16px;
            box-shadow: 0px 15px 25px rgba(0, 0, 0, 0.2);
            padding: 30px 40px;
            position: relative;
            overflow: hidden;
        }

        .passbook::before {
            content: '';
            position: absolute;
            top: -50px;
            right: -50px;
            width: 150px;
            height: 150px;
            background: rgba(0, 119, 255, 0.15);
            border-radius: 50%;
            z-index: 0;
        }

        .passbook::after {
            content: '';
            position: absolute;
            bottom: -50px;
            left: -50px;
            width: 150px;
            height: 150px;
            background: rgba(0, 119, 255, 0.15);
            border-radius: 50%;
            z-index: 0;
        }

        .header {
            display: flex;
            align-items: center;
            margin-bottom: 30px;
            z-index: 1;
            position: relative;
        }

        .header img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            border: 4px solid #0077ff;
            margin-right: 20px;
        }

        .header h2 {
            font-size: 26px;
            color: #333;
            font-weight: 700;
            margin: 0;
        }

        .details {
            z-index: 1;
            position: relative;
            background: #f1f4f9;
            padding: 20px;
            border-radius: 12px;
            border: 1px solid #e4e7ed;
            margin-bottom: 20px;
        }

        .details p {
            margin: 12px 0;
            font-size: 16px;
            color: #555;
        }

        .details p strong {
            color: #0077ff;
            font-weight: 600;
        }

        .success-message {
            text-align: center;
            color: #28a745;
            font-weight: 600;
            margin-bottom: 20px;
            font-size: 18px;
        }

        .button-container {
            text-align: center;
        }

        .home-button {
            background: linear-gradient(90deg, #0077ff, #0056cc);
            color: #ffffff;
            padding: 12px 50px;
            border: none;
            border-radius: 10px;
            text-decoration: none;
            font-weight: bold;
            font-size: 16px;
            cursor: pointer;
            transition: all 0.3s ease;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.15);
            z-index: 1;
            position: relative;
        }

        .home-button:hover {
            background: linear-gradient(90deg, #0056cc, #003b99);
            transform: translateY(-2px);
        }

        @media screen and (max-width: 768px) {
            .passbook {
                width: 90%;
                padding: 20px 30px;
            }

            .header img {
                width: 80px;
                height: 80px;
            }

            .header h2 {
                font-size: 22px;
            }

            .details p {
                font-size: 14px;
            }

            .home-button {
                padding: 10px 40px;
                font-size: 14px;
            }
        }
    </style>
</head>
<body>
    <%
        String userName = (String) session.getAttribute("userName");
        String password = (String) session.getAttribute("password");
        boolean isValid = EmployeeServices.verifyLogin(new EmployeeLogin(userName, password));
        if (!isValid) {
            response.sendRedirect("login.jsp");
        }
    %>

<%
   long accNo = Long.parseLong(request.getParameter("acc_no"));
   Customer customer = CustomerServices.getCustomer(accNo);

   String name = customer.getName() != null ? customer.getName() : "N/A";
   String ifsc = customer.getIfsc() != null ? customer.getIfsc() : "N/A";
   String branch = customer.getBranch() != null ? customer.getBranch() : "N/A";
   int bal = customer.getBal() != 0 ? customer.getBal() : 0;
   long phoneNo = customer.getPhoneNo() != 0 ? customer.getPhoneNo() : 0;
   LocalDate localDate = customer.getDob();
   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
   String dob = localDate != null ? localDate.format(formatter) : "N/A";
   long adharNo = customer.getAdharNo() != 0 ? customer.getAdharNo() : 0;
   String address = customer.getAddress() != null ? customer.getAddress() : "N/A";
   String mail = customer.getMail() != null ? customer.getMail() : "N/A";
%>
<div class="passbook">
    <div class="header">
        <img src="https://via.placeholder.com/100" alt="User Photo">
        <h2>Account Holder Profile</h2>
    </div>
    <div class="details">
        <p><strong>Name:</strong> <%= name %></p>
        <p><strong>IFSC:</strong> <%= ifsc %></p>
        <p><strong>Branch Name:</strong> <%= branch %></p>
        <p><strong>Balance:</strong> &#8377;<%= bal %></p>
        <p><strong>Phone No:</strong> <%= phoneNo %></p>
        <p><strong>Email:</strong> <%= mail %></p>
        <p><strong>Date of Birth:</strong> <%= dob %></p>
        <p><strong>Account No:</strong> <%= accNo %></p>
        <p><strong>Aadhar No:</strong> <%= adharNo %></p>
        <p><strong>Address:</strong> <%= address %></p>
    </div>
    <div class="success-message">
        Account details fetched successfully!
    </div>
    <div class="button-container">
        <a href="employeehome.jsp" class="home-button">Home</a>
    </div>
</div>
</body>
</html>