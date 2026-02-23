import java.util.Scanner;
public class caesar_cipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();
        System.out.print("Enter the shift value: ");
        int shift = scanner.nextInt();
        String ciphertext = encrypt(plaintext, shift);
        System.out.println("Ciphertext: " + ciphertext);
        scanner.close();
    }
   public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isUpperCase(ch)) {
                char c = (char) (((ch + shift - 65) % 26) + 65);
                result.append(c);
            } else if (Character.isLowerCase(ch)) {
                char c = (char) (((ch + shift - 97) % 26) + 97);
                result.append(c);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
}

// SAMPLE OUTPUTS:
// ==============
// Input: plaintext: HELLO, shift: 3
// Output:
//   Ciphertext: KHOOR
//
// Input: plaintext: ATTACK, shift: 5
// Output:
//   Ciphertext: FYYFHP