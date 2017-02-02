<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Please edit a record</title>
	<script type="text/javascript" src="./js/editor.js"></script>	
	<link rel="stylesheet" type="text/css" href="./css/styles-editor.css" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div id="recordContainer">
		<form action="" id="recordForm" method="post">
		
			<table>
				<tr>
					<td>id:</td>
					<td>
					<%-- When editing a record, 'id' control is disabled, because
							 a record with changed id is already another record.
					 --%>
					<input type="text" class="field" name="id" id="bookIdInput"
						value="<c:out value="${saveBook.bookId}"/>" placeholder="integer"
						 
							<c:if test="${not empty saveBook.bookId}">
							<c:out value="${'disabled'}"/>
							</c:if>	
													
						>
					<input type="hidden" value="${saveBook.bookId}" id=disabledIdControlByPass">
					<input type="hidden" id="addFlag"  name="add"  value="">
					<input type="hidden" id="saveFlag" name="save" value=""> 	
					</td>
				</tr>
				<tr>
					<td>Published:</td>
					<td>
					<input type="text" class="field" name="published" 
						value="<c:out value="${saveBook.bookPublished}"/>" placeholder="YYYY-MM-DD"></td>
				</tr>
				<tr>
					<td>Author:</td>
					<td>
					<input type="text" class="field" name="author" 
						value="<c:out value="${saveBook.bookAuthor}"/>" placeholder="Author's name"></td>
				</tr>
				<tr>
					<td>Title:</td>
					<td>
					<input type="text" class="field" name="name" 
						value="<c:out value="${saveBook.bookName}"/>" placeholder="Book title"></td>
				</tr>
				<tr>
					<td>Number of pages:</td>
					<td>
					<input type="text" class="field" name="number_of_pages" 
						value="<c:out value="${saveBook.bookNumberOfPages}"/>" placeholder="integer"></td>
				</tr>
				<tr>
					<td>Price:</td>
					<td>
					<input type="text" class="field" name="price" 
						value="<c:out value="${saveBook.bookPrice}"/>" placeholder="double"></td>
				</tr>
				<tr>
					<td>Availability:</td>
					<td>
					<input type="text" class="field" name="availability" 
						value="<c:out value="${saveBook.bookAvailability}"/>" placeholder="0 or 1"></td>
				</tr>
			</table>
			
		</form>
	</div>
	<div id="recordControls">
		<c:if test="${empty save }">
			<a class="btn btn-primary" name="adder" id="add"  onclick="addRecord()">ADD</a>
		</c:if>
		<c:if test="${not empty save }">
			<a class="btn btn-primary" name="saver"	id="save" onclick="saveRecord()">SAVE</a>
		</c:if>
			<a class="btn btn-primary" name="cancel" id="cancel" href="./cancel" >CANCEL</a>
	</div>
</body>
</html>