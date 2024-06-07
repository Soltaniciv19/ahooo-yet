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
import java.net.Socket;

public class VacuumCleanerApp {

    private static final Logger logger = LoggerFactory.getLogger(VacuumCleanerApp.class);

    public static void main(String[] args) {
        logger.info("Starting");
        VacumCleaner vacuumCleaner = new VacumCleaner();
        logger.info(String.format("Vacuum cleaner on: %b", vacuumCleaner.isOn()));

        try (Socket socket = new Socket(Configuration.HOST, Configuration.PORT);
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {

            ServerCommand serverCommand = (ServerCommand) ois.readObject();
            ClientCommand clientCommand = new ClientCommand(CommandType.ACKNOWLEDGE);

            if (serverCommand.getType().equals(CommandType.TURN_ON)) {
                vacuumCleaner.switchOn();
                logger.info("Client received TURN_ON command from server!");

            } else if (serverCommand.getType().equals(CommandType.TURN_OFF)) {
                vacuumCleaner.switchOff();
                logger.info("Client received TURN_OFF command from server!");
            }

            oos.writeObject(clientCommand);
            oos.flush();

            logger.info(String.format("Vacuum cleaner on: %b", vacuumCleaner.isOn()));
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Error occurred: ", e);
        }


        logger.info("Stopping");
        }

    }
