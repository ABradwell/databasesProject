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

@WebServlet("/querySeven")
public class Query7 extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String htmlresponse = "";
		Connection db = null;
	    Statement st = null;
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
				
				sql = "Select " + 
						"	finalproject.People.Person_id, " + 
						"	finalproject.People.first_name,  " + 
						"	finalproject.People.last_name,  " + 
						"	finalproject.employs.branch_id,  " + 
						"	finalproject.employs.country,  " + 
						"	finalproject.employees.salary, " + 
						"	'Employee' as employeeType " + 
						" " + 
						"	FROM finalproject.people, finalproject.employees, finalproject.employs " + 
						"	WHERE people.person_id = employees.employee_id AND " + 
						"	employs.employee_id = employees.employee_id AND " + 
						"	employs.employee_id in ( " + 
						"	SELECT employee_id FROM finalproject.employees WHERE ( " + 
						"		salary > CAST(15000 as money))) " + 
						" " + 
						"UNION " + 
						" " + 
						"Select " + 
						"	finalproject.People.Person_id, " + 
						"	finalproject.People.first_name, " + 
						"	finalproject.People.last_name, " + 
						"	finalproject.employs.branch_id, " + 
						"	finalproject.employs.country,  " + 
						"	finalproject.employees.salary, " + 
						"	'Manager' as employeeType " + 
						" " + 
						"	FROM finalproject.people, finalproject.employees, finalproject.employs " + 
						"	WHERE people.person_id = employees.employee_id AND " + 
						"	employs.employee_id = employees.employee_id AND " + 
						"	employees.employee_id IN ( SELECT manager_id from finalproject.manages) " + 
						"	AND employs.employee_id in ( " + 
						"	SELECT employee_id FROM finalproject.employees WHERE ( " + 
						"		salary > CAST(15000 as money))) " + 
						"order by employeeType desc, person_id";
				
		    	System.out.print(sql + "\n");
		    	rs = st.executeQuery(sql);
		        while(rs.next()) {
		        	
		        	htmlresponse += "<b>Worker: </b>"+ rs.getInt("Person_id") +"\n<br>";
			        htmlresponse += "<b>Name: </b>" + rs.getString("first_name") + " " + rs.getString("last_name") + "\n<br>";
			        htmlresponse += "<b>Branch: </b>" + rs.getString("country") + " :: " + rs.getInt("branch_id") + "\n<br>";
			        htmlresponse += "<b>Worker Type: </b>"+ rs.getString("employeeType") +"\n<br>";
			        htmlresponse += "<b>Salary: </b>" + rs.getString("salary") + "\n\n<br><br>";
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
	    request.setAttribute("Q7", htmlresponse);
		request.getRequestDispatcher("login_success.jsp?username=Marker").forward(request, response);
	}
}