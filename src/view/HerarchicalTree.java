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
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Jithinpv
 */
public class HerarchicalTree extends javax.swing.JFrame {

    /**
     * Creates new form HerarchicalTree
     */
    public HerarchicalTree() {
        initComponents();
        //loadTree();
        this.setLocationRelativeTo(null);
        Configuration.setIconOnLabel("heirarchy-background.jpg", jLabel2);
        loadTree2();
        expandNodes();
    }

    private void loadTree2() {
        String sql = "SELECT state.state_name  , cnt.country_name, org.* "
                + " FROM tbl_organization AS org ,"
                + " tbl_country AS cnt , "
                + " tbl_state AS state "
                + " WHERE org.country = cnt.country_id AND "
                + " org.state = state.state_id;";
        //ResultSet select = new Dbcon().select(sql);

        Dbcon dbcon1 = new Dbcon();
        Dbcon dbcon2 = new Dbcon();
        Dbcon dbcon3 = new Dbcon();

        String sql1 = "select * from tbl_country ";
        ResultSet rs1 = dbcon1.select(sql1);
        try {

            DefaultTreeModel model = (DefaultTreeModel) jTree2.getModel();
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

            while (rs1.next()) {
                String country_name = rs1.getString("country_name");
                String country_id = rs1.getString("country_id");
                DefaultMutableTreeNode defaultMutableTreeNode1 = new DefaultMutableTreeNode(country_name);
                String sql2 = "select * from tbl_state where country=" + country_id;

                ResultSet rs2 = dbcon2.select(sql2);
                while (rs2.next()) {
                    String state_name = rs2.getString("state_name");
                    String state_id = rs2.getString("state_id");

                    DefaultMutableTreeNode defaultMutableTreeNode2 = new DefaultMutableTreeNode(state_name);

                    defaultMutableTreeNode1.add(defaultMutableTreeNode2);

                    String sql3 = "select * from tbl_organization where state=" + state_id;
                    ResultSet rs3 = dbcon3.select(sql3);

                    while (rs3.next()) {
                        String organization_name = rs3.getString("organization_name");
                        DefaultMutableTreeNode defaultMutableTreeNode3 = new DefaultMutableTreeNode(organization_name);
                        defaultMutableTreeNode2.add(defaultMutableTreeNode3);
                    }
//                    defaultMutableTreeNode1.add(defaultMutableTreeNode2);
                }
                root.add(defaultMutableTreeNode1);
                root.setUserObject("multicast");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql2 = "select * from tbl_organization";
        jTree2.repaint();
        jTree2.revalidate();

    }
//    
// for (String ar : arr) {
//            DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(ar);
//            for (String ar2 : arr2) {
//                DefaultMutableTreeNode defaultMutableTreeNode2 = new DefaultMutableTreeNode(ar2);
//                for (String ar3 : arr3) {
//                    System.err.println("adding " + ar3);
//                    DefaultMutableTreeNode defaultMutableTreeNode3 = new DefaultMutableTreeNode(ar3);
//                    defaultMutableTreeNode2.add(defaultMutableTreeNode3);
//                }
//                defaultMutableTreeNode.add(defaultMutableTreeNode2);
//            }
//            root.add(defaultMutableTreeNode);
//        }

    private void loadTree() {

        DefaultTreeModel model = (DefaultTreeModel) jTree2.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

        ArrayList<String> arr = new ArrayList<>();
        String country_id="";
        Dbcon dbcon = new Dbcon();
        ResultSet rs = dbcon.select("select * from tbl_country");
        try {
            while (rs.next()) {
                String countryName = rs.getString("country_name");
                country_id=rs.getString("country_id");
                arr.add(countryName);
                System.out.println(countryName);
                System.out.println(arr);
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        ArrayList<String> arr2 = new ArrayList<>();
        String state_id="";
        ResultSet r = dbcon.select("select * from tbl_state where country='"+country_id+"'");
        try {
            while (r.next()) {
                String stateName = r.getString("state_name");
                state_id=r.getString("state_id");
                arr2.add(stateName);
                System.out.println(stateName);
                System.out.println(arr2);
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        ArrayList<String> arr3 = new ArrayList<>();

        ResultSet rst = dbcon.select("select * from tbl_organization where country='"+country_id+"' and state='"+state_id+"'");
        try {
            while (rst.next()) {
                String orgName = rst.getString("organization_name");

                arr3.add(orgName);
                System.out.println(orgName);
                System.out.println(arr3);
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        for (String ar : arr) {
            DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(ar);
            for (String ar2 : arr2) {
                DefaultMutableTreeNode defaultMutableTreeNode2 = new DefaultMutableTreeNode(ar2);
                for (String ar3 : arr3) {
                    System.err.println("adding " + ar3);
                    DefaultMutableTreeNode defaultMutableTreeNode3 = new DefaultMutableTreeNode(ar3);
                    defaultMutableTreeNode2.add(defaultMutableTreeNode3);
                }
                defaultMutableTreeNode.add(defaultMutableTreeNode2);
            }
            root.add(defaultMutableTreeNode);
        }
        jTree2.repaint();
        jTree2.revalidate();
        root.setUserObject("multicast");
    }

    private void expandNodes() {
        for (int i = 0; i < jTree2.getRowCount(); i++) {
            jTree2.expandRow(i);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTree2 = new javax.swing.JTree();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree2.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree2.setMaximumSize(new java.awt.Dimension(59, 50));
        jTree2.setPreferredSize(new java.awt.Dimension(59, 50));
        jScrollPane2.setViewportView(jTree2);

<<<<<<< .mine
        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 54, 430, 410));
=======
        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 54, 278, 300));
>>>>>>> .theirs

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MULTICAST HEIRARCHY");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 240, 25));

<<<<<<< .mine
        jButton1.setText("HOME");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 440, -1, -1));

=======
        jButton1.setText("HOME");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, -1, -1));

>>>>>>> .theirs
        jLabel2.setText("jLabel2");
<<<<<<< .mine
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -6, 670, 490));
        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, -1, -1));
=======
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -6, 410, 430));

>>>>>>> .theirs

        pack();
    }// </editor-fold>//GEN-END:initComponents

<<<<<<< .mine
private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    AdminHome adminHome = new AdminHome();
    adminHome.setVisible(true);
    this.dispose();
    // TODO add your handling code here:
}//GEN-LAST:event_jButton1ActionPerformed

=======
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        AdminHome home=new AdminHome();
        home.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


>>>>>>> .theirs
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
            java.util.logging.Logger.getLogger(HerarchicalTree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HerarchicalTree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HerarchicalTree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HerarchicalTree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new HerarchicalTree().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTree jTree2;
    // End of variables declaration//GEN-END:variables
}
