package com.ivangorbachev.misc;

import java.util.Comparator;

/**
 * @author Ivan Gorbachev
 */
public class Pair<K, V> {

    public K x;
    public V y;

    public Pair() {
    }

    public Pair(K x, V y) {
        this.x = x;
        this.y = y;
    }

    public K getX() {
        return x;
    }

    public void setX(K x) {
        this.x = x;
    }

    public V getY() {
        return y;
    }

    public void setY(V y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (x != null ? !x.equals(pair.x) : pair.x != null) return false;
        return !(y != null ? !y.equals(pair.y) : pair.y != null);

    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "[x = " + (x == null ? "null" : x.toString()) +
                " y = " + (y == null ? "null" : y.toString())
                + "]";
    }
}
