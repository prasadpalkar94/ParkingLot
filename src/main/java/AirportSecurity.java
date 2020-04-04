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

    @Override
    public void lotAvailable() {
        this.parkEfficiency = true;
    }

    @Override
    public boolean isLotAvailable() {
        return this.parkEfficiency;
    }
}
