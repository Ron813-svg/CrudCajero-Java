package modelo;

import java.sql.*;

public class ClaseConexion {
    private static final String URL = "jdbc:oracle:thin:@192.168.0.7:1521:xe";
    private static final String USER = "system";
    private static final String PASSWORD = "vKicgUbn";
    
    
    public static Connection getConexion(){
     try{
           Class.forName("oracle.jdbc.driver.OracleDriver");
           
           Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
          
           return conexion;
     }catch (SQLException e){
         System.out.println("Este es el error" + e);
          return null;
     
     }catch (ClassNotFoundException ex) {
            System.out.println("este es el error de la clase" + ex);
              return null;
      }
   }
}

