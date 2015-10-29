package com.ivangorbachev.util;

/**
 * @author Ivan Gorbachev
 */
public class Timer {
    long time;

    void start() {
        time = System.currentTimeMillis();
    }
    long time() {
        return System.currentTimeMillis() - time;
    }
    void print() {
        print("Time spent = ");
    }

    void print(String message) {
        Dbg.dbg(message, time());
    }
}
