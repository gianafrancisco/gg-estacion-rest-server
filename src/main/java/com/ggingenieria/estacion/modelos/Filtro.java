package com.ggingenieria.estacion.modelos;

import java.util.Calendar;

/**
 * Created by francisco on 29/07/15.
 */
public class Filtro {
    private Calendar desde;
    private Calendar hasta;
    private String filtro;

    public Calendar getDesde() {
        return desde;
    }

    public void setDesde(Calendar desde) {
        this.desde = desde;
    }

    public Calendar getHasta() {
        return hasta;
    }

    public void setHasta(Calendar hasta) {
        this.hasta = hasta;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }
}
