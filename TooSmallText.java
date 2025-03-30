public class TooSmallText extends Exception {
    public TooSmallText(String text) {
        super("TooSmallText: " + text);
    }
}