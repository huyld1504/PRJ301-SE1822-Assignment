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
import java.util.Date;
import models.SalesPerson;
import utils.DBUtils;

/**
 *
 * @author Thanh Vinh
 */
public class SalePersonDAO {
    
    public SalesPerson checkLogin(String name){
        SalesPerson sp =null;
        Connection conn = null;
        
        try{
          conn = DBUtils.getConnection();
          System.out.println(conn);

            if (conn != null) {
                String sql = "select salesID,salesName,birthday,sex,salesAddress\n"
                        + "from dbo.SalesPerson\n"
                        + "where salesName=?";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, name);
                ResultSet table = p.executeQuery();

                while (table.next()) {
                    String salesid = table.getString("salesID");
                    String salesname = table.getString("salesName");
                    String salesadd = table.getString("salesAddress");
                    String sex = table.getString("sex");
                    Date bd = table.getDate("birthday");

                    sp = new SalesPerson(salesid, salesname, salesadd, sex, bd);

                }
            }
        
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sp;
    }
    

    
    
    
    
    
    
}
