package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static Connection conn = null;

    private static final String DEFAULT_URL = "jdbc:mariadb://localhost:3306/lottery_pos";
    private static final String DEFAULT_USER = "mis";
    private static final String DEFAULT_PWD = "mis123";

    public static Connection getConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                return conn;
            }
            Properties cfg = loadConfig();
            conn = DriverManager.getConnection(
                    cfg.getProperty("url"),
                    cfg.getProperty("user"),
                    cfg.getProperty("password"));
            System.out.println("已連線資料庫: " + cfg.getProperty("url"));
        } catch (SQLException ex) {
            System.out.println("連線錯誤!");
            System.out.println(ex.toString());
        }
        return conn;
    }

    static Properties loadConfig() {
        Properties cfg = new Properties();
        cfg.setProperty("url", firstNonBlank(System.getenv("LOTTERY_POS_DB_URL"), DEFAULT_URL));
        cfg.setProperty("user", firstNonBlank(System.getenv("LOTTERY_POS_DB_USER"), DEFAULT_USER));
        cfg.setProperty("password", firstNonBlank(System.getenv("LOTTERY_POS_DB_PASSWORD"), DEFAULT_PWD));

        File file = findPropertiesFile();
        if (file == null) {
            return cfg;
        }
        Properties fileProps = new Properties();
        try (FileInputStream in = new FileInputStream(file)) {
            fileProps.load(in);
        } catch (IOException ex) {
            System.out.println("讀取 " + file.getPath() + " 失敗，改用預設值。");
            return cfg;
        }
        overlay(cfg, "url", fileProps, "db.url");
        overlay(cfg, "user", fileProps, "db.user");
        overlay(cfg, "password", fileProps, "db.password");
        return cfg;
    }

    private static File findPropertiesFile() {
        String[] candidates = {
            "db.properties",
            "config/db.properties"
        };
        for (String name : candidates) {
            File file = new File(name);
            if (file.isFile()) {
                return file;
            }
        }
        return null;
    }

    private static void overlay(Properties target, String targetKey, Properties source, String sourceKey) {
        String value = source.getProperty(sourceKey);
        if (value != null && !value.trim().isEmpty()) {
            target.setProperty(targetKey, value.trim());
        }
    }

    private static String firstNonBlank(String value, String fallback) {
        if (value == null || value.trim().isEmpty()) {
            return fallback;
        }
        return value.trim();
    }
}
