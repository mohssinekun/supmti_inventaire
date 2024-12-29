package db;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.stream.Collectors;

public class DatabaseInitializer {

    public static void initializeDatabase(Connection connection) {
        try {
            // Execute schema.sql
            executeScript(connection, "schema.sql");
            // Execute data.sql
            executeScript(connection, "data.sql");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeScript(Connection connection, String scriptName) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            String script = loadResourceFile(scriptName);
            for (String query : script.split(";")) {
                if (!query.trim().isEmpty()) {
                    stmt.execute(query);
                }
            }
        }
    }

    private static String loadResourceFile(String fileName) throws Exception {
        try (InputStream is = DatabaseInitializer.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                throw new Exception("Cannot find resource file: " + fileName);
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                return reader.lines().collect(Collectors.joining("\n"));
            }
        }
    }
}
