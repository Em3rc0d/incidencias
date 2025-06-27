package com.incidencias.service;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

public class FileMirrorUtil {

    private static final String FILE_PATH = "db_mirror_log.txt";

    public static void logOperation(String tableName, String operation, Object object) {
        String serialized = serializeToJson(object);
        String logEntry = String.format(
                "[%s] [%s] [%s] %s%n",
                LocalDateTime.now(), operation.toUpperCase(), tableName, serialized
        );
        try (FileWriter fw = new FileWriter(FILE_PATH, true)) {
            fw.write(logEntry);
        } catch (IOException e) {
            System.err.println("Error writing to mirror file: " + e.getMessage());
        }
    }

    private static String serializeToJson(Object obj) {
        if (obj == null) return "null";
        StringBuilder json = new StringBuilder("{ ");
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(obj);
                json.append("\"").append(field.getName()).append("\": ");
                json.append("\"").append(value).append("\", ");
            } catch (IllegalAccessException e) {
                json.append("\"").append(field.getName()).append("\": \"ERROR\", ");
            }
        }
        if (json.toString().endsWith(", ")) {
            json.setLength(json.length() - 2);
        }
        json.append(" }");
        return json.toString();
    }
}
