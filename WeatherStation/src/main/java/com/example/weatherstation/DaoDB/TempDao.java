package com.example.weatherstation.DaoDB;

import com.example.weatherstation.Model.Temp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TempDao
{
    public List<Temp> get_all_data() {
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/weatherstation?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true",
                "root",
                "password");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select id,temperature,areaId,created from weather")) {

            List<Temp> temp_data = new ArrayList<>();

            while (rs.next()) {

                Temp w = new Temp();
                w.setId(rs.getInt("id"));
                w.setTemperature(rs.getDouble("temperature"));
                w.setCreated(rs.getString("created"));
                w.setAreaId(rs.getInt("areaId"));

                temp_data.add(w);

            }
            return temp_data;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void add_new_data(double temp) {

        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/weatherstation?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true",
                "root",
                "password")){

            PreparedStatement stmt = con.prepareStatement("INSERT INTO weather (temperature) VALUES (?)");
            stmt.setDouble(1, temp);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}