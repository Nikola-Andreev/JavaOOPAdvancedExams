package a_emergency.models.emergencies;

import a_emergency.enums.EmergencyLevel;
import a_emergency.utils.RegistrationTime;

public class PropertyEmergency extends BaseEmergency {

    private int damage;

    public PropertyEmergency(String description, EmergencyLevel emergencyLevel, RegistrationTime registrationTime, int damage) {
        super(description, emergencyLevel, registrationTime);
        this.damage = damage;
    }

    @Override
    public Integer getResultAfterProcessing() {
        return this.damage;
    }
}
