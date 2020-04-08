import java.time.LocalDateTime;
import java.util.Objects;

public class ParkingSlot {
    public Object car;
    private LocalDateTime parkedTime;
    private int slotNo;



    public ParkingSlot(Object car) {
        this.car = car;
    }

    public LocalDateTime getParkedTime(){
        return parkedTime;
    }
    public void setParkedTime(Object car) {
        this.car = car;
        this.parkedTime = LocalDateTime.now();
    }

    public Object getCar(){
        return car;
    }

    public int getSlotNo() {
        return slotNo;
    }
}
