package com.example.javafxproject.data;

import com.example.javafxproject.data.models.Admin;
import com.example.javafxproject.data.models.Cake;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    private Connection connection;

    public static final String URL = "jdbc:mysql://localhost/manage_cake";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    public DBConnection(){
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connect successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Cake> getCake(){
        ArrayList<Cake> list = new ArrayList<>();
        String sql = "SELECT * FROM cake";
        try {
            ResultSet results = connection.prepareStatement(sql).executeQuery();
            while (results.next()){
                Cake cake = new Cake(
                        results.getInt("id"),
                        results.getString("name"),
                        results.getInt("quality"),
                        results.getFloat("price"),
                        results.getString("typecake"),
                        results.getString("image")
                                        );
                list.add(cake);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    public void insertProduct(Cake pro){
        String sql = "INSERT INTO cake (name, image, price, typecake, quality) VALUE ('"+ pro.name+"','"+ pro.image+"','"+ pro.price+"','"+ pro.typecake+"',"+ pro.quality+")";
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProduct(Cake pro){
        String sql = "UPDATE cake SET name = '"+ pro.name +"', image = '"+ pro.image+"', price = '"+ pro.price+"', typecake = '"+ pro.typecake+"', quality = '"+ pro.quality+"' WHERE id = "+ pro.id;
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct(int id){
        String sql = "DELETE FROM cake WHERE id = "+ id;
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //conect with database Admin
    public ArrayList<Admin> getAdmin() {
        ArrayList<Admin> admins = new ArrayList<>();
        try {
            var result = this.connection.prepareStatement("Select * from admin").executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("username");
                String password = result.getString("pass");
                System.out.println(id);
                System.out.println(name);
                System.out.println(password);
                admins.add(new Admin(id, name, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admins;
    }
}
