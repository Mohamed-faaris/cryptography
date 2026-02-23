import java.util.Scanner;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

class DES {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Message: ");
        String msg = sc.nextLine();

        System.out.print("Enter Key (8 chars for DES): ");
        String keyStr = sc.nextLine();

        while (keyStr.length() < 8) {
            keyStr += '0';
        }
        keyStr = keyStr.substring(0, 8);

        SecretKey key = new SecretKeySpec(keyStr.getBytes(), "DES");
        Cipher c = Cipher.getInstance("DES");

        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] enc = c.doFinal(msg.getBytes());

        c.init(Cipher.DECRYPT_MODE, key);
        byte[] dec = c.doFinal(enc);

        System.out.println("Encrypted (hex): " + bytesToHex(enc));
        System.out.println("Decrypted Text: " + new String(dec));
    }

    static String bytesToHex(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (byte val : b) {
            sb.append(String.format("%02x", val));
        }
        return sb.toString();
    }
}

// SAMPLE OUTPUTS:
// ==============
// Input: Message: SecretMsg, Key: mykey123
// Output:
//   Encrypted (hex): [varies]
//   Decrypted Text: SecretMsg
//
// Input: Message: Hello, Key: 12345678
// Output:
//   Encrypted (hex): [varies]
//   Decrypted Text: Hello