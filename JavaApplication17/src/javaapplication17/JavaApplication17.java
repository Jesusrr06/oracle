/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication17;

/**
 *
 * @author dam1
 */
public class JavaApplication17 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] array = new int[5];
        int aux = 0;
        int aux2 = 0;
        for (int i = 0; i < array.length-1; i++) {
            array[i] = (int) (Math.random() * 10);
        }
         for (int i = 0; i < array.length-1; i++) {
                    System.out.println(array[i]);

        }
        for (int i = 0; i < array.length-1; i++) {
        if(array[i]> array[i+1] ){
            aux = array[i];
            array[i] = array[i+1];
            array[i+1]= aux;
        }
        }
        for (int i = 0; i < array.length-1; i++) {
                    System.out.println(array[i]);

        }
    }

}
