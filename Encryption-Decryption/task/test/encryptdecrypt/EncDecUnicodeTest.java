package encryptdecrypt;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertThat;


public class EncDecUnicodeTest {

    private static EncDecFactory encDecFactory;

    @BeforeClass
    public static void setUp() {
        encDecFactory = new EncDecFactory();
    }

    @Test
    public void weCanEncrypt() {
        EncDec encDec = encDecFactory.createEncDec("unicode");
        assertThat("\\jqhtrj%yt%m~ujwxpnqq&",
                CoreMatchers.is(encDec.encrypt("Welcome to hyperskill!", 5)));
    }

    @Test
    public void weCanDecrypt() {
        EncDec encDec = encDecFactory.createEncDec("unicode");
        assertThat("Welcome to hyperskill!",
                CoreMatchers.is(encDec.decrypt("\\jqhtrj%yt%m~ujwxpnqq&", 5)));
    }
}
