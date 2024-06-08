package com.example.eazilydone.DTO;

import com.google.gson.annotations.SerializedName;

public class LeaderBoardItem {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("score")
    private int score;

    @SerializedName("mail")
    private String mail;

    @SerializedName("timestamp")
    private String timestamp;

    public LeaderBoardItem(String id, String name, int score, String mail, String timestamp) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.mail = mail;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}