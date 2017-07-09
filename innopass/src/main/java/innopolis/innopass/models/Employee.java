package innopolis.innopass.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by davlet on 7/5/17.
 */

public class Employee extends User implements Parcelable {
    private final Long id;
    private String companyName;
    private String position;
    private transient Random random;

    public Employee(Long id, String companyName, String position, Random random) {
        this.random = new Random();
        this.id = id;
        this.companyName = companyName;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.companyName);
        dest.writeString(this.position);
        dest.writeString(this.firstName);
        dest.writeString(this.surname);
        dest.writeString(this.middleName);
        dest.writeSerializable(this.dateOfBirth);
        dest.writeInt(this.photoId);
        dest.writeList(this.contacts);
    }

    protected Employee(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.companyName = in.readString();
        this.position = in.readString();
        this.firstName = in.readString();
        this.surname = in.readString();
        this.middleName = in.readString();
        this.dateOfBirth = (Calendar) in.readSerializable();
        this.photoId = in.readInt();
        this.contacts = new ArrayList<Contact>();
        in.readList(this.contacts, Contact.class.getClassLoader());
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel source) {
            return new Employee(source);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };
}
