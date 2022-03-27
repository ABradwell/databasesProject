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

@WebServlet("/queryTen")
public class Query10 extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String htmlresponse = "";
		
		String sql1;
		String sql2;
		String sql3;
		
		Statement st1 = null;
		Statement st2 = null;
		Statement st3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		Connection db = null;
		
	    int id =  Integer.parseInt(request.getParameter("id"));
	    
	    try {
	    	Class.forName("org.postgresql.Driver");
			db = DriverManager.getConnection("jdbc:postgresql://web0.site.uottawa.ca:15432/abrad060", "abrad060", "3nterPlayer0ne");
			st3 = db.createStatement();
	    	sql3 = "create function finalproject.FirstNameFirst(firstName varchar, lastName varchar)  " + 
	    			"	returns varchar as " + 
	    			"	$fullName$ " + 
	    			"	begin " + 
	    			"	    return (SELECT CONCAT(firstName , ' ', lastName));" + 
	    			"	end " + 
	    			"	$fullName$ language plpgsql;";
	    	System.out.print(sql3 + "\n");
	    	st3.executeUpdate(sql3);
	    	
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    
	    
		try{
			Class.forName("org.postgresql.Driver");
			db = DriverManager.getConnection("jdbc:postgresql://web0.site.uottawa.ca:15432/abrad060", "abrad060", "3nterPlayer0ne");
			
			st1 = db.createStatement();
			st2 = db.createStatement();
			
			sql1 = "select first_name as firstname from finalproject.people where person_id = (" + id + ")";
	    	System.out.print(sql1 + "\n");
	    	rs2 = st1.executeQuery(sql1);
	    	
	    	sql2 = "select last_name as lastname from finalproject.people where person_id = (" + id + ")";
	    	System.out.print(sql2 + "\n");
	    	rs1 = st2.executeQuery(sql2);
	    	
	    	rs1.next();
	    	rs2.next();
	    	
	    	try {
	    		
	    		sql3 = "select * from finalproject.FirstNameFirst('" + rs2.getString("firstname") + "','" + rs1.getString("lastname") +"')";
	    		System.out.println(sql3 + "\n");
	    		rs3 = st3.executeQuery(sql3);
	    		rs3.next();
	    		htmlresponse += "<h2>" + rs3.getString("FirstNameFirst") + "</h2>";
	    		
	    	}catch(Exception e) {
	    		
	    		e.printStackTrace();
	    		
	    	}
	        
	        db.close();
	        
		}catch(Exception e) {
			
			htmlresponse = "NO RESULTS FOUND...";
			System.out.println(e);
			
		}finally {
			try{
				
				if (rs1 != null) { rs1.close(); }
				if (rs2 != null) { rs2.close(); }
				if (st1 != null) { st1.close(); }
				if (st2 != null) { st2.close(); }
				if (db != null) { db.close(); }
				
			}catch (SQLException e) {
				
				e.printStackTrace();
				
			}
		}
	    
	    System.out.println(htmlresponse);
	    request.setAttribute("Q10", htmlresponse);
		request.getRequestDispatcher("login_success.jsp?username=Marker").forward(request, response);
	}
}