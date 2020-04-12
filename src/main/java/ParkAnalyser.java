import enums.Driver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class ParkAnalyser {

    private int efficiency;
    private int presentEfficiency;
    private Cars car;
    private ParkingSlot parkingSlot;
    private List<ParkingSlot> parkSlots;
    public int slotNo = 0;
    ParkingLotInformer parkingLotInformer;

    public ParkAnalyser() {
    }

    public ParkAnalyser(int efficiency) {
        this.efficiency = efficiency;
        this.parkSlots = new ArrayList<>();
        this.parkingLotInformer = new ParkingLotInformer();

    }


    public int initialiseLot() {
        IntStream.range(0,this.efficiency).forEach(value -> this.parkSlots.add(new ParkingSlot(value)));
        return parkSlots.size();
    }

    public boolean parkCar(Cars car)
    {
        if (presentEfficiency == efficiency)
        {
            parkingLotInformer.checkForFullParkingLot();
            throw new ParkingLotException("NOT ENOUGH SPACE", ParkingLotException.ExceptionType.SPACE_UNAVAILABLE);
        }
        if (isCarParked(car)) {
            throw new ParkingLotException("Car Is Already Parked", ParkingLotException.ExceptionType.CAR_PARKED_ALREADY);
        }
        this.car = car;
        presentEfficiency++;
        return true;
    }

    public int parkCar(Cars car,int slotNo) {
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


    public boolean unParkCar(Cars car) {
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

    public boolean isCarParked(Cars car) {
        parkingSlot = new ParkingSlot(car);
        return this.parkSlots.contains(parkingSlot);
    }

    public int findCar(Cars car) {
        if (isCarParked(car))
            return this.parkSlots.indexOf(parkingSlot);
        throw new ParkingLotException("Car Is Not Present At Location", ParkingLotException.ExceptionType.CAR_NOT_FOUND);
    }

    public ArrayList<Integer> findCarOnType(String color) {
        ArrayList<Integer> whiteCarList = new ArrayList<>();
        for (int slot = 0; slot < this.parkSlots.size(); slot++) {
            if ((this.parkSlots.get(slot) != null)) {
                if (this.parkSlots.get(slot).car.getColor().equals(color)) {
                    whiteCarList.add(slot);
                }
            }
        }
        return whiteCarList;
    }

    public List<String> findBlueCarOnPlateNo(String name, String plateNo,String color) {
        List<String> blueToyotaList = new ArrayList<>();
        blueToyotaList = this.parkSlots.stream()
                .filter(parkingSlot -> parkingSlot.getCar() != null)
                .filter(parkingSlot -> parkingSlot.getCar().getName().equals(name))
                .filter(parkingSlot -> parkingSlot.getCar().getColor().equals(color))
                .map(parkingSlot -> (parkingSlot.getName())+"  "+(parkingSlot.getSlotNo())+"  "+(parkingSlot.getCar().getPlateNo()))
                .collect(Collectors.toList());
        return blueToyotaList;
    }

    public ArrayList<Integer> findCarOnName(String name) {
        ArrayList<Integer> bmwList = new ArrayList<>();
        for (int slot = 0; slot < this.parkSlots.size(); slot++) {
            if ((this.parkSlots.get(slot) != null)) {
                if (this.parkSlots.get(slot).car.getName().equals(name)) {
                    bmwList.add(slot);
                }
            }
        }
        return bmwList;
    }

    public List<String> getCarsWhichParkedBefore30Min() {
        List<String> befor30MinParkedCarsList = new ArrayList<>();
        befor30MinParkedCarsList = this.parkSlots.stream()
                .filter(parkingSlot -> parkingSlot.getCar() != null)
                .filter(parkingSlot -> parkingSlot.getParkedTime().getMinute()- LocalDateTime.now().getMinute() <=30)
                .map(parkingSlot -> ((parkingSlot.getSlotNo()))+" "+(parkingSlot.getCar().getName())+" "+(parkingSlot.getCar().getPlateNo()))
                .collect(Collectors.toList());
        return befor30MinParkedCarsList;

    }


}
