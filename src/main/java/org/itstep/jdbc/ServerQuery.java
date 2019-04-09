package org.itstep.jdbc;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class ServerQuery {

    private static ServerQuery instance;
    private static Map<String, String> queries;

    /**
     * метод для подключения к серверу MySQL по свойствам читаемым из файла connection.properties
     */
    private ServerQuery() {

        PropertyReader propertyReader = new PropertyReader();
        Properties properties = propertyReader.getProperties("connection.properties");

        String server = properties.getProperty("server");

        if (server.equalsIgnoreCase("mysql")) {
            properties = propertyReader.getProperties("mysql_queries.properties");
        }

        Map<String, String> map = new HashMap<>(properties.size());
        for (Map.Entry<Object, Object> prop : properties.entrySet()) {
            map.put(prop.getKey().toString(), prop.getValue().toString());
        }
        queries = map;
    }

    /**
     * статический метод, возвращает единственный экземпляр класса, проверяя перед этим не создан ли он
     *
     * @return instance
     */
    public static ServerQuery getInstance() {
        if (instance == null) {
            instance = new ServerQuery();
        }
        return instance;
    }

    /**
     * метод получет запрос к серверу MySQL из файла mysql_queries.properties по ключу
     *
     * @param key String, название запроса
     * @return queries.get(key)
     */
    public String getQuery(String key) {
        if (key != null) {
            return queries.get(key);
        } else {
            return "";
        }
    }
}
