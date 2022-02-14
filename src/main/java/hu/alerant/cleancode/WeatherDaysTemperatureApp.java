package hu.alerant.cleancode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class WeatherDaysTemperatureApp {
    private final String dataFileName;

    public WeatherDaysTemperatureApp(String dataFileName) {
        this.dataFileName = dataFileName;
    }

    public static void main(String[] args) {
        String dataFileName = "datamunging/weather.dat";

        WeatherDaysTemperatureApp app = new WeatherDaysTemperatureApp(dataFileName);
        long minimumTemperatureDiffRow = app.findMinimumTemperatureDiffRow();

        System.out.println("Found minimum diff, row number:" + minimumTemperatureDiffRow);
    }

    /**
     * Find minimum difference between daily Max and Min temperatures
     * @return row number - business row number, not file row
     */
    public long findMinimumTemperatureDiffRow() {
        long foundMinimum = Long.MAX_VALUE;
        long foundMinimumRow = -1;

        try {
            List<String> allLines = Files.readAllLines(Paths.get(ClassLoader.getSystemResource(dataFileName).toURI()));

            //skipping first two header rows and last summary row
            for (String line : allLines.subList(2, allLines.size() - 1)) {
                List<String> lineColumns = Arrays.asList(line.split("\\s+"));

                long actualDiff = getMaxTemperature(lineColumns) - getMinTemperature(lineColumns);

                //compare to known minimum
                if (actualDiff < foundMinimum) {
                    foundMinimum = actualDiff;
                    foundMinimumRow = Long.parseLong(lineColumns.get(1));
                }
            }

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        //results
        System.out.println("Found minimum diff:" + foundMinimum);
        return foundMinimumRow;
    }

    private long getMinTemperature(List<String> lineColumns) {
        return Long.parseLong(lineColumns.get(3).replaceAll("\\*", ""));
    }

    private long getMaxTemperature(List<String> lineColumns) {
        return Long.parseLong(lineColumns.get(2).replaceAll("\\*", ""));
    }
}
