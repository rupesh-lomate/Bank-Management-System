<%@ page import="services.employee.EmployeeServices" %>
<%@ page import="services.login.employee.EmployeeLogin" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Customer Profile</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            background-color: #f5f5f5;
            color: #333;
            padding: 20px;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            background: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        .header {
            background: #4caf50;
            color: white;
            padding: 15px;
            font-size: 18px;
            font-weight: bold;
            border-radius: 6px;
            margin-bottom: 20px;
            text-align: center;
        }

        h2 {
            font-size: 18px;
            margin-bottom: 15px;
            color: #333;
        }

        label {
            font-size: 14px;
            font-weight: bold;
            color: #555;
            display: block;
            margin-bottom: 5px;
        }

        input, button {
            width: 100%;
            padding: 12px 15px;
            margin-bottom: 15px;
            border-radius: 6px;
            border: 1px solid #ddd;
            font-size: 14px;
        }

        input:focus {
            border-color: #4caf50;
            outline: none;
            box-shadow: 0 0 4px rgba(76, 175, 80, 0.3);
        }

        button {
            background-color: #4caf50;
            color: white;
            font-size: 15px;
            font-weight: bold;
            cursor: pointer;
            border: none;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #3e8e41;
        }

        .error {
            color: red;
            margin-bottom: 15px;
        }

        .success {
            color: green;
            margin-bottom: 15px;
        }

        .hidden {
            display: none;
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
    <div class="container">
        <div class="header">
            Customer Account Management
        </div>

        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            String successMessage = (String) request.getAttribute("successMessage");
            if (errorMessage != null) {
        %>
        <div class="error"><%= errorMessage %></div>
        <%
            } else if (successMessage != null) {
        %>
        <div class="success"><%= successMessage %></div>
        <%
            }
        %>

        <h2>Find Customer Details</h2>
        <form action="updateAccountProfileS" method="post">
            <label for="accNo">Customer Account Number</label>
            <input
                type="text"
                id="accNo"
                name="accNo"
                value="<%= request.getAttribute("accNo") != null ? request.getAttribute("accNo") : "" %>"
                placeholder="Enter account number"
                required
            >
            <button type="submit">Get Details</button>
        </form>

        <%
            String name = (String) request.getAttribute("name");
            if (name != null) {
        %>
        <div id="detailsForm">
            <h2>Modify Customer Details</h2>
            <form action="updateAccountProfileS" method="post">
                <input type="hidden" name="accNo" value="<%= request.getAttribute("accNo") %>">

                <label for="name">Name</label>
                <input
                    type="text"
                    id="name"
                    name="name"
                    value="<%= name %>"
                    placeholder="Enter name"
                    required
                >

                <label for="dob">Date of Birth</label>
                <input
                    type="date"
                    id="dob"
                    name="dob"
                    value="<%= request.getAttribute("dob") %>"
                    required
                >

                <label for="phoneNo">Phone Number</label>
                <input
                    type="text"
                    id="phoneNo"
                    name="phoneNo"
                    value="<%= request.getAttribute("phoneNo") %>"
                    placeholder="Enter phone number"
                    required
                >

                <label for="address">Address</label>
                <input
                    type="text"
                    id="address"
                    name="address"
                    value="<%= request.getAttribute("address") %>"
                    placeholder="Enter address"
                    required
                >

                <!-- ADDED -->
                <label for="mail">Email Address</label>
                <input
                    type="email"
                    id="mail"
                    name="mail"
                    value="<%= request.getAttribute("mail") != null ? request.getAttribute("mail") : "" %>"
                    placeholder="Enter email address"
                >

                <button type="submit">Modify Details</button>
            </form>
        </div>
        <%
            }
        %>
    </div>
</body>
</html>