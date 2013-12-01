<?php

require_once('connect.php');

$name = $_REQUEST['nam'];
$id = $_REQUEST['userid'];
$pass = $_REQUEST['passs'];


//checks for empty fields
 if ($name==null||$id==null||$pass==null)
 { 
 echo "1";
 exit();
  }
//checks double user name registration
else {

$dupe1 = mysql_num_rows(mysql_query("select * from managers where email='$id'")); 
 
        if ($dupe1 > 0) { 
  
 echo "2";
 exit();
}

}

//inserts data into the database
$sql = "INSERT INTO managers (names,email,password)
		VALUES ('$name','$id','$pass')";


$result = mysql_query($sql);

	if($result)
		{
			echo "4";
		}
	else
		{
			echo "5";
		}


?>






