package com.ggingenieria.webservices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

/**
 * Created by fgiana on 01/09/2015.
 */
public class Systray {
    public static final String LOGO = "../../../logo16.png";
    public static final String TRAY_ICON_TITLE = "tray icon";
    public static final String TRAY_EXIT = "Salir";
    private final PopupMenu popup;
    private final TrayIcon trayIcon;
    private final SystemTray tray;

    public Systray() {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            throw new RuntimeException("SystemTray is not supported");
        }
        popup = new PopupMenu();
        trayIcon =
                new TrayIcon(createImage(LOGO, TRAY_ICON_TITLE));
        tray = SystemTray.getSystemTray();
        MenuItem exitItem = new MenuItem(TRAY_EXIT);

        exitItem.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog (null, "Seguro que quiere salir?","Salir del programa",JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        popup.add(exitItem);

        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }

    }

    protected static Image createImage(String path, String description) {
        URL imageURL = Application.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }

}
