package com.ivangorbachev;

import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class LiveConcert {
    static class Song {
        int value;
        String name;

        public Song(int value, String name) {
            this.value = value;
            this.name = name;
        }
    }
    public int maxHappiness(int[] h, String[] s) {
        List<Song> songs = new ArrayList<>();
        for (int i = 0; i < h.length; i++) {
            songs.add(new Song(h[i], s[i]));
        }
        Collections.sort(songs, (a, b) -> b.value - a.value);
        HashSet<String> was = new HashSet<>();
        int ret = 0;
        for (Song song : songs) {
            if (!was.contains(song.name)) {
                was.add(song.name);
                ret += song.value;
            }
        }
        return ret;
    }
}
