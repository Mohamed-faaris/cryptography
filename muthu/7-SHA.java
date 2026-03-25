import java.security.MessageDigest;

class SHA {
    public static void main(String[] args) throws Exception {
        String text = "Hello";

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(text.getBytes());

        for (byte b : hash) {
            System.out.print(String.format("%02x", b));
        }
    }
}
