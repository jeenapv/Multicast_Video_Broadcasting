/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicastvideobroadcasting;

import General.Configuration;
import view.AdminLogin;

/**
 *
 * @author Jithinpv
 */
public class MulticastVideobroadcasting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Configuration.initializeEnvironment();
        AdminLogin adminLogin = new AdminLogin();
        adminLogin.setVisible(true);
    }
}
