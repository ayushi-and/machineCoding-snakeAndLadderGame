package com.example.machineCoding.model;

import lombok.Getter;

@Getter
//No setter is needed since all are immutable fields in this class.
public class Board {
    private int size;
    private int start;
    private int end;

    public Board(int size) {
        this.start = 1;
        this.end = start + size - 1;
        this.size = size;
    }
}
