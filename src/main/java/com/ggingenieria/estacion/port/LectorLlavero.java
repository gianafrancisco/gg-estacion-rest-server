package com.ggingenieria.estacion.port;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.io.SerializablePermission;

/**
 * Created by francisco on 15/06/15.
 */
public class LectorLlavero extends SerialPort implements Runnable, SerialPortEventListener {

    public LectorLlavero(String portName) {
        super(portName);
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

    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.isRXCHAR()) {//If data is available
            if (serialPortEvent.getEventValue() == 10) {//Check bytes count in the input buffer
                //Read data, if 10 bytes available
                try {
                    byte buffer[] = readBytes(10);
                } catch (SerialPortException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
