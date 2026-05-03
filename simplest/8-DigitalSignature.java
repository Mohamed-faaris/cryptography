import java.security.*;

public class DSS {
    public static void main(String[] args) throws Exception {

        String msg = "Hi";

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512);
        KeyPair keys = keyGen.generateKeyPair();

        Signature sign = Signature.getInstance("SHA256withRSA");

        sign.initSign(keys.getPrivate());
        sign.update(msg.getBytes());
        byte[] signature = sign.sign();

        sign.initVerify(keys.getPublic());
        sign.update(msg.getBytes());
        boolean ok = sign.verify(signature);

        System.out.println("Verified: " + ok);
    }
}