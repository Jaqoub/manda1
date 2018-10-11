import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerPart {

    final int PORT_LISTEN = 5656;
    static Socket socket;
    String clientIp;
    String msgIn;
    InputStream input;
    byte[] dataIn;
    ArrayList<ChatUser> chatUsers=new ArrayList<>();
    String msgToSend;
    OutputStream output;
    byte[] byteArray;

    public void Connection() throws IOException {
        System.out.println("=============SERVER==============");
        ServerSocket socket1 = new ServerSocket(PORT_LISTEN);
        while(true) {
            socket = socket1.accept();

            Thread T7 = new Thread(() -> {
                System.out.println("Client connected");
                clientIp = socket.getInetAddress().getHostAddress();
                System.out.println("IP: " + clientIp);
                System.out.println("PORT: " + socket.getPort());
                try {
                    sendmessageToclient(socket);
                    recievemessage(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        T7.start();
        }



    }

    public void recievemessage(Socket socket1) throws IOException {
        Thread T3= new Thread(()-> {
            while (true) {

                try {
                    input = socket1.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dataIn = new byte[1024];
                try {
                    input.read(dataIn);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                msgIn = new String(dataIn);
                msgIn = msgIn.trim();
                if (msgIn.equals("JOIN") || msgIn.equals(" DATA") || msgIn.equals("IMALIVE") || msgIn.equals("QUIT")) {
                    try {
                        Commands(msgIn,socket1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {

                        System.out.println("FROM client->"+ msgIn + "<--" + socket1.getInetAddress());

                }
            }
            });
                T3.start();
        }


    public void sendmessageToclient(Socket socket2) throws IOException {
        Thread T4= new Thread(()->{
        Scanner scan= new Scanner(System.in);
            while(true) {
            try {
                output = socket2.getOutputStream();
                System.out.println("what do want to send");
                String msgOut=scan.nextLine();

                if (!msgOut.equals("Null")) {
                    msgToSend = "SERVER: [sender:" + clientIp + " ]: " + msgOut;
                    byteArray = msgToSend.getBytes();
                    output.write(byteArray);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        });
        T4.start();
        }






     public void Commands(String command, Socket socket3) throws IOException {

        switch (command) {

            case "JOIN":
                byteArray=new byte[1024];
                input.read(byteArray);
                ChatUser user = new ChatUser();
                String username=new String(byteArray);
                System.out.println(username);
                user.setUsername(username);
                user.setIP(clientIp);
                user.setPORT(socket3.getPort());
                chatUsers.add(user);
                System.out.println(user.toString());

                byteArray=user.toString().getBytes();
                output.write(byteArray);
                break;

            case "DATA":

                break;

            case "J_ER":
                break;

            case "QUIT":
                String Errormessage="Error unknown command";
                output.write(Errormessage.getBytes());
                socket.close();
                break;


        }


    }



}