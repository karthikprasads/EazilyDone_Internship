package com.example.eazilydone;

public class Result {
    private String rank;
    private String name;
    private int points;

    public Result(String rank, String name, int points) {
        this.rank = rank;
        this.name = name;
        this.points = points;
    }
    public String getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }
}
