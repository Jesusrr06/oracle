/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pro_ud08_oracle_usuario;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import oracle.jdbc.OracleArray;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author virtual
 */
public class PRO_UD08_ORACLE_Usuario {

    static ArrayList<Cliente> lUsuarios;

    static final String NOMBRE_FICHERO = "usuario.txt";
    static final String NOMBRE_XML = "usuarios.xml";
    static final String ET_USUARIOS = "usuarios";
    
    static Conexion_Oracle conexionBD ;

    public static void main(String[] args)
    {
        Scanner teclado = new Scanner(System.in);

        int opcion = -1;

        lUsuarios = new ArrayList<>();
               
        conexionBD = conexionBD.getInstance( "172.26.43.207", "XEPDB1", "pro_usuarios", "P4ssw0rd") ;

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


                case 4:
                 //   escribirXML();
                    break;

                case 5:
                  //  leerXML();
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
        System.out.println("\n\t     BBDD Oracle Usuario");
        System.out.println("\t----------------------------");
        System.out.println("\t1. Insertar usuario (oracle)");
        System.out.println("\t2. Eliminar usuario (oracle)");

        System.out.println("\t3. Listar usuarios (oracle)");

        System.out.println("\t    ---------------");
        System.out.println("\t4.- Escribir XML");
        System.out.println("\t5.- Leer XML");

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
        
        
        Scanner sc = new Scanner( System.in) ;
        
        System.out.println(" INSERTAR USUARIO DE ORACLE");
        System.out.print("\nIntroduzca DNI: ");
        tuplaUsuario.add( sc.nextLine() );
        
        System.out.print("Introduzca nombre: ");
        tuplaUsuario.add( sc.nextLine() );
        
        System.out.print("Introduzca direccion: ");
        tuplaUsuario.add( sc.nextLine() );       
        
        System.out.print("Introduzca localidad: ");
        tuplaUsuario.add( sc.nextLine() );
        System.out.println("Introduzca su edad");
        tuplaUsuario.add(sc.nextLine());
        conexionBD.insertarDatos("PRO_USUARIOS.CLIENTE_JRR", tuplaUsuario) ;
        
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
        
        System.out.println(" ELIMINAR Clientes DE ORACLE");
        System.out.print("\nIntroduzca DNI: ");
        dni = sc.nextLine() ;
        
        
        
        conexionBD.eliminarDatos("PRO_USUARIOS.CLIENTE_JRR", dni) ;
    }

   
    //-------------------------------------------------------------------------
    private static void listar()
    {
        String sql ;
        

        try
        {
            Statement sentencia = (Statement) conexionBD.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM PRO_USUARIOS.CLIENTE_JRR ");

            mostrarDatosTabla(resultado);

            resultado.close();
            sentencia.close();
        } catch (SQLException e)
        {

            e.printStackTrace();
        }      

    }    

    
    /** 
     * private static void escribirXML()  
     * {
        try
        {
            // Crear una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Crear un documentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Crear un DOMImplementation
            //DOMImplementation implementation = builder.getDOMImplementation();
            // Crear un documento con un elemento raiz
            Document documento = builder.newDocument();
            // documento.setXmlVersion("1.0");
  
            System.out.println("----------------------------------------------");
            System.out.println(" --------->   Escribiendo fichero XML");
            Element elementoRaiz = documento.createElement(ET_USUARIOS);
            documento.appendChild(elementoRaiz);

            // Guardar los usuarios
            for (int i = 0; i < lUsuarios.size(); i++)
            {
                System.out.println(" ........ ");

                Element eUsuario = lUsuarios.get(i).escribirXML(documento);
                elementoRaiz.appendChild(eUsuario);
            }

            // Asociar el source con el Document
            Source fuente = new DOMSource(documento);

            // Crear el Result, indicado que fichero se va a crear
            Result resultado = new StreamResult(new File(NOMBRE_XML));

            // Creo un transformer, se crea el fichero XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            //Insertar saltos de línea al final de cada línea
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(fuente, resultado);

            System.out.println("----------------------------------------------");
            System.out.println("Fichero guardado en: " + "usuario.xml");
        } catch (ParserConfigurationException | TransformerException ex)
        {
            System.out.println(ex.getMessage());
        }
        System.out.println("¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬");

     }
    
   */
     
    
    //--------------------------------------------------------------------------
   /** private static void leerXML()
    {
        lUsuarios.clear();
    

        System.out.println("----------------------------------------------");
        System.out.println(" --------->   Lectura fichero XML");
        try
        {

            // Crear una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Crear un documentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Obtener el documento, a partir del XML
            Document documento = builder.parse(new File(NOMBRE_XML));
            // Coger todas las etiquetas Usuario del documento
            NodeList listaNodos = null;

            listaNodos = documento.getElementsByTagName("Usuario");
            // lectura y cargar los clientes
            for (int i = 0; i < listaNodos.getLength(); i++)
            {
                // Cojo el nodo actual
                Node nodo = listaNodos.item(i);

                // Comprobar si el nodo es un elemento
                if (nodo.getNodeType() == Node.ELEMENT_NODE)
                {
                    Cliente usuario = new Usuario();
                    usuario.leerXML(nodo);

                    lUsuarios.add( usuario);


                    System.out.println(" ........ ");
                }
            }

        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex)
        {
            System.out.println(ex.getMessage());
        }
        System.out.println("¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬");
    }
*/
    
    //--------------------------------------------------------------------------
    private static void cargarDatos()
    {
        lUsuarios.add(new Cliente("12345678Z","Amapola", "Gutierrez de la Vega","h" ));

    }

   
    //-------------------------------------------------------------------------------
    private static void mostrarDatosTabla(ResultSet _resultado) throws SQLException
    {
        java.sql.ResultSetMetaData metaDatos = _resultado.getMetaData();
        int nColumnas = metaDatos.getColumnCount();
        String[] formato =
        {
            "%10s", "%-15s", "%-20s", "%-10s" ,"%-10s"
        };

        for (int i = 1; i <= nColumnas; i++)
        {
            // Obtenemos el nombre de la columna y su valor
            System.out.printf(formato[i - 1] + ((i < nColumnas) ? " | " : " "), metaDatos.getColumnName(i));
        }
        System.out.println("");

        System.out.println("-----------------------------------------------------------");

        while (_resultado.next())
        {
            System.out.printf(" %10s, %-15s ,%-20s ,%-20s, %-10s",
               _resultado.getString(1),     _resultado.getString(2), _resultado.getString(3), _resultado.getString(4) , _resultado.getInt(5));

        }
    }
    
}
