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
import java.util.List;
import models.SalesInvoice;
import utils.DBUtils;

public class InvoiceDAO {
    public List<SalesInvoice> getSalesInvoicesByCustomerId(String customerId) {
        List<SalesInvoice> invoices = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DBUtils.getConnection(); // lấy kết nối từ DBUtils
            if (conn != null) {
                String query = "SELECT si.invoiceID, si.invoiceDate, si.salesID, sp.salesName, si.custID "
                        + "FROM SalesInvoice si "
                        + "JOIN SalesPerson sp ON si.salesID = sp.salesID "
                        + "WHERE si.custID = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, customerId); 

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    SalesInvoice invoice = new SalesInvoice();
                    invoice.setInvoiceID(rs.getInt("invoiceID"));
                    invoice.setInvoiceDate(rs.getDate("invoiceDate"));
                    invoice.setSalesID(rs.getString("salesID")); 
                    invoice.setCustID(rs.getString("custID")); 
                    invoices.add(invoice); // thêm hóa đơn vào danh sách
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close(); // đóng kết nối sau khi sử dụng
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return invoices; // trả về danh sách hóa đơn
    }
}
