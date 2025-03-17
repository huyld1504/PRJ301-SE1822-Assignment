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
import java.util.Date;
import models.SalesInvoice;
import utils.DBUtils;

public class InvoiceDAO {

    public ArrayList<SalesInvoice> getInvoices(String id, int flag) {
        ArrayList<SalesInvoice> rs = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();

            if (cn != null) {
                String sql = "SELECT [invoiceID]\n"
                        + "      ,[invoiceDate]\n"
                        + "      ,[salesID]\n"
                        + "      ,[carID]\n"
                        + "      ,[custID]\n"
                        + "  FROM [Car_Dealership].[dbo].[SalesInvoice]\n";

                if (flag == 1) {
                    sql = sql + "  WHERE [custID] = ?";
                } else if (flag == 2) {
                    sql = sql + " WHERE [salesID] = ?";
                }

                PreparedStatement st = cn.prepareStatement(sql);
                if (flag == 1 || flag == 2) {
                    st.setString(1, id);
                }
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int invid = table.getInt("invoiceID");
                        Date createdate = table.getDate("invoiceDate");
                        String saleid = table.getString("salesID");
                        String carid = table.getString("carID");
                        String custid = table.getString("custid");

                        SalesInvoice i = new SalesInvoice(invid, createdate, saleid, carid, custid);
                        rs.add(i);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }

    public boolean createInvoice(String saleID, String carID, String custID) {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "INSERT INTO SalesInvoice (invoiceDate, salesID, carID, custID) VALUES (GETDATE(), ?, ?, ?)";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, saleID);
                st.setString(2, carID);
                st.setString(3, custID);

                int rows = st.executeUpdate();
                return rows > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
