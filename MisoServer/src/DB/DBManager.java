package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Application.Domain.Reservation;


public class DBManager {
   private static String driver = "org.mariadb.jdbc.Driver";
   private static String url="jdbc:mariadb://localhost:3307/miso";
   private static String id = "root";
   private static String pwd = "kgu";
   private static Connection conn = null;
   private Statement stmt = null;
   private ResultSet rs = null;
   private static Connector Connector;

   
   public static void Connectivity(){ // Connector 만들어서 DB 연결 
      try {
         Connector = new Connector(driver,url,id,pwd);
         conn = Connector.getConnection();
         System.out.println("DB Connection!");
      } catch (SQLException e) {
         System.out.println("DB Connection Error"+e);
         e.printStackTrace();
         Connector=null;
      }
   }
   
   public void registerReserve(String id, int covers, int cost, String date_in, String date_out, String location) {
	   stmt=null;
	   try {
		   stmt=conn.createStatement();
		   String sql = "insert into reservation values('"
				   +id+"','"
				   +covers+"','"
		           +cost+"','"
		           +date_in+"','"
		           +date_out+"','"
		           +location+"');"; 
		   stmt.executeUpdate(sql);
	       stmt.close();
	   } catch(Exception e){
	       e.printStackTrace();
	   }
   }
   
   public ArrayList<Reservation> reserveCheck(String id) {
	   ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
	   stmt=null;
	   try {
		   stmt=conn.createStatement();
		   rs=stmt.executeQuery("select * from reservation where id ='"+id+"';");
		   int i=0;
		   while(rs.next()) {
			   Reservation reserve = new Reservation();
			   reserve.setId(rs.getString(1));
			   reserve.setCovers(rs.getInt(2));
			   reserve.setCost(rs.getInt(3));
			   reserve.setDate_in(rs.getString(4));
			   reserve.setDate_out(rs.getString(5));
			   reserve.setLocation(rs.getString(6));
			   reservationList.add(reserve);
			   i++;
			   /*
			   reservationList.get(i).setId(rs.getString(1));
			   reservationList.get(i).setCovers(rs.getInt(2));
			   reservationList.get(i).setCost(rs.getInt(3));
			   reservationList.get(i).setDate_in(rs.getString(4));
			   reservationList.get(i).setDate_out(rs.getString(5));
			   reservationList.get(i).setLocation(rs.getString(6));
			   */
		   }
		   if(i == 0) {
			  Reservation reserve = new Reservation();
			  reserve.setType("fail");
			  reservationList.add(reserve);
			  return reservationList;
		   }
		   else {
			   reservationList.get(0).setType("success");
			   return reservationList;
		   }
		   
	   } catch(Exception e){
	       e.printStackTrace();
	       return null;
	   }
   }
   
   
   public boolean SignUp(String id, String pwd,String name,String phoneNum){   // 회원가깁 DB연동 부분.
      stmt=null;
      
      try {
         stmt=conn.createStatement();
         rs=stmt.executeQuery("select * from userinfo where id ='"+id+"';");
      if(rs.next()){
           if(rs.getString(0).equals(id)){
              System.out.println("아이디중복");
              return false;
           }
        }
      String sql= "insert into userinfo values('"
            +id+"','"
            +pwd+"','"
            +name+"','"
            +phoneNum+"');"; 
         stmt.executeUpdate(sql);
         stmt.close();
         return true;
      }catch(Exception e){
         return false;
      }
   }
   

public String login(String id, String pwd){  //로그인 DB연동 부분
   stmt=null;
   String sql ="select pwd from userinfo where id ='"+id+"';";
   try {
      stmt=conn.createStatement();
      rs=stmt.executeQuery(sql);
      if(rs.next()){
         if(rs.getString(1).equals(pwd)){
            rs.close();
            stmt.close();
            return "승인";
         }
      }
   } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
   }
   try {
      rs.close();
      stmt.close();
      } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
   }
   return "비승인";
}
}