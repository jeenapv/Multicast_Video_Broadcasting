/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author kakes
 */
public class DataSender {

    public void sendNow(String sentence, String ipAddress, int port) {
        try {
            System.out.println("sending " + sentence + " to "+ ipAddress+ " on port " + port);
            BufferedReader inFromUser =
                    new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(ipAddress);
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            clientSocket.send(sendPacket);
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String arg[]) {
        new DataSender().sendNow("hellllllllllllllllllllllllllllllllooooooooo", "192.168.1.7", 9878);

    }
}
