package innopolis.innopass.entities;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by davlet on 6/17/17.
 */
public class User {
    protected String firstName;
    protected String surname;
    protected String middleName;
    protected Calendar dateOfBirth;
    protected int photoId;
    protected List<Contact> contacts;

    public User() {

    }

    public User(String firstName, String surname, String middleName,
                Calendar dateOfBirth, int photoId, List<Contact> contacts) {
        this.firstName = firstName;
        this.surname = surname;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.photoId = photoId;
        this.contacts = contacts;
    }
}
