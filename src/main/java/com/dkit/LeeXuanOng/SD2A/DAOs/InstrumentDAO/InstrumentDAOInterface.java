package com.dkit.LeeXuanOng.SD2A.DAOs.InstrumentDAO;

import com.dkit.LeeXuanOng.SD2A.DAOExceptions.DAOException;
import com.dkit.LeeXuanOng.SD2A.DTOs.Instrument;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public interface InstrumentDAOInterface {

    public List<Instrument> findAllInstruments() throws DAOException;

    public Instrument findInstrumentByInstrumentId(int instrumentId) throws DAOException;

    public List<Instrument> findInstrumentsUsingFilter(Comparator<Instrument> comparator) throws DAOException;

    public boolean deleteInstrument(int instrumentId) throws DAOException;

    public int addInstrument(Instrument instrument) throws DAOException;

    public Set<Integer> getAllIds() throws DAOException;
}
