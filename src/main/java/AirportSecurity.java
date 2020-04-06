public class AirportSecurity implements ParkingLotInterface {
    public boolean parkEfficiency = false;
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
        this.parkEfficiency = false;
    }

}
