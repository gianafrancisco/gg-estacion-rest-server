package com.ggingenieria.estacion.websocket;

import com.ggingenieria.estacion.DAO.DAO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class WsTargetaIdController {

    @MessageMapping("/wsvehiculo")
    @SendTo("/wsvehiculo/getvehiculo")
    public Map<String, Object> getVehiculo(String clave) {
        return DAO.getInstance().autorizacion(clave);
    }

    @MessageMapping("/wsclave")
    @SendTo("/wsclave/getclave")
    public String getClave(String clave) {
        return clave;
    }

}
