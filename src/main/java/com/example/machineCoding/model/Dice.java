package com.example.machineCoding.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@AllArgsConstructor
//No need for Setter since all fields are immutable so no need to set the values.
public class Dice {
    private int minValue;
    private int maxValue;
    private int currValue;

    public int roll() {
        //minValue included : 1
        //maxValue excluded, so maxValue + 1 : 7
        return RandomUtils.nextInt(minValue, maxValue + 1); //dice number from 1 to 6 (included)
        //return ThreadLocalRandom.current().nextInt(minValue, maxValue+1);
    }
}
