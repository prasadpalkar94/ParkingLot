import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    ParkAnalyser parkAnalyser;
    Object car ;
    ParkingLotOwner owner;
    AirportSecurity airportSecurity;

    @Before
    public void setUp() throws Exception {
        parkAnalyser = new ParkAnalyser(2);
        car = new Object();
        owner = new ParkingLotOwner();
        airportSecurity = new AirportSecurity();
    }

    @Test
    public void checkConditionFor_ParkTheCar_ShouldReturnTrue() {
            boolean check = parkAnalyser.parkCar(car);
            Assert.assertEquals(true, check);

    }

    @Test
    public void checkConditionFor_UnParkTheCar_ShouldReturnTrue() {
        parkAnalyser.parkCar(car);
        boolean check = parkAnalyser.unParkCar(car);
        Assert.assertEquals(true,check);
    }

    @Test
    public void  checkCondition_whetherParkingLotIsFull_SoThatPutOutSign() {
        try {
            parkAnalyser.parkCar(car);
            parkAnalyser.parkCar(car);
        } catch (ParkingLotException e) {
            Assert.assertEquals("NOT ENOUGH SPACE", e.getMessage());
        }
    }

    @Test
    public void checkCondition_whetherParkingLotIsFull_ShouldReturnTrue() {
        parkAnalyser.subscribeOwner(owner);
        try {
              parkAnalyser.parkCar(car);
              parkAnalyser.parkCar(new Object());
            } catch (ParkingLotException e) {
            Assert.assertEquals(true,owner.isFull());
        }

     }

    @Test
    public void checkCondition_whetherParkingLotIsFull_ShouldRedirectAirportSecurity() {

        parkAnalyser.subscribeParkingLotInterface(airportSecurity);
        try {
            parkAnalyser.parkCar(car);
            parkAnalyser.parkCar(new Object());
            parkAnalyser.parkCar(car);
        } catch (ParkingLotException e) {
            Assert.assertEquals(true,airportSecurity.isFull());
        }
    }

    @Test
    public void checkCondition_WhetherParkingLotHasSpaceAvailable() {
        parkAnalyser.subscribeOwner(owner);
        try {
            parkAnalyser.parkCar(car);
            parkAnalyser.parkCar(new Object());
        } catch (ParkingLotException e) {
            Assert.assertEquals(true,owner.isLotAvailable());
        }
    }
}


