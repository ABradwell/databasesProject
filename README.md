# Trip Advisor Recreation 
## Submission for Databases I, University of Ottawa 

Run on MySQL online SQL servers, this program emulates a TripAdvisor system. The user is able to login as either an admin, a customer, or an employee, and interact with the server through an HTML interface, hosted on an apache server. 

The assignment included the creation of and execution of various SQL commands through an "Admin Terminal"

A video is included for a run-time example. 


This project contains the following files, which serve their included purpose: 

	.settings (directory) 
    | Contains eclipse settings for launching the project in a similar state to its initial run

	build (directory) 
    | An auto-generated series of classes, for the subobjects stored in the SQL database

	src (directory)
    | Includes java representations of the objects stored in the SQL database

        | PostgreSqlConn.java (contains function run by database during runtime)
        | employee.java (contains functions for the employees of the system for runtime)
        | Room.java (contains functions for the rooms of the system for runtime)

        | CustomerloginServlet.java (backend for logging in as a customer account)
        | CustomerRegisterServlet.java (backend for registering as a new account)
        | EmployeeloginServlet.java (backend for logging in as an employee)
        | RoombookServlet.java (backend for booking a new room as a customer)


    WebContent (directory)
    | The User Interface of the program, created in either hand-coded HTML or javascript generated files. 

        | Customer.html (Customer UI for placing and booking new rooms. Includes currently booked rooms)
        | customer_login.html (Customer Login screen)
        | customer_register.html (Create new customer account screen)
        | Employee.html (Employee UI for editing and adding new accounts)
        | index.html (Main UI page launced when program runs)
        | booking.jsp (WARNING FOR BOOKING MADE)
        | login_failure.jsp (WARNING FOR FAILED LOGIN)
        | login_success.jsp (WARNING FOR SUCESSFUL LOGIN)

    .classpath
    .project
    Databasefill.java (Script to populate the database with various combinations of given strings // numbers)
    first.java (Inital load of SQL database)
    InitalBuild.sql (Inital load of SQL database)
    README.txt (This File)
    refinedSQL.sql (Searched made throughout the program, part of given assignment)
    Report.docx (Includes design maps of the database)
    Web Application Video.mp4 (Demonstartion of the program being run)

 