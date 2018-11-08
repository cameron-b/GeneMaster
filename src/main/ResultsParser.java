package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ResultsParser {
    private final String results;

    public ResultsParser(String results) {
        this.results = results;
    }

    public void sortScore(ArrayList<Bot> botResults) {
        String currLine = null;
        BufferedReader reader = null;

        int botId;
        int botNum;
        int botWins;
        int botTurns;

        try {
            reader = new BufferedReader(new FileReader(results));
        } catch(FileNotFoundException e) {
            System.out.println("File " + results + " not found: " + e + " | Exiting.");
            System.exit(-1);
        }

        try {
            currLine = reader.readLine();
        } catch (IOException e) {
            System.out.println("Results file was empty: " + e);
        }

        botId = Integer.parseInt(currLine.split(" ")[0]);
        botTurns = 0;
        botWins = 0;

        while(currLine != null) {
            String[] botAttrs = currLine.split(" ");

            botNum = Integer.parseInt(botAttrs[0]);

            //if we've moved on to a new bot, add the old one and move on
            if(botNum != botId) {
                botResults.add(new Bot(botId, botWins, botTurns));
                botId = botNum;
                botTurns = 0;
                botWins = 0;
            }

            //like this for testing purposes. should be changed
                // for instance, this doesn't count turns of lost games against bot's score
                // could just +/- turns based on bot win/loss
            botWins += Integer.parseInt(botAttrs[1]);
            botTurns  += Integer.parseInt(botAttrs[2]);

            try {
                currLine = reader.readLine();
            } catch (IOException e) {
                System.out.println("Error while reading: " + e);
            }
        }

        //last bot as currLine reaches null before adding it
        //not the greatest way to handle it, but it works fine so w/e
        botResults.add(new Bot(botId, botWins, botTurns));
        botResults.sort((b1, b2) -> b2.score - b1.score);
    }
}
