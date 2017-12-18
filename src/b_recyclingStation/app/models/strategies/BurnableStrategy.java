package b_recyclingStation.app.models.strategies;

import b_recyclingStation.app.annotations.Burnable;
import b_recyclingStation.app.contracts.GarbageDisposalStrategy;
import b_recyclingStation.app.contracts.ProcessingData;
import b_recyclingStation.app.contracts.Waste;
import b_recyclingStation.app.models.ProcessingDataImpl;

@Burnable
public class BurnableStrategy implements GarbageDisposalStrategy {

    private static final double ENERGY_USED_PERCENTAGE = 0.2;
    private static final double CAPITAL_EARNED_AND_USED = 0.0;

    @Override
    public ProcessingData processGarbage(Waste garbage) {

        Double energyProduced = garbage.getWeight() * garbage.getVolumePerKg();
        Double energyUsed = energyProduced * ENERGY_USED_PERCENTAGE;
        Double capitalEarned = CAPITAL_EARNED_AND_USED;
        Double capitalUsed = CAPITAL_EARNED_AND_USED;

        return new ProcessingDataImpl(energyProduced, energyUsed, capitalEarned, capitalUsed);
    }
}