package org.tdd.profile;

/**
 * Created by Tom on 8/29/2016.
 */
public class Answer {
    private final Bool condition;
    private Question question;

    public Answer(Question question, Bool condition) {
        this.question = question;
        this.condition = condition;
    }

    public Question getQuestion() {
        return question;
    }

    public Bool getCondition() {
        return condition;
    }

    //    unit test
    public boolean match(Answer that) {
        return this.getQuestion() == that.getQuestion() &&
                this.getCondition() == that.getCondition();
    }
}
