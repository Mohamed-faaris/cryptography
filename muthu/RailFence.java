import java.util.Scanner;

class RailFence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Text: ");
        String text = sc.nextLine();

        String r1="", r2="", r3="";

        for (int i = 0; i < text.length(); i++) {
            if (i % 4 == 0) r1 += text.charAt(i);
            else if (i % 2 == 1) r2 += text.charAt(i);
            else r3 += text.charAt(i);
        }

        System.out.println("Encrypted Text: " + r1 + r2 + r3);
    }
}