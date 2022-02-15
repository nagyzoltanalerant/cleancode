package hu.alerant.cleancode.common.football;

import hu.alerant.cleancode.common.DataLine;

public class FootballDataLine implements DataLine {
    private String teamName;
    private int goalsScored;
    private int goalsReceived;

    public FootballDataLine(String teamName, int goalsScored, int goalsReceived) {
        this.teamName = teamName;
        this.goalsScored = goalsScored;
        this.goalsReceived = goalsReceived;
    }

    public FootballDataLine() {
        this.teamName = "UNKNOWN";
        this.goalsScored = 0;
        this.goalsReceived = 0;
    }

    public int getDiff() {
        return Math.abs(this.goalsScored - this.goalsReceived);
    }

    public String getResult() {
        return this.teamName;
    }
}
