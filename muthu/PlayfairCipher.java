import java.util.Scanner;

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter two letters: ");
        String text = sc.nextLine().toUpperCase();

        int[] p1 = find(text.charAt(0));
        int[] p2 = find(text.charAt(1));

        System.out.println("Encrypted Text: " +
                key[p1[0]][p2[1]] + key[p2[0]][p1[1]]);
    }
}