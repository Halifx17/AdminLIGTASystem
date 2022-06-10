package com.example.adminligtasystem;

public class Announcement {

    String title, who, what, when, by;

    public Announcement(String title, String who, String what, String when, String by) {
        this.title = title;
        this.who = who;
        this.what = what;
        this.when = when;
        this.by = by;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Announcement(){

    }
}
