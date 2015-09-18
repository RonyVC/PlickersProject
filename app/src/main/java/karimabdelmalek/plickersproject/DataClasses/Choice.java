package karimabdelmalek.plickersproject.DataClasses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by karimtalaat on 9/13/15.
 */
public class Choice implements Parcelable
{
    public String body;
    public boolean correct;

    public Choice(Parcel parcel) {
        this.body = parcel.readString();
        this.correct = parcel.readByte() != 0;

    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.body);
        parcel.writeByte((byte) (this.correct ? 1 : 0));     //if myBoolean == true, byte == 1
    }

    public static final Parcelable.Creator<Choice> CREATOR = new Parcelable.Creator<Choice>() {
        public Choice createFromParcel(Parcel in) {
            return new Choice(in);
        }

        public Choice[] newArray(int size) {
            return new Choice[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

}
