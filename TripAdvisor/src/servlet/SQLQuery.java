package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sqlQuery")
public class SQLQuery extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String htmlresponse = "";
		Connection db = null;
	    ResultSet rs = null;
	    Statement st = null;
	    String sql;
	    sql = request.getParameter("sql");
        String type = request.getParameter("queryOrUpdate");
			try{
				Class.forName("org.postgresql.Driver");
				db = DriverManager.getConnection("jdbc:postgresql://web0.site.uottawa.ca:15432/abrad060", "abrad060", "3nterPlayer0ne");
				st = db.createStatement();
		        
		        if(type.contentEquals("query")) {
		        	rs = st.executeQuery(sql);
		        	System.out.println(sql);
		        	System.out.println(type);
		        	
		        	ResultSetMetaData meta = rs.getMetaData(); 
		        	int numofCols = meta.getColumnCount();  
		        	
		        	while(rs.next()) {
		        		for(int o = 1 ; o <= numofCols; o++) {
		        			htmlresponse += "(" + meta.getColumnName(o) + " : " +  rs.getString(meta.getColumnName(o)) + "), ";
		        			
		        		}
		        		htmlresponse += "<br><br>";
		        	}
		        	
		        }else if (type.contentEquals("update")){
		        	st.executeUpdate(sql);
		        	System.out.println(sql);
		        	System.out.println(type);
		        	htmlresponse = "UPDATE SUCCESSFUL.";
		        }else {
		        	htmlresponse = "PLEASE CHOOSE UPDATE OR QUERY";
		        }
	        	
		        db.close();
			}catch(Exception e) {
				htmlresponse = "ERROR IN QUERY OR UPDATE";
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
	    request.setAttribute("RESULTS", htmlresponse);
		request.getRequestDispatcher("admin.jsp?").forward(request, response);
	}
}