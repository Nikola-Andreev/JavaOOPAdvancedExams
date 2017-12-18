package a_emergency;

import a_emergency.core.EmergencyManagementSystem;
import a_emergency.core.ManagementSystem;
import a_emergency.engines.Engine;
import a_emergency.interpreter.CommandInterpreter;
import a_emergency.interpreter.Interpreter;
import a_emergency.io.ConsoleReader;
import a_emergency.io.ConsoleWriter;
import a_emergency.io.Reader;
import a_emergency.io.Writer;

public class Main {
    public static void main(String[] args) {

        Reader reader = new ConsoleReader();
        Writer writer = new ConsoleWriter();
        ManagementSystem managementSystem = new EmergencyManagementSystem();
        Interpreter interpreter = new CommandInterpreter(managementSystem);
        Runnable engine = new Engine(reader, writer, interpreter);
        engine.run();
    }
}
