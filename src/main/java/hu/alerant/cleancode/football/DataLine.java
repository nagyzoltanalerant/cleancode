package hu.alerant.cleancode.football;

public class DataLine {
    private String teamName;
    private int goalsScored;
    private int goalsReceived;

    public DataLine(String teamName, int goalsScored, int goalsReceived) {
        this.teamName = teamName;
        this.goalsScored = goalsScored;
        this.goalsReceived = goalsReceived;
    }

    public DataLine() {
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
