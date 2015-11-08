package com.ivangorbachev.misc;

import java.util.Comparator;

/**
 * @author Ivan Gorbachev
 */
public class LongLongPair implements Comparator<LongLongPair> {

    public long x, y;

    public LongLongPair() {
    }

    public LongLongPair(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    @Override
    public int compare(LongLongPair o1, LongLongPair o2) {
        if (o1.x == o2.x) {
            return o1.y == o2.y ? 0 : o1.y < o2.y ? -1 : 1;
        }
        return o1.x < o2.x ? -1 : 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LongLongPair that = (LongLongPair) o;

        if (x != that.x) return false;
        return y == that.y;

    }

    @Override
    public int hashCode() {
        int result = (int) (x ^ (x >>> 32));
        result = 31 * result + (int) (y ^ (y >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "[x = " + x + " y = " + y + "]";
    }
}
