package com.company.sweets;

public class Candy extends Sweet {
    private final int sweetness;

    public Candy() {
        super(3.3, 4.3, SweetType.CANDY);
        this.sweetness = 100;
    }

    public int getSweetness() {
        return sweetness;
    }
}
