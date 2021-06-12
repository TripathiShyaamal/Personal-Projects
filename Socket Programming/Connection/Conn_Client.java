import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
public class Conn_Client
{
    public static void main(String[] args)
    {
        try
        {
            System.out.println();
            System.out.println("The client has connected");
            System.out.println();
            Socket socket = new Socket("localhost",9800);

        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
    }
}