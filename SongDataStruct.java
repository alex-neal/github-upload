/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;
import java.util.ArrayList;
/**
 *
 * @author Alex
 */
public class SongDataStruct {
    
    private ArrayList<Song> arr;

    
    public SongDataStruct() {
        arr = new ArrayList<Song>();

    }
    
    public void add(Song s) {
        if (s.getIntensity() > 5) {
            System.out.println("A song was ignored due to unexpected intensity");
            return;
        }
        arr.add(s);

    }

    public Set generateSet(int i, int length) {

        Set set = new Set(length);
        Random r = new Random();
        int index;
        boolean stillSpace = true;
        while (stillSpace) {

            do {
                index = r.nextInt(arr.size());
            } while (Math.abs(i - arr.get(index).getIntensity()) > 1);
            stillSpace = set.addSong(arr.get(index));
            if (stillSpace) {
                arr.remove(index);
            }
        }
        return set;
    }
    
    public void removeAcouSongs() {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).isAcouOnly()) {
                arr.remove(i);
                i--;
            }
        }
    }

    
    public void display() {
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i).getName());
        }
    }
}
