/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro_ud08_oracle_usuario;

import java.sql.*;


import java.sql.Connection;
import java.sql.DriverManager;

import java.util.ArrayList;


/**
 *
 * @author manuela
 */
public class Conexion_Oracle
{

    private String servidor;
    private String baseDatos;
    private String usuario;
    private String pass;

    String url;
    Connection conexion;

    private static Conexion_Oracle instancia = null;

    //-------------------------------------------------------------------------
    private Conexion_Oracle(String _servidor, String _bd, String _user, String _pswd)
    {
        // The following code emulates slow initialization.
        if (conexion == null)
        {
            servidor = _servidor;
            baseDatos = _bd;
            usuario = _user;
            pass = _pswd;

            url = "jdbc:oracle://" + servidor + ":1521/" + baseDatos;
            conexion = getConector(servidor, baseDatos, usuario, pass);
        }

    }

    //-------------------------------------------------------------------------
    public static Conexion_Oracle getInstance(String _servidor, String _bd,
            String _user, String _pswd)
    {
        if (instancia == null)
        {
            instancia = new Conexion_Oracle(_servidor, _bd, _user, _pswd);
        }
        return instancia;
    }

    
    //-------------------------------------------------------------------------
    public void cerrar()
    {
        if (conexion != null)
        {
            try
            {
                conexion.close();
            } 
            catch (SQLException e)
            {
                System.err.println("Error: Fallo al cerrar la base de datos.");
                e.printStackTrace();
            }
        }

    }

    //---------------------------------------------------------------------
    public Connection getConexion()
    {

        return conexion;
    }

    //---------------------------------------------------------------------
    private Connection getConector(String _servidor, String _bd,
            String _user, String _pswd)
    {
        conexion = null;

        try
        {
            // Cargar el driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Configuración de la URL con parámetros de compatibilidad
            String URL = "jdbc:oracle:thin:@" + _servidor + ":1521/" + _bd ;

            conexion = DriverManager.getConnection(URL, _user, _pswd);
            System.out.println("Conexión exitosa a " + _bd);

        } catch (ClassNotFoundException e)
        {
            System.err.println("Error: No se encontró el Driver de MySQL.");
            e.printStackTrace();

        } catch (SQLException e)
        {
            System.err.println("Error: Fallo al conectar con la base de datos.");
            e.printStackTrace();
        }
        return conexion;
    }

    public boolean insertarDatos(String _tabla, ArrayList<String> _registro)
    {
        boolean correcto = false;

        String sql = "INSERT INTO " + _tabla + " (dni, nombre, direccion, localidad,edad) "
                + "VALUES ( ?,?,?,?,?)";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql))
        {
            // Java pondrá las comillas automáticamente donde haga falta
            pstmt.setString(1, _registro.get(0));
            pstmt.setString(2, _registro.get(1));
            pstmt.setString(3, _registro.get(2));
            pstmt.setString(4, _registro.get(3));
            pstmt.setInt(5, Integer.parseInt(_registro.get(4)));

            // Ejecutamos la inserción
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0)
            {
                System.out.println("¡Usuario insertado con éxito!");
                correcto = true;
            }

            //conexion.commit();  // efectuar los cambios - autocommit esta a true
        } catch (SQLException e)
        {
            System.err.println("Error al insertar: " + e.getMessage());
        }
        return correcto;
    }

    public boolean eliminarDatos(String _tabla, String _clave)
    {
        boolean correcto = false;

        String sql = "DELETE FROM " + _tabla + " WHERE dni = \'" + _clave + "\'";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql))
        {

            // executeUpdate devuelve el número de filas afectadas
            int filasBorradas = pstmt.executeUpdate();

            if (filasBorradas > 0)
            {
                System.out.println("El usuario existía y ha sido eliminado.");
            } else
            {
                System.out.println("No se encontró ningún usuario con ese DNI.");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return correcto;
    }
}
