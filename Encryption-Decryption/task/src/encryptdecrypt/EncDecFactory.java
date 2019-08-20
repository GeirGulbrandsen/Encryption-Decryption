package encryptdecrypt;

public class EncDecFactory {

    EncDec createEncDec(String type) {
        if (type.equals("shift")) {
            return new EncDecShift();
        } else if (type.equals("unicode")) {
            return new EncDecUnicode();
        } else return null;
    }
}
