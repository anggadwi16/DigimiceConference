package com.example.digimiceconferent.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Paid implements Parcelable {
    String id;
    String status;
    String name;
    String price;
    String maxParticipant;
    String paid;

    public Paid() {
    }

    protected Paid(Parcel in) {
        id = in.readString();
        status = in.readString();
        name = in.readString();
        price = in.readString();
        maxParticipant = in.readString();
        paid = in.readString();
    }

    public static final Creator<Paid> CREATOR = new Creator<Paid>() {
        @Override
        public Paid createFromParcel(Parcel in) {
            return new Paid(in);
        }

        @Override
        public Paid[] newArray(int size) {
            return new Paid[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMaxParticipant() {
        return maxParticipant;
    }

    public void setMaxParticipant(String maxParticipant) {
        this.maxParticipant = maxParticipant;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(status);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(maxParticipant);
        dest.writeString(paid);
    }
}
