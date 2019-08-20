package encryptdecrypt;

abstract class EncDec {
    abstract String encrypt(String input, int key);

    abstract String decrypt(String input, int key);
}
