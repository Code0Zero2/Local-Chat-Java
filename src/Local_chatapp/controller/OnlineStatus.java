/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Local_chatapp.controller;

/**
 *
 * @author Morxidia
 * 
 */

import Local_chatapp.MulticastClient;
import java.io.*;
import static java.lang.Thread.sleep;
import java.net.*;

public class OnlineStatus implements Runnable{
    DatagramSocket s;
    
    public OnlineStatus(){
        try {
            s=new DatagramSocket();
        } 
        catch (SocketException ex) {
            
        }
    }
    
    @Override
    public void run(){ 
        while(true)
        { 
            try {
                byte[] buf ;
                buf=MulticastClient.name.getBytes();
                // send it
                InetAddress group = InetAddress.getByName("230.0.0.2");
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 5000);
                s.send(packet);  
                //System.out.println(Math.random());
                try{
                    sleep((long)(Math.random() * 20000));
                }
                catch(Exception e){
                
                }
            }       
            catch (IOException e) {
                System.out.println("error in online status class");
                s.close();
            }
        }
    }
}


