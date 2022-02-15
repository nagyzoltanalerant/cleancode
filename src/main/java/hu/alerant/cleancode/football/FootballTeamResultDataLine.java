package hu.alerant.cleancode.football;

import hu.alerant.cleancode.weather.WeatherDataLine;

import java.util.Arrays;
import java.util.List;

public class FootballTeamResultDataLine {
    private final String teamName;
    private final int goalsScored;
    private final int goalsReceived;

    public FootballTeamResultDataLine(String teamName, int goalsScored, int goalsReceived) {
        this.teamName = teamName;
        this.goalsScored = goalsScored;
        this.goalsReceived = goalsReceived;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getGoalsReceived() {
        return goalsReceived;
    }



}
