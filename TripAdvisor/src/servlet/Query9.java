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

@WebServlet("/queryNine")
public class Query9 extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String htmlresponse = "";
		Connection db = null;
	    Statement st = null;
	    ResultSet rs = null;
	    String sql;
	    String ishidden = request.getParameter("isShown");
	    int id =  Integer.parseInt(request.getParameter("id"));
	    int newNumber =  Integer.parseInt(request.getParameter("number"));

		try{
			Class.forName("org.postgresql.Driver");
			db = DriverManager.getConnection("jdbc:postgresql://web0.site.uottawa.ca:15432/abrad060", "abrad060", "3nterPlayer0ne");
			st = db.createStatement();
			String sql2 = "select first_name, last_name from finalproject.people where person_id = (" + id + ")";
			sql = "update finalproject.People set phone_number = ("+ newNumber +")  where person_id = ("+ id +")";
			
	    	System.out.print(sql + "\n");
	    	st.executeUpdate(sql);
	    	System.out.print(sql2 + "\n");
	    	rs = st.executeQuery(sql2);
	        while(rs.next()) {
	        	htmlresponse += "<h2 style = 'color:black'>" + rs.getString("first_name") + " "+rs.getString("last_name") +"'s number is now " + newNumber +  "</h2>";
	        }
	        db.close();
		}catch(Exception e) {
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
	    
	    System.out.println(htmlresponse);
	    request.setAttribute("Q9", htmlresponse);
		request.getRequestDispatcher("login_success.jsp?username=Marker").forward(request, response);
	}
}