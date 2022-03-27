package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/queryEight")
public class Query8 extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String htmlresponse = "";
		Connection db = null;
		
	    
	    Statement st1 = null;
	    Statement st2 = null;
	    Statement st3 = null;
	    Statement st4 = null;
	    Statement st5 = null;
	    Statement st6 = null;
	    
	    ResultSet rs1 = null;
    	ResultSet rs2 = null;
    	ResultSet rs3 = null;
    	ResultSet rs4 = null;
    	ResultSet rs5 = null;
    	ResultSet rs6 = null;
    	
	    int id =  Integer.parseInt(request.getParameter("id"));
	    
		try{
			
			Class.forName("org.postgresql.Driver");
			db = DriverManager.getConnection("jdbc:postgresql://web0.site.uottawa.ca:15432/abrad060", "abrad060", "3nterPlayer0ne");
			
			st1 = db.createStatement();
			st2 = db.createStatement();
			st3 = db.createStatement();
			st4 = db.createStatement();
			st5 = db.createStatement();
			st6 = db.createStatement();
			
			String sql1 = "Select property_type as proptype from finalproject.properties where property_id =( " + 
						" Select property_id from finalproject.books where guest_id = "+ id +") ";
			
			String sql2 = "	Select first_name as fname from finalproject.People where person_id = ("+ id +")";
			
			String sql3 = "Select last_name as lname from finalproject.People where person_id = ("+ id +")";
			
			String sql4 = "Select * from finalproject.properties where property_id = ( " + 
					"			Select property_id from finalproject.books where guest_id = "+ id +")";
			
			String sql5 = "Select payment_type as paytype from finalproject.payments where payment_id = ( " + 
					"	Select payment_id from finalproject.pays where guest_id = "+ id +")";
			
			String sql6 = "Select status as paystatus from finalproject.payments where payment_id = ( " + 
					"	Select payment_id from finalproject.pays where guest_id = "+ id +")";
			
			
	    	System.out.print(sql1 + "\n");
	    	System.out.print(sql2 + "\n");
	    	System.out.print(sql3 + "\n");
	    	System.out.print(sql4 + "\n");
	    	System.out.print(sql5 + "\n");
	    	System.out.print(sql6 + "\n");
	    	
	    	rs1 = st6.executeQuery(sql1);
	    	rs2 = st1.executeQuery(sql2);
	    	rs3 = st2.executeQuery(sql3);
	    	rs4 = st3.executeQuery(sql4);
	    	rs5 = st4.executeQuery(sql5);
	    	rs6 = st5.executeQuery(sql6);
	    	
        	rs1.next();
        	rs2.next();
        	rs3.next();
        	rs4.next();
        	rs5.next();
        	rs6.next();
        	
        	htmlresponse += "BILL: \n";
	        htmlresponse += "<b> First Name </b> " + rs2.getString("fname") + "<br>";
	        htmlresponse += "<b> Last Name </b>" + rs3.getString("lname") + "<br>";
	        htmlresponse += "<b> Property Type </b>" + rs1.getString("proptype") + "<br>";
	        htmlresponse += "<b> Property: </b>" + rs4.getInt("ad_house_number") + " " + rs4.getString("ad_street")  + ", " + rs4.getString("ad_city") + " " + rs4.getString("ad_country") + "<br>";
	        htmlresponse += "<b> Payment Type </b>"+ rs5.getString("paytype") + "<br>";
	        htmlresponse += "<b> Status </b>" + rs6.getString("paystatus") + "<br><br>";
	        
		}catch(Exception e) {
			htmlresponse = "NO RESULTS FOUND...";
			System.out.println(e);
		}finally {
			
			try{
				if (rs1 != null) { rs1.close(); }
				if (rs2 != null) { rs2.close(); }
				if (rs3 != null) { rs3.close(); }
				if (rs4 != null) { rs4.close(); }
				if (rs5 != null) { rs5.close(); }
				if (rs6 != null) { rs6.close(); }
				if (db != null) { db.close(); }
				if (st1 != null) { st1.close(); }
				if (st2 != null) { st2.close(); }
				if (st3 != null) { st3.close(); }
				if (st4 != null) { st4.close(); }
				if (st5 != null) { st5.close(); }
				if (st6 != null) { st6.close(); }
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    
	    
	    System.out.println(htmlresponse);
	    request.setAttribute("Q8", htmlresponse);
		request.getRequestDispatcher("login_success.jsp?username=Marker").forward(request, response);
	}
}