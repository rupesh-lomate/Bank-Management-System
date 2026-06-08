<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign up - Step 1</title>
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
        }
        .error-message {
            color: #ff0000; /* Red text */
            font-size: 16px; /* Slightly larger text */
            font-weight: bold; /* Bold text */
            text-align: center; /* Center alignment */
            margin-bottom: 20px;
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
    </style>
</head>
<body>

    <div class="container">
        <h2>Bank Sign Up - Step 1</h2>

        <% 
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) { 
        %>
            <div class="error-message"><%= errorMessage %></div>
        <% } %>

        <form action="verifyRegisterS" method="post">
            <div class="mb-3">
                <label for="accno" class="form-label">Account Number</label>
                <input type="text" id="accNo" name="accNo" class="form-control" required />
            </div>
            <div class="mb-3">
                <label for="mobile" class="form-label">Mobile Number</label>
                <input type="text" id="mobile" name="phoneNo" class="form-control" required />
            </div>
            <button type="submit" class="btn-custom">Next</button>
        </form>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
