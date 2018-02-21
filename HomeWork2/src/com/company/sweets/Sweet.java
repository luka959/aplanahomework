package com.company.sweets;

public abstract class Sweet {
    private final double weight;
    private final double cost;
    private final SweetType type;

    protected Sweet(double weight, double cost, SweetType type) {
        this.weight = weight;
        this.cost = cost;
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public double getCost() {
        return cost;
    }

    public SweetType getType() {
        return type;
    }
}
