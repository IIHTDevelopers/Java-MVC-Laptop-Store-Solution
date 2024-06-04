<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Laptop Management</title>
	</head>
	<body>
	<div>
		<h2>Laptop </h2>
		<h3>Update Laptop</h3>
		<div>
			<div>
				<hr>
				<form:form action="saveLaptop" modelAttribute="laptop" method="POST">
					<form:hidden path="id"/>
					<div>
						<label for="name">Laptop Name: </label>
						<form:input path="name" name="name" />
						<form:errors path="name"/>
					</div>
                    <div>
						<label for="price">Price: </label>
						<form:input type="number" path="price" name="price" step = "0.1" />
						<form:errors path="price"/>
					</div>
					<div>
						<label for="brand">Brand: </label>
						<form:input path="brand" name="brand" />
						<form:errors path="brand"/>
					</div>
					<div>
						<label for="storage">Storage: </label>
						<form:input path="storage" name="storage" />
						<form:errors path="storage"/>
					</div>
					<div>
						<label for="ram">Ram: </label>
						<form:input path="ram" name="ram" />
						<form:errors path="ram"/>
					</div>
					<div>
						<label for="processor">Processor: </label>
						<form:input path="processor" name="processor" />
						<form:errors path="processor"/>
					</div>

					<input type="submit" value="Save" class="btn btn-info btn-lg btn-block"/>
				</form:form>
			</div>
		</div>
		<a href="${pageContext.request.contextPath}/laptop/list">Back to List</a>
	</div>
	</body>
</html>