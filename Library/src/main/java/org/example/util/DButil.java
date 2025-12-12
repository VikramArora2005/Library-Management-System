package org.example.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DButil {

    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/library");
        config.setUsername("root");
        config.setPassword("root");

        // Optional optimizations
        config.setMinimumIdle(5);
        config.setMaximumPoolSize(10);
        config.setIdleTimeout(30000);
        config.setConnectionTimeout(30000);
        config.setMaxLifetime(1800000);
        config.setLeakDetectionThreshold(5000);

        dataSource = new HikariDataSource(config);
//        System.out.println("Connected using HikariCP connection pool");
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection(); // pooled connection
    }
}

    // Closing returns the connection to the pool, not the DB
//    public static void closeConnection(Connection con) {
//        if (con != null) {
//            try {
//                con.close(); // returns to the pool
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }



