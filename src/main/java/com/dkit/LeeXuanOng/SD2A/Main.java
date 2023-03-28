package com.dkit.LeeXuanOng.SD2A;
import com.dkit.LeeXuanOng.SD2A.DAOException.DAOException;
import com.dkit.LeeXuanOng.SD2A.DAOs.DAO;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        DAO test = new DAO();
        try{
            test.getConnection();

        } catch (DAOException e){
            System.out.println(e);
        }
    }
}