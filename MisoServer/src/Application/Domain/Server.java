package Application.Domain;
import java.net.ServerSocket;
import java.net.Socket;

import DB.DBManager;


public class Server {
   final public static int DEFAULT_PORT = 8888;

   public static void main(String[] args) {
      
      ServerSocket serverSocket = null; //辑滚家南 积己
  
      try {
         serverSocket = new ServerSocket(DEFAULT_PORT);   //辑滚家南 积己
         System.out.println("辑滚 积己(port: " + DEFAULT_PORT + ")");
         DBManager DB=new DBManager();
         DB.Connectivity();
         while(true) {
            
            Socket clientSocket = serverSocket.accept();
            ClientThread cThread = new ClientThread();
            cThread.setSocket(clientSocket);
            cThread.setDB(DB);
            cThread.start();
         }
            
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
 
}