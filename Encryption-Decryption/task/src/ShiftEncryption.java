public class ShiftEncryption implements EncodingDecoding {

    @Override
    public char[] encryptOrDecrypt(char[] stringToArray, int key, String mode) {

        if ("dec".equals(mode)) {
            for (int i = 0; i < stringToArray.length; i++) {
                if (stringToArray[i] == 32) {
                    continue;
                }
                if (Character.toString(stringToArray[i]).matches("[a-zA-Z]")) {
                    stringToArray[i] = (char) (stringToArray[i] - key);
                    if (stringToArray[i] < 97) {
                        stringToArray[i] = (char) (122 + (stringToArray[i] - 97) + 1);
                    } else if (stringToArray[i] < 65) {
                        stringToArray[i] = (char) (90 + (stringToArray[i] - 65) + 1);
                    }
                }
            }
        } else {
            for (int i = 0; i < stringToArray.length; i++) {
                if (stringToArray[i] == 32) {
                    continue;
                }
                if (Character.toString(stringToArray[i]).matches("[a-zA-Z]")) {
                    stringToArray[i] = (char) (stringToArray[i] + key);
                    if (stringToArray[i] > 122) {
                        stringToArray[i] = (char) (97 + (stringToArray[i] - 122) - 1);
                    } else if (Character.toString(stringToArray[i]).matches("[A-Z]") && stringToArray[i] > 90) {
                        stringToArray[i] = (char) (65 + (stringToArray[i] - 90) - 1);
                    }
                }
            }
        }

        return stringToArray;
    }
}

