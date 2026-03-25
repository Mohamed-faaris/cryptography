import java.math.BigInteger;
import java.util.Scanner;
import java.security.SecureRandom;

class DiffieHellman {
    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Diffie Hellman Key Exchange ===");
            System.out.println("1. Generate Keys with Custom Prime");
            System.out.println("2. Generate Keys with Random Prime");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> customKeys(sc);
                case 2 -> randomKeys(sc);
                case 3 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void customKeys(Scanner sc) {
        System.out.print("Enter prime number (p): ");
        BigInteger p = sc.nextBigInteger();

        System.out.print("Enter primitive root (g): ");
        BigInteger g = sc.nextBigInteger();

        System.out.print("Enter private key for User A: ");
        BigInteger a = sc.nextBigInteger();

        System.out.print("Enter private key for User B: ");
        BigInteger b = sc.nextBigInteger();

        computeKeys(g, p, a, b);
    }

    private static void randomKeys(Scanner sc) {
        System.out.print("Enter bit length for prime (建议 512-1024): ");
        int bits = sc.nextInt();

        BigInteger p = generatePrime(bits);
        BigInteger g = findPrimitiveRoot(p);

        BigInteger a = generatePrivateKey(p);
        BigInteger b = generatePrivateKey(p);

        System.out.println("\nGenerated Prime (p): " + p);
        System.out.println("Primitive Root (g): " + g);
        System.out.println("Private Key A: " + a);
        System.out.println("Private Key B: " + b);

        computeKeys(g, p, a, b);
    }

    private static void computeKeys(BigInteger g, BigInteger p, BigInteger a, BigInteger b) {
        BigInteger A = g.modPow(a, p);
        BigInteger B = g.modPow(b, p);

        System.out.println("\nPublic Key A: " + A);
        System.out.println("Public Key B: " + B);

        BigInteger keyA = B.modPow(a, p);
        BigInteger keyB = A.modPow(b, p);

        System.out.println("\nSecret Key computed by A: " + keyA);
        System.out.println("Secret Key computed by B: " + keyB);
        System.out.println("Keys Match: " + keyA.equals(keyB));
    }

    private static BigInteger generatePrime(int bits) {
        return BigInteger.probablePrime(bits, random);
    }

    private static BigInteger generatePrivateKey(BigInteger p) {
        BigInteger privateKey;
        do {
            privateKey = new BigInteger(p.bitLength(), random);
        } while (privateKey.compareTo(BigInteger.TWO) < 0 || 
                 privateKey.compareTo(p.subtract(BigInteger.TWO)) > 0);
        return privateKey;
    }

    private static BigInteger findPrimitiveRoot(BigInteger p) {
        if (p.equals(BigInteger.TWO)) return BigInteger.ONE;

        BigInteger phi = p.subtract(BigInteger.ONE);
        BigInteger[] factors = primeFactors(phi);

        for (BigInteger g = BigInteger.TWO; g.compareTo(p) < 0; g = g.add(BigInteger.ONE)) {
            boolean isPrimitive = true;
            for (BigInteger factor : factors) {
                if (g.modPow(phi.divide(factor), p).equals(BigInteger.ONE)) {
                    isPrimitive = false;
                    break;
                }
            }
            if (isPrimitive) return g;
        }
        return BigInteger.TWO;
    }

    private static BigInteger[] primeFactors(BigInteger n) {
        java.util.List<BigInteger> factors = new java.util.ArrayList<>();
        BigInteger temp = n;
        for (BigInteger i = BigInteger.TWO; i.multiply(i).compareTo(temp) <= 0; 
             i = i.add(BigInteger.ONE)) {
            while (temp.mod(i).equals(BigInteger.ZERO)) {
                factors.add(i);
                temp = temp.divide(i);
            }
        }
        if (temp.compareTo(BigInteger.ONE) > 0) {
            factors.add(temp);
        }
        return factors.toArray(new BigInteger[0]);
    }
}
