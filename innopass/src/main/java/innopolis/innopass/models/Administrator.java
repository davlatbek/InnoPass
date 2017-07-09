package innopolis.innopass.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by davlet on 7/6/17.
 */

public class Administrator extends User implements Parcelable {

    public Administrator(){

    }

    public Administrator(Long id, String login, String password, String firstName, String surname, String middleName,
                         Calendar dateOfBirth, int photoId, List<Contact> contacts) {
        super(id, login, password, firstName, surname, middleName, dateOfBirth, photoId, contacts);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
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

    protected Administrator(Parcel in) {
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

    public static final Creator<Administrator> CREATOR = new Creator<Administrator>() {
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
