package connections;

import java.sql.*;
import java.util.Random;

public class DatabaseFill{


	public static void main(String[] args) {
		DatabaseFill newfill = new DatabaseFill();
	}
	
	Connection db = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Statement st = null;
	Statement st2 = null;
	String sql;
		
    public DatabaseFill(){
    	
		populateBranches(); // Manual
    	populatePeopleGeneral(); // including login auto	
    	populatePeopleHosts();
    	populatePeopleGuests();
    	populateEmployees();
    	populateProperties();
    	populateRentalAgreements();
    	populateDay10RentalAgreements();
    	populateSigns();
    	populatePayments();
    	populatePays();
    	populateManages();
    	populateBooks();
    	
    	
    	
    }
    
	public void getConn(){
		try {
			Class.forName("org.postgresql.Driver");
			db = DriverManager.getConnection("jdbc:postgresql://web0.site.uottawa.ca:15432/abrad060", "abrad060", "3nterPlayer0ne");	

		}catch(Exception e) {
			System.out.print("error caught in databse load." + "\n");
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

	public boolean populateProperties(){
		getConn();
		
		int property_id = getPropertyCount();
		String propertyType = "";
		String amenities = "";
		int bathrooms;
		int bedrooms;
		String room_type = "";
		int house_num;
		String street = "";
		String city = "";
		String province = "";
		int rating = 0;
		String country = "";

		String[] types = new String[]{"House","Apartment","Cabin","Room","Condo"};
		String[] amenitiess = new String[]{"Pool w/ hot tub","Backyard","Balcony","Pool w/ balcony"};
		String[] roomtypes = new String[]{"Private Room","Unique Space","Double Room"};
		String[] streetnames = new String[]{"Easy Avenue","Brockville Lane","Cumberland", "Echo Parkway"};
		String[] cities = new String[]{"Toronto","Windsor","Ottawa", "Moncton"};
		String[] provinces = new String[]{"Ontario","Alberta","Nova Scotia"};
		String[] countries = new String[]{"Canada", "England"};
								

		for (int i = 0; i< 15 ; i++) {
			Random random = new Random();

 	 		province = provinces[random.nextInt(provinces.length)];
 	 		propertyType = types[random.nextInt(types.length)];
			amenities = amenitiess[random.nextInt(amenitiess.length)];
			room_type = roomtypes[random.nextInt(roomtypes.length)];
			street = streetnames[random.nextInt(streetnames.length)];
	 	 	city = cities[random.nextInt(cities.length)];
	 	 	
 	 		
 	 		house_num = random.nextInt(2000) + 1;
 	 		bathrooms = random.nextInt(3) + 1;
 	 		bedrooms = random.nextInt(3) + 1;

 	 		rating = random.nextInt(12) + 1;
 	 		country = countries[random.nextInt(2)];
 	 		int host_id = getSomeIdFromTable("hosts");

	        try {
	        	st = db.createStatement();
	        	sql = "insert into finalproject.addresses values( "+  house_num +", '"+ street +"','"+ 
	        	city +"','"+ province +"','" + country + "')";
	        	System.out.print(sql + "\n");
	        	st.executeUpdate(sql);

	        	sql = "insert into finalproject.properties values( "+ property_id +", "+  host_id  +", "+ rating  
	        	+ ", '"+ propertyType +"','"+ amenities +"', "+ bathrooms +", "+ 
	        	bedrooms +", '"+ room_type +"', "+  house_num+", '"+ street +"','"+ 
	        	city +"','"+ province +"','"+ country +"')";
	        	System.out.print(sql + "\n");
	            st.executeUpdate(sql);
	            property_id++;
	        } catch(SQLException e) {
	            e.printStackTrace();
	        }
	        

		}			 	 		
		return true;
	}

	public boolean populatePeopleGeneral(){
		
		getConn();

		int selector;
		int person_id = getCount();
		int branch_id = 1;
		int phone_number;
		int house_num;
		
		String country = "Canada";
		String first_name;
		String middle_name = "Danger";
		String last_name;
		String email;
		String street = "";
		String city = "";
		String province = "";

		String[] firstnames = new String[]{"Joe", "Bill", "Sam", "Sarah","Isaac", "Donnel", "Karim"};
		String[] lastnames = new String[]{"Rosta", "Gimba", "Hirwa", "Smith","Joel", "Buraqitz", "Burna"};
		String[] streetnames = new String[]{"Point West Drive","Gerimia Street","Main Street", "Echo Avenue"};
		String[] cities = new String[]{"Toronto","London","Calgary", "Victoria"};
		String[] provinces = new String[]{"Ontario","Alberta","British Columbia"};
		String[] positions = new String[]{"Blog Writer","Publisher", "Cleaner", "Reviewer"};
		String[] countries = new String[]{"Canada", "England"};
			
		for(int t = 0; t < 50; t++){
	 	 		

	 	 		Random random = new Random();
	 	 		
	 	 		city = cities[random.nextInt(cities.length)];
	 	 		province = provinces[random.nextInt(provinces.length)];
	 	 		street = streetnames[random.nextInt(streetnames.length)];
 				first_name = firstnames[random.nextInt(firstnames.length)];
 				last_name = lastnames[random.nextInt(lastnames.length)];
				house_num = random.nextInt(2000) + 1;
				branch_id =  random.nextInt(3) + 1;
				country = countries[random.nextInt(2)];

				email = first_name + last_name + person_id + "@gmail.com";
				String username = first_name + last_name + getCount();
			    String password = "Password";
		 	 	phone_number = random.nextInt(9999999) + 1111111;


				try{
			        	st = db.createStatement();
			        	sql = "insert into finalproject.addresses values( "+  house_num +", '"+ street +"','"+ 
			        	city +"','"+ province +"','"+ "Canada" +"')";
			        	System.out.print(sql + "\n");
			        	st.executeUpdate(sql);

			        	sql = "insert into finalproject.People values( "+ person_id +", "+ branch_id +", '"+ country +"', '" + first_name +"','"+ middle_name +"','" + last_name +"','"+ 
			        	email +"', "+ phone_number +", "+ house_num+ ",'"+ street +"','"+ 
					    city +"','"+ province +"','"+ "Canada" +"')";
					    System.out.print(sql + "\n");
					  	st.executeUpdate(sql);

					  	sql = "insert into finalproject.login_credentials values('"+ username +"','"+ password +"', "+ person_id +")";
						System.out.print(sql + "\n");
						st.executeUpdate(sql);

					  	selector = random.nextInt(3);

					  	if(selector == 0){
					  		sql = "insert into finalproject.guests values( "+ person_id +")";
						    System.out.print(sql + "\n");
						  	st.executeUpdate(sql);

					  	}else if (selector == 1){
					  		sql = "insert into finalproject.hosts values( "+ person_id +")";
						    System.out.print(sql + "\n");
						  	st.executeUpdate(sql);

					  	}else{
					  		String position = positions[random.nextInt(positions.length)];
					  		double salary = random.nextInt(20000) + 8000; 
					  		sql = "insert into finalproject.employees values("+ person_id  +", '"+ position + "', " + salary +")";
						    System.out.print(sql + "\n");
						  	st.executeUpdate(sql);

						  	sql = "insert into finalproject.employs values('Canada' ,"+ branch_id + ", " + person_id + ")";
						  	System.out.print(sql + "\n");
						  	st.executeUpdate(sql);
						  	
					  	}
					 }catch(SQLException e){
				         e.printStackTrace();
				     }
				person_id = getCount();
				
			}
		return true;
	}
	
public boolean populateEmployees(){
		
		getConn();
		int person_id = getCount();
		int branch_id = 1;
		int phone_number;
		int house_num;
		
		String country = "Canada";
		String first_name;
		String middle_name = "Danger";
		String last_name;
		String email;
		String street = "";
		String city = "";
		String province = "";

		String[] firstnames = new String[]{"Joe", "Bill", "Sam", "Sarah","Isaac", "Donnel", "Karim"};
		String[] lastnames = new String[]{"Rosta", "Gimba", "Hirwa", "Smith","Joel", "Buraqitz", "Burna"};
		String[] streetnames = new String[]{"Point West Drive","Gerimia Street","Main Street", "Echo Avenue"};
		String[] cities = new String[]{"Toronto","London","Calgary", "Victoria"};
		String[] provinces = new String[]{"Ontario","Alberta","British Columbia"};
		String[] positions = new String[]{"Blog Writer","Publisher", "Cleaner", "Reviewer"};
		String[] countries = new String[]{"Canada", "England"};
			
		for(int t = 0; t < 15; t++){
 	 		

 	 		Random random = new Random();
 	 		
 	 		city = cities[random.nextInt(cities.length)];
 	 		province = provinces[random.nextInt(provinces.length)];
 	 		street = streetnames[random.nextInt(streetnames.length)];
				first_name = firstnames[random.nextInt(firstnames.length)];
				last_name = lastnames[random.nextInt(lastnames.length)];
			house_num = random.nextInt(2000) + 1;
			branch_id =  random.nextInt(3) + 1;
			country = countries[random.nextInt(2)];

			email = first_name + last_name + person_id + "@gmail.com";
			String username = first_name + last_name + getCount();
		    String password = "Password";
	 	 	phone_number = random.nextInt(9999999) + 1111111;

				try{
			        	st = db.createStatement();
			        	sql = "insert into finalproject.addresses values( "+  house_num +", '"+ street +"','"+ 
			        	city +"','"+ province +"','"+ country +"')";
			        	System.out.print(sql + "\n");
			        	st.executeUpdate(sql);

			        	sql = "insert into finalproject.People values( "+ person_id +", "+ branch_id +", '"+ country +"', '" + first_name +"','"+ middle_name +"','" + last_name +"','"+ 
			        	email +"', "+ phone_number +", "+ house_num+ ",'"+ street +"','"+ 
					    city +"','"+ province +"','"+ country +"')";
					    System.out.print(sql + "\n");
					  	st.executeUpdate(sql);

					  	sql = "insert into finalproject.login_credentials values('"+ username +"','"+ password +"', "+ person_id +")";
						System.out.print(sql + "\n");
						st.executeUpdate(sql);

				  		String position = positions[random.nextInt(positions.length)];
				  		double salary = random.nextInt(20000) + 8000; 
				  		sql = "insert into finalproject.employees values("+ person_id  +", '"+ position + "', " + salary +")";
					    System.out.print(sql + "\n");
					  	st.executeUpdate(sql);
					  	
					  	sql = "insert into finalproject.employs values('" + country + "' ,"+ branch_id + ", " + person_id + ")";
					  	System.out.print(sql + "\n");
					  	st.executeUpdate(sql);
						  	
					 }catch(SQLException e){
				         e.printStackTrace();
				     }
				person_id = getCount();
				
			}
		return true;
	}
	
public boolean populatePeopleGuests(){
	
	getConn();
	int person_id = getCount();
	int branch_id = 1;
	int phone_number;
	int house_num;
	
	String country = "Canada";
	String first_name;
	String middle_name = "Danger";
	String last_name;
	String email;
	String street = "";
	String city = "";
	String province = "";

	String[] firstnames = new String[]{"Joe", "Bill", "Sam", "Sarah","Isaac", "Donnel", "Karim"};
	String[] lastnames = new String[]{"Rosta", "Gimba", "Hirwa", "Smith","Joel", "Buraqitz", "Burna"};
	String[] streetnames = new String[]{"Point West Drive","Gerimia Street","Main Street", "Echo Avenue"};
	String[] cities = new String[]{"Toronto","London","Calgary", "Victoria"};
	String[] provinces = new String[]{"Ontario","Alberta","British Columbia"};
	String[] countries = new String[]{"Canada", "England"};
		
	for(int t = 0; t < 15; t++){
	 		

 		Random random = new Random();
 		
 		city = cities[random.nextInt(cities.length)];
 		province = provinces[random.nextInt(provinces.length)];
 		street = streetnames[random.nextInt(streetnames.length)];
		first_name = firstnames[random.nextInt(firstnames.length)];
		last_name = lastnames[random.nextInt(lastnames.length)];
		house_num = random.nextInt(2000) + 1;
		branch_id =  random.nextInt(3) + 1;
		country = countries[random.nextInt(2)];

		email = first_name + last_name + person_id + "@gmail.com";
		String username = first_name + last_name + getCount();
	    String password = "Password";
 	 	phone_number = random.nextInt(9999999) + 1111111;

			try{
		        	st = db.createStatement();
		        	sql = "insert into finalproject.addresses values( "+  house_num +", '"+ street +"','"+ 
		        	city +"','"+ province +"','"+ country +"')";
		        	System.out.print(sql + "\n");
		        	st.executeUpdate(sql);

		        	sql = "insert into finalproject.People values( "+ person_id +", "+ branch_id +", '"+ country +"', '" + first_name +"','"+ middle_name +"','" + last_name +"','"+ 
		        	email +"', "+ phone_number +", "+ house_num+ ",'"+ street +"','"+ 
				    city +"','"+ province +"','"+ country +"')";
				    System.out.print(sql + "\n");
				  	st.executeUpdate(sql);

				  	sql = "insert into finalproject.login_credentials values('"+ username +"','"+ password +"', "+ person_id +")";
					System.out.print(sql + "\n");
					st.executeUpdate(sql);

			  		sql = "insert into finalproject.guests values( "+ person_id +")";
				    System.out.print(sql + "\n");
				  	st.executeUpdate(sql);

				 }catch(SQLException e){
			         e.printStackTrace();
			     }
			person_id = getCount();
			}
		
	return true;
	}

public boolean populatePeopleHosts(){
	
	getConn();
	int person_id = getCount();
	int branch_id = 1;
	int phone_number;
	int house_num;
	
	String country = "Canada";
	String first_name;
	String middle_name = "Danger";
	String last_name;
	String email;
	String street = "";
	String city = "";
	String province = "";

	String[] firstnames = new String[]{"Joe", "Bill", "Sam", "Sarah","Isaac", "Donnel", "Karim"};
	String[] lastnames = new String[]{"Rosta", "Gimba", "Hirwa", "Smith","Joel", "Buraqitz", "Burna"};
	String[] streetnames = new String[]{"Point West Drive","Gerimia Street","Main Street", "Echo Avenue"};
	String[] cities = new String[]{"Toronto","London","Calgary", "Victoria"};
	String[] provinces = new String[]{"Ontario","Alberta","British Columbia"};
	String[] positions = new String[]{"Blog Writer","Publisher", "Cleaner", "Reviewer"};
	String[] countries = new String[]{"Canada", "England"};

 	 		
 	 		for(int t = 0; t < 15; t++){
	 	 		

	 	 		Random random = new Random();
	 	 		
	 	 		city = cities[random.nextInt(cities.length)];
	 	 		province = provinces[random.nextInt(provinces.length)];
	 	 		street = streetnames[random.nextInt(streetnames.length)];
 				first_name = firstnames[random.nextInt(firstnames.length)];
 				last_name = lastnames[random.nextInt(lastnames.length)];
				house_num = random.nextInt(2000) + 1;
				branch_id =  random.nextInt(3) + 1;
				country = countries[random.nextInt(2)];

				email = first_name + last_name + person_id + "@gmail.com";
				String username = first_name + last_name + getCount();
			    String password = "Password";
		 	 	phone_number = random.nextInt(9999999) + 1111111;

			try{
		        	st = db.createStatement();
		        	sql = "insert into finalproject.addresses values( "+  house_num +", '"+ street +"','"+ 
		        	city +"','"+ province +"','"+ country +"')";
		        	System.out.print(sql + "\n");
		        	st.executeUpdate(sql);

		        	sql = "insert into finalproject.People values( "+ person_id +", "+ branch_id +", '"+ country +"', '" + first_name +"','"+ middle_name +"','" + last_name +"','"+ 
		        	email +"', "+ phone_number +", "+ house_num+ ",'"+ street +"','"+ 
				    city +"','"+ province +"','"+ country +"')";
				    System.out.print(sql + "\n");
				  	st.executeUpdate(sql);

				  	sql = "insert into finalproject.login_credentials values('"+ username +"','"+ password +"', "+ person_id +")";
					System.out.print(sql + "\n");
					st.executeUpdate(sql);

			  		sql = "insert into finalproject.hosts values( "+ person_id +")";
				    System.out.print(sql + "\n");
				  	st.executeUpdate(sql);


			  		String position = positions[random.nextInt(positions.length)];
			  		double salary = random.nextInt(20000) + 8000; 
			  		sql = "insert into finalproject.employees values("+ person_id  +", '"+ position + "', " + salary +")";
				    System.out.print(sql + "\n");
				  	st.executeUpdate(sql);

				  	sql = "insert into finalproject.employs values('"+ country +"' ,"+ branch_id + ", " + person_id + ")";
				  	System.out.print(sql + "\n");
				  	st.executeUpdate(sql);

				 }catch(SQLException e){
			         e.printStackTrace();
			     }
			person_id = getCount();
			
		}
	return true;
	}


	public void populateManages(){
		
		getConn();
		
		int emp1;
		int emp2;
		
		for (int i = 0; i < 10 ; i++){
			
			emp2 = getSomeIdFromTable("employees");
			emp1 = getSomeIdFromTable("employees");
			
			try {
				sql = "insert into finalproject.manages values("+ emp2 +", "+ emp1 + ")";
			  	System.out.print(sql + "\n");
			  	st.executeUpdate(sql);
			} catch (Exception e){
				e.printStackTrace();
			}
			
		}
	}
	
	public void populateBooks(){
		
		getConn();
		
		int booking_id = getTableCount("books") + 1;
		int guest_id;
		int host_id;
		int property_id;
		int rental_id;
		ResultSet rs2 = null;
		Statement st2 = null;
		try {
			st2 = db.createStatement();

			String initialSql = "select signs.guest_id, signs.host_id, rental_agreements.rental_id,rental_agreements.property_id " + 
					"from finalproject.signs, finalproject.rental_agreements, finalproject.properties " + 
					"where " + 
					"	signs.rental_id = rental_agreements.rental_id " + 
					"	AND properties.property_id = rental_agreements.property_id";
			
				  	System.out.print(initialSql + "\n");
				  	rs2 = st2.executeQuery(initialSql);
			while (rs2.next()) {
				
				property_id = Integer.parseInt(rs2.getString("property_id"));
				host_id = Integer.parseInt(rs2.getString("host_id"));
				guest_id = Integer.parseInt(rs2.getString("guest_id"));
				rental_id = Integer.parseInt(rs2.getString("rental_id"));
				try {
					sql = "insert into finalproject.books values("+ booking_id +" ,"+ guest_id + ", " + host_id + ", " + rental_id + "," + property_id + ")";
						  	System.out.print(sql + "\n");
						  	st.executeUpdate(sql);
						  	booking_id++;
				} catch (Exception e){
					e.printStackTrace();
				}
			
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public void populatePayments(){
		
		getConn();
		
		int payment_id = getTableCount("payments") + 1;
		int host_id;
		int rental_id;
		String payment_type;
		int payment_amount;
		String status;

		String[] paymentTypes = new String[]{ "Cash", "Credit", "Debit"};
		String[] statuses = new String[]{ "Completed", "Pending" };
		String[] cardStatuses = new String[] {"Approved", "Pending"};
		int[] prices = new int[]{ 250, 500, 750, 1100};
		ResultSet rs2 = null;
		Statement st2 = null;
		try {
			st2 = db.createStatement();
			String initialSql = "select signs.host_id, rental_agreements.rental_id from finalproject.rental_agreements, finalproject.signs " + 
					"where signs.rental_id = rental_agreements.rental_id";
					System.out.print(initialSql + "\n");
					rs2 = st2.executeQuery(initialSql);
			while(rs2.next()){
				
				Random random = new Random();
				host_id  = Integer.parseInt(rs2.getString("host_id"));
				rental_id  = Integer.parseInt(rs2.getString("rental_id"));
				
				int typeChoice = random.nextInt(3);
				int subChoice = random.nextInt(2);
				payment_type = paymentTypes[typeChoice];
				if(typeChoice == 1 || typeChoice == 2) {
					status = cardStatuses[subChoice];
				}else {
					status = statuses[subChoice];
				}
				payment_amount = prices[random.nextInt(4)];
	
				try {
					sql = "insert into finalproject.payments values("+ payment_id +", "+ host_id + ", " + rental_id + ",'" + payment_type  + "', " + payment_amount + ", '" + status + "')";
					System.out.print(sql + "\n");
					st.executeUpdate(sql);
					payment_id++;
				} catch (Exception e){
					e.printStackTrace();
				}
			
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void populatePays(){
		
		getConn();
		int payment_id;
		int host_id;
		int guest_id;
		String intialSQL;
		ResultSet rs2 = null;
		Statement st2 = null;
		try {
			st2 = db.createStatement();
			intialSQL = "select signs.host_id, signs.guest_id, payments.payment_id " + 
					"from finalproject.rental_agreements, finalproject.signs, finalproject.payments " + 
					"where rental_agreements.rental_id = signs.rental_id " + 
					"AND payments.rental_id = rental_agreements.rental_id";
		  	System.out.print(intialSQL + "\n");
		  	rs2 = st2.executeQuery(intialSQL);
			while(rs2.next()) {
				host_id  = Integer.parseInt(rs2.getString("host_id"));
				guest_id  = Integer.parseInt(rs2.getString("guest_id"));
				payment_id  = Integer.parseInt(rs2.getString("payment_id"));
				try {
					sql = "insert into finalproject.pays values("+ guest_id +", "+ host_id + ", " + payment_id + ")";
				  	System.out.print(sql + "\n");
				  	st.executeUpdate(sql);
				  	
				} catch (Exception e){
					e.printStackTrace();
					
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void populateRentalAgreements(){
		
		getConn();
		
		int rental_id = getTableCount("rental_agreements");
		int property_id;
		int price;

		String[] types = new String[]{ "Room", "Double", "Triple", "House"};
		int[] prices = new int[]{ 250, 500, 750, 1100};
		String type = "";
		String signing = "'SIGNING IMAGE'";



		for (int i = 0; i < 20 ; i++){

			Random random = new Random();

			int typesAndPrice = random.nextInt(4);
			type = types[typesAndPrice];
			price = prices[typesAndPrice];
			property_id = random.nextInt(getTableCount("properties"));

			int day = random.nextInt(29)+1;
			int month = random.nextInt(12)+1;
			int year = random.nextInt(2)+200;
			int hour = random.nextInt(24);
			int minute = random.nextInt(60)+1;
			int second = random.nextInt(60)+1;
			
			Timestamp signingDate = new Timestamp(year, month, day, hour, minute, second, 0);
			Timestamp startdate = new Timestamp(year, month + 1, day, hour, minute, second, 0);
			Timestamp enddate = new Timestamp(year, month+1, day+1, hour, minute, second, 0);
			try {
				sql = "insert into finalproject.rental_agreements values("+ rental_id +", "+ property_id + ", '" +  type + "', " + price 
				+ ", " + signing + ", '" + signingDate + "', '" + startdate + "', '" + enddate + "')";
				System.out.print(sql + "\n");
				st.executeUpdate(sql);
				rental_id++;
			} catch (Exception e){
				e.printStackTrace();
			}
			
		}
	}
	
public void populateDay10RentalAgreements(){
		
		getConn();
		
		int rental_id = getTableCount("rental_agreements");
		int property_id;
		int price;

		String[] types = new String[]{ "Room", "Double", "Triple", "House"};
		int[] prices = new int[]{ 250, 500, 750, 1100};
		String type = "";
		String signing = "'SIGNING IMAGE'";



		for (int i = 0; i < 4 ; i++){

			Random random = new Random();

			int typesAndPrice = random.nextInt(4);
			type = types[typesAndPrice];
			price = prices[typesAndPrice];
			property_id = random.nextInt(getTableCount("properties"));

			int day = 10;
			int month = random.nextInt(12)+1;
			int year = random.nextInt(2)+200;
			int hour = random.nextInt(24);
			int minute = random.nextInt(60)+1;
			int second = random.nextInt(60)+1;
			
			Timestamp signingDate = new Timestamp(year, month, day, hour, minute, second, 0);
			Timestamp startdate = new Timestamp(year, month + 1, day, hour, minute, second, 0);
			Timestamp enddate = new Timestamp(year, month+1, day+1, hour, minute, second, 0);
			try {
				sql = "insert into finalproject.rental_agreements values("+ rental_id +", "+ property_id + ", '" +  type + "', " + price 
				+ ", " + signing + ", '" + signingDate + "', '" + startdate + "', '" + enddate + "')";
				System.out.print(sql + "\n");
				st.executeUpdate(sql);
				rental_id++;
			} catch (Exception e){
				e.printStackTrace();
			}
			
		}
	}
	
	public void populateSigns(){

		getConn();
		
		int rental_id;
		int host_id;
		int guest_id;
		ResultSet rs2 = null;
		String initialsql = "select rental_agreements.rental_id from finalproject.rental_agreements";
		try {
			System.out.println(initialsql);
			rs2 = st.executeQuery(initialsql);
			while(rs2.next()){
				host_id = getSomeIdFromTable("hosts");
				guest_id = getSomeIdFromTable("guests");
				rental_id = Integer.parseInt(rs2.getString("rental_id"));
				
				try {
					sql = "insert into finalproject.signs values("+ rental_id +", "+ host_id + ", " + guest_id + ")";
						  	System.out.print(sql + "\n");
						  	st.executeUpdate(sql);
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void populateBranches(){

		getConn();
		
		String[] countries = new String[]{"Canada", "England"};

		try{

	    	st = db.createStatement();

	    	for(int i = 0; i < 2 ; i++){
	    		for (int o = 1; o < 4; o++){

	    			sql = "insert into finalproject.branches values(" + o + ",'" + countries[i] + "')";
	    			System.out.print(sql + "\n");
	       			st.executeUpdate(sql);
	    		}
	    	}
	    }catch(SQLException e){

	        e.printStackTrace();
	    }
	}

	
	public int getCount(){
		
		getConn();
		
		try{
			st = db.createStatement();
	    	sql = "select count (*) as size from finalproject.People";
	    	System.out.print(sql + "\n");
	        rs = st.executeQuery(sql);
	        rs.next();
	        int size = rs.getInt("size");
	        return size;
		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
	}
	
	public int getPropertyCount(){
		
		getConn();
		
		try{
			st = db.createStatement();
	    	sql = "select count (*) as size from finalproject.Properties";
	    	System.out.print(sql + "\n");
	        rs = st.executeQuery(sql);
	        rs.next();
	        int size = rs.getInt("size");
	        return size;
		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
	}

	public int getTableCount(String table){
		
		
		try{
			st = db.createStatement();
	    	sql = "select count (*) as size from finalproject." + table;
	    	System.out.print(sql + "\n");
	        rs = st.executeQuery(sql);
	        rs.next();
	        return rs.getInt("size");
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public int getSomeIdFromTable(String table) {
		
		
		try {
			st = db.createStatement();
			String sql1 = "select * from finalproject." + table;
	        ResultSet rs1 = st.executeQuery(sql1);
	        
	        Random random = new Random();
			int cycleLoop = random.nextInt(getTableCount(table));
	        System.out.println(cycleLoop + " from " + getTableCount(table));
	        
	        for(int i = 0 ; i < cycleLoop ; i++ ) {
	        	rs1.next();
	        }
	        
	        return rs1.getInt(1);
	        
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int propertyOwner(int propertyid) {
		
		try {
			st = db.createStatement();
			String sql1 = "select host_id from finalproject.properties"
					+ " where property_id = " + propertyid;
	        ResultSet rs1 = st.executeQuery(sql1);
        	rs1.next();
	        return rs1.getInt("host_id");
	        
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		return -1;
	}
	
	
}