//This is a multi user chat application
//A multi threaded chat server

import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
public class Server
{
    public static void main(String[] args) throws Exception
    {
        System.out.println();
        System.out.println("Waiting for the clients....");
        System.out.println();
        ServerSocket ss = new ServerSocket(9806);// Creating a server socket object and binding it with a port number

        while(true)// While loop is for the server to continuously wait for the incoming client connections
        {
            Socket soc = ss.accept();// The accept method will accept the incoming client connections and return a socket object
            //That socket object will be caught in soc

            System.out.println("The connection is established between the client and the server.");
            System.out.println();
            ConversationHandler handler = new ConversationHandler(soc); // Creating the object of conversation handler inside which we will pass the soc socket object
            handler.start(); //Calling the start method to start the thread
        }

    }
}

class ConversationHandler extends Thread // Creating a thread class
{
    Socket socket; // The socket for which we are creating a thread
    BufferedReader in;
    PrintWriter out;

    public ConversationHandler(Socket socket) throws IOException
    {
        this.socket = socket;
    }
    public void run()
    {
        try
        {
            in=new BufferedReader(new InputStreamReader(socket.getInputStream())); // The BufferedReader will be used to read the data from the socket's input stream

            out = new PrintWriter(socket.getOutputStream(), true); //The PrintWriter will be used for sending the data to the socket's output stream
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }


}