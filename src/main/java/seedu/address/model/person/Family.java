package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents the family size of a person in the address book.
 * Guarantees: family size is present and not null, and adheres to specific constraints.
 */
public class Family {

    /** Message for constraints on family size. */
    public static final String MESSAGE_CONSTRAINTS = "Family size should be at least one";

    /** The family size. */
    private Integer familySize;

    /**
     * Constructs a {@code Family} instance with the given family size including him or herself.
     *
     * @param familySize The size of the family.
     */
    public Family(String familySize) {
        requireNonNull(familySize);
        this.familySize = Integer.valueOf(familySize);
    }

    /**
     * Checks if the given family size is a valid value.
     *
     * @param familySize The family size to check.
     * @return True if the family size is greater than or equal to zero, false otherwise.
     */
    public static boolean isValidFamily(String familySize) {
        return Integer.parseInt(familySize) > 0;
    }

    /**
     * Returns the string representation of the family size.
     *
     * @return The string representation of the family size.
     */
    @Override
    public String toString() {
        return familySize.toString();
    }

    /**
     * Returns a string representation of the family size with additional descriptive text.
     *
     * @return A string representation with descriptive text.
     */
    public String toStringWithRepresentation() {
        return "Family size is " + familySize.toString();
    }

    /**
     * Checks if this {@code Family} instance is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true; // short circuit if same object
        }
        if (!(other instanceof Family)) {
            return false; // instanceof handles nulls
        }
        Family otherFamily = (Family) other;
        return familySize.equals(otherFamily.familySize); // state check
    }
}
