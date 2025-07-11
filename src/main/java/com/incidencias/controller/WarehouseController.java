package com.incidencias.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/warehouse")
@RequiredArgsConstructor
public class WarehouseController {

    private final JdbcTemplate jdbc;

    @GetMapping("/{viewName}")
    public List<Map<String, Object>> getViewData(@PathVariable String viewName) {
        // Validación básica para evitar SQL Injection
        if (!viewName.matches("^[a-zA-Z0-9_]+$")) {
            throw new IllegalArgumentException("Nombre de vista inválido");
        }

        String sql = String.format("SELECT * FROM data_warehouse.%s", viewName);
        return jdbc.queryForList(sql);
    }
}
