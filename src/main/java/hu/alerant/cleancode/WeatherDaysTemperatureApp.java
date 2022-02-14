package hu.alerant.cleancode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class WeatherDaysTemperatureApp {
    private final String fileName;

    public WeatherDaysTemperatureApp(String fileName) {
        this.fileName = fileName;
    }

    public static void main(String[] args) {
        String fileName = "datamunging/weather.dat";

        WeatherDaysTemperatureApp app = new WeatherDaysTemperatureApp(fileName);
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
            List<String> allLines = Files.readAllLines(Paths.get(ClassLoader.getSystemResource(fileName).toURI()));

            //skipping first two header rows and last summary row
            for (String line : allLines.subList(2, allLines.size() - 1)) {
                List<String> lineColumns = Arrays.asList(line.split("\\s+"));

                //get max and min columns but remove asterisks
                long max = Long.parseLong(lineColumns.get(2).replaceAll("\\*", ""));
                long min = Long.parseLong(lineColumns.get(3).replaceAll("\\*", ""));
                long actualDiff = max - min;

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
}
