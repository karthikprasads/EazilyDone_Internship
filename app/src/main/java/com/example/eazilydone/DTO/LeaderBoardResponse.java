package com.example.eazilydone.DTO;
import com.example.eazilydone.DTO.LeaderBoardItem;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class LeaderBoardResponse {
    @SerializedName("lb")
    private List<LeaderBoardItem> lb;

    @SerializedName("msg")
    private String msg;

    public List<LeaderBoardItem> getLb() {
        return lb;
    }

    public void setLb(List<LeaderBoardItem> lb) {
        this.lb = lb;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}