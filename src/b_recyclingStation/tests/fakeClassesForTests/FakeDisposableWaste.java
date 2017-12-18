package b_recyclingStation.tests.fakeClassesForTests;

import b_recyclingStation.app.contracts.Waste;

@FakeDisposable
public class FakeDisposableWaste implements Waste {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public double getVolumePerKg() {
        return 0;
    }

    @Override
    public double getWeight() {
        return 0;
    }
}
