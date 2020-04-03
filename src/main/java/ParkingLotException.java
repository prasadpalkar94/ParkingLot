public class ParkingLotException extends RuntimeException {
    enum ExceptionType {
        SPACE_UNAVAILABLE
    }

    ExceptionType type;
    public ParkingLotException(String message, ExceptionType type) {
        super(message);
    }
}
