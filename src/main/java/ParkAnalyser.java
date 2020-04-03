import java.util.ArrayList;
import java.util.List;

public class ParkAnalyser {
    private final int efficiency ;
    private int presentEfficiency = 0;
    private Object car;

    public ParkAnalyser(int efficiency) {
        this.efficiency = efficiency;
    }

    public boolean parkCar(Object car)
    {
        if (this.efficiency == presentEfficiency)
            throw new ParkingLotException("NOT ENOUGH SPACE", ParkingLotException.ExceptionType.SPACE_UNAVAILABLE);
        this.car = car;
        presentEfficiency ++;
        return true;
    }




    public boolean unParkCar(Object car) {
        if ( this.car.equals(car)){
            return true;
        }
        return false;
    }


}
