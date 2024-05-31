package com.example.eazilydone.data;

import android.content.Context;
import android.content.SharedPreferences;

public class playerData {
    private static playerData instance;

    private boolean firstDialogue;
    private int moneyAmount;

    private playerData(boolean firstDialogue, int moneyAmount) {
        this.firstDialogue = firstDialogue;
        this.moneyAmount = moneyAmount;
    }
    public static synchronized playerData getInstance(Context context) {
        if (instance == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("PlayerData", Context.MODE_PRIVATE);
            boolean firstDialogue = sharedPreferences.getBoolean("firstDialogue", false);
            int moneyAmount = sharedPreferences.getInt("moneyAmount", 0);
            instance = new playerData(firstDialogue, moneyAmount);
        }
        return instance;
    }
    public void saveData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PlayerData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("firstDialogue", firstDialogue);
        editor.putInt("moneyAmount", moneyAmount);
        editor.apply();
    }

    public boolean getfirstDialogue() {
        return firstDialogue;
    }

    public void setfirstDialogue(boolean firstDialogue) {
        this.firstDialogue = firstDialogue;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

}
