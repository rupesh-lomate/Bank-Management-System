<%@ page import="services.customer.CustomerServices" %>
<%@ page import="services.login.customer.CustomerLogin" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Portal</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
            color: #343a40;
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
            display: flex;
            gap: 20px;
            justify-content: space-between;
            align-items: flex-start;
        }

        .options {
            flex: 1;
            background-color: white;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            border-radius: 10px;
            padding: 20px;
        }

        .options h2 {
            font-size: 26px;
            color: #005aa7;
            margin-bottom: 20px;
        }

        .options button {
            width: 80%;
            padding: 10px;
            margin: 10px auto;
            font-size: 16px;
            color: white;
            background-color: #005aa7;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: transform 0.2s, background-color 0.3s;
            display: block;
        }

        .options button:hover {
            background-color: #003d73;
            transform: translateY(-3px);
        }

        .customer-info {
            width: 300px;
            background-color: white;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            border-radius: 10px;
            text-align: center;
            padding: 20px;
        }

        .customer-info img {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            object-fit: cover;
            margin-bottom: 20px;
        }

        .customer-info h3 {
            margin: 10px 0;
            font-size: 20px;
            color: #005aa7;
        }

        footer {
            background-color: #005aa7;
            color: white;
            text-align: center;
            padding: 10px;
            position: fixed;
            bottom: 0;
            width: 100%;
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
        <div class="customer-info">
            <img src="customer-photo.jpg" alt="Customer Photo">
            <h3><%=session.getAttribute("name")%> </h3>
            
        </div>

        <div class="options">
            <h2>Banking Operations</h2>
            <!-- Updated form action URL to /customerS -->
            <form action="customerS" method="GET">
               <button type="submit" name="operation" value="checkBalance">Check Balance</button>
           </form>

           <form action="customerS" method="GET">
              <button type="submit" name="operation" value="sendMoney">Send Money</button>
           </form>

           <form action="customerS" method="GET">
              <button type="submit" name="operation" value="viewStatements">View Statements</button>
            </form>
        </div>
    </div>

    <footer>
        <p>&copy; 2025 Swarajya Bank. All Rights Reserved.</p>
    </footer>
 
</body>
</html>