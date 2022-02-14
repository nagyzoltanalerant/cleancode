package hu.alerant.cleancode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WeatherDaysTemperatureApp {
    private final String dataFileName;

    public WeatherDaysTemperatureApp(String dataFileName) {
        this.dataFileName = dataFileName;
    }

    public static void main(String[] args) {
        String dataFileName = "datamunging/weather.dat";

        WeatherDaysTemperatureApp app = new WeatherDaysTemperatureApp(dataFileName);
        List<WeatherDataLine> lines = app.readDataLines();
        WeatherDataLine minimumTemperatureDiffRow = app.findMinimumTemperatureDiffRow(lines);

        System.out.println("Found minimum diff, in this row:" +"\n"+ minimumTemperatureDiffRow.toString());
    }


    public List<WeatherDataLine> readDataLines() {
        List<WeatherDataLine> dataLines = new ArrayList<>();
        try {
            List<String> allLines = Files.readAllLines(Paths.get(ClassLoader.getSystemResource(dataFileName).toURI()));

            //skipping first two header rows and last summary row
            dataLines =
                    allLines.subList(2, allLines.size() - 1)
                            .stream()
                            .map(WeatherDataLine::convert)
                            .collect(Collectors.toList());

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return dataLines;
    }

    public WeatherDataLine findMinimumTemperatureDiffRow(List<WeatherDataLine> lines) {
        WeatherDataLine minimumTemperatureDiffRow = new WeatherDataLine();
        long foundMinTemp = Long.MAX_VALUE;

        for (WeatherDataLine line : lines) {
            long actualDiffTemp = line.getMaxTemp() - line.getMinTemp();

            //compare to known minimum
            if (actualDiffTemp < foundMinTemp) {
                foundMinTemp = actualDiffTemp;
                minimumTemperatureDiffRow = line;
            }
        }

        //results
        System.out.println("Found minimum diff:" + foundMinTemp);
        return minimumTemperatureDiffRow;
    }

}
