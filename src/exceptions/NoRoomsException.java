package exceptions;

public class NoRoomsException extends Exception {

    public NoRoomsException() {}

    public NoRoomsException(String message) {
        super(message);
    }
}
