package com.ivangorbachev.util;

/**
 * @author Ivan Gorbachev
 */
public class BitsUtil {

    public static boolean checkBit(int number, int bit) {
        return (number & (1 << bit)) != 0;
    }

    public static int setBit(int number, int bit) {
        return number | (1 << bit);
    }
}
