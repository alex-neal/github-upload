/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;
/**
 *
 * @author Alex
 */
public class SetListDriver {

    private static String perfDate, venue;
    
    public static void main(String[] args) {
        System.out.println("SET LIST GENERATOR");
        SongDataStruct songs = importFromFile();
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter date of performance (MM/DD/YYYY): ");
        perfDate = keyboard.nextLine();
        System.out.print("Enter venue: ");
        venue = keyboard.nextLine();
        System.out.print("Enter number of sets: ");
        int numSets = keyboard.nextInt();
        keyboard.nextLine();
        System.out.print("Enter length of each set in minutes: ");
        int setLength = keyboard.nextInt();
        keyboard.nextLine();
        int[] intensities = new int[numSets];
        for (int i = 1; i <= numSets; i++) {
            System.out.print("Enter avg intensity (1-5) for set " + i + ": ");
            intensities[i-1] = keyboard.nextInt();
            keyboard.nextLine();
        }
        System.out.print("Acoustic show? (Y/N): ");
        String choice = keyboard.nextLine().toUpperCase();
        if (!choice.equals("Y")) {
            songs.removeAcouSongs();
        }
        
        System.out.println("Generating setlist...");
        Set[] sets = new Set[numSets];
        for (int i = 0; i < numSets; i++) {
            sets[i] = songs.generateSet(intensities[i], setLength);
        }

        System.out.println();
        System.out.println();
        displaySetlist(sets);
        
        System.out.println();
        System.out.print("Export LaTeX file? (Y/N): ");
        String exChoice = keyboard.nextLine().toUpperCase();
        if (exChoice.equals("Y")) {
            String filename = venue.replaceAll(" ", "") + "_";
            filename += perfDate.replaceAll("/", "_");
            filename += ".tex";
            try {
                PrintWriter writer = new PrintWriter(filename, "UTF-8");
                writer.print(BuildLatex.createString(venue, perfDate, sets));
                writer.close();
                System.out.println();
                System.out.println("Success. Use command pdflatex to compile \"" + filename + "\"");
            } catch (IOException e) {
                System.out.println("Error writing file. Aborting.");
            }
        } else {
            System.out.println("Goodbye");
        }


    }
    
    public static void displaySetlist(Set[] sets) {
        System.out.println("Armstead @ " + venue);
        System.out.println(perfDate);
        System.out.println();
        int maxNumSongs = 0;
        for (int i = 0; i < sets.length; i++) {
            if (sets[i].getNumSongs() > maxNumSongs)
                maxNumSongs = sets[i].getNumSongs();
        }
        String[] rowTitles = new String[4];
        for (int j = 0; j < rowTitles.length; j++) {
                rowTitles[j] = "";
        }
        for (int i = 0; i < sets.length; i++) {
            rowTitles[i] = "SET " + (i + 1);
        }
        System.out.format("%-24.24s %-24.24s %-24.24s %-20.20s\n", rowTitles[0], rowTitles[1], rowTitles[2], rowTitles[3]);
        for (int i = 0; i < maxNumSongs; i++) {
            for (int j = 0; j < rowTitles.length; j++) {
                rowTitles[j] = "";
            }
            for (int j = 0; j < sets.length; j++) {
                rowTitles[j] = sets[j].getNumberedTitle(i);
            }
            
            System.out.format("%-24.24s %-24.24s %-24.24s %-20.20s\n", rowTitles[0], rowTitles[1], rowTitles[2], rowTitles[3]);
            
        }
        
    }

    public static SongDataStruct importFromFile() {
        SongDataStruct songs = new SongDataStruct();
        Song curSong;
        BufferedReader reader = null;
        String line = "";
        String title, artist;
        int minutes, seconds, length, intensity, acInt;
        boolean acOnly;
        System.out.println("Importing song data...");
        try {
            reader = new BufferedReader(new FileReader("armsteadsongs.csv"));
            reader.readLine();
            line = reader.readLine();
            while (line != null) {

                String[] songData = line.split(",");
                if (songData.length == 5) {
                    title = songData[0];
                    artist = songData[1];
                    String[] minSecs = songData[2].split(":");
                    minutes = Integer.parseInt(minSecs[0]);
                    seconds = Integer.parseInt(minSecs[1]);
                    length = (minutes * 60) + seconds;
                    intensity = Integer.parseInt(songData[3]);
                    acInt = Integer.parseInt(songData[4]);
                    if (acInt == 0) {
                        acOnly = false;
                    } else {
                        acOnly = true;
                    }

                    curSong = new Song(title, artist, length, intensity, acOnly);
                    songs.add(curSong);
                }
                line = reader.readLine();
            }
            System.out.println("Import successful");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return songs;
    }
}
