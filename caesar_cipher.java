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

 
}