package ru.itis.chat;

import com.beust.jcommander.JCommander;
import ru.itis.jcommander.Args;

public class MainForServer {

    public static void main(String[] argv) {
        Args args = new Args();
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        Server server = new Server(args.port);
    }

}
