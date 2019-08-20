package encryptdecrypt;

public class EncDecShift extends EncDec {

    String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (ch >= 97 && ch <= 122) {
                ch += key;
                if (ch > 122) {
                    ch -= 26;
                }
            }
            encrypted.append(ch);
        }
        return encrypted.toString();
    }

    String decrypt(String input, int key) {
        StringBuilder decrypted = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (ch >= 97 && ch <= 122) {
                ch -= key;
                if (ch < 97) {
                    ch += 26;
                }
            }
            decrypted.append(ch);
        }
        return decrypted.toString();
    }
}
