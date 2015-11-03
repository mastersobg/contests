package com.ivangorbachev.misc;

/**
 * @author Ivan Gorbachev
 */
public class IntIntPair implements Comparable<IntIntPair> {

    private int x, y;

    public IntIntPair() {}

    public IntIntPair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntIntPair intIntPair = (IntIntPair) o;

        if (x != intIntPair.x) return false;
        return y == intIntPair.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public int compareTo(IntIntPair o) {
        if (x == o.x) {
            return y == o.y ? 0 : y < o.x ? -1 : 1;
        }
        return x < o.x ? -1 : 1;
    }
}
