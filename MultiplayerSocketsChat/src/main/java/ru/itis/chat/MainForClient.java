package ru.itis.chat;

import com.beust.jcommander.JCommander;
import ru.itis.jcommander.Args;

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
        System.out.println("Перед тем, как начать общаться в чате, введите первым сообщением свое имя.");
        System.out.println("Введите ваше имя: ");
        String name  = scanner.nextLine();
        System.out.println("Ниже вы можете написать ваши сообщения: ");

        while (true) {
            String message = scanner.nextLine();
            client.sendMessage(name + ": " + message);
        }

    }

}
