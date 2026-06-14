<%@ page import="dao.entities.Employee" %>
<%@ page import="java.util.Date" %>
<%@ page import="services.employee.EmployeeServices" %>
<%@ page import="services.login.employee.EmployeeLogin" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Account</title>
    
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }

        .form-container {
            width: 60%;
            max-width: 800px;
            margin: 50px auto;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
        }

        .form-container h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #0a4275;
            font-size: 1.8em;
        }

        .form-group {
            display: flex;
            flex-direction: column;
            margin-bottom: 15px;
        }

        .form-group label {
            font-size: 1em;
            color: #333;
            margin-bottom: 5px;
            font-weight: bold;
        }

        .form-group input,
        .form-group select,
        .form-group textarea {
            width: 100%;
            padding: 12px;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 5px;
            transition: border-color 0.3s ease;
        }

        .form-group input:focus,
        .form-group select:focus,
        .form-group textarea:focus {
            border-color: #0a4275;
            outline: none;
        }

        .form-group .small-input {
            width: calc(33% - 10px);
        }

        .form-row {
            display: flex;
            justify-content: space-between;
            gap: 10px;
        }

        button {
            background-color: #0a4275;
            color: #ffffff;
            font-size: 1.2em;
            padding: 12px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            width: 100%;
        }

        button:hover {
            background-color: #0e5a99;
        }

        textarea {
            resize: none;
        }

        .note {
            font-size: 0.9em;
            color: #666;
            margin-top: -10px;
            margin-bottom: 10px;
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
<div class="form-container">
    <h2>Create Customer Account</h2>
    <div name="errorMessage"  style='text-align:center; color : red; font-size : 18px; margin: 10px' >
       <b></b> <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %> </b>
    </div>
    <form action="createAccountS" method="post">
        <!-- Name Fields -->
        <div class="form-row">
            <div class="form-group">
                <label for="first-name">First Name</label>
                <input type="text" id="first-name" name="first-name" value='<%= request.getAttribute("first-name") != null ? request.getAttribute("first-name") : "" %>' placeholder="Enter First Name" required>
            </div>
            <div class="form-group">
                <label for="middle-name">Middle Name</label>
                <input type="text" id="middle-name" name="middle-name" value='<%= request.getAttribute("middle-name") != null ? request.getAttribute("middle-name") : "" %>' placeholder="Enter Middle Name" required>
            </div>
            <div class="form-group">
                <label for="last-name">Last Name</label>
                <input type="text" id="last-name" name="last-name" value='<%= request.getAttribute("last-name") != null ? request.getAttribute("last-name") : "" %>' placeholder="Enter Last Name" required>
            </div>
        </div>

        <!-- Aadhaar Number -->
        <div class="form-group">
            <label for="aadhaar-number">Aadhaar Number</label>
            <input type="text" id="aadhaar-number" name="aadhaar-number" maxlength="12"
                   value='<%= request.getAttribute("aadhaar-number") != null ? request.getAttribute("aadhaar-number") : "" %>'
                   placeholder="Enter 12-digit Aadhaar Number" pattern="\d{12}" title="Aadhaar number must be 12 digits" required>
        </div>

        <!-- Phone Number -->
        <div class="form-group">
            <label for="phone-number">Phone Number</label>
            <input type="tel" id="phone-number" name="phone-number" placeholder="Enter Phone Number (10 digits)"
                   value='<%= request.getAttribute("phone-number") != null ? request.getAttribute("phone-number") : "" %>'
                   pattern="[0-9]{10}" title="Phone number must be 10 digits" required>
        </div>

        <!-- Mail -->                                          <%-- ADDED --%>
        <div class="form-group">
            <label for="mail">Email Address</label>
            <input type="email" id="mail" name="mail"
                   value='<%= request.getAttribute("mail") != null ? request.getAttribute("mail") : "" %>'
                   placeholder="Enter Email Address" required>
        </div>

        <!-- IFSC Code -->
        <div class="form-group">
            <label for="ifsc-code">IFSC Code</label>
            <input type="text" id="ifsc-code" name="ifsc-code"  value="SBI5001TU" readonly>
        </div>

        <!-- Branch -->
        <div class="form-group">
            <label for="branch">Branch</label>
               <input type="text" id="branch" name="branch"  readonly value=<%= ((Employee)session.getAttribute("employee")).getBranch() %> readonly>
        </div>

        <!-- Date of Birth -->
        <div class="form-group">
            <label for="dob">Date of Birth</label>
            <input type="date" id="dob" name="dob"
            value='<%=request.getAttribute("dob")%>'
             required>
        </div>

        <!-- Address -->
        <div class="form-group">
            <label for="address">Address</label>
            <textarea id="address" name="address"
             rows="3" placeholder="Enter Full Address" required><%= request.getAttribute("address") != null ? request.getAttribute("address") : "" %></textarea>
        </div>

        <!-- Initial Balance -->
        <div class="form-group">
            <label for="initial-balance">Initial Balance</label>
            <input type="number" id="initial-balance" name="initial-balance" min="1000" placeholder="Enter Initial Balance" value='<%= request.getAttribute("initial-balance") != null ? request.getAttribute("initial-balance") : "2000" %>' required>
        </div>

        <!-- Submit Button -->
        <div class="form-group">
            <button type="submit">Create Account</button>
        </div>
    </form>
</div>

</body>