<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Database content</title>

 	<script type="text/javascript" src="./js/grid.js"></script>		
	
	<script type="text/javascript" src="./js/jquery.js"></script>
	
	<script type="text/javascript" src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
	<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" />
	
	<link rel="stylesheet" type="text/css" href="./css/styles-grid.css" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	
  	<script type="text/javascript">
	$(document).ready(function() {
			$('#books_table').DataTable();
	});
	// The following function saves database records 
	// 	  picked by user in a hidden input element
	// For some reason if this function is moved to a separate .js file, 
	// 	  it doesn't "see" the needed page element
		var collected = [];
		function checkboxCollector(id) {
			var storage = document.getElementById("pickedChoices");
			collected = storage.value.split(",");
			
			if (storage.value === "") {
				storage.value = id;
			} else {
				var index = collected.indexOf(id.toString());
				if (index > -1) {
					collected.splice(index, 1);
					storage.value = collected;
				} else {
					storage.value += ("," + id); }
			}
			//alert(storage.value);
		}
	</script>
</head>
<body>
	<div id="dbcontrols" class="dbcontrols">
		<a class="btn btn-primary" name="refresh" id="refresh" href="./refresh">REFRESH</a>
		<br/>
		<a class="btn btn-primary" name="ed"	  id="edit"	   onclick="editing()" >EDIT</a> 
		<br/>
		<a class="btn btn-primary" name="del"  id="delete"  onclick="deletion()" >DELETE</a> 
		<br />

		<form id="selectedRows" action="./send" method="post">
			<input type="hidden" id="pickedChoices" name="picked" value="">
			<input type="hidden" id="editFlag"		name="edit"	  value="">
			<input type="hidden" id="deleteFlag"	name="delete" value="">
<%--		
		<input class="btn btn-primary" name="edit" id="edit" value="EDIT" onclick="editing()">
		<br />
		<input class="btn btn-primary" name="delete" id="delete" value="DELETE" onclick="deletion()">
		<br />
--%>	</form>
	</div>
	<div id="datagrid" class="datagrid">
		<%-- TODO change "action" value set in 'editAndRemove.js'. This is a temporary solution due to lack of time --%>
<%--	<form id="selectedRows" action="./send" method="post">
			<input type="hidden" id="pickedChoices" name="picked" value="">
		</form>
--%>		<table id="books_table">
			<thead>
				<tr>
					<th></th>
					<th>id</th>		
					<th>Published</th>
					<th>Author</th>
					<th>Title</th>
					<th>Pages</th>
					<th>Price</th>
					<th>Availability</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${records}" var="books">
				
						<tr id="${books.bookId}">
							<td>
								<input type="checkbox" name="book${books.bookId}" value="${books.bookId}"
												onchange="checkboxCollector(${books.bookId})" >
							</td>
							<td>
								<c:out value="${books.bookId}"></c:out>
							</td>
							<td>
								<c:out value="${books.bookPublished}"></c:out>
							</td>
							<td>
								<c:out value="${books.bookAuthor}"></c:out>
							</td>
							<td>
								<c:out value="${books.bookName}"></c:out>
							</td>
							<td>
								<c:out value="${books.bookNumberOfPages}"></c:out>
							</td>
							<td>
								<c:out value="${books.bookPrice}"></c:out>
							</td>
							<td>
								<c:out value="${books.bookAvailability}"></c:out>
							</td>
						</tr>
				
				</c:forEach>
			</tbody>
		</table>
		
	</div>
	<div id="rightdiv"></div>
</body>
</html>