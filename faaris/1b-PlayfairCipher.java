import java.util.Scanner;

class PlayfairCipher {
    static char[][] keyMatrix = new char[5][5];

    static void generateKeyMatrix(String key) {
        key = key.toUpperCase().replace("J", "I");
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[26];

        for (char c : key.toCharArray()) {
            if (c >= 'A' && c <= 'Z' && !visited[c - 'A']) {
                sb.append(c);
                visited[c - 'A'] = true;
            }
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (c != 'J' && !visited[c - 'A']) {
                sb.append(c);
            }
        }

        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                keyMatrix[i][j] = sb.charAt(k++);
            }
        }
    }

    static int[] find(char c) {
        if (c == 'J') c = 'I';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keyMatrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    static String encryptPair(char a, char b) {
        int[] p1 = find(a);
        int[] p2 = find(b);

        if (p1[0] == p2[0]) {
            return "" + keyMatrix[p1[0]][(p1[1] + 1) % 5] + keyMatrix[p2[0]][(p2[1] + 1) % 5];
        } else if (p1[1] == p2[1]) {
            return "" + keyMatrix[(p1[0] + 1) % 5][p1[1]] + keyMatrix[(p2[0] + 1) % 5][p2[1]];
        } else {
            return "" + keyMatrix[p1[0]][p2[1]] + keyMatrix[p2[0]][p1[1]];
        }
    }

    static String decryptPair(char a, char b) {
        int[] p1 = find(a);
        int[] p2 = find(b);

        if (p1[0] == p2[0]) {
            return "" + keyMatrix[p1[0]][(p1[1] - 1 + 5) % 5] + keyMatrix[p2[0]][(p2[1] - 1 + 5) % 5];
        } else if (p1[1] == p2[1]) {
            return "" + keyMatrix[(p1[0] - 1 + 5) % 5][p1[1]] + keyMatrix[(p2[0] - 1 + 5) % 5][p2[1]];
        } else {
            return "" + keyMatrix[p1[0]][p2[1]] + keyMatrix[p2[0]][p1[1]];
        }
    }

    static String prepareText(String text) {
        text = text.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(text.charAt(i));
            if (i + 1 < text.length() && text.charAt(i) == text.charAt(i + 1)) {
                sb.append('X');
            }
        }
        if (sb.length() % 2 != 0) {
            sb.append('X');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Key: ");
        String key = sc.nextLine();

        System.out.print("Enter Text: ");
        String text = sc.nextLine();

        generateKeyMatrix(key);
        String prepared = prepareText(text);

        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < prepared.length(); i += 2) {
            encrypted.append(encryptPair(prepared.charAt(i), prepared.charAt(i + 1)));
        }

        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < encrypted.length(); i += 2) {
            decrypted.append(decryptPair(encrypted.charAt(i), encrypted.charAt(i + 1)));
        }

        System.out.println("Encrypted Text: " + encrypted);
        System.out.println("Decrypted Text: " + decrypted);
    }
}
