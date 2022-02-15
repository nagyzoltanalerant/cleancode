package hu.alerant.cleancode.common.weather;

import hu.alerant.cleancode.common.DataLine;
import hu.alerant.cleancode.common.DiffContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WeatherDiffContext implements DiffContext {

    private final String dataFileName = "datamunging/weather.dat";
    public final int HEADER_LINES = 2;

    @Override
    public String getDataFileName() {
        return dataFileName;
    }

    @Override
    public int getSkipHeaders() {
        return HEADER_LINES;
    }

    @Override
    public DataLine getInitDataLine() {
        return new WeatherDataLine(0,0l,0l);
    }

    @Override
    public List<DataLine> parseDataFile(Stream<String> allLines) {
        return  allLines
                .skip(getSkipHeaders())
                .filter(WeatherDiffContext::filterInvalidLine)
                .map(WeatherDiffContext::convert)
                .collect(Collectors.toList());
    }

    public static DataLine convert(String rawDataLine) {
        List<String> lineColumns = Arrays.asList(rawDataLine.split("\\s+"));

        int day = (int) Long.parseLong(lineColumns.get(1));
        long maxTemp = Long.valueOf(lineColumns.get(2).replaceAll("\\*", ""));
        long minTemp = Long.valueOf(lineColumns.get(3).replaceAll("\\*", ""));

        return new WeatherDataLine(day,maxTemp, minTemp);
    }
    public static boolean filterInvalidLine(String line) {
        return  !line.contains("mo");
    }
}
