package karimabdelmalek.plickersproject.DataClasses;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by karimtalaat on 9/13/15.
 */
public class PollResponse implements Parcelable {
    public String modified;
    public String created;
    public String section;
    public Question question;
    public ArrayList<Response> responses;
    public String id;

    public PollResponse(Parcel parcel) {
        this.modified = parcel.readString();
        this.created = parcel.readString();
        this.section = parcel.readString();
        this.question = parcel.readParcelable(Question.class.getClassLoader());
        this.responses = parcel.readArrayList(Response.class.getClassLoader());
        this.id = parcel.readString();

    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.modified);
        parcel.writeString(this.created);
        parcel.writeString(this.section);
        parcel.writeParcelable(this.question,flags);
        parcel.writeList(this.responses);
        parcel.writeString(this.id);

    }

    public static final Parcelable.Creator<PollResponse> CREATOR = new Parcelable.Creator<PollResponse>() {
        public PollResponse createFromParcel(Parcel in) {
            return new PollResponse(in);
        }

        public PollResponse[] newArray(int size) {
            return new PollResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}

