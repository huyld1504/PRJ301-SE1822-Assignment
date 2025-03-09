/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import models.SalesPerson;
import utils.DBUtils;

/**
 *
 * @author Thanh Vinh
 */
public class SalePersonDAO {
    public SalesPerson checkLogin(String name){
        SalesPerson rs=null;
        Connection cn=null;
        try{
          cn= DBUtils.getConnection();

          if(cn!=null){
              String sql = "select salesID,salesName,birthday,sex,salesAddress\n"
                      + "from dbo.SalesPerson\n"
                      + "where salesName=?";
              PreparedStatement st=cn.prepareStatement(sql);
              st.setString(1, name);
              
              ResultSet table=st.executeQuery();
                if(table!=null){
                  while(table.next()){
                      String salesid=table.getString("salesID");
                      String salesname=table.getString("salesName");
                      Date bd = table.getDate("birthday");
                      String sex=table.getString("sex");
                      String salesadd = table.getString("salesAddress");
                      rs= new SalesPerson(salesid, salesname, salesadd, sex, bd);
                              
                  }
              }
          }
        
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(cn!=null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }
}
