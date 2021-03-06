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
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Jithinpv
 */
public class ChooseCountries extends javax.swing.JFrame {

    /**
     * Creates new form CreateMulticastSubscription
     */
    public ChooseCountries() {
        initComponents();
        this.setLocationRelativeTo(null);
        loadIcons();
        loadAllCountry();
    }

    private void loadIcons() {
        Configuration.setIconOnLabel("rightArrow.png", jLabel6);
        Configuration.setIconOnLabel("leftArrow.png", jLabel5);
        Configuration.setIconOnLabel("choose.jpg", jLabel4);
    }

    private void loadAllCountry() {
        Dbcon dbcon = new Dbcon();
        ResultSet rs = dbcon.select("select * from tbl_country");
        try {
            while (rs.next()) {
                String countryName = rs.getString("country_name");
                System.out.println(countryName);
                country_list.addItem(countryName);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        country_list = new java.awt.List();
        jLabel3 = new javax.swing.JLabel();
        selected_country = new java.awt.List();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Choose Countries:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 43, 164, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("All Countries");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 89, -1, -1));
        getContentPane().add(country_list, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 113, 91, 150));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Selected Countries");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(353, 89, -1, -1));
        getContentPane().add(selected_country, new org.netbeans.lib.awtextra.AbsoluteConstraints(353, 113, 91, 150));

        jButton1.setText("NEXT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 325, -1, -1));

        jButton2.setText("BACK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 325, -1, -1));

        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 200, 146, 54));

        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 113, 146, 54));

        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -6, 550, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private ArrayList<String> generateCountryIds() {
        ArrayList<String> countryIds = new ArrayList<String>();
        String[] items = selected_country.getItems();

        if (items.length > 0) {
            Dbcon dbcon = new Dbcon();

            for (int i = 0; i < items.length; i++) {
                ResultSet rs = dbcon.select("select country_id from tbl_country where country_name='" + items[i] + "'");
                try {
                    if (rs.next()) {
                        countryIds.add(rs.getString("country_id"));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }
        return countryIds;
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ArrayList<String> countryIds = generateCountryIds();
        if (countryIds.size() > 0) {
            Dbcon dbcon=new Dbcon();
           
           
            this.dispose();
            ChooseStates3 chooseStates = new ChooseStates3(countryIds);
            chooseStates.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(rootPane, "select country");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        AdminHome adminHome = new AdminHome();
        adminHome.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:

        Dbcon dbcon = new Dbcon();
        ResultSet rs = dbcon.select("select * from tbl_country");
        try {
            while (rs.next()) {

            }
        } catch (SQLException ex) {
            // Logger.getLogger(ChooseCountries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        if (country_list.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(rootPane, "select a country");
        } else {
            String selectedItem = country_list.getSelectedItem();
            selected_country.addItem(selectedItem);
            country_list.remove(selectedItem);
        }
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        if (selected_country.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(rootPane, "select a country");
        } else {
            String selectedItem = selected_country.getSelectedItem();
            selected_country.remove(selectedItem);
            country_list.addItem(selectedItem);
        }
    }//GEN-LAST:event_jLabel5MouseClicked

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
            java.util.logging.Logger.getLogger(ChooseCountries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChooseCountries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChooseCountries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChooseCountries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChooseCountries().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.List country_list;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private java.awt.List selected_country;
    // End of variables declaration//GEN-END:variables
}
