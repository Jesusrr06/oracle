/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utiles;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author dam1
 */
public class Utilidades {
    
    public Utilidades() {
    }
    
    public static boolean comprobarMatricula(String m) {
        boolean val = false;
        Pattern pat = Pattern.compile("^\\d{4}[^aeiouñ]{3}$");
        Matcher mat = pat.matcher(m);
        if (mat.find()) {
            val = true;
        }
        if (!val) {
            ES.escribirLn("Vuelva a intentarlo");
        }
        return val;
    }
    
    public static boolean comprobarDni(String dni) {
        if (dni == null || dni.length() != 9) {
            return false;
        }
        boolean correcto = false;
        dni = dni.toUpperCase();
        Pattern patDNI = Pattern.compile("^\\d{8}[TRWAGMYFPDXBNJZSQVHLCKE]$");
        Matcher matDNI = patDNI.matcher(dni);
        if (matDNI.find()) {
            correcto = true;
        }
        
        String num = dni.substring(0, 8);
        char letra = dni.charAt(8);
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numeros = Integer.parseInt(num);
        char letraCorrecta = letras.charAt(numeros % 23);
        if (letra != letraCorrecta) {
            ES.escribirLn("Intentelo de nuevo");
        }
        return correcto & letra == letraCorrecta;
    }
    
    public static boolean comprobarCodigoPostal(String codP) {
        boolean val = false;
        Pattern patr = Pattern.compile("^[0-5]\\d{4}$");
        Matcher match = patr.matcher(codP);
        if (match.find()) {
            val = true;
        }
        if (!val) {
            ES.escribirLn("Vuelva a intentarlo");
        }
        return val;
    }
}
