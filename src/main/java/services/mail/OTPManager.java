package services.mail;

import java.security.SecureRandom;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class OTPManager {

    private static final long OTP_EXPIRY_MIN = 5;

    private static final ConcurrentHashMap<String, String> OTP_STORE =
            new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, Long> OTP_TIMESTAMP =
            new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, ScheduledFuture<?>> OTP_SCHEDULER =
            new ConcurrentHashMap<>();

    private static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    private OTPManager() {
    }

    public static void saveOtp(String username, String otp) {

        ScheduledFuture<?> existingTask =
                OTP_SCHEDULER.get(username);

        if (existingTask != null && !existingTask.isDone()) {
            existingTask.cancel(false);
            System.out.println(
                    "Old OTP scheduler cancelled for: " + username
            );
        }

        OTP_STORE.put(username, otp);
        OTP_TIMESTAMP.put(username, System.currentTimeMillis());

        ScheduledFuture<?> newTask = scheduler.schedule(() -> {
            OTP_STORE.remove(username);
            OTP_TIMESTAMP.remove(username);
            OTP_SCHEDULER.remove(username);
            System.out.println(
                    "OTP expired and removed for: " + username
            );
        }, OTP_EXPIRY_MIN, TimeUnit.MINUTES);

        OTP_SCHEDULER.put(username, newTask);
    }

    public static boolean verifyOtp(
            String username,
            String enteredOtp
    ) {

        String actualOtp =
                OTP_STORE.get(username);

        if (actualOtp == null) {
            return false;
        }

        Long savedTime =
                OTP_TIMESTAMP.get(username);

        if (savedTime == null ||
                (System.currentTimeMillis() - savedTime) > OTP_EXPIRY_MIN * 60 * 1000) {

            OTP_STORE.remove(username);
            OTP_TIMESTAMP.remove(username);
            OTP_SCHEDULER.remove(username);
            return false;
        }

        boolean verified =
                actualOtp.equals(enteredOtp);

        if (verified) {

            ScheduledFuture<?> task =
                    OTP_SCHEDULER.get(username);

            if (task != null) {
                task.cancel(false);
            }

            OTP_STORE.remove(username);
            OTP_TIMESTAMP.remove(username);
            OTP_SCHEDULER.remove(username);
        }

        return verified;
    }

    public static String generateOtp() {

        SecureRandom random =
                new SecureRandom();

        int otp =
                100000 + random.nextInt(900000);

        return String.valueOf(otp);
    }

    public static boolean isExpired(String username) {
        Long savedTime =
                OTP_TIMESTAMP.get(username);

        if (savedTime == null) return true;

        return (System.currentTimeMillis() - savedTime)
                > OTP_EXPIRY_MIN * 60 * 1000;
    }
}