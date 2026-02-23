import java.util.Scanner;

class CaesarCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Text: ");
        String text = sc.nextLine().toUpperCase();

        System.out.print("Enter Key: ");
        int key = sc.nextInt();

        String enc = "", dec = "";

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                enc += (char)((c - 'A' + key) % 26 + 'A');
            } else {
                enc += c;
            }
        }

        for (int i = 0; i < enc.length(); i++) {
            char c = enc.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                dec += (char)((c - 'A' - key + 26) % 26 + 'A');
            } else {
                dec += c;
            }
        }

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
// Input: WORLD, Key: 7
// Output:
//   Encrypted Text: DVYST
//   Decrypted Text: WORLD
//
// Input: ATTACK, Key: 5
// Output:
//   Encrypted Text: FYYFHP
//   Decrypted Text: ATTACK
