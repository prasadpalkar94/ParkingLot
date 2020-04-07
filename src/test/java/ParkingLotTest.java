import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    Object car ;
    ParkingLotOwner owner;
    AirportSecurity airportSecurity;
    ParkingLotInformer parkingLotInformer;

    @Before
    public void setUp() throws Exception {
        car = new Object();
        owner = new ParkingLotOwner();
        airportSecurity = new AirportSecurity();
        parkingLotInformer = new ParkingLotInformer();
    }

    @Test
    public void checkConditionFor_ParkTheCar_ShouldReturnTrue() {
        ParkAnalyser parkAnalyser = new ParkAnalyser(1);
            boolean check = parkAnalyser.parkCar(car);
            Assert.assertEquals(true, check);

    }

    @Test
    public void checkConditionFor_UnParkTheCar_ShouldReturnTrue() {
        ParkAnalyser parkAnalyser = new ParkAnalyser(1);
        parkAnalyser.parkCar(car);
        boolean check = parkAnalyser.unParkCar(car);
        Assert.assertEquals(true,check);
    }

    @Test
    public void  checkCondition_whetherParkingLotIsFull_SoThatPutOutSign() {
        try {
            ParkAnalyser parkAnalyser = new ParkAnalyser(1);
            parkAnalyser.parkCar(car);
            parkAnalyser.parkCar(car);
            Assert.assertEquals(true, parkingLotInformer.owner.isFull());
        } catch (ParkingLotException e) {
            Assert.assertEquals("NOT ENOUGH SPACE", e.getMessage());
        }
    }



    @Test
    public void checkCondition_whetherParkingLotIsFull_ShouldRedirectAirportSecurity() {
        ParkAnalyser parkAnalyser = new ParkAnalyser(2);
        try {
            parkAnalyser.parkCar(car);
            parkAnalyser.parkCar(new Object());
            parkAnalyser.parkCar(car);
        } catch (ParkingLotException e) {
            Assert.assertEquals(true,parkAnalyser.parkingLotInformer.owner.isFull());
        }
    }

    @Test
    public void checkCondition_WhetherParkingLotHasSpaceAvailable() {
        ParkAnalyser parkAnalyser = new ParkAnalyser(2);
        try {
            parkAnalyser.parkCar(car);
            parkAnalyser.parkCar(new Object());
        } catch (ParkingLotException e) {
            Assert.assertEquals(true,parkAnalyser.parkingLotInformer.security.isFull());
        }
    }

    @Test
    public void checkConditionFor_ParkingAttendantToParkCar_ShouldReturnTrue() {
            ParkAnalyser parkAnalyser = new ParkAnalyser(2);
            parkAnalyser.parkCar(car,1);
            int check = parkAnalyser.parkCar(car,2);
            Assert.assertEquals(2,check);
    }




    @Test
    public void checkConditionFor_FindCar_ShouldReturnSlotNo() {
        try {
            ParkAnalyser parkAnalyser = new ParkAnalyser(1);
            int check = parkAnalyser.parkCar(car, 5);
            Assert.assertEquals(5, check);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Ur Car Is NOT Available", ParkingLotException.ExceptionType.CAR_NOT_FOUND);
        }

    }
        @Test
        public void checkCondition_To_FindCar_ShouldReturnTrue() {
            ParkAnalyser parkAnalyser = new ParkAnalyser(2);
            parkAnalyser.parkCar(car,2);
            owner.setSlot(2);
            Assert.assertEquals(parkAnalyser.slotNo,owner.slotNo);
        }
    }







