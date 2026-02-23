import java.util.Scanner;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

class DES {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Message: ");
        String msg = sc.nextLine();

        SecretKey key = new SecretKeySpec("12345678".getBytes(), "DES");
        Cipher c = Cipher.getInstance("DES");

        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] enc = c.doFinal(msg.getBytes());

        c.init(Cipher.DECRYPT_MODE, key);
        byte[] dec = c.doFinal(enc);

        System.out.println("Decrypted Text: " + new String(dec));
    }
}