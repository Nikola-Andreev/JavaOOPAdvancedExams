package b_recyclingStation.app.abstract_classes;

import b_recyclingStation.app.contracts.Waste;

public abstract class Garbage implements Waste{

    private String name;
    private Double weight;
    private Double volumePerKg;

    protected Garbage(String name, Double weight, Double volumePerKg) {
        this.name = name;
        this.weight = weight;
        this.volumePerKg = volumePerKg;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getVolumePerKg() {
        return this.volumePerKg;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }
}