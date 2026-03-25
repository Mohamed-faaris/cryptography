import java.security.*;
import java.util.Base64;
import java.util.Scanner;

class DigitalSignature {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter message: ");
        String msg = sc.nextLine();

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();

        Signature s = Signature.getInstance("SHA256withRSA");
        s.initSign(kp.getPrivate());
        s.update(msg.getBytes());
        byte[] sign = s.sign();

        System.out.println("\nSignature: " + Base64.getEncoder().encodeToString(sign));

        s.initVerify(kp.getPublic());
        s.update(msg.getBytes());

        System.out.println("Verified: " + s.verify(sign));

        sc.close();
    }
}
