package com.ivangorbachev.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Ivan Gorbachev
 */
public class Reader {

    private BufferedReader in;
    private StringTokenizer st;

    public Reader(InputStream is) {
        in = new BufferedReader(new InputStreamReader(is));
    }

    public String readString() {
        while (st == null || !st.hasMoreTokens())
            try {
                st = new StringTokenizer(in.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return st.nextToken();
    }

    public String next() {
        return readString();
    }

    public int readInt() {
        return Integer.valueOf(readString());
    }

    public int[] readIntArray(int size) {
        int []ret = new int[size];
        for (int i = 0; i < size; i++) {
            ret[i] = readInt();
        }
        return ret;
    }

    public int[] readIntArray() {
        int n = readInt();
        return readIntArray(n);
    }

    public List<Integer> readIntList(int size) {
        List<Integer> ret = new ArrayList<Integer>(size);
        for (int i = 0; i < size; i++) {
            ret.add(readInt());
        }
        return ret;
    }
    public List<Integer> readIntList() {
        int n = readInt();
        return readIntList(n);
    }

    public long readLong() {
        return Long.valueOf(readString());
    }

    public double readDouble() {
        return Double.valueOf(readString());
    }

    public String readLine() {
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
