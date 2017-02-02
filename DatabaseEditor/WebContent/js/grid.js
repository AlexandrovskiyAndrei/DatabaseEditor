/**
 * 
 */

function deletion() {
	var selected = document.getElementById("pickedChoices");
	if (selected.value !=="") {
		document.getElementById("deleteFlag").setAttribute("value", "yes");
		document.getElementById("selectedRows").submit();
	} else { alert ("There are no selected records to delete"); 
	}
}

function editing() {
	var selected = document.getElementById("pickedChoices");
	if ( selected.value.indexOf(",")>0 ) { 
		alert ("To edit a record, please select only one value");
	} else {	
		document.getElementById("editFlag").setAttribute("value", "yes");
		document.getElementById("selectedRows").submit();
	}
}