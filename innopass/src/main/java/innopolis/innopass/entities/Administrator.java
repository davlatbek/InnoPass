package innopolis.innopass.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by davlet on 7/6/17.
 */

public class Administrator extends User implements Parcelable {
    private final Long id;

    public Administrator(Long id, String firstName, String surname, String middleName,
                         Calendar dateOfBirth, int photoId, List<Contact> contacts) {
        super(firstName, surname, middleName, dateOfBirth, photoId, contacts);
        this.id = id;
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
        dest.writeString(this.firstName);
        dest.writeString(this.surname);
        dest.writeString(this.middleName);
        dest.writeSerializable(this.dateOfBirth);
        dest.writeInt(this.photoId);
        dest.writeList(this.contacts);
    }

    protected Administrator(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.firstName = in.readString();
        this.surname = in.readString();
        this.middleName = in.readString();
        this.dateOfBirth = (Calendar) in.readSerializable();
        this.photoId = in.readInt();
        this.contacts = new ArrayList<Contact>();
        in.readList(this.contacts, Contact.class.getClassLoader());
    }

    public static final Parcelable.Creator<Administrator> CREATOR = new Parcelable.Creator<Administrator>() {
        @Override
        public Administrator createFromParcel(Parcel source) {
            return new Administrator(source);
        }

        @Override
        public Administrator[] newArray(int size) {
            return new Administrator[size];
        }
    };
}
