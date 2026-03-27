/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro_ud08_bd_alquileres_jrr;

import java.sql.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author manuela
 */
public class ConexionBBDD
{

    private String servidor;
    private String baseDatos;
    private String usuario;
    private String pass;

    String url;
    Connection conexion;

    private static ConexionBBDD instancia = null;

    //-------------------------------------------------------------------------
    private ConexionBBDD(String _servidor, String _bd, String _user, String _pswd)
    {
        // The following code emulates slow initialization.
        if (conexion == null)
        {
            servidor = _servidor;
            baseDatos = _bd;
            usuario = _user;
            pass = _pswd;

            url = "jdbc:mysql://" + servidor + ":3306/" + baseDatos
                    + "?useSSL=false"
                    + "&serverTimezone=UTC"
                    + "&allowPublicKeyRetrieval=true";
            conexion = getConector(servidor, baseDatos, usuario, pass);
        }

    }

    //-------------------------------------------------------------------------
    public static ConexionBBDD getInstance(String _servidor, String _bd,
            String _user, String _pswd)
    {
        if (instancia == null)
        {
            instancia = new ConexionBBDD(_servidor, _bd, _user, _pswd);
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
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Configuración de la URL con parámetros de compatibilidad
            String URL = "jdbc:mysql://" + _servidor + ":3306/" + _bd
                    + "?useSSL=false"
                    + "&serverTimezone=UTC"
                    + "&allowPublicKeyRetrieval=true";

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

        String sql = "INSERT INTO " + _tabla + " (dni, nombre,direccion,localidad,codigopostal,baja ) "
                + "VALUES ( ?,?,?,?,?,?)";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql))
        {
            // Java pondrá las comillas automáticamente donde haga falta
            pstmt.setString(1, _registro.get(0));
            pstmt.setString(2, _registro.get(1));
            pstmt.setString(3, _registro.get(2));
            pstmt.setString(4, _registro.get(3));
            pstmt.setString(5, _registro.get(4));
            if(_registro.get(5).equals("false")||_registro.get(5).equals("n")||_registro.get(5).equals("no")){
           pstmt.setBoolean(6, false);

                
            }else{
                       pstmt.setBoolean(6, true);

            }

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
