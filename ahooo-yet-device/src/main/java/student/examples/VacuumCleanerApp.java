package student.examples;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import student.examples.comm.CommandType;
import student.examples.comm.ServerCommand;
import student.examples.config.Configuration;
import student.examples.devices.VacumCleaner;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class VacuumCleanerApp {

    final static Logger logger = LoggerFactory.getLogger(VacuumCleanerApp.class);
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        logger.info("Starting");
        VacumCleaner vacumCleaner = new VacumCleaner();
        logger.info(String.format("%b",vacumCleaner.isOn()));

        Socket socket = new Socket(Configuration.HOST,Configuration.PORT);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ServerCommand serverCommand = (ServerCommand) ois.readObject();

        if (serverCommand.getType().equals(CommandType.TURN_ON)){
            vacumCleaner.switchOn();
        }

        logger.info(String.format("%b",vacumCleaner.isOn()));


        logger.info("Stoping");
    }
}
