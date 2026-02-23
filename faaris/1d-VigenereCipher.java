import java.util.Scanner;

class VigenereCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Text: ");
        String text = sc.nextLine().toUpperCase();

        System.out.print("Enter Key: ");
        String key = sc.nextLine().toUpperCase();

        StringBuilder enc = new StringBuilder();
        StringBuilder dec = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                enc.append((char)((c - 'A' + key.charAt(i % key.length()) - 'A') % 26 + 'A'));
            } else {
                enc.append(c);
            }
        }

        for (int i = 0; i < enc.length(); i++) {
            char c = enc.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                dec.append((char)((c - 'A' - key.charAt(i % key.length()) - 'A' + 26) % 26 + 'A'));
            } else {
                dec.append(c);
            }
        }

        System.out.println("Encrypted Text: " + enc);
        System.out.println("Decrypted Text: " + dec);
    }
}
