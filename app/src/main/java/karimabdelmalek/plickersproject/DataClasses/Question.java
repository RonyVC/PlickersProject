package karimabdelmalek.plickersproject.DataClasses;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by karimtalaat on 9/12/15.
 */
public class Question implements Parcelable
{
    public String id ;
    public String modified ;
    public String created;
    public String body ;
    public String image ;
    public ArrayList<Choice>choices;

    public Question(Parcel parcel) {
        this.id = parcel.readString();
        this.modified = parcel.readString();
        this.created = parcel.readString();
        this.body = parcel.readString();
        this.image = parcel.readString();
        this.choices = parcel.readArrayList(Choice.class.getClassLoader());


    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.id);
        parcel.writeString(this.modified);
        parcel.writeString(this.created);
        parcel.writeString(this.body);
        parcel.writeString(this.image);
        parcel.writeList(this.choices);

    }

    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
