public class ParkAnalyser {

    boolean unPark = false;

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
}
