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


        return DAO.getInstance().autorizacion(clave);
        //return v;
    }

    @MessageMapping("/wsclave")
    @SendTo("/wsclave/getclave")
    public String getClave(String clave) throws Exception {
        return clave;
    }

}
