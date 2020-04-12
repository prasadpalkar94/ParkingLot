import enums.Location;

public class Cars {
    private String color;
    private Location location;

    public Cars() {
    }

    public Cars(String color, Location location) {
        this.color = color;
        this.location = location;
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

}
