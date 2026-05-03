import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;

import java.util.Base64;

public class DES {
	public static void main(String[] args) throws Exception {
		String plainText = "Hello DES";

		// Generate Secret Key
		KeyGenerator keygen = KeyGenerator.getInstance("DES");
		// KeyGenerator keygen = KeyGenerator.getInstance("AES");
		keygen.init(56);
		SecretKey secret = keygen.generateKey();

		// Create Cipher
		Cipher cipher = Cipher.getInstance("DES");
		// Cipher cipher = Cipher.getInstance("AES");

		// Encrypt
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
		String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);

		// Decrypt
		cipher.init(Cipher.DECRYPT_MODE, secret);
		byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
		String decryptedText = new String(decryptedBytes);

		System.out.println("Encrypted Text: " + encryptedText);
		System.out.println("Decrypted Text: " + decryptedText);
	}
}
