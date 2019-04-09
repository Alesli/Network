package org.itstep.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ServerConnector {

    private static ServerConnector instance;
    private static Connection connection;

    /**
     * метод для соединения с БД по параметрам, читаемые из файла connection.properties
     */
    private ServerConnector() {

        PropertyReader propertyReader = new PropertyReader();
        Properties properties = propertyReader.getProperties("connection.properties");

        Connection conn = null;

        try {
            Class.forName(properties.getProperty("driver"));

            try {
                conn = DriverManager.getConnection(
                        properties.getProperty("url"),
                        properties.getProperty("username"),
                        properties.getProperty("password")
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        connection = conn;
    }

    /**
     * статический метод, возвращает единственный экземпляр класса, проверяя перед этим не создан ли он
     * (создание объекта при первом вызове метода)
     *
     * @return instance
     */
    public static ServerConnector getInstance() {
        if (instance == null) {
            instance = new ServerConnector();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
