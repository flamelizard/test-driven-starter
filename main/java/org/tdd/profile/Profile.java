package org.tdd.profile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tom on 8/29/2016.
 */
public class Profile {

    private Map<String, Answer> answers = new HashMap<>();

    public void add(Answer answer) {
        answers.put(answer.getQuestionText(), answer);
    }

    public Answer getMatchingAnswer(Criterion criterion) {
        return answers.get(criterion.getAnswer().getQuestionText());
    }

    public boolean matches(Criterion criterion) {
        return criterion.getWeight() == Weight.DontCare ||
                criterion.getAnswer().match(getMatchingAnswer(criterion));
    }

    public boolean matches(Criteria criteria) {
        for (Criterion criterion : criteria) {
            if (matches(criterion))
                return true;
            else if (criterion.getWeight() == Weight.MustMatch)
                return false;
        }
        return false;
    }
}
