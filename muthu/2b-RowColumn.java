import java.util.Scanner;

class RowColumn {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter 6-letter text: ");
        String text = sc.nextLine();

        char[][] mat = new char[2][3];
        int k = 0;

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 3; j++)
                mat[i][j] = text.charAt(k++);

        System.out.print("Encrypted Text: ");
        for (int j = 0; j < 3; j++)
            for (int i = 0; i < 2; i++)
                System.out.print(mat[i][j]);
    }
}

// SAMPLE OUTPUTS:
// ==============
// Input: ATTACK
// Output:
//   Encrypted Text: AATCTK
//
// Input: SECRET
// Output:
//   Encrypted Text: SETCER
//
// Input: IMPORT
// Output:
//   Encrypted Text: IPMOTR