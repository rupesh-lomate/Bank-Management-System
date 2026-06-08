<%@ page import="dao.entities.Employee" %>
<%@ page import="services.employee.EmployeeServices" %>
<%@ page import="services.login.employee.EmployeeLogin" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Portal</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }

        header {
            background-color: #0a4275;
            color: #ffffff;
            padding: 20px;
            text-align: center;
            font-size: 1.5em;
        }

        .container {
            display: flex;
            flex-wrap: wrap;
            padding: 20px;
        }

        .sidebar {
            width: 25%;
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            border-radius: 8px;
            margin-right: 20px;
        }

        .sidebar img {
            width: 100%;
            border-radius: 50%;
        }

        .sidebar h3 {
            margin: 15px 0 5px;
            font-size: 1.2em;
            color: #333333;
        }

        .sidebar p {
            color: #666666;
            margin: 5px 0;
            font-size: 0.9em;
        }

        .main-content {
            flex: 1;
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            border-radius: 8px;
        }

        .main-content h2 {
            margin-bottom: 20px;
            color: #0a4275;
        }

        .button-container {
            display: flex;
            flex-wrap: wrap;
            gap: 15px;
        }

        .button-container button {
            background-color: #0a4275;
            color: #ffffff;
            border: none;
            padding: 15px 20px;
            font-size: 1em;
            border-radius: 8px;
            cursor: pointer;
            transition: 0.3s;
        }

        .button-container button:hover {
            background-color: #0e5a99;
        }

        .deposit-box {
            margin-top: 30px;
            padding: 20px;
            width : 70%;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
        }

        .deposit-box h3 {
            margin-bottom: 20px;
            color: #0a4275;
        }

        .deposit-box form input[type="number"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 1em;
        }

        .deposit-box form button {
            background-color: #0a4275;
            color: #ffffff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 1em;
            cursor: pointer;
            transition: 0.3s;
        }

        .deposit-box form button:hover {
            background-color: #0e5a99;
        }

        .message {
            font-size: 0.9em;
            margin-top: 10px;
        }

        .message.success {
            color: green;
        }

        .message.error {
            color: red;
        }

        footer {
            text-align: center;
            padding: 10px;
            background-color: #0a4275;
            color: #ffffff;
            position: fixed;
            bottom: 0;
            width: 100%;
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
    <header>
        SBI Bank Employee Portal
    </header>

    <div class="container">
        <div class="sidebar">
            <img src="employee-photo.jpg" alt="Employee Photo">
            <h3><%=(session.getAttribute("employee") != null) ? ((Employee) session.getAttribute("employee")).getName() : "" %></h3>
            <p>Employee ID: <%=(session.getAttribute("employee") != null) ? ((Employee) session.getAttribute("employee")).getId() : "" %></p>
            <p>Bank Branch: <%=(session.getAttribute("employee") != null) ? ((Employee) session.getAttribute("employee")).getBranch() : "" %></p>
            <p>Balance: <%= (EmployeeServices.getBalance((String) session.getAttribute("userName"))) != -1 ? EmployeeServices.getBalance((String) session.getAttribute("userName")) : "" %></p>
            <p>Cash: <%= (EmployeeServices.getCash((String) session.getAttribute("userName"))) != -1 ? EmployeeServices.getCash((String) session.getAttribute("userName")) : "" %></p>
        </div>

        <div class="main-content">
            <h2>Bank Operations</h2>
            <div class="button-container">
                <form action="createaccount.jsp" method="get">
                    <button type="submit">Create Account</button>
                </form>
                <form action="updateAccountProfile.jsp" method="get">
                    <button type="submit">Update Account</button>
                </form>
                <form action="employeehome.jsp" method ="get">
                   <button >Deposit Balance</button>
                </form>
                <form action="withdraw.jsp" method="get">
                     <button >Withdraw Balance</button>
                </form>
            </div>

            <!-- Deposit Box -->
            <div class="deposit-box" id="depositBox">
                <h3>Deposit Funds</h3>
                <form action="depositeCashS" method="post">
                    <input type="number" name="accNo" placeholder="Account Number" required>
                    <input type="number" name="amount" placeholder="Amount" required>
                    <button type="submit">Deposit</button>
                </form>
                <% if (request.getAttribute("depositSuccess") != null) { %>
                    <p class="message success"><b><%= request.getAttribute("depositSuccess") %></b></p>
                <% } else if (request.getAttribute("depositError") != null) { %>
                    <p class="message error"><b><%= request.getAttribute("depositError") %></b></p>
                <% } %>
            </div>
        </div>
    </div>

    <footer>
        &copy; 2025 SBI Bank. All rights reserved.
    </footer>
</body>
</html>
