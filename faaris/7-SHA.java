import java.security.MessageDigest;
import java.util.Scanner;

class SHA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== SHA Hashing ===");
            System.out.println("1. SHA-256");
            System.out.println("2. SHA-1");
            System.out.println("3. SHA-512");
            System.out.println("4. MD5");
            System.out.println("5. Exit");
            System.out.print("Choose algorithm: ");
            int choice = sc.nextInt();

            if (choice == 5) {
                System.out.println("Exiting...");
                break;
            }

            System.out.print("Enter message to hash: ");
            String msg = sc.next();

            String algorithm = switch (choice) {
                case 1 -> "SHA-256";
                case 2 -> "SHA-1";
                case 3 -> "SHA-512";
                case 4 -> "MD5";
                default -> {
                    System.out.println("Invalid choice");
                    yield null;
                }
            };

            if (algorithm != null) {
                try {
                    hashMessage(msg, algorithm);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        sc.close();
    }

    private static void hashMessage(String text, String algorithm) throws Exception {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] hash = md.digest(text.getBytes());

        System.out.println("\nAlgorithm: " + algorithm);
        System.out.print("Hash (hex): ");
        for (byte b : hash) {
            System.out.print(String.format("%02x", b));
        }
        System.out.println();

        System.out.print("Hash (base64): ");
        System.out.println(java.util.Base64.getEncoder().encodeToString(hash));

        System.out.println("Hash length: " + (hash.length * 8) + " bits");
    }
}
