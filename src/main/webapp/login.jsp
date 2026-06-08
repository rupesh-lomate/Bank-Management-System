<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Swarajya Bank Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(to right, #003580, #0056a1);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            color: white;
        }

        .container {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 15px;
            padding: 40px;
            box-shadow: 0 8px 30px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 400px;
        }

        .login-box h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #003580; /* SBI Blue */
            font-size: 24px;
        }

        .radio-group {
            margin-bottom: 20px;
            text-align: center;
        }

        .radio-group label {
            margin: 0 15px;
            font-weight: bold;
            color: #003580;
        }

        .textbox {
            margin-bottom: 15px;
            position: relative; /* For positioning the eye icon */
        }

        .textbox input {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            transition: border-color 0.3s;
        }

        .textbox input:focus {
            border-color: #0056a1; /* Focus color */
            outline: none;
        }

        .btn {
            width: 100%;
            padding: 12px;
            background-color: #003580; /* SBI Blue */
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 18px;
            transition: background-color 0.3s;
        }

        .btn:hover {
            background-color: #0056a1; /* Darker Blue */
        }

        .forgot-password, .signup {
            text-align: center;
            margin-top: 15px;
        }

        .forgot-password a, .signup a {
            color: #003580; /* SBI Blue */
            text-decoration: none;
            font-weight: bold;
        }

        .forgot-password a:hover, .signup a:hover {
            text-decoration: underline;
        }

        .signup p {
            margin: 10px 0 0;
            font-size: 14px;
            color: #333; /* Darker text for better visibility */
        }

        .error-message {
            color: red;
            font-weight: bold;
            text-align: center;
        }

        /* Eye icon style */
        .eye-icon {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            cursor: pointer;
            font-size: 18px;
            color: #003580;
            display: none; /* Initially hidden */
        }
    </style>
</head>
<body>

<div class="container">
    <div class="login-box">
        <h1>SWARAJYA BANK LOGIN</h1>

        <!-- Display error message if login fails -->
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
        <div class="error-message">
            <p><%= errorMessage %></p>
        </div>
        
        <%
            }errorMessage = null;
        %>

        <form id="loginForm" method="post" action="loginS">
            <div class="radio-group">
                <label>
                    <input type="radio" name="user" value="customer" <% if ("customer".equals(request.getParameter("user"))) { %>checked<% } %> required>
                    Customer
                </label>
                <label>
                    <input type="radio" name="user" value="employee" <% if ("employee".equals(request.getParameter("user"))) { %>checked<% } %> >
                    Employee
                </label>
            </div>
            <div class="textbox">
                <input type="text" placeholder="Username" name="username" required value="<%= request.getParameter("username") != null ? request.getParameter("username") : "" %>">
            </div>
            <div class="textbox">
                <input type="password" id="password" placeholder="Password" name="password" required>
                <!-- Eye Icon -->
                <span id="eyeIcon" class="eye-icon fa fa-eye" onclick="togglePasswordVisibility()"></span>
            </div>
            
            <button type="submit" class="btn">Login</button>
            <div class="forgot-password">
                <a href="forgotpassword.jsp">Forgot Password?</a>
            </div>
            <div class="signup">
                <p>Don't have an account? <a href="verifyregister.jsp">Sign Up</a></p>
            </div>
        </form>
    </div>
</div>

<script>
    // Show or hide the eye icon when the user starts typing in the password field
    const passwordInput = document.getElementById('password');
    const eyeIcon = document.getElementById('eyeIcon');

    passwordInput.addEventListener('input', function() {
        if (passwordInput.value.length > 0) {
            eyeIcon.style.display = 'block'; // Show eye icon
        } else {
            eyeIcon.style.display = 'none'; // Hide eye icon if password is empty
        }
    });

    // Toggle password visibility when the eye icon is clicked
    function togglePasswordVisibility() {
        if (passwordInput.type === 'password') {
            passwordInput.type = 'text'; // Show password
            eyeIcon.classList.remove('fa-eye'); // Change to 'eye-slash' icon
            eyeIcon.classList.add('fa-eye-slash');
        } else {
            passwordInput.type = 'password'; // Hide password
            eyeIcon.classList.remove('fa-eye-slash');
            eyeIcon.classList.add('fa-eye');
        }
    }
</script>

</body>
</html>
