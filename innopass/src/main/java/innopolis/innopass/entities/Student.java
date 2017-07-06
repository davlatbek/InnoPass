package innopolis.innopass.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by davlet on 7/04/17.
 */
public class Student extends User implements Serializable, Parcelable {
    private final Long id;
    private String firstName;
    private String surname;
    private String middleName;
    private Date dateOfBirth;
    private Long groupId;
    private int photoId;
    private transient List<Contact> contacts;
    private transient Random random;

    public Student(String firstName, String surname, String middleName, Date dateOfBirth, Long groupId) {
        random = new Random();
        this.firstName = firstName;
        this.surname = surname;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.groupId = groupId;
        this.id = System.currentTimeMillis() + random.nextInt();
    }

    public Student(String firstName, String surname, String middleName, Date dateOfBirth, Long groupId, List<Contact> contacts) {
        random = new Random();
        this.id = System.currentTimeMillis() + random.nextInt();
        this.firstName = firstName;
        this.surname = surname;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.groupId = groupId;
        this.contacts = contacts;
    }

    public Student(String firstName, String surname, String middleName, Date dateOfBirth, Long groupId, int photoId, List<Contact> contacts) {
        random = new Random();
        this.id = System.currentTimeMillis() + random.nextInt();
        this.firstName = firstName;
        this.surname = surname;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.groupId = groupId;
        this.photoId = photoId;
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Student)) return false;
        if (!(this.getId().equals(((Student) o).getId()))) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstName);
        dest.writeString(this.surname);
        dest.writeString(this.middleName);
        dest.writeLong(this.dateOfBirth != null ? this.dateOfBirth.getTime() : -1);
        dest.writeValue(this.id);
        dest.writeValue(this.groupId);
    }

    protected Student(Parcel in) {
        this.firstName = in.readString();
        this.surname = in.readString();
        this.middleName = in.readString();
        long tmpDateOfBirth = in.readLong();
        this.dateOfBirth = tmpDateOfBirth == -1 ? null : new Date(tmpDateOfBirth);
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.groupId = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
