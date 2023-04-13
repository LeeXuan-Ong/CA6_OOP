package com.dkit.LeeXuanOng.SD2A.DAOs.InstrumentDAO;

import com.dkit.LeeXuanOng.SD2A.Comparator.InstrumentsIDComparator;
import com.dkit.LeeXuanOng.SD2A.Comparator.InstrumentsNameComparator;
import com.dkit.LeeXuanOng.SD2A.Comparator.InstrumentsPriceComparator;
import com.dkit.LeeXuanOng.SD2A.DAOExceptions.DAOException;
import com.dkit.LeeXuanOng.SD2A.DTOs.Instrument;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class MySqlInstrumentDAOTest {
    private Connection connection;

    private MySqlInstrumentDAO m;


    @Before
    public void setUp() throws Exception {
        this.m = new MySqlInstrumentDAO();
        this.m.isTest_database();
        System.out.println(this.m.getTest_database());
    }

    @After
    public void tearDown() throws Exception {
        m.freeConnection(connection);
        this.m = null;
    }

    @Test
    public void testGetConnection() throws DAOException {
        this.connection = m.getConnection();
        assertNotNull(connection);
    }

    @Test
    public void testFreeConnection() throws SQLException {
        m.freeConnection(connection);
        assertNull(connection);
    }

    @Test
    public void testGetAllIds() throws DAOException{
        Set<Integer> actual = m.getAllIds();
        assertNotNull(actual);
    }

    @Test
    public void testFindAllInstruments() throws DAOException{
        List<Instrument> actual = m.findAllInstruments();
        assertNotNull(actual);
    }

    @Test
    public void testFindInstrumentByInstrumentId() throws DAOException{
        Instrument actual = m.findInstrumentByInstrumentId(1);
        Instrument expected = new Instrument(1, "Guitar", "Is a Guitar", 10, 100, "String");
        if(expected.equals(actual)){
            assertTrue(true);
        } else {
            fail();
        }
    }

    @Test
    public void testFindInstrumentsUsingFilterID() throws DAOException {
        Comparator<Instrument> InstrumentsIDComparator = new InstrumentsIDComparator();
        List<Instrument> actual = m.findInstrumentsUsingFilter(InstrumentsIDComparator);
        List<Instrument> expected = m.findInstrumentsUsingFilter(Comparator.comparingInt(Instrument::getId));
        for(int i = 0; i < actual.size(); i++){
            if(!actual.get(i).equals(expected.get(i))){
                fail();
            }
        }
        assertTrue(true);
    }

    @Test
    public void testFindInstrumentsUsingFilterName() throws DAOException {
        List<Instrument> expected = m.findInstrumentsUsingFilter(Comparator.comparing(Instrument::getInsName));
        Comparator<Instrument> InstrumentsNameComparator = new InstrumentsNameComparator();
        List<Instrument> actual = m.findInstrumentsUsingFilter(InstrumentsNameComparator);
        for(int i = 0; i < actual.size(); i++){
            if(!actual.get(i).equals(expected.get(i))){
                fail();
            }
        }
        assertTrue(true);
    }

    @Test
    public void testFindInstrumentsUsingFilterPrice() throws DAOException {
        List<Instrument> expected = m.findInstrumentsUsingFilter(Comparator.comparing(Instrument::getInsPrice));
        Comparator<Instrument> InstrumentsPriceComparator = new InstrumentsPriceComparator();
        List<Instrument> actual = m.findInstrumentsUsingFilter(InstrumentsPriceComparator);
        for(int i = 0; i < actual.size(); i++){
            if(!actual.get(i).equals(expected.get(i))){
                fail();
            }
        }
        assertTrue(true);
    }

    @Test
    public void testFindAllInstrumentsJson() throws DAOException {
        String actual = m.findAllInstrumentsJson();
        assertNotNull(actual);
    }

    @Test
    public void testFindInstrumentByInstrumentIdJson() throws DAOException {
        String expected = "{\"id\":1,\"insName\":\"Guitar\",\"insDesc\":\"This is a guitar\",\"insStock\":10,\"insPrice\":100.0,\"insCategory\":\"String\"}";
        String actual = m.findInstrumentByInstrumentIdJson(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testAddInstrument() throws DAOException {
        Instrument instrument = new Instrument("Guitar", "Is a Guitar", 10, 100, "String");
        int result = m.addInstrument(instrument);
        Instrument expected = m.findInstrumentByInstrumentId(result);
        if(expected.equals(instrument)){
//            m.deleteInstrument(result);
            assertTrue(true);
        } else {
            fail();
        }


    }
    @Test
    public void testDeleteInstrument() throws DAOException {
        Instrument instrument = new Instrument("Guitar", "Is a Guitar", 10, 100, "String");
        int result = m.addInstrument(instrument);
        boolean actual = m.deleteInstrument(result);
        assertTrue(actual);
    }
}