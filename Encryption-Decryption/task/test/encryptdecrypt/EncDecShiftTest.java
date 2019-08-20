package encryptdecrypt;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class EncDecShiftTest {

    private static EncDecFactory encDecFactory;

    @BeforeClass
    public static void setUp() {
        encDecFactory = new EncDecFactory();
    }

    @Test
    public void weCanEncrypt() {
        EncDec encDec = encDecFactory.createEncDec("shift");
        assertThat(encDec.encrypt("welcome to hyperskill", 5), is("bjqhtrj yt mdujwxpnqq"));
    }

    @Test
    public void weCanDecrypt() {
        EncDec encDec = encDecFactory.createEncDec("shift");
        assertThat(encDec.decrypt("bjqhtrj yt mdujwxpnqq", 5), is("welcome to hyperskill"));
    }
}

