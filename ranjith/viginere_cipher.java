import java.util.Scanner;

public class viginere_cipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Plaintext: ");
        String text = sc.nextLine().toUpperCase();
        
        System.out.print("Enter Key: ");
        String key = sc.nextLine().toUpperCase();

        String encrypted = encrypt(text, key);
        System.out.println("Ciphertext: " + encrypted);

        sc.close();
    }

    static String encrypt(String text, String key) {
        StringBuilder res = new StringBuilder();
        int keyIndex = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                int shift = key.charAt(keyIndex % key.length()) - 'A';
                char encryptedChar = (char) ((c - 'A' + shift) % 26 + 'A');
                res.append(encryptedChar);
                keyIndex++;
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }
}

// SAMPLE OUTPUTS:
// ==============
// Input: Plaintext: HELLO, Key: KEY
// Output:
//   Ciphertext: RIJVS
//
// Input: Plaintext: ATTACK, Key: LEMON
// Output:
//   Ciphertext: LXFOPVEFRNHR
