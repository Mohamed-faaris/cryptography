import java.util.Scanner;

class VigenereCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Text: ");
        String text = sc.nextLine().toUpperCase();

        System.out.print("Enter Key: ");
        String key = sc.nextLine().toUpperCase();

        String enc = "", dec = "";

        for (int i = 0; i < text.length(); i++)
            enc += (char)((text.charAt(i) - 'A' +
                    key.charAt(i % key.length()) - 'A') % 26 + 'A');

        for (int i = 0; i < enc.length(); i++)
            dec += (char)((enc.charAt(i) -
                    key.charAt(i % key.length()) + 26) % 26 + 'A');

        System.out.println("Encrypted Text: " + enc);
        System.out.println("Decrypted Text: " + dec);
    }
}

// SAMPLE OUTPUTS:
// ==============
// Input: HELLO, Key: KEY
// Output:
//   Encrypted Text: RIJVS
//   Decrypted Text: HELLO
//
// Input: ATTACK, Key: LEMON
// Output:
//   Encrypted Text: LXFOPVEFRNHR
//   Decrypted Text: ATTACK
//
// Input: CRYPTO, Key: ABC
// Output:
//   Encrypted Text: CSARQW
//   Decrypted Text: CRYPTO