package edu.school21.socket.app;

import edu.school21.socket.client.Client;
import edu.school21.socket.config.ArgsParser;

public class Program {
    public static void main(String[] args) {
        ArgsParser config = new ArgsParser(args);

        Client.startConnection(config.serverPort);
    }
}
