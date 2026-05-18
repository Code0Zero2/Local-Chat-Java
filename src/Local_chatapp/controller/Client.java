/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Local_chatapp.controller;

import Local_chatapp.ChatApp;
import Local_chatapp.MulticastClient;
import static Local_chatapp.MulticastClient.name;
import static Local_chatapp.MulticastClient.dataSocket;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import javax.swing.JOptionPane;

/**
 *
 * @author morxidia
 */

public class Client implements Runnable {
    Client(){
         try{
            MulticastClient.socket = new MulticastSocket(4446) ;
            MulticastClient.dataSocket=new DatagramSocket();
            MulticastClient.address=InetAddress.getByName("230.0.0.1");
            MulticastClient.socket.joinGroup(MulticastClient.address);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(new ChatApp(), "Sorry,Cannot bind");
        }
     }
 
    @Override
    public void run(){
        Thread t3=new Thread(new OnlineStatus());
        t3.start();
        Thread t4=new Thread(new ReceiveOnlineStatus());    
        t4.start();
        newUser();
        while(true){  
            try{
                DatagramPacket packet;
                byte[] buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);
                MulticastClient.socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                MulticastClient.MessageWindow.setText(MulticastClient.MessageWindow.getText()+received+"\n");
                MulticastClient.messageBox.setText("");
            }
            catch(IOException e){
                System.err.println(e);
            }
        }
    } 
    
    void newUser(){
        String x= name+" has logged into the chat room";
        byte buf[]=x.getBytes();
        try{  
            InetAddress group = InetAddress.getByName("230.0.0.1");
            DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);
            dataSocket.send(packet);
        }
        catch(Exception e){
            System.err.println(e);
        }
    }
}
