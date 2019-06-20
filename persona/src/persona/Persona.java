/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persona;

import java.util.Calendar;

/**
 *
 * @author Gabriel
 */
public abstract class Persona {
    
    public final static String DELIM = "\t";
    
    private Integer dni;
    
    private String apellido;
    
    private String nombre;
    
    private MiCalendario fechaNac;
    
    private char sexo;
    
    public Persona(Integer dni) throws PersonaException {
        setDni(dni);
    }
    
    //Agrego validaciones al contructor

    public Persona(Integer dni, String apellido, String nombre,MiCalendario fechaNac, char sexo) throws PersonaException {
        setDni(dni);
        setApellido(apellido);
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        setSexo(sexo);
    }

    
    
    public Persona() {
        super();
    }

    public Integer getDni() {
        return dni;
    }

    public final void setDni(Integer dni) throws PersonaException {
        if (dni <=0 || dni>99999999) {
           throw new PersonaException("El dni debe ser positivo");
        }
        
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public final void setApellido(String apellido) throws PersonaException{
        if (apellido==null || apellido.trim().isEmpty()) {
            throw new PersonaException("El apellido debe tener contenido");
        }
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MiCalendario getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(MiCalendario fechaNac) {
        this.fechaNac = fechaNac;
    }

    public char getSexo() {
        return sexo;
    }

    public final void setSexo(char sexo) throws AlumnoException {
        sexo = Character.toUpperCase(sexo);
        if (sexo!='F' && sexo!='M' ) {
            throw new AlumnoException("Los unicos valores validos para sexo son M o F");
        }
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return String.format("%08d", dni)+DELIM+
                String.format("%-20s",apellido)+DELIM+
                fechaNac; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public abstract String getInfo();
}
