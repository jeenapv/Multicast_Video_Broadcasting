/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * OrganisationClient.java
 *
 * Created on Sep 24, 2016, 12:01:38 AM
 */
package view;

import General.Configuration;
import db.Dbcon;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Blob;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author kakes
 */
public class OrganisationClient extends javax.swing.JFrame {

    String organisationId;
    File lastReceivedFile = null;

    /**
     * Creates new form OrganisationClient
     */
    public OrganisationClient() {
        initComponents();
        this.setLocationRelativeTo(null);
        file_open_button.setEnabled(false);
        jPanel1.setVisible(false);
    }

    public OrganisationClient(String organisationId) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.organisationId = organisationId;
        file_open_button.setEnabled(false);
        jPanel1.setVisible(false);
        this.setLocationRelativeTo(null);
        loadOrganisationDetails();
    }

    class PresentationReceiverThread extends Thread {

        String adminIp;
        int ftpPort;
        String fileName;

        private PresentationReceiverThread(String adminIp, int ftpPort, String fileName) {
            this.adminIp = adminIp;
            this.ftpPort = ftpPort;
            this.fileName = fileName;
        }

        public void run() {
            System.out.println("File receiver thread started ");
            try {
                System.out.println("Client started");
                Socket socket = new Socket(InetAddress.getByName(adminIp), ftpPort);
                byte[] contents = new byte[10000];

                File temporaryFolder = new File(Configuration.tempFiles + System.currentTimeMillis() + "/");
                temporaryFolder.mkdir();
                File fileToBeCreated = new File(temporaryFolder + "file_" + fileName.trim());
                System.out.println("Creating file " + fileToBeCreated.getAbsolutePath());
                FileOutputStream fos = new FileOutputStream(fileToBeCreated);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                InputStream is = socket.getInputStream();

                //No of bytes read in one read() call
                int bytesRead = 0;
                while ((bytesRead = is.read(contents)) != -1) {
                    bos.write(contents, 0, bytesRead);
                }
                bos.flush();
                socket.close();
                System.out.println("File saved successfully!");
                lastReceivedFile = fileToBeCreated;
                JOptionPane.showMessageDialog(rootPane, "Video presentation received!");
                file_open_button.setEnabled(true);
                jPanel1.setVisible(true);
                bit_rate.setEditable(false);
                file_size.setEditable(false);
                total_time.setEditable(false);
                frame_rate.setEditable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadOrganisationDetails() {
        try {
            ResultSet rs = new Dbcon().select("select * from tbl_organization where organization_id=" + organisationId.trim());
            if (rs.next()) {
                String organization_name = rs.getString("organization_name");
                String description = rs.getString("description");
                String ip_address = rs.getString("ip_address");
                String port = rs.getString("port");
                startAgent(port);

                Blob blob = rs.getBlob("photo");
                if (blob != null) {
                    try {
                        InputStream binaryStream = blob.getBinaryStream();
                        BufferedImage image = ImageIO.read(binaryStream);
                        Image img = image;
                        Image scaledInstance = img.getScaledInstance(org_image_label.getWidth(), org_image_label.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon imageIcon = new ImageIcon(scaledInstance);
                        org_image_label.setIcon(imageIcon);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error in loading image icon ");
                    }
                } else {
                    System.out.println("Blob is null");
                }

                organisation_name_label.setText(organization_name);
                ipaddress_label.setText(ip_address);
                port_label.setText(port);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    DatagramSocket serverSocket;

    private void closeSocket() {
        try {
            serverSocket.close();
        } catch (Exception e) {
        }
    }

    class ServerThread extends Thread {

        String portString;

        private ServerThread(String portString) {
            this.portString = portString;
        }

        public void run() {
            try {
                int port = Integer.parseInt(portString);
                serverSocket = new DatagramSocket(port);
                byte[] receiveData = new byte[1024];
                byte[] sendData = new byte[1024];
                System.out.println("Server listening on " + port);
                while (true) {
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    serverSocket.receive(receivePacket);
                    String sentence = new String(receivePacket.getData());
                    System.out.println("RECEIVED: " + sentence);
                    String[] split = sentence.split(deLimiter);
                    String adminIp = split[0];
                    String ftpPort = split[1];
                    String fileName = split[2];
                    new PresentationReceiverThread(adminIp, Integer.parseInt(ftpPort.trim()), fileName).start();

                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    String deLimiter = "#######";

    private void startAgent(String portString) {
        new ServerThread(portString).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        organisation_name_label = new javax.swing.JLabel();
        org_image_label = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        ipaddress_label = new javax.swing.JLabel();
        port_label = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        file_open_button = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        bit_rate = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        total_time = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        frame_rate = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        file_size = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        ftp_port = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        organisation_name_label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        organisation_name_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        organisation_name_label.setText("Organisation name");

        org_image_label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Ip address");

        ipaddress_label.setText("127.0.0.1");

        port_label.setText("1234");

        jLabel6.setText("Port");

        jLabel1.setText("LOGOUT");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Listner health : Active");

        file_open_button.setText("Open");
        file_open_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                file_open_buttonActionPerformed(evt);
            }
        });

        jLabel4.setText("Bit Rate");

        jLabel5.setText("Total turnaround time");

        total_time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_timeActionPerformed(evt);
            }
        });

        jLabel7.setText("Frame rate");

        frame_rate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frame_rateActionPerformed(evt);
            }
        });

        jLabel8.setText("File Size");

        file_size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                file_sizeActionPerformed(evt);
            }
        });

        jLabel9.setText("FTP port");

        ftp_port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftp_portActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bit_rate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(total_time, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(frame_rate)
                            .addComponent(file_size)
                            .addComponent(ftp_port))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(bit_rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(total_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(frame_rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(file_size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(ftp_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(organisation_name_label, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(org_image_label, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)))
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(port_label, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(ipaddress_label, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(148, 148, 148)
                                        .addComponent(file_open_button)))
                                .addGap(41, 41, 41))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(organisation_name_label, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(org_image_label, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ipaddress_label, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(port_label, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(file_open_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logout() {
        try {
            int updated = new Dbcon().update("update organisation_hotline set status_code=2 where organisation_id=" + organisationId);
            closeSocket();
            if (updated > 0) {
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Some issue in database connection, couldn't logout succesfully, Please check database");
                this.dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

    logout();
    // TODO add your handling code here:
}//GEN-LAST:event_jLabel1MouseClicked

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    logout();
    // TODO add your handling code here:
}//GEN-LAST:event_formWindowClosing

    private void file_open_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_file_open_buttonActionPerformed
        try {
            Desktop.getDesktop().open(lastReceivedFile);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "File could not be opened!");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_file_open_buttonActionPerformed

    private void total_timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_timeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total_timeActionPerformed

    private void frame_rateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frame_rateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_frame_rateActionPerformed

    private void file_sizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_file_sizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_file_sizeActionPerformed

    private void ftp_portActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftp_portActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftp_portActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrganisationClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrganisationClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrganisationClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrganisationClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new OrganisationClient().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bit_rate;
    private javax.swing.JButton file_open_button;
    private javax.swing.JTextField file_size;
    private javax.swing.JTextField frame_rate;
    private javax.swing.JTextField ftp_port;
    private javax.swing.JLabel ipaddress_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel org_image_label;
    private javax.swing.JLabel organisation_name_label;
    private javax.swing.JLabel port_label;
    private javax.swing.JTextField total_time;
    // End of variables declaration//GEN-END:variables
}
