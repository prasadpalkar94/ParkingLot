import enums.Driver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ParkManagement {
    List<ParkAnalyser> parkAnalyserList;
    private Object car;

    public ParkManagement() {
        this.parkAnalyserList = new ArrayList<>();
    }

    public void addLot(ParkAnalyser parkAnalyser){
         this.parkAnalyserList.add(parkAnalyser);
    }

    public boolean parkCar(Object car, Enum type) {
        ParkAnalyser parkAnalyser = getFreeSpaceForParkingLot();
        boolean parkedCar = parkAnalyser.parkCar(car);
        return parkedCar;
    }

    public boolean unParkCar(Object car) {
        for (ParkAnalyser parkAnalyser : this.parkAnalyserList) {
            return parkAnalyser.unParkCar(car);
        }
        throw new ParkingLotException("Car Is Not Present @ Location", ParkingLotException.ExceptionType.CAR_NOT_FOUND);
    }

    public ParkAnalyser getFreeSpaceForParkingLot() {
        return parkAnalyserList.stream().sorted(Comparator.comparing(parkList -> parkList.getEmptySlot().size(),
                Comparator.reverseOrder())).collect(Collectors.toList()).get(0);
    }

    public int findCar(Object car) {
        for (ParkAnalyser parkAnalyser : this.parkAnalyserList)
            return parkAnalyser.findCar(car);
        throw new ParkingLotException("Car Is Not Present @ Location", ParkingLotException.ExceptionType.CAR_NOT_FOUND);
    }

    public boolean isCarPark(Object car) {
        for (int slot = 0; slot < this.parkAnalyserList.size(); slot++) {
            if (this.parkAnalyserList.get(slot).isCarParked(car)) {
                return true;
            }
        }
        return false;
    }

}
