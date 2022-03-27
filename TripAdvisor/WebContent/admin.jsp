<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<style>
		h1{
			color:white;
			font-size: 55px;
		}h2{
			color:white;
			font-size: 35px;
		}h3{
			color:white;
			font-size: 25px;
		}p{
			color:white;
			font-size: 10px;
		}
		</style>
		
		
		<title>Admin Panel</title>
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
	
	<body style = "background-color:darkgreen">
	<br><br><br>
		<table style = "width:100%">

			<tr>
				<td style = "text-align:center">
				<form method="post" action="sqlQuery" style = "background-color:seagreen; border:solid black 6px">
						<br><br><h2> ***(REQUIRED) Type of Query:***  </h2>
						<h3><input style = "color:black" type="radio" name="queryOrUpdate" value="query" /> Query 
						<input style = "color:black" type="radio" name="queryOrUpdate" value="update" />  Update </h3>
						
						<br><br><br>
						<h2> ***PLEASE ENTER SQL HERE: ***  </h2>
						<textarea rows="15" cols="100" name="sql" style = "border: solid black 3px"></textarea>
						<button style = "color:black" type="submit" value="submit">Submit</button>
					</form>
				</td>
				<td style = "color:black;text-align:center">
					<div style = "color:black;background-color:white; border:solid black 3px">${RESULTS}</p></div> 
				</td>
			</tr>
		</table>				

			
	</body>
</html>
