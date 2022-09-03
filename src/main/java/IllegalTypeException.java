public class IllegalTypeException extends RuntimeException {
    private static final long serialVersionUID = -4665193269635474750L;

    public IllegalTypeException() {
        super();
    }
    public IllegalTypeException(String s) {
        super(s);
    }

    public IllegalTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalTypeException(Throwable cause) {
        super(cause);
    }
}
