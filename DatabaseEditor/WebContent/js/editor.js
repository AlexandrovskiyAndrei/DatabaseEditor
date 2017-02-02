/**
 * 
 */

function addRecord() {
	document.getElementById("addFlag").setAttribute("value", "yes");
	document.getElementById("recordForm").submit();
}

function saveRecord() {
	document.getElementById("saveFlag").setAttribute("value", "yes");
	document.getElementById('bookIdInput').disabled = false;
	document.getElementById("recordForm").submit();
}	
