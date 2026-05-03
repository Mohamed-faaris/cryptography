import java.security.MessageDigest;

public class SHA {
    public static void main(String[] args) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] hash = md.digest("Hello".getBytes());

        for (byte b : hash) {
            System.out.print(String.format("%02x", b));
        }
    }
}

