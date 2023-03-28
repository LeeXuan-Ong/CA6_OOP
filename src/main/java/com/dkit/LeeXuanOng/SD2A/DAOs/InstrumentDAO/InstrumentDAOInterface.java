package com.dkit.LeeXuanOng.SD2A.DAOs.InstrumentDAO;

import com.dkit.LeeXuanOng.SD2A.DAOException.DAOException;
import com.dkit.LeeXuanOng.SD2A.DTOs.Instrument;

import java.util.List;

public interface InstrumentDAOInterface {

    public List<Instrument> findAllInstruments() throws DAOException;

}
