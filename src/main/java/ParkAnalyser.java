import java.util.ArrayList;
import java.util.List;

public class ParkAnalyser {

    private final int efficiency ;
    private int presentEfficiency = 0;
    private Object car;
    private ParkingLotOwner parkSubscriber;
    ParkingLotOwner parkOwner = new ParkingLotOwner();

    public ParkAnalyser(int efficiency) {
        this.efficiency = efficiency;
    }

    public boolean parkCar(Object car)
    {
        if (this.efficiency == presentEfficiency)
            throw new ParkingLotException("NOT ENOUGH SPACE", ParkingLotException.ExceptionType.SPACE_UNAVAILABLE);
        this.car = car;
        parkSubscriber.checkParking();
        presentEfficiency ++;
        return true;
    }
    

    public boolean unParkCar(Object car) {
        if ( this.car.equals(car)){
            return true;
        }
        return false;
    }

    public void subscribeOwner(ParkingLotOwner parkSubscriber) {
        this.parkSubscriber = parkSubscriber;
    }

}
