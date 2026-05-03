class PlayfairCipher {
    static char[][] key = {
        {'M','O','N','A','R'},
        {'C','H','Y','B','D'},
        {'E','F','G','I','K'},
        {'L','P','Q','S','T'},
        {'U','V','W','X','Z'}
    };

    static int[] find(char c) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (key[i][j] == c)
                    return new int[]{i, j};
        return null;
    }

    static String solve(String text) {
        int[] p1 = find(text.charAt(0));
        int[] p2 = find(text.charAt(1));

        if (p1[0] == p2[0]) {
            return "" + key[p1[0]][(p1[1] + 1) % 5] + key[p2[0]][(p2[1] + 1) % 5];
        }

        if (p1[1] == p2[1]) {
            return "" + key[(p1[0] + 1) % 5][p1[1]] + key[(p2[0] + 1) % 5][p2[1]];
        }

        return "" + key[p1[0]][p2[1]] + key[p2[0]][p1[1]];
    }

    public static void main(String[] args) {
        String text = "HI";
        String enc = solve(text);

        System.out.println("Text: " + text);
        System.out.println("Encrypted Text: " + enc);
    }
}
