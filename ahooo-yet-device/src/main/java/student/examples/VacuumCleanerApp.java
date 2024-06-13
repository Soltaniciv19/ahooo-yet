package student.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import student.examples.comm.ClientCommand;
import student.examples.comm.CommandType;
import student.examples.comm.ServerCommand;
import student.examples.config.Configuration;
import student.examples.devices.VacumCleaner;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class VacuumCleanerApp {
    private static final Logger logger = LoggerFactory.getLogger(VacuumCleanerApp.class);
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        logger.info("CLIENT: Starting");

        VacumCleaner vacuumCleaner = new VacumCleaner(1,"Atom");
        Socket socket = new Socket(InetAddress.getLocalHost(), Configuration.PORT);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        ClientCommand clientCommand = new ClientCommand(CommandType.IDENTITY,vacuumCleaner);

        oos.writeObject(clientCommand);
        oos.flush();

        ServerCommand commandFromServer = (ServerCommand) ois.readObject();

        if (commandFromServer.getType().equals(CommandType.ACKNOWLEDGE)) {
            System.out.println("Connected to server!");
        }

        logger.info("Stopping");
        }

    }
