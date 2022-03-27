package entities;
public class Employee {
		
	private int employee_id;
	private String employee_password;
	private String employee_username;
	
	public Employee() {
		
	}
	
	public Employee(int id, String pass, String user) {
		employee_id = id;
		employee_password = pass;
		employee_username = user;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_i) {
		this.employee_id = employee_i;
	}

	public String getEmployee_username() {
		return employee_username;
	}

	public void setEmployee_username(String user_un) {
		this.employee_username = user_un;
	}

	public String getEmployee_password() {
		return employee_password;
	}

	public void setEmployee_password(String user_pass) {
		this.employee_password = user_pass;
	}
}