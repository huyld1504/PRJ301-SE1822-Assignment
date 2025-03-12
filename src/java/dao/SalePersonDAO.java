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
import models.Customer;
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
    
    // 📌 1. Lấy danh sách tất cả khách hàng (Read)
    public ArrayList<Customer> getCustomersBySalesID(String salesID) {
        ArrayList<Customer> customerList = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Lọc danh sách khách hàng theo salesID từ bảng SalesInvoice
                String sql = "SELECT DISTINCT C.custID, C.custName, C.phone, C.sex, C.cusAddress "
                        + "FROM Customer C "
                        + "JOIN SalesInvoice SI ON C.custID = SI.custID "
                        + "WHERE SI.salesID = ?";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, salesID);

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


    // 📌 2. Thêm khách hàng mới (Create)
    public boolean addCustomer(Customer customer) {
        boolean isAdded = false;
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO dbo.Customer (custID, custName, phone, sex, cusAddress) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, customer.getCustID());
                p.setString(2, customer.getCustName());
                p.setString(3, customer.getPhone());
                p.setString(4, customer.getSex());
                p.setString(5, customer.getCusAddress());

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

    // 📌 3. Cập nhật thông tin khách hàng (Update)
    public boolean updateCustomer(Customer customer) {
        boolean isUpdated = false;
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE dbo.Customer SET custName=?, phone=?, sex=?, cusAddress=? WHERE custID=?";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, customer.getCustName());
                p.setString(2, customer.getPhone());
                p.setString(3, customer.getSex());
                p.setString(4, customer.getCusAddress());
                p.setString(5, customer.getCustID());

                int result = p.executeUpdate();
                isUpdated = result > 0;
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
        return isUpdated;
    }

    // 📌 4. Xóa khách hàng (Delete)
    public boolean deleteCustomer(String custID) {
        boolean isDeleted = false;
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM dbo.Customer WHERE custID=?";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, custID);

                int result = p.executeUpdate();
                isDeleted = result > 0;
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
        return isDeleted;
    }

    // 📌 5. Tìm kiếm khách hàng theo tên (Search)
    public ArrayList<Customer> searchCustomerByName(String name) {
        ArrayList<Customer> customerList = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT custID, custName, phone, sex, cusAddress FROM dbo.Customer WHERE custName LIKE ?";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, "%" + name + "%");
                ResultSet rs = p.executeQuery();

                while (rs.next()) {
                    String custID = rs.getString("custID");
                    String custName = rs.getString("custName");
                    String phone = rs.getString("phone");
                    String sex = rs.getString("sex");
                    String address = rs.getString("cusAddress");

                    customerList.add(new Customer(custID, custName, phone, address, sex));
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


    
    
    
    
    
    
}
