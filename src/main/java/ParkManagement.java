import enums.Driver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ParkManagement {
    List<ParkAnalyser> parkAnalyserList;
    private Cars car;

    public ParkManagement() {
        this.parkAnalyserList = new ArrayList<>();
    }

    public void addLot(ParkAnalyser parkAnalyser){
         this.parkAnalyserList.add(parkAnalyser);
    }

    public boolean parkCar(Cars car, Enum type) {
        ParkAnalyser parkAnalyser = getFreeSpaceForParkingLot();
        boolean parkedCar = parkAnalyser.parkCar(car);
        return parkedCar;
    }

    public boolean unParkCar(Cars car) {
        for (ParkAnalyser parkAnalyser : this.parkAnalyserList) {
            return parkAnalyser.unParkCar(car);
        }
        throw new ParkingLotException("Car Is Not Present @ Location", ParkingLotException.ExceptionType.CAR_NOT_FOUND);
    }

    public ParkAnalyser getFreeSpaceForParkingLot() {
        return parkAnalyserList.stream().sorted(Comparator.comparing(parkList -> parkList.getEmptySlot().size(),
                Comparator.reverseOrder())).collect(Collectors.toList()).get(0);
    }

    public int findCar(Cars car) {
        for (ParkAnalyser parkAnalyser : this.parkAnalyserList)
            return parkAnalyser.findCar(car);
        throw new ParkingLotException("Car Is Not Present @ Location", ParkingLotException.ExceptionType.CAR_NOT_FOUND);
    }

    public boolean isCarPark(Cars car) {
        for (int slot = 0; slot < this.parkAnalyserList.size(); slot++) {
            if (this.parkAnalyserList.get(slot).isCarParked(car)) {
                return true;
            }
        }
        return false;
    }

    public List findCarByType(String color) {
        List<ArrayList> parkingLotsList = new ArrayList<>();
        for (ParkAnalyser list : this.parkAnalyserList) {
            ArrayList<Integer> fieldList = list.findCarOnType(color);
            parkingLotsList.add(fieldList);
        }
        return parkingLotsList;
    }

    public List findBlueCarByPlateNo(String name,String plateNo) {
        List<ArrayList> parkingLotsList = new ArrayList<>();
        for (ParkAnalyser list : this.parkAnalyserList) {
            ArrayList<Integer> fieldList = list.findBlueCarOnPlateNo(name,plateNo);
            parkingLotsList.add(fieldList);
        }
        return parkingLotsList;
    }


}
