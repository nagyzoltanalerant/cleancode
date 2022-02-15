package hu.alerant.cleancode.common.weather;

import hu.alerant.cleancode.common.DataLine;

import java.util.Arrays;
import java.util.List;

public class WeatherDataLine  implements DataLine {

    private int day;
    private Long maxTemp;
    private Long minTemp;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Long getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Long maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Long getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Long minTemp) {
        this.minTemp = minTemp;
    }

    public WeatherDataLine(int day, Long maxTemp, Long minTemp) {
        this.day = day;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    @Override
    public int getDiff() {
        return (int) (getMaxTemp()-getMinTemp());
    }

    @Override
    public String getResult() {
        return String.valueOf(this.day);
    }
}
