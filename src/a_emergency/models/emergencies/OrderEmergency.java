package a_emergency.models.emergencies;

import a_emergency.enums.EmergencyLevel;
import a_emergency.enums.Status;
import a_emergency.utils.RegistrationTime;

public class OrderEmergency extends BaseEmergency {

    private Status specialCase;

    public OrderEmergency(String description, EmergencyLevel emergencyLevel, RegistrationTime registrationTime, Status specialCase) {
        super(description, emergencyLevel, registrationTime);
        this.specialCase = specialCase;
    }

    @Override
    public Integer getResultAfterProcessing() {
        switch (this.specialCase) {
            case SPECIAL:
                return 1;
            case NON_SPECIAL:
                return 0;
        }
        return null;
    }
}
