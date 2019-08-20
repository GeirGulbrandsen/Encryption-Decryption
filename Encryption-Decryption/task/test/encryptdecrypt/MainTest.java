package encryptdecrypt;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MainTest {

    @Test
    public void mainTestEncShift() {

        final String outputFile = "protected.txt";

        Main.main(new String[]{
                "-alg", "shift",
                "-mode", "enc",
                "-key", "5",
                "-data", "welcome to hyperskill",
                "-out", outputFile
        });

        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(outputFile)));
        } catch (IOException e) {
            System.out.printf("Unable to read file %s", outputFile);
        }

        assertThat(text, is("bjqhtrj yt mdujwxpnqq"));
    }

    @Test
    public void mainTestDecShift() {

        final String outputFile = "protected.txt";

        Main.main(new String[]{
                "-alg", "shift",
                "-mode", "dec",
                "-key", "5",
                "-data", "bjqhtrj yt mdujwxpnqq",
                "-out", outputFile
        });

        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(outputFile)));
        } catch (IOException e) {
            System.out.printf("Unable to read file %s", outputFile);
        }

        assertThat(text, is("welcome to hyperskill"));
    }    @Test
    public void mainTestEncUnicode() {

        final String outputFile = "protected.txt";

        Main.main(new String[]{
                "-alg", "unicode",
                "-mode", "enc",
                "-key", "5",
                "-data", "Welcome to hyperskill!",
                "-out", outputFile
        });

        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(outputFile)));
        } catch (IOException e) {
            System.out.printf("Unable to read file %s", outputFile);
        }

        assertThat(text, is("\\jqhtrj%yt%m~ujwxpnqq&"));
    }

    @Test
    public void mainTestDecUnicode() {

        final String outputFile = "protected.txt";

        Main.main(new String[]{
                "-alg", "unicode",
                "-mode", "dec",
                "-key", "5",
                "-data", "\\jqhtrj%yt%m~ujwxpnqq&",
                "-out", outputFile
        });

        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(outputFile)));
        } catch (IOException e) {
            System.out.printf("Unable to read file %s", outputFile);
        }

        assertThat(text, is("Welcome to hyperskill!"));
    }

    @Test
    public void mainTestUnknownAlgo() {

        final String outputFile = "protected.txt";

        Main.main(new String[]{
                "-alg", "myAlgo",
                "-mode", "dec",
                "-key", "5",
                "-data", "\\jqhtrj%yt%m~ujwxpnqq&",
                "-out", outputFile
        });

        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(outputFile)));
        } catch (IOException e) {
            System.out.printf("Unable to read file %s", outputFile);
        }

        assertThat(text, is("Could not create cryptographer"));
    }
}