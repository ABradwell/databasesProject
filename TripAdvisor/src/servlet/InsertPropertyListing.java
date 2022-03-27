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

@WebServlet("/insertProperty")
public class InsertPropertyListing extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String htmlresponse = "";
		Connection db = null;
	    ResultSet rs = null;
	    ResultSet rs3 = null;
	    Statement st = null;
	    Statement st2 = null;
	    Statement st3 = null;
	    Statement st4 = null;
	    String sql;
			try{
				Class.forName("org.postgresql.Driver");
				db = DriverManager.getConnection("jdbc:postgresql://web0.site.uottawa.ca:15432/abrad060", "abrad060", "3nterPlayer0ne");
				st = db.createStatement();
				st4 = db.createStatement();
		        int host_id = 0;
		        int bedrooms =  Integer.parseInt(request.getParameter("bedrooms"));
		        int bathrooms =  Integer.parseInt(request.getParameter("bathrooms"));
		        int house_num =  Integer.parseInt(request.getParameter("houseNum"));
		        String street = request.getParameter("streetName");
		        String city = request.getParameter("city");
		        String province = request.getParameter("province");
		        String country = request.getParameter("country");
		        String propertyType = request.getParameter("propType");
		        String room_type = request.getParameter("roomType");
		        String username = request.getParameter("username");
		        String amenities = request.getParameter("amenities");
	        	try {
	        		sql = "select count (*) as size from finalproject.Properties";
	    	    	System.out.print(sql + "\n");
	    	        rs = st.executeQuery(sql);
	    	        rs.next();
	    	        int size = rs.getInt("size");
	    	        
	    	        try {
		    	        sql = "select person_id from finalproject.login_credentials where username = '" + username + "'";
		    	    	System.out.print(sql + "\n");
		    	        rs3 = st4.executeQuery(sql);
		    	        rs3.next();
		    	        host_id = rs3.getInt("person_id");
	    	        }catch(Exception e) {
	    	        	System.out.println("Error using the provided username... ");
	    	        	e.printStackTrace();
	    	        }
	    	        try {
			        	st2 = db.createStatement();
			        	sql = "insert into finalproject.addresses values( "+  house_num +", '"+ street +"','"+ 
			        	city +"','"+ province +"','" + country + "')";
			        	System.out.print(sql + "\n");
			        	st2.executeUpdate(sql);
	    	        }catch(Exception e) {
	    	        	System.out.println("Error inserting into addresses... ");
	    	        	e.printStackTrace();
	    	        }
	    	        try {
			        	st3 = db.createStatement();
			        	sql = "insert into finalproject.properties values( "+ (size+1) +", "+  host_id  +", "+ 7  
			        	+ ", '"+ propertyType +"','"+ amenities +"', "+ bathrooms +", "+ 
			        	bedrooms +", '"+ room_type +"', "+  house_num+", '"+ street +"','"+ 
			        	city +"','"+ province +"','"+ country +"')";
			        	System.out.print(sql + "\n");
			            st3.executeUpdate(sql);
	    	        }catch(Exception e) {
	    	        	System.out.println("Error adding into properties... ");
	    	        	e.printStackTrace();
	    	        }
		        } catch(SQLException e) {
		            e.printStackTrace();
		        }
		        db.close();
			}catch(Exception e) {
				htmlresponse = "NO RESULTS FOUND...";
				System.out.println(e);
			}finally {
				try{
					if (rs != null) { rs.close(); }
					if (st != null) { st.close(); }
					if (db != null) { db.close(); }
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
	    
		request.getRequestDispatcher("user.jsp").forward(request, response);
	}
}