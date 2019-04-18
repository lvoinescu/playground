package org.sam.playground.platefill;

import java.util.Objects;

public class Sandwich {

    private final int width;
    private final int height;
    private final SandwichType sandwichType;
    private final int number;

    public Sandwich(int width, int height, SandwichType sandwichType, int number) {
        this.width = width;
        this.height = height;
        this.sandwichType = sandwichType;
        this.number = number;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "[" + width + "," + height + "], " + sandwichType + " | " + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sandwich sandwich = (Sandwich) o;
        return width == sandwich.width &&
                height == sandwich.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}
