package com.ggingenieria.estacion.websocket;

import com.ggingenieria.estacion.DAO.DAO;
import com.ggingenieria.estacion.modelos.Empresa;
import com.ggingenieria.estacion.modelos.Registro;
import com.ggingenieria.estacion.modelos.Vehiculo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class WsTargetaIdController {

    @MessageMapping("/wsvehiculo")
    @SendTo("/wsvehiculo/getvehiculo")
    public Map<String,Object> vehiculo(String clave) throws Exception {
        Vehiculo v = DAO.getInstance().getVehiculoPorClave(clave);
        //Vehiculo v=DAO.getInstance().getVehiculo(14);
        Empresa e = DAO.getInstance().getEmpresa(v.getEmpresaId());
        Registro r = DAO.getInstance().getUltimaVenta(v.getVehiculoId());

        Calendar calendar = GregorianCalendar.getInstance();
        Date fechaActual = calendar.getTime();
        Date proximaVenta = r.getFechaProximaVenta();

        long diferencia = fechaActual.getTime() - proximaVenta.getTime();
        v.setAutorizado(diferencia >= 0);
        DAO.getInstance().update(v);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("activado",diferencia >= 0);
        map.put("vehiculo",v);

        return map;
        //return v;
    }

    @MessageMapping("/wsclave")
    @SendTo("/wsclave/getclave")
    public String getClave(String clave) throws Exception {
        return clave;
    }

}
