<%@page import="fr.tbr.iam.datamodel.Identity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	Boolean auth = (Boolean) session.getAttribute("authenticated");
	if (auth == null || !auth) {
%>
<meta http-equiv="refresh" content="0; URL=index.jsp">
<%
	}
%>

<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<h2>Creation</h2>
	<div class="message"><%=session.getAttribute("message")%></div>
	<div class="container">
		<form xmlns="http://www.w3.org/1999/xhtml" class="form-horizontal"
			role="form" action="Search" method="get">
			<div class="form-group">
				<label for="displayName" class="col-sm-2 control-label">Display
					Name </label>
				<div class="col-sm-10">
					<input type="text" name="displayName" class="form-control"
						id="firstName" placeholder="Display Name" />
				</div>
			</div>
			<!-- TODO : complete criteria-->

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Search</button>
				</div>
			</div>
		</form>

		<!-- Search results -->
		<div>


			<table xmlns="http://www.w3.org/1999/xhtml" class="table">
				<thead>
					<tr>
						<th>Selection</th>
						<th>UID</th>
						<th>DisplayName Name</th>

						<th>Email</th>
					</tr>
				</thead>
				<tbody>

					<%
						List<Identity> identityList = (List<Identity>) session.getAttribute("lastIdentitySearchResults");
						if (identityList == null) {
					%>
					<tr>
						<td colspan="5">No result</td>
					</tr>

					<%
						} else {
							for (Identity identity : identityList) {
					%>
					<tr>
						<td><input name="selection" type="radio" /></td>
						<td><%=identity.getId()%></td>
						<td><%=identity.getDisplayName()%></td>
						<td><%=identity.getEmail()%></td>
					</tr>
					<%
						}
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
