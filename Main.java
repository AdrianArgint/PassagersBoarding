package com.adrianargint;
import java.io.*;

/**
 *       Adrian Argint
 *          323CB
 */
public class Main {

    /**
     * Reads from "queue.in" <br>
     * Does the work    <br>
     * Writes in "queue.out"
     * @param args empty (no args) :)
     * @throws IOException Throws exceptions catched by reading/writing from/in files
     */
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(new File("queue.in")));
        int passagersNumber = Integer.parseInt(input.readLine());

        PriorityQueue pq = new PriorityQueue();
        String line;
        String[] word;
        for (int i = 0; i < passagersNumber; i++){
            line = input.readLine();
            word = line.split(" ", 10);
            if((String.valueOf(word[0].charAt(0))).compareTo("f") == 0) {
                Person g = pq.contains(word[0]);
                if(g == null)
                    g = pq.newFamily(word[3], word[4], word[0]);
                g.add(new HumanBeing(word[1], Integer.parseInt(word[2]), Boolean.parseBoolean(word[5])));
            }
            else if((String.valueOf(word[0].charAt(0))).compareTo("g") == 0) {
                Person g = pq.contains(word[0]);
                if(g == null)
                    g = pq.newGroup(word[3], word[4], word[0]);
                g.add(new HumanBeing(word[1], Integer.parseInt(word[2]), Boolean.parseBoolean(word[5])));
            }
            else {
                Person g = pq.contains(word[0]);
                if(g == null)
                    g = pq.newSolo(word[3], word[4], word[0]);
                g.add(new HumanBeing(word[1], Integer.parseInt(word[2]), Boolean.parseBoolean(word[5])));
            }
        }

        //clear output file
        FileWriter output = new FileWriter(new File("queue.out"));
        output.close();

        int ok = 0; // kinda useless but usefull because it`s asked not to print \n at the end
        while ((line = input.readLine()) != null) {
            if(line.compareTo("embark") == 0)
                pq.embark();

            if(line.compareTo("list") == 0){
                if(ok == 1) {
                    output = new FileWriter(new File("queue.out"), true);
                    output.write("\n");
                    output.close();
                }
                else
                    ok = 1;
                pq.list();
            }

            try{
                if(String.valueOf(line.charAt(0)).compareTo("i") == 0){
                    word = line.split(" ", 10);
                    Person g = pq.contains(word[1]);
                    pq.insert(g, g.getPriority());
//                    groups.deletePerson(g);
                }
            }
            catch(Exception ignored){}

            try{
                if(String.valueOf(line.charAt(0)).compareTo("d") == 0){
                    word = line.split(" ");
                    Person p = pq.contains(word[1]);
                    if(word.length == 2){
                        pq.delete(p);
                    }
                    else{
                        pq.delete(p, word[2]);
                    }
                }
            }
            catch(Exception ignored){}

        }
        input.close();
    }
}
