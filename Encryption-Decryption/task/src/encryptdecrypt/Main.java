package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String mode = "enc";
        String data = "";
        String inputFile = "";
        String outputFile = "";
        String algo = "";
        int key = 0;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode":
                    mode = args[++i];
                    break;
                case "-key":
                    key = Integer.parseInt(args[++i]);
                    break;
                case "-data":
                    data = args[++i];
                    break;
                case "-in":
                    inputFile = args[++i];
                    break;
                case "-alg":
                    algo = args[++i];
                    break;
                case "-out":
                    outputFile = args[++i];
                    break;
                default:
                    break;
            }
        }

        if (data.isEmpty()) {
            GetInput getInput = new GetInput(data, inputFile, key).invoke();
            data = getInput.getData();
            key = getInput.getKey();
        }

        outputCipherText(outputFile, processInput(mode, data, key, new EncDecFactory().createEncDec(algo)));
    }

    private static String processInput(String mode, String data, int key, EncDec cryptographer) {
        String output = "Could not create cryptographer";
        if (cryptographer != null) {
            switch (mode) {
                case "enc":
                    output = cryptographer.encrypt(data, key);
                    break;
                case "dec":
                    output = cryptographer.decrypt(data, key);
                    break;
                default:
                    System.out.println("Bad request");
                    break;
            }
        }
        return output;
    }

    private static void outputCipherText(String outputFile, String output) {
        if (outputFile.isEmpty()) {
            System.out.println(output);
        } else {
            try (FileWriter writer = new FileWriter(new File(outputFile))) {
                writer.write(output);
            } catch (IOException e) {
                System.out.printf("Could not open file %s", outputFile);
            }
        }
    }

    private static class GetInput {
        private String data;
        private String inputFile;
        private int key;

        GetInput(String data, String inputFile, int key) {
            this.data = data;
            this.inputFile = inputFile;
            this.key = key;
        }

        private static String getInputFromFile(String data, String inputFile) {
            try {
                data = new String(Files.readAllBytes(Paths.get(inputFile)));
            } catch (IOException e) {
                System.out.printf("Unable to read file %s", inputFile);
            }
            return data;
        }

        String getData() {
            return data;
        }

        int getKey() {
            return key;
        }

        GetInput invoke() {
            if (inputFile.isEmpty()) {
                Scanner scanner = new Scanner(System.in);
                data = scanner.nextLine();
                key = scanner.nextInt();
            } else {
                data = getInputFromFile(data, inputFile);
            }
            return this;
        }
    }
}
