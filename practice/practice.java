import java.util.*;
import java.security.*;

class Practice{
    public static void Main(String[] args){
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initalize(2048);
        KeyPair kp = kpg.generateKeyPair();

        Cipher c =  Cipher.getInstance("RSA");
        c.init(Cipher.ENCRYPT_MODE,kp.getPublic());

        
    }
}