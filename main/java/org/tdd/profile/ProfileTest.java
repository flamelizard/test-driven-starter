package org.tdd.profile;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tom on 8/29/2016.
 */
public class ProfileTest {

    private Profile profile;
    private Question questionRelocationPackage;
    private Answer hasRelocationPackage;
    private Answer hasNoRelocationPackage;

    @Before
    public void createProfile() {
        profile = new Profile();
    }

    @Before
    public void createQuestionAndAnswer() {
        questionRelocationPackage = new BooleanQuestion(
                1, "Relocation package");
        hasRelocationPackage = new Answer(
                questionRelocationPackage, Bool.TRUE);
        hasNoRelocationPackage = new Answer(
                questionRelocationPackage, Bool.FALSE);
    }

    @Test
    public void matchesNothingWhenProfileEmpty() {
        Criterion criterion = new Criterion(
                new Answer(questionRelocationPackage, Bool.TRUE), Weight.DontCare);

        boolean result = profile.matches(criterion);

        assertFalse(result);
    }

    @Test
    public void matchesWhenContainsAnswerMatchingCriterion() {
        profile.add(hasRelocationPackage);
        Criterion criterion = new Criterion(
                hasRelocationPackage, Weight.MustMatch);

        boolean result = profile.matches(criterion);

        assertTrue(result);
    }

    @Test
    public void doesNotMatchWhenAnswerNotMatching() {
        profile.add(hasNoRelocationPackage);
        Criterion criterion = new Criterion(
                hasRelocationPackage, Weight.Important);

        boolean result = profile.matches(criterion);

        assertFalse(result);
    }
}
