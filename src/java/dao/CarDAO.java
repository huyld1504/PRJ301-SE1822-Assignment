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
import java.util.List;
import models.Car;
import utils.DBUtils;

/**
 *
 * @author Thanh Vinh
 */
public class CarDAO {

    // Xem tất cả xe
    public ArrayList<Car> getAllCars() {
        ArrayList<Car> carList = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Truy vấn để lấy tất cả các xe, bao gồm cả cột 'price'
                String sql = "SELECT carID, serialNumber, model, colour, year, price FROM dbo.Cars";
                PreparedStatement p = conn.prepareStatement(sql);

                ResultSet rs = p.executeQuery();
                while (rs.next()) {
                    String carID = rs.getString("carID");
                    String serialNumber = rs.getString("serialNumber");
                    String model = rs.getString("model");
                    String colour = rs.getString("colour");
                    int year = rs.getInt("year");
                    double price = rs.getDouble("price");  // Lấy giá trị price (kiểu double)

                    // Thêm đối tượng Car vào danh sách với đầy đủ thông tin
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
                // Lấy carID lớn nhất hiện có trong bảng Cars
                String getMaxCarIDSQL = "SELECT MAX(CAST(carID AS INT)) FROM dbo.Cars";
                PreparedStatement getMaxCarIDStmt = conn.prepareStatement(getMaxCarIDSQL);
                ResultSet rs = getMaxCarIDStmt.executeQuery();

                int newCarID = 1122334400; // Giá trị mặc định từ 1122334400 nếu bảng trống

                if (rs.next() && rs.getObject(1) != null) {
                    newCarID = rs.getInt(1) + 1; // Tăng carID nếu đã có bản ghi
                }

                // Chèn xe mới vào bảng
                String sql = "INSERT INTO dbo.Cars (carID, serialNumber, model, colour, year) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setInt(1, newCarID); // Gán carID tự động tạo
                p.setString(2, car.getSerialNumber());
                p.setString(3, car.getModel());
                p.setString(4, car.getColour());
                p.setInt(5, car.getYear());

                int result = p.executeUpdate();  // Chèn xe mới vào bảng
                isAdded = result > 0; // Kiểm tra việc thêm xe vào cơ sở dữ liệu

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
                // Cập nhật câu lệnh SQL để bao gồm cột 'price'
                String sql = "UPDATE dbo.Cars SET serialNumber=?, model=?, colour=?, year=?, price=? WHERE carID=?";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, car.getSerialNumber());
                p.setString(2, car.getModel());
                p.setString(3, car.getColour());
                p.setInt(4, car.getYear());
                p.setDouble(5, car.getPrice());  // Cập nhật giá trị price
                p.setString(6, car.getCarID());  // Cập nhật carID để xác định xe cần cập nhật

                // Thực thi câu lệnh cập nhật
                int result = p.executeUpdate();
                isUpdated = result > 0;  // Nếu có ít nhất 1 dòng bị ảnh hưởng thì trả về true
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

    // Lấy xe ra từ id (có thêm price)
    public Car getCarById(String carId) {
        Car car = null;
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // SQL để lấy thông tin xe theo carID (bao gồm cột price)
                String sql = "SELECT carID, serialNumber, model, colour, year, price FROM dbo.Cars WHERE carID = ?";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, carId);  // Gán carId vào câu lệnh SQL

                ResultSet rs = p.executeQuery();
                if (rs.next()) {
                    // Lấy thông tin từ ResultSet và tạo đối tượng Car
                    car = new Car(
                            rs.getString("carID"),
                            rs.getString("serialNumber"),
                            rs.getString("model"),
                            rs.getString("colour"),
                            rs.getInt("year"),
                            rs.getDouble("price") // Lấy giá trị price từ ResultSet
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

    // xóa car
    public boolean deleteCar(String carID) {
        boolean isDeleted = false;
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // SQL để xóa xe theo carID
                String sql = "DELETE FROM dbo.Cars WHERE carID=?";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, carID);

                int result = p.executeUpdate();
                isDeleted = result > 0;
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

        return isDeleted;
    }

    // Phương thức trong CarDAO để lấy model của xe từ carID
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
                // Cập nhật câu lệnh SQL để lấy thêm cột price
                String sql = "SELECT carID, serialNumber, model, colour, year, price FROM dbo.Cars WHERE 1=1";

                // Build query based on provided parameters
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
                    // Cập nhật constructor Car để thêm price
                    Car car = new Car(
                            rs.getString("carID"),
                            rs.getString("serialNumber"),
                            rs.getString("model"),
                            rs.getString("colour"),
                            rs.getInt("year"),
                            rs.getDouble("price") // Thêm price vào đối tượng Car
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
