import java.security.*;
import java.util.Base64;
import java.util.Scanner;

class DigitalSignature {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        KeyPair kp = null;

        while (true) {
            System.out.println("\n=== Digital Signature ===");
            System.out.println("1. Generate Key Pair");
            System.out.println("2. Sign Message");
            System.out.println("3. Verify Signature");
            System.out.println("4. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> kp = generateKeys(sc);
                case 2 -> {
                    if (kp == null) {
                        System.out.println("Generate key pair first!");
                        continue;
                    }
                    signMessage(sc, kp);
                }
                case 3 -> {
                    if (kp == null) {
                        System.out.println("Generate key pair first!");
                        continue;
                    }
                    verifySignature(sc, kp);
                }
                case 4 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static KeyPair generateKeys(Scanner sc) {
        System.out.print("Enter key size (1024, 2048, 4096): ");
        int keySize = sc.nextInt();

        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(keySize);
            KeyPair kp = kpg.generateKeyPair();

            System.out.println("\nKey Pair Generated!");
            System.out.println("Public Key: " + Base64.getEncoder().encodeToString(
                kp.getPublic().getEncoded()));
            System.out.println("Private Key: " + Base64.getEncoder().encodeToString(
                kp.getPrivate().getEncoded()));

            return kp;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    private static void signMessage(Scanner sc, KeyPair kp) {
        System.out.print("Enter message to sign: ");
        String msg = sc.next();

        try {
            Signature s = Signature.getInstance("SHA256withRSA");
            s.initSign(kp.getPrivate());
            s.update(msg.getBytes());

            byte[] signature = s.sign();

            System.out.println("\nSignature (Base64): " + 
                Base64.getEncoder().encodeToString(signature));
            System.out.println("Signature (hex): ");
            for (byte b : signature) {
                System.out.print(String.format("%02x", b));
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void verifySignature(Scanner sc, KeyPair kp) {
        System.out.print("Enter original message: ");
        String msg = sc.next();

        System.out.print("Enter signature (Base64): ");
        String sigStr = sc.next();

        try {
            byte[] signature = Base64.getDecoder().decode(sigStr);

            Signature s = Signature.getInstance("SHA256withRSA");
            s.initVerify(kp.getPublic());
            s.update(msg.getBytes());

            boolean verified = s.verify(signature);

            System.out.println("\nVerification: " + (verified ? "SUCCESS" : "FAILED"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
