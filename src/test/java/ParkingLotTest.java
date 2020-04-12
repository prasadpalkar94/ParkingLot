import enums.Cars;
import enums.Driver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class ParkingLotTest {
    Object car ;
    ParkingLotOwner owner;
    AirportSecurity airportSecurity;
    ParkingLotInformer parkingLotInformer;
    ParkManagement parkManagement;

    @Before
    public void setUp() throws Exception {
        car = new Object();
        owner = new ParkingLotOwner();
        airportSecurity = new AirportSecurity();
        parkingLotInformer = new ParkingLotInformer();
        parkManagement = new ParkManagement();
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

    @Test
    public void checkCondition_whenCarWasParked_ShouldReturnTime() {
        ParkingSlot slot = new ParkingSlot(car);
        slot.setParkedTime(car);
        Assert.assertEquals(LocalDateTime.now().getMinute(), slot.getParkedTime().getMinute());
    }

    @Test
    public void checkCondition_ForEvenlyCarPark_ShouldReturnTrue() {
        ParkAnalyser parkAnalyser1 = new ParkAnalyser(5);
        parkAnalyser1.initialiseLot();
        ParkAnalyser parkAnalyser2 = new ParkAnalyser(5);
        parkAnalyser2.initialiseLot();
        parkManagement.addLot(parkAnalyser1);
        parkManagement.addLot(parkAnalyser2);
        Object bmw = new Object();
        Object benz = new Object();
        Object buggati = new Object();
        try{
        boolean check1 = parkManagement.parkCar(car, Driver.NORMAL);
        boolean check2 = parkManagement.parkCar(bmw,Driver.NORMAL);
        boolean check3 = parkManagement.parkCar(benz,Driver.NORMAL);
        boolean check4 = parkManagement.parkCar(buggati,Driver.NORMAL);
        boolean check5 = parkManagement.parkCar(new Object(),Driver.NORMAL);
        Assert.assertTrue( check1 && check2 && check3 && check4 && check5);
    }catch (ParkingLotException e){
        e.printStackTrace();
        }
    }

    @Test
    public void checkConditionFor_HandiCapDriver_ShouldParkNearestSpaceAvailable(){
        ParkAnalyser parkAnalyser = new ParkAnalyser(5);
        parkAnalyser.initialiseLot();
        ParkAnalyser parkAnalyser1 = new ParkAnalyser(5);
        parkAnalyser1.initialiseLot();
        parkManagement.addLot(parkAnalyser);
        parkManagement.addLot(parkAnalyser1);
        Object bmw = new Object();
        Object audi = new Object();
        Object benz = new Object();
        Object skoda = new Object();
        try {
            parkManagement.parkCar(bmw, Driver.NORMAL);
            parkManagement.parkCar(skoda, Driver.HANDICAP);
            parkManagement.unParkCar(bmw);
            parkManagement.parkCar(audi, Driver.NORMAL);
            parkManagement.parkCar(benz, Driver.HANDICAP);
            int position = parkManagement.findCar(benz);
            Assert.assertEquals(1, position);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkConditionsFor_LargeCarsToPark_AtMaximumEnoughSpace_ShouldReturnTrue() {
        ParkAnalyser parkAnalyser = new ParkAnalyser(15);
        parkAnalyser.initialiseLot();
        ParkAnalyser parkAnalyser1 = new ParkAnalyser(10);
        parkAnalyser1.initialiseLot();
        parkManagement.addLot(parkAnalyser);
        parkManagement.addLot(parkAnalyser1);
        Object bmw = new Object();
        Object audi = new Object();
        Object benz = new Object();
        Object skoda = new Object();
        try {
            parkManagement.parkCar(bmw, Cars.SMALL);
            parkManagement.parkCar(skoda, Cars.LARGE);
            parkManagement.parkCar(audi,Cars.SMALL);
            parkManagement.parkCar(benz, Cars.SMALL);
            boolean park = parkManagement.isCarPark(skoda);
            Assert.assertEquals(true, park);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}







