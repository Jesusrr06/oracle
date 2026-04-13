/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro_ud08_oracle_jrr;

import java.io.Serializable;

/**
 *
 * @author dam1
 */
public class Cliente implements Serializable {

    private String dni;
    private String nombre;
    private String direccion;
    private String localidad;
    private boolean baja;

    public Cliente(String dni, String nombre, String direccion, String localidad, String codigoPostal) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.localidad = localidad;
        this.baja = false;
    }

    public Cliente(String dni, String nombre, String direccion, String localidad) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.localidad = localidad;
    }

    public Cliente(Cliente c) {
        this.dni = c.dni;
        this.nombre = c.nombre;
        this.localidad = c.localidad;
                this.direccion = c.direccion;

    }

   

    

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLocalidad() {
        return localidad;
    }



    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
        sb.append("").append(dni);
        sb.append("#");

        sb.append("").append(nombre);
        sb.append("#");

        sb.append("").append(direccion);
        sb.append("#");

        sb.append("").append(localidad);
        sb.append("#");

      
        return sb.toString();
    }

    public String toString2() {
     
    StringBuilder sb = new StringBuilder();
        sb.append("Cliente{");
        sb.append("\n");

        sb.append("dni=").append(dni);
        sb.append("\n");

        sb.append(", nombre=").append(nombre);
        sb.append("\n");
        sb.append("direccion").append(direccion);
        sb.append("\n");
        sb.append(", localidad=").append(localidad);
        sb.append("\n");

       

        return sb.toString();
    }

}
