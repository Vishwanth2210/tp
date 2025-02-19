package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Pane flag;
    @FXML
    private FlowPane tags;
    @FXML
    private Label prevDateMet;
    @FXML
    private Label info;
    @FXML
    private Label scheduledMeeting;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText("Phone Number: " + person.getPhone().value);
        address.setText("Address: " + person.getAddress().value);
        email.setText("Email: " + person.getEmail().value);
        if (person.getFlag().isFlagged) {
            flag.setStyle("-fx-background-color: #c94848");
        }
        prevDateMet.setText("Date last met:\n" + person.getPrevDateMet().value.toString());
        info.setText("Info: " + person.getInfo().value);

        String meetingDetails = person.getScheduledMeeting().toString();
        if (meetingDetails.equals("No meeting scheduled")) {
            scheduledMeeting.setText(meetingDetails);
        } else {
            String[] meetingSplit = person.getScheduledMeeting().toString().split(" ");
            String meetingDate = meetingSplit[0];
            String meetingTime = meetingSplit[1];
            scheduledMeeting.setText("Upcoming meeting:\n" + meetingDate + " at " + meetingTime);
        }

        tags.getChildren().add(new Label("Salary: $" + person.getSalary().value));
        tags.getChildren().get(0).setStyle("-fx-background-color: #6bd16b;"
                + "-fx-hgap: 7;"
                + "-fx-vgap: 3;");
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
