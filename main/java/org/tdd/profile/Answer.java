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

    public String getQuestionText() {
        return question.getText();
    }

    public Bool getCondition() {
        return condition;
    }

    //    unit test
    public boolean match(Answer otherAnswer) {
        return otherAnswer != null &&
                this.getQuestionText().equals(otherAnswer.getQuestionText()) &&
                this.getCondition() == otherAnswer.getCondition();
    }
}
