/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jithinpv
 */
public class Dbcon {
    public Connection con = null;
    public Statement stmt = null;
    public ResultSet rs = null;

    public Dbcon() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://localhost/multicast_video_broadcasting", "root", "root");
            con = DriverManager.getConnection("jdbc:mysql://localhost/multicast_video_broadcasting", "root", "root");
            stmt = con.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public int insert(String sql) {
        System.out.println(sql);
        int r = 0;
        try {
            r = stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public ResultSet select(String sql) {
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public int update(String sql) {
        System.out.println(sql);
        int a = 0;
        try {
            a = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return a;
    }
    
    public boolean registerOrganisation(String organization_name, String countryId, String stateId, String description,String ip_address, String port,String filePath ) {
        try {
            String querySetLimit = "SET GLOBAL max_allowed_packet=104857600;";  // 10 MB
            Statement stSetLimit = con.createStatement();
            stSetLimit.execute(querySetLimit);
            String sql = "INSERT INTO tbl_organization ("
                    + " organization_name, "
                    + " country,"
                    + " state,"
                    + " description,"
                    + " ip_address,"
                    + " port,"
                    + " created_at,"
                    + " photo) values (?, ?, ?,?,?, ?, ?,?)";
            PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
            statement.setString(1, organization_name);
            statement.setString(2, countryId);
            statement.setString(3, stateId);
            statement.setString(4, description);
            statement.setString(5, ip_address);
            statement.setString(6, port);
            statement.setString(7, System.currentTimeMillis()+"");
            InputStream inputStream = new FileInputStream(new File(filePath));
            statement.setBlob(8, inputStream);
            
            int row = statement.executeUpdate();
            con.close();
            inputStream.close();
            if (row > 0) {
                System.out.println("Sucess");
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
}
