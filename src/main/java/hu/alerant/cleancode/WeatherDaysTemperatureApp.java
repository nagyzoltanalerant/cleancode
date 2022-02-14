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

        WeatherDaysTemperatureApp app = new WeatherDaysTemperatureApp();

        String fileName = "datamunging/weather.dat";
        BigDecimal foundMinimum = BigDecimal.valueOf(Long.MAX_VALUE);
        BigDecimal foundMinimumRow = BigDecimal.ZERO;

        try {
            List<String> allLines = allLines = Files.readAllLines(Paths.get(ClassLoader.getSystemResource(fileName).toURI()));

            for (String line : allLines.subList(2, allLines.size() - 1)) {
                List<String> lineColumns = Arrays.asList(line.split("\\s+"));

                BigDecimal max = BigDecimal.valueOf(Long.parseLong(lineColumns.get(2).replaceAll("\\*", "")));
                BigDecimal min = BigDecimal.valueOf(Long.parseLong(lineColumns.get(3).replaceAll("\\*", "")));
                BigDecimal actualDiff = max.subtract(min);

                if (actualDiff.compareTo(foundMinimum) < 0) {
                    foundMinimum = actualDiff;
                    foundMinimumRow = BigDecimal.valueOf(Long.parseLong(lineColumns.get(1)));
                }
            }
            System.out.println("Found minimum diff:" + foundMinimum);
            System.out.println("Found minimum diff, row number:" + foundMinimumRow);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

}
