package org.crypto.training.repository;

import org.crypto.training.model.User;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5431/training_db";
    static final String USER = "admin";
    static final String PASS = "Training123!";

    public List<User> getUsers() {
        //Step1: Prepare the required data model
        List<User> users = new ArrayList<User>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //Step2: Open a connection ->5 key points to connect db
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Step3: Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM users";
            rs = stmt.executeQuery(sql);

            //Step4: Extract data from result set
            while(rs.next()) {
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Date registration_date = rs.getDate("registration_date");

                User user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.setRegistration_date(registration_date);
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //Step6: finally block used to close resources
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
}