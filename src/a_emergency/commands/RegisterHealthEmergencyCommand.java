package a_emergency.commands;

import a_emergency.annotations.InjectArgs;
import a_emergency.core.ManagementSystem;
import a_emergency.enums.EmergencyLevel;
import a_emergency.models.emergencies.Emergency;
import a_emergency.models.emergencies.HealthEmergency;
import a_emergency.utils.RegistrationTime;
import a_emergency.utils.RegistrationTimeImpl;

public class RegisterHealthEmergencyCommand extends BaseCommand {
    @InjectArgs
    private String[] params;
    private Emergency emergency;
    private RegistrationTime registrationTime;

    public RegisterHealthEmergencyCommand(ManagementSystem managementSystem) {
        super(managementSystem);
    }

    @Override
    public String execute() {
        String description = this.params[1];
        EmergencyLevel emergencyLevel = EmergencyLevel.valueOf(this.params[2].toUpperCase());
        this.registrationTime = new RegistrationTimeImpl(this.params[3]);
        Integer casualties = Integer.valueOf(this.params[4]);
        this.emergency = new HealthEmergency(description, emergencyLevel, registrationTime, casualties);
        return super.getManagementSystem().registerHealthEmergency(emergency);
    }
}
