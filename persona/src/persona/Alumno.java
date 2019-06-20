/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persona;

/**
 *
 * @author Gabriel
 */
public class Alumno extends Persona {

    // No usar!!!
    private int legajo;
    
    private String carrera;
    
    private MiCalendario fechaIng;
    
    private Integer cantMatAprob;
    
    private Double promedio;

    public Alumno() {
    }

    public Alumno(int legajo, String carrera, MiCalendario fechaIng, Integer cantMatAprob, Double promedio) {
        this.legajo = legajo;
        this.carrera = carrera;
        this.fechaIng = fechaIng;
        this.cantMatAprob = cantMatAprob;
        this.promedio = promedio;
    }

    public Alumno(int legajo, String carrera, MiCalendario fechaIng, Integer cantMatAprob, Double promedio, Integer dni, String apellido, String nombre, MiCalendario fechaNac, char sexo) throws PersonaException {
        super(dni, apellido, nombre, fechaNac, sexo);
        this.legajo = legajo;
        this.carrera = carrera;
        this.fechaIng = fechaIng;
        this.cantMatAprob = cantMatAprob;
        setPromedio(promedio);
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public MiCalendario getFechaIng() {
        return fechaIng;
    }

    public void setFechaIng(MiCalendario fechaIng) 
            throws AlumnoException {
        if (fechaIng.esMenorQue(this.getFechaNac())) {
            throw new AlumnoException("La fecha de Ingreso debe ser mayor a la de nacimiento");
        }
        
        this.fechaIng = fechaIng;
    }

    public Integer getCantMatAprob() {
        return cantMatAprob;
    }

    public void setCantMatAprob(Integer cantMatAprob) {
        this.cantMatAprob = cantMatAprob;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) throws AlumnoException{
        if (promedio == null || promedio<0 || promedio>10) {
            throw new AlumnoException("El promedio es inválido");
        }
        this.promedio = promedio;
    }
    
    @Override
    public String getInfo() {

        return "";
    }

    @Override
    public String toString() {
        carrera = carrera.length()>20?
                    carrera.substring(0, 20):carrera;
        return super.toString()+DELIM+
                legajo+DELIM+
                String.format("%-20s", carrera)+DELIM+
                fechaIng+DELIM+
                cantMatAprob+DELIM+
                String.format("%5.2f", promedio); 
    }

    public static Alumno string2Alumno(String[] campos) 
            throws PersonaException, MiCalendarioException {
        Alumno alumno = new Alumno();
        int indice=0;
        alumno.setDni(Integer.valueOf(campos[indice++]));
        alumno.setApellido(campos[indice++]);
        alumno.setNombre(campos[indice++]);
        alumno.setLegajo(Integer.valueOf(campos[indice++]));
        alumno.setCarrera(campos[indice++]);
               
        String fechaIng[] = campos[indice++].split("/");
        int dia = Integer.valueOf(fechaIng[0]);
        int mes = Integer.valueOf(fechaIng[1]);
        int anio = Integer.valueOf(fechaIng[2]);
        alumno.setFechaIng(new MiCalendario(dia, mes, anio));
        
        alumno.setCantMatAprob(Integer.valueOf(campos[indice++]));
        alumno.setPromedio(Double.valueOf(campos[indice++].replace(",", ".")));
        return alumno;
    }

    
}
