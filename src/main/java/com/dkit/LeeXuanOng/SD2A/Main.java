package com.dkit.LeeXuanOng.SD2A;

import com.dkit.LeeXuanOng.SD2A.FilterComparator.*;
import com.dkit.LeeXuanOng.SD2A.DAOExceptions.DAOException;
import com.dkit.LeeXuanOng.SD2A.DAOs.InstrumentDAO.MySqlInstrumentDAO;
import com.dkit.LeeXuanOng.SD2A.DTOs.Instrument;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try{
            while(true){
                System.out.println("1. findAllInstruments \n2. findInstrumentByInstrumentId \n3. addInstrument \n4. deleteInstrument \n5. findInstrumentsUsingFilter \n6. Instrument JSON\n7. Instrument ID\n0. Exit");
                System.out.println("Please enter your choice: ");
                int input = sc.nextInt();
                if(input == 0){
                    break;
                }
                menu(input);
            }
        } catch (InputMismatchException e){
            System.out.println("Invalid input");
        }
    }

    public static void menu(int input){
        MySqlInstrumentDAO m = new MySqlInstrumentDAO();
        Scanner sc = new Scanner(System.in);
        Set<Integer> ids;
        try {
            ids = m.getAllIds();
            switch (input){
                case 1:
                    System.out.println(m.findAllInstruments());
                    break;
                case 2:
                    int input2 = sc.nextInt();
                    if(ids.contains(input2)){
                        System.out.println(m.findInstrumentByInstrumentId(input2));
                    }
                    else
                        System.out.println("Invalid ID");
                    break;
                case 3:
                    String name = sc.nextLine();
                    String desc = sc.nextLine();
                    int stock = sc.nextInt();
                    double price = sc.nextInt();
                    String category = sc.nextLine();
                    System.out.println(m.addInstrument(new Instrument(name, desc,  stock, price, category)));
                    break;
                case 4:
                    int input3 = sc.nextInt();
                    System.out.println(m.deleteInstrument(input3));
                    break;
                case 5:
                    System.out.println("1. Name\n2. Price\n");
                    int input4 = sc.nextInt();
                    if(input4 == 1){
                        List<Instrument> list = m.findInstrumentsUsingFilter(new InstrumentNameFilter("Guitar"),new InstrumentsNameComparator());
                        for (Instrument i : list) {
                            System.out.println(i);
                        }
                    }
                    else if(input4 == 2){
                        List<Instrument> list = m.findInstrumentsUsingFilter(new InstrumentPriceFilter(90, 200),new InstrumentsPriceComparator());
                        for (Instrument i : list) {
                            System.out.println(i);
                        }
                    }
                    else
                        System.out.println("Invalid input");
//
                    break;
                case 6:
                    System.out.println(m.findAllInstrumentsJson());
                    break;
                case 7:
                    int input5 = sc.nextInt();
                    System.out.println(m.findInstrumentByInstrumentIdJson(input5));
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }


}