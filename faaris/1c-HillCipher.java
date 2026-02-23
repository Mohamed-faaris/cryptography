import java.util.Scanner;

class HillCipher {
    static int mod26(int x) {
        x = x % 26;
        if (x < 0) x += 26;
        return x;
    }

    static int[][] invertMatrix(int[][] key, int size) {
        int det = 0;
        for (int i = 0; i < size; i++) {
            det = (det + key[0][i] * key[1][(i + 1) % size]) % 26;
            det = (det - key[0][i] * key[1][(i + size - 1) % size]) % 26;
        }
        det = mod26(det);

        int invDet = 0;
        for (int i = 1; i < 26; i++) {
            if (mod26(det * i) == 1) {
                invDet = i;
                break;
            }
        }

        int[][] adj = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int sign = ((i + j) % 2 == 0) ? 1 : -1;
                int[][] minor = new int[size - 1][size - 1];
                int mi = 0, mj = 0;
                for (int ki = 0; ki < size; ki++) {
                    if (ki == i) continue;
                    mj = 0;
                    for (int kj = 0; kj < size; kj++) {
                        if (kj == j) continue;
                        minor[mi][mj++] = key[ki][kj];
                    }
                    mi++;
                }
                int minorDet = 0;
                if (size == 2) {
                    minorDet = minor[0][0] * minor[1][1] - minor[0][1] * minor[1][0];
                } else {
                    minorDet = minor[0][0] * (minor[1][1] * minor[2][2] - minor[1][2] * minor[2][1])
                             - minor[0][1] * (minor[1][0] * minor[2][2] - minor[1][2] * minor[2][0])
                             + minor[0][2] * (minor[1][0] * minor[2][1] - minor[1][1] * minor[2][0]);
                }
                adj[j][i] = mod26(sign * minorDet);
            }
        }

        int[][] inv = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                inv[i][j] = mod26(adj[i][j] * invDet);
            }
        }
        return inv;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Key Matrix Size (2 or 3): ");
        int size = sc.nextInt();

        int[][] key = new int[size][size];
        System.out.println("Enter " + size + "x" + size + " key matrix values:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                key[i][j] = sc.nextInt() % 26;
            }
        }

        sc.nextLine();
        System.out.print("Enter Text: ");
        String text = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "");

        while (text.length() % size != 0) {
            text += 'X';
        }

        StringBuilder encrypted = new StringBuilder();
        for (int k = 0; k < text.length(); k += size) {
            int[] vec = new int[size];
            for (int i = 0; i < size; i++) {
                vec[i] = text.charAt(k + i) - 'A';
            }

            int[] result = new int[size];
            for (int i = 0; i < size; i++) {
                int sum = 0;
                for (int j = 0; j < size; j++) {
                    sum += key[i][j] * vec[j];
                }
                result[i] = mod26(sum);
            }

            for (int i = 0; i < size; i++) {
                encrypted.append((char)(result[i] + 'A'));
            }
        }

        int[][] invKey = invertMatrix(key, size);
        StringBuilder decrypted = new StringBuilder();
        for (int k = 0; k < encrypted.length(); k += size) {
            int[] vec = new int[size];
            for (int i = 0; i < size; i++) {
                vec[i] = encrypted.charAt(k + i) - 'A';
            }

            int[] result = new int[size];
            for (int i = 0; i < size; i++) {
                int sum = 0;
                for (int j = 0; j < size; j++) {
                    sum += invKey[i][j] * vec[j];
                }
                result[i] = mod26(sum);
            }

            for (int i = 0; i < size; i++) {
                decrypted.append((char)(result[i] + 'A'));
            }
        }

        System.out.println("Encrypted Text: " + encrypted);
        System.out.println("Decrypted Text: " + decrypted);
    }
}
