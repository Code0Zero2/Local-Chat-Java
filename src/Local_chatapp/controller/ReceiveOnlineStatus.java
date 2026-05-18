/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Local_chatapp.controller;

import Local_chatapp.MulticastClient;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

/**
 *
 * @author morxidia
 */

public class ReceiveOnlineStatus implements Runnable{
    InetAddress address=null;
    MulticastSocket socket=null;
    public static ArrayList al=new ArrayList();
    
    public ReceiveOnlineStatus(){
        try{
            socket = new MulticastSocket(5000) ;
            address=InetAddress.getByName("230.0.0.2");
            socket.joinGroup(address);
        }
        catch(Exception e){
            System.err.println("error");
        }
    }
    @Override
    public void run(){
        al=new ArrayList();
        while(true){ 
           try{ 
               DatagramPacket packet;
                byte[] buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String name=new String(packet.getData(), 0, packet.getLength());

                if(name.equals("exited")){
                    al=new ArrayList();
                }
                if(!al.contains(name)&& !name.equals("exited"))
                {
                    al.add(name);

                   if(MulticastClient.userList.getText().equals(""))
                     MulticastClient.userList.setText(name);
                   else{ 
                       MulticastClient.userList.setText("");
                        for(Object obj:al){
                          MulticastClient.userList.setText(MulticastClient.userList.getText()+obj.toString()+"\n");  
                        }
                   }       
                }
            }
            catch(Exception e){
                System.out.println("error in receiveonline status class");
            }
        }
    }
}