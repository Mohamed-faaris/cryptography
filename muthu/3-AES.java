import java.util.Scanner;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

class AES {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Message: ");
        String msg = sc.nextLine();

        SecretKey key = new SecretKeySpec("1234567890123456".getBytes(), "AES");
        Cipher c = Cipher.getInstance("AES");

        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] enc = c.doFinal(msg.getBytes());

        c.init(Cipher.DECRYPT_MODE, key);
        byte[] dec = c.doFinal(enc);

        System.out.println("Decrypted Text: " + new String(dec));
    }
}

// SAMPLE OUTPUTS:
// ==============
// Input: Hello World
// Output:
//   Decrypted Text: Hello World
//
// Input: Secret Message
// Output:
//   Decrypted Text: Secret Message
//
// Input: Cryptography
// Output:
//   Decrypted Text: Cryptography