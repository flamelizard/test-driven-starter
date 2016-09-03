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

    public ProfileMatch match(Criteria criteria) {
        return new ProfileMatch(answers, criteria);
    }
}
