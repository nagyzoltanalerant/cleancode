package hu.alerant.cleancode.common.football;

import hu.alerant.cleancode.common.DataLine;
import hu.alerant.cleancode.common.DiffContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FootballDiffContext implements DiffContext {

    private final String dataFileName = "datamunging/football.dat";
    public final int HEADER_LINES = 1;

    @Override
    public String getDataFileName() {
        return dataFileName;
    }

    @Override
    public int getSkipHeaders() {
        return HEADER_LINES;
    }

    @Override
    public DataLine getInitDataLine() {
        return new FootballDataLine();
    }

    @Override
    public List<DataLine> parseDataFile(Stream<String> allLines) {
        return  allLines
                .skip(getSkipHeaders())
                .filter(FootballDiffContext::filterInvalidLine)
                .map(FootballDiffContext::convert)
                .collect(Collectors.toList());
    }

    public static DataLine convert(String rawDataLine) {
        List<String> lineColumns = Arrays.asList(rawDataLine.split("\\s+"));
        String teamName = lineColumns.get(2);
        int goalsScored = Integer.valueOf(lineColumns.get(7));
        int goalsReceived = Integer.valueOf(lineColumns.get(9));

        return new FootballDataLine(teamName, goalsScored, goalsReceived);
    }

    public static boolean filterInvalidLine(String line) {
        return  !line.contains("---");
    }

}
