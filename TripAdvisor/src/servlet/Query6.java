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

@WebServlet("/querySix")
public class Query6 extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String htmlresponse = "";
		Connection db = null;
	    Statement st = null;
	    ResultSet rs = null;
	    String sql;
	    String ishidden = request.getParameter("isShown");
	    if(ishidden.equals("hidden")) {
	    	htmlresponse = "";
	    } else {
			try{
				Class.forName("org.postgresql.Driver");
				db = DriverManager.getConnection("jdbc:postgresql://web0.site.uottawa.ca:15432/abrad060", "abrad060", "3nterPlayer0ne");
				st = db.createStatement();
				
				sql = "select * " + 
						"from finalproject.properties " + 
						"where properties.property_id in (select property_id from finalproject.rental_agreements where EXTRACT(day from start_date) = 10)" + 
						"order by properties.property_id ";
				
		    	System.out.print(sql + "\n");
		    	rs = st.executeQuery(sql);
		    	while(rs.next()) {
		        	htmlresponse += "<b>Posting "+ rs.getInt("property_id") +": \n</b><br>";
			        htmlresponse += rs.getInt("ad_house_number") + " " + rs.getString("ad_street") + "," + rs.getString("ad_city") + "," + rs.getString("ad_province")+ "," + rs.getString("ad_country") +"\n<br>";
			        htmlresponse += "<b>Propery Type: </b>" + rs.getString("property_type") + "\n<br>";
			        htmlresponse += "<b>Propery Amenities: </b>" + rs.getString("amenities") + "\n<br>";
			        htmlresponse += "<b>Bathrooms: </b>" + rs.getInt("bathrooms") + "\n<br>";
			        htmlresponse += "<b>Bedrooms: </b>" + rs.getInt("bedrooms") + "\n<br>";
			        htmlresponse += "<b>Room Type: </b>" + rs.getString("room_type") + "\n<br><br>";
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
	    }
	    
	    System.out.println(htmlresponse);
	    request.setAttribute("Q6", htmlresponse);
		request.getRequestDispatcher("login_success.jsp?username=Marker").forward(request, response);
	}
}