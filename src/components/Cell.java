package components;

import java.util.Random;

public class Cell {
    private int value;

    public Cell (int value) {
        this.value = value;
    }

    public Cell() {
        Random rand = new Random();
        this.value = rand.nextInt(2);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int newValue) {
        this.value = newValue;
    }
}
