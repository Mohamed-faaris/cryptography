import java.util.*;
public class playfair_cipher {
    static char[][] matrix = new char[5][5];
    static void generateMatrix(String key) {
        key=key.toUpperCase().replaceAll("[J]", "I").replaceAll("[^A-Z]", "");
        String temp ="";
        for(char c : key.toCharArray()) {
            if(temp.indexOf(c) ==-1) {
                temp += c;
     }
        }
        for(char c ='A';c<= 'Z';c++) {
            if(temp.indexOf(c) == -1 && c != 'J') {
                temp += c;
        }
        }
        int k = 0;
        for(int i=0; i<5; i++){
            for(int j =0;j<5; j++){
                matrix[i][j] = temp.charAt(k++);
    }
    }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter key: ");
        String key = scanner.nextLine();
        
        generateMatrix(key);
        
        System.out.println("\nPlayfair Matrix:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        scanner.close();
    }
}

// SAMPLE OUTPUTS:
// ==============
// Input: key: MONARCHY
// Output:
//   Playfair Matrix:
//   M O N A R 
//   C H Y B D 
//   E F G I K 
//   L P Q S T 
//   U V W X Z
