package com.dkit.LeeXuanOng.SD2A.DAOs.InstrumentDAO;

import com.dkit.LeeXuanOng.SD2A.DAOException.DAOException;
import com.dkit.LeeXuanOng.SD2A.DAOs.DAO;
import com.dkit.LeeXuanOng.SD2A.DTOs.Instrument;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlInstrumentDAO extends DAO implements InstrumentDAOInterface {


    @Override
    public List<Instrument> findAllInstruments() throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Instrument> instrumentsList = new ArrayList<>();

        try {
            connection = this.getConnection();
            String query = "SELECT * FROM INSTRUMENT";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int instrumentId = rs.getInt("insId");
                String insName = rs.getString("insName");
                int insStock = rs.getInt("insStock");
                String instrumentDescription = rs.getString("insDesc");
                double insPrice = rs.getDouble("insPrice");
                String insCategory = rs.getString("insCategory");
                Instrument i = new Instrument(instrumentId, insName, insCategory, insStock, insPrice, insCategory);
                instrumentsList.add(i);
            }
        } catch (SQLException e) {
            throw new DAOException("findAllInstruments() " + e.getMessage());
        } finally {
            try{
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
                if(connection != null){
                    this.freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DAOException("findAllInstruments() " + e.getMessage());
            }

        }
        return instrumentsList;
    }
}
