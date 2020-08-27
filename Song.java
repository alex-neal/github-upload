/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alex
 */
public class Song {
    
    private String title;
    private String artist;
    private int length;
    private int intensity;
    private boolean acOnly;
    
    public Song(String n, String a, int l, int i, boolean ac) {
        title = n;
        artist = a;
        length = l;
        intensity = i;
        acOnly = ac;
    }
    
    public String getName() {
        return title;
    }
    
    public int getLength() {
        return length;
    }
    
    public int getIntensity() {
        return intensity;
    }
    
    public String getArtist() {
        return artist;
    }
    
    public boolean isAcouOnly() {
        return acOnly;
    }
    public String toString() {
        String output = "Title: " + title + "\t\tArtist: " + artist;
        return output;
    }
}
