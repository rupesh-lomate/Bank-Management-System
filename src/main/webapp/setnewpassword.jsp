<!DOCTYPE html>
<html>
<head>
    <title>Set New Password</title>
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

        /* Success and error message styling */
        .message {
            margin-bottom: 20px;
            padding: 15px;
            text-align: center;
            border-radius: 5px;
            font-size: 16px;
            font-weight: bold;
        }
        .success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        .error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }

        /* Error message for password mismatch */
        .error-password {
            color: red;
            font-size: 14px;
            display: none;
        }

        /* Go to Login button styling */
        .login-link {
            text-align: center;
            margin-top: 10px;
        }
        .login-link a {
            color: red;
            text-decoration: underline;
            font-size: 16px;
        }
        .login-link a:hover {
            text-decoration: none;
        }
    </style>
    <script>
        function validatePasswords() {
            var newPassword = document.getElementById("newPassword").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            var errorMessage = document.getElementById("errorMessage");

            if (newPassword !== confirmPassword) {
                errorMessage.style.display = "block";
                return false;
            }
            errorMessage.style.display = "none";
            return true;
        }

        // Function to redirect to login page
        function redirectToLogin() {
            window.location.href = 'login.jsp'; // Replace with your login page URL
        }

        // Prevent back button navigation
        function preventBack() {
            // Push a new state to the history
            history.pushState(null, '', window.location.href);

            window.addEventListener('popstate', function(event) {
                // Push the same state again to prevent back navigation
                history.pushState(null, '', window.location.href);
                redirectToLogin();
            });
        }

        // Call the preventBack function on page load
        window.onload = function() {
            preventBack();
        };
    </script>
</head>
<body>
    <%
        boolean isAlreadyChanged = 
          request.getAttribute("isAlreadyChanged") != null ? (boolean)request.getAttribute("isAlreadyChanged") : false ;
        
        boolean canShowLoginButton = 
                request.getAttribute("canShowLoginButton") != null ? (boolean)request.getAttribute("canShowLoginButton") : false ;	
    %>
    <div class="container">
        <h2>Set New Password</h2>

        <!-- Success or Error message -->
        <% 
            String successMessage = (String) request.getAttribute("successMessage");
            String errorMessage = (String) request.getAttribute("errorMessage");
        %>
        
        <% if (successMessage != null) { %>
            <div class="message success">
                <%= successMessage %>
            </div>
        <% } %>

        <% if (errorMessage != null) { %>
            <div class="message error">
                <%= errorMessage %>
            </div>
        <% } %>

        <!-- Form -->
        <form method="post" action="setNewPasswordS" onsubmit="return validatePasswords()">
            <div class="form-group">
                <label for="newPassword">New Password:</label>
                <input type="password" id="newPassword" name="newPassword" required>
            </div>
            <div class="form-group">
                <label for="confirmPassword">Confirm Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
            </div>
            <div class="error-password" id="errorMessage">Passwords do not match. Please try again.</div>
            <input type="hidden" name="username" value='<%= request.getAttribute("username") %>'>
            <input type="hidden" name="isAlreadyChanged" value='<%= isAlreadyChanged %>'>
            <input type="hidden" name="canShowLoginButton" value='<%= canShowLoginButton %>'>
            <div class="form-group">
                <button type="submit">Set Password</button>
            </div>
        </form>

        <!-- Go to Login button -->
        <% if (canShowLoginButton) { %>
            <div class="login-link">
                <a href="login.jsp">Go to Login</a>
            </div>
        <% } %>
    </div>
</body>
</html>