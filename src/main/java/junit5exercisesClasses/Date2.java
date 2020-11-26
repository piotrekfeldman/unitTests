package junit5exercisesClasses;


import java.time.LocalTime;
import java.time.ZoneId;

public class Date2 {

    public static void main(String[] args){

        System.out.println(ZoneId.getAvailableZoneIds());


        ZoneId tunis = ZoneId.of("Africa/Tunis");
        LocalTime lt = LocalTime.now(tunis);
        System.out.println(lt);
    }
}
