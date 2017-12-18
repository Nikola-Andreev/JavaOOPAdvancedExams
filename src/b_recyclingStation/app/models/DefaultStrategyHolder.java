package b_recyclingStation.app.models;

import b_recyclingStation.app.contracts.GarbageDisposalStrategy;
import b_recyclingStation.app.contracts.StrategyHolder;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultStrategyHolder implements StrategyHolder {

    private Map<Class,GarbageDisposalStrategy> strategies;

    public DefaultStrategyHolder(){
        this.strategies = new LinkedHashMap<>();
    }

    @Override
    public Map<Class, GarbageDisposalStrategy> getDisposalStrategies() {
        return Collections.unmodifiableMap(this.strategies);
    }

    @Override
    public boolean addStrategy(Class annotationClass, GarbageDisposalStrategy strategy) {
        int sizeBefore = this.strategies.size();
        this.strategies.put(annotationClass,strategy);
        return sizeBefore < this.strategies.size();
    }

    @Override
    public boolean removeStrategy(Class annotationClass) {
        int sizeBeforeRemove = this.strategies.size();
        this.strategies.remove(annotationClass);
        return sizeBeforeRemove > this.strategies.size();
    }
}
