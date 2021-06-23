//This is a multi user chat application

import java.io.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
//All the Swing components will be static because we are using the same components for all our client instances
public class Client
{
    static JFrame chatWindow = new JFrame("Chat Application");// Creating a JFrame object and basically it is the outer frame on which we are going to add our components

    static JTextArea chatArea=new JTextArea(22,40);// A chat area to display all our messages
    //It takes two parameters that is number of rows and number of columns

    static JTextField textField = new JTextField(40);// This text field will be used for entering our messages
    //It takes one parameter and that is only the number of columns

    static JLabel blankLabel=new JLabel("           ");//The blankLabel is used to display a blank space between the text field and the chat area

    static JButton sendButton=new JButton("Send");//Send button
    static BufferedReader in;
    static PrintWriter out;
    static JLabel nameLabel=new JLabel("      ");

    Client()
    {                
        //Setting up some components and properties of our chatWindow
        chatWindow.setLayout(new FlowLayout());// Using FlowLayout to arrange the components on out JFrame in the required manner
        chatWindow.add(nameLabel);// For displaying the name of the current user
        chatWindow.add(new JScrollPane(chatArea));// Adding a scroller functionality for our chat area
        chatWindow.add(blankLabel);// Adding the blankLabel 
        chatWindow.add(textField);
        chatWindow.add(sendButton);//Adding the send button

        chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Adding close method to close the application as soon as the user clicks on the close button
        chatWindow.setSize(475,500);// Setting the size of our chat window
        chatWindow.setVisible(true);// If we do not create this, the messages will not be displayed on our chat window

        textField.setEditable(false);// Once the server permits the user to send messages then only this text field should be set to true

        chatArea.setEditable(false);// the chat area will only be used for displaying the messages and not for entering the messages

        sendButton.addActionListener(new Listener()); //Binding the Listener with the send button
        textField.addActionListener(new Listener()); //User can also send a message using Enter key
        
    }

    void startChat() throws Exception
    {
        String ipAddress =JOptionPane.showInputDialog( // This method will beused for displaying the dialog box
        chatWindow, //component on which we display the dialog box
        "Enter IP Address", //Message to be displayed on the dialog box
        "IP Address Required!!", //The title of the dialog box
        JOptionPane.PLAIN_MESSAGE); //The type of message to be displayed
        //PLAIN_MESSAGE is a constant which is a constant of the class JOptionPane
        Socket soc= new Socket(ipAddress,9806); // Creating a socket object
        in=new BufferedReader(new InputStreamReader(soc.getInputStream())); //BufferedReader object
        out= new PrintWriter(soc.getOutputStream(), true); //PrintWriter object

        while(true)
        {
            String str=in.readLine();

            if (str.equals("NAMEREQUIRED"))

            {
 
            String name = JOptionPane.showInputDialog(
                chatWindow,
                "Enter a unique name:",
                "Name Required!!",
                JOptionPane.PLAIN_MESSAGE);
                
                out.println(name);
 
             }

            else if(str.equals("NAMEALREADYEXISTS"))
            {

                String name=JOptionPane.showInputDialog(
                    chatWindow,
                    "Enter another name:",
                    "Name already Exists!!",
                    JOptionPane.WARNING_MESSAGE);
                
                out.println(name);

            }
            else if(str.startsWith("NAMEACCEPTED"))
            {
                textField.setEditable(true); // Setting the text field editable
                nameLabel.setText("You are logged in as: "+ str.substring(12)); //Till the index number 11 we have NAMEACCEPTED and after that we have name from server
            }
            else
            {
                chatArea.append(str+ "\n"); // If non of the other three messages, the message will be considered as a normal message from the clients
            }
        }

    }
    public static void main(String[] args) throws Exception
    {
        Client client = new Client();// Creating the Client object in the main method
        client.startChat(); //Calling startChat function

    }
}
//The code which needs to be executed when the send button is clicked
class Listener implements ActionListener //Class to implement some,istener interface
{
    public void actionPerformed(ActionEvent e)
    {
        Client.out.println(Client.textField.getText()); //Whatever the user types in, it will be sent to socket's output stream
        Client.textField.setText(""); //Clearing the text field 
    }

}
