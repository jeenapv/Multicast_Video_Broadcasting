/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Jithinpv
 */
public class Configuration {

    public static String iconFolder = "images/";
    public static String organisationIconFolder = "organisationIconFolder/";
    public static String presentationFolder = "presentationFolder/";
    public static String adminIp = null;

    public static void setIconOnLabel(String fileString, JLabel label) {
        // convert string file path to image icona and set on this label
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(iconFolder + fileString));
            Image scaledInstance = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(scaledInstance);
            label.setIcon(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setIconOnButton(String fileString, JButton button) {
        // convert string file path to image icona and set on this label
        BufferedImage img = null;
        try {
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            img = ImageIO.read(new File(iconFolder + fileString));
            Image scaledInstance = img.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(scaledInstance);
            button.setIcon(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initializeEnvironment() {
        try {
            File f_organisationIconFolder = new File(organisationIconFolder);
            if (!f_organisationIconFolder.exists()) {
                f_organisationIconFolder.mkdir();
            }

            File f_presentationFolder = new File(presentationFolder);
            if (!f_presentationFolder.exists()) {
                f_presentationFolder.mkdir();
            }
            
            findIpAddress();
//
//            File f_organisationImages = new File(organisationImages);
//            if (!f_organisationImages.exists()) {
//                f_organisationImages.mkdir();
//            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void findIpAddress() {
        try {
            String ipPattern = "192.168.1.";
            String ipAddress = "";
            boolean ipFound = false;
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface n = (NetworkInterface) e.nextElement();
                Enumeration ee = n.getInetAddresses();
                while (ee.hasMoreElements()) {
                    InetAddress i = (InetAddress) ee.nextElement();
                    if (i.getHostAddress().contains(ipPattern)) {
                        ipAddress = i.getHostAddress();
                        adminIp = ipAddress;
                        ipFound = true;
                    }
                }
            }
            if (ipFound) {
                // network found
                System.out.println("Admin ip  " + adminIp);
            } else {
                JOptionPane.showMessageDialog(null, "Not connected to network... exiting!");
                System.exit(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
