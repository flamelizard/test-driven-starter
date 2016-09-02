package org.tdd.profile;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tom on 8/29/2016.
 */
/*
TDD
Always write test first and let it fail.
Then write code that will make it pass - prove the code passed the test.

 */
public class ProfileTest {

    private Profile profile;
    private Question questionRelocationPackage;
    private Answer hasRelocationPackage;
    private Answer hasNoRelocationPackage;
    private BooleanQuestion questionYearlyBonus;
    private Answer hasYearlyBonus;
    private Answer hasNoYearlyBonus;
    private Answer hasSickDays;
    private BooleanQuestion questionHasSickDays;
    private Criteria criteria;

    @Before
    public void createProfile() {
        profile = new Profile();
        criteria = new Criteria();
    }

    @Before
    public void createQuestionAndAnswer() {
        questionRelocationPackage = new BooleanQuestion(
                1, "Relocation package");
        questionYearlyBonus = new BooleanQuestion(2, "Yearly bonus");
        questionHasSickDays = new BooleanQuestion(3, "Sick days");
        hasRelocationPackage = new Answer(
                questionRelocationPackage, Bool.TRUE);
        hasNoRelocationPackage = new Answer(
                questionRelocationPackage, Bool.FALSE);
        hasYearlyBonus = new Answer(questionYearlyBonus, Bool.FALSE);
        hasNoYearlyBonus = new Answer(questionYearlyBonus, Bool.TRUE);
        hasSickDays = new Answer(questionHasSickDays, Bool.TRUE);
    }

// Each TDD cleanup may need you to delete tests that are redundant. These
// tests duplicate checks in some later tests.

//    @Test
//    public void matchesNothingWhenProfileEmpty() {
//        Criterion criterion = new Criterion(
//                new Answer(questionRelocationPackage, Bool.TRUE), Weight.Important);
//
//        boolean result = profile.matches(criterion);
//
//        assertFalse(result);
//    }

    @Test
    public void matchesWhenContainsAnswerMatchingCriterion() {
        profile.add(hasRelocationPackage);
        Criterion criterion = new Criterion(
                hasRelocationPackage, Weight.MustMatch);

        boolean result = profile.matches(criterion);

        assertTrue(result);
    }

    //    just for contrast, below gradually cleaned code, above initial versions
    @Test
    public void doesNotMatchWhenAnswerNotMatching() {
        profile.add(hasNoRelocationPackage);
        Criterion criterion = new Criterion(
                hasRelocationPackage, Weight.Important);

        assertFalse(profile.matches(criterion));
    }

    @Test
    public void matchesWhenContainMultipleAnswers() {
        profile.add(hasRelocationPackage);
        profile.add(hasYearlyBonus);
        Criterion criterion = new Criterion(hasYearlyBonus, Weight.MustMatch);

        assertTrue(profile.matches(criterion));
    }

    @Test
    public void returnFalseWhenNoneOfMultipleCriteriaMatches() {
        profile.add(hasNoRelocationPackage);
        profile.add(hasYearlyBonus);
        criteria.add(new Criterion(hasRelocationPackage, Weight.Important));
        criteria.add(new Criterion(hasSickDays, Weight.MustMatch));

        assertFalse(profile.matches(criteria));
    }

    @Test
    public void matchesWhenAnyOfMultipleCriteriaMatch() {
        profile.add(hasNoYearlyBonus);
        profile.add(hasSickDays);
        criteria.add(new Criterion(hasSickDays, Weight.Important));
        criteria.add(new Criterion(hasYearlyBonus, Weight.Important));

        assertTrue(profile.matches(criteria));
    }

    @Test
    public void doesNotMatchWhenMustMatchCriteriaNotMet() {
        profile.add(hasNoYearlyBonus);
        profile.add(hasSickDays);
        criteria.add(new Criterion(hasYearlyBonus, Weight.MustMatch));
        criteria.add(new Criterion(hasSickDays, Weight.Important));

        assertFalse(profile.matches(criteria));
    }

    @Test
    public void matchesWhenHasCriterionDontCare() {
        criteria.add(new Criterion(hasSickDays, Weight.DontCare));

        assertTrue(profile.matches(criteria));
    }
}
