/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persona.Alumno;
import persona.MiCalendarioException;
import persona.Persona;
import persona.PersonaException;


/**
 *
 * @author Gabriel
 */
public class AlumnoDAOTXT extends DAO<Alumno, Integer>{

    private RandomAccessFile raf;
    
    public AlumnoDAOTXT(String filename) 
            throws DAOException {
        
        File file = new File(filename);
        try {
            raf = new RandomAccessFile(file, "rws");
        } catch (FileNotFoundException ex) {
            throw new DAOException("Archivo No encontrado ==> "+ex.getMessage());
        }
    }
    
    @Override
    public void create(Alumno alumno) 
            throws DAOException {
        try {
            if (exist(alumno.getDni())) {
                throw new DAOException("El alumno ya existe ==> DNI= "+alumno.getDni());
            }
            // Me posiciono al final del archivo
            raf.seek(raf.length());
            raf.writeBytes(alumno.toString()+
                    System.lineSeparator());
        } catch (IOException ex) {
            throw new DAOException("Error al crear alumno ==> "+ex.getMessage());
        }
        
    }

    @Override
    public Alumno read(Integer dni) throws DAOException {
        try {
            raf.seek(0);
            String linea;
            String campos[];
            while ((linea = raf.readLine()) != null) {
                campos = linea.split(Persona.DELIM);
                if (dni.equals(Integer.valueOf(campos[0]))) {
                    return Alumno.string2Alumno(campos);
                }
            }
        } catch (IOException | PersonaException | MiCalendarioException ex) {
            throw new DAOException(ex.getMessage());
        }
        
        return null;
    }

    @Override
    public void update(Alumno alu) throws DAOException {
        // Buscar hasta encontrar alumno
        try {
            raf.seek(0);
            String linea;
            String campos[];
            Long puntero = 0L;
            while ((linea = raf.readLine()) != null) {
                campos = linea.split(Persona.DELIM);
                if (alu.getDni().equals(Integer.valueOf(campos[0]))) {
                    // si encuentro ==> posicionarse
                    raf.seek(puntero);
                    
                    // Actualizar toda la linea con la info del alumno
                    raf.writeBytes(alu.toString());
                    return;
                }
                puntero = raf.getFilePointer();
            }
        } catch (IOException ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public void delete(Integer clave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Alumno> findAll() throws DAOException {
        List<Alumno> alumnos = new ArrayList<>();
        try {
            raf.seek(0);
            String linea;
            String campos[];
            int i = 1;
            while ((linea = raf.readLine()) != null) {
                campos = linea.split(Persona.DELIM);
                alumnos.add(Alumno.string2Alumno(campos));
            }
        } catch (IOException | PersonaException | MiCalendarioException ex) {
            throw new DAOException(ex.getMessage());
        }
        
        return alumnos;
    }

    @Override
    public boolean exist(Integer dni) throws DAOException {
        try {
            raf.seek(0);
            String linea;
            String campos[];
            while ((linea = raf.readLine()) != null) {
                campos = linea.split(Persona.DELIM);
                if (dni.equals(Integer.valueOf(campos[0]))) {
                    return true;
                }
            }
            
        } catch (IOException ex) {
            throw new DAOException(ex.getMessage());
        }
        
        return false;
    }

    @Override
    public void close() throws DAOException {
        try {
            raf.close();
        } catch (IOException ex) {
            throw new DAOException(ex.getMessage());
        }
    }
    
}
