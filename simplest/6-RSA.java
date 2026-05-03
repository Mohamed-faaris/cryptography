import java.util.*;
import java.security.*;
import javax.crypto.*;

class RSA {
    public static void main(String[] args) throws Exception {
        String msg = "Hello RSA";

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, kp.getPublic());
        byte[] enc = cipher.doFinal(msg.getBytes());

        cipher.init(Cipher.DECRYPT_MODE, kp.getPrivate());
        byte[] dec = cipher.doFinal(enc);

        System.out.println("Encrypted: " + HexFormat.of().formatHex(enc));
        System.out.println("Decrypted: " + new String(dec));
    }
}
