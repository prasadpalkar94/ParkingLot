public class ParkingLotException extends RuntimeException {
    enum ExceptionType {
        SPACE_NOT_AVAILABLE
    }

    ExceptionType type;
    public ParkingLotException(String message, ExceptionType type) {
        super(message);
    }
}
