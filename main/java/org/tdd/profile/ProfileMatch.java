package org.tdd.profile;

import java.util.Map;

/**
 * Created by Tom on 9/2/2016.
 */
public class ProfileMatch {
    private final Map<String, Answer> answers;
    private Criteria criteria;

    public ProfileMatch(Map<String, Answer> answers, Criteria criteria) {
        this.answers = answers;
        this.criteria = criteria;
    }

    public Answer getMatchingAnswer(Criterion criterion) {
        return answers.get(criterion.getAnswer().getQuestionText());
    }

    public boolean matches(Criterion criterion) {
        return criterion.getWeight() == Weight.DontCare ||
                criterion.getAnswer().match(getMatchingAnswer(criterion));
    }

    public int getScore() {
        int score = 0;
        for (Criterion criterion : criteria) {
            if (matches(criterion)) {
                score += criterion.getWeight().getValue();
            }
        }
        return score;
    }
}
