<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.entities.Transactions" %>
<%@ page import="services.customer.CustomerServices" %>
<%@ page import="services.login.customer.CustomerLogin" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Demo Project</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
            color: #343a40;
            padding-bottom: 60px; /* Ensures content doesn't overlap with footer */
        }

        header {
            background-color: #005aa7;
            color: white;
            padding: 20px;
            text-align: center;
        }

        header h1 {
            margin: 0;
            font-size: 28px;
        }

        .container {
            max-width: 1200px;
            margin: 40px auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            border-radius: 10px;
            overflow-x: auto; /* Enables horizontal scrolling for the table */
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px 20px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #005aa7;
            color: white;
            text-align: center;
            vertical-align: middle;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #e9ecef;
        }

        footer {
            position: fixed;
            bottom: 0;
            width: 100%;
            text-align: center;
            padding: 20px;
            background-color: #005aa7;
            color: white;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <%
        String userName = (String)session.getAttribute("userName");
        String password = (String)session.getAttribute("password");
        boolean isSuccess = false;
        CustomerLogin customerLogin = new CustomerLogin();
        customerLogin.setUserName(userName);
        customerLogin.setPassword(password);
        isSuccess = CustomerServices.verfiyLogin(customerLogin);
        if(!isSuccess) {
        	response.sendRedirect("login.html");
        }
    %>
    <header>
        <h1>Welcome to Swarajya Banking Portal</h1>
    </header>

    <div class="container">
        <h2>Transactions</h2>
        <table>
            <thead>
                <tr>
                    <th>Account No</th>
                    <th>Operation</th>
                    <th>Amount</th>
                    <th>Description</th>
                    <th>Current Balance</th>
                    <th>Old Balance</th>
                    <th>Date</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    // Retrieve the list of transactions from the request
                    ArrayList<Transactions> transactions = (ArrayList<Transactions>) request.getAttribute("transactions");
                    
                    // Check if transactions list is null or empty
                    if (transactions != null && !transactions.isEmpty()) {
                        // Loop through the transactions list
                        for (Transactions transaction : transactions) {
                %>
                    <tr>
                        <td><%= transaction.getAccNo() %></td>
                        <td><%= transaction.getOperation() %></td>
                        <td><%= transaction.getAmt() %></td>
                        <td><%= transaction.getInfo() %></td>
                        <td><%= transaction.getCurrentBal() %></td>
                        <td><%= transaction.getOldBal() %></td>
                        <td><%= transaction.getDate() %></td>
                    </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="7" style="text-align: center;">No transactions available.</td>
                    </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>

    <footer>
        <p>&copy; 2025 State Bank of India. All Rights Reserved.</p>
    </footer>
</body>
</html>
