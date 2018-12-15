package com.example.noie.exitpoll.model;

import java.util.Locale;

public class VoteItem {
    public final long _id;
    public final String name;
    public final String score;
    public final String image; //final คือ ห้ามใครแก้แล้วเด้อ

    public VoteItem(long _id, String name, String score , String image) {
        this._id = _id;
        this.name = name;
        this.score = score;
        this.image = image;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(),
                "NAME: %s\nID: %s",
                this.name,
                this._id);
    }
}
