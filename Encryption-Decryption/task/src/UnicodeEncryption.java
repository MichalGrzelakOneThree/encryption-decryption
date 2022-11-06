class UnicodeEncryption implements EncodingDecoding {

    @Override
    public char[] encryptOrDecrypt(char[] stringToArray, int key, String mode) {

        if ("dec".equals(mode)) {
            for (int i = 0; i < stringToArray.length; i++) {
                stringToArray[i] = (char) (stringToArray[i] - key);
            }
        } else {
            for (int i = 0; i < stringToArray.length; i++) {
                stringToArray[i] = (char) (stringToArray[i] + key);
            }
        }

        return stringToArray;
    }
}
