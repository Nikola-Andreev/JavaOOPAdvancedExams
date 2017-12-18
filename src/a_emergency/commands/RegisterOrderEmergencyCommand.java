package a_emergency.commands;

import a_emergency.annotations.InjectArgs;
import a_emergency.core.ManagementSystem;
import a_emergency.enums.EmergencyLevel;
import a_emergency.enums.Status;
import a_emergency.models.emergencies.Emergency;
import a_emergency.models.emergencies.OrderEmergency;
import a_emergency.utils.RegistrationTime;
import a_emergency.utils.RegistrationTimeImpl;

public class RegisterOrderEmergencyCommand extends BaseCommand{

    @InjectArgs
    private String[] params;
    private Emergency emergency;
    private RegistrationTime registrationTime;

    public RegisterOrderEmergencyCommand(ManagementSystem managementSystem) {
        super(managementSystem);
    }

    @Override
    public String execute() {
        String description = this.params[1];
        EmergencyLevel emergencyLevel = EmergencyLevel.valueOf(this.params[2].toUpperCase());
        this.registrationTime = new RegistrationTimeImpl(this.params[3]);
        Status status = Status.valueOf(this.params[4].replace("-", "_").toUpperCase());
        this.emergency = new OrderEmergency(description, emergencyLevel, registrationTime, status);
        return super.getManagementSystem().registerOrderEmergency(emergency);
    }
}
