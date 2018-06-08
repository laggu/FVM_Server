package Network;

import Command.BaseCommand;
import FileIO.FileManager;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientManager extends Thread {

    ClientStatus clientStatus;
    NetworkManager networkManager;
    FileManager fileManager;

    public ClientManager(ClientStatus clientStatus, Socket socket){
        this.clientStatus = clientStatus;
        this.networkManager = new NetworkManager(socket);
        this.fileManager = new FileManager(clientStatus);
    }

    @Override
    public void run() {
        BaseCommand command = null;
        boolean logout_flag = false;

        while(true){
            try {
                int choice = networkManager.readInt();

                switch(choice){
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        logout_flag = true;
                        break;
                    default:
                            break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(logout_flag) {
                clientStatus = null;
                networkManager.logout();
                break;
            }

            command.execute();
        }
    }
}
