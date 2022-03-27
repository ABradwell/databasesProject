import java.sql.*;

class first{
	public static void main(String args[]){
		try{
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql:web0.site.uottawa.ca:15432/abrad060", "abrad060", "Player0ne3nters");
			Statement st  = db.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM labratories.artist");
			while(rs.next()){
				System.out.println("Column 1 returned: ");
				System.out.println(rs.getString(1));
			}
			rs.close();
			st.close();
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(SQLException e){
			System.out.println(e);
		}
		
	}
}

