package a_emergency.engines;

import a_emergency.commands.Executable;
import a_emergency.interpreter.Interpreter;
import a_emergency.io.Reader;
import a_emergency.io.Writer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Engine implements Runnable {

    private Reader reader;
    private Writer writer;
    private Interpreter interpreter;

    public Engine(Reader reader, Writer writer, Interpreter interpreter) {
        this.reader = reader;
        this.writer = writer;
        this.interpreter = interpreter;
    }

    @Override
    public void run() {
        try {
            String line = this.reader.read();
            while (true){
                if (line.equalsIgnoreCase("EmergencyBreak")) break;
                Executable executable = this.interpreter.interpretCommand(line);
                String result = executable.execute();
                this.writer.write(result);
                line = this.reader.read();
            }
        } catch (IOException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException |
                InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}