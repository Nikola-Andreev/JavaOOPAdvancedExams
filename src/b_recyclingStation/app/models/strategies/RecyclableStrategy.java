package b_recyclingStation.app.models.strategies;

import b_recyclingStation.app.annotations.Recycable;
import b_recyclingStation.app.contracts.GarbageDisposalStrategy;
import b_recyclingStation.app.contracts.ProcessingData;
import b_recyclingStation.app.contracts.Waste;
import b_recyclingStation.app.models.ProcessingDataImpl;

@Recycable
public class RecyclableStrategy implements GarbageDisposalStrategy {

    private static final double ENERGY_PRODUCED_AND_CAPITAL_USED = 0.0;
    private static final int CAPITAL_EARNED_MULTIPLIER = 400;
    private static final double ENERGY_USED_PERCENT = 0.5;

    @Override
    public ProcessingData processGarbage(Waste garbage) {

        Double energyProduced = ENERGY_PRODUCED_AND_CAPITAL_USED;
        Double energyUsed = garbage.getWeight() * garbage.getVolumePerKg() * ENERGY_USED_PERCENT;
        Double capitalEarned = CAPITAL_EARNED_MULTIPLIER * garbage.getWeight();
        Double capitalUsed = ENERGY_PRODUCED_AND_CAPITAL_USED;

        return new ProcessingDataImpl(energyProduced, energyUsed, capitalEarned, capitalUsed);
    }
}