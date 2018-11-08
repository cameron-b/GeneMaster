package main;

public class Bot {
    public int id;
    public int wins;
    public int turns;
    public int score;

    public Bot(int id, int wins, int turns) {
        this.id = id;
        this.wins = wins;
        this.turns = turns;

        scoreBot();
    }

    //Handles scoring of a bot
    //Just made something up for testing, recommend changing
    private void scoreBot() {
        int winScore = wins * 100;
        int turnsScore = (int)((100.0/turns) * 10);
        score = winScore + turnsScore;
    }
}
