package b_recyclingStation.tests;

import b_recyclingStation.app.contracts.GarbageDisposalStrategy;
import b_recyclingStation.app.contracts.GarbageProcessor;
import b_recyclingStation.app.contracts.ProcessingData;
import b_recyclingStation.app.contracts.StrategyHolder;
import b_recyclingStation.app.models.DefaultGarbageProcessor;
import b_recyclingStation.tests.fakeClassesForTests.FakeDisposable;
import b_recyclingStation.tests.fakeClassesForTests.FakeDisposableWaste;
import b_recyclingStation.tests.fakeClassesForTests.FakeNonDisposableWasteWithoutAnnotation;
import b_recyclingStation.tests.fakeClassesForTests.FakeNonDisposableWasteWithAnotation;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.Mockito;

import java.util.LinkedHashMap;
import java.util.Map;

public class GarbageProcessorTests {

    private final Map<Class, GarbageDisposalStrategy> strategies = new LinkedHashMap<>();

    private GarbageProcessor garbageProcessor;
    private StrategyHolder strategyHolderMock;
    private ProcessingData processingDataMock;
    private GarbageDisposalStrategy garbageDisposalStrategyMock;


    @Before
    public void initialize() {
        this.strategyHolderMock = Mockito.mock(StrategyHolder.class);
        this.garbageDisposalStrategyMock = Mockito.mock(GarbageDisposalStrategy.class);
        this.garbageProcessor = new DefaultGarbageProcessor(strategyHolderMock);
        this.processingDataMock = Mockito.mock(ProcessingData.class);
        this.strategies.put(FakeDisposable.class, this.garbageDisposalStrategyMock);
    }

    @Test
    public void returnStrategyHolderIfPresent() {
        Assert.assertEquals(this.strategyHolderMock, this.garbageProcessor.getStrategyHolder());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenNullStrategyHolderIsPassed() {
        this.garbageProcessor = new DefaultGarbageProcessor(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenNonDisposableWasteIsProcessed() {
        this.garbageProcessor.processWaste(new FakeNonDisposableWasteWithAnotation());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenNonAnnotatedWasteIsProcessed() {
        this.garbageProcessor.processWaste(new FakeNonDisposableWasteWithoutAnnotation());
    }

    @Test
    public void returnProcessingDataIfDisposableWastePassed() {
        //arrange
        Mockito.when(this.garbageDisposalStrategyMock.processGarbage(Mockito.isA(FakeDisposableWaste.class)))
                .thenReturn(this.processingDataMock);
        Mockito.when(this.strategyHolderMock.getDisposalStrategies()).thenReturn(this.strategies);

        //act
        ProcessingData result = this.garbageProcessor.processWaste(new FakeDisposableWaste());

        //assert
        Assert.assertSame(this.processingDataMock, result);
    }
}