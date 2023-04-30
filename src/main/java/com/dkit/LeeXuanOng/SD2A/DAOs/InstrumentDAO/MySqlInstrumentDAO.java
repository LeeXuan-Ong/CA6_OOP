package com.dkit.LeeXuanOng.SD2A.DAOs.InstrumentDAO;

import com.dkit.LeeXuanOng.SD2A.DAOExceptions.DAOException;
import com.dkit.LeeXuanOng.SD2A.DAOs.DAO;
import com.dkit.LeeXuanOng.SD2A.DTOs.Instrument;
import com.dkit.LeeXuanOng.SD2A.FilterComparator.IFilter;
import com.google.gson.Gson;

import java.sql.*;
import java.util.*;

public class MySqlInstrumentDAO extends DAO implements InstrumentDAOInterface {



    public Set<Integer> getAllIds() throws DAOException {
        Set<Integer> ids = new HashSet<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = this.getConnection();
            String query = "SELECT insId FROM INSTRUMENTS";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                ids.add(rs.getInt("insId"));
            }
        } catch (SQLException e){
            throw new DAOException("getAllIds() " + e.getMessage());
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
                throw new DAOException("getAllIds() " + e.getMessage());
            }
        }
        return ids;
    }
    @Override
    public List<Instrument> findAllInstruments() throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Instrument> instrumentsList = new ArrayList<>();

        try {
            connection = this.getConnection();
            String query = "SELECT * FROM INSTRUMENTS";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int instrumentId = rs.getInt("insId");
                String insName = rs.getString("insName");
                int insStock = rs.getInt("insStocks");
                String instrumentDescription = rs.getString("insDesc");
                double insPrice = rs.getDouble("insPrice");
                String insCategory = rs.getString("insCategory");
                Instrument i = new Instrument(instrumentId, insName, instrumentDescription, insStock, insPrice, insCategory);
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

    @Override
    public Instrument findInstrumentByInstrumentId(int instrumentId) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Instrument i = null;

        try {
            connection = this.getConnection();
            String query = "SELECT * FROM INSTRUMENTS WHERE insId = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, instrumentId);
            rs = ps.executeQuery();
            if (rs.next()) {
                String insName = rs.getString("insName");
                int insStock = rs.getInt("insStocks");
                String instrumentDescription = rs.getString("insDesc");
                double insPrice = rs.getDouble("insPrice");
                String insCategory = rs.getString("insCategory");
                i = new Instrument(instrumentId, insName, instrumentDescription, insStock, insPrice, insCategory);
            }
        } catch (SQLException e) {
            throw new DAOException("findInstrumentByInstrumentId() " + e.getMessage());
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
                throw new DAOException("findInstrumentByInstrumentId() " + e.getMessage());
            }

        }
        return i;
    }

    @Override
    public List<Instrument> findInstrumentsUsingFilter(IFilter filter, Comparator<Instrument> comparator) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Instrument> instrumentsList = new ArrayList<>();

        try {
            connection = this.getConnection();
            String query = "SELECT * FROM INSTRUMENTS";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int instrumentId = rs.getInt("insId");
                String insName = rs.getString("insName");
                int insStock = rs.getInt("insStocks");
                String instrumentDescription = rs.getString("insDesc");
                double insPrice = rs.getDouble("insPrice");
                String insCategory = rs.getString("insCategory");
                Instrument i = new Instrument(instrumentId, insName, instrumentDescription, insStock, insPrice, insCategory);
                instrumentsList.add(i);
            }
        } catch (SQLException e) {
            throw new DAOException("findInstrumentsUsingFilter() " + e.getMessage());
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
                throw new DAOException("findInstrumentsUsingFilter() " + e.getMessage());
            }

        }

        for(Instrument i : instrumentsList){
            if(!filter.matches(i)){
                instrumentsList.remove(i);
            }
        }
        return instrumentsList;
    }


    @Override
    public String findAllInstrumentsJson() throws DAOException {
        List<Instrument> list = findAllInstruments();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @Override
    public String findInstrumentByInstrumentIdJson(int instrumentId) throws DAOException {
        Instrument i = findInstrumentByInstrumentId(instrumentId);
        Gson gson = new Gson();
        if(i != null){
            String json = gson.toJson(i);
            return json;
        } else {
            String json = gson.toJson("ErrorMessage : Instrument not found");
            return json;
        }
    }

    @Override
    public boolean deleteInstrument(int instrumentId) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        int result = 0;

        try {
            connection = this.getConnection();
            String query = "DELETE FROM INSTRUMENTS WHERE insId = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, instrumentId);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("deleteInstrument() " + e.getMessage());
        } finally {
            try{
                if(ps != null){
                    ps.close();
                }
                if(connection != null){
                    this.freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DAOException("deleteInstrument() " + e.getMessage());
            }

        }
        return (result == 1);
    }

    @Override
    public int addInstrument(Instrument instrument) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        int result;
        ResultSet rs = null;


        try{
            connection = this.getConnection();
            String query = "INSERT INTO INSTRUMENTS (insName, insStocks, insDesc, insPrice, insCategory) VALUES (?,?,?,?,?)";
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, instrument.getInsName());
            ps.setInt(2, instrument.getInsStock());
            ps.setString(3, instrument.getInsDesc());
            ps.setDouble(4, instrument.getInsPrice());
            ps.setString(5, instrument.getInsCategory());
            result = ps.executeUpdate();

            if(result == 1){
                System.out.println("Instrument added");
                //get the generated key

                rs = ps.getGeneratedKeys();
                if(rs.next()){
                    result =  rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("addInstrument() " + e.getMessage());
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
                throw new DAOException("addInstrument() " + e.getMessage());
            }

        }
       return result;
    }
}
