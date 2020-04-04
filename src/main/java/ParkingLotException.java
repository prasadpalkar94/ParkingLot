public class ParkingLotException extends RuntimeException {
    enum ExceptionType {
        SPACE_UNAVAILABLE,CAR_NOT_FOUND
    }

    ExceptionType type;
    public ParkingLotException(String message, ExceptionType type) {
        super(message);
    }
}
