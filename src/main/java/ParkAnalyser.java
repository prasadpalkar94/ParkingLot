import java.util.ArrayList;
import java.util.List;

public class ParkAnalyser {

    private int efficiency ;
    private Object car;
    private ParkingLotOwner parkSubscriber;
    private AirportSecurity airportSecurity;
    private List<ParkingLotInterface> parkList;
    private List cars;

    public ParkAnalyser(int efficiency) {
        this.efficiency = efficiency;
        parkSubscriber = new ParkingLotOwner();
        airportSecurity = new AirportSecurity();
        this.parkList = new ArrayList<>();
        this.cars = new ArrayList();
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }

    public void subscribeParkingLotInterface(ParkingLotInterface parkLot) {
        this.parkList.add(parkLot);
    }

    public boolean parkCar(Object car)
    {
        if (this.cars.size() == efficiency)
            throw new ParkingLotException("NOT ENOUGH SPACE", ParkingLotException.ExceptionType.SPACE_UNAVAILABLE);
        this.car = car;
        parkSubscriber.checkParking();
        checkFull();
        return true;
    }

    public void checkFull(){
        for (ParkingLotInterface parkLot: parkList) {
            parkLot.isFull();
        }
    }

    public boolean unParkCar(Object car) {
        if ( this.car.equals(car)){
            this.car = null;
            return true;
        }
        if ( this.car == null ){
            parkSubscriber.lotAvailable();
            checkAvailable();
        }
        throw new ParkingLotException("Car Not Found",ParkingLotException.ExceptionType.CAR_NOT_FOUND);
    }

    public void checkAvailable(){
        for (ParkingLotInterface parkLot: parkList) {
            parkLot.isLotAvailable();
        }
    }

    public void subscribeOwner(ParkingLotOwner parkSubscriber) {
        this.parkSubscriber = parkSubscriber;
    }

}
