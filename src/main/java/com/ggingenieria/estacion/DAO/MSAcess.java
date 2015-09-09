package com.ggingenieria.estacion.DAO;

import java.sql.*;

public class MSAcess implements AutoCloseable {
    public static final String DEFAULT_MDB_FILE = "D:\\MILP\\BASEDATA.mdb";
    private static MSAcess db = null;
    private Connection connection = null;
    private Statement st = null;

    private MSAcess(String filename) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection("jdbc:ucanaccess://" + filename, "", "");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static MSAcess getInstance(){
        /*if(db == null){
            db = new MSAcess(System.getProperty("mdb.file", DEFAULT_MDB_FILE));
        }*/
        db = new MSAcess(System.getProperty("mdb.file", DEFAULT_MDB_FILE));
        return db;
    }

    public void consultarSurtidores() throws SQLException {
        st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM DESPACHOS ORDER BY FECHA DESC, HORA DESC");
        st.close();
        while (rs.next()) {
            System.out.print(rs.getString("SURTIDOR"));
            System.out.print("\t");
            System.out.print(rs.getDate("FECHA"));
            System.out.print("\t");
            System.out.print(rs.getTime("HORA"));
            System.out.print("\t");
            System.out.print(rs.getString("PRODUCTO"));
            System.out.print("\t");
            System.out.print(rs.getString("LITROS"));
            System.out.print("\t");
            System.out.println(rs.getString("PESOS"));
        }
    }

    public static void main(String... args) throws Exception {
        try (MSAcess a = new MSAcess("/home/francisco/dbf/BASEDATA.mdb")) {
            a.consultarSurtidores();
        }
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

    public ResultSet getDespacho(final int surtidorId) {
        ResultSet rs = null;
        try(Statement st = connection.createStatement()) {
            rs = st.executeQuery("SELECT TOP 1 * FROM DESPACHOS WHERE SURTIDOR = " + surtidorId + " ORDER BY FECHA DESC, HORA DESC");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}

