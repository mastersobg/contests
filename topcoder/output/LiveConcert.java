import java.io.*;
import java.util.*;

import static java.lang.Math.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Collections;

public class LiveConcert {
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

    static class Song {
        int value;
        String name;

        public Song(int value, String name) {
            this.value = value;
            this.name = name;
        }

    }

}
