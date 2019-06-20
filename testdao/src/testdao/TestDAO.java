/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdao;

import dao.AlumnoDAOTXT;
import dao.DAO;
import dao.DAOException;
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
public class TestDAO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            DAO dao = new AlumnoDAOTXT("alumnos.txt");
            
            Alumno alu = new Alumno(1,"ING. SISTEMAS",new MiCalendario(23, 8, 1974),45, 7.5,2222226,"GARCIA", "JUAN",new MiCalendario(23, 11, 1974),'M');
            dao.create(alu);
            System.out.println(dao.read(2222226).toString());
            dao.close();
        } catch (DAOException ex) {
            Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MiCalendarioException ex) {
            Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersonaException ex) {
            Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
