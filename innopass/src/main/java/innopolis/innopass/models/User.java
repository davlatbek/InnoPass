package innopolis.innopass.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by davlet on 6/17/17.
 */
public class User {
    protected Long id;
    protected String login;
    protected String password;
    protected String firstName;
    protected String surname;
    protected String middleName;
    protected Calendar dateOfBirth;
    protected int photoId;
    protected List<Contact> contacts;

    protected User() {

    }

    protected User(final Long id, String login, String password, String firstName, String surname, String middleName,
                Calendar dateOfBirth, int photoId, List<Contact> contacts) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.photoId = photoId;
        this.contacts = contacts;
    }

    public User(String login, String password, String firstName, String surname, String middleName, Calendar dateOfBirth, int photoId, List<Contact> contacts) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.photoId = photoId;
        this.contacts = contacts;
    }

    public User(String login, String password, String firstName, String surname, String middleName, Calendar dateOfBirth, int photoId) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.photoId = photoId;
    }

    public User(String login, String password, String firstName, String surname, String middleName, SimpleDateFormat dateOfBirth, int photoId, List<Contact> contacts) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        this.middleName = middleName;
//        this.dateOfBirth = this.convert(dateOfBirth);
        Calendar calendar = Calendar.getInstance();
        calendar.set(0,0,0);
        this.photoId = photoId;
        this.contacts = contacts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
