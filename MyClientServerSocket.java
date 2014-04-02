package main.lessonsone;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by valerijszemlanikins on 20.02.14.
 */
public class MyClientServerSocket {

//    Socket socket;
//    DataOutputStream out;
//    BufferedReader in;
//    public void listenSocket (){
//            try{
//                socket = new Socket("localhost",8000);
//                out = new DataOutputStream(socket.getOutputStream())
//
//                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                out.writeUTF("Hello");
//            } catch (UnknownHostException e) {
//                System.out.println("Unknow host name");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//    }
//}
public static void main(String [] args)
{
    String serverName = "localhost"; //args[0];
    int port = 5005;//Integer.parseInt(args[1]);
    String message = null;
    try
    {
        System.out.println("Connecting to " + serverName
                + " on port " + port);
        Socket client = new Socket(serverName, port);
        System.out.println("Just connected to "
                + client.getRemoteSocketAddress());
        OutputStream outToServer = client.getOutputStream();
        DataOutputStream out =
                new DataOutputStream(outToServer);
        try{

            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            InputStream inFromServer = client.getInputStream();
            do {
                message = bufferRead.readLine();

                out.writeUTF(message);
                DataInputStream in =
                        new DataInputStream(inFromServer);
                System.out.println("Server says " + in.readUTF());
            }


            while(message!="");

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        out.writeUTF("Hello from " + message
                + client.getLocalSocketAddress());


//        InputStream inFromServer = client.getInputStream();
//        DataInputStream in =
//                new DataInputStream(inFromServer);
//        System.out.println("Server says " + in.readUTF());
        client.close();
    }catch(IOException e)
    {
        e.printStackTrace();
    }
}
}
