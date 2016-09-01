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
        return criterion.getAnswer().match(getMatchingAnswer(criterion));

//        String question1 = criterion.getAnswer().getQuestionText();
//        for (String question2: answers.keySet()) {
//            if (question1.equals(question2)) {
//                return criterion.getAnswer().match(answers.get(question2));
//            }
//        }
//        return false;
    }

    public boolean matches(Criteria criteria) {
        for (Criterion criterion : criteria) {
            if (matches(criterion)) {
                return true;
            }
        }
        return false;
    }
//
//    public void add(Answer answer) {
//        answers.put(answer.getQuestion().getText(), answer);
//    }
}
