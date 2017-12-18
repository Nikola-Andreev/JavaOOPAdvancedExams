package b_recyclingStation.app.io;

import b_recyclingStation.app.contracts.Writer;

public class ConsoleWriter implements Writer{

    @Override
    public void write(String text) {
        System.out.println(text);
    }
}
