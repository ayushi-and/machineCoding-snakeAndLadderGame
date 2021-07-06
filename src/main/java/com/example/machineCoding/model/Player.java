package com.example.machineCoding.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Player {
    //no setter is needed for name since name is immutable field
    private String name;
    @Setter
    private int position;
    @Setter
    private boolean won;

    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.won = false;
    }
}
