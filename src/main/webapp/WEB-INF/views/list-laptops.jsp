<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Laptop Management</title>
	</head>
	<body>
	<div>
		<h2><a href="${pageContext.request.contextPath}/laptop/list">Insurance Policy List</a></h2>

		<input type="button" value="Add Laptop" onclick="window.location.href='addLaptopForm'; return false;"/>
        <form:form action="search" method="POST">
			<div>
			    <div>
			      <div><i aria-hidden="true"></i></div>
			    </div>
			    <input type="text" placeholder="Search By Name" name="theSearchName">
			    <input type="submit" value="Search"/>
			</div>
        </form:form>
		<table border="1">
			<tr>
				<th>#</th>
				<th>Laptop Name</th>
				<th>Price</th>
				<th>Brand</th>
				<th>Storage</th>
				<th>Ram</th>
				<th>Processor</th>
				<th colspan="2">Action</th>
			</tr>
			<c:set var="index" value="0" />
			<c:forEach var="laptop" items="${laptops }">
				<c:url var="updateLink" value="/laptop/updateLaptopForm">
					<c:param name="laptopId" value="${laptop.id}"></c:param>
				</c:url>

				<c:url var="deleteLink" value="/laptop/delete">
					<c:param name="laptopId" value="${laptop.id}"></c:param>
				</c:url>

				<c:set var="index" value="${index + 1}" />
				<tr>
					<td>${index}</td>
					<td>${laptop.name}</td>
					<td>${laptop.price}</td>
					<td>${laptop.brand}</td>
					<td>${laptop.storage}</td>
					<td>${laptop.ram}</td>
					<td>${laptop.processor}</td>
					<td>
						<!-- display the update link -->
						<a href="${updateLink}">Update</a>
					</td>
					<td>
						<a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this laptop?'))) return false">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	</body>
</html>