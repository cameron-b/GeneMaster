package main;

import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GeneController {
    private final String format;

    public GeneController(String format) {
        this.format = format;
    }

    public void reproduceBots(ArrayList<Bot> botResults, boolean swapRegions, boolean testParams) {
        ArrayList<String> f2Contents;
        String f1, f2, cLine1, cLine2;
        BufferedReader r1, r2;
        BufferedWriter w;
        int cut;

        r1 = null;
        r2 = null;
        w = null;
        cut = (botResults.size() / 2);
        f2Contents = new ArrayList<>();

        //for loop that cycles thru arraylist up until c2-1
        //pass both files to each param changing function
        for(int c1 = 0, c2 = cut; c1 < cut; c1++, c2++) {
            f1 = format + botResults.get(c1).id + ".txt";
            f2 = format + botResults.get(c2).id + ".txt";

            //System.out.println("Accessing " + file1 + " and overwriting " + file2);

            try {
                r2 = new BufferedReader(new FileReader(f2));

                cLine2 = r2.readLine();

                while(cLine2 != null) {
                    f2Contents.add(cLine2);
                    //System.out.println("f2:" + cLine2);
                    cLine2 = r2.readLine();
                }
            } catch (IOException e) {
                System.out.println("Error: " + e);
                System.exit(-1);
            }

            try {
                r1 = new BufferedReader(new FileReader(f1));
                w = new BufferedWriter((new FileWriter(f2)));

                cLine1 = r1.readLine();
                if(swapRegions)
                    w.write(swapStartingRegions(cLine1, f1, f2));
                else
                    w.write(f2Contents.get(0));

                w.newLine();
                w.flush();

                cLine1 = r1.readLine();
                if(testParams)
                    w.write(swapStartingRegions(cLine1, f1, f2));
                else
                    w.write(f2Contents.get(1));

                w.flush();
                //add more param swapping functions as needed
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }

        }
    }

    private String swapStartingRegions(String params, String file1, String file2) {
        String[] prefRegions = params.split(" ");
        BufferedWriter writer;
        String tempRegion;
        int r1, r2;

        r1 = new Random().nextInt(prefRegions.length);
        do {
            r2 = new Random().nextInt(prefRegions.length);
        } while (r2 == r1);

        //System.out.println("[" + file1 + "]->[" + file2 + "] " + r1 +":" + prefRegions[r1] + " <-> " + r2 + ":" + prefRegions[r2]);
        tempRegion = prefRegions[r1];
        prefRegions[r1] = prefRegions[r2];
        prefRegions[r2] = tempRegion;

        return String.join(" ", prefRegions);
    }
}
