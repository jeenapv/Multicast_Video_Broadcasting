/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

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
         loadTree();
        this.setLocationRelativeTo(null);
    }
    private void loadTree(){
   
        DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
       
            
            
        ArrayList<String> arr = new ArrayList<>();
        
        
         Dbcon dbcon = new Dbcon();
        ResultSet rs = dbcon.select("select * from tbl_country");
        try {
            while (rs.next()) {
                String countryName = rs.getString("country_name");
                
                arr.add(countryName);
                System.out.println(countryName);
                System.out.println(arr);
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
       
       
        ArrayList<String> arr2 = new ArrayList<>();
        
        
        ResultSet r = dbcon.select("select * from tbl_state");
        try {
            while (r.next()) {
                String stateName = r.getString("state_name");
              
                arr2.add(stateName);
                  System.out.println(stateName);
                  System.out.println(arr2);
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
 
        ArrayList<String> arr3 = new ArrayList<>();
             
        ResultSet rst = dbcon.select("select * from tbl_organization");
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
       
        for(String ar : arr) {
            DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(ar);
            for (String ar2 : arr2) {
                DefaultMutableTreeNode defaultMutableTreeNode2 = new DefaultMutableTreeNode(ar2);
                for (String ar3 : arr3) {
                    DefaultMutableTreeNode defaultMutableTreeNode3 = new DefaultMutableTreeNode(ar3);
                    defaultMutableTreeNode2.add(defaultMutableTreeNode3);
                }
                defaultMutableTreeNode.add(defaultMutableTreeNode2);               
            }
            root.add(defaultMutableTreeNode);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.setEditable(true);
        jTree1.setEnabled(false);
        jTree1.setExpandsSelectedPaths(false);
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTree1MouseClicked

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
