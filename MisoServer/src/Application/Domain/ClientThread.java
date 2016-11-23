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
         System.out.println(clientSocket.getLocalAddress() + " - 클라이언트 연결");
         o = new ObjectOutputStream(clientSocket.getOutputStream());
         i = new ObjectInputStream(clientSocket.getInputStream());
         
         User tempUser = new User();
         //Reservation tempReservation = new Reservation();
         //User p = new User();
        

         while(true){
            read = i.readObject();
            
            // 로그인 화면에서 회원가입 버튼 누름
            if(read.equals(newRegister)) {
               System.out.println("회원가입");
               //o.writeObject(newRegister);
               //flag="회원가입";
               break; 
            }
            
            // 로그인 화면에서 종료 버튼 누름
            else if(read.equals(exit)) {
               System.out.println(exit);
               break;
            }
            
            // 메인 화면에서 예약조회 버튼 누름
            else if(read.toString().substring(0, 1).equals(reserve_check)) {
            	System.out.println(clientSocket.getLocalAddress() + " - " + read.toString().substring(1) + " 회원님 회원 조회");
            	ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
            	reservationList = DB.reserveCheck(read.toString().substring(1));
            	
            	if(reservationList.get(0).getType().equals("success")) {
            		System.out.println(clientSocket.getLocalAddress() + " - " + read.toString().substring(1) + " 회원님 예약 정보 있음");
            		System.out.println(reservationList.get(0).getCost());
            		
            	}
            	else {
            		System.out.println(clientSocket.getLocalAddress() + " - " + read.toString().substring(1) + " 회원님 예약 정보 없음");
            	}
         
            	if(reservationList != null) {
            		o.writeObject(reservationList);
            	}
            	break;
            }
            
            if(read.getClass().toString().equals(tempUser.getClass().toString())) {
            	tempUser = (User)read;
            	// 로그인 버튼 or 회원 가입창
                if(tempUser.getType().equals("login") || tempUser.getType().equals("signUp")) {
                	//tempUser = (User)read;
                	
                	// 로그인 버튼
                	if(tempUser.getType().equals("login")) {
                		String Check = DB.login(tempUser.getId(),tempUser.getPwd());
                		
                		// 로그인 승인
                		if(Check.equals("승인")){
                            System.out.println(clientSocket.getLocalAddress() + " - 로그인 성공!!");
                            System.out.println(clientSocket.getLocalAddress() + " - id: " + tempUser.getId() + ", pwd: " + tempUser.getPwd() + " 로그인 요청");
                            o.writeObject(success);
                            break;
                         }
                         
                		// 로그인 실패 - 아이디 or 비번 미스매치
                         else if(Check.equals("비승인")){
                            o.writeObject(miss);
                            System.out.println(clientSocket.getLocalAddress() + " - id,pwd miss match");
                         }
                	}
                	
                	// 회원가입 창
                	else if(tempUser.getType().equals("signUp")) {
                		
                		// 회원가입 버튼을 누르면
                		if (tempUser.isRegister()) {
                			User newUser = new User();
                            newUser = tempUser;        
                            	
                            // 회원가입 성공
                            if( DB.SignUp(newUser.getId(),newUser.getPwd(),newUser.getName(),newUser.getPhone())){
                            	System.out.println("회원가입승인완료");
                            	System.out.println(clientSocket.getLocalAddress() + " - 회원가입(DB 저장)!!");
                            	o.writeObject(newRegisterSuccess);
                            	break;
                            }
                            
                            // 회원가입 실패 - 아이디(PK) 중복
                            else {
                            	o.writeObject(newRegisterFail);
                            	System.out.println("아이디중복");
                            }
                	
                		}
                		
                		// 회원가입 창에서 취소 버튼을 누르면
                		else {
                			System.out.println(clientSocket.getLocalAddress() + " - 회원가입 창에서 취소");
                			break;
            			}
                	}
                }
            }
            else {
            	
            	// 예약 결과 저장 - reserve 객체에
            	Reservation reserve = (Reservation)read;
            	DB.registerReserve(reserve.getId(), reserve.getCovers(), reserve.getCost(), reserve.getDate_in(), reserve.getDate_out(), reserve.getLocation());
            	System.out.println(clientSocket.getLocalAddress() + " - 예약정보 저장!!");
             	System.out.println("ID : " + reserve.getId() + " 인원 : " + reserve.getCovers() + "명 요금 : " + reserve.getCost() + "원 등등...");
             	break;
            }
            o.flush();
         }
         
         System.out.println(clientSocket.getLocalAddress() + " - 클라이언트 종료");
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