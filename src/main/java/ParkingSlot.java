import java.time.LocalDateTime;
import java.util.Objects;

public class ParkingSlot {
    public Cars car;
    private LocalDateTime parkedTime;
    private int slotNo;



    private String name;


    public ParkingSlot(int slotNo){ this.slotNo = slotNo; }

    public ParkingSlot(Cars car) {
        this.car = car;
    }

    public LocalDateTime getParkedTime(){
        return parkedTime;
    }
    public void setParkedTime(Cars car) {
        this.car = car;
        this.parkedTime = LocalDateTime.now();
    }

    public Cars getCar(){
        return car;
    }

    public int getSlotNo() {
        return slotNo;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "car=" + car +
                ", parkedTime=" + parkedTime +
                ", slotNo=" + slotNo +
                ", name='" + name + '\'' +
                '}';
    }
}
