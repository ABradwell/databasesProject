<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<style>
		h1{
			color:black;
			font-size: 55px;
		} h2{
			color:black;
			font-size: 45px;
		} p{
			color:black;
			font-size: 30px;
		}
		</style>
		
		
		<title>Loginsuccess</title>
		<link rel="stylesheet" type="text/css" href="masterStyleSheet.css">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta http-equiv="Content-Language" content="ch-cn">
	</head>
	<header>
		<h1 style = "line-height: 25px;  margin-bottom: 0px;color: black; font-size: 25px; padding-bottom: 7px;"> CSI 2132 :: Databases Final Project </h1>
		<h3 style = "line-height: 15px; margin-bottom: 0px;  font-size: 18px; margin-top: 0px; color: black"> University of Ottawa, Ontario, Canada <br><br></h3> 
		<h3 style = "margin-top: 0px;margin-left: : 50px;color: black"> Submitted By 
			<br> Aiden Stevenson Bradwell || 300064655 || abrad060@uottawa.ca 
			<br> Abdullah Morrison ||300057314|| amorr128@uottawa.ca
			<br> Bryan Edler || 7161340 || bryanedler0@gmail.com </h3>
			<a href="default.html" class = "buttons" style = "float: center"> RETURN TO USER LOGIN </a><br><br>
	</header>
	<body>
		<br>
			<div>
				<h2 style = "font-size: 20px; color: black; line-height: 1px">  Choose a query to display...</h2><br><br>
				
				
				
				<div style = "text-align: center"> 
					<br><br><h2 style = "color:black">Query One</h2>
					<p style = "color:black"> Give the details of all the guests who rented properties. Please display the columns as guest
						name, rental type, rental price, signing date, branch, payment type and payment status. Sort by
						the payment type in ascending order and signing date in descending order </p><br>
					<form method="post" action="queryOne">
						<input style = "color:black" type="radio" name="isShown" value="shown" />Show
						<input style = "color:black" type="radio" name="isShown" value="hidden" />Hide
						<button style = "color:black" type="submit" value="submit">Submit</button>
					</form>
				</div>
				<div id = "q1div"> <p style = "color:black"><b>Result: </b><br> ${Q1} </p> </div>
				
				
				
				<div style = "text-align: center"> 
					<br><br><h2 style = "color:black">Query Two</h2>
					<p style = "color:black"> Create a view named GuestListView that gives the details of all the guests. Please, sort the
						guests by the branch id and then by guest id. </p><br>
					<form method="post" action="queryTwo">
						<input style = "color:black" type="radio" name="isShown" value="shown" />Show
						<input style = "color:black" type="radio" name="isShown" value="hidden" />Hide
						<button style = "color:black" type="submit" value="submit">Submit</button>
					</form>
				</div>
				<div id = "q2div"> <p style = "color:black"><b>Result: </b><br> ${Q2} </p></div>
				
				
				
				<div style = "text-align: center"> 
					<br><br><h2 style = "color:black">Query Three</h2>
					<p style = "color:black"> Display the details of the cheapest (completed) rental. </p><br>
					<form method="post" action="queryThree">
						<input style = "color:black" type="radio" name="isShown" value="shown" />Show
						<input style = "color:black" type="radio" name="isShown" value="hidden" />Hide
						<button style = "color:black" type="submit" value="submit">Submit</button>
					</form>
				</div>
				<div id = "q3div"> <p style = "color:black"><b>Result: </b><br> ${Q3}  </p></div>
				
				
				
				<div style = "text-align: center"> 
					<br><br><h2 style = "color:black">Query Four</h2>
					<p style = "color:black"> List all the properties rented and sort based on the branch id and review rating. </p><br>
					<form method="post" action="queryFour">
						<input style = "color:black" type="radio" name="isShown" value="shown" />Show
						<input style = "color:black" type="radio" name="isShown" value="hidden" />Hide
						<button  style = "color:black" type="submit" value="submit">Submit</button>
					</form>
				</div>
				<div id = "q4div"> <p style = "color:black"><b>Result: </b><br> ${Q4} </p></div>
				
				
				
				<div style = "text-align: center"> 
					<br><br><h2 style = "color:black">Query Five</h2>
					<p style = "color:black"> Find the properties that are already listed but not yet rented. Please, avoid duplications. </p><br>
					<form method="post" action="queryFive">
						<input style = "color:black" type="radio" name="isShown" value="shown" />Show
						<input style = "color:black" type="radio" name="isShown" value="hidden" />Hide
						<button style = "color:black" type="submit" value="submit">Submit</button>
					</form>
				</div>
				<div id = "q5div"> <p style = "color:black"><b>Result: </b><br> ${Q5} </p></div>
				
				
				
				<div style = "text-align: center"> 
					<br><br><h2 style = "color:black">Query Six</h2>
					<p style = "color:black"> List all the details of all properties rented on the 10th day of any month. Ensure to insert dates in
						your table that correspond in order to run your query. </h3><br>
					<form method="post" action="querySix">
						<input style = "color:black" type="radio" name="isShown" value="shown" />Show
						<input style = "color:black" type="radio" name="isShown" value="hidden" />Hide
						<button style = "color:black" type="submit" value="submit">Submit</button>
					</form>
				</div>
				<div id = "q6div"> <p style = "color:black"><b>Result: </b><br> ${Q6} </p></div>
				
				
				
				
				
				
				<div style = "text-align: center"> 
					<br><br><h2 style = "color:black">Query Seven</h2>
					<p style = "color:black"> List all the managers and the employees with salary greater than or equal to $15000 by their ids,
						names, branch ids, branch names and salary. Sort by manager id and then by employee id. </p><br>
					<form method="post" action="querySeven">
						<input style = "color:black" type="radio" name="isShown" value="shown" />Show
						<input style = "color:black" type="radio" name="isShown" value="hidden" />Hide
						<button style = "color:black" type="submit" value="submit">Submit</button>
					</form>
				</div>
				<div id = "q7div"> <p style = "color:black"><b>Result: </b><br> ${Q7} </p></div>
				
				
				
				
				
				
				<div style = "text-align: center"> 
					<br><br><h2 style = "color:black">Query Eight</h2>
					<p style = "color:black"> Consider creating a simple bill for a guest stating the property type, host, address, amount paid
						and payment type. </p><br>
					<form method="post" action="queryEight">
						Target Person_Id:<input type="text" id="id" name="id">
						<button style = "color:black" type="submit" value="submit">Submit</button>
					</form>
				</div>
				<div id = "q8div"> <p style = "color:black"><b>Result: </b><br> ${Q8} </p></div>
				
				
				
				<div style = "text-align: center"> 
					<br><br><h2 style = "color:black">Query Nine</h2>
					<p style = "color:black"> Update the phone number of a guest. </p><br>
					<form method="post" action="queryNine">
						Target Person_Id:<input type="text" id="id" name="id">
						<br>New Phone Number:<input type="text" id="number" name="number">
						<button style = "color:black" type="submit" value="submit">Submit</button>
					</form>
				</div>
				<div id = "q9div"> <p style = "color:black"><b>Result: </b><br> ${Q9} </p></div>
				
				
				
				
				<div style = "text-align: center"> 
					<br><br><h2 style = "color:black">Query Ten</h2>
					<p style = "color:black"> Create and test a user-defined function named FirstNameFirst that combines two attributes of the
					guest named firstName and lastName into a concatenated value named fullName </p><br>
					<form method="post" action="queryTen">
						Target Person_Id:<input type="text" id="id" name="id">
						<button style = "color:black" type="submit" value="submit">Submit</button>
					</form>
				</div>
				<div id = "q10div"> <p style = "color:black"><b>Result: </b><br> ${Q10} </p></div>
				
			</div>
	</body>
</html>
