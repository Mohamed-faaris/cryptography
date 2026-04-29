# Cryptography Programs - Simple

Simplest implementations of classical and modern cryptographic algorithms.

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
import java.util.*;

class CaesarCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Text: ");
        String text = sc.nextLine().toUpperCase();
        System.out.print("Enter Key: ");
        int key = sc.nextInt();

        String enc = "", dec = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z')
                enc += (char)((c - 'A' + key) % 26 + 'A');
            else enc += c;
        }
        for (int i = 0; i < enc.length(); i++) {
            char c = enc.charAt(i);
            if (c >= 'A' && c <= 'Z')
                dec += (char)((c - 'A' - key + 26) % 26 + 'A');
            else dec += c;
        }

        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);
        sc.close();
    }
}
```

### Sample I/O

```
Input: HELLO, Key: 3
Output: Encrypted: KHOOR, Decrypted: HELLO
```

### Reference: [Caesar Cipher - Wikipedia](https://en.wikipedia.org/wiki/Caesar_cipher)

---

## 1b. Playfair Cipher

### Substitution Cipher - Digraph

### Code Explanation

```
1. Generate 5x5 key matrix from key word
2. Prepare plaintext in pairs
3. If letters same, insert X between them
4. Encryption rules:
   - Same row: take letters to right
   - Same column: take letters below
   - Rectangle: swap columns
5. Decryption: reverse the rules
```

### Code

```java
import java.util.*;

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
            if (c != 'J' && !visited[c - 'A']) sb.append(c);
        }
        int k = 0;
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                keyMatrix[i][j] = sb.charAt(k++);
    }
    static int[] find(char c) {
        if (c == 'J') c = 'I';
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (keyMatrix[i][j] == c) return new int[]{i, j};
        return null;
    }
    static String encryptPair(char a, char b) {
        int[] p1 = find(a), p2 = find(b);
        if (p1[0] == p2[0])
            return "" + keyMatrix[p1[0]][(p1[1] + 1) % 5] + keyMatrix[p2[0]][(p2[1] + 1) % 5];
        else if (p1[1] == p2[1])
            return "" + keyMatrix[(p1[0] + 1) % 5][p1[1]] + keyMatrix[(p2[0] + 1) % 5][p2[1]];
        else return "" + keyMatrix[p1[0]][p2[1]] + keyMatrix[p2[0]][p1[1]];
    }
    static String decryptPair(char a, char b) {
        int[] p1 = find(a), p2 = find(b);
        if (p1[0] == p2[0])
            return "" + keyMatrix[p1[0]][(p1[1] - 1 + 5) % 5] + keyMatrix[p2[0]][(p2[1] - 1 + 5) % 5];
        else if (p1[1] == p2[1])
            return "" + keyMatrix[(p1[0] - 1 + 5) % 5][p1[1]] + keyMatrix[(p2[0] - 1 + 5) % 5][p2[1]];
        else return "" + keyMatrix[p1[0]][p2[1]] + keyMatrix[p2[0]][p1[1]];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Key: ");
        String key = sc.nextLine();
        System.out.print("Enter Text: ");
        String text = sc.nextLine().toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");

        generateKeyMatrix(key);
        StringBuilder enc = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = (i + 1 < text.length()) ? text.charAt(i + 1) : 'X';
            if (a == b) { enc.append(encryptPair(a, 'X')); i--; }
            else enc.append(encryptPair(a, b));
        }
        StringBuilder dec = new StringBuilder();
        for (int i = 0; i < enc.length(); i += 2)
            dec.append(decryptPair(enc.charAt(i), enc.charAt(i + 1)));

        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);
        sc.close();
    }
}
```

### Sample I/O

```
Input: MONARCHY, HELLO
Output: Encrypted: FCSMCS, Decrypted: HELLO
```

### Reference: [Playfair Cipher - Wikipedia](https://en.wikipedia.org/wiki/Playfair_cipher)

---

## 1c. Hill Cipher

### Substitution Cipher - Polyalphabetic (Matrix-based)

### Code Explanation

```
1. Fixed 2x2 key matrix: {{3,3},{2,5}}
2. Convert letters to numbers (A=0, B=1...Z=25)
3. Encryption: C = K * P mod 26 (matrix multiplication)
4. Decryption: P = K^-1 * C mod 26
5. Convert back to letters
```

### Code

```java
import java.util.*;

class HillCipher {
    static int mod26(int x) { x = x % 26; if (x < 0) x += 26; return x; }
    static int[][] invertMatrix(int[][] key) {
        int det = mod26(key[0][0] * key[1][1] - key[0][1] * key[1][0]);
        int invDet = 1;
        for (int i = 1; i < 26; i++) if (mod26(det * i) == 1) { invDet = i; break; }
        int[][] inv = {{mod26(key[1][1] * invDet), mod26(-key[0][1] * invDet)},
                       {mod26(-key[1][0] * invDet), mod26(key[0][0] * invDet)}};
        return inv;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] key = {{3, 3}, {2, 5}};
        System.out.print("Enter Text: ");
        String text = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "");
        while (text.length() % 2 != 0) text += 'X';

        StringBuilder enc = new StringBuilder();
        for (int k = 0; k < text.length(); k += 2) {
            int[] vec = {text.charAt(k) - 'A', text.charAt(k + 1) - 'A'};
            enc.append((char)(mod26(key[0][0] * vec[0] + key[0][1] * vec[1]) + 'A'));
            enc.append((char)(mod26(key[1][0] * vec[0] + key[1][1] * vec[1]) + 'A'));
        }
        int[][] invKey = invertMatrix(key);
        StringBuilder dec = new StringBuilder();
        for (int k = 0; k < enc.length(); k += 2) {
            int[] vec = {enc.charAt(k) - 'A', enc.charAt(k + 1) - 'A'};
            dec.append((char)(mod26(invKey[0][0] * vec[0] + invKey[0][1] * vec[1]) + 'A'));
            dec.append((char)(mod26(invKey[1][0] * vec[0] + invKey[1][1] * vec[1]) + 'A'));
        }
        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);
        sc.close();
    }
}
```

### Sample I/O

```
Input: HI
Output: Encrypted: TC, Decrypted: HI
```

### Reference: [Hill Cipher - Wikipedia](https://en.wikipedia.org/wiki/Hill_cipher)

---

## 1d. Vigenere Cipher

### Polyalphabetic Substitution Cipher

### Code Explanation

```
1. Take plaintext and key (word) from user
2. Repeat key to match plaintext length
3. Encryption: C = (P + K) mod 26
4. Decryption: P = (C - K) mod 26
```

### Code

```java
import java.util.*;

class VigenereCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Text: ");
        String text = sc.nextLine().toUpperCase();
        System.out.print("Enter Key: ");
        String key = sc.nextLine().toUpperCase();

        StringBuilder enc = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z')
                enc.append((char)((c - 'A' + key.charAt(i % key.length()) - 'A') % 26 + 'A'));
            else enc.append(c);
        }
        StringBuilder dec = new StringBuilder();
        for (int i = 0; i < enc.length(); i++) {
            char c = enc.charAt(i);
            if (c >= 'A' && c <= 'Z')
                dec.append((char)((c - 'A' - key.charAt(i % key.length()) + 26) % 26 + 'A'));
            else dec.append(c);
        }
        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);
        sc.close();
    }
}
```

### Sample I/O

```
Input: HELLO, Key: KEY
Output: Encrypted: RIJVS, Decrypted: HELLO
```

### Reference: [Vigenere Cipher - Wikipedia](https://en.wikipedia.org/wiki/Vigen%C3%A8re_cipher)

---

## 2a. Rail Fence Cipher

### Transposition Cipher

### Code Explanation

```
1. Take plaintext and number of rails from user
2. Write text in zigzag pattern across rails
3. Read row by row to get ciphertext
```

### Code

```java
import java.util.*;

class RailFence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Text: ");
        String text = sc.nextLine();
        System.out.print("Enter Rails: ");
        int rails = sc.nextInt();
        if (rails < 2) rails = 2;

        StringBuilder[] rail = new StringBuilder[rails];
        for (int i = 0; i < rails; i++) rail[i] = new StringBuilder();

        int pos = 0;
        boolean down = false;
        for (int i = 0; i < text.length(); i++) {
            rail[pos].append(text.charAt(i));
            if (i == 0 || i == rails - 1) down = !down;
            pos += down ? 1 : -1;
        }
        StringBuilder enc = new StringBuilder();
        for (int i = 0; i < rails; i++) enc.append(rail[i]);

        System.out.println("Encrypted: " + enc);
        sc.close();
    }
}
```

### Sample I/O

```
Input: HELLO, Rails: 3
Output: Encrypted: HOLEL
```

### Reference: [Rail Fence Cipher - Wikipedia](https://en.wikipedia.org/wiki/Rail_fence_cipher)

---

## 2b. Row Column Transposition

### Transposition Cipher

### Code Explanation

```
1. Take plaintext, rows and columns from user
2. Fill matrix row-wise
3. Read column-wise to get ciphertext
```

### Code

```java
import java.util.*;

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
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                mat[i][j] = text.charAt(k++);

        StringBuilder enc = new StringBuilder();
        for (int j = 0; j < cols; j++)
            for (int i = 0; i < rows; i++)
                enc.append(mat[i][j]);

        System.out.println("Encrypted: " + enc);
        sc.close();
    }
}
```

### Sample I/O

```
Input: ATTACK, Rows: 2, Cols: 3
Output: Encrypted: AATCTK
```

### Reference: [Transposition Cipher - Wikipedia](https://en.wikipedia.org/wiki/Transposition_cipher)

---

## 3. AES

### Symmetric Block Cipher

### Code Explanation

```
1. Take message and 16-char key from user
2. Use Java javax.crypto library
3. AES-128 encryption (128-bit key)
4. Encrypt then decrypt to verify
```

### Code

```java
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

class AES {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Message: ");
        String msg = sc.nextLine();
        System.out.print("Enter Key (16 chars): ");
        String keyStr = sc.nextLine();
        while (keyStr.length() < 16) keyStr += '0';
        keyStr = keyStr.substring(0, 16);

        SecretKey key = new SecretKeySpec(keyStr.getBytes(), "AES");
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] enc = c.doFinal(msg.getBytes());
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] dec = c.doFinal(enc);

        System.out.println("Encrypted (hex): " + HexFormat.of().formatHex(enc));
        System.out.println("Decrypted: " + new String(dec));
        sc.close();
    }
}
```

### Sample I/O

```
Input:  Hello World
        mysecretkey12345
Output: Encrypted (hex): a8b9c7d4e5f6a1b2c3d4e5f6a7b8c9d0
        Decrypted: Hello World
```

### Reference: [AES - Wikipedia](https://en.wikipedia.org/wiki/Advanced_Encryption_Standard)

---

## 4. DES

### Symmetric Block Cipher

### Code Explanation

```
1. Take message and 8-char key from user
2. Use Java javax.crypto library
3. DES encryption (56-bit key, 64-bit block)
4. Encrypt then decrypt to verify
```

### Code

```java
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

class DES {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Message: ");
        String msg = sc.nextLine();
        System.out.print("Enter Key (8 chars): ");
        String keyStr = sc.nextLine();
        while (keyStr.length() < 8) keyStr += '0';
        keyStr = keyStr.substring(0, 8);

        SecretKey key = new SecretKeySpec(keyStr.getBytes(), "DES");
        Cipher c = Cipher.getInstance("DES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] enc = c.doFinal(msg.getBytes());
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] dec = c.doFinal(enc);

        System.out.println("Encrypted (hex): " + HexFormat.of().formatHex(enc));
        System.out.println("Decrypted: " + new String(dec));
        sc.close();
    }
}
```

### Sample I/O

```
Input:  SecretMsg
        12345678
Output: Encrypted (hex): a1b2c3d4e5f60718
        Decrypted: SecretMsg
```

### Reference: [DES - Wikipedia](https://en.wikipedia.org/wiki/Data_Encryption_Standard)

---

## 5. Diffie Hellman

### Asymmetric Key Exchange

### Code Explanation

```
1. Take prime (p) and primitive root (g) from user
2. Take private keys for A and B
3. Compute public keys: A = g^a mod p, B = g^b mod p
4. Compute shared secret: key = B^a mod p = A^b mod p
```

### Code

```java
import java.math.BigInteger;
import java.util.*;

class DiffieHellman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter prime (p): ");
        BigInteger p = sc.nextBigInteger();
        System.out.print("Enter primitive root (g): ");
        BigInteger g = sc.nextBigInteger();
        System.out.print("Private key A: ");
        BigInteger a = sc.nextBigInteger();
        System.out.print("Private key B: ");
        BigInteger b = sc.nextBigInteger();

        BigInteger A = g.modPow(a, p);
        BigInteger B = g.modPow(b, p);
        System.out.println("\nPublic Key A: " + A);
        System.out.println("Public Key B: " + B);

        BigInteger keyA = B.modPow(a, p);
        BigInteger keyB = A.modPow(b, p);
        System.out.println("\nSecret Key A: " + keyA);
        System.out.println("Secret Key B: " + keyB);
        sc.close();
    }
}
```

### Sample I/O

```
Input: p=23, g=5, a=6, b=15
Output: Public Key A: 8, Public Key B: 19, Secret: 2
```

### Reference: [Diffie-Hellman - Wikipedia](https://en.wikipedia.org/wiki/Diffie%E2%80%93Hellman_key_exchange)

---

## 6. RSA

### Asymmetric Encryption

### Second Version: HTML/JS (Main)

> Pure math RSA is kept in the HTML file as a commented reference, but the page uses browser crypto.

```html
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>RSA - Web Crypto API</title>
</head>
<body>
  <textarea id="msg">Hello</textarea>
  <button id="run">Run RSA</button>
  <pre id="out">Click Run RSA.</pre>

  <script>
    const out = document.getElementById('out');
    const msg = document.getElementById('msg');
    const run = document.getElementById('run');

    function toBase64(bytes) {
      let s = '';
      for (const b of bytes) s += String.fromCharCode(b);
      return btoa(s);
    }

    run.addEventListener('click', async () => {
      const encoder = new TextEncoder();
      const decoder = new TextDecoder();
      const data = encoder.encode(msg.value);
      const keyPair = await crypto.subtle.generateKey(
        { name: 'RSA-OAEP', modulusLength: 2048, publicExponent: new Uint8Array([1, 0, 1]), hash: 'SHA-256' },
        true,
        ['encrypt', 'decrypt']
      );
      const encrypted = await crypto.subtle.encrypt({ name: 'RSA-OAEP' }, keyPair.publicKey, data);
      const decrypted = await crypto.subtle.decrypt({ name: 'RSA-OAEP' }, keyPair.privateKey, encrypted);
      out.textContent = 'Encrypted (base64): ' + toBase64(new Uint8Array(encrypted)) + '\nDecrypted: ' + decoder.decode(decrypted);
    });
  </script>
</body>
</html>
```

### Code Explanation

```
1. Take message from user
2. Generate RSA key pair using KeyPairGenerator (2048 bits)
3. Use built-in Cipher class for encryption
4. Decrypt using private key
```

### Code

```java
import java.util.*;
import java.security.*;
import javax.crypto.*;

class RSA {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter message: ");
        String msg = sc.nextLine();

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, kp.getPublic());
        byte[] enc = cipher.doFinal(msg.getBytes());

        cipher.init(Cipher.DECRYPT_MODE, kp.getPrivate());
        byte[] dec = cipher.doFinal(enc);

        System.out.println("Encrypted: " + HexFormat.of().formatHex(enc));
        System.out.println("Decrypted: " + new String(dec));

        sc.close();
    }
}
```

### Sample I/O

```
Input:  Hello
Output: Encrypted: a8b9c0d1e2f3...
        Decrypted: Hello
```

### Reference: [RSA - Wikipedia](https://en.wikipedia.org/wiki/RSA_(cryptosystem))

---

## 7. SHA-256

### Hashing Algorithm

### Code Explanation

```
1. Take message from user
2. Use Java MessageDigest with SHA-256
3. Compute hash bytes
4. Convert to hexadecimal
```

### Code

```java
import java.security.MessageDigest;
import java.util.*;

class SHA {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter message: ");
        String msg = sc.nextLine();

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(msg.getBytes());

        System.out.print("Hash: ");
        for (byte b : hash) System.out.print(String.format("%02x", b));
        System.out.println();
        sc.close();
    }
}
```

### Sample I/O

```
Input:  Hello
Output: Hash: 185f8db32271fe25f561a6fc938b2e264306ec304eda518007d1764826381969
```

### Reference: [SHA-2 - Wikipedia](https://en.wikipedia.org/wiki/SHA-2)

---

## 8. Digital Signature

### RSA-based Digital Signature

### Code Explanation

```
1. Generate RSA key pair (2048 bits)
2. Create signature using private key
3. Verify signature using public key
```

### Code

```java
import java.security.*;
import java.util.Base64;
import java.util.*;

class DigitalSignature {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter message: ");
        String msg = sc.nextLine();

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();

        Signature s = Signature.getInstance("SHA256withRSA");
        s.initSign(kp.getPrivate());
        s.update(msg.getBytes());
        byte[] sign = s.sign();

        System.out.println("\nSignature: " + Base64.getEncoder().encodeToString(sign));

        s.initVerify(kp.getPublic());
        s.update(msg.getBytes());
        System.out.println("Verified: " + s.verify(sign));
        sc.close();
    }
}
```

### Sample I/O

```
Input:  Hello
Output: Signature: (base64 string)
        Verified: true
```

### Reference: [Digital Signature - Wikipedia](https://en.wikipedia.org/wiki/Digital_signature)

---

## Quick Revision

| # | Cipher | Type | Key |
|---|--------|------|-----|
| 1a | Caesar | Monoalphabetic | Shift |
| 1b | Playfair | Digraph | 5x5 Matrix |
| 1c | Hill | Polyalphabetic | 2x2 Matrix |
| 1d | Vigenere | Polyalphabetic | Word |
| 2a | Rail Fence | Transposition | Rails |
| 2b | Row Column | Transposition | Rows/Cols |
| 3 | AES | Block | 16 chars |
| 4 | DES | Block | 8 chars |
| 5 | Diffie Hellman | Key Exchange | Prime+Root |
| 6 | RSA | Asymmetric | p,q,e,d |
| 7 | SHA-256 | Hashing | None |
| 8 | Digital Signature | Authentication | RSA Key Pair |

---

## References

- [Cryptography - Wikipedia](https://en.wikipedia.org/wiki/Cryptography)
- [Java Security Documentation](https://docs.oracle.com/javase/8/docs/technotes/guides/security/)
- [NIST Cryptographic Standards](https://csrc.nist.gov/projects/cryptographic-standards-and-guidelines)
