package b_recyclingStation.app;

import b_recyclingStation.app.contracts.*;
import b_recyclingStation.app.io.ConsoleReader;
import b_recyclingStation.app.io.ConsoleWriter;
import b_recyclingStation.app.models.DefaultGarbageProcessor;
import b_recyclingStation.app.models.DefaultStrategyHolder;
import b_recyclingStation.app.models.garbages.BurnableGarbage;
import b_recyclingStation.app.models.garbages.RecyclableGarbage;
import b_recyclingStation.app.models.garbages.StorableGarbage;
import b_recyclingStation.app.models.strategies.BurnableStrategy;
import b_recyclingStation.app.models.strategies.RecyclableStrategy;
import b_recyclingStation.app.models.strategies.StorableStrategy;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException,
            InstantiationException, IOException {

        Reader reader = new ConsoleReader();
        Writer writer = new ConsoleWriter();
        StrategyHolder strategyHolder = new DefaultStrategyHolder();
        GarbageProcessor garbageProcessor = new DefaultGarbageProcessor(strategyHolder);
        DecimalFormat df = new DecimalFormat("0.00");
        Double totalEnergy = 0.0;
        Double totalCapital = 0.0;

        // bonus task
        String type = "";
        Double energy = 0.0;
        Double capital = 0.0;

        strategyHolder.addStrategy(BurnableStrategy.class.getAnnotations()[0].annotationType(), new BurnableStrategy());
        strategyHolder.addStrategy(RecyclableStrategy.class.getAnnotations()[0].annotationType(), new RecyclableStrategy());
        strategyHolder.addStrategy(StorableStrategy.class.getAnnotations()[0].annotationType(), new StorableStrategy());

        while (true) {

            String[] tokens = reader.readLine().split("\\s+");

            if(tokens[0].equalsIgnoreCase("ProcessGarbage")) {
                Waste garbage = createWaste(tokens[1]);
                ProcessingData data = garbageProcessor.processWaste(garbage);
                String[] againTokens = tokens[1].split("\\|");
                if (againTokens[3].equals(type) && (totalCapital < capital || totalEnergy < energy)) {
                    writer.write("Processing Denied!");
                    continue;
                }
                totalEnergy += data.getEnergyBalance();
                totalCapital += data.getCapitalBalance();
                writer.write(df.format(garbage.getWeight()) + " kg of " + garbage.getName() + " successfully processed!");
            } else if (tokens[0].equalsIgnoreCase("status")){
                writer.write("Energy: " + df.format(totalEnergy) +" Capital: " + df.format(totalCapital));
            } else if (tokens[0].equalsIgnoreCase("ChangeManagementRequirement")){
                String[] bonusTokens = tokens[1].split("\\|");
                type = bonusTokens[2];
                energy = Double.valueOf(bonusTokens[0]);
                capital = Double.valueOf(bonusTokens[1]);
                writer.write("Management requirement changed!");
            } else {
                break;
            }
        }
    }

    private static Waste createWaste(String token) {

        String[] parameters = token.split("\\|");

        switch (parameters[3].toLowerCase()) {
            case "recyclable":
                return new RecyclableGarbage(parameters[0], Double.valueOf(parameters[1]), Double.valueOf(parameters[2]));
            case "burnable":
                return new BurnableGarbage(parameters[0], Double.valueOf(parameters[1]), Double.valueOf(parameters[2]));
            case "storable":
                return new StorableGarbage(parameters[0], Double.valueOf(parameters[1]), Double.valueOf(parameters[2]));
            default:
                return null;
        }
    }
}