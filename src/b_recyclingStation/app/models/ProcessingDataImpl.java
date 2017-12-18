package b_recyclingStation.app.models;

import b_recyclingStation.app.contracts.ProcessingData;

public class ProcessingDataImpl implements ProcessingData{

    private Double energyProduced;
    private Double energyUsed;
    private Double capitalEarned;
    private Double capitalUsed;

    public ProcessingDataImpl(Double energyProduced, Double energyUsed, Double capitalEarned, Double capitalUsed) {
        this.energyProduced = energyProduced;
        this.energyUsed = energyUsed;
        this.capitalEarned = capitalEarned;
        this.capitalUsed = capitalUsed;
    }

    @Override
    public double getEnergyBalance() {
        return this.energyProduced - this.energyUsed;
    }

    @Override
    public double getCapitalBalance() {
        return this.capitalEarned - this.capitalUsed;
    }
}