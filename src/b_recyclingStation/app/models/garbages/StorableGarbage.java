package b_recyclingStation.app.models.garbages;

import b_recyclingStation.app.abstract_classes.Garbage;
import b_recyclingStation.app.annotations.Storable;

@Storable
public class StorableGarbage extends Garbage {

    public StorableGarbage(String name, Double weight, Double volumePerKg) {
        super(name, weight, volumePerKg);
    }
}
