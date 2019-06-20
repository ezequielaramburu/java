/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import persona.Alumno;

/**
 *
 * @author Ezequiel
 */
public class AlumnoDAOSQLTest {
    
    public AlumnoDAOSQLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class AlumnoDAOSQL.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Alumno alu = null;
        AlumnoDAOSQL instance = null;
        instance.create(alu);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class AlumnoDAOSQL.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        Integer dni = null;
        AlumnoDAOSQL instance = null;
        Alumno expResult = null;
        Alumno result = instance.read(dni);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAlumnoProperties method, of class AlumnoDAOSQL.
     */
    @Test
    public void testSetAlumnoProperties() throws Exception {
        System.out.println("setAlumnoProperties");
        Alumno alu = null;
        ResultSet rs = null;
        AlumnoDAOSQL instance = null;
        instance.setAlumnoProperties(alu, rs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class AlumnoDAOSQL.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Alumno alu = null;
        AlumnoDAOSQL instance = null;
        instance.update(alu);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class AlumnoDAOSQL.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Integer dni = null;
        AlumnoDAOSQL instance = null;
        instance.delete(dni);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class AlumnoDAOSQL.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        AlumnoDAOSQL instance = null;
        List<Alumno> expResult = null;
        List<Alumno> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exist method, of class AlumnoDAOSQL.
     */
    @Test
    public void testExist() throws Exception {
        System.out.println("exist");
        Integer dni = null;
        AlumnoDAOSQL instance = null;
        boolean expResult = false;
        boolean result = instance.exist(dni);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of close method, of class AlumnoDAOSQL.
     */
    @Test
    public void testClose() throws Exception {
        System.out.println("close");
        AlumnoDAOSQL instance = null;
        instance.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
