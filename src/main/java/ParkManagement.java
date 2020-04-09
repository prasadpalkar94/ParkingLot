import java.util.ArrayList;
import java.util.List;

public class ParkManagement {
    List<ParkAnalyser> parkAnalyserList;


    public ParkManagement() {
        this.parkAnalyserList = new ArrayList<>();
    }

    public void addLot(ParkAnalyser parkAnalyser){
         this.parkAnalyserList.add(parkAnalyser);
    }
}
