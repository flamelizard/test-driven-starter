package org.tdd.profile;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by Tom on 8/29/2016.
 */
/*
TDD
Always write test first and let it fail.
Then write code that will make it pass - prove the code passed the test.
 */
public class ProfileMatchTest {

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

    @Test
    public void matchesWhenContainsAnswerMatchingCriterion() {
        profile.add(hasRelocationPackage);
        Criterion criterion = new Criterion(
                hasRelocationPackage, Weight.MustMatch);

        ProfileMatch match = profile.match(new Criteria(criterion));

        assertTrue(match.matches());
    }

    @Test
    public void doesNotMatchWhenAnswerNotMatching() {
        profile.add(hasNoRelocationPackage);
        Criterion criterion = new Criterion(
                hasRelocationPackage, Weight.Important);

        ProfileMatch match = profile.match(new Criteria(criterion));

        assertFalse(match.matches());
    }

    @Test
    public void matchesWhenContainMultipleAnswers() {
        profile.add(hasRelocationPackage);
        profile.add(hasYearlyBonus);
        Criterion criterion = new Criterion(hasYearlyBonus, Weight.MustMatch);

        ProfileMatch match = profile.match(new Criteria(criterion));

        assertTrue(match.matches());
    }

    @Test
    public void returnFalseWhenNoneOfMultipleCriteriaMatches() {
        profile.add(hasNoRelocationPackage);
        profile.add(hasYearlyBonus);
        criteria.add(new Criterion(hasRelocationPackage, Weight.Important));
        criteria.add(new Criterion(hasSickDays, Weight.MustMatch));

        ProfileMatch match = profile.match(criteria);

        assertFalse(match.matches());
    }

    @Test
    public void matchesWhenAnyOfMultipleCriteriaMatch() {
        profile.add(hasNoYearlyBonus);
        profile.add(hasSickDays);
        criteria.add(new Criterion(hasSickDays, Weight.Important));
        criteria.add(new Criterion(hasYearlyBonus, Weight.Important));

        ProfileMatch match = profile.match(criteria);

        assertTrue(match.matches());
    }

    @Test
    public void doesNotMatchWhenMustMatchCriteriaNotMet() {
        profile.add(hasNoYearlyBonus);
        profile.add(hasSickDays);
        criteria.add(new Criterion(hasYearlyBonus, Weight.MustMatch));
        criteria.add(new Criterion(hasSickDays, Weight.Important));

        ProfileMatch match = profile.match(criteria);

        assertFalse(match.matches());
    }

    @Test
    public void matchesWhenHasCriterionDontCare() {
        criteria.add(new Criterion(hasSickDays, Weight.DontCare));

        ProfileMatch match = profile.match(criteria);

        assertTrue(match.matches());
    }

    @Test
    public void scoreZeroWhenThereAreNoMatches() {
        criteria.add(new Criterion(hasRelocationPackage, Weight.Important));

        ProfileMatch match = profile.match(criteria);

        assertThat(match.getScore(), equalTo(0));
    }

    @Test
    public void scoreIncreaseForSingleMatch() {
        profile.add(hasRelocationPackage);
        profile.add(hasYearlyBonus);
        criteria.add(new Criterion(hasRelocationPackage, Weight.MustMatch));

        ProfileMatch match = profile.match(criteria);

        assertThat(match.getScore(), equalTo(100));
    }

    @Test
    public void scoreIncreaseForMultipleMatch() {
        profile.add(hasRelocationPackage);
        profile.add(hasYearlyBonus);
        profile.add(hasSickDays);
        criteria.add(new Criterion(hasRelocationPackage, Weight.Important));
        criteria.add(new Criterion(hasSickDays, Weight.DontCare));

        ProfileMatch match = profile.match(criteria);

        assertThat(match.getScore(), equalTo(60));
    }
}
