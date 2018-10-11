import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Scanner;

public class ClientPart {
    Scanner sc = new Scanner(System.in);
    public  InputStream input;
    public OutputStream output;
    public  Socket socket;
    public byte[] byteArray;
    public int Port;
    public void ClientStartop() throws IOException {
        System.out.println("=============CLIENT==============");
        sc = new Scanner(System.in);
        System.out.print("What is the IP for the server\n (type 0 for localhost): ");
        String IP=sc.next();

        if(IP.equals("0")) {
            IP="127.0.0.1";
        }else
            if(IP.equals("1")){
            IP=sc.next();
            }

        System.out.print("What is the port number for the server: ");
        int PORT_SERVER=sc.nextInt();
        socket = new Socket(IP, PORT_SERVER);
        input = socket.getInputStream();
        output = socket.getOutputStream();

    }

    public void sendmessageToserver(){
        Thread T1= new Thread(()->{
            while(true) {

                sc = new Scanner(System.in);
                System.out.println("What do you want to do? Options are\n JOIN\n DATA\n J_ER\n QUIT ");
                String msgToSend = sc.nextLine();

                try {
                        byteArray = msgToSend.getBytes();
                        output.write(byteArray);
                        commandcenter(msgToSend);


                        byteArray = msgToSend.getBytes();
                    output.write(byteArray);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

            T1.start();

    }

    public void recievemessage(){
        byte[] dataIn = new byte[1024];
        Thread t2= new Thread(()->{
            while(true) {
                try {
                    input.read(dataIn);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String msgIn = new String(dataIn);
                msgIn = msgIn.trim();
                System.out.println("IN -->" + msgIn + "<--");
            }
        });
        t2.start();
    }



    private  void commandcenter(String value) throws IOException, InterruptedException {
        switch (value) {
            case "QUIT":
                System.out.println("sure? type:yes OR NO");
                String answer = sc.next();
                if (answer.equals("yes")) {
                    socket.close();

                }
                break;

            case "DATA":

                break;

            case "JOIN":

                ChatUser user = new ChatUser();
                System.out.println("enter username");
                user.setUsername(sc.next());
                String dts = String.valueOf(user.getUsername());
                byteArray = dts.getBytes();
                output.write(byteArray);
        }

    }




}