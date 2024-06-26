package seedu.realodex.model.person;

import static seedu.realodex.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.realodex.commons.util.ToStringBuilder;

/**
 * Represents a Person in the Realodex.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Income income;
    private final Email email;
    private final Address address;
    private final Family family;
    private final Set<Tag> tags = new HashSet<>();
    private final HousingType housingType;
    private final Remark remark;
    private final Birthday birthday;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Income income, Email email, Address address,
                  Family family, Set<Tag> tags, HousingType housingType, Remark remark, Birthday birthday) {
        requireAllNonNull(name, phone, income, email, address, family, tags, housingType, remark);
        this.name = name;
        this.phone = phone;
        this.income = income;
        this.email = email;
        this.address = address;
        this.family = family;
        this.tags.addAll(tags);
        this.housingType = housingType;
        this.remark = remark;
        this.birthday = birthday;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Income getIncome() {
        return income;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Family getFamily() {
        return family;
    }
    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns the housing type of the person.
     */
    public HousingType getHousingType() {
        return housingType;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && income.equals(otherPerson.income)
                && family.equals(otherPerson.family)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags)
                && housingType.equals(otherPerson.housingType)
                && remark.equals(otherPerson.remark)
                && birthday.equals(otherPerson.birthday);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, income, email, address, family, tags, housingType, remark);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("income", income)
                .add("email", email)
                .add("address", address)
                .add("family", family)
                .add("tags", tags)
                .add("housingType", housingType)
                .add("remark", remark)
                .add("birthday", birthday)
                .toString();
    }

    public Birthday getBirthday() {
        return birthday;
    }
}

