package b_recyclingStation.app.models.garbages;

import b_recyclingStation.app.abstract_classes.Garbage;
import b_recyclingStation.app.annotations.Recycable;

@Recycable
public class RecyclableGarbage extends Garbage {

    public RecyclableGarbage(String name, Double weight, Double volumePerKg) {
        super(name, weight, volumePerKg);
    }
}
