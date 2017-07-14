package innopolis.innopass.models.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by davlet on 7/04/17.
 */
public class Student extends User implements Serializable, Parcelable {
    private Long groupId;
    private transient Random random;

    public Student(){

    }

    public Student(final Long id, String login, String password, String firstName,
                   String surname, String middleName, Calendar dateOfBirth,
                   Long groupId, int photoId, List<Contact> contacts) {
        super(id, login, password, firstName, surname, middleName, dateOfBirth, photoId, contacts);
        random = new Random();
        this.groupId = groupId;
    }

    public Student(String login, String password, String firstname, String surname, String middleName, Calendar dob, int photoId) {
        super(login, password, firstname, surname, middleName, dob, photoId);
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        if (!groupId.equals(student.groupId)) return false;
        return random.equals(student.random);

    }

    @Override
    public int hashCode() {
        int result = groupId.hashCode();
        result = 31 * result + random.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.groupId);
        dest.writeValue(this.id);
        dest.writeString(this.login);
        dest.writeString(this.password);
        dest.writeString(this.firstName);
        dest.writeString(this.surname);
        dest.writeString(this.middleName);
        dest.writeSerializable(this.dateOfBirth);
        dest.writeInt(this.photoId);
        dest.writeList(this.contacts);
    }

    protected Student(Parcel in) {
        this.groupId = (Long) in.readValue(Long.class.getClassLoader());
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.login = in.readString();
        this.password = in.readString();
        this.firstName = in.readString();
        this.surname = in.readString();
        this.middleName = in.readString();
        this.dateOfBirth = (Calendar) in.readSerializable();
        this.photoId = in.readInt();
        this.contacts = new ArrayList<Contact>();
        in.readList(this.contacts, Contact.class.getClassLoader());
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
