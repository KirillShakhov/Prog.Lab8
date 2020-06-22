package lab;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        final String DB_URL;
        final String USER;
        final String PASS;
        if(true){
            DB_URL = "jdbc:postgresql://pg:5432/studs";
            USER = "s285896";
            PASS = "kts231";
        }
        else{
            DB_URL = "jdbc:postgresql://localhost:5555/postgres";
            USER = "postgres";
            PASS = "14102001";
        }
        new BD(DB_URL, USER, PASS);
        if (args.length > 0){
            ServerController serverController = new ServerController(args[0]);
            serverController.run();
        }
        else{
            ServerController serverController = new ServerController("3030");
            serverController.run();
        }
    }
}
