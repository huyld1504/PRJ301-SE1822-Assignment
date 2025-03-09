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
import java.util.ArrayList;
import models.Service;
import utils.DBUtils;

/**
 *
 * @author Asus
 */
public class ServiceDAO {

    public ArrayList<Service> getServiceByID() {
        ArrayList<Service> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps;
        ResultSet table;
        
        try {
            conn = DBUtils.getConnection();
            
            if(conn != null) {
                String sql = "";
            }
        } catch (Exception e) {
        } finally{
            try {
                if(conn != null) conn.close();
            } catch (SQLException e) {
            }
        }

        return result;
    }
}
