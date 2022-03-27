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

@WebServlet("/loginUser")
public class LoginUser extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userUsername = req.getParameter("uname");
		String userPwd = req.getParameter("upass");
		PostgreSqlConn con = new PostgreSqlConn();
		String pwdfromdb = con.getpwdbyUname(userUsername);
		
		String sql1 = "";
		String sql2 = "";
		
		String htmlresponse1 = "";
		String htmlresponse2 = "";
		
		Statement st1 = null;
		Statement st2 = null;
		
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		
		Connection db = null;
		
		System.out.println(userPwd);
		System.out.println(pwdfromdb);
		if (userPwd.equals(pwdfromdb)) {
			try {
				Class.forName("org.postgresql.Driver");
				db = DriverManager.getConnection("jdbc:postgresql://web0.site.uottawa.ca:15432/abrad060", "abrad060", "3nterPlayer0ne");
				st1 = db.createStatement();
				
				sql1 = "SELECT * " + 
						" FROM finalproject.properties  " +  
						" WHERE property_id IN (SELECT property_id FROM finalproject.books) ";
				
		    	System.out.print(sql1 + "\n");
		    	rs1 = st1.executeQuery(sql1);
		    	while(rs1.next()) {
		    		htmlresponse1 += "<b>Posting </b>"+ rs1.getInt("property_id") +": \n<br>";
		    		htmlresponse1 += rs1.getInt("ad_house_number") + " " + rs1.getString("ad_street") + "," + rs1.getString("ad_city") + "," + rs1.getString("ad_province")+ "," + rs1.getString("ad_country") +"\n<br>";
		    		htmlresponse1 += "<b>Propery Type: </b>" + rs1.getString("property_type") + "\n<br>";
		    		htmlresponse1 += "<b>Rating: </b>" + rs1.getInt("rating") + "\n<br>";
		    		htmlresponse1 += "<b>Propery Amenities: </b>" + rs1.getString("amenities") + "\n<br>";
			        htmlresponse1 += "<b>Bathrooms: </b>" + rs1.getInt("bathrooms") + "\n<br>";
			        htmlresponse1 += "<b>Bedrooms: </b>" + rs1.getInt("bedrooms") + "\n<br>";
			        htmlresponse1 += "<b>Rating: </b>" + rs1.getString("rating") + "\n<br>";
			        htmlresponse1 += "<b>Room Type: </b>" + rs1.getString("room_type") + "\n<br><br><hr>";
		        }
			}catch (Exception e) {
				System.out.println("ERROR RETREIVING BOOKED PROPERTIES");
				htmlresponse1 = "No Currently booked properties";
				e.printStackTrace();
			}
			
			try {
				Class.forName("org.postgresql.Driver");
				db = DriverManager.getConnection("jdbc:postgresql://web0.site.uottawa.ca:15432/abrad060", "abrad060", "3nterPlayer0ne");
				st2 = db.createStatement();
				
				sql2 = "SELECT * " + 
						"FROM finalproject.properties " + 
						"WHERE property_id NOT IN (SELECT property_id FROM finalproject.books) ";
				
		    	System.out.print(sql2 + "\n");
		    	rs2 = st2.executeQuery(sql2);
		        while(rs2.next()) {
		        	htmlresponse2 += "<b>Posting </b>"+ rs2.getInt("property_id") +": \n<br>";
			        htmlresponse2 += rs2.getInt("ad_house_number") + " " + rs2.getString("ad_street") + "," + rs2.getString("ad_city") + "," + rs2.getString("ad_province")+ "," + rs2.getString("ad_country") +"\n<br>";
			        htmlresponse2 += "<b>Propery Type: </b>" + rs2.getString("property_type") + "\n<br>";
			        htmlresponse2 += "<b>Propery Amenities: </b>" + rs2.getString("amenities") + "\n<br>";
			        htmlresponse2 += "<b>Bathrooms: </b>" + rs2.getInt("bathrooms") + "\n<br>";
			        htmlresponse2 += "<b>Bedrooms: </b>" + rs2.getInt("bedrooms") + "\n<br>";
			        htmlresponse2 += "<b>Room Type: </b>" + rs2.getString("room_type") + "\n<br><br><hr>";
		        }
			}catch (Exception e) {
				System.out.println("ERROR RETREIVING NOT BOOKED PROPERTIES");
				htmlresponse2 = "No Currently unbooked properties";
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
			
			System.out.println(htmlresponse1);
			System.out.println(htmlresponse2);
			req.setAttribute("BOOKED", htmlresponse1);
			req.setAttribute("NOTBOOKED", htmlresponse2);
			req.setAttribute("Username", pwdfromdb);
			req.getRequestDispatcher("user.jsp").forward(req,resp);
			return;	
		}
		resp.sendRedirect("login_failure.jsp");
		return;
	}
}