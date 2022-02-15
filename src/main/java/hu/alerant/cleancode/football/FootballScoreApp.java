package hu.alerant.cleancode.football;
import hu.alerant.cleancode.weather.WeatherDataLine;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.ClassLoader.getSystemResource;

public class FootballScoreApp {

    private final String dataFileName;
    public static final int HEADER_LINES = 1;

    public FootballScoreApp(String dataFileName) {
        this.dataFileName = dataFileName;
    }

    public static void main(String[] args) {
        String dataFileName = "datamunging/football.dat";
        FootballScoreApp app = new FootballScoreApp(dataFileName);
        List<FootballTeamResultDataLine> footballTeamResultDataLines = app.readWeatherDataLines();
        FootballTeamResultDataLine minimumGoalDiffRow = app.findMinimumGoalDiffRow(footballTeamResultDataLines);

        System.out.println(minimumGoalDiffRow.getTeamName());

    }
    public List<FootballTeamResultDataLine> readWeatherDataLines() {
        List<FootballTeamResultDataLine> dataLines = new ArrayList<>();
        try (var allLines = Files.lines(Paths.get(getSystemResource(dataFileName).toURI()))) {

            dataLines = allLines
                    .skip(HEADER_LINES)
                    .filter(FootballScoreApp::filterInvalidLine)
                    .map(FootballScoreApp::convert)
                    .collect(Collectors.toList());

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return dataLines;
    }

    public FootballTeamResultDataLine findMinimumGoalDiffRow(List<FootballTeamResultDataLine> lines) {
        FootballTeamResultDataLine findMinimumGoalDiffRow = new FootballTeamResultDataLine();
        long foundMinDiff = Long.MAX_VALUE;

        for (FootballTeamResultDataLine line : lines) {
            long actualDiff = Math.abs(line.getGoalsScored() - line.getGoalsReceived());

            if (actualDiff < foundMinDiff) {
                foundMinDiff = actualDiff;
                findMinimumGoalDiffRow = line;
            }
        }

        return findMinimumGoalDiffRow;
    }

    public static boolean filterInvalidLine(String line) {
        return  !line.contains("---");
    }

    public static FootballTeamResultDataLine convert(String rawDataLine) throws NumberFormatException {
        List<String> lineColumns = Arrays.asList(rawDataLine.split("\\s+"));
        String teamName = lineColumns.get(2);
        int goalsScored = Integer.valueOf(lineColumns.get(7));
        int goalsReceived = Integer.valueOf(lineColumns.get(9));

        return  new FootballTeamResultDataLine(teamName, goalsScored, goalsReceived);
    }
}
