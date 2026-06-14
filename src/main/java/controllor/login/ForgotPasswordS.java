package controllor.login;

import dao.customer.CustomerDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.mail.OTPManager;
import service.mail.OTPUtil;
import services.customer.CustomerServices;

import java.io.IOException;

public class ForgotPasswordS extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String action =
                request.getParameter("action");

        String username =
                request.getParameter("username");

        if ("sendOtp".equals(action)) {

            if (username == null || username.isBlank()) {

                request.setAttribute(
                        "errorMessage",
                        "Username is required."
                );

                request.getRequestDispatcher(
                        "forgotpassword.jsp"
                ).forward(request, response);

                return;
            }

            if (!CustomerServices.containUsername(username)) {

                request.setAttribute(
                        "errorMessage",
                        "Invalid Username."
                );

                request.getRequestDispatcher(
                        "forgotpassword.jsp"
                ).forward(request, response);

                return;
            }

            String email =
                    CustomerDao.getMailByUsername(username);

            if (email == null || email.isBlank()) {

                request.setAttribute(
                        "errorMessage",
                        "No email registered with this account. Please contact your branch."
                );

                request.getRequestDispatcher(
                        "forgotpassword.jsp"
                ).forward(request, response);

                return;
            }

            String otp =
                    OTPManager.generateOtp();

            OTPManager.saveOtp(
                    username,
                    otp
            );

            OTPUtil.sendOTP(
                    email,
                    otp
            );

            request.setAttribute(
                    "username",
                    username
            );

            request.setAttribute(
                    "otpSent",
                    true
            );

            request.getRequestDispatcher(
                    "forgotpassword.jsp"
            ).forward(request, response);

            return;
        }

        if ("verifyOtp".equals(action)) {

            String enteredOtp =
                    request.getParameter("otp");

            request.setAttribute(
                    "username",
                    username
            );

            request.setAttribute(
                    "otpSent",
                    true
            );

            if (enteredOtp == null
                    || enteredOtp.isBlank()) {

                request.setAttribute(
                        "errorMessageOTP",
                        "Please enter OTP."
                );

                request.getRequestDispatcher(
                        "forgotpassword.jsp"
                ).forward(request, response);

                return;
            }

            boolean verified =
                    OTPManager.verifyOtp(
                            username,
                            enteredOtp
                    );

            if (!verified) {

                request.setAttribute(
                        "errorMessageOTP",
                        "Invalid OTP. Please try again."
                );

                request.getRequestDispatcher(
                        "forgotpassword.jsp"
                ).forward(request, response);

                return;
            }

            request.setAttribute(
                    "username",
                    username
            );

            request.getRequestDispatcher(
                    "setnewpassword.jsp"
            ).forward(request, response);
        }
    }

}