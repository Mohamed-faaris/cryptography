class CaesarCipher {
    public static void main(String[] args) {
        String text = "HELLO WORLD";
        int key = 3;

        StringBuilder enc = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                enc.append((char)((c - 'A' + key) % 26 + 'A'));
            } else {
                enc.append(c);
            }
        }

        StringBuilder dec = new StringBuilder();
        for (int i = 0; i < enc.length(); i++) {
            char c = enc.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                dec.append((char)((c - 'A' - key + 26) % 26 + 'A'));
            } else {
                dec.append(c);
            }
        }

        System.out.println("Text: " + text);
        System.out.println("Key: " + key);
        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);
    }
}
