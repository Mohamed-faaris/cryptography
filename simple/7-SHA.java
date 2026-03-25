import java.security.MessageDigest;
import java.util.Scanner;

class SHA {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter message: ");
        String msg = sc.nextLine();

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(msg.getBytes());

        System.out.print("\nHash: ");
        for (byte b : hash) {
            System.out.print(String.format("%02x", b));
        }
        System.out.println();

        sc.close();
    }
}
