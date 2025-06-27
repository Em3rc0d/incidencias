package com.incidencias.service;

import java.util.List;
import java.util.Map;

public interface DashboardService {
    List<Map<String, Object>> getTopChoferesConMasIncidencias();
}
