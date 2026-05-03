class RailFence {
    public static void main(String[] args) {
        String text = "WEAREDISCOVERED";
        int rails = 3;

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

        System.out.println("Text: " + text);
        System.out.println("Encrypted: " + enc);
    }
}
