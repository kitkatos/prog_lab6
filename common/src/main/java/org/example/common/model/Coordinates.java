package org.example.common.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Coordinates implements Validatable, Serializable {
    private Double x;
    private double y;

    public Coordinates(Double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return String.format("[%s, %s]", (x != null ? x : 0), y);
    }

    @Override
    public boolean validate(){
    return x != null && x > -382;
    }
}
