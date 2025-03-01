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
import models.Customer;
import utils.DBUtils;

/**
 *
 * @author Asus
 */
public class CustomerDAO {

    public Customer login(String username, String phone) {
        Customer r = null;
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT [custID]\n"
                        + "      ,[custName]\n"
                        + "      ,[phone]\n"
                        + "      ,[sex]\n"
                        + "      ,[cusAddress]\n"
                        + "FROM [dbo].[Customer]\n"
                        + "WHERE [custName]= ? AND [phone] = ?";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, username);
                p.setString(2, phone);
                
                ResultSet table = p.executeQuery();
                while(table.next()) {
                    String custID = table.getString("custID");
                    String custName = table.getString("custName");
                    String custPhone = table.getString("phone");
                    String sex = table.getString("sex");
                    String cusAddress = table.getString("cusAddress");
                    
                    r = new Customer(custID, custName, phone, cusAddress, sex);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return r;
    }
}
