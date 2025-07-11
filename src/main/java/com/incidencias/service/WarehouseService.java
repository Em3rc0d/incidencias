package com.incidencias.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final JdbcTemplate jdbc;
    private final NamedParameterJdbcTemplate namedJdbc;

    public void saveOrUpdate(String viewName, Map<String, Object> data) {
        if (!viewExiste(viewName)) {
            crearVistaConReglas(viewName, data);
        }

        if (!datosValidos(data)) return;

        // Intentar UPDATE (si id ya existe), si no, INSERT
        String id = data.get("id").toString();
        String checkExistSql = String.format("SELECT COUNT(*) FROM data_warehouse.%s WHERE id = ?", viewName);
        Long count = jdbc.queryForObject(checkExistSql, Long.class, id);

        if (count != null && count > 0) {
            update(viewName, data);
        } else {
            insert(viewName, data);
        }
    }

    public void delete(String viewName, Object id) {
        String sql = String.format("DELETE FROM data_warehouse.%s WHERE id = ?", viewName);
        jdbc.update(sql, id);
    }

    private void insert(String viewName, Map<String, Object> data) {
        StringJoiner columnas = new StringJoiner(", ");
        StringJoiner valores = new StringJoiner(", ");

        for (String key : data.keySet()) {
            columnas.add(key);
            valores.add(":" + key);
        }

        String sql = String.format("INSERT INTO data_warehouse.%s (%s) VALUES (%s)", viewName, columnas, valores);
        namedJdbc.update(sql, data);
    }

    private void update(String viewName, Map<String, Object> data) {
        StringJoiner sets = new StringJoiner(", ");
        for (String key : data.keySet()) {
            if (!key.equals("id")) {
                sets.add(key + " = :" + key);
            }
        }

        String sql = String.format("""
            UPDATE data_warehouse.%s
            SET %s
            WHERE id = :id
        """, viewName, sets);

        namedJdbc.update(sql, data);
    }

    private boolean viewExiste(String viewName) {
        String sql = """
            SELECT EXISTS (
                SELECT 1
                FROM information_schema.views
                WHERE table_schema = 'data_warehouse'
                  AND table_name = ?
            )
        """;
        return jdbc.queryForObject(sql, Boolean.class, viewName);
    }

    private void crearVistaConReglas(String viewName, Map<String, Object> data) {
        String tableName = "auto_" + viewName.replace("view_", "table_");

        StringJoiner columnas = new StringJoiner(", ");
        for (String key : data.keySet()) {
            columnas.add(key + " TEXT");
        }

        // Crear tabla
        String createTable = String.format("CREATE TABLE IF NOT EXISTS data_warehouse.%s (%s)", tableName, columnas);

        // Crear vista
        String createView = String.format("CREATE OR REPLACE VIEW data_warehouse.%s AS SELECT * FROM data_warehouse.%s", viewName, tableName);

        // Crear regla INSERT
        StringJoiner keys = new StringJoiner(", ");
        StringJoiner values = new StringJoiner(", ");
        for (String key : data.keySet()) {
            keys.add(key);
            values.add("NEW." + key);
        }

        String ruleInsert = String.format("""
            CREATE OR REPLACE RULE insert_into_%s AS
            ON INSERT TO data_warehouse.%s
            DO INSTEAD
            INSERT INTO data_warehouse.%s (%s) VALUES (%s)
        """, viewName, viewName, tableName, keys, values);

        // Crear regla UPDATE
        StringJoiner updates = new StringJoiner(", ");
        for (String key : data.keySet()) {
            if (!key.equals("id")) {
                updates.add(key + " = NEW." + key);
            }
        }

        String ruleUpdate = String.format("""
            CREATE OR REPLACE RULE update_%s AS
            ON UPDATE TO data_warehouse.%s
            DO INSTEAD
            UPDATE data_warehouse.%s SET %s WHERE id = OLD.id
        """, viewName, viewName, tableName, updates);

        // Crear regla DELETE
        String ruleDelete = String.format("""
            CREATE OR REPLACE RULE delete_%s AS
            ON DELETE TO data_warehouse.%s
            DO INSTEAD
            DELETE FROM data_warehouse.%s WHERE id = OLD.id
        """, viewName, viewName, tableName);

        jdbc.execute(createTable);
        jdbc.execute(createView);
        jdbc.execute(ruleInsert);
        jdbc.execute(ruleUpdate);
        jdbc.execute(ruleDelete);
    }

    private boolean datosValidos(Map<String, Object> data) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (entry.getValue() == null) {
                System.err.println("Campo nulo: " + entry.getKey());
                return false;
            }
        }
        return true;
    }
}
