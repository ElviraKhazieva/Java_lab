package ru.itis;

import com.beust.jcommander.JCommander;
import ru.itis.Args;

import java.util.Scanner;

public class MainForClient {

    public static void main(String[] argv) {

        Args args = new Args();
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        SocketClient client = new SocketClient(args.serverIp , args.serverPort);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ваше имя: ");
        String name  = scanner.nextLine();
        System.out.println("Теперь вы можете отправлять ваши сообщения! ");

        while (true) {
            String message = scanner.nextLine();
            client.sendMessage(name + ": " + message);
        }

    }

}
