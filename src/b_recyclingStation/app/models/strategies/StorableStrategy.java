package b_recyclingStation.app.models.strategies;

import b_recyclingStation.app.annotations.Storable;
import b_recyclingStation.app.contracts.GarbageDisposalStrategy;
import b_recyclingStation.app.contracts.ProcessingData;
import b_recyclingStation.app.contracts.Waste;
import b_recyclingStation.app.models.ProcessingDataImpl;

@Storable
public class StorableStrategy implements GarbageDisposalStrategy {

    private static final double ENERGY_PRODUCED_AND_CAPITAL_EARNED = 0.0;
    private static final double ENERGY_USED_PERCENTAGE = 0.13;
    private static final double CAPITAL_USED_PERCENTAGE = 0.65;

    @Override
    public ProcessingData processGarbage(Waste garbage) {

        Double energyProduced = ENERGY_PRODUCED_AND_CAPITAL_EARNED;
        Double energyUsed = garbage.getWeight() * garbage.getVolumePerKg() * ENERGY_USED_PERCENTAGE;
        Double capitalEarned = ENERGY_PRODUCED_AND_CAPITAL_EARNED;
        Double capitalUsed = garbage.getWeight() * garbage.getVolumePerKg() * CAPITAL_USED_PERCENTAGE;

        return new ProcessingDataImpl(energyProduced, energyUsed, capitalEarned, capitalUsed);
    }
}