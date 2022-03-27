<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<style>
		h1{
			color:black;
			font-size: 55px;
		}h2{
			color:black;
			font-size: 45px;
		}h3{
			color:black;
			font-size: 30px;
		}p{
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
			<a href="default.html" class = "buttons" style = "float: center"> RETURN TO HOME </a><br><br>
	</header>
	<body style = "background-color: darkorange">
		<br><br><br>
		<div style = "align-self:center; width:100%">
			<a href="property_insert.html" class = "buttons"> List New Property </a>
		</div><br><br>
		<table style = "width:100%">
			<tr>
				<th>Currently Not Booked Properties </th>
				<th>Currently Booked Properties </th>
			</tr>
			<tr>
				<td style = "color:black;text-align:left">
					<div style = "color:black;background-color:white; border:solid black 6px">${NOTBOOKED}</div></td>
				<td style = "color:black;text-align:left">
					<div style = "color:black;background-color:white; border:solid black 6px">${BOOKED}</div></td>
			</tr>
		</table>				

			
	</body>
</html>
