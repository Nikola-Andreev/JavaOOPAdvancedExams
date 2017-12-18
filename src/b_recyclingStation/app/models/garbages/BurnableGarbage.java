package b_recyclingStation.app.models.garbages;

import b_recyclingStation.app.abstract_classes.Garbage;
import b_recyclingStation.app.annotations.Burnable;

@Burnable
public class BurnableGarbage extends Garbage {

    public BurnableGarbage(String name, Double weight, Double volumePerKg) {
        super(name, weight, volumePerKg);
    }
}
