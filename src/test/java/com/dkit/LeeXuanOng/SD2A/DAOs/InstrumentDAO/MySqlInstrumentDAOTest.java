package com.dkit.LeeXuanOng.SD2A.DAOs.InstrumentDAO;

import com.dkit.LeeXuanOng.SD2A.DAOException.DAOException;
import com.dkit.LeeXuanOng.SD2A.DTOs.Instrument;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class MySqlInstrumentDAOTest {

    @Test
    public void testFindAllInstruments() throws DAOException {
        MySqlInstrumentDAO m = new MySqlInstrumentDAO()   ;
        List<Instrument> l = m.findAllInstruments();
        boolean expected = l.size()>0;
        assertTrue(expected);

    }

    @Test
    public void testFindInstrumentByInstrumentId() throws DAOException{
        MySqlInstrumentDAO m = new MySqlInstrumentDAO();
        Instrument i = m.findInstrumentByInstrumentId(1);
        assertEquals("Guitar", i.getInsName());
    }

    @Test
    public void testAddInstrument() throws DAOException {
        MySqlInstrumentDAO m = new MySqlInstrumentDAO();
        int result = m.addInstrument(new Instrument( "Guitar", "Is a Guitar", 10, 100, "String"));
        assert(result != -1);
    }

    @Test
    public void testDeleteInstrument() throws DAOException {
        MySqlInstrumentDAO m = new MySqlInstrumentDAO();
        boolean result = m.deleteInstrument(30);
        assertTrue(result);
    }

}