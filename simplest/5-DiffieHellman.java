import java.math.BigInteger;

class DiffieHellman {
    public static void main(String[] args) {
        BigInteger p = BigInteger.valueOf(23);
        BigInteger g = BigInteger.valueOf(5);
        BigInteger a = BigInteger.valueOf(6);
        BigInteger b = BigInteger.valueOf(15);

        BigInteger A = g.modPow(a, p);
        BigInteger B = g.modPow(b, p);

        System.out.println("\nPublic Key A: " + A);
        System.out.println("Public Key B: " + B);

        BigInteger keyA = B.modPow(a, p);
        BigInteger keyB = A.modPow(b, p);

        System.out.println("\nSecret Key A: " + keyA);
        System.out.println("Secret Key B: " + keyB);
    }
}
