import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
public class Cal_Server 
{
    public static void main(String[] args) throws Exception
    {
        System.out.println();
        System.out.println("The Server has started");
        System.out.println();
        ServerSocket ss=new ServerSocket(9806);
        System.out.println("Waiting for the client.....");
        System.out.println();
        Socket soc=ss.accept();
        System.out.println("The connection is established between with the client");
        System.out.println();
        BufferedReader in=new BufferedReader(new InputStreamReader(soc.getInputStream()));// Reading data from client
        PrintWriter out=new PrintWriter(soc.getOutputStream(), true);
        
        while(true)
        {
            String str[]=in.readLine().split(":");// split into different parts based on the delimeter storing each part in string array
            int option=Integer.parseInt(str[0]);
            int num1=Integer.parseInt(str[1]);
            int num2=Integer.parseInt(str[2]);
            String result="";
            int flag=0;

            switch(option)
            {
                
                case 1: 
                result="Addition is:"+(num1+num2);
                break;
                
                case 2: 
                result="Subtraction is:"+(num1-num2);
                break;

                case 3: 
                result="Multiplication is:"+(num1*num2);
                break;

                case 4: 
                result="Divison is:"+(num1/(double)num2);
                break;

                case 5: 
                flag=1;
                break;

                default: 
                break;
            }
            if(flag==1)
            {
                break;
            }
            out.println(result);
        }
        System.out.println();
        System.out.println("The Server has terminated");
    }
    
}
