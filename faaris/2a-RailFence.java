import java.util.Scanner;

class RailFence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Text: ");
        String text = sc.nextLine();

        System.out.print("Enter Number of Rails: ");
        int rails = sc.nextInt();

        if (rails < 2) rails = 2;

        StringBuilder[] rail = new StringBuilder[rails];
        for (int i = 0; i < rails; i++) {
            rail[i] = new StringBuilder();
        }

        int[] sequence = new int[text.length()];
        int pos = 0;
        boolean down = false;

        for (int i = 0; i < text.length(); i++) {
            if (i == 0 || i == (rails - 1)) {
                down = !down;
            }
            sequence[i] = pos;
            rail[pos].append(text.charAt(i));
            if (!down) {
                pos++;
            } else {
                pos--;
            }
        }

        StringBuilder enc = new StringBuilder();
        for (int i = 0; i < rails; i++) {
            enc.append(rail[i]);
        }

        StringBuilder dec = new StringBuilder();
        int[] index = new int[rails];
        for (int i = 0; i < text.length(); i++) {
            dec.append(rail[sequence[i]].charAt(index[sequence[i]]++));
        }

        System.out.println("Encrypted Text: " + enc);
        System.out.println("Decrypted Text: " + dec);
    }
}
