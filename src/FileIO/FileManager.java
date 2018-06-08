package FileIO;

import Network.ClientStatus;

import java.io.File;

public class FileManager {
    private ClientStatus clientStatus;
    private String clientRootPath;


    public FileManager(ClientStatus clientStatus){
        this.clientStatus = clientStatus;
        clientRootPath = System.getProperty("user.home")+"/FVM_server/"+clientStatus.getId();
        File f = new File(clientRootPath);

        if (!f.exists()) {
            f.mkdirs();
        }
    }

    private void check_dir_exist(String path){
        String commmitPath = clientRootPath + clientStatus.getProject() + clientStatus.getBranch() + clientStatus.getVersion();
        String dir = path.substring(0,path.lastIndexOf(File.separator));

        File f = new File(commmitPath + dir);

        if (!f.exists()) {
            f.mkdirs();
        }
    }

    public File makeFile(String path){
        check_dir_exist(path);
        File f = new File(path);

        return f;
    }

    public void setClientStatus(ClientStatus clientStatus) {
        this.clientStatus = clientStatus;
    }
}
