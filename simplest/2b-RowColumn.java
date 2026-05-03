class RowColumn {
    public static void main(String[] args) {
        String text = "HELLOWORLD";
        int rows = 2;
        int cols = 5;

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

        System.out.println("Text: " + text);
        System.out.println("Encrypted: " + enc);
    }
}
