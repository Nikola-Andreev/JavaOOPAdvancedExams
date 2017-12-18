package a_emergency.commands;

import a_emergency.annotations.InjectArgs;
import a_emergency.core.ManagementSystem;
import a_emergency.models.centers.EmergencyCenter;
import a_emergency.models.centers.PoliceServiceCenter;

public class RegisterPoliceServiceCenterCommand extends BaseCommand {

    @InjectArgs
    private String[] params;
    private EmergencyCenter emergencyCenter;

    public RegisterPoliceServiceCenterCommand(ManagementSystem managementSystem) {
        super(managementSystem);
    }

    @Override
    public String execute() {
        String name = this.params[1];
        Integer amountOfEmergencies = Integer.valueOf(this.params[2]);
        this.emergencyCenter = new PoliceServiceCenter(name, amountOfEmergencies);
        return super.getManagementSystem().registerPoliceServiceCenter(emergencyCenter);
    }

}
