package com.ggingenieria.estacion.DAO;

import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component("mdbSurtidor")
public class MDBSurtidor extends DBSurtidor {

    @Override
    public Map<String, Double> getLecturaSurtidores(final int surtidorId) {
        Map<String, Double> surtidores = new HashMap<>();
        try(MSAcess msAcess = MSAcess.getInstance()) {
            ResultSet rs = msAcess.getDespacho(surtidorId);
            while(rs.next()){
                surtidores.put(rs.getString("SURTIDOR").trim(),rs.getDouble("LITROS"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return surtidores;
    }
}
