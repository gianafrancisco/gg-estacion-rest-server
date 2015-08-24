package com.ggingenieria.estacion.DAO;

import java.sql.*;

public class MSAcess implements  AutoCloseable {
    private Connection connection = null;
    private Statement st = null;

    public MSAcess(String filename) throws ClassNotFoundException, SQLException {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        connection = DriverManager.getConnection("jdbc:ucanaccess://"+filename, "", "");
    }

    public void consultarSurtidores() throws SQLException {
        st =connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM DESPACHOS");
        while(rs.next()){
            System.out.print(rs.getString("SURTIDOR"));
            System.out.print("\t");
            System.out.print(rs.getString("FECHA"));
            System.out.print("\t");
            System.out.print(rs.getString("HORA"));
            System.out.print("\t");
            System.out.print(rs.getString("PRODUCTO"));
            System.out.print("\t");
            System.out.print(rs.getString("LITROS"));
            System.out.print("\t");
            System.out.println(rs.getString("PESOS"));
        }
    }


    public static void main(String...args) throws Exception {
        try(MSAcess a = new MSAcess("/home/francisco/dbf/BASEDATA.mdb")) {
            a.consultarSurtidores();
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}

