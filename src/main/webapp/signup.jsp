<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign In - Step 2</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            max-width: 500px;
            margin-top: 100px;
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #0073e6;
            text-align: center;
            font-weight: bold;
            margin-bottom: 20px;
        }
        .alert {
            font-size: 15px;
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 20px;
            text-align: center;
        }
        .alert-success {
            background-color: #e6f9ec;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        .alert-error {
            background-color: #fce8e6;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
        .btn-custom {
            background-color: #0073e6;
            color: white;
            border-radius: 5px;
            border: none;
            padding: 12px;
            font-size: 16px;
            width: 100%;
        }
        .btn-custom:hover {
            background-color: #005bb5;
        }
        .btn-login {
            color: red;
            text-decoration: underline;
            background: none;
            border: none;
            font-size: 16px;
            margin-top: 20px;
            display: block;
            margin-left: auto;
            margin-right: auto;
        }
        .btn-login:hover {
            color: darkred;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Bank Sign Up - Step 2</h2>

        <% 
            // Check for error message
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) { 
        %>
            <div class="alert alert-error"><%= errorMessage %></div>
        <% } %>

        <% 
            // Check for success message
            String successMessage = (String) request.getAttribute("successMessage");
            if (successMessage != null) { 
        %>
            <div class="alert alert-success"><%= successMessage %></div>
        <% } %>

        <!-- Sign In Form -->
        <form action="signUpS" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" id="username" name="username" class="form-control" required />
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" id="password" name="password" class="form-control" required />
            </div>
            <input type="hidden" name="accNo" value='<%= request.getAttribute("accNo") %>' >
            <button type="submit" class="btn-custom">Sign In</button>
        </form>

        <% 
            // Show the "Go to Login" button below the form if there's a success message
            if (successMessage != null) { 
        %>
            <!-- Go to Login Button -->
            <form action="login.html" method="get">
                <button type="submit" class="btn-login">Go to Login</button>
            </form>
        <% } %>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
