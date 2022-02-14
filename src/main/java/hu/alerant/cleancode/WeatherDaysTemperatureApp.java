package hu.alerant.cleancode;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class WeatherDaysTemperatureApp {
    public static void main(String[] args) {

        String fileName = "datamunging/weather.dat";

        BigDecimal foundMinimum = BigDecimal.valueOf(Long.MAX_VALUE);
        BigDecimal foundMinimumRow = BigDecimal.ZERO;

        try {
            List<String> allLines = allLines = Files.readAllLines(Paths.get(ClassLoader.getSystemResource(fileName).toURI()));

            //skipping first two header rows and last summary row
            for (String line : allLines.subList(2, allLines.size() - 1)) {
                List<String> lineColumns = Arrays.asList(line.split("\\s+"));

                //get max and min columns but remove asterisks
                BigDecimal max = BigDecimal.valueOf(Long.parseLong(lineColumns.get(2).replaceAll("\\*", "")));
                BigDecimal min = BigDecimal.valueOf(Long.parseLong(lineColumns.get(3).replaceAll("\\*", "")));
                BigDecimal actualDiff = max.subtract(min);

                //compare to known minimum
                if (actualDiff.compareTo(foundMinimum) < 0) {
                    foundMinimum = actualDiff;
                    foundMinimumRow = BigDecimal.valueOf(Long.parseLong(lineColumns.get(1)));
                }
            }
            //results
            System.out.println("Found minimum diff:" + foundMinimum);
            System.out.println("Found minimum diff, row number:" + foundMinimumRow);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

}
