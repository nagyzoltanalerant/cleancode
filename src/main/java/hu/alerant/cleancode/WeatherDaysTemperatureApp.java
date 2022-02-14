package hu.alerant.cleancode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class WeatherDaysTemperatureApp {
    public static void main(String[] args) {

        String fileName = "datamunging/weather.dat";

        long foundMinimum = Long.MAX_VALUE;
        long foundMinimumRow = -1;

        try {
            List<String> allLines = allLines = Files.readAllLines(Paths.get(ClassLoader.getSystemResource(fileName).toURI()));

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

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        //results
        System.out.println("Found minimum diff:" + foundMinimum);
        System.out.println("Found minimum diff, row number:" + foundMinimumRow);
    }

}
