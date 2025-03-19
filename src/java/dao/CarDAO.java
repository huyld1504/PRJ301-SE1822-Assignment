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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import models.Car;
import utils.DBUtils;

/**
 *
 * @author Thanh Vinh
 */
public class CarDAO {

    // xem tất cả xe
    public ArrayList<Car> getAllCars() {
        ArrayList<Car> carList = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                
                String sql = "SELECT carID, serialNumber, model, colour, year, price FROM dbo.Cars";
                PreparedStatement p = conn.prepareStatement(sql);

                ResultSet rs = p.executeQuery();
                while (rs.next()) {
                    String carID = rs.getString("carID");
                    String serialNumber = rs.getString("serialNumber");
                    String model = rs.getString("model");
                    String colour = rs.getString("colour");
                    int year = rs.getInt("year");
                    double price = rs.getDouble("price");  

                    // thêm đối tượng Car vào danh sách
                    carList.add(new Car(carID, serialNumber, model, colour, year, price));
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
        return carList;
    }



    // thêm car
    public boolean addCar(Car car) {
        boolean isAdded = false;
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // lấy carID lớn nhất hiện có trong bảng Cars
                String getMaxCarIDSQL = "SELECT MAX(CAST(carID AS INT)) FROM dbo.Cars";
                PreparedStatement getMaxCarIDStmt = conn.prepareStatement(getMaxCarIDSQL);
                ResultSet rs = getMaxCarIDStmt.executeQuery();

                int newCarID = 1122334400; // giá trị mặc định từ 1122334400 nếu bảng trống

                if (rs.next() && rs.getObject(1) != null) {
                    newCarID = rs.getInt(1) + 1; // tăng carID nếu đã có bản ghi
                }

                // chèn xe mới vào bảng
                String sql = "INSERT INTO dbo.Cars (carID, serialNumber, model, colour, year, price) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setInt(1, newCarID); // gán carID tự động tạo
                p.setString(2, car.getSerialNumber());
                p.setString(3, car.getModel());
                p.setString(4, car.getColour());
                p.setInt(5, car.getYear());
                p.setDouble(6, car.getPrice());
                
                int result = p.executeUpdate();  // chèn xe mới vào bảng
                isAdded = result > 0; // kiểm tra việc thêm xe vào cơ sở dữ liệu

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

        return isAdded;
    }

    // cập nhật car
    public boolean updateCar(Car car) {
        boolean isUpdated = false;
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                
                String sql = "UPDATE dbo.Cars SET serialNumber=?, model=?, colour=?, year=?, price=? WHERE carID=?";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, car.getSerialNumber());
                p.setString(2, car.getModel());
                p.setString(3, car.getColour());
                p.setInt(4, car.getYear());
                p.setDouble(5, car.getPrice());  
                p.setString(6, car.getCarID());  

                // thực thi câu lệnh cập nhật
                int result = p.executeUpdate();
                isUpdated = result > 0;  // nếu có ít nhất 1 dòng bị ảnh hưởng thì trả về true
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

    // lấy xe ra từ id 
    public Car getCarById(String carId) {
        Car car = null;
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                
                String sql = "SELECT carID, serialNumber, model, colour, year, price FROM dbo.Cars WHERE carID = ?";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, carId);  // gán carId vào câu lệnh SQL

                ResultSet rs = p.executeQuery();
                if (rs.next()) {
                    // lấy thông tin từ ResultSet và tạo đối tượng Car
                    car = new Car(
                            rs.getString("carID"),
                            rs.getString("serialNumber"),
                            rs.getString("model"),
                            rs.getString("colour"),
                            rs.getInt("year"),
                            rs.getDouble("price") 
                    );
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

    // xóa từng bảng để tránh lỗi
    public boolean deleteCar(String carID) {
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql1 = "DELETE FROM SalesInvoice WHERE carID =  ?";
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ps1.setString(1, carID);
                ps1.executeUpdate();

                String sql2 = "DELETE FROM PartsUsed WHERE serviceTicketID IN \n"
                        + "    (SELECT serviceTicketID FROM ServiceTicket WHERE carID = ?);";
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, carID);
                ps2.executeUpdate();

                String sql3 = "DELETE FROM ServiceMechanic WHERE serviceTicketID IN \n"
                        + "    (SELECT serviceTicketID FROM ServiceTicket WHERE carID = ?)";
                PreparedStatement ps3 = conn.prepareStatement(sql3);
                ps3.setString(1, carID);
                ps3.executeUpdate();

                String sql4 = "DELETE FROM ServiceTicket WHERE carID = ?";
                PreparedStatement ps4 = conn.prepareStatement(sql4);
                ps4.setString(1, carID);
                ps4.executeUpdate();

                String sql5 = "DELETE FROM Cars WHERE carID = ?";
                PreparedStatement ps5 = conn.prepareStatement(sql5);
                ps5.setString(1, carID);

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




    // lấy model của xe từ carID
    public String getCarModelById(String carID) {
        String model = null;
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT model FROM dbo.Cars WHERE carID = ?";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, carID);

                ResultSet rs = p.executeQuery();
                if (rs.next()) {
                    model = rs.getString("model");
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

        return model;
    }

    // tìm xe
    public List<Car> searchCar(String serialNumber, String model, Integer year) {
        List<Car> carList = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                
                String sql = "SELECT carID, serialNumber, model, colour, year, price FROM dbo.Cars WHERE 1=1";

                // xây dựng truy vấn dựa trên các tham số được cung cấp
                if (serialNumber != null && !serialNumber.isEmpty()) {
                    sql += " AND serialNumber LIKE ?";
                }
                if (model != null && !model.isEmpty()) {
                    sql += " AND model LIKE ?";
                }
                if (year != null) {
                    sql += " AND year = ?";
                }

                PreparedStatement p = conn.prepareStatement(sql);

                int paramIndex = 1;
                // paramIndex++ bắt đầu tìm từ dấu chấm hỏi đầu tiên rồi tăng dần lên
                if (serialNumber != null && !serialNumber.isEmpty()) {
                    p.setString(paramIndex++, "%" + serialNumber + "%");
                }
                if (model != null && !model.isEmpty()) {
                    p.setString(paramIndex++, "%" + model + "%");
                }
                if (year != null) {
                    p.setInt(paramIndex++, year);
                }

                ResultSet rs = p.executeQuery();
                while (rs.next()) {
                    // cập nhật constructor Car để thêm price
                    Car car = new Car(
                            rs.getString("carID"),
                            rs.getString("serialNumber"),
                            rs.getString("model"),
                            rs.getString("colour"),
                            rs.getInt("year"),
                            rs.getDouble("price") // thêm price vào đối tượng Car
                    );
                    carList.add(car);
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

        return carList;
    }





    
    
    
}
