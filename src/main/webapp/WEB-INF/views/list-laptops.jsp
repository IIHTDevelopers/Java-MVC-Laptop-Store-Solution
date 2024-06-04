<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Laptop Management</title>
	</head>
	<body>
	<div>
		<h2>Laptop List</a></h2>

		<input type="button" value="Add Laptop" onclick="window.location.href='addLaptopForm'; return false;"/>
        <form:form action="search" method="POST">
			    <input type="text" placeholder="Search By Name" name="theSearchName" value="${theSearchName}">
			    <input type="submit" value="Search"/>
        </form:form>

		<table border="1">
			<tr>
				<th>S No.</th>

				<th>Laptop Name
       	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=name,desc"> Desc </a>
                &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=name"> Asc </a>
				</th>

				<th>Price
       	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=price,desc"> Desc </a>
                &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=price"> Asc </a>
				</th>

				<th>Brand
       	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=brand,desc"> Desc </a>
                &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=brand"> Asc </a>
				</th>

				<th>Storage
       	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=storage,desc"> Desc </a>
                &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=storage"> Asc </a>
				</th>

				<th>Ram
       	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=ram,desc"> Desc </a>
                &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=ram"> Asc </a>
				</th>

				<th>Processor
       	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=processor,desc"> Desc </a>
                &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=processor"> Asc </a>
				</th>

				<th>Review
				</th>

				<th colspan="2">Action</th>
			</tr>
						<c:set var="index" value="${page * 5 + 1}" />

			<c:forEach var="laptop" items="${laptops }">
				<c:url var="updateLink" value="/laptop/updateLaptopForm">
					<c:param name="laptopId" value="${laptop.id}"></c:param>
				</c:url>

				<c:url var="deleteLink" value="/laptop/delete">
					<c:param name="laptopId" value="${laptop.id}"></c:param>
				</c:url>

				<tr>
					<td>${index}</td>
					<c:set var="index" value="${index + 1}" />

					<td>${laptop.name}</td>
					<td>${laptop.price}</td>
					<td>${laptop.brand}</td>
					<td>${laptop.storage}</td>
					<td>${laptop.ram}</td>
					<td>${laptop.processor}</td>
				    <c:if test="${laptop.bestSeller}">
                			<td>Best Seller</td>
                    </c:if>
				    <c:if test="${!laptop.bestSeller}">
                			<td></td>
                    </c:if>
					<td>
						<!-- display the update link -->
						<a href="${updateLink}">Update</a>
					</td>
					<td>
						<a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this laptop?'))) return false">Delete</a>
					</td>
					<c:if test="${!laptop.bestSeller}">
					<td><a href="/updateBestSeller?id=${laptop.id}">Mark As Match Found </a></td>
					</c:if>

				</tr>
			</c:forEach>
		</table>

			        <br><br>
                    	<c:choose>
                            <c:when test="${totalPage == 0}">
                                No Record Found
                            </c:when>
                            <c:otherwise>
                                <c:forEach begin="0" end="${totalPage-1}" varStatus="loop">
                                        &nbsp &nbsp<a href="/search?page=${loop.index}&size=5&theSearchName=${theSearchName}&sort=${sortBy}">${loop.index + 1}</a></li>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>

	</body>
</html>