<?php

require_once('connect.php');

$idnumber = $_REQUEST['id'];
$bos = $_REQUEST['boss'];


//checks for empty fields
if (!$idnumber)
  { 
 echo "1";
 exit();
  }



else {

$dupe1 = mysql_num_rows(mysql_query("select * from `".$bos."` where phonenumber='$idnumber'")); 
 
        if ($dupe1 == 0) { 
  
 echo "2";
 exit();
 exit; 
}

}

//inserts data into the database
$sql = mysql_query("SELECT * FROM `".$bos."` WHERE phonenumber='$idnumber'");


while($row=mysql_fetch_array($sql))

{
       $field1= $row['name'];
       $field2= $row['amountloaned'];
      $field3= $row['collateral'];

	   echo "$field1#$field2#$field3";
	   
}	


?>

