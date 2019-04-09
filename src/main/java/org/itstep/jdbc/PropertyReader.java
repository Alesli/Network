package org.itstep.jdbc;

import java.io.IOException;
import java.util.Properties;

class PropertyReader {

    Properties getProperties(String fileName) {

        Properties properties = new Properties();

        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("property/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
