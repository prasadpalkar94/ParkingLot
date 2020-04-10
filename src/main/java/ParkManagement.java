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

    public boolean parkCar(Object car, Driver type) {
        ParkAnalyser parkAnalyser = getFreeSpaceForParkingLot();
        boolean parkedCar = parkAnalyser.parkCar(car);
        return parkedCar;
    }

    public ParkAnalyser getFreeSpaceForParkingLot() {
        return parkAnalyserList.stream().sorted(Comparator.comparing(parkList -> parkList.getEmptySlot().size(),
                Comparator.reverseOrder())).collect(Collectors.toList()).get(0);
    }


}
