package ex46;

/*
 *  UCF COP3330 Summer 2021 Assignment 3 Solution
 *  Copyright 2021 Gracie Bliss
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class app {
    public static void main(String[] args){
        ArrayList<String> lines=new ArrayList<String>();

        //read in from the file
        try {
            File input = new File("exercise46_input.txt");
            Scanner in = new Scanner(input);
            while (in.hasNextLine()) {
                String data = in.nextLine();
                lines.add(data);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        ArrayList<word> allWords=returnWords(lines);
        printList(allWords);
    }



    //calls the split function to get each word between the spaces
    public static String[] splitLines(String line){
        String[] words=line.split(" ");
        return words;
    }

    //finds all the different words
    public static ArrayList<String> findWords(ArrayList<String> lines){
        ArrayList <String[]> words=new ArrayList<String[]>();

        for(int i=0; i<lines.size(); i++){
            words.add(splitLines(lines.get(i)));
        }

        ArrayList <String> word=new ArrayList<String>();

        for(int i=0; i<words.size(); i++){
            for(int j=0; j<words.get(i).length; j++){
                for(int k=0; k<word.size(); k++){
                    if(word.get(k).equals(words.get(i)[j])){
                        break;
                    }
                    else{
                        word.add(words.get(i)[j]);
                    }
                }
            }
        }
        return word;
    }


    //finds the amount of times a word occurs
    public static int count(String name, ArrayList<String> line){
        int count=0;
        for(int i=0;i<line.size(); i++){
            String[] words=splitLines(line.get(i));
            for(int j=0; j<words.length;j++){
                if(words[j].equals(name)){
                    count++;
                }
            }
        }
        return count;
    }


    //returns an array of words that contains the word itself and how many times it occurs
    public static ArrayList<word> returnWords(ArrayList<String> line){
        ArrayList<word> allWords=new ArrayList<word>();
        ArrayList<String> words=findWords(line);

        for(int i=0; i<words.size(); i++){
            word w=new word();
            w.name=words.get(i);
            w.count=count(words.get(i), line);
            allWords.add(w);
        }
        return allWords;
    }


    //prints the list of words in order of most used to least used
    public static void printList(ArrayList<word> words){
        word most=new word();

        while(words.size()>0) {
            most=words.get(0);
            for (int i = 0; i < words.size(); i++) {
                if (most.count < words.get(i).count) {
                    most = words.get(i);
                }
            }
            System.out.print(most.name + ": ");
            for(int j = 0; j < most.count; j++) {
                System.out.print("*");
                }
            System.out.print("\n");
            words.remove(most);
        }
    }

}
