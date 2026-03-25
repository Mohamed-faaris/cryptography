import java.util.Scanner;

class HillCipher {
    static int mod26(int x) {
        x = x % 26;
        if (x < 0) x += 26;
        return x;
    }

    static int[][] invertMatrix(int[][] key) {
        int det = (key[0][0] * key[1][1] - key[0][1] * key[1][0]) % 26;
        det = mod26(det);

        int invDet = 1;
        for (int i = 1; i < 26; i++) {
            if (mod26(det * i) == 1) {
                invDet = i;
                break;
            }
        }

        int[][] inv = new int[2][2];
        inv[0][0] = mod26(key[1][1] * invDet);
        inv[0][1] = mod26(-key[0][1] * invDet);
        inv[1][0] = mod26(-key[1][0] * invDet);
        inv[1][1] = mod26(key[0][0] * invDet);
        return inv;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] key = {{3, 3}, {2, 5}};

        System.out.print("Enter Text: ");
        String text = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "");

        while (text.length() % 2 != 0) {
            text += 'X';
        }

        StringBuilder encrypted = new StringBuilder();
        for (int k = 0; k < text.length(); k += 2) {
            int[] vec = {text.charAt(k) - 'A', text.charAt(k + 1) - 'A'};
            int[] result = {
                mod26(key[0][0] * vec[0] + key[0][1] * vec[1]),
                mod26(key[1][0] * vec[0] + key[1][1] * vec[1])
            };
            encrypted.append((char)(result[0] + 'A')).append((char)(result[1] + 'A'));
        }

        int[][] invKey = invertMatrix(key);
        StringBuilder decrypted = new StringBuilder();
        for (int k = 0; k < encrypted.length(); k += 2) {
            int[] vec = {encrypted.charAt(k) - 'A', encrypted.charAt(k + 1) - 'A'};
            int[] result = {
                mod26(invKey[0][0] * vec[0] + invKey[0][1] * vec[1]),
                mod26(invKey[1][0] * vec[0] + invKey[1][1] * vec[1])
            };
            decrypted.append((char)(result[0] + 'A')).append((char)(result[1] + 'A'));
        }

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        sc.close();
    }
}
