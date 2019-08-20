package encryptdecrypt;

public class EncDecUnicode extends EncDec {
    @Override
    String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder();
        for (char ch : input.toCharArray()) {
            encrypted.append(ch += key);
        }
        return encrypted.toString();

    }

    @Override
    String decrypt(String input, int key) {
        StringBuilder decrypted = new StringBuilder();
        for (char ch : input.toCharArray()) {
            decrypted.append(ch -= key);
        }
        return decrypted.toString();
    }
}

