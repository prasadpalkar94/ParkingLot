public class AirportSecurity implements ParkingLotInterface {
    private boolean parkEfficiency;
    @Override
    public void checkParking() {
        this.parkEfficiency = true;
    }

    @Override
    public boolean isFull() {
        return this.parkEfficiency;
    }
}
