package com.ggingenieria.estacion.DAO;

import com.ggingenieria.estacion.modelos.*;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DAO {

    public static final String VENTA = "VENTA";
    public static final String CAMBIO_DE_PUNTOS = "CAMBIO_DE_PUNTOS";
    private static DAO instance = null;
    private static SessionFactory sessionFactory;
    private static StandardServiceRegistry serviceRegistry;

    private DAO() {

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

    }

    public static DAO getInstance() {
        if (instance == null) {
            instance = new DAO();
        }
        return instance;
    }

    //USUARIOS
    public List getUsuarios() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List usuarios = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Usuario WHERE activo = 1");
            usuarios = q.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return usuarios;
    }

    public Usuario getUsuario(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Usuario usuarios = null;
        try {
            tx = session.beginTransaction();
            usuarios = (Usuario) session.get(Usuario.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return usuarios;
    }

    public Usuario getUsuarioPorNombre(String username) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Usuario usuario = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Usuario WHERE nombreUsuario = :username AND activo = 1").setParameter("username", username);
            usuario = (Usuario) q.list().get(0);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return usuario;
    }

    //VEHICULOS
    public List getVehiculos() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List vehiculo = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Vehiculo WHERE activo = 1");
            vehiculo = q.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return vehiculo;
    }

    public Vehiculo getVehiculo(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Vehiculo vehiculo = null;
        try {
            tx = session.beginTransaction();
            vehiculo = (Vehiculo) session.get(Vehiculo.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return vehiculo;
    }

    public Vehiculo getVehiculoPorClave(String clave) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Vehiculo vehiculo = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Vehiculo WHERE tarjetaId = :tarjetaId AND activo = 1").setParameter("tarjetaId", clave);
            if (q.list().size() > 0) {
                vehiculo = (Vehiculo) q.list().get(0);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return vehiculo;
    }


    //EMPRESA
    public List getEmpresas() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List empresa = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Empresa WHERE activo = 1");
            empresa = q.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return empresa;
    }

    public Empresa getEmpresa(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Empresa empresa = null;
        try {
            tx = session.beginTransaction();
            empresa = (Empresa) session.get(Empresa.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return empresa;
    }


    //PRODUCTOS

    public List<Producto> getProductos() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List producto = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Producto WHERE activo = 1");
            producto = q.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return producto;
    }

    public Producto getProducto(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Producto producto = null;
        try {
            tx = session.beginTransaction();
            producto = (Producto) session.get(Producto.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return producto;
    }


    //REGISTROS
    public List<Registro> getRegistroVentas(String fecha, int empresaId) {
        return getRegistros(fecha, empresaId, VENTA);
    }

    public List<Registro> getRegistroCambios(String fecha, int empresaId) {
        return getRegistros(fecha, empresaId, CAMBIO_DE_PUNTOS);
    }

    public List getRegistros(final String fecha, int empresaId, String tipo) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List registro = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM Registro WHERE fechaVencimiento > :fecha AND empresaId=:empresaId AND accion = :accion");
            try {
                q.setTimestamp("fecha", format.parse(fecha));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            q.setParameter("empresaId", empresaId);
            q.setParameter("accion", tipo);
            registro = q.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return registro;
    }

    public Registro getUltimaVenta(final int vehiculoId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List registro;
        Registro r = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM Registro WHERE vehiculoId = :vehiculoId AND accion = 'VENTA' ORDER BY fechaRegistro DESC");
            q.setParameter("vehiculoId", vehiculoId);
            registro = q.list();
            System.out.println(registro);
            if (registro.size() > 0) {
                r = (Registro) registro.get(0);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return r;
    }

    public Registro getRegistro(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Registro registro = null;
        try {
            tx = session.beginTransaction();
            registro = (Registro) session.get(Registro.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return registro;
    }


    public List getSurtidores() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List surtidor = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Surtidor WHERE activo = 1 ORDER BY descripcion ASC");
            surtidor = q.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return surtidor;
    }

    public Surtidor getSurtidor(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Surtidor surtidor = null;
        try {
            tx = session.beginTransaction();
            surtidor = (Surtidor) session.get(Surtidor.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return surtidor;
    }

    //OPEN SESSION
    public Session getSession() {
        return sessionFactory.openSession();
    }

    public void delete(Activable u) {
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        u.setActivo(false);
        //s.delete(u);
        s.update(u);
        s.getTransaction().commit();
        s.close();
    }

    public void update(Object u) {
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        s.update(u);
        s.getTransaction().commit();
        s.close();
    }

    //INSERTS
    public void add(Activable u) {
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        u.setActivo(true);
        s.save(u);
        s.getTransaction().commit();
        s.close();
    }


    public Map<String, Object> autorizacion(String clave) {

        Vehiculo v = DAO.getInstance().getVehiculoPorClave(clave);
        if (v == null) {
            return null;
        }
        Registro r = DAO.getInstance().getUltimaVenta(v.getVehiculoId());
        Map<String, Object> map = new HashMap<>();
        System.out.println(r);
        if (r != null) {

            Calendar calendar = GregorianCalendar.getInstance();
            Date fechaActual = calendar.getTime();
            Date proximaVenta = r.getFechaProximaVenta();

            long diferencia = fechaActual.getTime() - proximaVenta.getTime();
            v.setAutorizado(diferencia >= 0);
            DAO.getInstance().update(v);
            map.put("activado", diferencia >= 0);
        } else {
            v.setAutorizado(true);
            DAO.getInstance().update(v);
            map.put("activado", true);
        }
        map.put("vehiculo", v);

        return map;

    }

    public List getRegistros(Filtro filtro, int ipp, int p) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List registro = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM Registro WHERE fechaRegistro BETWEEN :fechaDesde AND :fechaHasta ORDER BY registroId DESC");
            q.setTimestamp("fechaDesde", filtro.getDesde().getTime());
            q.setTimestamp("fechaHasta", filtro.getHasta().getTime());
            q.setFirstResult((p - 1) * ipp);
            q.setMaxResults(ipp);
            registro = q.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return registro;
    }

    public long getSize(final String tabla, final Filtro filtro) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        long rows = 0;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM " + tabla + " WHERE fechaRegistro BETWEEN :fechaDesde AND :fechaHasta");
            q.setTimestamp("fechaDesde", filtro.getDesde().getTime());
            q.setTimestamp("fechaHasta", filtro.getHasta().getTime());
            //q.setParameter("tabla", tabla);
            rows = (long) q.list().size();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return rows;
    }
}
