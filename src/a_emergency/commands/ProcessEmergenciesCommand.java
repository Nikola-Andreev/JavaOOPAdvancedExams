package a_emergency.commands;

import a_emergency.annotations.InjectType;
import a_emergency.core.ManagementSystem;

public class ProcessEmergenciesCommand extends BaseCommand{

    @InjectType
    private String type;

    public ProcessEmergenciesCommand(ManagementSystem managementSystem) {
        super(managementSystem);
    }

    @Override
    public String execute() {
        return super.getManagementSystem().processEmergencies(this.type);
    }
}
