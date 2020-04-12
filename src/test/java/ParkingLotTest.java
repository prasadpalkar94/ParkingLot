import enums.CarSize;
import enums.Driver;
import enums.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotTest {
    Cars car ;
    ParkingLotOwner owner;
    AirportSecurity airportSecurity;
    ParkingLotInformer parkingLotInformer;
    ParkAnalyser parkAnalyser;
    ParkManagement parkManagement;

    @Before
    public void setUp() throws Exception {
        car = new Cars();
        parkAnalyser = new ParkAnalyser();
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
            parkAnalyser.parkCar(new Cars());
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
            parkAnalyser.parkCar(new Cars());
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
        Cars bmw = new Cars();
        Cars benz = new Cars();
        Cars buggati = new Cars();
        try{
        boolean check1 = parkManagement.parkCar(car, Driver.NORMAL);
        boolean check2 = parkManagement.parkCar(bmw,Driver.NORMAL);
        boolean check3 = parkManagement.parkCar(benz,Driver.NORMAL);
        boolean check4 = parkManagement.parkCar(buggati,Driver.NORMAL);
        boolean check5 = parkManagement.parkCar(new Cars(),Driver.NORMAL);
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
        Cars bmw = new Cars();
        Cars audi = new Cars();
        Cars benz = new Cars();
        Cars skoda = new Cars();
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
        Cars bmw = new Cars();
        Cars audi = new Cars();
        Cars benz = new Cars();
        Cars skoda = new Cars();
        try {
            parkManagement.parkCar(bmw, CarSize.SMALL);
            parkManagement.parkCar(skoda, CarSize.LARGE);
            parkManagement.parkCar(audi, CarSize.SMALL);
            parkManagement.parkCar(benz, CarSize.SMALL);
            boolean park = parkManagement.isCarPark(skoda);
            Assert.assertEquals(true, park);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkConditions_ToFindAllWhiteCars_ShouldReturnSlotList() {
        ParkAnalyser parkAnalyser = new ParkAnalyser(15);
        parkAnalyser.initialiseLot();
        ParkAnalyser parkAnalyser1 = new ParkAnalyser(15);
        parkAnalyser1.initialiseLot();
        parkManagement.addLot(parkAnalyser);
        parkManagement.addLot(parkAnalyser1);
        Cars bmw = new Cars("red", Location.ANDHERI);
        Cars audi = new Cars("white",Location.PANVEL);
        Cars benz = new Cars("white",Location.ANDHERI);
        Cars skoda = new Cars("black",Location.VASHI);
        Cars honda = new Cars("white",Location.PANVEL);
        try {
            parkManagement.parkCar(bmw, Driver.NORMAL);
            parkManagement.parkCar(skoda, Driver.HANDICAP);
            parkManagement.parkCar(audi, Driver.NORMAL);
            parkManagement.parkCar(benz, Driver.HANDICAP);
            parkManagement.parkCar(honda, Driver.NORMAL);
            List<Integer> whiteCarList = parkManagement.findCarByType("white");
            System.out.println(whiteCarList.toString());
            List<Integer> output= new ArrayList();
            output.add(1);
            output.add(2);
            output.add(4);
            Assert.assertEquals(output,whiteCarList.get(1));
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void checkConditions_ToFindAllBlueToyotaCars_ShouldReturnSlotList() {
        ParkAnalyser parkAnalyser = new ParkAnalyser(15);
        parkAnalyser.initialiseLot();
        ParkAnalyser parkAnalyser1 = new ParkAnalyser(15);
        parkAnalyser1.initialiseLot();
        parkManagement.addLot(parkAnalyser);
        parkManagement.addLot(parkAnalyser1);
        Cars bmw = new Cars("blue", "Toyota","MH-13-BO1945");
        Cars audi = new Cars("white","Swift","MH-14-BR1887");
        Cars benz = new Cars("blue","Toyota","MH-13-AD1845");
        Cars skoda = new Cars("black","Benz","MH-13-AD1748");
        Cars honda = new Cars("white","Audi","MH-13-AD1987");
        try {
            parkManagement.parkCar(bmw, Driver.NORMAL);
            parkManagement.parkCar(skoda, Driver.HANDICAP);
            parkManagement.parkCar(audi, Driver.NORMAL);
            parkManagement.parkCar(benz, Driver.HANDICAP);
            parkManagement.parkCar(honda, Driver.NORMAL);
            List<String> blueToyotaCarList = parkManagement.findBlueCarByPlateNo("Toyota","MH-13-BO1945","blue");
            List<String> output= new ArrayList();
            output.add("0 Toyota MH-13-BO1945");
            output.add("2 Toyota MH-13-AD1845");
            Assert.assertEquals(output,blueToyotaCarList.get(0));
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void checkCondition_ToFindBMWCars_ShouldReturnList() {
        ParkAnalyser parkAnalyser = new ParkAnalyser(15);
        parkAnalyser.initialiseLot();
        ParkAnalyser parkAnalyser1 = new ParkAnalyser(15);
        parkAnalyser1.initialiseLot();
        parkManagement.addLot(parkAnalyser);
        parkManagement.addLot(parkAnalyser1);
        Cars bmw = new Cars("bmw");
        Cars audi = new Cars("skoda");
        Cars benz = new Cars("bmw");
        Cars skoda = new Cars("audi");
        Cars honda = new Cars("honda");
        try {
            parkManagement.parkCar(bmw, Driver.NORMAL);
            parkManagement.parkCar(skoda, Driver.HANDICAP);
            parkManagement.parkCar(audi, Driver.NORMAL);
            parkManagement.parkCar(benz, Driver.HANDICAP);
            parkManagement.parkCar(honda, Driver.NORMAL);
            List<Integer> CarList = parkManagement.findCarByType("white");
            System.out.println(CarList.toString());
            List<Integer>  output= new ArrayList();
            output.add(0);
            output.add(2);
            Assert.assertEquals(output,CarList.get(0));
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void checkCondition_ForCarsParkedBefore30mins_ShouldReturnList() {
        ParkAnalyser parkAnalyser = new ParkAnalyser(15);
        parkAnalyser.initialiseLot();
        ParkAnalyser parkAnalyser1 = new ParkAnalyser(15);
        parkAnalyser1.initialiseLot();
        parkManagement.addLot(parkAnalyser);
        parkManagement.addLot(parkAnalyser1);
        Cars bmw = new Cars("bmw");
        Cars audi = new Cars("skoda");
        Cars benz = new Cars("bmw");
        Cars skoda = new Cars("audi");
        Cars honda = new Cars("honda");
        try {
            parkManagement.parkCar(bmw, Driver.NORMAL);
            parkManagement.parkCar(skoda, Driver.HANDICAP);
            parkManagement.parkCar(audi, Driver.NORMAL);
            parkManagement.parkCar(benz, Driver.HANDICAP);
            parkManagement.parkCar(honda, Driver.NORMAL);
            List<String> CarList = parkAnalyser.getCarsWhichParkedBefore30Min();
            System.out.println(CarList.toString());
            List<String>  output= new ArrayList();
            output.add("0 Toyota MH-13-BO1945");
            output.add("2 Toyota MH-13-BO1945");
            Assert.assertEquals(output,CarList);
        } catch (ParkingLotException e) {
        }
    }

    //String color, Location location, String plateNo, String name, CarSize size
    @Test
    public void checkCondition_ToFindAllSmallHandicapCars_ShouldReturnList() {
        ParkAnalyser parkAnalyser = new ParkAnalyser(15);
        parkAnalyser.initialiseLot();
        ParkAnalyser parkAnalyser1 = new ParkAnalyser(15);
        parkAnalyser1.initialiseLot();
        parkManagement.addLot(parkAnalyser);
        parkManagement.addLot(parkAnalyser1);
        Cars bmw = new Cars("blue",Location.PANVEL,"MH-13-BO1945","BMW",CarSize.SMALL,Driver.HANDICAP);
        Cars audi = new Cars("white",Location.ANDHERI,"MH-13-AD1845","AUDI",CarSize.LARGE,Driver.NORMAL);
        Cars benz = new Cars("blue",Location.VASHI,"MH-13-BO1845","BENZ",CarSize.SMALL,Driver.HANDICAP);
        Cars skoda = new Cars("green",Location.PANVEL,"MH-13-AD1745","SKODA",CarSize.LARGE,Driver.NORMAL);
        Cars honda = new Cars("black",Location.ANDHERI,"MH-13-BO1745","HONDA",CarSize.SMALL,Driver.HANDICAP);
        try {
            parkManagement.parkCar(bmw);
            parkManagement.parkCar(skoda);
            parkManagement.parkCar(audi);
            parkManagement.parkCar(benz);
            parkManagement.parkCar(honda);
            List<String> CarList = parkAnalyser.getSmallCarsAndHandicapDriversDetails(Driver.HANDICAP,CarSize.SMALL);
            System.out.println(CarList.toString());
            List<String>  output= new ArrayList();
            output.add("2 blue Location.VASHI MH-13-BO1845 BENZ CarSize.SMALL Driver.HANDICAP");
            output.add("4 black Location.ANDHERI MH-13-BO1745 HONDA CarSize.SMALL Driver.HANDICAP");
            Assert.assertEquals(output,CarList);
        } catch (ParkingLotException e) {
        }
    }
}







