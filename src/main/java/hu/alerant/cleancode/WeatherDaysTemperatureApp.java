package hu.alerant.cleancode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WeatherDaysTemperatureApp {
    private final String dataFileName;

    public WeatherDaysTemperatureApp(String dataFileName) {
        this.dataFileName = dataFileName;
    }

    public static void main(String[] args) {
        String dataFileName = "datamunging/weather.dat";

        WeatherDaysTemperatureApp app = new WeatherDaysTemperatureApp(dataFileName);
        List<String> lines = app.readDataLines();
        String minimumTemperatureDiffRow = app.findMinimumTemperatureDiffRow(lines);

        System.out.println("Found minimum diff, in this row:" +"\n"+ minimumTemperatureDiffRow);
    }


    private List<String> readDataLines() {
        List<String> dataLines = new ArrayList<>();
        try {
            List<String> allLines = Files.readAllLines(Paths.get(ClassLoader.getSystemResource(dataFileName).toURI()));

            //skipping first two header rows and last summary row
            dataLines.addAll(allLines.subList(2, allLines.size() - 1));

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return dataLines;
    }

    public String findMinimumTemperatureDiffRow(List<String> lines) {
        String minimumTemperatureDiffRow = "";
        long foundMinTemp = Long.MAX_VALUE;

        for (String line : lines) {
            List<String> lineColumns = Arrays.asList(line.split("\\s+"));

            long actualDiffTemp = getMaxTemp(lineColumns) - getMinTemp(lineColumns);

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

    private long getMinTemp(List<String> lineColumns) {
        return Long.parseLong(lineColumns.get(3).replaceAll("\\*", ""));
    }

    private long getMaxTemp(List<String> lineColumns) {
        return Long.parseLong(lineColumns.get(2).replaceAll("\\*", ""));
    }
}
