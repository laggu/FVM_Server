package Main;

import Network.ClientManager;
import Network.ClientStatus;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        int port = 25934;
        ServerSocket serverSocket = null;
        Socket client = null;
        ArrayList<ClientManager> clientList = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true){
            try {
                client = serverSocket.accept();
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                String id = in.readUTF();
                int pw = in.readInt();

                //db 에서 id pw 비교

                // 같으면 클라이언트 연결 유지
                if(true) {
                    ClientStatus cs = (ClientStatus) in.readObject();
                    ClientManager cm = new ClientManager(cs, client);
                    cm.start();
                    clientList.add(cm);
                }
                else{
                    // 다르면 연결 종료
                    client.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


    }
}
