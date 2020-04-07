public class ParkingLotOwner implements ParkingLotInterface {
    public boolean parkEfficiency = false;
    public int slotNo;

    public void setSlot(int slotNo){
        this.slotNo = slotNo;
    }
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


}
