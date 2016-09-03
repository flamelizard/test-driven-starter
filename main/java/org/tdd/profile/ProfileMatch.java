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

    private Answer getMatchingAnswer(Criterion criterion) {
        return answers.get(criterion.getAnswer().getQuestionText());
    }

    private boolean matches(Criterion criterion) {
        return criterion.getWeight() == Weight.DontCare ||
                criterion.getAnswer().match(getMatchingAnswer(criterion));
    }

    public boolean matches() {
        for (Criterion criterion : criteria) {
            if (matches(criterion))
                return true;
            else if (criterion.getWeight() == Weight.MustMatch)
                return false;
        }
        return false;
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
