import java.math.BigInteger;
import java.util.Scanner;
import java.security.SecureRandom;
import java.util.Base64;

class RSA {
    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== RSA Encryption ===");
            System.out.println("1. Encrypt Message");
            System.out.println("2. Decrypt Message");
            System.out.println("3. Generate Key Pair");
            System.out.println("4. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> encrypt(sc);
                case 2 -> decrypt(sc);
                case 3 -> generateKeys(sc);
                case 4 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void encrypt(Scanner sc) {
        System.out.print("Enter message (as BigInteger): ");
        BigInteger msg = sc.nextBigInteger();

        System.out.print("Enter public key (n): ");
        BigInteger n = sc.nextBigInteger();

        System.out.print("Enter public exponent (e): ");
        BigInteger e = sc.nextBigInteger();

        BigInteger encrypted = msg.modPow(e, n);
        System.out.println("\nEncrypted: " + encrypted);
        System.out.println("Encrypted (Base64): " + Base64.getEncoder().encodeToString(encrypted.toByteArray()));
    }

    private static void decrypt(Scanner sc) {
        System.out.print("Enter ciphertext (as BigInteger): ");
        BigInteger cipher = sc.nextBigInteger();

        System.out.print("Enter private key (n): ");
        BigInteger n = sc.nextBigInteger();

        System.out.print("Enter private exponent (d): ");
        BigInteger d = sc.nextBigInteger();

        BigInteger decrypted = cipher.modPow(d, n);
        System.out.println("\nDecrypted: " + decrypted);
    }

    private static void generateKeys(Scanner sc) {
        System.out.print("Enter bit length for p and q (建议 512-1024): ");
        int bits = sc.nextInt();

        BigInteger p = generatePrime(bits);
        BigInteger q = generatePrime(bits);
        while (q.equals(p)) {
            q = generatePrime(bits);
        }

        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = generateE(phi);
        BigInteger d = e.modInverse(phi);

        System.out.println("\n=== Public Key ===");
        System.out.println("n: " + n);
        System.out.println("e: " + e);

        System.out.println("\n=== Private Key ===");
        System.out.println("n: " + n);
        System.out.println("d: " + d);

        System.out.println("\n=== Full Key Pair (Base64) ===");
        System.out.println("Public Key (n,e): " + Base64.getEncoder().encodeToString(
            (n.toString() + "," + e.toString()).getBytes()));
        System.out.println("Private Key (n,d): " + Base64.getEncoder().encodeToString(
            (n.toString() + "," + d.toString()).getBytes()));
    }

    private static BigInteger generatePrime(int bits) {
        return BigInteger.probablePrime(bits, random);
    }

    private static BigInteger generateE(BigInteger phi) {
        BigInteger e = BigInteger.probablePrime(phi.bitLength() / 2, random);
        while (!phi.gcd(e).equals(BigInteger.ONE)) {
            e = e.add(BigInteger.TWO);
        }
        return e;
    }
}
