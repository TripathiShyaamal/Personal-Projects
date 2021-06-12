import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Conn_Server 
{
    public static void main(String[] args)
    {
        try
        {
            System.out.println();
            System.out.println("Waiting for the client....");
            System.out.println();
            ServerSocket ss = new ServerSocket(9800);
            Socket soc= ss.accept();
            System.out.println("The connection is established");
            System.out.println();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
}
