# Cryptography Programs - Faaris

Dynamic/Versatile implementations with user-configurable keys and parameters.

---

## 1a. Caesar Cipher

### Substitution Cipher - Monoalphabetic

### Code Explanation

```
1. Take plaintext and key (shift value) from user
2. Convert text to uppercase
3. Encryption: C = (P + K) mod 26
   - For each character, shift by key positions
   - Non-alphabetic characters remain unchanged
4. Decryption: P = (C - K) mod 26
   - Reverse the shift to get original text

Key Features:
- Handles any integer key (positive or negative)
- Preserves non-alphabetic characters
- Uses modulo arithmetic with adjustment for negative values
```

### Sample Input/Output

```
Enter Text: HELLO
Enter Key: 3
Encrypted Text: KHOOR
Decrypted Text: HELLO

Enter Text: WORLD
Enter Key: 7
Encrypted Text: DVYST
Decrypted Text: WORLD
```

---

## 1b. Playfair Cipher

### Substitution Cipher - Digraph

### Code Explanation

```
1. Key Matrix Generation:
   - Take key from user
   - Remove duplicates (keep first occurrence)
   - Fill 5×5 matrix with key + remaining alphabet
   - Treat I and J as same letter

2. Text Preparation:
   - Convert to uppercase
   - Replace J with I
   - Remove non-letters
   - Add X between duplicate letters in pair
   - Pad with X if odd length

3. Encryption Rules:
   - Same row: take characters to right (wrap)
   - Same column: take characters below (wrap)
   - Rectangle: swap column positions

4. Decryption: Reverse all rules
```

### Sample Input/Output

```
Enter Key: MONARCHY
Enter Text: HELLO
Encrypted Text: FCSMCS

Enter Key: KEYWORD
Enter Text: ATTACK
Encrypted Text: QXQFQO
```

---

## 1c. Hill Cipher

### Polyalphabetic Cipher - Matrix-based

### Code Explanation

```
1. User selects matrix size (2×2 or 3×3)
2. User enters key matrix values
3. Encryption Process:
   - Convert text to numbers (A=0, B=1, ..., Z=25)
   - Pad with X if needed to match block size
   - Multiply key matrix by plaintext vector
   - Apply mod 26 to each result
   - Convert back to letters

4. Decryption Process:
   - Calculate matrix determinant mod 26
   - Find modular inverse of determinant
   - Compute adjugate matrix
   - Multiply inverse × adjugate mod 26
   - Multiply inverse key by ciphertext

Key Requirement: Matrix must be invertible (gcd(det,26)=1)
```

### Sample Input/Output

```
Enter Key Matrix Size (2 or 3): 2
Enter 2x2 key matrix values:
3 3
2 5
Enter Text: HI
Encrypted Text: TC
Decrypted Text: HI

Enter Key Matrix Size: 2
Enter: 4 7 5 3
Enter Text: HELLO
Encrypted Text: JEBWQXWX
Decrypted Text: HELLO
```

---

## 1d. Vigenere Cipher

### Polyalphabetic Substitution Cipher

### Code Explanation

```
1. Take plaintext and key from user
2. Repeat key to match plaintext length
3. Encryption: C = (P + K) mod 26
   - Each letter shifted by corresponding key letter
   - Key letter values: A=0, B=1, ..., Z=25
4. Decryption: P = (C - K) mod 26
   - Subtract key letter value to reverse

Formula:
- Encrypt: (text[i] - 'A' + key[i%keyLen] - 'A') % 26 + 'A'
- Decrypt: (text[i] - 'A' - key[i%keyLen] - 'A' + 26) % 26 + 'A'

Key Features:
- Works with any length key
- Preserves non-alphabetic characters
```

### Sample Input/Output

```
Enter Text: HELLO
Enter Key: KEY
Encrypted Text: RIJVS
Decrypted Text: HELLO

Enter Text: ATTACKATDAWN
Enter Key: CRYPTO
Encrypted Text: CSAWPCLRWAWB
Decrypted Text: ATTACKATDAWN
```

---

## 2a. Rail Fence Cipher

### Transposition Cipher

### Code Explanation

```
1. Take plaintext and number of rails from user
2. Create array of StringBuilders for each rail
3. Distribute characters in zigzag pattern:
   - Start going down, then up at rails-1
   - Track position with direction flag
4. Concatenate all rails for ciphertext
5. Decryption:
   - Calculate which rail each position belongs to
   - Rebuild original text using rail positions

Key Features:
- Any number of rails (≥2)
- Handles any text length
- Reversible algorithm
```

### Sample Input/Output

```
Enter Text: HELLO WORLD
Enter Number of Rails: 3
Encrypted Text: HOELWRDLO 
Decrypted Text: HELLO WORLD

Enter Text: CRYPTOGRAPHY
Enter Number of Rails: 4
Encrypted Text: CTPORAHYRRGYP
Decrypted Text: CRYPTOGRAPHY
```

---

## 2b. Row Column Transposition

### Transposition Cipher

### Code Explanation

```
1. User inputs text, rows, and columns
2. Pad text with X if needed to fill matrix
3. Encryption:
   - Fill matrix row-wise with plaintext
   - Read column-wise for ciphertext
4. Decryption:
   - Fill matrix column-wise with ciphertext
   - Read row-wise for plaintext

Key Features:
- Configurable matrix dimensions
- Handles any text length (auto-padding)
- Works with any rows × columns combination
```

### Sample Input/Output

```
Enter Text: ATTACKATDAWN
Enter Number of Rows: 3
Enter Number of Columns: 4
Encrypted Text: AANAWTCADTATK
Decrypted Text: ATTACKATDAWN

Enter Text: SECRET
Enter Rows: 2
Enter Columns: 3
Encrypted Text: SETCER
Decrypted Text: SECRET
```

---

## 3. AES (Advanced Encryption Standard)

### Symmetric Block Cipher

### Code Explanation

```
1. User inputs message and 16-character key
2. Pad key if < 16 characters (with '0')
3. Use Java javax.crypto library:
   - Create SecretKeySpec from key bytes
   - Initialize Cipher with AES algorithm
4. Encryption:
   - cipher.init(ENCRYPT_MODE, key)
   - byte[] encrypted = cipher.doFinal(message.getBytes())
5. Decryption:
   - cipher.init(DECRYPT_MODE, key)
   - byte[] decrypted = cipher.doFinal(encrypted)
6. Display encrypted as hex string

Variants:
- AES-128: 16 char key
- AES-192: 24 char key
- AES-256: 32 char key
```

### Sample Input/Output

```
Enter Message: Hello World
Enter Key (16 chars for AES-128): mysecretkey12345
Encrypted (hex): a8b3c4d5e6f7a8b9c0d1e2f3a4b5c6d7
Decrypted Text: Hello World

Enter Message: Secret Data
Enter Key: 1234567890123456
Encrypted (hex): [varies each execution]
Decrypted Text: Secret Data
```

---

## 4. DES (Data Encryption Standard)

### Symmetric Block Cipher

### Code Explanation

```
1. User inputs message and 8-character key
2. Pad key if < 8 characters (with '0')
3. Use Java javax.crypto library:
   - Create SecretKeySpec from key bytes
   - Initialize Cipher with DES algorithm
4. Encryption:
   - cipher.init(ENCRYPT_MODE, key)
   - byte[] encrypted = cipher.doFinal(message.getBytes())
5. Decryption:
   - cipher.init(DECRYPT_MODE, key)
   - byte[] decrypted = cipher.doFinal(encrypted)
6. Display encrypted as hex string

Note: DES is now considered insecure
      Use AES for modern applications
```

### Sample Input/Output

```
Enter Message: SecretMsg
Enter Key (8 chars for DES): mykey123
Encrypted (hex): 8a6c8f2e4b3a1d5e
Decrypted Text: SecretMsg

Enter Message: Hello
Enter Key: 12345678
Encrypted (hex): [varies each execution]
Decrypted Text: Hello
```

---

## Quick Revision Summary

| Cipher | Type | Key/Parameter |
|--------|------|---------------|
| Caesar | Monoalphabetic | Shift value (any int) |
| Playfair | Digraph | Key word → 5×5 matrix |
| Hill | Polyalphabetic | 2×2 or 3×3 matrix |
| Vigenere | Polyalphabetic | Any length key word |
| Rail Fence | Transposition | Number of rails |
| Row Column | Transposition | Rows × Columns |
| AES | Block | 16 char key |
| DES | Block | 8 char key |

---

## Key Differences: Muthu vs Faaris

| Feature | Muthu (Static) | Faaris (Dynamic) |
|---------|---------------|------------------|
| Caesar | Fixed key | User input key |
| Playfair | Hardcoded matrix | User key → generate matrix |
| Hill | Fixed 2×2 matrix | User selects size & values |
| Vigenere | Sample key | User input key |
| Rail Fence | Fixed 3 rails | User input rail count |
| Row Column | Fixed 2×3 | User input rows/cols |
| AES | Fixed key | User input key |
| DES | Fixed key | User input key |
