import java.math.BigInteger;
import java.util.Scanner;

class RSA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter p: ");
        BigInteger p = sc.nextBigInteger();

        System.out.print("Enter q: ");
        BigInteger q = sc.nextBigInteger();

        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = new BigInteger("3");
        while (!phi.gcd(e).equals(BigInteger.ONE)) {
            e = e.add(BigInteger.ONE);
        }

        BigInteger d = e.modInverse(phi);

        System.out.println("\nn = " + n);
        System.out.println("e = " + e);
        System.out.println("d = " + d);

        System.out.print("\nEnter message (number): ");
        BigInteger msg = sc.nextBigInteger();

        BigInteger enc = msg.modPow(e, n);
        BigInteger dec = enc.modPow(d, n);

        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);

        sc.close();
    }
}
