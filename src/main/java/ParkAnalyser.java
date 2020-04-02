import java.util.ArrayList;
import java.util.List;

public class ParkAnalyser {

    boolean unPark = false;

    List<Car>list = new ArrayList<>();
    int capacity = 5;
    private Car Car;

    public boolean parkCar() {
        return true;
    }

    boolean park = parkCar();

    public boolean unParkCar() {
        if ( park == true )
        {
            unPark = true;
        }
        return unPark;
    }


    public boolean checkFull() {
        if (list.size() != capacity){
            list.add(Car);
        }
        if (list.size() > capacity){
            return true;
        }
        throw new ParkingLotException("NOT ENOUGH SPACE",ParkingLotException.ExceptionType.SPACE_NOT_AVAILABLE);
    }
}
