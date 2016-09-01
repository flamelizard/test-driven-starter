package org.tdd.profile;

/**
 * Created by Tom on 8/29/2016.
 */
public class Criterion {
    private final Answer answer;
    private final Weight weight;

    public Criterion(Answer answer, Weight weight) {
        this.answer = answer;
        this.weight = weight;
    }

    public Answer getAnswer() {
        return answer;
    }

    public Weight getWeight() {
        return weight;
    }
}
