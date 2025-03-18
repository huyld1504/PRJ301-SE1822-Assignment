package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Part;
import utils.DBUtils;

public class PartDAO {

    //print partList
    public ArrayList<Part> getAllPart() {
        ArrayList<Part> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.getConnection();
            if (cn != null) {
                String query = "SELECT [partID]\n"
                + "      ,[partName]\n"
                + "      ,[purchasePrice]\n"
                + "      ,[retailPrice]\n"
                + "  FROM [Car_Dealership].[dbo].[Parts]"
                + "where [Status] = 'Active'";
                PreparedStatement p = cn.prepareStatement(query);
                ResultSet rs = p.executeQuery();
                while (rs.next()) {
                    String partID = rs.getString("partID");
                    String partName = rs.getString("partName");
                    Double purchasePrice = rs.getDouble("purchasePrice");
                    Double retailPrice = rs.getDouble("retailPrice");
                    list.add(new Part(partID, partName, purchasePrice, retailPrice));

                }
            } else {
                System.out.println("ERROR");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // create Part
    public boolean creatPart(Part part) {
        boolean isAdded = false;
        try {
            Connection cn = DBUtils.getConnection();
            String query = "INSERT INTO [dbo].[Parts]\n"
                    + "           ([partID]\n"
                    + "           ,[partName]\n"
                    + "           ,[purchasePrice]\n"
                    + "           ,[retailPrice])\n"
                    + "     VALUES " + "(?,?,?,?)";
            PreparedStatement ps = cn.prepareCall(query);
            if (cn != null) {
                ps.setString(1, part.getPartID());
                ps.setString(2, part.getPartName());
                ps.setDouble(3, part.getPurchasePrice());
                ps.setDouble(4, part.getRetailPrice());
                int rowEffected = ps.executeUpdate();
                isAdded = rowEffected > 0;
                ps.close();
                cn.close();
            } else {
                System.out.println("ERROR!! database connection failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAdded;
    }

    //update
    public boolean updatePart(String id, Part newPart) {
        boolean isUpdate = false;
        Connection cn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String query = "UPDATE [dbo].[Parts] "
                        + "SET [partName] = ?, [purchasePrice] = ?, [retailPrice] = ? "
                        + "WHERE [partID] = ?";
                ps = cn.prepareStatement(query);

                ps.setString(1, newPart.getPartName());
                ps.setDouble(2, newPart.getPurchasePrice());
                ps.setDouble(3, newPart.getRetailPrice());
                ps.setString(4, id);

                result = ps.executeUpdate();
            }
            if (result != 0 && cn != null) {
                isUpdate = true;
                cn.commit();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isUpdate;
    }

    //delete
    public void deletePartByID(String partID) {
    Connection cn = null;
    PreparedStatement ps = null;
    try {
        cn = DBUtils.getConnection();
        if (cn != null) {
            String query="UPDATE Parts\n"
                + "SET Status = 'Deactive'\n"
                + "WHERE partID = ?";
            ps=cn.prepareStatement(query);
            ps.setString(1, partID);
            ps.executeUpdate();
        }
    } catch (Exception e) {
        try {
            if (cn != null) cn.rollback(); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        e.printStackTrace();
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

    public Part getPartByID(String partID) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT [partID]\n"
                + "      ,[partName]\n"
                + "      ,[purchasePrice]\n"
                + "      ,[retailPrice]\n"
                + "  FROM [dbo].[Parts]\n"
                + "  WHERE [Status] = 'Active' and [partID] like ?";
        try {
            cn = DBUtils.getConnection();
            ps = cn.prepareStatement(query);
            ps.setString(1, partID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Part(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4));
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //search
    public ArrayList<Part> searchPartByPartName(String partName) throws SQLException {
        ArrayList<Part> parts = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.getConnection();

            if (cn != null) {
                String query;
                if (partName == null || partName.trim().isEmpty()) {
                    query = "SELECT [partID], [partName], [purchasePrice], [retailPrice] FROM [dbo].[Parts]";
                    ps = cn.prepareStatement(query);
                } else {
                    query = "SELECT [partID], [partName], [purchasePrice], [retailPrice] "
                            + "FROM [dbo].[Parts] WHERE [Status] = 'Active'and [partName] LIKE ?";
                    ps = cn.prepareStatement(query);
                    ps.setString(1, "%" + partName + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    Part part = new Part(
                            rs.getString("partID"),
                            rs.getString("partName"),
                            rs.getDouble("purchasePrice"),
                            rs.getDouble("retailPrice")
                    );
                    parts.add(part);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return parts;
    }

}
