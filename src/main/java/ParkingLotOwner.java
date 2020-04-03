public class ParkingLotOwner {
    private boolean parkEfficiency;

    public void checkParking() {
        this.parkEfficiency = true;
    }

    public boolean isFull() {
        return this.parkEfficiency;
    }
}
