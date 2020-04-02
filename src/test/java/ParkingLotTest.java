import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {
    @Test
    public void checkConditionFor_ParkTheCar_ShouldReturnTrue() {
        ParkAnalyser parkAnalyser = new ParkAnalyser();
        boolean check = parkAnalyser.parkCar();
        Assert.assertEquals(true,check);
    }
}
