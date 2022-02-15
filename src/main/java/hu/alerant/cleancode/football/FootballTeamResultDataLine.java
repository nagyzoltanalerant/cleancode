package hu.alerant.cleancode.football;

import hu.alerant.cleancode.weather.WeatherDataLine;

import java.util.Arrays;
import java.util.List;

public class FootballTeamResultDataLine {
    private String teamName;
    private int goalsScored;
    private int goalsReceived;

    public FootballTeamResultDataLine(String teamName, int goalsScored, int goalsReceived) {
        this.teamName = teamName;
        this.goalsScored = goalsScored;
        this.goalsReceived = goalsReceived;
    }

    public FootballTeamResultDataLine() {
        this.teamName = "UNKNOWN";
        this.goalsScored = 0;
        this.goalsReceived = 0;
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
