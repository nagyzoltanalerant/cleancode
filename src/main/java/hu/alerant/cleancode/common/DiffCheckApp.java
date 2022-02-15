package hu.alerant.cleancode.common;

import hu.alerant.cleancode.common.football.FootballDiffContext;
import hu.alerant.cleancode.common.weather.WeatherDiffContext;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.ClassLoader.getSystemResource;

public class DiffCheckApp {

    private DiffContext diffContext;

    public DiffCheckApp(DiffContext diffContext) {
        this.diffContext = diffContext;
    }

    public static void main(String[] args) {

        //football
        DiffContext diffContext = new FootballDiffContext();
        DiffCheckApp app = new DiffCheckApp(diffContext);
        List<DataLine> dataLines = app.readDataLines();
        DataLine minimumDiffRow = app.findMinimumDiffRow(dataLines);

        System.out.println(minimumDiffRow.getResult());

        //weather
        diffContext = new WeatherDiffContext();
        app = new DiffCheckApp(diffContext);
        dataLines = app.readDataLines();
        minimumDiffRow = app.findMinimumDiffRow(dataLines);

        System.out.println(minimumDiffRow.getResult());

    }

    public List<DataLine> readDataLines() {
        List<DataLine> dataLines = new ArrayList<>();
        try (var allLines = Files.lines(Paths.get(getSystemResource(diffContext.getDataFileName()).toURI()))) {

            dataLines = diffContext.parseDataFile(allLines);

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return dataLines;
    }

    public DataLine findMinimumDiffRow(List<DataLine> lines) {
        DataLine findMinimumGoalDiffRow = lines.get(0);
        long foundMinDiff = Long.MAX_VALUE;

        for (DataLine line : lines) {
            long actualDiff = line.getDiff();

            if (actualDiff < foundMinDiff) {
                foundMinDiff = actualDiff;
                findMinimumGoalDiffRow = line;
            }
        }

        return findMinimumGoalDiffRow;
    }
}
