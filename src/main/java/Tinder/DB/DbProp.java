package Tinder.DB;

import Tinder.HerokuEnv;

public class DbProp {
    public static final String conn = getConn();
    public static final String user = getUser();
    public static final String pass = getPass();


    private static String getConn(){
        return System.getenv("TYPE") != null && System.getenv("TYPE").equals("PROD") ? HerokuEnv.jdbc_url() : "jdbc:postgresql://localhost:5432/tinder";
    }

    private static String getUser(){
        return System.getenv("TYPE") != null && System.getenv("TYPE").equals("PROD") ? HerokuEnv.jdbc_username() : "root";
    }

    private static String getPass(){
        return System.getenv("TYPE") != null && System.getenv("TYPE").equals("PROD") ? HerokuEnv.jdbc_password() : "root";
    }

}

