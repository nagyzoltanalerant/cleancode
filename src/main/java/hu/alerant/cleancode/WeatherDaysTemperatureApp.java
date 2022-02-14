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

        System.out.println("Found minimum diff, day number:" + minimumTemperatureDiffRow);
    }

    /**
     * Find minimum difference between daily Max and Min temperatures
     * @return row number - business row number, not file row
     */
    public long findMinimumTemperatureDiffRow() {
        long foundMinTemp = Long.MAX_VALUE;
        long foundMinTempDay = -1;

        try {
            List<String> allLines = Files.readAllLines(Paths.get(ClassLoader.getSystemResource(dataFileName).toURI()));

            //skipping first two header rows and last summary row
            for (String line : allLines.subList(2, allLines.size() - 1)) {
                List<String> lineColumns = Arrays.asList(line.split("\\s+"));

                long actualDiffTemp = getMaxTemp(lineColumns) - getMinTemp(lineColumns);

                //compare to known minimum
                if (actualDiffTemp < foundMinTemp) {
                    foundMinTemp = actualDiffTemp;
                    foundMinTempDay = Long.parseLong(lineColumns.get(1));
                }
            }

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        //results
        System.out.println("Found minimum diff:" + foundMinTemp);
        return foundMinTempDay;
    }

    private long getMinTemp(List<String> lineColumns) {
        return Long.parseLong(lineColumns.get(3).replaceAll("\\*", ""));
    }

    private long getMaxTemp(List<String> lineColumns) {
        return Long.parseLong(lineColumns.get(2).replaceAll("\\*", ""));
    }
}
