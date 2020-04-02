import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    ParkAnalyser parkAnalyser;

    @Before
    public void setUp() throws Exception {
        parkAnalyser = new ParkAnalyser();
    }

    @Test
    public void checkConditionFor_ParkTheCar_ShouldReturnTrue() {
        boolean check = parkAnalyser.parkCar();
        Assert.assertEquals(true,check);
    }

    @Test
    public void checkConditionFor_UnParkTheCar_ShouldReturnTrue() {
        boolean check = parkAnalyser.unParkCar();
        Assert.assertEquals(true,check);
    }

    @Test
    public void checkCondition_whetherParkingLotIsFull_SoThatPutOutSign() {
       try {
           boolean check = parkAnalyser.checkFull();
           Assert.assertEquals(true, check);
       }catch (ParkingLotException e){
           e.printStackTrace();
       }
    }
}
