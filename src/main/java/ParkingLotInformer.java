import java.util.ArrayList;
import java.util.List;

public class ParkingLotInformer {
    List<ParkingLotInterface> parkList;
    ParkingLotOwner owner = new ParkingLotOwner();
    AirportSecurity security = new AirportSecurity();

    public ParkingLotInformer(){
        this.parkList = new ArrayList<>();
        parkList.add(owner);
        parkList.add(security);
    }



    public void checkForFullParkingLot(){
        for (ParkingLotInterface parkLot: parkList) {
            parkLot.checkParking();
        }
    }

    public void checkForAvailableParkingLot(){
        for (ParkingLotInterface parkLot: parkList) {
            parkLot.lotAvailable();
        }
    }

}
