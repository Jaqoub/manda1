import com.sun.corba.se.spi.activation.Server;

import java.net.Socket;

public class ClientHandler extends Thread{
private Server server;
private Socket socket;
public ClientHandler(Server server, Socket socket){
        this.server = server;
        this.socket = socket;

        start();
    }
}
