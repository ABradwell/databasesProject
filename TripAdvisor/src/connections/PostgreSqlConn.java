package connections;

import java.sql.*;
import java.util.ArrayList;

import entities.*; 


public class PostgreSqlConn{
	
		Connection db = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Statement st = null;
	    String sql;
	    
		public void getConn(){
			try {
				Class.forName("org.postgresql.Driver");
				 db = DriverManager.getConnection("jdbc:postgresql://web0.site.uottawa.ca:15432/abrad060", "abrad060", "3nterPlayer0ne");									
			}catch(Exception e) {
				System.out.print("error caught  at database constuction");
			}
		}
		
		public void closeDB() {
				try {
					if(rs != null){
						rs.close();
					}
					if(ps!=null){
						ps.close();
					}
					if(st!=null){
						st.close();
					}
					if(db!=null){
						db.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		
		public String getpwdbyUname(String param){
			getConn(); 
			String pwd = "";
	        try{
	            ps = db.prepareStatement("select passw from finalproject.login_credentials where username=?");
	            ps.setString(1, param);	               
	            rs = ps.executeQuery();
				while(rs.next()) {
					pwd = rs.getString(1);
				}
	        }catch(SQLException e){
	            e.printStackTrace();
	        }finally {
	        	closeDB();
	        }
			return pwd;       
	    }
		
		public String[] getUserInforByUsername(String param){
			getConn();
			String[] out = new String[2];
			
	        try{
	            ps = db.prepareStatement("select * from finalproject.login_credentials where username=?");
	            ps.setString(1, param);
	            rs = ps.executeQuery();
				while(rs.next()) {
					out[0] = rs.getString(2); // Password
					out[1] = rs.getString(3); // Person_id
				}
				
	        }catch(SQLException e){
	            e.printStackTrace();
	            
	        }finally {
	        	closeDB();
	        	
	        }
			return out;       
	    }

		public boolean insertLoginCredentials(String[] param){
			getConn();
	        try{
	        	st = db.createStatement();
	        	int countsql = getCount();
	        	sql = "insert into finalproject.login_credentials values('"+param[0]+"','"+param[1]+"','"+countsql+"')";
	        	System.out.print(sql);
	            st.executeUpdate(sql);
	            return true;
	            
	        }catch(SQLException e){
	            e.printStackTrace();
	            return false;

	        }finally {
	        	closeDB();
	        }	       
	    }
		
		public String getPersonFromLogin(String username){
			
			getConn();
			int person_id = -1;
			String ps2 = null;
			
	        try{
	        	st = db.createStatement();
	            ps = db.prepareStatement("select * from finalproject.login_credentials where username=?");
	            ps.setString(1, username);
	            rs = ps.executeQuery();
				while(rs.next()) {
					person_id = rs.getInt(3);
				}
				
				ps2 = "select * from finalproject.People where person_id = " + person_id;
				rs = st.executeQuery(ps2);
	        	String countString = "select count (*) as size from finalproject.employees where id = " + person_id;
	        	ResultSet countReturnSet = st.executeQuery(countString);
		        int size = countReturnSet.getInt("size");
		        
	        	if(size > 0) {
	        		return ("employee");
	        	}else {
	        		return ("user");
	        	}
	        			
	        }catch (SQLException e){
	        	
	        	System.out.println(e);
	        }
	        
			return "Not a User";       
	    }
		
		

	    public boolean insertUser(String[] param){
	    	
	    	getConn();
	    	
	    	try{
	    		st = db.createStatement();
	    		int countsql = getCount();
	    		sql = "insert into finalproject.People values('"+countsql+"','"+param[0]+"','"+param[1]+"','"+param[2]+"','"+param[3]+"','"+param[4]+"','"+param[5]+
	    			  "','"+param[6]+"','"+param[7]+"','"+param[8]+"','"+param[9]+"')";
	    		System.out.print(sql);
	            st.executeUpdate(sql);
	            return true;
	    	}catch(SQLException e){
	    		e.printStackTrace();
	    		return false;
	    	}finally{
	    		closeDB();
	    	}
	    }

	    public int getCount(){
	    	getConn();
	    	try{
	    		st = db.createStatement();
		    	sql = "select count (*) as size from finalproject.People";
		    	System.out.print(sql);
		        rs = st.executeQuery(sql);
		        int size = rs.getInt("size");
		        return size;
	    	}catch(SQLException e){
				e.printStackTrace();
	    		return -1;
	    	}finally{
	    		closeDB();
	    	}
	    	
	    }
}