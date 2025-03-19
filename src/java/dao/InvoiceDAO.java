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
import java.sql.Date;
import java.time.LocalDate;
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

        return rs;
    }

    public boolean createInvoice(int invoiceID, Date invoiceDate, String saleID, String carID, String custID) {
        String sql = "INSERT INTO SalesInvoice (invoiceID, invoiceDate, salesID, carID, custID) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, invoiceID);
            ps.setDate(2, invoiceDate);
            ps.setString(3, saleID);
            ps.setString(4, carID);
            ps.setString(5, custID);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            System.err.println("Lỗi khi tạo hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        InvoiceDAO dao = new InvoiceDAO();
        // Giả lập dữ liệu test
        int invoiceID = dao.getNextInvoiceID(); // Lấy ID tiếp theo
        LocalDate localDate = LocalDate.now();  // Lấy ngày hôm nay
        Date invoiceDate = Date.valueOf(localDate); // Chuyển sang java.sql.Date
        String saleID = "30121050015";  // Giả sử có một nhân viên sales ID "S123"
        String carID = "1122334456";   // Giả sử có một xe ID "C456"
        String custID = "11051"; // Giả sử có một khách hàng ID "CU789"

        // Gọi phương thức để test
        boolean success = dao.createInvoice(invoiceID, invoiceDate, saleID, carID, custID);

        // Kiểm tra kết quả
        if (success) {
            System.out.println("Tạo hóa đơn thành công! InvoiceID: " + invoiceID);
        } else {
            System.out.println("Tạo hóa đơn thất bại!");
        }
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
        return invoices; // trả về danh sách hóa đơn
    }
}
