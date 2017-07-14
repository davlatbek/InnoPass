package innopolis.innopass.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by davlet on 7/6/17.
 */

public class Administrator implements Parcelable {
    private Long id;
    private String login;
    private String password;
    private String firstName;

    public Administrator(){

    }

    public Administrator(Long id, String login, String password, String firstName) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
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
    }

    protected Administrator(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.login = in.readString();
        this.password = in.readString();
        this.firstName = in.readString();
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
