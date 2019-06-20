/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Map;

/**
 *
 * @author Gabriel
 */
public class DAOAlumnoFactory {

    public static final String TIPO_DAO = "TIPO_DAO";
    public static final String DAO_TXT = "DAO_TXT";
    public static final String DAO_SQL = "DAO_SQL";
    public static final String FILE_NAME = "FILE_NAME";
    public static final String SQL_CONNECTION = "SQL_CONNECTION";

    private static DAOAlumnoFactory instance;
            
    private DAOAlumnoFactory() {
    }
    
    public static DAOAlumnoFactory getInstance() {
        if (instance == null) {
            instance = new DAOAlumnoFactory();
        }
        
        return instance;
    }
    
    public DAO crearDAO(Map<String, String> config) throws DAOException {
        String tipo = config.get(TIPO_DAO);
        
        switch (tipo){
            case DAO_TXT:
                String filename = config.get(FILE_NAME);
                return new AlumnoDAOTXT(filename);
            case DAO_SQL:
                return new AlumnoDAOSQL(config.get(SQL_CONNECTION), "root", "root");
            default:
                throw new DAOException("Tipo de DAO no implementado");
        }
    }
    
}
