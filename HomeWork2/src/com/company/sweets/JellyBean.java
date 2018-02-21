package com.company.sweets;

public class JellyBean extends Sweet {
    private final String color;

    public JellyBean() {
        super(13.0, 56, SweetType.JELLY_BEAN);
        this.color = "red";
    }

    public String getColor() {
        return color;
    }
}
