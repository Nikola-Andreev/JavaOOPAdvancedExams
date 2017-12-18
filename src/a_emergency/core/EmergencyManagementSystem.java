package a_emergency.core;

import a_emergency.collection.EmergencyRegister;
import a_emergency.collection.Register;
import a_emergency.models.centers.EmergencyCenter;
import a_emergency.models.emergencies.Emergency;

import java.util.HashMap;
import java.util.Map;

public class EmergencyManagementSystem implements ManagementSystem{

    private static final String PROPERTY = "Property";
    private static final String HEALTH = "Health";
    private static final String ORDER = "Order";

    private Map<String, Register<Emergency>> emergencies;
    private Map<String, Register<EmergencyCenter>> centers;
    private Map<String, Long> processedEmergencies;
    private int totalProcessedEmergencies;

    public EmergencyManagementSystem() {
        this.setEmergencies();
        this.setCenters();
        this.setProcessedEmergencies();
        this.totalProcessedEmergencies = 0;
    }

    private void setProcessedEmergencies() {
        this.processedEmergencies = new HashMap<>();
        this.processedEmergencies.put(PROPERTY, 0L);
        this.processedEmergencies.put(HEALTH, 0L);
        this.processedEmergencies.put(ORDER, 0L);
    }

    private void setEmergencies() {
        this.emergencies = new HashMap<>();
        this.emergencies.put(PROPERTY, new EmergencyRegister<>());
        this.emergencies.put(HEALTH, new EmergencyRegister<>());
        this.emergencies.put(ORDER, new EmergencyRegister<>());
    }

    private void setCenters() {
        this.centers = new HashMap<>();
        this.centers.put(PROPERTY, new EmergencyRegister<>());
        this.centers.put(HEALTH, new EmergencyRegister<>());
        this.centers.put(ORDER, new EmergencyRegister<>());
    }

    @Override
    public String registerPropertyEmergency(Emergency emergency) {
        this.emergencies.get(PROPERTY).enqueueEmergency(emergency);
        return "Registered Public Property Emergency of level " + emergency.getEmergencyLevel() +
                " at " + emergency.getRegistrationTime() + ".";
    }

    @Override
    public String registerHealthEmergency(Emergency emergency) {
        this.emergencies.get(HEALTH).enqueueEmergency(emergency);
        return "Registered Public Health Emergency of level " + emergency.getEmergencyLevel() +
                " at " + emergency.getRegistrationTime() + ".";
    }

    @Override
    public String registerOrderEmergency(Emergency emergency) {
        this.emergencies.get(ORDER).enqueueEmergency(emergency);
        return "Registered Public Order Emergency of level " + emergency.getEmergencyLevel() +
                " at " + emergency.getRegistrationTime() + ".";
    }

    @Override
    public String registerFireServiceCenter(EmergencyCenter emergencyCenter) {
        this.centers.get(PROPERTY).enqueueEmergency(emergencyCenter);
        return "Registered Fire Service Emergency Center – "+ emergencyCenter.getName() +".";
    }

    @Override
    public String registerMedicalServiceCenter(EmergencyCenter emergencyCenter) {
        this.centers.get(HEALTH).enqueueEmergency(emergencyCenter);
        return "Registered Medical Service Emergency Center – "+ emergencyCenter.getName() +".";
    }

    @Override
    public String registerPoliceServiceCenter(EmergencyCenter emergencyCenter) {
        this.centers.get(ORDER).enqueueEmergency(emergencyCenter);
        return "Registered Police Service Emergency Center – "+ emergencyCenter.getName() +".";
    }

    @Override
    public String processEmergencies(String type) {

        Register<Emergency> emergenciesToProcess = this.emergencies.get(type);
        Register<EmergencyCenter> processCenters = this.centers.get(type);

        while (!emergenciesToProcess.isEmpty()) {
            if (processCenters.isEmpty()) {
                return type + " Emergencies left to process: " + emergenciesToProcess.count() + ".";
            }
            EmergencyCenter currentCenter = processCenters.dequeueEmergency();
            if (currentCenter.isForRetirement()) {
                continue;
            }
            Emergency currentEmergency = emergenciesToProcess.dequeueEmergency();
            Long currentResult = this.processedEmergencies.get(type);
            this.processedEmergencies.put(type, currentResult + currentEmergency.getResultAfterProcessing());
            currentCenter.processEmergency();
            processCenters.enqueueEmergency(currentCenter);
            this.totalProcessedEmergencies++;
        }
        return "Successfully responded to all " + type + " emergencies.";
    }

    @Override
    public String emergencyReport() {
        return "PRRM Services Live Statistics\n" +
                "Fire Service Centers: " + this.centers.get(PROPERTY).count() + "\n" +
                "Medical Service Centers: " + this.centers.get(HEALTH).count() + "\n" +
                "Police Service Centers: " + this.centers.get(ORDER).count() + "\n" +
                "Total Processed Emergencies: " + this.totalProcessedEmergencies + "\n" +
                "Currently Registered Emergencies: " + this.emergencies.entrySet().stream().
                mapToInt(currentEmergencies -> currentEmergencies.getValue().count()).sum() + "\n" +
                "Total Property Damage Fixed: " + this.processedEmergencies.get(PROPERTY) + "\n" +
                "Total Health Casualties Saved: " + this.processedEmergencies.get(HEALTH) + "\n" +
                "Total Special Cases Processed: " + this.processedEmergencies.get(ORDER);
    }
}