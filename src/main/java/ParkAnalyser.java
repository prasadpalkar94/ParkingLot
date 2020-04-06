import java.util.ArrayList;
import java.util.List;

public class ParkAnalyser {

    private int efficiency;
    private int presentEfficiency;
    private Object car;
    private List cars;
    ParkingLotInformer parkingLotInformer;

    public ParkAnalyser(int efficiency) {
        this.efficiency = efficiency;
        this.cars = new ArrayList();
        this.parkingLotInformer = new ParkingLotInformer();
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

}
