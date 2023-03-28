package com.dkit.LeeXuanOng.SD2A.DAOs.InstrumentDAO;

import com.dkit.LeeXuanOng.SD2A.DAOException.DAOException;
import com.dkit.LeeXuanOng.SD2A.DTOs.Instrument;

import java.util.List;

public interface InstrumentDAOInterface {

    public List<Instrument> findAllInstruments() throws DAOException;

    public Instrument findInstrumentByInstrumentId(int instrumentId) throws DAOException;

    public boolean deleteInstrument(int instrumentId) throws DAOException;

    public boolean addInstrument(Instrument instrument) throws DAOException;
}
