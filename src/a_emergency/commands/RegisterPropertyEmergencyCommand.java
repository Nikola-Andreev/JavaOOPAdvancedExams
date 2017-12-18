package a_emergency.commands;

import a_emergency.annotations.InjectArgs;
import a_emergency.core.ManagementSystem;
import a_emergency.enums.EmergencyLevel;
import a_emergency.models.emergencies.Emergency;
import a_emergency.models.emergencies.PropertyEmergency;
import a_emergency.utils.RegistrationTime;
import a_emergency.utils.RegistrationTimeImpl;

public class RegisterPropertyEmergencyCommand extends BaseCommand{

    @InjectArgs
    private String[] params;
    private Emergency emergency;
    private RegistrationTime registrationTime;

    public RegisterPropertyEmergencyCommand(ManagementSystem managementSystem) {
        super(managementSystem);
    }

    @Override
    public String execute() {
        String description = this.params[1];
        EmergencyLevel emergencyLevel = EmergencyLevel.valueOf(this.params[2].toUpperCase());
        this.registrationTime = new RegistrationTimeImpl(this.params[3]);
        Integer damage = Integer.valueOf(this.params[4]);
        this.emergency = new PropertyEmergency(description, emergencyLevel, registrationTime, damage);
        return super.getManagementSystem().registerPropertyEmergency(emergency);
    }
}
