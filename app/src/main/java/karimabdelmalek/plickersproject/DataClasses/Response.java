package karimabdelmalek.plickersproject.DataClasses;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by karimtalaat on 9/13/15.
 */
public class Response implements Parcelable
{
    public String student ;
    public String answer ;
    public int card ;

    public Response(Parcel parcel) {
        this.student = parcel.readString();
        this.answer = parcel.readString();
        this.card = parcel.readInt();


    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.student);
        parcel.writeString(this.answer);
        parcel.writeInt(this.card);


    }

    public static final Parcelable.Creator<Response> CREATOR = new Parcelable.Creator<Response>() {
        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        public Response[] newArray(int size) {
            return new Response[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
