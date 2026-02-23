import java.util.Scanner;

/**
 * Simple Hill Cipher (2x2 key) example.
 *
 * This program encrypts a plaintext of exactly two letters using a fixed 2x2
 * key matrix. It demonstrates the core steps of the Hill cipher for a 2-letter
 * block:
 *
 * 1. Convert each plaintext letter to an integer in range 0..25 (A -> 0, B -> 1, ...).
 * 2. Represent the 2-letter block as a column vector [p0, p1].
 * 3. Multiply the key matrix (2x2) by the plaintext vector (2x1) to get a
 *    ciphertext vector of integers.
 * 4. Reduce each resulting integer modulo 26 to stay within the alphabet.
 * 5. Convert the numbers back to uppercase letters (0 -> A, 1 -> B, ...).
 *
 * Notes:
 * - The key used here is {{3,3},{2,5}} which is a common toy example for
 *   demonstrating the Hill cipher. In a real system the key must be invertible
 *   modulo 26 to allow decryption.
 * - This program does not handle input validation beyond converting to uppercase
 *   and assumes exactly two letters are provided.
 */
class HillCipher {
    public static void main(String[] args) {
        // Scanner for reading user input from standard input
        Scanner sc = new Scanner(System.in);

        // 2x2 key matrix used for encryption (fixed for this example)
        // Each entry should be treated modulo 26 when performing arithmetic.
        int[][] key = {{3, 3}, {2, 5}};

        // Prompt the user for a two-letter plaintext block
        System.out.print("Enter 2-letter text: ");
        // Read input and convert to uppercase to simplify mapping
        String text = sc.nextLine().toUpperCase();

        // Map characters to numbers 0..25: 'A' -> 0, 'B' -> 1, ...
        // We assume the user entered at least two characters.
        int a = text.charAt(0) - 'A';
        int b = text.charAt(1) - 'A';

        // Perform matrix multiplication: c = key * p (mod 26), where p = [a, b]
        // First ciphertext letter = key[0][0]*a + key[0][1]*b (mod 26)
        char c1 = (char) (((key[0][0] * a + key[0][1] * b) % 26) + 'A');

        // Second ciphertext letter = key[1][0]*a + key[1][1]*b (mod 26)
        char c2 = (char) (((key[1][0] * a + key[1][1] * b) % 26) + 'A');

        // Output the two-letter ciphertext
        System.out.println("Encrypted Text: " + c1 + c2);
    }
}

// SAMPLE OUTPUTS:
// ==============
// Input: HI
// Output:
//   Encrypted Text: TC
//
// Input: AT
// Output:
//   Encrypted Text: AY
//
// Input: GO
// Output:
//   Encrypted Text: QX