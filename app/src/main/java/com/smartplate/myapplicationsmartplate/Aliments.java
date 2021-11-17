package com.smartplate.myapplicationsmartplate;

import android.os.Parcel;
import android.os.Parcelable;

public class Aliments implements Parcelable {
    private String name;
    private int imageID;
    private int id;

    public Aliments(String name, int imageID, int id) {
        this.name = name;
        this.imageID = imageID;
        this.id = id;
    }

    protected Aliments(Parcel in) {
        name = in.readString();
        imageID = in.readInt();
        id = in.readInt();
    }

    public static final Creator<Aliments> CREATOR = new Creator<Aliments>() {
        @Override
        public Aliments createFromParcel(Parcel in) {
            return new Aliments(in);
        }

        @Override
        public Aliments[] newArray(int size) {
            return new Aliments[size];
        }
    };

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(imageID);
        dest.writeInt(id);
    }
}
