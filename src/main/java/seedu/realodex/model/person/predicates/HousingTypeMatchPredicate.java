package seedu.realodex.model.person.predicates;

import java.util.function.Predicate;

import seedu.realodex.commons.util.ToStringBuilder;
import seedu.realodex.model.person.HousingType;
import seedu.realodex.model.person.Person;


/**
 * Tests that a {@code Person}'s preferred HousingType equals the specified HousingType.
 */
public class HousingTypeMatchPredicate implements Predicate<Person> {
    private final HousingType housingType;

    public HousingTypeMatchPredicate(HousingType housingType) {
        this.housingType = housingType;
    }

    @Override
    public boolean test(Person person) {
        HousingType personHousingType = person.getHousingType();
        return personHousingType.equals(housingType);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HousingTypeMatchPredicate)) {
            return false;
        }

        HousingTypeMatchPredicate otherHousingTypeMatchPredicate = (HousingTypeMatchPredicate) other;
        return housingType.equals(otherHousingTypeMatchPredicate.housingType);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("Housing Type", housingType).toString();
    }

}
