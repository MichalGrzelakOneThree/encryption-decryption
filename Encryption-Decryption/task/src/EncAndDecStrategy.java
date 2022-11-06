public class EncAndDecStrategy {

    private EncodingDecoding method;

    public void setMethod(EncodingDecoding method) {
        this.method = method;
    }

    public char[] selectEncryptOrDecrypt(char[] stringToArray, int key, String mode) {
        return this.method.encryptOrDecrypt(stringToArray, key, mode);
    }
}
