package hu.alerant.cleancode;

import java.util.Arrays;
import java.util.List;

public class WeatherDataLine {
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


    public static WeatherDataLine convert(String rawDataLine) {
        List<String> lineColumns = Arrays.asList(rawDataLine.split("\\s+"));
        WeatherDataLine weatherDataLine = new WeatherDataLine();
        weatherDataLine.setDay((int) Long.parseLong(lineColumns.get(1)));
        weatherDataLine.setMaxTemp(Long.valueOf(lineColumns.get(2).replaceAll("\\*", "")));
        weatherDataLine.setMinTemp(Long.valueOf(lineColumns.get(3).replaceAll("\\*", "")));
        return  weatherDataLine;
    }

    @Override
    public String toString() {
        return "WeatherDataLine{" +
                "day=" + day +
                ", maxTemp=" + maxTemp +
                ", minTemp=" + minTemp +
                '}';
    }
}
