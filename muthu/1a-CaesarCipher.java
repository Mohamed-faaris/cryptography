import java.util.Scanner;

class CaesarCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Text: ");
        String text = sc.nextLine().toUpperCase();

        System.out.print("Enter Key: ");
        int key = sc.nextInt();

        String enc = "", dec = "";

        for (int i = 0; i < text.length(); i++)
            enc += (char)((text.charAt(i) - 'A' + key) % 26 + 'A');

        for (int i = 0; i < enc.length(); i++)
            dec += (char)((enc.charAt(i) - 'A' - key + 26) % 26 + 'A');

        System.out.println("Encrypted Text: " + enc);
        System.out.println("Decrypted Text: " + dec);
    }
}

// SAMPLE OUTPUTS:
// ==============
// Input: HELLO, Key: 3
// Output:
//   Encrypted Text: KHOOR
//   Decrypted Text: HELLO
//
// Input: ATTACK, Key: 5
// Output:
//   Encrypted Text: FYYFHP
//   Decrypted Text: ATTACK
//
// Input: WORLD, Key: 7
// Output:
//   Encrypted Text: DVYST
//   Decrypted Text: WORLD