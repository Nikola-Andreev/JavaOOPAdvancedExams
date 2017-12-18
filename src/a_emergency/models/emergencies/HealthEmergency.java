package a_emergency.models.emergencies;

import a_emergency.enums.EmergencyLevel;
import a_emergency.utils.RegistrationTime;

public class HealthEmergency extends BaseEmergency {

    private int numberOfCasualties;

    public HealthEmergency(String description, EmergencyLevel emergencyLevel, RegistrationTime registrationTime, int numberOfCasualties) {
        super(description, emergencyLevel, registrationTime);
        this.numberOfCasualties = numberOfCasualties;
    }

    @Override
    public Integer getResultAfterProcessing() {
        return this.numberOfCasualties;
    }
}
