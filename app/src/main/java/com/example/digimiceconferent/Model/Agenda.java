package com.example.digimiceconferent.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Agenda implements Parcelable {
    String id;
    String idSession;
    String sessionAgenda;
    String namaAgenda;
    String startAgenda;
    String endAgenda;
    String descAgenda;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    public Agenda() {
    }

    protected Agenda(Parcel in) {
        id = in.readString();
        idSession = in.readString();
        sessionAgenda = in.readString();
        namaAgenda = in.readString();
        startAgenda = in.readString();
        endAgenda = in.readString();
        descAgenda = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(idSession);
        dest.writeString(sessionAgenda);
        dest.writeString(namaAgenda);
        dest.writeString(startAgenda);
        dest.writeString(endAgenda);
        dest.writeString(descAgenda);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Agenda> CREATOR = new Creator<Agenda>() {
        @Override
        public Agenda createFromParcel(Parcel in) {
            return new Agenda(in);
        }

        @Override
        public Agenda[] newArray(int size) {
            return new Agenda[size];
        }
    };

    public String getStartAgenda() {
        return startAgenda;
    }

    public void setStartAgenda(String startAgenda) {
        this.startAgenda = startAgenda;
    }

    public String getEndAgenda() {
        return endAgenda;
    }

    public void setEndAgenda(String endAgenda) {
        this.endAgenda = endAgenda;
    }

    public String getSessionAgenda() {
        return sessionAgenda;
    }

    public void setSessionAgenda(String sessionAgenda) {
        this.sessionAgenda = sessionAgenda;
    }

    public String getNamaAgenda() {
        return namaAgenda;
    }

    public void setNamaAgenda(String namaAgenda) {
        this.namaAgenda = namaAgenda;
    }

    public String getDescAgenda() {
        return descAgenda;
    }

    public void setDescAgenda(String descAgenda) {
        this.descAgenda = descAgenda;
    }

}
