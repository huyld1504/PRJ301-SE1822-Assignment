/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.PartsUsed;
import utils.DBUtils;

/**
 *
 * @author Asus
 */
public class PartUsedDAO {

    public ArrayList<PartsUsed> getPartsUsedByServiceTicketID(String serviceTicketID) {
        ArrayList<PartsUsed> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps;
        ResultSet table;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = "SELECT [serviceTicketID]\n"
                        + "      ,[partID]\n"
                        + "      ,[numberUsed]\n"
                        + "      ,[price]\n"
                        + "  FROM [dbo].[PartsUsed]\n"
                        + "  where serviceTicketID = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, serviceTicketID);

                table = ps.executeQuery();
                while (table.next()) {
                    String partID = table.getString("partID");
                    int numberUsed = table.getInt("numberUsed");
                    Double price = table.getDouble("price");
                    PartsUsed p = new PartsUsed(serviceTicketID, partID, numberUsed, price);
                    list.add(p);
                }
            }
        } catch (Exception e) {
        }
        return list;
    }
}
