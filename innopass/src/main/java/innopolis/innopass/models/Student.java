package innopolis.innopass.models;

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
    private final Long id;
    private Long groupId;
    private transient Random random;

    public Student(Long id, String firstName, String surname, String middleName,
                   Calendar dateOfBirth, Long groupId, int photoId, List<Contact> contacts) {
        super(firstName, surname, middleName, dateOfBirth, photoId, contacts);
        random = new Random();
        this.id = System.currentTimeMillis() + random.nextInt();
        this.groupId = groupId;
    }

    public Student(String firstName, String surname, String middleName,
                   Calendar dateOfBirth, Long groupId, int photoId, List<Contact> contacts) {
        super(firstName, surname, middleName, dateOfBirth, photoId, contacts);
        random = new Random();
        this.id = System.currentTimeMillis() + random.nextInt();
        this.groupId = groupId;
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
        dest.writeValue(this.id);
        dest.writeValue(this.groupId);
        dest.writeString(this.firstName);
        dest.writeString(this.surname);
        dest.writeString(this.middleName);
        dest.writeSerializable(this.dateOfBirth);
        dest.writeInt(this.photoId);
        dest.writeList(this.contacts);
    }

    protected Student(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.groupId = (Long) in.readValue(Long.class.getClassLoader());
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
