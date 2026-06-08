<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>  
<!DOCTYPE html>
<html>
<head>
    <title>Forgot Password</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f5f5f5;
            color: #333;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 400px;
            margin: 100px auto;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        h2 {
            text-align: center;
            color: #0073e6;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-group input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .form-group button {
            width: 100%;
            padding: 10px;
            background: #0073e6;
            border: none;
            color: white;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        .form-group button:hover {
            background: #005bb5;
        }
        .otp-display {
            text-align: center;
            margin: 20px 0;
            font-size: 20px;
            color: #0073e6;
            font-weight: bold;
        }
        .error-message {
            color: red;
            font-weight: bold;
            text-align: center;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>

    <%
      String username = (String)request.getAttribute("username");
      Object generatedOTP =  request.getAttribute("generatedOTP");
    %>
    <div class="container">
        <h2>Forgot Password</h2>
        <form method="post" action="forgotPasswordS">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" value='<%=username != null ? username : "" %>' required placeholder="enter username">
                <% if (request.getAttribute("errorMessage") != null) { %>
                    <div class="error-message"><%= request.getAttribute("errorMessage") %></div>
                <% } %>
            </div>
            <div class="form-group">
                <button type="submit" name="getOTP">Get OTP</button>
            </div>
        </form>

        <% 
            if (generatedOTP != null) {
        %>
            <div class="otp-display">
                Your OTP: <%= generatedOTP %>
            </div>
        <% } %>
        
        <form method="post" action="forgotPasswordS">
           
            <div class="form-group">
                <label for="otp">Enter OTP:</label>
                <input type="text" id="otp" name="otp" value='<%=(request.getAttribute("otp") != null) ? request.getAttribute("otp") : "" %>' required placeholder="enter otp">
                <input type="hidden" name="username" value='<%=username != null ? username : "" %>'>
                 <input type="hidden" name="generatedOTP" value='<%=generatedOTP != null ? generatedOTP : "" %>'>
            </div>
            <div class="form-group">
                <button type="submit" name="verifyOTP">Verify OTP</button>
            </div>
        </form>

        <% if (request.getAttribute("errorMessageOTP") != null) { %>
            <div class="error-message"><%= request.getAttribute("errorMessageOTP") %></div>
        <% } %>
    </div>
    
</body>
</html>

