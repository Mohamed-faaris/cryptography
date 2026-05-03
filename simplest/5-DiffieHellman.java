import java.math.BigInteger;
import java.util.Scanner;

class DiffieHellman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter prime (p): ");
        BigInteger p = sc.nextBigInteger();

        System.out.print("Enter primitive root (g): ");
        BigInteger g = sc.nextBigInteger();

        System.out.print("Private key A: ");
        BigInteger a = sc.nextBigInteger();

        System.out.print("Private key B: ");
        BigInteger b = sc.nextBigInteger();

        BigInteger A = g.modPow(a, p);
        BigInteger B = g.modPow(b, p);

        System.out.println("\nPublic Key A: " + A);
        System.out.println("Public Key B: " + B);

        BigInteger keyA = B.modPow(a, p);
        BigInteger keyB = A.modPow(b, p);

        System.out.println("\nSecret Key A: " + keyA);
        System.out.println("Secret Key B: " + keyB);

        sc.close();
    }
}
