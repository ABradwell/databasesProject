package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connections.PostgreSqlConn;
import entities.Employee;

@WebServlet("/loginEmployee")
public class LoginEmployee extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Employee account = new Employee();
		String username = req.getParameter("uname");
		String pwd = req.getParameter("upass");
		
		PostgreSqlConn con = new PostgreSqlConn();
		String pwdfromdb = con.getpwdbyUname(username);
		
		if (pwd.equals(pwdfromdb)) {			
				System.out.println("success");
				req.setAttribute("username", username);
				resp.sendRedirect("Employee.jsp");
				return;			
		}
		resp.sendRedirect("login_failure.jsp");
		return;
	}
}