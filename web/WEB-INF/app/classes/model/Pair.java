package model;

/**
 * Created by User on 28.08.2015.
 */
public class Pair<T, T1> {
    T firstAttr;
    T1 secondAttr;

    public Pair(T firstAttr, T1 secondAttr) {
        this.firstAttr = firstAttr;
        this.secondAttr = secondAttr;
    }

    public T getFirstAttr() {
        return firstAttr;
    }

    public T1 getSecondAttr() {
        return secondAttr;
    }
}
