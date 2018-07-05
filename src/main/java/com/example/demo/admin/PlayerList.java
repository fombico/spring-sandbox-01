package com.example.demo.admin;

import java.util.List;

public class PlayerList {

    private List<String> players;

    public PlayerList(List<String> players) {
        this.players = players;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }
}
