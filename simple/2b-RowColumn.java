import java.util.Scanner;

class RowColumn {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Text: ");
        String text = sc.nextLine();

        System.out.print("Enter Rows: ");
        int rows = sc.nextInt();

        System.out.print("Enter Cols: ");
        int cols = sc.nextInt();

        int len = rows * cols;
        while (text.length() < len) text += 'X';
        text = text.substring(0, len);

        char[][] mat = new char[rows][cols];
        int k = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mat[i][j] = text.charAt(k++);
            }
        }

        StringBuilder enc = new StringBuilder();
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                enc.append(mat[i][j]);
            }
        }

        System.out.println("Encrypted: " + enc);

        sc.close();
    }
}
