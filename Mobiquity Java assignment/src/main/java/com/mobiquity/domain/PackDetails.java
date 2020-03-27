package com.mobiquity.domain;

public class PackDetails implements Comparable<PackDetails>{

    private final int id;
    private final double weight;
    private final double price;

    public PackDetails(int id, double weight, double price){
        this.id = id;
        this.weight = weight;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public int compareTo(PackDetails o) {
        return id - o.id;
    }
}
