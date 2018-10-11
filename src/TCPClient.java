import java.io.*;
import java.net.ServerSocket;

public class TCPClient {

    public static void main(String[] args) throws IOException {
        ClientPart client= new ClientPart();
        client.ClientStartop();
        client.sendmessageToserver();
        client.recievemessage();
    }

}
