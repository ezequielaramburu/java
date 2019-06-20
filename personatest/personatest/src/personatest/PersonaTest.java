/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personatest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persona.Alumno;
import persona.AlumnoException;
import persona.MiCalendario;
import persona.MiCalendarioException;
import persona.Persona;
import persona.PersonaException;

/**
 *
 * @author Gabriel
 */
public class PersonaTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            /*
            Persona persona1 = new Persona();
            try {
            persona1.setDni(24004600);
            } catch (PersonaException ex) {
            Logger.getLogger(PersonaTest.class.getName()).log(Level.SEVERE, null, ex);
            return;
            }
            
            System.out.println("TODO OK!!!");
            System.out.println("Perosna con DNI = "+
            persona1.getDni());
            
            Persona persona2;
            try {
            persona2 = new Persona(24004601);
            } catch (PersonaException ex) {
            Logger.getLogger(PersonaTest.class.getName()).log(Level.SEVERE, null, ex);
            return;
            }
            
            System.out.println("TODO OK!!!");
            System.out.println("Perosna2 con DNI = "+
            persona2.getDni());
            
            Persona persona3;
            try {
            MiCalendario fechaNac =
            new MiCalendario(32, 4, 2019);
            persona3 = new Persona(24004600,
            "PEREZ",
            "Juan",
            fechaNac,
            'M');
            } catch (PersonaException | MiCalendarioException ex) {
            Logger.getLogger(PersonaTest.class.getName()).log(Level.SEVERE, null, ex);
            return;
            }
            
            System.out.println("TODO OK!!!");
            System.out.println("Perosna3 con DNI = "+
            persona3.getDni());
            System.out.println("Apellido = "+
            persona3.getApellido());
            System.out.println("Nombre = "+
            persona3.getNombre());
            System.out.println("Fecha Nac. = "+
            persona3.getFechaNac());
            */
            List<Alumno> alumnos = 
                    new ArrayList<>();
            Alumno pepe = new Alumno(1,
                    "ING. SISTEMAS",
                    new MiCalendario(1, 3, 1993),
                    45, 7.50, 
                    22222222,
                    "PEREZ", "JUAN",
                    new MiCalendario(23, 8, 1974),
                    'M');
            
            alumnos.add(pepe);

            Alumno pepe2 = new Alumno(1,
                    "ING. SISTEMAS",
                    new MiCalendario(1, 4, 1993),
                    45, 7.50, 
                    22222223,
                    "PEREZ", "JUANA",
                    new MiCalendario(24, 8, 1974),
                    'F');
            
            alumnos.add(pepe2);
            
            Alumno pepe3 = new Alumno(1,
                    "ING. SISTEMAS",
                    new MiCalendario(1, 3, 1993),
                    45, 7.50, 
                    22222224,
                    "GOMEZ", "PEPE",
                    new MiCalendario(23, 8, 1974),
                    'M');
            
            alumnos.add(pepe3);
            
            for (Alumno alumno : alumnos) {
                System.out.println("Alumno: "+alumno.toString());
            }
            
        } catch (MiCalendarioException ex) {
            Logger.getLogger(PersonaTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersonaException ex) {
            if (ex instanceof AlumnoException) {
                System.err.println("AlumnoException");
            }
            else {
                System.err.println("PersonaException");
            }
           Logger.getLogger(PersonaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
