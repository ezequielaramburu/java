/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author Gabriel
 */
public abstract class DAO<T, K> {
    
    /**
     *
     * @param entidad
     */
    public abstract void create(T entidad) throws DAOException;
    public abstract T read(K clave) throws DAOException;
    public abstract void update(T entidad) throws DAOException;
    public abstract void delete(K clave) throws DAOException;
    public abstract List<T> findAll() throws DAOException;
    public abstract boolean exist(K clave) throws DAOException;
    public abstract void close() throws DAOException;

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
