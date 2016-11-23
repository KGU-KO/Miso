package Application.Domain;
import java.net.ServerSocket;
import java.net.Socket;

import DB.DBManager;


public class Server {
   final public static int DEFAULT_PORT = 8888;

   public static void main(String[] args) {
      
      ServerSocket serverSocket = null; //�������� ����
  
      try {
         serverSocket = new ServerSocket(DEFAULT_PORT);   //�������� ����
         System.out.println("���� ����(port: " + DEFAULT_PORT + ")");
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