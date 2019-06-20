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
            alu = new Alumno(14,"tecnica",new MiCalendario(26, 10, 2007),52, 8.33,23729256, "reha", "carlos",new MiCalendario(23, 12, 1990),'m');
            //alu = new Alumno(9,"Abogacia",new MiCalendario(6, 12, 2010),41, 6.1,36521256, "Perez", "Jimena",new MiCalendario(23, 12, 1989),'F');
            //alu = new Alumno(10,"tecnica",new MiCalendario(26, 1, 2014),22, 8, 30521256, "reha", "carlos",new MiCalendario(10, 12, 1989),'M');
        } catch (PersonaException | MiCalendarioException ex) {
            Logger.getLogger(TestSingleton.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        try {
            //dao.create(alu);
            //dao.exist(36524125);
            //dao.update(alu);
            //dao.delete(36524125);
            //System.out.println("Alumno: "+ dao.read(36524125));
            List<Alumno> lista = dao.findAll();
            lista.forEach((alumno) -> {
                System.out.println(alumno);
            });
            
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
