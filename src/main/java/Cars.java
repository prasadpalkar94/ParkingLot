import enums.Location;

public class Cars {
    private String color;
    private Location location;
    private String plateNo;
    private String name;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }
}
