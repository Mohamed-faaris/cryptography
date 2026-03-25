import java.math.BigInteger;
import java.util.Scanner;

class DiffieHellman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter prime number (p): ");
        BigInteger p = sc.nextBigInteger();

        System.out.print("Enter primitive root (g): ");
        BigInteger g = sc.nextBigInteger();

        System.out.print("Enter private key of User A: ");
        BigInteger a = sc.nextBigInteger();

        System.out.print("Enter private key of User B: ");
        BigInteger b = sc.nextBigInteger();

        BigInteger A = g.modPow(a, p);
        BigInteger B = g.modPow(b, p);

        System.out.println("\nPublic key of A: " + A);
        System.out.println("Public key of B: " + B);

        BigInteger keyA = B.modPow(a, p);
        BigInteger keyB = A.modPow(b, p);

        System.out.println("\nSecret key computed by A: " + keyA);
        System.out.println("Secret key computed by B: " + keyB);

        sc.close();
    }
}
