package com.ggingenieria.estacion.DAO;

import java.util.Map;

public abstract class DBSurtidor {
    public abstract Map<String, Double> getLecturaSurtidores(final int surtidorId);
}
