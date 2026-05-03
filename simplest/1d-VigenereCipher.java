class VigenereCipher {
    public static void main(String[] args) {
        String text = "ATTACKATDAWN";
        String key = "LEMON";

        StringBuilder enc = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                enc.append((char)((c - 'A' + key.charAt(i % key.length()) - 'A') % 26 + 'A'));
            } else {
                enc.append(c);
            }
        }

        StringBuilder dec = new StringBuilder();
        for (int i = 0; i < enc.length(); i++) {
            char c = enc.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                dec.append((char)((c - 'A' - key.charAt(i % key.length()) + 26) % 26 + 'A'));
            } else {
                dec.append(c);
            }
        }

        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);
    }
}
