/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsingleton;

import dao.DAO;
import dao.DAOAlumnoFactory;
import dao.DAOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import persona.Alumno;
import persona.MiCalendario;
import persona.MiCalendarioException;
import persona.PersonaException;

/**
 *
 * @author Gabriel
 */
public class TestSingleton {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DAOAlumnoFactory factory = 
                DAOAlumnoFactory.getInstance();
        
        Map<String, String> config = new HashMap<>();
//        config.put(DAOAlumnoFactory.TIPO_DAO, DAOAlumnoFactory.DAO_TXT);
//        config.put(DAOAlumnoFactory.FILE_NAME, "alu.txt");
        
        config.put(DAOAlumnoFactory.TIPO_DAO, DAOAlumnoFactory.DAO_SQL);
        config.put(DAOAlumnoFactory.SQL_CONNECTION, "jdbc:mysql://localhost:3306/java?zeroDateTimeBehavior=convertToNull");

        DAO<Alumno, Integer> dao;
        try {
            dao = factory.crearDAO(config);
        } catch (DAOException ex) {
            Logger.getLogger(TestSingleton.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        Alumno alu;
        try {
            alu = new Alumno(6,"tecnica",new MiCalendario(26, 10, 1990),52, 8.33,36524125, "reha", "carlos",new MiCalendario(23, 12, 1989),'M');
        } catch (PersonaException | MiCalendarioException ex) {
            Logger.getLogger(TestSingleton.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        try {
            //dao.create(alu);
            dao.exist(36524125);
            //dao.update(alu);
            System.out.println("Alumno: "+ dao.read(36524125));
            //dao.delete(12345674);
           
          /*  List<Alumno> lista = dao.findAll();
            lista.forEach((alumno) -> {
                System.out.println(alumno);
            }); //dao.update(alu);
            /*
            List<Alumno> lista = dao.findAll();
            for (Alumno alumno : lista) {
            System.out.println(alumno); 
            }
             */
        } catch (DAOException ex) {
            Logger.getLogger(TestSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            dao.close();
        } catch (DAOException ex) {
            Logger.getLogger(TestSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
    }
    
}
