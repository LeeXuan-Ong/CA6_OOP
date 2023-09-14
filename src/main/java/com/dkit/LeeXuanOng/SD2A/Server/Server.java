package com.dkit.LeeXuanOng.SD2A.Server;
import com.dkit.LeeXuanOng.SD2A.DTOs.DeserializationInstrument;
import com.dkit.LeeXuanOng.SD2A.FilterComparator.*;
import com.dkit.LeeXuanOng.SD2A.DAOExceptions.DAOException;
import com.dkit.LeeXuanOng.SD2A.DAOs.InstrumentDAO.MySqlInstrumentDAO;
import com.dkit.LeeXuanOng.SD2A.DTOs.Instrument;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.*;


//having a switch statement listening to the client command
// is error becuaz of the switch cases does not match
public class Server
{
    public static void main(String[] args)
    {
        Server server = new Server();
        server.start();
    }

    public void start()
    {
        try {
            ServerSocket ss = new ServerSocket(8080);
            System.out.println("Server Message: Server started. Listening for connections...");
            int clientNumber = 0;
            while(true)
            {
                clientNumber++;
                Socket socket = ss.accept();  // wait for client to connect, and open a socket with the client
                System.out.println("Server: Client " + clientNumber + " has connected.");

                System.out.println("Server: Port# of remote client: " + socket.getPort());
                System.out.println("Server: Port# of this server: " + socket.getLocalPort());

                Thread thread = new Thread(new ClientHandler(socket, clientNumber));
                thread.start();
                System.out.println("Server: ClientHandler started in thread for client " + clientNumber + ". ");
                System.out.println("Server: Listening for further connections...");
            }
        } catch (IOException | SecurityException | IllegalArgumentException e) {
            System.out.println("Server Message: \nException: "+e);

        }
    }

    public static class ClientHandler implements Runnable{

        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;
        Set<Integer> cache;

        public ClientHandler(Socket clientSocket, int clientNumber)
        {
            try
            {
                MySqlInstrumentDAO m = new MySqlInstrumentDAO();
                cache = m.getAllIds();
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer

                this.clientNumber = clientNumber;  // ID number that we are assigning to this client

                this.socket = clientSocket;  // store socket ref for closing

            } catch (IOException ex)
            {
                ex.printStackTrace();
            } catch (DAOException ed)
            {
                System.out.println(ed);
            }
        }

        @Override
        public void run() {
            String message;
            MySqlInstrumentDAO m = new MySqlInstrumentDAO();
            try
            {
                while ((message = socketReader.readLine()) != null)
                {
                    System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);
                    String reply= "";
                    String[] temp = message.split("%");
                    System.out.println(Arrays.toString(temp));
                    if(Objects.equals(temp[0], "findAllInstruments")){
                        reply = "ALLINSTRUMENTS%"+m.findAllInstruments();
                    } else if(Objects.equals(temp[0], "findInstrumentByInstrumentId")){
                        try{
                            int id = Integer.parseInt(temp[1]);
                            if(cache.contains(id)){
                                reply = "INSTRUMENTBYID%"+m.findInstrumentByInstrumentId(id);
                            } else {
                                reply = "ERROR";
                            }
                        } catch (NumberFormatException e){
                            reply = "Please enter a valid number";
                        }
                    } else if(Objects.equals(temp[0], "findInstrumentsUsingFilter")){
                        Comparator<Instrument> c = null;
                        IFilter filter = null;
                        if(Objects.equals(temp[1], "ID")){
                            filter = new InstrumentIDFilter(Integer.parseInt(temp[2]));
                            c = new InstrumentsIDComparator();
                        } else if(Objects.equals(temp[1], "NAME")){
                            filter = new InstrumentNameFilter(temp[2]);
                            c = new InstrumentsNameComparator();
                        } else if(Objects.equals(temp[1], "PRICE")){
                            filter = new InstrumentPriceFilter(Double.parseDouble(temp[2]), Double.parseDouble(temp[3]));
                            c = new InstrumentsPriceComparator();
                        } else {
                            reply = "ERROR";
                        }
                        if(c != null && filter != null){
                            reply = "INSTRUMENTSBYFILTER%"+m.findInstrumentsUsingFilter(filter,c);
                        }
                    } else if(temp[0].startsWith("deleteInstrument")){
                        try{
                            int id = Integer.parseInt(temp[1]);
                            System.out.println(id);
                            if(cache.contains(id)){
                                if(m.deleteInstrument(id)){
                                    reply = "INSTRUMENTDELETED%"+id;
                                } else {
                                    reply = "ERROR";
                                }
                            }
                        } catch (NumberFormatException e){
                            reply = "Please enter a valid number";
                        }
                    } else if(temp[0].equals("addInstrument")){

                        Gson gson = new GsonBuilder().registerTypeAdapter(Instrument.class, new DeserializationInstrument()).create();
                        Instrument i = gson.fromJson(temp[1], Instrument.class);
                        int id = m.addInstrument(i);
                        cache.add(id);
                        i.setId(id);
                        String json = gson.toJson(i);
                        reply = "INSTRUMENTADDED%" + json;
                    } else if(temp[0].equals("getAllIds")){
                        reply = "ALLIDS%"+m.getAllIds();
                    } else if(temp[0].equals("findAllInstrumentsJson")){
                        reply = "ALLINSTRUMENTSJSON%"+m.findAllInstrumentsJson();
                    } else if(temp[0].equals("findInstrumentByInstrumentIdJson")){
                        try{
                            if(cache.contains(Integer.parseInt(temp[1]))){
                                reply = "INSTRUMENTBYIDJSON%"+m.findInstrumentByInstrumentIdJson(Integer.parseInt(temp[1]));
                            } else {
                                reply = "Not Found";
                            }
                        } catch (NumberFormatException e){
                            reply = "Please enter a valid number";
                        }
                    } else {
                        reply = "ERROR";
                    }
                    socketWriter.println(reply);
                }

                socket.close();

            } catch (IOException ex)
            {
                ex.printStackTrace();
            } catch (DAOException ed)
            {
                System.out.println(ed);
            }
            System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
        }
    }

}
