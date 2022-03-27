<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<style>

		th{
			color:darkred;
			font-size: 30px;
		}
		</style>
		
		
		<title>SearchEngine</title>
		
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
			<a href="default.html" class = "buttons" style = "float: center"> RETURN TO HOME </a><br><br>
	</header>
	
	<body style = "background-color:darkred">
	<br><br><br>
		<table style = "width:100%">
			<tr>
				<th> Search Criteria </th>
				<th> Currently Booked Properties </th>
			</tr>
			<tr>
				<td style = "text-align:center">
				<form method="post" action="findSearch" style = "background-color:white; border:solid red 3px">
				
						<br><br><h2 style = "color:black"><b> BOOKED/UNBOOKED </b></h2><br>
						<input style = "color:black" type="radio" name="isBooked" value="yes" />Booked
						<input style = "color:black" type="radio" name="isBooked" value="no" />Unbooked
						
						<br><br><br><h2 style = "color:black"><b>RENTAL</b></h2><br>
						Rented By [user_id] :    <input type="text" id="guest_id" name="guest_id"><br>
						Rented To [host_id] :    <input type="text" id="host_id" name="host_id"><br>
						
						<br><br><br><h2 style = "color:black"><b>ADDRESS</b></h2><br>
						House Number :    <input type="text" id="house_num" name="house_num"><br>
						Street :    <input type="text" id="street" name="street"><br>
						City :    <input type="text" id="city" name="city"><br>
						Province :    <input type="text" id="province" name="province"><br>
						Country:    <input type="text" id="country" name="country"><br>
						
						<button style = "color:black" type="submit" value="submit">Search</button><br><br>
					</form>
				</td>
				<td style = "color:black;text-align:center">
					<div style = "color:black;background-color:white; border:solid red 3px">${RESULTS}</div> 
				</td>
			</tr>
		</table>				

			
	</body>
</html>
