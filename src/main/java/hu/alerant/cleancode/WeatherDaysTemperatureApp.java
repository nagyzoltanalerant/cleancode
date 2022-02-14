package hu.alerant.cleancode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.ClassLoader.getSystemResource;

public class WeatherDaysTemperatureApp {
    public static final int HEADER_LINES = 2;
    private final String dataFileName;

    public WeatherDaysTemperatureApp(String dataFileName) {
        this.dataFileName = dataFileName;
    }

    public static void main(String[] args) {
        String dataFileName = "datamunging/weather.dat";
        WeatherDaysTemperatureApp app = new WeatherDaysTemperatureApp(dataFileName);
        List<WeatherDataLine> weatherDataLines = app.readWeatherDataLines();
        WeatherDataLine minimumTemperatureDiffRow = app.findMinimumTemperatureDiffRow(weatherDataLines);

        System.out.println("Found minimum diff, in this row:" +"\n"+ minimumTemperatureDiffRow.toString());
    }


    public List<WeatherDataLine> readWeatherDataLines() {
        List<WeatherDataLine> weatherDataLines = new ArrayList<>();
        try (var allLines = Files.lines(Paths.get(getSystemResource(dataFileName).toURI()))) {

            weatherDataLines = allLines
                                .skip(HEADER_LINES)
                                .filter(WeatherDaysTemperatureApp::filterInvalidLine)
                                .map(WeatherDataLine::convert)
                                .collect(Collectors.toList());

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return weatherDataLines;
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

    public static boolean filterInvalidLine(String line) {
        return  !line.contains("mo");
    }

}
