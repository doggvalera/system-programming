package main.lessonsone;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by valerijszemlanikins on 20.02.14.
 */
public class MyServerSocket extends Thread {
//    ServerSocket server;
//    Socket clientS;
//    BufferedReader in;
//    PrintWriter out;
//    String line;
//
//    public void listenSocket(){
//        try{
//           server = new ServerSocket(8000);
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
//        try {
//           clientS = server.accept();
//        } catch (IOException e) {
//            System.out.println("Accept failed 4321");
//        }
//
//        try {
//           in = new BufferedReader(new InputStreamReader(
//                    clientS.getInputStream()));
//           out = new PrintWriter(clientS.getOutputStream(),true);
//        } catch (IOException e) {
//            System.out.println("Read failed");
//        }
//        while(true){
//            try{
//                line = in.readLine();
//                System.out.println(line);
//            } catch (IOException e) {
//                System.out.println("Read failed");
//            }
//        }
//
//    }
//
//



        private ServerSocket serverSocket;

        public MyServerSocket(int port) throws IOException
        {
            serverSocket = new ServerSocket(port);
        }
        String message = null;
        public void run()
        {
            while(true)
                try {
                    System.out.println("Waiting for client on port " +
                            serverSocket.getLocalPort() + "...");
                    Socket server = serverSocket.accept();
                    System.out.println("Just connected to "
                            + server.getRemoteSocketAddress());
                    DataOutputStream out =
                            new DataOutputStream(server.getOutputStream());

                    DataInputStream in =
                            new DataInputStream(server.getInputStream());
                    System.out.println(in.readUTF());
                    out.writeUTF("f-up");
                      byte buff[] = new byte[10];
                    do {
                       in.read(buff,0,10);
                        message = buff.toString();
                        System.out.println(message);
                        out.writeUTF("Thank you for connecting to " + message
                                + server.getLocalSocketAddress() + "\nGoodbye!" + server);
                        out.flush();
                    }
                    while (message != "");


                    //out.writeUTF("Thank you for connecting to "// + // message
//                            + server.getLocalSocketAddress() + "\nGoodbye!" + server);

                    server.close();
                } catch (SocketTimeoutException s) {
                    System.out.println("Socket timed out!");
                    s.printStackTrace();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
        }
        public static void main(String [] args)
        {
            int port = 5005;//Integer.parseInt(args[0]);
            try
            {
                Thread t = new MyServerSocket(port);

                t.start();
            }catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }




