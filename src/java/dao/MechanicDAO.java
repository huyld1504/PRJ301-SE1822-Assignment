/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Mechanic;
import utils.DBUtils;

/**
 *
 * @author Asus
 */
public class MechanicDAO {

    public Mechanic login(String username) {
        Mechanic m = null;
        Connection conn = null;
        PreparedStatement p;
        ResultSet table;

        try {
            conn = DBUtils.getConnection();
            System.out.println(conn);

            if (conn != null) {
                String sql = "SELECT [mechanicID]\n"
                        + "      ,[mechanicName]\n"
                        + "  FROM [dbo].[Mechanic]\n"
                        + "  WHERE mechanicName = ?";
                p = conn.prepareStatement(sql);
                p.setString(1, username);
                table = p.executeQuery();
                
                while(table.next()) {
                    String mechanicID = table.getString("mechanicID");
                    m = new Mechanic(mechanicID, username);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return m;
    }
    
    public Mechanic getMechanicByID(String id) {
        Mechanic m = null;
        Connection conn = null;
        PreparedStatement p;
        ResultSet table;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = "SELECT [mechanicID]\n"
                        + "      ,[mechanicName]\n"
                        + "  FROM [dbo].[Mechanic]\n"
                        + "  WHERE [mechanicID] = ?";
                p = conn.prepareStatement(sql);
                p.setString(1, id);
                table = p.executeQuery();
                
                while(table.next()) {
                    String mechanicName = table.getString("mechanicName");
                    m = new Mechanic(id, mechanicName);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return m;
    }
}
