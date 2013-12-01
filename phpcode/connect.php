<?php	
	$db=mysql_connect("127.0.0.1","root","root")  or die ("Could not connect to database"); 
	
	mysql_select_db('PropertyApp', $db) or die('Error selecting database : ' . mysql_error());
	
		
	

?>