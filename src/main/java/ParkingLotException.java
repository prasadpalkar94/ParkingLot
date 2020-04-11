public class ParkingLotException extends RuntimeException {
    enum ExceptionType {
        SPACE_UNAVAILABLE,CAR_NOT_FOUND,CAR_PARKED_ALREADY
    }

    ExceptionType type;
    public ParkingLotException(String message, ExceptionType type) {
        super(message);
    }
}
