package seedu.realodex.model.person.predicates;

import java.util.function.Predicate;

import seedu.realodex.commons.util.ToStringBuilder;
import seedu.realodex.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Name} contains the keyphrase given.
 */
public class NameContainsKeyphrasePredicate implements Predicate<Person> {
    private final String keyphrase;

    public NameContainsKeyphrasePredicate(String keyphrase) {
        this.keyphrase = keyphrase;
    }

    @Override
    public boolean test(Person person) {
        String nameInLowerCase = person.getName().fullName.toLowerCase();
        String keyphraseInLowerCase = keyphrase.toLowerCase();
        return nameInLowerCase.contains(keyphraseInLowerCase);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsKeyphrasePredicate)) {
            return false;
        }

        NameContainsKeyphrasePredicate otherNameContainsKeyphrasePredicate = (NameContainsKeyphrasePredicate) other;
        return keyphrase.equals(otherNameContainsKeyphrasePredicate.keyphrase);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyphrase", keyphrase).toString();
    }

}
