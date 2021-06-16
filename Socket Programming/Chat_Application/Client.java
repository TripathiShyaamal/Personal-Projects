//This is a multi user chat application

import java.io.*;
import javax.swing.*;
import java.awt.FlowLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
public class Client
{
    static JFrame chatWindow = new JFrame("Chat Application");
    static JTextArea chatArea=new JTextArea(22,40);
    static JTextField textField = new JTextField(40);
    static JLabel blankLabel=new JLabel("           ");
    static JButton sendButton=new JButton("Send");

    Client()
    {
        chatWindow.setLayout(new FlowLayout());

        chatWindow.add(new JScrollPane(chatArea));
        chatWindow.add(blankLabel); 
        chatWindow.add(sendButton);

        chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatWindow.setSize(475,500);
        chatWindow.setVisible(true);

        textField.setEditable(false);
        chatArea.setEditable(false);

         
    }
    public static void main(String[] args)
    {
        Client client = new Client();
        

    }
}
