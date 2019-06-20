/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persona.Alumno;
import persona.AlumnoException;
import persona.MiCalendario;
import persona.PersonaException;

/**
 *
 * @author Gabriel
 */
public class AlumnoDAOSQL extends DAO<Alumno, Integer>{

    private Connection conn;
    private PreparedStatement prepareStatementSelect = null;
    private PreparedStatement prepareStatementInsert = null;
    // Se agrega Statment para el resto de las consultas
    private PreparedStatement prepareStatementUpdate = null;
    private PreparedStatement prepareStatementDelete = null;
    private PreparedStatement prepareStatementFindAll = null;
    private PreparedStatement prepareStatementExist = null;
    
    AlumnoDAOSQL(String conexion, String user, String pwd) 
            throws DAOException {
        try {
            conn = DriverManager.getConnection(conexion, user, pwd);
        } catch (SQLException ex) {
            throw new DAOException("No se pudo conectar => "+ex.getMessage());
        }
        System.out.println("Conectado OK!!!");
        
        String sqlSelectOne = "SELECT * FROM alumnos " + "WHERE DNI = ?";        
        String sqlUpdate = "UPDATE `alumnos`\n" + "SET\n" + "`apellido` = ?,\n" +"`nombre` = ?,\n" +"`fecha_nac` = ?,\n" +"`promedio` = ?,\n" +"`cant_mat_aprob` = ?,\n" +"`sexo` = ?,\n" + "`carrera` = ?,\n" +"`fecha_ing` = ?\n" +"WHERE `dni` = ?;";        
        String sqlDelete = "DELETE FROM `alumnos` WHERE `dni` = ?";        
        String sqlFinddAll = "SELECT * FROM alumnos";
        String sqlInsert = "INSERT INTO `alumnos`(`dni`,`apellido`,`nombre`,`fecha_nac`,`promedio`,`cant_mat_aprob`,`sexo`,`legajo`,`carrera`,`fecha_ing`)VALUES(?,?,?,?,?,?,?,?,?,?);";
        String sqlExist = "SELECT COUNT(*) FROM alumnos WHERE DNI = ? ;";    
        
        try {
            prepareStatementSelect  =  conn.prepareStatement(sqlSelectOne);
            prepareStatementInsert  =  conn.prepareStatement(sqlInsert);            
            prepareStatementUpdate  =  conn.prepareStatement(sqlUpdate);            
            prepareStatementDelete  =  conn.prepareStatement(sqlDelete);
            prepareStatementFindAll =  conn.prepareStatement(sqlFinddAll);
            prepareStatementExist   =  conn.prepareStatement(sqlExist);
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void create(Alumno alu) throws DAOException {
        try {
            int index = 1;
            prepareStatementInsert.setInt(index++, alu.getDni());
            prepareStatementInsert.setString(index++, alu.getApellido());
            prepareStatementInsert.setString(index++, alu.getNombre());
            prepareStatementInsert.setDate(index++,MiCalendario.convert2SqlDate(alu.getFechaNac()));
            prepareStatementInsert.setDouble(index++, alu.getPromedio());
            prepareStatementInsert.setInt(index++, alu.getCantMatAprob());
            prepareStatementInsert.setString(index++, toString(alu.getSexo()));
            prepareStatementInsert.setInt(index++, alu.getLegajo());
            prepareStatementInsert.setString(index++, alu.getCarrera());
            prepareStatementInsert.setDate(index++,MiCalendario.convert2SqlDate(alu.getFechaIng()));
            prepareStatementInsert.executeUpdate();
            System.out.println("Alumno agregado con exito");
        } catch (SQLException ex) {
            throw new DAOException("No se pudo agregar alumno"+ex.getMessage());
        }
    }

    @Override
    public Alumno read(Integer dni) throws DAOException {

        try {
            Alumno alu = null;
            
            prepareStatementSelect.setInt(1, dni);
            ResultSet rs = prepareStatementSelect.executeQuery();
            if (rs.next()) {
                alu = new Alumno();
                setAlumnoProperties(alu, rs);
            }
            
            return alu;
        } catch (SQLException | PersonaException ex) {
            throw new DAOException("No se pudo leer alumno"+ex.getMessage());
        }
        
    }

    public void setAlumnoProperties(Alumno alu, ResultSet rs) throws AlumnoException, SQLException, PersonaException {
        alu.setDni(rs.getInt("dni"));
        alu.setApellido(rs.getString("apellido"));
        alu.setNombre(rs.getString("nombre"));
        alu.setFechaNac(MiCalendario.convert2MiCalendario(rs.getDate("FECHA_NAC")));
        alu.setPromedio(rs.getDouble("promedio"));
        alu.setCantMatAprob(rs.getInt("CANT_MAT_APROB"));
        alu.setSexo((rs.getString("sexo").charAt(0)));
        alu.setLegajo(rs.getInt("legajo"));
        alu.setCarrera(rs.getString("carrera"));
        alu.setFechaIng(MiCalendario.convert2MiCalendario(rs.getDate("fecha_ing")));
    }

    @Override
    public void update(Alumno alu) throws DAOException {
        int index = 1;        
        try {
            prepareStatementUpdate.setString(index++, alu.getApellido());
            prepareStatementUpdate.setString(index++, alu.getNombre());
            prepareStatementUpdate.setDate(index++,MiCalendario.convert2SqlDate(alu.getFechaNac()));
            prepareStatementUpdate.setDouble(index++, alu.getPromedio());
            prepareStatementUpdate.setInt(index++, alu.getCantMatAprob());
            prepareStatementUpdate.setString(index++, toString(alu.getSexo()));
            prepareStatementUpdate.setString(index++, alu.getCarrera());
            prepareStatementUpdate.setDate(index++,MiCalendario.convert2SqlDate(alu.getFechaIng()));
            prepareStatementUpdate.setInt(index++, alu.getDni());
            prepareStatementUpdate.executeUpdate();
            System.out.println("Alumno con DNI "+alu.getDni()+" modificado con exito");
            
            } 
        catch (SQLException ex) {
            throw new DAOException("No se pudo actualizar alumno"+ex.getMessage());
        }   
    }

    @Override
    public void delete(Integer dni) throws DAOException {
        try {
            prepareStatementDelete.setInt(1,dni);
            prepareStatementDelete.execute();
            System.out.println("Alumno con DNI "+dni+"  borrado con exito");
        } catch (SQLException ex) {
            throw new DAOException("No se pudo borrar alumno"+ex.getMessage());
        }
    }

    @Override
    public List<Alumno> findAll() throws DAOException {
        
        List<Alumno> listaAlumnos = new ArrayList<>();
        ResultSet rs;
        try {            
            rs = prepareStatementFindAll.executeQuery();
            while (rs.next())
            {
            Alumno alu= new Alumno();
            alu.setDni(rs.getInt("dni"));
            alu.setApellido(rs.getString("apellido"));
            alu.setNombre(rs.getString("nombre"));
            alu.setFechaNac(MiCalendario.convert2MiCalendario(rs.getDate("FECHA_NAC")));
            alu.setPromedio(rs.getDouble("promedio"));
            alu.setCantMatAprob(rs.getInt("CANT_MAT_APROB"));
            alu.setSexo((rs.getString("sexo").charAt(0)));
            alu.setLegajo(rs.getInt("legajo"));
            alu.setCarrera(rs.getString("carrera"));
            alu.setFechaIng(MiCalendario.convert2MiCalendario(rs.getDate("fecha_ing")));
            listaAlumnos.add(alu);
                    
            }
            
            
        } catch (SQLException | PersonaException ex) {
              throw new DAOException("No se pudo listar alumnos"+ex.getMessage());
            
        }
        
        return listaAlumnos;
        
    }

    @Override
    public boolean exist(Integer dni) throws DAOException {
        
        int n = 0;
      
        try {
            
            
            try {
                prepareStatementExist.setInt(1, dni);
            } catch (SQLException ex) {
                Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            ResultSet  rs = null;
            try {
                rs = prepareStatementExist.executeQuery();
            } catch (SQLException ex) {
                Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(rs.next()) {
                n = rs.getInt(1);
                
                if(n!=0)
                {
                    System.out.println("El Alumno con DNI "+dni+" existe");
                    return true;
                }
                
            
            else
            {
                System.out.println("El Alumno  con DNI "+dni+" no existe");
                return false;
            }
            }                
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void close() throws DAOException {
        try {
            System.out.println("Conexion cerrada con exito");
            conn.close();
        } catch (SQLException ex) {
            throw new DAOException("No se pudo cerrar la conexión ==>"+ex.getMessage());
        }
    }

    private String toString(char sexo) {
        String cadena = Character.toString(sexo);
        return cadena;
    }
    
}
