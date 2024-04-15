package seedu.realodex.logic.parser;

import static seedu.realodex.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.realodex.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.realodex.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.realodex.testutil.Assert.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.realodex.logic.Messages;
import seedu.realodex.logic.commands.FilterCommand;
import seedu.realodex.model.person.Birthday;
import seedu.realodex.model.person.HousingType;
import seedu.realodex.model.person.Name;
import seedu.realodex.model.person.Tag;
import seedu.realodex.model.person.predicates.BirthdayIsInMonthPredicate;
import seedu.realodex.model.person.predicates.HousingTypeMatchPredicate;
import seedu.realodex.model.person.predicates.NameContainsKeyphrasePredicate;
import seedu.realodex.model.person.predicates.RemarkContainsKeyphrasePredicate;
import seedu.realodex.model.person.predicates.TagsMatchPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    // EP: Parsing an empty argument should result in a parse failure with the appropriate error message
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    // EP: Parsing valid arguments with a name prefix should return a
    // FilterCommand with a NameContainsKeyphrasePredicate
    @Test
    void parse_validArgsWithName_returnsFilterCommand() {
        String userInput = " n/Alice";
        FilterCommand expectedCommand = new FilterCommand(new NameContainsKeyphrasePredicate("Alice"));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    // EP: Parsing invalid arguments with a name prefix
    // should throw a parse exception with the appropriate error message
    @Test
    void parse_invalidArgsWithName_throwsParseException() {
        assertParseFailure(parser, " n/#$@%^", Name.MESSAGE_CONSTRAINTS);
    }

    // EP: Parsing empty arguments with a name prefix
    // should throw a parse exception with the appropriate error message
    @Test
    void parse_emptyArgsWithName_throwsParseException() {
        assertParseFailure(parser, " n/", Name.MESSAGE_CONSTRAINTS);
    }

    // EP: Parsing valid arguments with a remark prefix
    // should return a FilterCommand with a RemarkContainsKeyphrasePredicate
    @Test
    void parse_validArgsWithRemark_returnsFilterCommand() {
        String userInput = " r/Loves cats";
        FilterCommand expectedCommand = new FilterCommand(new RemarkContainsKeyphrasePredicate("Loves cats"));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    // EP: Parsing invalid arguments with an empty remark prefix
    // should throw a parse exception with the appropriate error message
    @Test
    void parse_invalidArgsWithEmptyRemark_throwsParseException() {
        assertParseFailure(parser, " r/", FilterCommand.MESSAGE_FILTER_EMPTY_REMARK);
    }

    // EP: Parsing valid arguments with a tag prefix should
    // return a FilterCommand with a TagsMatchPredicate
    @Test
    void parse_validArgsWithTag_returnsFilterCommand() {
        String userInput = " t/buyer";
        Set<Tag> predicateTags = Set.of(new Tag("buyer"));
        FilterCommand expectedCommand = new FilterCommand(new TagsMatchPredicate(predicateTags));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    // EP: Parsing invalid arguments with a tag prefix
    // should throw a parse exception with the appropriate error message
    @Test
    void parse_invalidArgsWithTag_throwsParseException() {
        String userInput = " t/customer";
        assertParseFailure(parser, userInput, Tag.MESSAGE_CONSTRAINTS);
    }

    // EP: Parsing valid arguments with two tag prefixes
    // should return a FilterCommand with a TagsMatchPredicate
    @Test
    void parse_validArgsWithTwoTags_returnsFilterCommand() {
        String userInput = " t/Buyer t/SELLER";
        Set<Tag> predicateTags = Set.of(new Tag("buyer"), new Tag("seller"));
        FilterCommand expectedCommand = new FilterCommand(new TagsMatchPredicate(predicateTags));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    // EP: Parsing one valid tag and one invalid tag
    // should throw a parse exception with the appropriate error message
    @Test
    void parse_oneValidOneInvalidTag_throwsParseException() {
        String userInput = " t/Buyer t/customer";
        assertParseFailure(parser, userInput, Tag.MESSAGE_CONSTRAINTS);
    }
    // EP: Parsing valid arguments with three tag prefixes
    // should throw a parse exception with the appropriate error message
    @Test
    void parse_validArgsWithThreeTags_throwsParseException() {
        String userInput = " t/Buyer t/Seller t/Buyer";
        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TAG));
    }

    // EP: Parsing invalid tag format
    // should throw IllegalArgumentException
    @Test
    void parse_invalidTagFormat_throwsParseException() {
        String userInput = " t/Buyer$";
        assertThrows(IllegalArgumentException.class, () -> new Tag(userInput));
    }

    // EP: Parsing empty arguments with a tag prefix
    // should throw a parse exception with the appropriate error message
    @Test
    void parse_emptyArgsWithTag_throwsParseException() {
        assertParseFailure(parser, " t/", Tag.MESSAGE_CONSTRAINTS);
    }

    // EP: Parsing invalid birth month with birthday
    // should throw a parse exception with the appropriate error message
    @Test
    void parse_invalidBirthMonthWithBirthday_throwsParseException() {
        assertParseFailure(parser, " b/#$@%^", Birthday.FILTER_MONTH_MESSAGE_CONSTRAINTS);
    }

    // EP: Parsing valid birth month with birthday
    // should return a FilterCommand with a BirthdayIsInMonthPredicate
    @Test
    void parse_validBirthMonthWithBirthday_returnsFilterCommand() {
        String userInput = " b/June";
        FilterCommand expectedCommand = new FilterCommand(new BirthdayIsInMonthPredicate("June"));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    // EP: Parsing empty arguments with a birthday prefix
    // should throw a parse exception with the appropriate error message
    @Test
    void parse_emptyArgsWithBirthday_throwsParseException() {
        assertParseFailure(parser, " b/", Birthday.FILTER_MONTH_MESSAGE_CONSTRAINTS);
    }

    // EP: Parsing valid arguments with a housing type prefix
    // should return a FilterCommand with a HousingTypeMatchPredicate
    @Test
    void parse_validArgsWithHousingType_returnsFilterCommand() {
        String userInput = " h/hdb";
        HousingType housingType = new HousingType("hdb");
        FilterCommand expectedCommand = new FilterCommand(new HousingTypeMatchPredicate(housingType));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    // EP: Parsing invalid arguments with a housing type prefix
    // should throw a parse exception with the appropriate error message
    @Test
    void parse_invalidArgsWithHousingType_throwsParseException() {
        String userInput = " h/hdbb";
        assertParseFailure(parser, userInput, HousingType.MESSAGE_CONSTRAINTS);
    }

    // EP: Parsing empty arguments with a housing type prefix
    // should throw a parse exception with the appropriate error message
    @Test
    void parse_emptyArgsWithHousingType_throwsParseException() {
        String userInput = " h/";
        assertParseFailure(parser, userInput, HousingType.MESSAGE_CONSTRAINTS);
    }

    // EP: Parsing invalid arguments should throw a parse exception
    // with the appropriate error message
    @Test
    void parse_invalidArgs_throwsParseException() {
        String userInput = " invalidArg";
        assertParseFailure(parser, userInput,
                           String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    // EP: Parsing with an invalid prefix should throw a
    // parse exception with the appropriate error message
    @Test
    void parse_invalidPrefix_throwsParseException() {
        String userInput = " p/999";
        assertParseFailure(parser, userInput,
                           String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    // EP: Parsing with multiple prefixes should throw a
    // parse exception with the appropriate error message
    @Test
    void parse_multiplePrefixes_throwsParseException() {
        String userInput = " n/Alice r/Loves cats";
        assertParseFailure(parser, userInput,
                           String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_FILTER_CONFLICT));
    }

    // EP: Parsing with multiple prefixes and an empty
    // remark should throw a parse exception with the appropriate error message
    @Test
    void parse_multiplePrefixesWithEmptyRemark_throwsParseException() {
        String userInput = " n/Alice r/";
        assertParseFailure(parser, userInput,
                           String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_FILTER_CONFLICT));
    }

    // EP: Parsing with multiple prefixes and an empty
    // name should throw a parse exception with the appropriate error message
    @Test
    void parse_multiplePrefixesWithEmptyName_throwsParseException() {
        String userInput = " n/ r/Loves cats";
        assertParseFailure(parser, userInput,
                           String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_FILTER_CONFLICT));
    }
    // EP: Parsing with duplicate prefixes should throw a
    // parse exception with the appropriate error message
    @Test
    void parse_duplicatePrefixes_throwsParseException() {
        String userInput = " n/Alice n/Bob";
        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
    }

    // EP: Parsing with an empty preamble should throw a
    // parse exception with the appropriate error message
    @Test
    void parse_emptyPreamble_throwsParseException() {
        String userInput = " yapyap n/Alice";
        assertParseFailure(parser, userInput,
                           String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }
}
