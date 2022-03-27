  //////////////////////////////////
 //  Employee Search Terminal
//////////////////////////////////
	--Base seach
	select * from finalproject.properties where property_id in (select property_id from finalproject.properties)
	--POTENTIAL ADDONS, based on what information is inputted
		AND property_id in (select property_id from finalproject.books) 
		AND property_id not in (select property_id from finalproject.books)
		AND property_id in (select property_id from finalproject.books where guest_id = ( SOMEGUEST )
		AND property_id in (select property_id from finalproject.books where guest_id = ( SOMEHOST )
		AND ad_house_number =  SOMEHOUSENUM
		AND ad_street = SOMESTREET
		AND ad_city = SOMECITY
		AND ad_province = SOMEPROV
		AND ad_country = SOMECOUNTRY


  ////////////////////////////
 //  User Interface Loading
//////////////////////////////
	-- Find all properties that have been booked
	SELECT * 
		FROM finalproject.properties  
		WHERE property_id IN (SELECT property_id FROM finalproject.books) 
	-- Find all properties that have not been booked
	SELECT * 
		FROM finalproject.properties 
		WHERE property_id NOT IN (SELECT property_id FROM finalproject.books) 


  ///////////////////////////////////////
 //  General Login SQL
///////////////////////////////////////
	-- Add new login credentials
	1) insert into finalproject.login_credentials values('"+param[0]+"','"+param[1]+"','"+countsql+"')
	-- return password based on a username
	2) select passw from finalproject.login_credentials where username=?
		--return all login information based on username
		select * from finalproject.login_credentials where username=?
	-- Return person values based on id
	3) select * from finalproject.People where person_id = SOME_person_id
  	-- Insert people based on a new user file 
  	5) insert into finalproject.People values( countsql ,'param[0]','param[1]','param[2]','param[3]','param[4]','param[5]',
  			'param[6]','param[7]','param[8]','param[9]'
  	-- Find number of people in the system
	6) select count (*) as size from finalproject.People


  ////////////////////////////////////////
 //  Adding a Property to the Database
//////////////////////////////////////////
	-- Find the id of the corresponding username
	select person_id from finalproject.login_credentials where username = some_user_name
	-- Add new address
	insert into finalproject.addresses values (house_num, street, city, province, country)
	-- Add new property
	insert into finalproject.properties values ((size+1), host_id, 7, propertyType , amenities, bathrooms,bedrooms, 
												room_type, house_num, street, city, province, country)


  /////////////////////
 //  QUERY ONE
/////////////////////

	select People.first_name,  
			People.last_name,  
			rental_agreements.rental_type,  
			rental_agreements.rental_price,  
			rental_agreements.signing_date,  
			People.branch_id, 
			People.branch_country, 
	 		payments.payment_type, 
	 		payments.status 
	FROM finalproject.People, finalproject.signs, finalproject.rental_agreements,  finalproject.payments 
	WHERE signs.guest_id = People.person_id 
		AND signs.rental_id = rental_agreements.rental_id 
		AND rental_agreements.rental_id = payments.rental_id 
		order by payments.payment_type desc 



  /////////////////////
 //  QUERY TWO      
/////////////////////

	CREATE VIEW finalproject.GuestListView AS 
		SELECT 
			People.person_id, 
			People.first_name, 
			People.last_name, 
			People.email, 
			People.phone_number, 
			People.branch_id, 
			People.branch_country, 
			People.ad_house_number, 
			People.ad_street, 
			People.ad_city, 
			People.ad_province, 
			People.ad_country 
			FROM finalproject.guests, finalproject.People 
			where guests.guest_id = People.person_id 
			order by branch_id, branch_country, person_id



  /////////////////////
 //  QUERY THREE
/////////////////////

	SELECT *
		FROM finalproject.properties  
		INNER JOIN 
		finalproject.payments 
		ON finalproject.properties.host_id= finalproject.payments.host_id 
		where (finalproject.payments.status = 'Completed' or finalproject.payments.status = 'Approved') 
		AND payments.payment_amount = ( SELECT MIN(payment_amount) FROM finalproject.payments)


  ///////////////////
 //  QUERY FOUR
/////////////////////

	SELECT properties.property_id, properties.ad_house_number,  properties.ad_street, properties.rating, 
		properties.ad_city, properties.ad_province, properties.ad_country, 
		properties.property_type, properties.amenities, properties.bathrooms, 
		properties.bedrooms, properties.room_type, people.branch_id, people.branch_country  
		FROM finalproject.properties  
	INNER JOIN 
		finalproject.people 
		ON finalproject.properties.host_id = finalproject.people.Person_id 
	WHERE property_id IN (SELECT property_id FROM finalproject.books) 
	ORDER BY branch_country, branch_id, rating desc


  ///////////////////
 //  QUERY FIVE
///////////////////

	SELECT * 
		FROM finalproject.properties 
		WHERE property_id NOT IN (SELECT property_id FROM finalproject.books) 



  ///////////////////
 //  QUERY SIX
///////////////////

	select * 
		from finalproject.properties 
		where properties.property_id in (select property_id from finalproject.rental_agreements where EXTRACT(day from start_date) = 10) 
		order by properties.property_id 



  ///////////////////
 //  QUERY SEVEN
///////////////////

	Select 
		finalproject.People.Person_id, 
		finalproject.People.first_name,   
		finalproject.People.last_name,  
		finalproject.employs.branch_id,  
		finalproject.employs.country,  
		finalproject.employees.salary,  
		'Employee' as employeeType  
		FROM finalproject.people, finalproject.employees, finalproject.employs 
		WHERE people.person_id = employees.employee_id AND 
			employs.employee_id = employees.employee_id AND 
			employs.employee_id in ( 
				SELECT employee_id FROM finalproject.employees WHERE ( 
						salary > CAST(15000 as money))) 
	UNION 
	Select 
		finalproject.People.Person_id, 
		finalproject.People.first_name, 
		finalproject.People.last_name, 
		finalproject.employs.branch_id, 
		finalproject.employs.country,  
		finalproject.employees.salary, 
		'Manager' as employeeType 
		FROM finalproject.people, finalproject.employees, finalproject.employs 
		WHERE people.person_id = employees.employee_id AND 
		employs.employee_id = employees.employee_id AND 
		employees.employee_id IN ( SELECT manager_id from finalproject.manages) 
		AND employs.employee_id in ( 
		SELECT employee_id FROM finalproject.employees WHERE ( 
			salary > CAST(15000 as money))) 
	order by employeeType desc, person_id



  ///////////////////
 //  QUERY EIGHT
///////////////////

	Select property_type as proptype from finalproject.properties where property_id =( 
			Select property_id from finalproject.books where guest_id = ( SOMEID ))	

	Select first_name as fname from finalproject.People where person_id = ( SOMEID );

	Select last_name as lname from finalproject.People where person_id = ( SOMEID )

	Select * from finalproject.properties where property_id = ( 
			Select property_id from finalproject.books where guest_id = ( SOMEID ))

	Select payment_type as paytype from finalproject.payments where payment_id = ( 
			Select payment_id from finalproject.pays where guest_id = SOMEID )

	Select status as paystatus from finalproject.payments where payment_id = ( 
			Select payment_id from finalproject.pays where guest_id =  SOMEID )



  ///////////////////
 //  QUERY NINE
///////////////////

	select first_name, last_name from finalproject.people where person_id = ( SOMEID )
	update finalproject.People set phone_number = ( SOMENEWNUMBER )  where person_id = ( SOMEID )



  ///////////////////
 //  QUERY TEN
///////////////////
	--Create function
	create function finalproject.FirstNameFirst(firstName varchar, lastName varchar) 
		returns varchar as 
		$fullName$ 
		begin 
		    return (SELECT CONCAT(firstName , ' ', lastName));
		end  
		$fullName$ language plpgsql;
	-- Call information to use it
	select first_name as firstname from finalproject.people where person_id = ( SOMEID )
	select last_name as lastname from finalproject.people where person_id = ( SOMEID )
	-- Call function
	select * from finalproject.FirstNameFirst(firstname , lastname)