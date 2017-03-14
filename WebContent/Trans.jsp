<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>
		<center>Perform the transaction...</center>
	</h1>
	<br />
	<form action="ExecuteTransaction" method="post">
		<label>Account Holder's Name</label> &nbsp;&nbsp;&nbsp; <label
			name="name"><%=request.getAttribute("hname")%></label> <br /> <br />
		<label> </label> <label>Account Balance</label> &nbsp;&nbsp;&nbsp; <label
			name="balance"><%=request.getAttribute("balance")%></label> <br /> <br />

		Add Balance: <input type="text" name="balance" /> <input type="submit"
			value="Add Balance" name=" submit" />
	</form>
</body>
</html>