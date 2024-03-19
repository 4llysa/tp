package seedu.realodex.model.remark;

/**
 * Represents a remark in the address book.
 */
public class Remark {

    public static final String MESSAGE_CONSTRAINTS = "Remarks should be non-empty";

    public final String remarkName;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remarkName A valid remark.
     */
    public Remark(String remarkName) {
        this.remarkName = remarkName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Remark)) {
            return false;
        }

        Remark otherRemark = (Remark) other;
        return remarkName.equals(otherRemark.remarkName);
    }

    @Override
    public int hashCode() {
        return remarkName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return remarkName;
    }

    /**
     * Format state as text for representation.
     */
    public String toStringWithRepresentation() {
        if (remarkName.isBlank()) {
            return "No remark.";
        } else {
            return "Remark: " + remarkName;
        }
    }

}
