package org.tdd.profile;

/**
 * Created by Tom on 8/29/2016.
 */
public enum Weight {
    MustMatch(100), Important(50), DontCare(10);

    private int value;

    Weight(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
