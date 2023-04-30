package com.dkit.LeeXuanOng.SD2A.client;

/** CLIENT                                                  March 2021
 *
 * This Client program asks the user to input commands to be sent to the server.
 *
 * There are only two valid commands in the protocol: "Time" and "Echo"
 *
 * If user types "Time" the server should reply with the current server time.
 *
 * If the user types "Echo" followed by a message, the server will echo back the message.
 * e.g. "Echo Nice to meet you"
 *
 * If the user enters any other input, the server will not understand, and
 * will send back a message to the effect.
 *
 * NOte: You must run the server before running this the client.
 * (Both the server and the client will be running together on this computer)
 */


import com.dkit.LeeXuanOng.SD2A.DTOs.Instrument;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        Client client = new Client();
        client.start();
    }

    public static void clientMenu(){
        System.out.println("Welcome to the Instrument Management System");
        System.out.println("Please enter a command according to the menu below by entering the number.");
        System.out.println("1. Find Instrument By ID");
        System.out.println("2. Find all instruments ");
        System.out.println("3. Add Instrument");
        System.out.println("4. Delete Instrument");
    }


    public void start()
    {
        Scanner in = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 8080);  // connect to server socket
            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server :" + socket.getPort() );

            System.out.println("Client message: The Client is running and has connected to the server");
//            String command = in.nextLine();
            Gson gson = new Gson();
            String command = "";
            int choice = 0;
            OutputStream os = socket. getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers
                clientMenu();
                choice = in.nextInt();
                System.out.println("You have chosen " + choice);
                switch(choice){
                    case 1:
                        System.out.println("Please enter the ID of the instrument you want to find");
                        command = "findInstrumentByInstrumentIdJson%" + in.nextInt();
                        break;
                    case 2:
                        command = "findAllInstrumentsJson";
                        break;
                    case 3:
                        System.out.println("Please enter the name of the instrument you want to add");
                        String name = in.next();
                        System.out.println("Please enter the description of the instrument you want to add");
                        String desc = in.next();
                        System.out.println("Please enter the category of the instrument you want to add");
                        String category = in.next();
                        System.out.println("Please enter the cost of the instrument you want to add");
                        double cost = in.nextDouble();
                        System.out.println("Please enter the stock of the instrument you want to add");
                        int stock = in.nextInt();
                        Instrument instrument = new Instrument( name, desc,stock, cost,category);
                        String json = gson.toJson(instrument);
                        command = "addInstrument%" + json;
                        break;
                    case 4:
                        System.out.println("Please enter the ID of the instrument you want to delete");
                        command = "deleteInstrument%" + in.nextInt();
                        break;
                    case 5:
                        System.out.println("Thank you for using the Instrument Management System");
                        break;
                    default:
                        System.out.println("Please enter a valid number");
                        break;
                }

            socketWriter.println(command);

            Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply
            String reply = socketReader.nextLine();
            System.out.println("Client message: Reply from Server -> ");

            String[] token = reply.split("%");
            if(Objects.equals(token[0], "ALLINSTRUMENTSJSON")){
                System.out.println("All instruments");
                Instrument[] instruments = gson.fromJson(token[1], Instrument[].class);
                for(Instrument i : instruments){
                    System.out.println(i.toString());
                }
            } else if(Objects.equals(token[0], "INSTRUMENTJSON")){
                Instrument instrument = gson.fromJson(token[1], Instrument.class);
                System.out.println(instrument.toString());
            } else if(Objects.equals(token[0], "INSTRUMENTADDED")){
                Instrument instrument = gson.fromJson(token[1], Instrument.class);
                System.out.println("Added instrument: " + instrument.toString());
            } else if(Objects.equals(token[0], "INSTRUMENTDELETED")){
                System.out.println("Deleted instrument with ID: " + token[1]);
            } else {
                System.out.println(reply);
            }
            socketWriter.close();
            socketReader.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Client message: IOException: "+e);
        }
    }
}


//  LocalTime time = LocalTime.parse(timeString); // Parse timeString -> convert to LocalTime object if required