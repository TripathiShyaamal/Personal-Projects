import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
public class Cal_Client
{
    public static void main(String[] args) throws Exception
    {
        System.out.println();
        System.out.println("The client has started...");
        Socket soc = new Socket("localhost", 9806);//Creating a socket object with an IP address and a port number
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        PrintWriter out= new PrintWriter(soc.getOutputStream(), true);

        int ch, num_1, num_2;
        do
        {
            System.out.println("Please choose the operation from the list of options");
            System.out.println("1. Addition");
            System.out.println();
            System.out.println("2. Subtraction");
            System.out.println();
            System.out.println("3. Multiplication");
            System.out.println();
            System.out.println("4. Divison");
            System.out.println();
            System.out.println("5. Exit");
            System.out.println();
            ch=Integer.parseInt(userInput.readLine());
            if(ch==5)
            {
                out.println(ch+":0:0");
                break;
            }
            else
            {

            System.out.println("Please enter the first number: ");
            num_1=Integer.parseInt(userInput.readLine());
            System.out.println();
            System.out.println("Please enter the second number: ");
            num_2=Integer.parseInt(userInput.readLine());
            out.println(ch+":"+num_1+":"+num_2);

            String answer=in.readLine();
            System.out.println("Server says: "+answer);
            
        } 

    }while(true);
    System.out.println("The client has terminated.");
}

}


