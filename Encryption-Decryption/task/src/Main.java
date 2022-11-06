import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static char[] stringToArray;
    static String mode = "";
    static String finalMessage = "";
    static String messageFromData = "";
    static String messageFromIn = "";
    static String filePathToWrite = "";
    static String algorithm = "";
    static boolean isOutArgument = true;
    static boolean isData = false;
    static boolean isIn = false;
    static int key = 0;

    public static void main(String[] args) {

        setParameters(args);
        final EncodingDecoding alg = create(algorithm);
        EncAndDecStrategy ctx = new EncAndDecStrategy();
        ctx.setMethod(alg);
        char[] aa = ctx.selectEncryptOrDecrypt(stringToArray, key, mode);
        printOutput(aa);
    }

    private static void setParameters(String[] args) {

        for (int i = 0; i < args.length - 1; i++) {
            if ("-mode".equals(args[i])) {
                if ("-key-data-in-out-alg".contains(args[i + 1])) {
                    mode = "enc";
                } else {
                    mode = args[i + 1];
                }
            } else if ("-key".equals(args[i])) {
                if ("-mode-data-in-out-alg".contains(args[i + 1])) {
                    key = 0;
                } else {
                    key = Integer.parseInt(args[i + 1]);
                }
            } else if ("-data".equals(args[i])) {
                if ("-mode-key-in-out-alg".contains(args[i + 1])) {
                    messageFromData = "";
                } else {
                    messageFromData = args[i + 1];
                }
            } else if ("-in".equals(args[i])) {
                if ("-mode-key-data-out-alg".contains(args[i + 1])) {
                    messageFromIn = "";
                } else {
                    File file = new File(args[i + 1]);
                    try (Scanner scanner = new Scanner(file)) {
                        while (scanner.hasNext()) {
                            messageFromIn += scanner.nextLine();
                        }
                    } catch (IOException e) {
                        System.out.println("Error");
                    }
                }
            } else if ("-out".equals(args[i])) {
                if ("-mode-key-in-data-alg".contains(args[i + 1])) {
                    isOutArgument = false;
                } else {
                    filePathToWrite = args[i + 1];
                }
            } else if ("-alg".equals(args[i])) {
                if ("-mode-data-in-out-key".contains(args[i + 1])) {
                    algorithm = "shift";
                } else {
                    algorithm = args[i + 1];
                }
            }
        }

        for (int j = 0; j < args.length - 1; j++) {
            if ("-data".equals(args[j]) && !"-mode-key-in-out-alg".contains(args[j + 1])) {
                isData = true;
            }
            if ("-in".equals(args[j]) && !"-mode-key-data-out-alg".contains(args[j + 1])) {
                isIn = true;
            }
        }

        finalMessage = isIn && isData ? messageFromData : messageFromIn;
        stringToArray = finalMessage.toCharArray();
    }

    private static void printOutput(char[] aa) {

        if (!isOutArgument) {
            System.out.println(String.valueOf(aa));
        } else {
            File file = new File(filePathToWrite);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(aa);
            } catch (IOException e) {
                System.out.println("Error");
            }
        }
    }

    public static EncodingDecoding create(String algorithm) {
        switch (algorithm) {
            case "unicode": {
                return new UnicodeEncryption();
            }
            case "shift": {
                return new ShiftEncryption();
            }
            default: {
                throw new IllegalArgumentException("Unknown algorithm type " + algorithm);
            }
        }
    }
}
