package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connections.PostgreSqlConn;
import entities.Property;
import entities.Employee;

@WebServlet("/findSearch")
public class EmployeeSearch extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String isBooked = req.getParameter("isBooked"); // yes or no
		String guest_id = req.getParameter("guest_id"); 
		String host_id = req.getParameter("host_id"); 
		String house_num =req.getParameter("house_num"); 
		String street = req.getParameter("street"); 
		String city = req.getParameter("city"); 
		String province = req.getParameter("province"); 
		String country = req.getParameter("country"); 
		
		System.out.println(isBooked);
		System.out.println(guest_id);
		System.out.println(host_id);
		System.out.println(house_num);
		System.out.println(street);
		System.out.println(city);
		System.out.println(province);
		
		PostgreSqlConn con = new PostgreSqlConn();
		
			
		String htmlresponse = "";
		
		Statement st1 = null;
		Statement st2 = null;
		
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		
		Connection db = null;
		
		String searchsql = "select * from finalproject.properties where property_id in (select property_id from finalproject.properties)"; // void first parameter to assure the and system works
		if(isBooked != null && isBooked.contentEquals("yes")) {
			searchsql += " AND property_id in (select property_id from finalproject.books) ";
		}else if(isBooked != null && isBooked.contentEquals("no")) {
			searchsql += " AND property_id not in (select property_id from finalproject.books) ";
		}
		if( guest_id != null && guest_id != "") {
			searchsql += " AND property_id in (select property_id from finalproject.books where guest_id = ("+ guest_id +")";
		}
		if(host_id != null && host_id != "") {
			searchsql += " AND property_id in (select property_id from finalproject.books where guest_id = ("+ host_id +")";
		}
		if(house_num != null && house_num != "") {
			searchsql += " AND ad_house_number = " + house_num;
		}
		if(street != null && street != "") {
			searchsql += " AND ad_street = '" + street +"'";
		}
		if(city != null && city != "") {
			searchsql += " AND ad_city = '" + city +"'";
		}
		if(province != null && province != "") {
			searchsql += " AND ad_province = '" + province +"'";
		}
		if(country != null && country != "") {
			searchsql += " AND ad_country = '" + country + "'";
		}
		
		try {
			Class.forName("org.postgresql.Driver");
			db = DriverManager.getConnection("jdbc:postgresql://web0.site.uottawa.ca:15432/abrad060", "abrad060", "3nterPlayer0ne");
			st2 = db.createStatement();
			
	    	System.out.print(searchsql + "\n");
	    	rs2 = st2.executeQuery(searchsql);
	        while(rs2.next()) {
	        	htmlresponse += "<b>Posting </b>"+ rs2.getInt("property_id") +": \n<br>";
	        	htmlresponse += rs2.getInt("ad_house_number") + " " + rs2.getString("ad_street") + "," + rs2.getString("ad_city") + "," + rs2.getString("ad_province")+ "," + rs2.getString("ad_country") +"\n<br>";
	        	htmlresponse += "<b>Propery Type: </b>" + rs2.getString("property_type") + "\n<br>";
	        	htmlresponse += "<b>Propery Amenities: </b>" + rs2.getString("amenities") + "\n<br>";
	        	htmlresponse += "<b>Bathrooms: </b>" + rs2.getInt("bathrooms") + "\n<br>";
	        	htmlresponse += "<b>Bedrooms: </b>" + rs2.getInt("bedrooms") + "\n<br>";
	        	htmlresponse += "<b>Room Type: </b>" + rs2.getString("room_type") + "\n<br><br><hr>";
	        }
		}catch (Exception e) {
			System.out.println("ERROR RETREIVING NOT BOOKED PROPERTIES");
			htmlresponse = "No Matched Search Results";
			e.printStackTrace();
		}finally {
			
			try{
				if (rs1 != null) { rs1.close(); }
				if (rs2 != null) { rs2.close(); }
				if (db != null) { db.close(); }
				if (st1 != null) { st1.close(); }
				if (st2 != null) { st2.close(); }
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(htmlresponse);
		req.setAttribute("RESULTS", htmlresponse);
		req.getRequestDispatcher("Employee.jsp").forward(req,resp);
		return;	
	}
}