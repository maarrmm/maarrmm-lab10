public class InvalidStopwordException extends Exception {
    public InvalidStopwordException(String text) {
        super("InvalidStopwordException: " + text);
    }
}