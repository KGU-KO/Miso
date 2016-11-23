package DB;

import java.sql.*;
import java.util.ArrayList;

public class Connector{
      String driver = null;
      String connection = null;
      String user;
      String passwd;
      Connection conn;
      public Connector(String _driver, String _connection, String _user, String _passwd) throws SQLException{
         driver = _driver;
         connection = _connection;
         user = _user;
         passwd = _passwd;
         try{
            Class.forName(_driver);
            Connection conTemp;
         }catch(ClassNotFoundException e){
            System.out.println("error " + e);
         }
      }
      public Connection getConnection() throws SQLException {
         conn = (Connection) DriverManager.getConnection(this.connection, this.user, this.passwd);      
         return conn;
      }
      public void close() {
         try{
            conn.close();
         } catch (Exception e){
            System.out.println("error " + e);
         }
      }
   }