package main;

import java.util.ArrayList;

// commit test

public class RunMaster {
    final String results, botFormat;

    ArrayList<Bot> botResults = new ArrayList<>();

    public RunMaster(String [] args) {
        results = args[0];
        botFormat = args[1];
    }

    public static void main(String[] args) {
        RunMaster master = new RunMaster(args);
        master.run();
    }

    public void run() {
        System.out.println("GeneMaster running");

        ResultsParser rp = new ResultsParser(results);
        GeneController gc = new GeneController(botFormat);

        //Totals results of each bot + stores into individual bot objects
            // e.g. 5 results from bot1 totaled and stored into a "Bot" object
            // ArrayList then contains each bot sorted by score
        rp.sortScore(botResults);

        System.out.println("Sorted + Scored results");
        System.out.println("-----------------------");
        for(Bot bot: botResults) {
            System.out.println("Bot: " + bot.id + " | Wins: " + bot.wins + " | Turns: " + bot.turns + " | Score : " + bot.score);
        }

        //Takes list of bot scores + handles reproduction
            //for first 50 bots, takes their results + modifies slightly, stores changes in the latter 50
            //e.g. if top bot was bot1.txt and 51st bot was bot51.txt, modify bot1.txt and store changes in bot51.txt
        gc.reproduceBots(botResults);

    }
}
