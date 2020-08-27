/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
/**
 *
 * @author Alex
 */
public class Set {
    
    private int totalSecs;
    private int maxSecs;
    private ArrayList<Song> songs;
    
    Set(int minutes) {
        maxSecs = (int)(minutes * 60 * 0.9);
        totalSecs = 0;
        songs = new ArrayList<Song>();
    }
    
    public boolean addSong(Song s) {
        if (totalSecs >= maxSecs)
            return false;
        songs.add(s);
        totalSecs += s.getLength();
        return true;
    }
    
    public void display() {
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i).getName());
        }
    }
    
    public int getNumSongs() {
        return songs.size();
    }
    
    public String getNumberedTitle(int i) {
        if (i >= songs.size())
            return "";
        else {  
            String num = String.format("%-4.4s", i+1 + ". ");
            return num + songs.get(i).getName();
        }

    }
    
    public String getTitle(int i) {
        if (i >= songs.size())
            return "";
        else {
            return songs.get(i).getName();
        }
    }
    
}
