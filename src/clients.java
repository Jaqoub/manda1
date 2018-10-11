import java.net.InetAddress;
import java.net.UnknownHostException;

public class clients {
    public static void main(String[] args) {
        showIpadress();

    }
    public static void showIpadress(){

        InetAddress IP= null;
        try {
            IP = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(IP.getHostAddress());
    }

}