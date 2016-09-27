/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ViewAllOrganisations.java
 *
 * Created on Sep 23, 2016, 10:59:49 PM
 */
package view;

import General.Configuration;
import db.Dbcon;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kakes
 */
public class ViewAllOrganisations extends javax.swing.JFrame {

    private Pattern pattern;
    private Matcher matcher;
    private static final String IPADDRESS_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    
    /** Creates new form ViewAllOrganisations */
    public ViewAllOrganisations() {
        initComponents();
        this.setLocationRelativeTo(null);
        loadAllOrganisation();
        save_button.setEnabled(false);
        pattern = Pattern.compile(IPADDRESS_PATTERN);
         Configuration.setIconOnLabel("launch_sample.jpg", jLabel3);
    }

    private void loadAllOrganisation() {
        try {
            clearTable();
            String str = "select * from tbl_organization order by organization_id desc";
            ResultSet rs = new Dbcon().select(str);
            String arr[] = new String[10];
            DefaultTableModel model = (DefaultTableModel) all_organisation.getModel();
            int count = 0;
            while (rs.next()) {

                String organization_id = rs.getString("organization_id");
                String organization_name = rs.getString("organization_name");
                String country = rs.getString("country");
                String state = rs.getString("state");
                String ip_address = rs.getString("ip_address");
                String port = rs.getString("port");
                count++;
                arr[0] = count + "";
                arr[1] = organization_name;
                arr[2] = state;
                arr[3] = country;
                arr[4] = ip_address;
                arr[5] = port;
                arr[6] = organization_id;

                model.addRow(arr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        all_organisation = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ip_address_text = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        port_text = new javax.swing.JTextField();
        save_button = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        all_organisation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Name", "State", "Country", "IP Address", "Port", "org_id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        all_organisation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                all_organisationMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                all_organisationMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(all_organisation);
        if (all_organisation.getColumnModel().getColumnCount() > 0) {
            all_organisation.getColumnModel().getColumn(0).setMinWidth(50);
            all_organisation.getColumnModel().getColumn(0).setPreferredWidth(50);
            all_organisation.getColumnModel().getColumn(0).setMaxWidth(50);
            all_organisation.getColumnModel().getColumn(6).setMinWidth(0);
            all_organisation.getColumnModel().getColumn(6).setPreferredWidth(0);
            all_organisation.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 14, 590, 240));

        jButton1.setText("Launch");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 125, -1));

        jLabel1.setText("Ip Address");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 264, 72, -1));
        getContentPane().add(ip_address_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 261, 176, -1));

        jLabel2.setText("Port");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 264, 37, -1));
        getContentPane().add(port_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 261, 80, -1));

        save_button.setText("Save");
        save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(save_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(591, 260, -1, -1));

        jButton2.setText("Home");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(523, 289, 125, -1));

        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, -6, 650, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    if (all_organisation.getSelectedRow() >= 0) {
        String organisation_id = all_organisation.getValueAt(all_organisation.getSelectedRow(), 6).toString();
        System.out.println("organisation_id " + organisation_id);
        ResultSet rs = new Dbcon().select("select * from organisation_hotline where organisation_id=" + organisation_id.trim());
        try {
            if (rs.next()) {
                // already there , check if offline or online
                String status_code = rs.getString("status_code");
                if (status_code.trim().equals("2")) {
                    // user is offline
                    int updated = new Dbcon().update("update organisation_hotline set status_code=1 where organisation_id=" + organisation_id);
                    if (updated > 0) {
                        // launch organisation listner
                        OrganisationClient organisationClient = new OrganisationClient(organisation_id);
                        organisationClient.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Could not launch, please try again later");
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Organisation in already active");
                }
            } else {
                // not there yet, add it
                int insert = new Dbcon().insert("insert into organisation_hotline (organisation_id,status_code) values (" + organisation_id + " ,1)");
                if (insert > 0) {
                    // launch organisation listner
                    OrganisationClient organisationClient = new OrganisationClient(organisation_id);
                    organisationClient.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Could not launch, please try again later");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    } else {
        JOptionPane.showMessageDialog(rootPane, "Please select an organisation to launch");
    }




    // TODO add your handling code here:
}//GEN-LAST:event_jButton1ActionPerformed

    private void clearTable() throws Exception {
        DefaultTableModel dm = (DefaultTableModel) all_organisation.getModel();
        int rowCount = dm.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
    }

private void all_organisationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_all_organisationMouseClicked

    try {
        if (all_organisation.getSelectedRow() >= 0) {
            String ipAddress = all_organisation.getValueAt(all_organisation.getSelectedRow(), 4).toString();
            String port = all_organisation.getValueAt(all_organisation.getSelectedRow(), 5).toString();
            ip_address_text.setText(ipAddress);
            port_text.setText(port);
            save_button.setEnabled(true);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    // TODO add your handling code here:
}//GEN-LAST:event_all_organisationMouseClicked

private void all_organisationMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_all_organisationMousePressed
// TODO add your handling code here:
}//GEN-LAST:event_all_organisationMousePressed

private void save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_buttonActionPerformed

    if (all_organisation.getSelectedRow() >= 0) {
        String organisation_id = all_organisation.getValueAt(all_organisation.getSelectedRow(), 6).toString();
        System.out.println("organisation_id " + organisation_id);
        String ipAddress = ip_address_text.getText().trim();
        String port = port_text.getText().trim();

        if(ipAddress.trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Enter Ip address");
            return;
        }
        
        if (port.equals("")) {
            JOptionPane.showMessageDialog(this, "Enter port");
            return;
        }
        
        try {
            int portNo = Integer.parseInt(port);
            if (portNo < 1024 || portNo > 10000) {
                JOptionPane.showMessageDialog(rootPane, "Port must be within range 1024 - 10000 ");
                return;
            }
        } catch (NumberFormatException ne) {
            JOptionPane.showMessageDialog(rootPane, "Enter an integer for port");
            return;
        }
        
        matcher = pattern.matcher(ipAddress);
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(rootPane, "Enter proper IP Address");
            return;
        }
        
        String sql = "update tbl_organization set ip_address='" + ipAddress + "' , port='" + port + "' where organization_id=" + organisation_id;
        int update = new Dbcon().update(sql);
        if (update > 0) {
            JOptionPane.showMessageDialog(rootPane, "Sucessfully updated");
            loadAllOrganisation();
            save_button.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Could not update now, please try again after some time");
        }
    }
    // TODO add your handling code here:
}//GEN-LAST:event_save_buttonActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    new AdminHome().setVisible(true);
    dispose();
    // TODO add your handling code here:
}//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(ViewAllOrganisations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewAllOrganisations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewAllOrganisations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewAllOrganisations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ViewAllOrganisations().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable all_organisation;
    private javax.swing.JTextField ip_address_text;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField port_text;
    private javax.swing.JButton save_button;
    // End of variables declaration//GEN-END:variables
}
