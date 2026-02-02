import java.util.*;
public class hill_cipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] key = {{3, 3}, {2, 5}};
        System.out.print("Enter Plaintext: ");
        String text = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "");
if (text.length() % 2 != 0) text += "X";
        String cipher = "";
for (int i = 0; i < text.length(); i += 2) {
        int p1 = text.charAt(i) - 'A';
        int p2 = text.charAt(i + 1) - 'A';
        int c1 = (key[0][0] * p1 + key[0][1] * p2) % 26;
        int c2 = (key[1][0] * p1 + key[1][1] * p2) % 26;
            cipher += (char) (c1 + 'A');
            cipher += (char) (c2 + 'A');
}
        System.out.println("Ciphertext: " + cipher);
        sc.close();
   }
}
