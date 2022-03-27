package entities;
public class User {
		
	private String user_id;
	private String user_password;
	private String user_username;
	
	public User() {
		
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_i) {
		this.user_id = user_i;
	}

	public String getUser_username() {
		return user_username;
	}

	public void setUser_username(String user_un) {
		this.user_username = user_un;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_pass) {
		this.user_password = user_pass;
	}

}