create table rental_agreements(
	Rental_id int primary key,
	Property_id int references properties(Property_id),
	Signing varchar(25),
	Start_date timestamp not null,
	End_date timestamp not null
);

create table branches(
	Branch_id int,
	Country varchar(75),
	primary key (Branch_id,Country)
);

create table employees(
	Employee_id int primary key,
	hasPosition varchar(50),
	Salary double check (Salary >= 0)
);

create table reviews(
	Review_id int primary key,
	Rating int,
	Cleanliness int,
	Locat int,
	Comfort int,
	Facilities int,
	Overallvalue int,
	Communications varchar(500),
	Building int,
	Food int,
	Constraint CHK_reviews Check (
		(Rating = null or Rating <= 10 AND Rating >= 1) AND
		(Cleanliness = null or Cleanliness <= 10 AND Cleanliness >= 1) AND
		(Locat = null or Locat <= 10 AND Locat >= 1) AND
		(Comfort = null or Comfort <= 10 AND Comfort >= 1) AND
		(Facilities = null or Facilities <= 10 AND Facilities >= 1) AND
		(Overallvalue = null or Overallvalue <= 10 AND Overallvalue >= 1) AND
		(Building = null or Building <= 10 AND Building >= 1) AND
		(Food = null or Food <= 10 AND Food >= 1)
	)
);

create table addresses(
	House_num int,
	Street varchar(50),
	City varchar(50),
	Province varchar(50),
	Country varchar(50),
	primary key (House_num, Street, City, Province, Country)
);


Create table People (
	PersonId int Not null Primary key,
	First_name varchar(35) not null,
	Middle_name varchar(35),
	Last_name varchar(35) not null,
	Email varchar(50) not null unique,
	Phone_Number int not null,
	Ad_house_number int not null references addresses(House_num),
	Ad_street varchar(50) not null  references addresses(Street),
	Ad_city varchar(50) not null  references addresses(City),
	Ad_province varchar(50) not null  references addresses(Province),
	Ad_Country varchar(50) not null references addresses(Country)
);
create table properties(
	Property_id int primary key,
	Property_type varchar(50) not null,
	Amenities varchar(50) not null,
	Bathrooms int not null check (bathrooms > 0),
	Bedrooms int not null check (bedrooms > 0),
	Room_Type varchar(50) not null,
	Ad_house_number int not null references addresses(House_num),
	Ad_street varchar(50) not null  references addresses(Street),
	Ad_city varchar(50) not null  references addresses(City),
	Ad_province varchar(50) not null  references addresses(Province),
	Ad_Country varchar(50) not null references addresses(Country)
);

create table pays(
	Guest_id int references guests(Guest_id),
	Host_id int references hosts(Host_id),
	Payment_id int references payments(Payment_id),
	primary key(Guest_id,Host_id,Payment_id)
);

create table payments(
	Payment_id int primary key,
	Host_id int not null references hosts(Host_id),
	Payment_type varchar(50) not null,
	Status varchar(25) not null,
	 CONSTRAINT CHK_payments CHECK (
		(payment_type = 'Cash' OR payment_type = 'Debit' Or payment_type = 'Credit') AND
		(payment_type = 'Cash' AND status ='Completed' OR 
	 	payment_type = 'Debit' AND status ='Approved' OR
		payment_type = 'Credit' AND status ='Approved' OR
		status ='Pending'))
);

create table hosts(
	Host_id int not null primary key references people(person_id)
);

create table guests(
	Guest_id int not null primary key references people(person_id)
);

create table books(
	Booking_id int primary key,
	Guest_id int references guests(Guest_id),
	Host_id int references hosts(Host_id),
	Property_id int references properties(Property_id)
);

create table manages(
	Manager_id int references employees(Employee_id),
	Employee_id int references employees(Employee_id),
	Primary key (Manager_id, Employee_id)
);


create table employs(
	Branch_id int references branches(branch_id),
	Employee_id int references employees(Employee_id),
	primary key (Branch_id, Employee_id)
);

create table makes(
	Guest_id int references guests(Guest_id),
	Property_id int references properties(Property_id),
	Review_id int references reviews(Review_id)
);