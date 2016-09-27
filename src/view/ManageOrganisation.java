/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import General.Configuration;
import db.Dbcon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jithinpv
 */
public class ManageOrganisation extends javax.swing.JFrame {

    private Pattern pattern;
    private Matcher matcher;
    private static final String IPADDRESS_PATTERN
            = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    /**
     * Creates new form ManageOrganisation
     */
    public ManageOrganisation() {
        initComponents();
        loadAllOrg();
        this.setLocationRelativeTo(null);
        Configuration.setIconOnLabel("create.jpg", jLabel7);
    }

    private void loadAllOrg() {
        DefaultTableModel model = (DefaultTableModel) tbl_org.getModel();
        String arr[] = new String[3];
        Dbcon dbcon = new Dbcon();
        ResultSet rs = dbcon.select("select * from tbl_organization");
        try {
            while (rs.next()) {
                String orgName = rs.getString("organization_name");
                String org_id = rs.getString("organization_id");
                System.out.println(orgName);
                arr[0] = org_id;
                arr[1] = orgName;
                model.addRow(arr);
                name.setEditable(false);
                description.setEditable(false);
                edit_button.setEnabled(false);
                save_button.setEnabled(false);
                port.setEditable(false);
                ip.setEditable(false);

            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_org = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        edit_button = new javax.swing.JButton();
        save_button = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        port = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ip = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_org.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ORGANIZATIONS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_org.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_orgMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_org);
        if (tbl_org.getColumnModel().getColumnCount() > 0) {
            tbl_org.getColumnModel().getColumn(0).setMinWidth(50);
            tbl_org.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbl_org.getColumnModel().getColumn(0).setMaxWidth(50);
            tbl_org.getColumnModel().getColumn(1).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 43, 205, 239));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MANAGE ORGANIZATIONS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 11, 230, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("NAME");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(257, 54, -1, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("DESCRIPTION");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(257, 107, -1, -1));
        getContentPane().add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 51, 146, -1));

        description.setColumns(20);
        description.setRows(5);
        jScrollPane2.setViewportView(description);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 89, 146, 82));

        edit_button.setText("EDIT");
        edit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(edit_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 293, -1, -1));

        save_button.setText("SAVE");
        save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(save_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(404, 293, -1, -1));

        jButton3.setText("HOME");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 293, -1, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("PORT");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(257, 192, 68, -1));
        getContentPane().add(port, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 189, 146, -1));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("IP ADDRESS");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(257, 230, -1, -1));
        getContentPane().add(ip, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 227, 146, -1));

        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -6, 570, 360));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        AdminHome home = new AdminHome();
        home.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tbl_orgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_orgMouseClicked
        // TODO add your handling code here:
        if (tbl_org.getSelectedRow() >= 0) {
            String organization_id = tbl_org.getValueAt(tbl_org.getSelectedRow(), 0).toString();
            Dbcon dbcon = new Dbcon();
            ResultSet rs = dbcon.select("select * from tbl_organization where organization_id='" + organization_id + "' ");

            try {
                if (rs.next()) {
                    name.setText(rs.getString("organization_name"));
                    description.setText(rs.getString("description"));
                    port.setText(rs.getString("port"));
                    ip.setText(rs.getString(rs.getShort("ip_address")));
                    edit_button.setEnabled(true);
                    save_button.setEnabled(true);
                }
            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_tbl_orgMouseClicked

    private void edit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_buttonActionPerformed
        // TODO add your handling code here:
        name.setEditable(true);
        description.setEditable(true);
        port.setEditable(true);
        ip.setEditable(true);
    }//GEN-LAST:event_edit_buttonActionPerformed

    private void save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_buttonActionPerformed
        // TODO add your handling code here:
        String org_id = tbl_org.getValueAt(tbl_org.getSelectedRow(), 0).toString();
        String org_name = name.getText();
        String desc = description.getText();
        String ip_port = port.getText();
        String ip_address = ip.getText();

        matcher = pattern.matcher(ip_address);

        int portNo = Integer.parseInt(ip_port);
        if (org_name.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Enter name");
        } else if (desc.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Enter description");
        } else if (ip_port.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Enter port");
        } else if (ip_address.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Enter ip address");
        } else if (!matcher.matches()) {
            JOptionPane.showMessageDialog(rootPane, "Enter proper IP Address");
            return;
        } else if (portNo < 1024 || portNo > 10000) {
            try {

                JOptionPane.showMessageDialog(rootPane, "Port must be within range 1024 - 10000 ");
                return;

            } catch (NumberFormatException ne) {
                JOptionPane.showMessageDialog(rootPane, "Enter an integer for port");
                return;
            }
        } else {

            Dbcon dbcon = new Dbcon();
            dbcon.update("update tbl_organization set organization_name='" + org_name + "', description='" + desc + "',port='" + ip_port + "',ip_address='" + ip_address + "' where organization_id='" + org_id + "'");
        }

    }//GEN-LAST:event_save_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(ManageOrganisation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageOrganisation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageOrganisation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageOrganisation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageOrganisation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea description;
    private javax.swing.JButton edit_button;
    private javax.swing.JTextField ip;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField name;
    private javax.swing.JTextField port;
    private javax.swing.JButton save_button;
    private javax.swing.JTable tbl_org;
    // End of variables declaration//GEN-END:variables
}
