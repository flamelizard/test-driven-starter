package org.tdd.profile;

/**
 * Created by Tom on 8/29/2016.
 */
public class Profile {

    private Answer answer;

    public void add(Answer answer) {
        this.answer = answer;
    }

    public boolean matches(Criterion criterion) {
        return answer != null && answer.match(criterion.getAnswer());
    }
//
//    public void add(Answer answer) {
//        answers.put(answer.getQuestion().getText(), answer);
//    }
}
