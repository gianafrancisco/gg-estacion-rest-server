package com.ggingenieria.estacion.port;

import com.ggingenieria.estacion.DAO.DAO;
import com.ggingenieria.estacion.configuration.Configuracion;
import com.ggingenieria.estacion.modelos.Vehiculo;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by francisco on 15/06/15.
 */
@Component("lectorLlavero")
public class LectorLlavero extends SerialPort implements Runnable, SerialPortEventListener {


    @Autowired
    private SimpMessagingTemplate template;
    private Configuracion configuracion;

    public void setTemplate(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void setConfiguracion(Configuracion configuracion) {
        this.configuracion = configuracion;
    }

    @Autowired
    public LectorLlavero(Configuracion configuracion) {
        super(configuracion.getPuertoLlavero());
        this.configuracion = configuracion;
        try {
            openPort();//Open port
            setParams(9600, 8, 1, 0);//Set params
            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
            setEventsMask(mask);//Set mask
            addEventListener(this);//Add SerialPortEventListener
        } catch (SerialPortException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.isRXCHAR()) {//If data is available
            if (serialPortEvent.getEventValue() == 14) {//Check bytes count in the input buffer
                //Read data, if 10 bytes available
                try {
                    byte buffer[] = readBytes(14);
                    if (buffer[0] == 0x02) {
                        String key = new String(buffer).substring(1, 11);
                        System.out.println(key);
                        template.convertAndSend("/wsclave/getclave", key);
                        Map<String, Object> autorizado = DAO.getInstance().autorizacion(key);
                        if (autorizado != null) {
                            template.convertAndSend("/wsvehiculo/getvehiculo", autorizado);
                        }
                    }
                } catch (SerialPortException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
