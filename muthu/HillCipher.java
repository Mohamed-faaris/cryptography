import java.util.Scanner;

class HillCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] key = {{3,3},{2,5}};

        System.out.print("Enter 2-letter text: ");
        String text = sc.nextLine().toUpperCase();

        int a = text.charAt(0) - 'A';
        int b = text.charAt(1) - 'A';

        char c1 = (char)((key[0][0]*a + key[0][1]*b) % 26 + 'A');
        char c2 = (char)((key[1][0]*a + key[1][1]*b) % 26 + 'A');

        System.out.println("Encrypted Text: " + c1 + c2);
    }
}