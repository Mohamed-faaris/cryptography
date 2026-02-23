# Cryptography Programs - Muthu

Fixed/Static implementations of classical and modern encryption algorithms.

---

## 1a. Caesar Cipher

### Substitution Cipher - Monoalphabetic

### Code Explanation

```
1. Take plaintext and key (shift value) from user
2. Convert text to uppercase
3. Encryption: C = (P + K) mod 26
4. Decryption: P = (C - K) mod 26
```

### Code

```java
import java.util.Scanner;

class CaesarCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Text: ");
        String text = sc.nextLine().toUpperCase();

        System.out.print("Enter Key: ");
        int key = sc.nextInt();

        String enc = "", dec = "";

        for (int i = 0; i < text.length(); i++)
            enc += (char)((text.charAt(i) - 'A' + key) % 26 + 'A');

        for (int i = 0; i < enc.length(); i++)
            dec += (char)((enc.charAt(i) - 'A' - key + 26) % 26 + 'A');

        System.out.println("Encrypted Text: " + enc);
        System.out.println("Decrypted Text: " + dec);
    }
}
```

### Sample Input/Output

```
Input:  HELLO, Key: 3
Output: Encrypted: KHOOR, Decrypted: HELLO

Input:  ATTACK, Key: 5
Output: Encrypted: FYYFHP, Decrypted: ATTACK
```

---

## 1b. Playfair Cipher

### Substitution Cipher - Digraph

### Code Explanation

```
1. Fixed 5x5 key matrix (MONARCHY)
2. Prepare plaintext in pairs
3. Apply encryption rules
```

### Code

```java
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
```

### Sample Input/Output

```
Input:  MO
Output: Encrypted: OM

Input:  HE
Output: Encrypted: EH
```

---

## 1c. Hill Cipher

### Substitution Cipher - Polyalphabetic (Matrix-based)

### Code Explanation

```
1. Fixed 2x2 key matrix: {{3,3},{2,5}}
2. Matrix multiplication mod 26
```

### Code

```java
import java.util.Scanner;

class HillCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] key = {{3, 3}, {2, 5}};

        System.out.print("Enter 2-letter text: ");
        String text = sc.nextLine().toUpperCase();

        int a = text.charAt(0) - 'A';
        int b = text.charAt(1) - 'A';

        char c1 = (char) (((key[0][0] * a + key[0][1] * b) % 26) + 'A');
        char c2 = (char) (((key[1][0] * a + key[1][1] * b) % 26) + 'A');

        System.out.println("Encrypted Text: " + c1 + c2);
    }
}
```

### Sample Input/Output

```
Input:  HI
Output: Encrypted: TC

Input:  AT
Output: Encrypted: AY
```

---

## 1d. Vigenere Cipher

### Polyalphabetic Substitution Cipher

### Code Explanation

```
1. Take plaintext and key from user
2. Repeat key to match plaintext length
3. Apply Caesar shift with repeating key
```

### Code

```java
import java.util.Scanner;

class VigenereCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Text: ");
        String text = sc.nextLine().toUpperCase();

        System.out.print("Enter Key: ");
        String key = sc.nextLine().toUpperCase();

        String enc = "", dec = "";

        for (int i = 0; i < text.length(); i++)
            enc += (char)((text.charAt(i) - 'A' +
                    key.charAt(i % key.length()) - 'A') % 26 + 'A');

        for (int i = 0; i < enc.length(); i++)
            dec += (char)((enc.charAt(i) -
                    key.charAt(i % key.length()) + 26) % 26 + 'A');

        System.out.println("Encrypted Text: " + enc);
        System.out.println("Decrypted Text: " + dec);
    }
}
```

### Sample Input/Output

```
Input:  HELLO, Key: KEY
Output: Encrypted: RIJVS, Decrypted: HELLO

Input:  ATTACK, Key: LEMON
Output: Encrypted: LXFOPVEFRNHR, Decrypted: ATTACK
```

---

## 2a. Rail Fence Cipher

### Transposition Cipher

### Code Explanation

```
1. Take plaintext from user
2. Write text in 3 rails (zigzag)
3. Read row by row
```

### Code

```java
import java.util.Scanner;

class RailFence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Text: ");
        String text = sc.nextLine();

        String r1="", r2="", r3="";

        for (int i = 0; i < text.length(); i++) {
            if (i % 4 == 0) r1 += text.charAt(i);
            else if (i % 2 == 1) r2 += text.charAt(i);
            else r3 += text.charAt(i);
        }

        System.out.println("Encrypted Text: " + r1 + r2 + r3);
    }
}
```

### Sample Input/Output

```
Input:  HELLO
Output: Encrypted: HOELL

Input:  ATTACKATDAWN
Output: Encrypted: AACTWTAKADN
```

---

## 2b. Row Column Transposition

### Transposition Cipher

### Code Explanation

```
1. Fixed 2x3 matrix (6 characters)
2. Fill row-wise, read column-wise
```

### Code

```java
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
```

### Sample Input/Output

```
Input:  ATTACK
Output: Encrypted: AATCTK

Input:  SECRET
Output: Encrypted: SETCER
```

---

## 3. AES (Advanced Encryption Standard)

### Symmetric Block Cipher

### Code Explanation

```
1. Fixed 16-char key: "1234567890123456"
2. Uses Java javax.crypto library
3. AES-128 encryption/decryption
```

### Code

```java
import java.util.Scanner;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

class AES {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Message: ");
        String msg = sc.nextLine();

        SecretKey key = new SecretKeySpec("1234567890123456".getBytes(), "AES");
        Cipher c = Cipher.getInstance("AES");

        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] enc = c.doFinal(msg.getBytes());

        c.init(Cipher.DECRYPT_MODE, key);
        byte[] dec = c.doFinal(enc);

        System.out.println("Decrypted Text: " + new String(dec));
    }
}
```

### Sample Input/Output

```
Input:  Hello World
Output: Decrypted: Hello World

Input:  Secret Message
Output: Decrypted: Secret Message
```

---

## 4. DES (Data Encryption Standard)

### Symmetric Block Cipher

### Code Explanation

```
1. Fixed 8-char key: "12345678"
2. Uses Java javax.crypto library
3. DES encryption/decryption
```

### Code

```java
import java.util.Scanner;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

class DES {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Message: ");
        String msg = sc.nextLine();

        SecretKey key = new SecretKeySpec("12345678".getBytes(), "DES");
        Cipher c = Cipher.getInstance("DES");

        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] enc = c.doFinal(msg.getBytes());

        c.init(Cipher.DECRYPT_MODE, key);
        byte[] dec = c.doFinal(enc);

        System.out.println("Decrypted Text: " + new String(dec));
    }
}
```

### Sample Input/Output

```
Input:  SecretMsg
Output: Decrypted: SecretMsg

Input:  MyData
Output: Decrypted: MyData
```

---

## Quick Revision Summary

| Cipher | Type | Key Type |
|--------|------|----------|
| Caesar | Monoalphabetic | Single number |
| Playfair | Digraph | 5×5 matrix |
| Hill | Polyalphabetic | 2×2 matrix |
| Vigenere | Polyalphabetic | Word |
| Rail Fence | Transposition | Number (rails) |
| Row Column | Transposition | Rows × Cols |
| AES | Block | 16 chars |
| DES | Block | 8 chars |
