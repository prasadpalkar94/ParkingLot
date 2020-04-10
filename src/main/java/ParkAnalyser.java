import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ParkAnalyser {

    private int efficiency;
    private int presentEfficiency;
    private Object car;
    private ParkingSlot parkingSlot;
    private List<ParkingSlot> parkSlots;
    public int slotNo = 0;
    ParkingLotInformer parkingLotInformer;

    public ParkAnalyser(int efficiency) {
        this.efficiency = efficiency;
        this.parkSlots = new ArrayList<>();
        this.parkingLotInformer = new ParkingLotInformer();
        setEfficiency(efficiency);
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
        initialiseLot();
    }

    public int initialiseLot() {
        IntStream.range(0,this.efficiency).forEach(value -> this.parkSlots.add(new ParkingSlot(value)));
        return parkSlots.size();
    }

    public boolean parkCar(Object car)
    {
        if (presentEfficiency == efficiency)
        {
            parkingLotInformer.checkForFullParkingLot();
            throw new ParkingLotException("NOT ENOUGH SPACE", ParkingLotException.ExceptionType.SPACE_UNAVAILABLE);
        }
        this.car = car;
        presentEfficiency++;
        return true;
    }

    public int parkCar(Object car,int slotNo) {
        this.slotNo=slotNo;
        if (this.car == car || this.slotNo == slotNo ){
            return slotNo;
        }
        if (slotNo == parkingLotInformer.owner.slotNo){
            return slotNo;
        }
        throw new ParkingLotException("Ur Car Is NOT Available",ParkingLotException.ExceptionType.CAR_NOT_FOUND);
    }

    public ArrayList<Integer> getEmptySlot() {
        ArrayList<Integer> emptySlots = new ArrayList();
        IntStream.range(0, this.efficiency).filter(slot -> parkSlots.get(slot) == null).forEach(slot -> emptySlots.add(slot));
        return emptySlots;
    }


    public boolean unParkCar(Object car) {
        if ( car.equals(this.car)){
            this.car = null;
            return true;
        }
        if ( this.car == null ){
            parkingLotInformer.checkForAvailableParkingLot();
            return false;
        }
        parkingLotInformer.checkForAvailableParkingLot();
        throw new ParkingLotException("Car Not Found",ParkingLotException.ExceptionType.CAR_NOT_FOUND);
    }

    public boolean isCarParked(Object car) {
        parkingSlot = new ParkingSlot(car);
        return this.parkSlots.contains(parkingSlot);
    }

    public int findCar(Object car) {
        if (isCarParked(car))
            return this.parkSlots.indexOf(parkingSlot);
        throw new ParkingLotException("Car Is Not Present At Location", ParkingLotException.ExceptionType.CAR_NOT_FOUND);
    }
}
