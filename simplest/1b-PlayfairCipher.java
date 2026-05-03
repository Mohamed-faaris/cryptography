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

    static String solve(String t) {
        StringBuilder enc = new StringBuilder();
        for (int i = 0; i < t.length(); i += 2) {
            int[] p1 = find(t.charAt(i));
            int[] p2 = find(t.charAt(i+1));
            if (p1[0] == p2[0]) {
                enc.append(key[p1[0]][(p1[1] + 1) % 5]);
                enc.append(key[p2[0]][(p2[1] + 1) % 5]);
            } else if (p1[1] == p2[1]) {
                enc.append(key[(p1[0] + 1) % 5][p1[1]]);
                enc.append(key[(p2[0] + 1) % 5][p2[1]]);
            } else {
                enc.append(key[p1[0]][p2[1]]);
                enc.append(key[p2[0]][p1[1]]);
            }
        }
        return enc.toString();
    }

    public static void main(String[] args) {
        String text = "HELLOWORLD";
        if (text.length() % 2 != 0) text += 'X';
        System.out.println(text + " -> " + solve(text));
    }
}
