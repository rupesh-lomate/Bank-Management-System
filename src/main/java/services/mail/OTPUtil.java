package services.mail;

public final class OTPUtil {

    private OTPUtil() {
    }

    public static void sendOTP(
            String toMailAddress,
            String otp
    ) {

        String subject =
                "Swarajya Bank - OTP Verification";

        String message =
                "Dear Customer,\n\n" +
                        "Your One-Time Password (OTP) for account verification is:\n\n" +
                        otp + "\n\n" +
                        "This OTP is valid for 5 minutes.\n\n" +
                        "Please do not share this OTP with anyone.\n\n" +
                        "Regards,\n" +
                        "Swarajya Bank\n" +
                        "Secure Banking Services";

        MailUtil.sendMail(
                toMailAddress,
                subject,
                message
        );
    }
}