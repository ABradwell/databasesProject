import java.util.Random;
import java.sql.*;

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
    	//populateProperties();
		populateBranches(); // Manual
    	populatePeopleGeneral(); // including login auto	
    	populatePeopleHosts();
    	populatePeopleGuests();
    	populateEmployees();
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
		String countries = "";

		String[] types = new String[]{"House","Apartment","Cabin","Room","Condo"};
		String[] amenitiess = new String[]{"Pool w/ hot tub","Backyard","Balcony","Pool w/ balcony"};
		String[] roomtypes = new String[]{"Private Room","Unique Space","Double Room"};
		String[] streetnames = new String[]{"Easy Avenue","Brockville Lane","Cumberland", "Echo Parkway"};
		String[] cities = new String[]{"Toronto","Windsor","Ottawa", "Moncton"};
		String[] provinces = new String[]{"Ontario","Alberta","Nova Scotia"};
		String[] countries = new String[]{"Canada", "England"};
			for(int o = 0; o < types.length; o++) {
				propertyType = types[o];
				for(int i = 0; i < amenitiess.length; i++) {
					amenities = amenitiess[o];
					for(int u = 0; u < roomtypes.length; u++) {
						room_type = roomtypes[u];
						 for(int y = 0; y < streetnames.length; y++) {
							street = streetnames[y];
							for(int t = 0; t < cities.length; t++) {
						 	 	city = cities[t];

						 	 	for(int r = 0; r < provinces.length; r++) {
						 	 		province = provinces[r];

						 	 		Random random = new Random();
						 	 		house_num = random.nextInt(2000) + 1;
						 	 		bathrooms = random.nextInt(3) + 1;
						 	 		bedrooms = random.nextInt(3) + 1;

						 	 		rating = streetnames[random.nextInt(12) + 1];
						 	 		branchID = streetnames[random.nextInt(3) + 1];
						 	 		branchID = streetnames[random.nextInt(3) + 1];
						 	 		country = countries[countries];
						 	 		int host_id = random.nextInt(getTableCount("hosts"));

							        try {
							        	st = db.createStatement();
							        	sql = "insert into finalproject.addresses values( "+  house_num +", '"+ street +"','"+ 
							        	city +"','"+ province +"','" + country + "')";
							        	System.out.print(sql + "\n");
							        	st.executeUpdate(sql);

							        	sql = "insert into finalproject.properties values( "+ property_id +"', "+  host_id  +", "+ rating  
							        	+ ", '"+ propertyType +"','"+ amenities +"', "+ bathrooms +", "+ 
							        	bedrooms +", '"+ room_type +"', "+  house_num+", '"+ street +"','"+ 
							        	city +"','"+ province +"','"+ country +"')";
							        	System.out.print(sql + "\n");
							            st.executeUpdate(sql);

							            sql = "insert into finalproject.branches values( "+ branch_id +"','"+ country +"')";
							        	System.out.print(sql + "\n");
							            st.executeUpdate(sql);

							        } catch(SQLException e) {
							            e.printStackTrace();
							        }
							        
							    property_id++;	       
						 	 	}
							}	
						}
					}
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
			
		for(int t = 0; t < cities.length; t++){
	 	 	city = cities[t];
	 	 	for(int r = 0; r < provinces.length; r++){
	 	 		province = provinces[r];

	 	 		Random random = new Random();
	 	 		street = streetnames[random.nextInt(streetnames.length)];
 				first_name = firstnames[random.nextInt(firstnames.length)];
 				last_name = lastnames[random.nextInt(lastnames.length)];
				house_num = random.nextInt(2000) + 1;
				branchID = streetnames[random.nextInt(3) + 1];
				country = countries[countries];

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
			
		for(int t = 0; t < cities.length; t++){
	 	 	city = cities[t];
	 	 	for(int r = 0; r < provinces.length; r++){
	 	 		province = provinces[r];

	 	 		Random random = new Random();
	 	 		branchID = streetnames[random.nextInt(3) + 1];
				country = countries[countries];
	 	 		street = streetnames[random.nextInt(streetnames.length)];
 				first_name = firstnames[random.nextInt(firstnames.length)];
 				last_name = lastnames[random.nextInt(lastnames.length)];
				house_num = random.nextInt(2000) + 1;

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
			}
		return true;
	}
	
public boolean populatePeopleGuests(){
	
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
		
	for(int t = 0; t < cities.length; t++){
 	 	city = cities[t];
 	 	for(int r = 0; r < provinces.length; r++){
 	 		province = provinces[r];

 	 		Random random = new Random();
 	 		street = streetnames[random.nextInt(streetnames.length)];
			first_name = firstnames[random.nextInt(firstnames.length)];
			last_name = lastnames[random.nextInt(lastnames.length)];
			house_num = random.nextInt(2000) + 1;
			branchID = streetnames[random.nextInt(3) + 1];
			countrychoice = streetnames[random.nextInt(2) + 1];
			country = countries[countries];

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
		}
	return true;
	}

public boolean populatePeopleHosts(){
	
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
		
	for(int t = 0; t < cities.length; t++){
 	 	city = cities[t];
 	 	for(int r = 0; r < provinces.length; r++){
 	 		province = provinces[r];

 	 		Random random = new Random();
 	 		street = streetnames[random.nextInt(streetnames.length)];
			first_name = firstnames[random.nextInt(firstnames.length)];
			last_name = lastnames[random.nextInt(lastnames.length)];
			house_num = random.nextInt(2000) + 1;

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
		}
	return true;
	}


	public void populateManages(){
		int emp1;
		int emp2;

		for (int i = 0; i < 10 ; i++){
			
			Random random = new Random();
			emp2 = random.nextInt(10)
			emp1 = random.nextInt(getTableCount("employees"));

			sql = "insert into finalproject.manages values("+ emp2 +", "+ emp1 + ", " + payment_id + ")";
				  	System.out.print(sql + "\n");
				  	st.executeUpdate(sql);
		}
	}


	public void populateBooks(){

		//int booking_id = getTableCount("bookings");
		int booking_id = 0;
		int guest_id;
		int host_id;
		int property_id;

		for (int i = 0; i < 15 ; i++){
			
			Random random = new Random();
			property_id = random.nextInt(getTableCount("properties"));
			host_id = random.nextInt(getTableCount("hosts"));
			guest_id = random.nextInt(getTableCount("guests"));
			sql = "insert into finalproject.books values("+ booking_id +" ,"+ guest_id + ", " + host_id + ", " + property_id + ")";
				  	System.out.print(sql + "\n");
				  	st.executeUpdate(sql);
			booking_id++;
		}
	}


	public void populatePayments(){

		int payment_id = 0;
		int host_id;
		String payment_type;
		int payment_amount;
		String status;

		String[] paymentTypes = new String[]{ "cash", "check", "credit card", "direct debit"};
		String[] statuses = new String[]{ "completed", "approved", "pending"};
		int[] prices = new String[]{ 250, 500, 750, 1100};

		for (int i = 0; i < 50 ; i++){
			
			Random random = new Random();

			payment_id = random.nextInt(getTableCount("payments"));
			host_id = random.nextInt(getTableCount("hosts"));
			guest_id = random.nextInt(getTableCount("guests"));
			payment_type = paymentTypes[random.nextInt(4)];
			status = statuses[random.nextInt(3)];
			payment_amount = prices[random.nextInt(4)];

			sql = "insert into finalproject.payments values("+ payment_id +", "+ host_id + ", '" + payment_type  + "', " + payment_amount + ", '" + status + "')";
			System.out.print(sql + "\n");
			st.executeUpdate(sql);

		payment_id++;
		}
	}



	public void populatePays(){
		int payment_id;
		int host_id;
		int guest_id;

		for (int i = 0; i < 10 ; i++){
			
			Random random = new Random();
			payment_id = random.nextInt(getTableCount("payments"));
			host_id = random.nextInt(getTableCount("hosts"));
			guest_id = random.nextInt(getTableCount("guests"));
			sql = "insert into finalproject.pays values("+ guest_id +", "+ host_id + ", " + payment_id + ")";
				  	System.out.print(sql + "\n");
				  	st.executeUpdate(sql);
		}
	}

	public void populateRentalAgreements(){

		//int rental_id = getTableCount("rental_agreements");
		int rental_id = 0;
		int property_id;
		int price;

		String[] types = new String[]{ "Room", "Double", "Triple", "House"};
		int[] prices = new String[]{ 250, 500, 750, 1100};
		String type = "";
		String signing = "*SIGNING IMAGE*";



		for (int i = 0; i < 75 ; i++){

			Random random = new Random();

			int typesAndPrice = random.nextInt(4);
			type = types[typesAndPrice];
			price = types[typesAndPrice];
			property_id = random.nextInt(getTableCount("properties"));

			int day = random.nextInt(29+1);
			int month = random.nextInt(12+1);
			int year = random.nextInt(2+2020);
			int hour = random.nextInt(24);
			int minute = random.nextInt(60+1);
			int second = random.nextInt(60+1);

			Timestamp signingDate = new Timestamp(year, month, date,  hour,  minute,  second,  0);
			Timestamp startDate = new Timestamp(year, (month+1) , date ,  hour,  minute,  second,  0);
			Timestamp endDate = new Timestamp(year, (month+1) , (date=1) ,  hour,  minute,  second,  0);

			sql = "insert into finalproject.rental_agreements values("+ rental_id +", "+ property_id + ", " +  type + ", " + price 
			+ ", " + signing + ", " + signingDate + ", " + startDate + ", " + endDate + ")";
			System.out.print(sql + "\n");
			st.executeUpdate(sql);
			booking_id++;
		}
	}



	public void populateSigns(){

		int rental_id;
		int host_id;
		int guest_id;

		for (int i = 0; i < 10 ; i++){
			
			Random random = new Random();
			rental_id = random.nextInt(getTableCount("rental_agreements"));
			host_id = random.nextInt(getTableCount("hosts"));
			guest_id = random.nextInt(getTableCount("guests"));
			sql = "insert into finalproject.signs values("+ rental_id +", "+ host_id + ", " + guest_id + ")";
				  	System.out.print(sql + "\n");
				  	st.executeUpdate(sql);
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
		getConn();
		try{
			st = db.createStatement();
	    	sql = "select count (*) as size from finalproject." + table;
	    	System.out.print(sql + "\n");
	        rs = st.executeQuery(sql);
	        int size = ;
	        return rs.getInt("size");
		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
	}
}