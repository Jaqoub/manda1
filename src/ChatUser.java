import java.io.IOException;
import java.util.Scanner;

public class ChatUser {
    public String Username;
    public String IP;
    public int PORT;


    public ChatUser() {

    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username)  {
        Scanner scan = new Scanner(System.in);
        String user = null;
        if (!username.contains("# € % & / ( ) = ] + ") && (username.length() <= 12)) {
            Username = username;
            System.out.println("working");
        } else
            do {
                System.out.println("please pick another username");
                user = scan.next();

            } while (user.length() > 12 && user.contains("! # € % & / ( ) = ?"));
        Username = user;
    }




    public void setIP(String IP) {
        this.IP = IP;
    }


    public void setPORT(int PORT) {
        this.PORT = PORT;
    }

    @Override
    public String toString() {
        return "<<"+ Username +">>" + " " + "<<" + IP + ">>" + ":<<"+ PORT +">>";
    }
}
