package a_emergency.interpreter;

import a_emergency.annotations.InjectArgs;
import a_emergency.annotations.InjectType;
import a_emergency.commands.Executable;
import a_emergency.core.ManagementSystem;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreter implements Interpreter {

    private static final String PACKAGE = "a_emergency.commands.";
    private static final String COMMAND = "Command";

    private ManagementSystem managementSystem;

    public CommandInterpreter(ManagementSystem managementSystem) {
        this.managementSystem = managementSystem;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Executable interpretCommand(String line) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        String[] params = line.split("\\|");
        String commandName = params[0];
        Class<Executable> executableClass = (Class<Executable>) Class.forName(PACKAGE + commandName + COMMAND);
        Constructor<Executable> executableConstructor = executableClass.getDeclaredConstructor(ManagementSystem.class);
        Executable executable = executableConstructor.newInstance(this.managementSystem);
        this.injectDependencies(executable, params);
        return executable;
    }

    private void injectDependencies(Executable executable, String[] params) throws IllegalAccessException {
        Field[] fields = executable.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectArgs.class)) {
                field.setAccessible(true);
                field.set(executable, params);
            } else if (field.isAnnotationPresent(InjectType.class)) {
                field.setAccessible(true);
                field.set(executable, params[1]);
            }
        }
    }
}
