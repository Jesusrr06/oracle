/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pro_ud08_bd_alquileres_jrr;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.Scanner;
import utiles.ES;
import utiles.Utilidades;


/**
 *
 * @author Manuela
 */
public class PRO_UD08_BD_Alquileres_JRR
{

    static Cliente lUsuarios[];
    static int nUsuarios;
  
    
    static ConexionBBDD conexionBD ;

    public static void main(String[] args)
    {
        Scanner teclado = new Scanner(System.in);

        int opcion = -1;

        lUsuarios = new Cliente[10];
        nUsuarios = 0;
        
        conexionBD = conexionBD.getInstance( "172.26.101.114", "Alquileres_JRR", "jrodriguez", "jrodriguez") ;

        cargarDatos();

        do
        {
            opcion = menu();

            switch (opcion)
            {
                case 1:
                    insertarUsuario() ;
                    break;
                case 2:
                    eliminarUsuario() ;
                    break;
                case 3:
                    listar();
                    break;



              

                case 0: 
                    System.out.println("Saliendo....");
                    conexionBD.cerrar();
                    break ;
            }

        } while (opcion != 0);
    }

    //-----------------------------------------------------------------
    public static int menu()
    {
        Scanner teclado = new Scanner(System.in);
        int opcion;
        System.out.println("\n\t     BBDD Usuario");
        System.out.println("\t-------------------------");
        System.out.println("\t1. Insertar usuario (BBDD)");
        System.out.println("\t2. Eliminar usuario (BBDD)");

        System.out.println("\t3. Listar usuarios");

        System.out.println("\t    ---------------");

        System.out.println("\t0. Salir");
        System.out.print("\nSelecciona opcion: ");

        try
        {
            opcion = Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e)
        {
            opcion = -1;
        }
        return opcion;
    }

    //-----------------------------------------------------------------
    /**
     * Insertar usuario a la BBDD
     *
     */
    public static void insertarUsuario()
    {
        ArrayList tuplaUsuario = new ArrayList<String>() ;
       
        String dni;
        String codigoPostal;
        String nombre;
        String direccion;
        String localidad;
         String baja;
        do {
            dni = ES.leerCadena("Introduzca un Dni valido");
            
        } while (!Utilidades.comprobarDni(dni));
        tuplaUsuario.add(dni);
         nombre = ES.leerCadena("Introduzca su nombre");
        tuplaUsuario.add(nombre);
         direccion = ES.leerCadena("Introduzca su direccion");

        tuplaUsuario.add(direccion);
        localidad = ES.leerCadena("Introduzca su localidad");

        tuplaUsuario.add(localidad);
        do {
            codigoPostal = ES.leerCadena("Introduzca su codigo posta");
        } while (!Utilidades.comprobarCodigoPostal(codigoPostal)) ; 
               tuplaUsuario.add(codigoPostal);
 baja = ES.leerCadena("Introduce si esta de baja");
 tuplaUsuario.add(baja);
         
        conexionBD.insertarDatos("Clientes", tuplaUsuario) ;
        
    }
    

    //-----------------------------------------------------------------
    /**
     * Eliminar Usuario a la BBDD
     *
     */
    public static void eliminarUsuario()
    {
        String dni;
        
        
        Scanner sc = new Scanner( System.in) ;
        
        System.out.println(" INSERTAR USUARIO A LA BBDD");
        System.out.print("\nIntroduzca DNI: ");
        dni = sc.nextLine() ;
        
        
        
        conexionBD.eliminarDatos("Clientes", dni) ;
    }

   
    //-------------------------------------------------------------------------
    private static void listar()
    {
        String sql ;
        

        try
        {
            Statement sentencia = (Statement) conexionBD.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM Clientes ;");

            mostrarDatosTabla(resultado);

            resultado.close();
            sentencia.close();
        } catch (SQLException e)
        {

            e.printStackTrace();
        }      

    }    

    //--------------------------------------------------------------------------
    
    //--------------------------------------------------------------------------
 
    //--------------------------------------------------------------------------
    private static void cargarDatos()
    {
        lUsuarios[0] = (new Cliente("12345678Z","Amapola", "Gutierrez de la Vega", " ", ""));

        nUsuarios = 1;
    }

   
    //-------------------------------------------------------------------------------
    private static void mostrarDatosTabla(ResultSet _resultado) throws SQLException
    {
        java.sql.ResultSetMetaData metaDatos = _resultado.getMetaData();
        int nColumnas = metaDatos.getColumnCount();
        String[] formato =
        {
            "%10s", "%-15s", "%-20s", "%-10s" , "%-20s", "%-10s"
        };

        for (int i = 1; i <= nColumnas; i++)
        {
            // Obtenemos el nombre de la columna y su valor
            System.out.printf(formato[i - 1] + ((i < nColumnas) ? " | " : " "), metaDatos.getColumnName(i));
        }
        System.out.println("");

        System.out.println("-----------------------------------------------------------");

         int valor = _resultado.getInt(6);
boolean admin = (valor == 1);
            System.out.printf( " %10s, %-15s ,%-20s ,%-25s, %-30s , %-35s ", 
                    
                    _resultado.getString(1),
                    _resultado.getString(2),
                    _resultado.getString(3),
                    _resultado.getInt(4),
                    _resultado.getString(5) ,
                     (admin? "si":"no") );
    }

}
