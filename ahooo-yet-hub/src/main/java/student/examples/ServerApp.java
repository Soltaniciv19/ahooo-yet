package student.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import student.examples.comm.ClientCommand;
import student.examples.comm.CommandType;
import student.examples.comm.ServerCommand;
import student.examples.config.Configuration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServerApp {
    private static final Logger logger = LoggerFactory.getLogger(ServerApp.class);
    private Set<Map<String, Object>> connections;
    private ServerSocket serverSocket;

    public ServerApp(int port) throws IOException {
        create(port);
    }

    public void create(int port) throws IOException {
        connections = new HashSet<>();
        serverSocket = new ServerSocket(port,0, InetAddress.getLocalHost());
    }

    private void listen() throws IOException {
        while (true){
            Socket clientSocket = serverSocket.accept();
            logger.info(String.format("SERVER: Client connected from ip: %s",clientSocket.getInetAddress()));
            Map<String,Object> client = new HashMap<>();

            client.put("socket",client);
            connections.add(client);
        }
    }

    public static void main(String[] args) throws IOException {
        logger.info("SERVER: Initializing");
        ServerApp app = new ServerApp(10000);

        logger.info("SERVER: Starting");
        app.listen();
        logger.info("SERVER: Shutting down");


//        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
//        ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
//
//        ServerCommand turnOnCommand = new ServerCommand(CommandType.TURN_ON);
//        oos.writeObject(turnOnCommand);
//        oos.flush();
//
//        ClientCommand clientTurnOnCommand = (ClientCommand) ois.readObject();

//        if (clientTurnOnCommand.getType().equals(CommandType.ACKNOWLEDGE)) {
//            logger.info("Operation on client turn ON done!");
//        } else {
//            logger.warn("Error while trying to turn ON client!");
//        }
    }
}
