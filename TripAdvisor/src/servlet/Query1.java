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

@WebServlet("/queryOne")
public class Query1 extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String htmlresponse = "";
		Connection db = null;
	    ResultSet rs = null;
	    Statement st = null;
	    String sql;
	    String ishidden = request.getParameter("isShown");
	    if(ishidden.equals("hidden")) {
	    	htmlresponse = "";
	    }
	    else {
			try{
				Class.forName("org.postgresql.Driver");
				db = DriverManager.getConnection("jdbc:postgresql://web0.site.uottawa.ca:15432/abrad060", "abrad060", "3nterPlayer0ne");
				st = db.createStatement();
				
				sql = "select People.first_name,  " + 
						"		People.last_name,  " + 
						"		rental_agreements.rental_type,  " + 
						"		rental_agreements.rental_price,  " + 
						"		rental_agreements.signing_date,  " + 
						"		People.branch_id, " + 
						"		People.branch_country, " + 
						" 		payments.payment_type, " + 
						" 		payments.status " + 
						" FROM finalproject.People, finalproject.signs, finalproject.rental_agreements,  finalproject.payments " + 
						" WHERE signs.guest_id = People.person_id " + 
						"	AND signs.rental_id = rental_agreements.rental_id " + 
						"	AND rental_agreements.rental_id = payments.rental_id " + 
						"	order by payments.payment_type desc ";
				
		        rs = st.executeQuery(sql);
		        
		       
		        int booking = 0;
		        while (rs.next()) {
		        	booking++;
		        	System.out.println("ADDING NEW BOOKING...");
		        	htmlresponse += "<b>Booking:</b> " + booking + "...\n<br>";
		        	htmlresponse += "      <b>Name:</b> " + rs.getString("first_name") + " " + rs.getString("last_name") + "<br>";
		        	htmlresponse += "      <b>Rental Type:</b> " + rs.getString("rental_type") + "<br>";
		        	htmlresponse += "      <b>Price:</b> " + rs.getString("rental_price") + "<br>";
		        	htmlresponse += "      <b>Signing Date:</b> " + rs.getDate("signing_date") + "<br>";
		        	htmlresponse += "      <b>Branch:</b> " + rs.getString("branch_country") + " " + rs.getString("branch_id") + "<br>";
		        	htmlresponse += "      <b>Payment Type:</b> " + rs.getString("payment_type") + "<br>";
		        	htmlresponse += "      <b>Payment Status:</b> " + rs.getString("status") + "<br><br>";
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
	    request.setAttribute("Q1", htmlresponse);
		request.getRequestDispatcher("login_success.jsp?username=Marker").forward(request, response);
	}
}