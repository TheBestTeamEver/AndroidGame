package com.thebestteamever.game.item;


public class ListItem {
    private String userName;
    private String rating;

    public ListItem(String user, String rate) {
        this.userName = user;
        this.rating = rate;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getRating() {
        return this.rating;
    }

    public void setUserName(String user) {
        this.userName = user;
    }

    public void setRating(String rate) {
        this.rating = rate;
    }
}