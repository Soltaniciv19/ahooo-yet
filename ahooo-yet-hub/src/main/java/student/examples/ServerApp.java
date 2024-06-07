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
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    private static final Logger logger = LoggerFactory.getLogger(ServerApp.class);

    public static void main(String[] args) {
        logger.info("Started");

        try (ServerSocket serverSocket = new ServerSocket(Configuration.PORT)) {
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());

                    ServerCommand turnOnCommand = new ServerCommand(CommandType.TURN_ON);
                    oos.writeObject(turnOnCommand);
                    oos.flush();

                    ClientCommand clientTurnOnCommand = (ClientCommand) ois.readObject();

                    if (clientTurnOnCommand.getType().equals(CommandType.ACKNOWLEDGE)) {
                        logger.info("Operation on client turn ON done!");
                    } else {
                        logger.warn("Error while trying to turn ON client!");
                    }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
