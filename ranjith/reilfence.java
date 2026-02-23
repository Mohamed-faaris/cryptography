import java.util.Scanner;

public class reilfence {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int key = 2;

		System.out.print("Enter Plaintext: ");
		String text = sc.nextLine();

		String cipher = encrypt(text, key);
		System.out.println("Ciphertext: " + cipher);

		String decrypted = decrypt(cipher, key);
		System.out.println("Decrypted Text: " + decrypted);

		sc.close();
	}

	static String encrypt(String text, int key) {
		if (key <= 1 || text.length() <= 1) return text;

		StringBuilder row1 = new StringBuilder();
		StringBuilder row2 = new StringBuilder();

		for (int i = 0; i < text.length(); i++) {
			if (i % 2 == 0) {
				row1.append(text.charAt(i));
			} else {
				row2.append(text.charAt(i));
			}
		}

		return row1.toString() + row2.toString();
	}

	static String decrypt(String cipher, int key) {
		if (key <= 1 || cipher.length() <= 1) return cipher;

		int n = cipher.length();
		int row1Length = (n + 1) / 2;

		String row1 = cipher.substring(0, row1Length);
		String row2 = cipher.substring(row1Length);

		StringBuilder plain = new StringBuilder();
		int i = 0, j = 0;

		while (i < row1.length() || j < row2.length()) {
			if (i < row1.length()) plain.append(row1.charAt(i++));
			if (j < row2.length()) plain.append(row2.charAt(j++));
		}

		return plain.toString();
	}
}
