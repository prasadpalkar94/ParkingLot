import enums.CarSize;
import enums.Driver;
import enums.Location;

public class Cars {
    private String color;
    private Location location;
    private String plateNo;
    private String name;
    private CarSize size;
    private Driver type;

    public Cars() {
    }

    public Cars(String color, Location location) {
        this.color = color;
        this.location = location;
    }

    public Cars(String color, String name, String plateNo) {
        this.color = color;
        this.name = name;
        this.plateNo = plateNo;
    }

    public Cars(String name){
        this.name = name;
    }

    public Cars(String color, Location location, String plateNo, String name, CarSize size, Driver type) {
        this.color = color;
        this.location = location;
        this.plateNo = plateNo;
        this.name = name;
        this.size = size;
        this.type = type;
    }

    public String getColor() {
        return color;
    }


    public Location getLocation() {
        return location;
    }


    public String getPlateNo() {
        return plateNo;
    }

    public String getName() {
        return name;
    }

    public CarSize getSize() {
        return size;
    }

    public Driver getType() {
        return type;
    }
}
