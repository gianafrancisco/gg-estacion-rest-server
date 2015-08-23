package com.ggingenieria.estacion.port;

import jssc.SerialPortList;

public class MySerialPort {
    public static void main(String[] args) {
        String[] portNames = SerialPortList.getPortNames();
        for (String portName : portNames) {
            System.out.println(portName);
        }

        LectorLlavero ll = new LectorLlavero();

        Thread tr = new Thread(ll);

        tr.start();

    }
}
