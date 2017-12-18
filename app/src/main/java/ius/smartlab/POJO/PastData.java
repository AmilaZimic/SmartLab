package ius.smartlab.POJO;

/**
 * Created by Džana on 08/12/2017.
 */

public class PastData {
    private Double temperature;
    private Integer light;
    private Integer doors;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public PastData(Double temperature, Integer light, Integer doors, String time) {
        this.temperature = temperature;
        this.light = light;
        this.doors = doors;
        this.time = time;
    }

    public PastData() {
    }

    public PastData(Double temperature, Integer light, Integer doors) {
        this.temperature = temperature;
        this.light = light;
        this.doors = doors;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getLight() {
        return light;
    }

    public void setLight(Integer light) {
        this.light = light;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }
}
