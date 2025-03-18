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

    public boolean createInvoice(int invoiceID, String saleID, String carID, String custID) {
        String sql = "INSERT INTO SalesInvoice (invoiceID, invoiceDate, saleID, carID, custID) VALUES (?, GETDATE(), ?, ?, ?)";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, invoiceID);
            ps.setString(2, saleID);
            ps.setString(3, carID);
            ps.setString(4, custID);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<SalesInvoice> getInvoicesBySaleID(String saleID) {
        ArrayList<SalesInvoice> invoices = new ArrayList<>();
        String query = "SELECT si.invoiceID,si.invoiceDate,si.salesID, si.carID,si.custID FROM [dbo].[SalesInvoice] si join SalesPerson sp ON si.salesID=sp.salesID WHERE si.salesID = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, saleID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int invid = rs.getInt("invoiceID");
                Date createdate = rs.getDate("invoiceDate");
                String saleid = rs.getString("salesID");
                String carid = rs.getString("carID");
                String custid = rs.getString("custID");

                invoices.add(new SalesInvoice(invid, createdate, saleid, carid, custid));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoices;
    }

    public String getSalesPersonNameByID(String saleID) {
        String saleName = null;
        String sql = "SELECT salesName FROM SalesPerson WHERE salesID = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, saleID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                saleName = rs.getString("salesName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return saleName;
    }

    public int getNextInvoiceID() {
        int nextID = 1;
        String sql = "SELECT MAX(invoiceID) FROM SalesInvoice";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                nextID = rs.getInt(1) + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nextID;
    }
}
