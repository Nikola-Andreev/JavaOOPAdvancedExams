package a_emergency.commands;

import a_emergency.core.ManagementSystem;

public abstract class BaseCommand implements Executable {

    private ManagementSystem managementSystem;

    protected BaseCommand(ManagementSystem managementSystem) {
        this.managementSystem = managementSystem;
    }

    protected ManagementSystem getManagementSystem() {
        return this.managementSystem;
    }
}
