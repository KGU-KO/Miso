package Application.Domain;



import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import DB.DBManager;



public class ClientThread extends Thread {
   private Socket clientSocket;
   DBManager DB ;
   ObjectOutputStream o = null;
   ObjectInputStream i = null;

   int exit = 0;
   String newRegisterFail = "%";
   String newRegisterSuccess = "^";
   String miss = "#";
   String success = "$";
   String newRegister = "@";
   String flag="login";
   String reserve_check = "(";
   Object read = null; 


   @Override
   public void run() {
      super.run();

      try {
         System.out.println(clientSocket.getLocalAddress() + " - Ŭ���̾�Ʈ ����");
         o = new ObjectOutputStream(clientSocket.getOutputStream());
         i = new ObjectInputStream(clientSocket.getInputStream());
         
         User tempUser = new User();
         //Reservation tempReservation = new Reservation();
         //User p = new User();
        

         while(true){
            read = i.readObject();
            
            // �α��� ȭ�鿡�� ȸ������ ��ư ����
            if(read.equals(newRegister)) {
               System.out.println("ȸ������");
               //o.writeObject(newRegister);
               //flag="ȸ������";
               break; 
            }
            
            // �α��� ȭ�鿡�� ���� ��ư ����
            else if(read.equals(exit)) {
               System.out.println(exit);
               break;
            }
            
            // ���� ȭ�鿡�� ������ȸ ��ư ����
            else if(read.toString().substring(0, 1).equals(reserve_check)) {
            	System.out.println(clientSocket.getLocalAddress() + " - " + read.toString().substring(1) + " ȸ���� ȸ�� ��ȸ");
            	ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
            	reservationList = DB.reserveCheck(read.toString().substring(1));
            	
            	if(reservationList.get(0).getType().equals("success")) {
            		System.out.println(clientSocket.getLocalAddress() + " - " + read.toString().substring(1) + " ȸ���� ���� ���� ����");
            		System.out.println(reservationList.get(0).getCost());
            		
            	}
            	else {
            		System.out.println(clientSocket.getLocalAddress() + " - " + read.toString().substring(1) + " ȸ���� ���� ���� ����");
            	}
         
            	if(reservationList != null) {
            		o.writeObject(reservationList);
            	}
            	break;
            }
            
            if(read.getClass().toString().equals(tempUser.getClass().toString())) {
            	tempUser = (User)read;
            	// �α��� ��ư or ȸ�� ����â
                if(tempUser.getType().equals("login") || tempUser.getType().equals("signUp")) {
                	//tempUser = (User)read;
                	
                	// �α��� ��ư
                	if(tempUser.getType().equals("login")) {
                		String Check = DB.login(tempUser.getId(),tempUser.getPwd());
                		
                		// �α��� ����
                		if(Check.equals("����")){
                            System.out.println(clientSocket.getLocalAddress() + " - �α��� ����!!");
                            System.out.println(clientSocket.getLocalAddress() + " - id: " + tempUser.getId() + ", pwd: " + tempUser.getPwd() + " �α��� ��û");
                            o.writeObject(success);
                            break;
                         }
                         
                		// �α��� ���� - ���̵� or ��� �̽���ġ
                         else if(Check.equals("�����")){
                            o.writeObject(miss);
                            System.out.println(clientSocket.getLocalAddress() + " - id,pwd miss match");
                         }
                	}
                	
                	// ȸ������ â
                	else if(tempUser.getType().equals("signUp")) {
                		
                		// ȸ������ ��ư�� ������
                		if (tempUser.isRegister()) {
                			User newUser = new User();
                            newUser = tempUser;        
                            	
                            // ȸ������ ����
                            if( DB.SignUp(newUser.getId(),newUser.getPwd(),newUser.getName(),newUser.getPhone())){
                            	System.out.println("ȸ�����Խ��οϷ�");
                            	System.out.println(clientSocket.getLocalAddress() + " - ȸ������(DB ����)!!");
                            	o.writeObject(newRegisterSuccess);
                            	break;
                            }
                            
                            // ȸ������ ���� - ���̵�(PK) �ߺ�
                            else {
                            	o.writeObject(newRegisterFail);
                            	System.out.println("���̵��ߺ�");
                            }
                	
                		}
                		
                		// ȸ������ â���� ��� ��ư�� ������
                		else {
                			System.out.println(clientSocket.getLocalAddress() + " - ȸ������ â���� ���");
                			break;
            			}
                	}
                }
            }
            else {
            	
            	// ���� ��� ���� - reserve ��ü��
            	Reservation reserve = (Reservation)read;
            	DB.registerReserve(reserve.getId(), reserve.getCovers(), reserve.getCost(), reserve.getDate_in(), reserve.getDate_out(), reserve.getLocation());
            	System.out.println(clientSocket.getLocalAddress() + " - �������� ����!!");
             	System.out.println("ID : " + reserve.getId() + " �ο� : " + reserve.getCovers() + "�� ��� : " + reserve.getCost() + "�� ���...");
             	break;
            }
            o.flush();
         }
         
         System.out.println(clientSocket.getLocalAddress() + " - Ŭ���̾�Ʈ ����");
         o.close();
         i.close();
         clientSocket.close();
      
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   public void setSocket(Socket _socket) {
      clientSocket = _socket;
   }
   public void setDB(DBManager _DB) {
        DB = _DB;
   }
}