class HillCipher {
    static int[][] key = {
        {3, 3},
        {2, 5}
    };

    public static void main(String[] args) {
        String text = "HI";

        int[] vec = {
            text.charAt(0) - 'A',
            text.charAt(1) - 'A'
        };

        char first = (char) (((key[0][0] * vec[0] + key[0][1] * vec[1]) % 26) + 'A');
        char second = (char) (((key[1][0] * vec[0] + key[1][1] * vec[1]) % 26) + 'A');

        System.out.println("Text: " + text);
        System.out.println("Encrypted Text: " + first + second);
    }
}
