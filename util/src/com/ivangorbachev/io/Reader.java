package com.ivangorbachev.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    String readString() {
        while (st == null || !st.hasMoreTokens())
            try {
                st = new StringTokenizer(in.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return st.nextToken();
    }

    int readInt() {
        return Integer.valueOf(readString());
    }

    long readLong() {
        return Long.valueOf(readString());
    }

    double readDouble() {
        return Double.valueOf(readString());
    }

    String readLine() {
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
