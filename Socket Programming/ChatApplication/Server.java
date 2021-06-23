//This is a multi user chat application
//A multi threaded chat server

import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.BufferedReader;
public class Server
{
    static ArrayList<String> userNames=new ArrayList<String>(); //List to store all the usernames of the clients associated with our server
    static ArrayList<PrintWriter> printWriters=new ArrayList<PrintWriter>(); // List to store the PrintWriter objects of all the clients


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
    String name; 
    PrintWriter pw;
    static FileWriter fw;
    static BufferedWriter bw;

    public ConversationHandler(Socket socket) throws IOException
    {
        this.socket = socket;
        fw=new FileWriter("C:\\Users\\hp\\ChatServerLogs.txt",true);// We use true so as to just append the content and not overwrite it
        bw=new BufferedWriter(fw);// The BufferedWriter is capable of writing a whole string at once unlike FileWriter
        pw=new PrintWriter(bw,true);
    }
    public void run()
    {
        try
        {
            in=new BufferedReader(new InputStreamReader(socket.getInputStream())); // The BufferedReader will be used to read the data from the socket's input stream

            out = new PrintWriter(socket.getOutputStream(), true); //The PrintWriter will be used for sending the data to the socket's output stream
            int count=0;
            while(true) // A while loop to wait till the user enters a unique name
            {
                if(count>0)
                {
                    out.println("NAMEALREADYEXISTS");
                }
                else
                {
                    out.println("NAMEREQUIRED");
                }
                name=in.readLine(); // To capture the name from the client side
                if(name==null)
                {
                    return;
                }
                if(!Server.userNames.contains(name)) // If the particulat user name is not present in the array list
                {
                    Server.userNames.add(name); // Then add that name to the list
                    break; // Once we get a unique username, we break the loop
                }
                count++;

            }
            out.println("NAMEACCEPTED"+name);
            Server.printWriters.add(out); // Adding that name inside the printWriters array list
            while(true)
            {
                String message=in.readLine();// Reading the message from the socket's input stream
                if(message==null)
                {
                    return;
                }
                //We need to send the message from client to all the other clients
                pw.println(name +":"+message);// Writing to the file
                for(PrintWriter writer:Server.printWriters)
                {
                    writer.println(name + ":" +message); // appending the message with the name
                }

            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }


}