package ius.smartlab.Helpers;

/**
 * Created by Amila on 03/08/2017.
 */

public class FutureSettings {

    public String temperature;
    public String light;
    public String doors;
    public String date;
    public String time;

    public FutureSettings() {
    }

    public FutureSettings(String temperature, String light, String doors, String phone, String date, String time) {
        this.temperature = temperature;
        this.light = light;
        this.doors = doors;
        this.date = date;
        this.time = time;
    }
}
