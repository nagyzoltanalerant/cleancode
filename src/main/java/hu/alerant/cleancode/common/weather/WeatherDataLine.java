package hu.alerant.cleancode.common.weather;

import hu.alerant.cleancode.common.DataLine;

import java.util.Arrays;
import java.util.List;

public class WeatherDataLine  implements DataLine {

    private int day;
    private Long maxTemp;
    private Long minTemp;

    public WeatherDataLine(int day, Long maxTemp, Long minTemp) {
        this.day = day;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    @Override
    public int getDiff() {
        return (int) (this.maxTemp-this.minTemp);
    }

    @Override
    public String getResult() {
        return String.valueOf(this.day);
    }
}
