/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package holamundo;

/**
 *
 * @author dam1
 */
public class Holamundo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                
            
            System.out.println(args[i]);
        }} else {
            System.out.println("Hola mundo");
        }
    }

}
