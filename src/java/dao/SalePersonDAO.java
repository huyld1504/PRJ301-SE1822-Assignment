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
import java.util.HashMap;
import java.util.Map;
import models.Customer;
import models.SalesPerson;
import utils.DBUtils;

/**
 *
 * @author Thanh Vinh
 */
public class SalePersonDAO {

    public SalesPerson checkLogin(String name) {
        SalesPerson sp = null;
        Connection conn = null;

        try {
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

    // lấy danh sách tất cả khách hàng 
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customerList = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Lấy tất cả khách hàng
                String sql = "SELECT custID, custName, phone, sex, cusAddress FROM dbo.Customer";
                PreparedStatement p = conn.prepareStatement(sql);

                ResultSet table = p.executeQuery();
                while (table.next()) {
                    String custID = table.getString("custID");
                    String custName = table.getString("custName");
                    String phone = table.getString("phone");
                    String sex = table.getString("sex");
                    String cusAddress = table.getString("cusAddress");

                    customerList.add(new Customer(custID, custName, phone, cusAddress, sex));
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
        return customerList;
    }

    //  Thêm khách hàng mới 
    public boolean addCustomer(Customer customer) {
        boolean isAdded = false;
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Lấy `custID` lớn nhất và tăng lên 1
                String getMaxIdSQL = "SELECT MAX(CAST(custID AS INT)) FROM dbo.Customer";
                PreparedStatement getMaxIdStmt = conn.prepareStatement(getMaxIdSQL);
                ResultSet rs = getMaxIdStmt.executeQuery();
                int newCustID = 11000; // Giá trị mặc định nếu bảng trống

                if (rs.next() && rs.getObject(1) != null) {
                    newCustID = rs.getInt(1) + 1;
                }

                // Chèn khách hàng mới với `custID` vừa tạo
                String sql = "INSERT INTO dbo.Customer (custID, custName, phone, sex, cusAddress) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setInt(1, newCustID);
                p.setNString(2, customer.getCustName());  // Dùng setNString để đảm bảo Tiếng Việt
                p.setString(3, customer.getPhone());
                p.setString(4, customer.getSex());
                p.setNString(5, customer.getCusAddress());  // Dùng setNString để đảm bảo Tiếng Việt

                int result = p.executeUpdate();
                isAdded = result > 0;
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
        return isAdded;
    }

    // cập nhật thông tin khách hàng
    public boolean updateCustomer(String custID, Customer updatedCustomer) {
        boolean isUpdated = false;
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // SQL để cập nhật thông tin khách hàng
                String sql = "UPDATE dbo.Customer SET custName=?, phone=?, sex=?, cusAddress=? WHERE custID=?";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, updatedCustomer.getCustName());
                p.setString(2, updatedCustomer.getPhone());
                p.setString(3, updatedCustomer.getSex());
                p.setString(4, updatedCustomer.getCusAddress());
                p.setString(5, custID);

                int result = p.executeUpdate();
                isUpdated = result > 0; // Kiểm tra có dòng nào bị ảnh hưởng không
            }
        } catch (SQLException | ClassNotFoundException e) {
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

        return isUpdated;
    }

    // SalePersonDAO.java
    public Customer getCustomerById(String custID) {
        Customer customer = null;
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Truy vấn thông tin khách hàng theo custID
                String sql = "SELECT custID, custName, phone, sex, cusAddress FROM dbo.Customer WHERE custID = ?";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, custID); // Gán giá trị custID vào câu truy vấn

                ResultSet rs = p.executeQuery();
                if (rs.next()) {
                    // Nếu tìm thấy khách hàng
                    String custName = rs.getString("custName");
                    String phone = rs.getString("phone");
                    String sex = rs.getString("sex");
                    String cusAddress = rs.getString("cusAddress");

                    // Khởi tạo đối tượng Customer với thông tin lấy từ cơ sở dữ liệu
                    customer = new Customer(custID, custName, phone, cusAddress, sex);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
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

        return customer;
    }

    
    public boolean deleteCustomer(String custID) {
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql1 = "DELETE FROM SalesInvoice WHERE custID = ?";
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ps1.setString(1, custID);
                ps1.executeUpdate();
                
                String sql2 = "DELETE FROM PartsUsed WHERE serviceTicketID IN \n"
                        + "    (SELECT serviceTicketID FROM ServiceTicket WHERE custID = ?)";
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, custID);
                ps2.executeUpdate();

                String sql3 = "DELETE FROM ServiceMechanic WHERE serviceTicketID IN \n"
                        + "    (SELECT serviceTicketID FROM ServiceTicket WHERE custID = ?)";
                PreparedStatement ps3 = conn.prepareStatement(sql3);
                ps3.setString(1, custID);
                ps3.executeUpdate();

                String sql4 = "DELETE FROM ServiceTicket WHERE custID = ?";
                PreparedStatement ps4 = conn.prepareStatement(sql4);
                ps4.setString(1, custID);
                ps4.executeUpdate();
                
                String sql5 = "DELETE FROM Customer WHERE custID = ?";
                PreparedStatement ps5 = conn.prepareStatement(sql5);
                ps5.setString(1, custID);

                int deleted = ps5.executeUpdate();

                return deleted > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public String getCustomerNameById(String custID) {
        String customerName = null;
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT custName FROM dbo.Customer WHERE custID=?";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, custID);

                ResultSet rs = p.executeQuery();
                if (rs.next()) {
                    customerName = rs.getString("custName");
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
        return customerName;
    }


    // tìm theo tên khách hàng
    public ArrayList<Customer> searchCustomerByName(String name) {
        ArrayList<Customer> customerList = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
       
                String sql = "SELECT custID, custName, phone, sex, cusAddress FROM dbo.Customer WHERE custName COLLATE Latin1_General_CI_AS LIKE ?";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, "%" + name + "%");  // Tìm kiếm theo tên với ký tự đại diện

                ResultSet table = p.executeQuery();
                while (table.next()) {
                    String custID = table.getString("custID");
                    String custName = table.getString("custName");
                    String phone = table.getString("phone");
                    String sex = table.getString("sex");
                    String cusAddress = table.getString("cusAddress");

                    customerList.add(new Customer(custID, custName, phone, cusAddress, sex));
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
        return customerList;
    }

    
    // xe bán ra theo năm
    public ArrayList<Map<String, Object>> getCarsSoldByYear() {
        ArrayList<Map<String, Object>> report = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT YEAR(invoiceDate) AS year, COUNT(*) AS carsSold FROM dbo.SalesInvoice GROUP BY YEAR(invoiceDate)";
                PreparedStatement p = conn.prepareStatement(sql);
                ResultSet rs = p.executeQuery();

                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("year", rs.getInt("year"));
                    row.put("carsSold", rs.getInt("carsSold"));
                    report.add(row);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
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

        return report;
    }

    // doanh thu bán xe theo năm
    public ArrayList<Map<String, Object>> getCarSalesRevenueByYear() {
        ArrayList<Map<String, Object>> report = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT YEAR(invoiceDate) AS year, SUM(price) AS revenue FROM dbo.SalesInvoice JOIN dbo.Cars ON SalesInvoice.carID = Cars.carID GROUP BY YEAR(invoiceDate)";
                PreparedStatement p = conn.prepareStatement(sql);
                ResultSet rs = p.executeQuery();

                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("year", rs.getInt("year"));
                    row.put("revenue", rs.getDouble("revenue"));
                    report.add(row);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
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

        return report;
    }

    // top 10 các mẫu xe bán chạy nhất
    public ArrayList<Map<String, Object>> getBestSellingCarModels() {
        ArrayList<Map<String, Object>> report = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT top 10 model, COUNT(*) AS salesCount FROM dbo.SalesInvoice JOIN dbo.Cars ON SalesInvoice.carID = Cars.carID GROUP BY model ORDER BY salesCount DESC";
                PreparedStatement p = conn.prepareStatement(sql);
                ResultSet rs = p.executeQuery();

                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("model", rs.getString("model"));
                    row.put("salesCount", rs.getInt("salesCount"));
                    report.add(row);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
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

        return report;
    }

    // top 10 các phụ tùng được sử dụng nhiều nhất
    public ArrayList<Map<String, Object>> getBestUsedParts() {
        ArrayList<Map<String, Object>> report = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT top 10 partName, SUM(numberUsed) AS totalUsed FROM dbo.PartsUsed JOIN dbo.Parts ON PartsUsed.partID = Parts.partID GROUP BY partName ORDER BY totalUsed DESC";
                PreparedStatement p = conn.prepareStatement(sql);
                ResultSet rs = p.executeQuery();

                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("partName", rs.getString("partName"));
                    row.put("totalUsed", rs.getInt("totalUsed"));
                    report.add(row);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
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

        return report;
    }

    // 3 thợ được giao sửa nhiều nhất
    public ArrayList<Map<String, Object>> getTop3Mechanics() {
        ArrayList<Map<String, Object>> report = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT top 3 mechanicName, COUNT(serviceTicketID) AS RepairsHandled\n"
                        + "FROM ServiceMechanic\n"
                        + "JOIN Mechanic ON ServiceMechanic.mechanicID = Mechanic.mechanicID\n"
                        + "GROUP BY mechanicName\n"
                        + "ORDER BY RepairsHandled DESC";
                PreparedStatement p = conn.prepareStatement(sql);
                ResultSet rs = p.executeQuery();

                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("mechanicName", rs.getString("mechanicName"));
                    row.put("repairsHandled", rs.getInt("repairsHandled"));
                    report.add(row);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
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

        return report;
    }

    // cập nhật thông tin của sale
    public boolean update(String salespersonID, SalesPerson newProfile) {
        boolean isUpdated = false;
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Cập nhật thông tin SalesPerson bao gồm cả ngày sinh
                String sql = "UPDATE dbo.SalesPerson SET salesName=?, sex=?, salesAddress=?, birthday=? WHERE salesID=?";
                PreparedStatement p = conn.prepareStatement(sql);

                p.setString(1, newProfile.getSalesName());  // salesName
                p.setString(2, newProfile.getSex());        // sex
                p.setString(3, newProfile.getSalesAddress()); // salesAddress

                // Chuyển đổi java.util.Date thành java.sql.Date
                java.sql.Date sqlDate = new java.sql.Date(newProfile.getBirthday().getTime());
                p.setDate(4, sqlDate);  // birthday

                p.setString(5, salespersonID);              // salesID (để xác định bản ghi cần cập nhật)

                int result = p.executeUpdate(); // Thực thi câu lệnh SQL
                isUpdated = result > 0;  // Nếu có ít nhất một dòng bị ảnh hưởng, nghĩa là cập nhật thành công
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();  // Đóng kết nối sau khi thực thi
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isUpdated;
    }
    
    
    
    
    
    
    

}
