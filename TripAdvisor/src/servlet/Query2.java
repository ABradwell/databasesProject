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

@WebServlet("/queryTwo")
public class Query2 extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String htmlresponse = "";
		Connection db = null;
	    Statement st = null;
	    Statement st1 = null;
	    ResultSet rs = null;
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
				
				sql = "CREATE VIEW finalproject.GuestListView AS " + 
						"SELECT " + 
						"	People.person_id, " + 
						"	People.first_name, " + 
						"	People.last_name, " + 
						"	People.email, " + 
						"	People.phone_number, " + 
						"	People.branch_id, " + 
						"	People.branch_country, " + 
						"	People.ad_house_number, " + 
						"	People.ad_street, " + 
						"	People.ad_city, " + 
						"	People.ad_province, " + 
						"	People.ad_country " + 
						"	FROM finalproject.guests, finalproject.People " + 
						"	where guests.guest_id = People.person_id " + 
						"	order by branch_id, branch_country, person_id";
				
		    	System.out.print(sql + "\n");
		        st.executeUpdate(sql);
		        htmlresponse = "VIEW SUCCESSFULLY CREATED";  
			}catch(Exception e) {
				htmlresponse = "NO RESULTS FOUND...";
				System.out.println(e);
				
			} try {
				st = db.createStatement();
				String sql2 = "select * from finalproject.GuestListView"; 
				rs = st.executeQuery(sql2);
				
				while(rs.next()) {
					htmlresponse +="<b>GUEST ID: </b>" + rs.getInt("person_id") +"<br>";
					htmlresponse +="<b>NAME: </b>" + rs.getString("first_name") + " " + rs.getString("last_name") +"<br>";
					htmlresponse +="<b>CONTACT: </b>" + rs.getString("email") + " & " + rs.getInt("phone_number") + "<br>";
					htmlresponse +="<b>ADDRESS: </b>" + rs.getInt("ad_house_number") + " " + rs.getString("ad_street") + 
									rs.getString("ad_city") + ", " + rs.getString("ad_province") + ", " + rs.getString("ad_country") + "<br><br>";
				}
			}catch(Exception e) {
				System.out.println(e);
			}
			finally {
				try{
					if (st != null) { st.close(); }
					if (db != null) { db.close(); }
					
				}catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
	    }
	    
	    System.out.println(htmlresponse);
	    request.setAttribute("Q2", htmlresponse);
		request.getRequestDispatcher("login_success.jsp?username=Marker").forward(request, response);
	}
}