/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Car;
import models.Customer;
import models.SalesInvoice;
import models.SalesInvoiceDetail;
import models.SalesPerson;
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
                while (table.next()) {
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
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    public boolean update(String id, Customer newProfile) {
        boolean isUpdated = false;
        int result = 0;
        
        if(!id.equals(newProfile.getCustID())) {
            return false;
        }
        
        Connection conn = null;
        PreparedStatement p;
        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                conn.setAutoCommit(false);
                String sql = "UPDATE [dbo].[Customer] \n"
                        + "   SET [custName] = ? \n"
                        + "      ,[phone] = ? \n"
                        + "      ,[sex] = ? \n"
                        + "      ,[cusAddress] = ? \n"
                        + " WHERE [custID] = ?";
                p = conn.prepareStatement(sql);
                p.setString(1, newProfile.getCustName());
                p.setString(2, newProfile.getPhone());
                p.setString(3, newProfile.getSex());
                p.setString(4, newProfile.getCusAddress());
                p.setString(5, id);
                result = p.executeUpdate();
            }
            if(result != 0 && conn != null) {
                isUpdated = true;
                conn.commit();
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
        return isUpdated;
    }
    
    public Customer getCustomerById(String custID) {
        Customer customer = null;
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT custID, custName, phone, sex, cusAddress FROM dbo.Customer WHERE custID = ?";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, custID);

                ResultSet rs = p.executeQuery();
                if (rs.next()) {
                    String custName = rs.getString("custName");
                    String phone = rs.getString("phone");
                    String sex = rs.getString("sex");
                    String cusAddress = rs.getString("cusAddress");

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

    // Lấy tất cả hóa đơn của khách hàng
    public List<SalesInvoice> getSalesInvoicesByCustomerId(String custID) {
        List<SalesInvoice> invoices = new ArrayList<>();
        Connection conn = null;

        try {
            // Kết nối đến cơ sở dữ liệu
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "SELECT invoiceID, invoiceDate, salesID, carID, custID "
                        + "FROM SalesInvoice WHERE custID = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, custID);

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    SalesInvoice invoice = new SalesInvoice();
                    invoice.setInvoiceID(rs.getInt("invoiceID"));
                    invoice.setInvoiceDate(rs.getDate("invoiceDate"));
                    invoice.setSalesID(rs.getString("salesID"));
                    invoice.setCarID(rs.getString("carID"));
                    invoice.setCustID(rs.getString("custID"));
                    invoices.add(invoice);
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

        return invoices;
    }

    
    
    
    
    // Phương thức để lấy thông tin hóa đơn theo invoiceID
    public SalesInvoice getSalesInvoiceById(int invoiceID) {
        SalesInvoice invoice = null;
        Connection conn = null;
        try {
            // Kết nối đến cơ sở dữ liệu
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Truy vấn dữ liệu từ bảng SalesInvoice theo invoiceID
                String query = "SELECT invoiceID, invoiceDate, salesID, carID, custID FROM SalesInvoice WHERE invoiceID = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, invoiceID);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    invoice = new SalesInvoice();
                    invoice.setInvoiceID(rs.getInt("invoiceID"));
                    invoice.setInvoiceDate(rs.getDate("invoiceDate"));
                    invoice.setSalesID(rs.getString("salesID"));
                    invoice.setCarID(rs.getString("carID"));
                    invoice.setCustID(rs.getString("custID"));
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
        return invoice;
    }

    // lấy thông tin detail của invoice
    public SalesInvoiceDetail getSalesInvoiceDetailById(int invoiceID) {
        SalesInvoiceDetail detail = null;
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Query lấy thông tin hóa đơn
                String query = "SELECT * FROM SalesInvoice WHERE invoiceID = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, invoiceID);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    // Lấy thông tin từ hóa đơn
                    int invoiceIDFromDB = rs.getInt("invoiceID");
                    Date invoiceDate = rs.getDate("invoiceDate");
                    String salesID = rs.getString("salesID");
                    String carID = rs.getString("carID");
                    String custID = rs.getString("custID");

                    // Lấy thông tin khách hàng, xe, nhân viên bán hàng
                    Customer customer = getCustomerInvoiceById(custID);
                    Car car = getCarById(carID);
                    SalesPerson salesPerson = getSalesPersonById(salesID);

                    // Tạo đối tượng SalesInvoiceDetail
                    detail = new SalesInvoiceDetail(invoiceIDFromDB, invoiceDate, salesID, carID, custID, customer, car, salesPerson);
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
        return detail;
    }

    // Lấy thông tin khách hàng theo custID
    public Customer getCustomerInvoiceById(String custID) {
        Customer customer = null;
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "SELECT * FROM Customer WHERE custID = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, custID);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    customer = new Customer();
                    customer.setCustID(rs.getString("custID"));
                    customer.setCustName(rs.getString("custName"));
                    customer.setPhone(rs.getString("phone"));
                    customer.setCusAddress(rs.getString("cusAddress"));
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

    // Lấy thông tin xe theo carID
    public Car getCarById(String carID) {
        Car car = null;
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "SELECT * FROM Cars WHERE carID = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, carID);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    car = new Car();
                    car.setCarID(rs.getString("carID"));
                    car.setSerialNumber(rs.getString("serialNumber"));
                    car.setModel(rs.getString("model"));
                    car.setColour(rs.getString("colour"));
                    car.setYear(rs.getInt("year"));
                    car.setPrice(rs.getDouble("price"));
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
        return car;
    }

    // Lấy thông tin nhân viên bán hàng theo salesID
    public SalesPerson getSalesPersonById(String salesID) {
        SalesPerson salesPerson = null;
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "SELECT * FROM SalesPerson WHERE salesID = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, salesID);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    salesPerson = new SalesPerson();
                    salesPerson.setSalesID(rs.getString("salesID"));
                    salesPerson.setSalesName(rs.getString("salesName"));
                    salesPerson.setBirthday(rs.getDate("birthday"));
                    salesPerson.setSalesAddress(rs.getString("salesAddress"));
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
        return salesPerson;
    }


    
    
    
    
}
