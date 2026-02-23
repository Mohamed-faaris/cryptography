import java.util.Scanner;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

class AES {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Message: ");
        String msg = sc.nextLine();

        System.out.print("Enter Key (16 chars for AES-128): ");
        String keyStr = sc.nextLine();

        while (keyStr.length() < 16) {
            keyStr += '0';
        }
        keyStr = keyStr.substring(0, 16);

        SecretKey key = new SecretKeySpec(keyStr.getBytes(), "AES");
        Cipher c = Cipher.getInstance("AES");

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
