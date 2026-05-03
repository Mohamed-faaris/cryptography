import java.util.Scanner;

class RailFence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Text: ");
        String text = sc.nextLine();

        System.out.print("Enter Rails: ");
        int rails = sc.nextInt();

        if (rails < 2) rails = 2;

        StringBuilder[] rail = new StringBuilder[rails];
        for (int i = 0; i < rails; i++) {
            rail[i] = new StringBuilder();
        }

        int pos = 0;
        boolean down = false;

        for (int i = 0; i < text.length(); i++) {
            rail[pos].append(text.charAt(i));
            if (i == 0 || i == rails - 1) down = !down;
            pos += down ? 1 : -1;
        }

        StringBuilder enc = new StringBuilder();
        for (int i = 0; i < rails; i++) {
            enc.append(rail[i]);
        }

        System.out.println("Encrypted: " + enc);

        sc.close();
    }
}
