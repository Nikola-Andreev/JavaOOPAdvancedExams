package a_emergency.models.emergencies;

import a_emergency.enums.EmergencyLevel;
import a_emergency.utils.RegistrationTime;

public interface Emergency {

    String getDescription();

    EmergencyLevel getEmergencyLevel();

    RegistrationTime getRegistrationTime();

    Integer getResultAfterProcessing();
}
