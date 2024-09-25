package exceptions;

public class DuplicatedRoomException extends Exception {

    public DuplicatedRoomException() {}

    public DuplicatedRoomException(String message) {
        super(message);
    }
}
