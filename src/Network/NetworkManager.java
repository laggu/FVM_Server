package Network;

import java.io.*;
import java.net.Socket;

public class NetworkManager {

    private String ip;
    private int port;
    private boolean logedin;
    private Socket client;
    private DataOutputStream os;
    private DataInputStream is;

    public NetworkManager(Socket client){
        logedin = false;
        this.client = client;
        try {
            os = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
            is = new DataInputStream(new BufferedInputStream(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout(){
        try {
            close();
            logedin = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendFile(File f){
        byte[] buffer = new byte[10000];
        int readBytes;
        FileInputStream fis = null;

        try {
            String fName = f.getName();
            writeUTF(fName);

            fis = new FileInputStream(f);

            while ((readBytes = fis.read(buffer)) > 0) {
                write(buffer, 0, readBytes);
                flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendDir(File dir){

    }

    private void receiveFile(){
        byte[] buffer = new byte[10000];
        int n;
        FileOutputStream fos = null;

        try{
            String fileName = readUTF();


            // 경로 변경 필요함    !!!!!!!!!!!!
            File f = new File(fileName);
            fos = new FileOutputStream(f);

            while((n = read(buffer)) != -1){
                fos.write(buffer,0,n);
                fos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void receiveDir(){

    }





    public int readInt() throws IOException{
        return is.readInt();
    }

    public String readUTF() throws IOException{
        return is.readUTF();
    }

    public int read(byte[] buffer) throws IOException{
        return is.read(buffer);
    }

    public void writeUTF(String str) throws IOException{
        os.writeUTF(str);
    }

    public void writeInt(int i) throws IOException{
        os.writeInt(i);
    }

    public void write(byte[] b, int off, int len) throws IOException{
        os.write(b,off,len);
    }

    public void flush() throws IOException{
        os.flush();
    }

    public void close() throws IOException{
        os.close();
        is.close();
        client.close();
    }
}