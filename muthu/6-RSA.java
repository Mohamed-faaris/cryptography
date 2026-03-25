import java.math.BigInteger;
import java.util.Scanner;

class RSA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BigInteger p = sc.nextBigInteger();
        BigInteger q = sc.nextBigInteger();
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = new BigInteger("3");
        while (!phi.gcd(e).equals(BigInteger.ONE)) {
            e = e.add(BigInteger.ONE);
        }

        BigInteger d = e.modInverse(phi);

        BigInteger msg = sc.nextBigInteger();
        BigInteger enc = msg.modPow(e, n);
        BigInteger dec = enc.modPow(d, n);

        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);

        sc.close();
    }
}
