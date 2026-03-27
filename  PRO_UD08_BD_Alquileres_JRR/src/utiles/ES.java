/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author mgordillo
 */
public class ES {

    static public byte leerByte(String _msg, byte _min, byte _max) {
        Scanner s = new Scanner(System.in);
        boolean datoValido = false;

        Byte num = 0;
        do {
            try {
                System.out.print(_msg);
                num = Byte.parseByte(s.nextLine());

                if (num >= _min && num <= _max) {
                    datoValido = true;
                } else {
                    System.out.printf("Debe ser un numero entre %d y %d.\n", _min, _max);
                }
            } catch (NumberFormatException e) {
                System.out.print("El dato introducido no es correcto");
                System.out.println(" Por favor, introduzca un valor correcto.");
            }
        } while (!datoValido);

        return num;
    }

    static public int leerEntero(String _msg) {
        Scanner s = new Scanner(System.in);
        boolean datoValido = false;

        int num = 0;
        do {
            try {
                System.out.print(_msg);
                num = Integer.parseInt(s.nextLine());

                datoValido = true;

            } catch (NumberFormatException e) {
                System.out.print("El dato introducido no es correcto");
                System.out.println(" Por favor, introduzca un valor correcto.");
            }
        } while (!datoValido);

        return num;
    }

    static public byte leerByte(String _msg) {
        Scanner s = new Scanner(System.in);
        boolean datoValido = false;

        byte num = 0;
        do {
            try {
                System.out.print(_msg);
                num = Byte.parseByte(s.nextLine());

                datoValido = true;
            } catch (NumberFormatException e) {
                System.out.print("El dato introducido no es correcto");
                System.out.println(" Por favor, introduzca un valor correcto.");
            }
        } while (!datoValido);

        return num;
    }

    static public int leerInt(String _msg, int _min, int _max) {
        Scanner s = new Scanner(System.in);
        boolean datoValido = false;

        int num = 0;
        do {
            try {
                System.out.println(_msg + " entre " + _min + " y " + _max);
                num = Integer.parseInt(s.nextLine());

                if (num >= _min && num <= _max) {
                    datoValido = true;
                } else {
                    System.out.printf("Debe ser un numero entre %d y %d.\n", _min, _max);

                }
            } catch (NumberFormatException e) {
                System.out.print("El dato introducido no es correcto");
                System.out.println(" Por favor, introduzca un valor correcto.");
            }
        } while (!datoValido);

        return num;
    }

    static public boolean leerBoolean(String _msg) {
        Scanner sb = new Scanner(System.in);
        boolean valido = false;
        boolean valido2;
        try {
            System.out.println(_msg);

            String cadena = sb.next();

            if (cadena.toUpperCase().equals("SI") | cadena.toUpperCase().equals("S")) {
                valido = true;
                valido2 = true;

            } else if (cadena.toUpperCase().equals("NO") | cadena.toUpperCase().equals("N")) {
                valido = false;
                valido2 = false;

            }

        } catch (Exception e) {
            System.out.println(e);

        }
        return valido;

    }

    static public String leerCadena(String _msg) {
        Scanner s = new Scanner(System.in);
        String cadena;
        System.out.print(_msg);

        cadena = s.nextLine();
        System.out.println("");
        return cadena;
    }

    public static void escribirLn(String _cadena) {
        System.out.println(_cadena);
    }

    public static void escribir(String _cadena) {
        System.out.print(_cadena);
    }

    public static boolean escribirArchivo(String ruta, String linea, boolean sobreescribirArchivo) {
        File archivo = new File(ruta);
        BufferedWriter bw = null;
        try {
            File parent = archivo.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            bw = new BufferedWriter(new FileWriter(archivo, !sobreescribirArchivo));
            bw.write(linea);
            if (!linea.endsWith(System.lineSeparator())) {
                bw.newLine();
            }
            bw.flush();
            System.out.println("Datos escritos correctamente en: " + ruta);
            return true;
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
            return false;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    public static String leerArchivo(String ruta) {
        String datos = "";
        
        File fichero = new File(ruta);
        Scanner sc = null;

        try {
            System.out.println("Leyendo el contenido del fichero..........\n\n");
            sc = new Scanner(fichero);

            // leer línea a linea el fichero
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
               datos += linea +"\n" ;
                System.out.println(linea);
            }

            System.out.println("\n --->>   Lectura completada");
        } catch (Exception e) {
            System.out.println("Mensaje:  " + e.getMessage());
        } finally {
            try {
                if (sc != null) {
                    sc.close();
                }
            } catch (Exception e2) {
                System.out.println("Mensaje fichero:   " + e2.getMessage());
            }
        }

        return datos;
    }

}